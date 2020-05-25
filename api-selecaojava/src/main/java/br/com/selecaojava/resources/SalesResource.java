package br.com.selecaojava.resources;

import java.io.BufferedReader;  
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.hibernate.hql.spi.PositionalParameterInformation;
import org.hibernate.loader.custom.sql.PositionalParamBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVParser;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import br.com.selecaojava.domain.PriceHistoric;
import br.com.selecaojava.domain.Sales;
import br.com.selecaojava.domain.User;
import br.com.selecaojava.dto.ModelCSVDTO;
import br.com.selecaojava.dto.PriceHistoricDTO;
import br.com.selecaojava.dto.SalesDTO;
import br.com.selecaojava.repositories.SalesRepository;
import br.com.selecaojava.services.UserService;
import br.com.selecaojava.services.PriceHistoricService;
import br.com.selecaojava.services.SalesServices;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/user/{id}/sales")
public class SalesResource {
	
	@Autowired
	private SalesServices vendasServices;
	@Autowired
	private UserService userService;
	private PriceHistoricService priceHistoricService;
	
	/*
	 * Operação PUT
	 */
	@ApiOperation(value = "importar arquivo csv", response = Sales.class, 
			notes = "Essa operação Edita um novo registro propriedades")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna um ResponseEntity com o objeto e o status", response = Sales.class),
			@ApiResponse(code = 406, message = "Caso o arquivo não sejam aceita retorna um erro")

	})
	@RequestMapping(value="/upload-csv-file", method = RequestMethod.PUT)
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model, 
    		@Valid @RequestPart @RequestParam("id") @PathVariable Integer id) {
		User user = userService.find(id);
		
        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `Vendas` objects
        	try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_16))) {
        		
                // create csv bean reader
                CsvToBean<ModelCSVDTO> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(ModelCSVDTO.class)
                        .withSkipLines(1)
                        .withSeparator(' ')
                        .withSeparator('\t')
                        .build();
                // convert `CsvToBean` object to list of users
                List<ModelCSVDTO> sales = csvToBean.parse();
                List<Sales> salesDB = new ArrayList<Sales>();
                
                for (ModelCSVDTO s: sales) {
                	if(s.getRegiaoSigla() != null && s.getEstadoSigla()!= null &&
                			s.getMunicipio()!= null &&
                			s.getRevenda()!= null && s.getCnpjdaRevenda()!= null && 
                			s.getProduto()!= null && s.getDatadaColeta()!= null && 
                			s.getValordeVenda()!= null && s.getValordeCompra()!= null &&
                			s.getUnidadedeMedida()!= null && s.getBandeira()!= null) {
                		PriceHistoric priceHistoric = new PriceHistoric(s.getRevenda(), s.getValordeVenda());
                		Sales r = new Sales(s.getRegiaoSigla().toLowerCase(), s.getEstadoSigla().toLowerCase(), 
                				s.getMunicipio().toLowerCase(), s.getRevenda().toLowerCase(), s.getCnpjdaRevenda(), 
                				s.getProduto().toLowerCase(), s.getDatadaColeta(), s.getValordeVenda() ,s.getValordeCompra(),
                    			s.getUnidadedeMedida(), s.getBandeira(), user);
                		salesDB.add(r);
                		
                	}
                	
                	
                }
                vendasServices.insert(salesDB);
                priceHistoricService.insertAll(salesDB);
                
                // TODO: save users in DB?
//                vendasServices.insert(sales);
//                user.setSales(sales);;
                // save users list on model
                model.addAttribute("sales", sales);
                model.addAttribute("status", true);

//                priceHistoricService.insert(p);
                userService.update(user);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }
        

        return "file-upload-status";
    }
	
	@RequestMapping(value = "/find/{sigla}", method = RequestMethod.GET) //lists all
	public List<Sales> sigla(@PathVariable String sigla) {
		List<Sales> obj = vendasServices.findBysigla(sigla);
		if (obj == null) {
			return (List<Sales>) new ResponseEntity("Venda não encotrada!", HttpStatus.NOT_FOUND);
		}
		return obj;
	}
	@ApiOperation(value = "Buscar uma venda por id", response = Sales.class, notes = "Essa operação Busca um registro com as informações de uma venda.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna um ResponseEntity com o objeto", response = Sales.class),
			@ApiResponse(code = 404, message = "Caso tenhamos algum erro vamos retornar uma mensagem e um código http")

	})
	@RequestMapping(value = "/consulta/{id}", method = RequestMethod.GET)
	public ResponseEntity<Sales> listar(@PathVariable Integer id) {
		Sales obj = vendasServices.find(id);

		if (obj == null) {
			return new ResponseEntity("Venda não encotrada!", HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(obj);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT) //update
	public ResponseEntity<Void> update(@Valid @RequestBody SalesDTO objDto,@PathVariable Integer id) throws ObjectNotFoundException{
		Sales obj = vendasServices.fromDTO(objDto);
		obj = vendasServices.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE) //Delete 
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws ObjectNotFoundException {
		vendasServices.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET) //list all 
	public ResponseEntity<List<SalesDTO>> find() {
		List<Sales> list = vendasServices.findAll();
		List<SalesDTO> listDTO = list.stream().map(obj -> new SalesDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}
