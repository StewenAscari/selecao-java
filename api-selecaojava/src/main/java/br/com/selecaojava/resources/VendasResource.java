package br.com.selecaojava.resources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.hql.spi.PositionalParameterInformation;
import org.hibernate.loader.custom.sql.PositionalParamBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import br.com.selecaojava.domain.Vendas;
import br.com.selecaojava.dto.VendasDTO;
import br.com.selecaojava.repositories.VendasRepository;
import br.com.selecaojava.services.VendasServices;

@RestController
@RequestMapping(value="/vendas")
public class VendasResource {
	
	@Autowired
	private VendasServices vendasServices;
	@Autowired
	private VendasRepository vendasRepository;
	
	@RequestMapping(value="/upload-csv-file", method = RequestMethod.POST)
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `Vendas` objects
        	try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        		
                // create csv bean reader
                CsvToBean<Vendas> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Vendas.class)
                        .withSkipLines(1)
//                        .withSeparator(',')
                        .withSeparator('\t')
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                
                // convert `CsvToBean` object to list of users
                List<Vendas> vendas = csvToBean.parse();
                // TODO: save users in DB?
                vendasServices.insert(vendas);
                // save users list on model
                model.addAttribute("users", vendas);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }
        

        return "file-upload-status";
    }
	
	@RequestMapping(value = "/{sigla}", method = RequestMethod.GET) //lists all paged ADMs
	public ResponseEntity<List<VendasDTO>> findPage(@PathVariable String sigla) {
		List<Vendas> list = vendasServices.findAll(sigla);
		List<VendasDTO> listDTO = list.stream().map(obj -> new VendasDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}
