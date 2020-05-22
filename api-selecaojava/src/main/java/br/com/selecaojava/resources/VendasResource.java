package br.com.selecaojava.resources;

import java.io.BufferedReader;  
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.hql.spi.PositionalParameterInformation;
import org.hibernate.loader.custom.sql.PositionalParamBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import br.com.selecaojava.domain.Sales;
import br.com.selecaojava.domain.User;
import br.com.selecaojava.dto.VendasDTO;
import br.com.selecaojava.repositories.VendasRepository;
import br.com.selecaojava.services.UserService;
import br.com.selecaojava.services.VendasServices;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/user/{id}/sales")
public class VendasResource {
	
	@Autowired
	private VendasServices vendasServices;
	@Autowired
	private UserService userService;
	
	/*
	 * Operação PUT
	 */
	@ApiOperation(value = "Insere s", response = Sales.class, 
			notes = "Essa operação Edita um novo registro propriedades")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna um ResponseEntity com o objeto e o status", response = Sales.class),
			@ApiResponse(code = 406, message = "Caso as imagens não sejam aceitas retorna um erro")

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
        	try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        		
                // create csv bean reader
                CsvToBean<Sales> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Sales.class)
                        .withSkipLines(1)
//                        .withSeparator(',')
                        .withSeparator('\t')
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                
                // convert `CsvToBean` object to list of users
                List<Sales> sales = csvToBean.parse();
                // TODO: save users in DB?
                vendasServices.insert(sales);
                user.setSales(sales);;
                // save users list on model
                model.addAttribute("sales", sales);
                model.addAttribute("status", true);
                
                userService.update(user);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }
        

        return "file-upload-status";
    }
	
	@RequestMapping(value = "/{sigla}", method = RequestMethod.GET) //lists all paged ADMs
	public ResponseEntity<List<VendasDTO>> findPage(@PathVariable String sigla) {
		List<Sales> list = vendasServices.findAll(sigla);
		List<VendasDTO> listDTO = list.stream().map(obj -> new VendasDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@ApiOperation(value = "Buscar uma Imagem por ID", response = Sales.class, notes = "Essa operação Busca um novo registro com as informações de uma Propriedade.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna um ResponseEntity com o objeto", response = Sales.class),
			@ApiResponse(code = 404, message = "Caso tenhamos algum erro vamos retornar uma mensagem e um código http")

	})
	@RequestMapping(value = "/consulta/{id}", method = RequestMethod.GET)
	public ResponseEntity<Sales> listar(@PathVariable Integer id) {
		Sales obj = vendasServices.find(id);

		if (obj == null) {
			return new ResponseEntity("Imagem não encotrada!", HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(obj);
	}
}
