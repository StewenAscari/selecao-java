package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	
	@Value("${contato.disco.raiz}")
	private String raiz = "/home/alvaro/Documents/projetos";
	
	@Value("${contato.disco.diretorio-fotos}")
	private String diretorioFotos = "imagensdoprojeto";
	
	public String salvarFoto(MultipartFile foto) {
		return this.salvar(this.diretorioFotos, foto);
	}
	
	public String salvar(String diretorio, MultipartFile arquivo) {
		
		
		Path diretorioPath = Paths.get(this.raiz, diretorio);		
		Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());		
		
		try {
			Files.createDirectories(diretorioPath);
			
			arquivo.transferTo(arquivoPath.toFile());
			
			return arquivoPath.toString();
		} catch (IOException e) {
			
			throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
		}		
	}
	
	public Boolean deletar(String url) {
		File file = new File(url);
		return file.delete();
	}
}