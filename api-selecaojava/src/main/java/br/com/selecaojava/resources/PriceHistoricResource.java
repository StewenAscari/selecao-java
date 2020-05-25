package br.com.selecaojava.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.selecaojava.domain.PriceHistoric;
import br.com.selecaojava.domain.User;
import br.com.selecaojava.dto.PriceHistoricDTO;
import br.com.selecaojava.dto.UserDTO;
import br.com.selecaojava.services.PriceHistoricService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/priceHistoric")
public class PriceHistoricResource {

	@Autowired
	private PriceHistoricService priceHistoricService;
	
	@ApiOperation(value = "Buscar um historico de preço por id", response = PriceHistoric.class, notes = "Essa operação Busca um registro do histórico de preco.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna um ResponseEntity com o objeto", response = PriceHistoric.class),
			@ApiResponse(code = 404, message = "Caso tenhamos algum erro vamos retornar uma mensagem e um código http")

	})
	@RequestMapping(value = "/consulta/{id}", method = RequestMethod.GET)
	public ResponseEntity<PriceHistoric> listar(@PathVariable Integer id) {
		PriceHistoric obj = priceHistoricService.find(id);

		if (obj == null) {
			return new ResponseEntity("Venda não encotrada!", HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(obj);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT) //update
	public ResponseEntity<Void> update(@Valid @RequestBody PriceHistoricDTO objDto,@PathVariable Integer id) throws ObjectNotFoundException{
		PriceHistoric obj = priceHistoricService.fromDTO(objDto);
		obj = priceHistoricService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE) //Delete ADM
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws ObjectNotFoundException {
		priceHistoricService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET) //list all 
	public ResponseEntity<List<PriceHistoricDTO>> find() {
		List<PriceHistoric> list = priceHistoricService.findAll();
		List<PriceHistoricDTO> listDTO = list.stream().map(obj -> new PriceHistoricDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}
