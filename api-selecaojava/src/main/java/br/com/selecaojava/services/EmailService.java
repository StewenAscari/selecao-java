package br.com.selecaojava.services;

import org.springframework.mail.SimpleMailMessage; 
import org.springframework.stereotype.Service;

import br.com.selecaojava.domain.User;


@Service
public interface EmailService {
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(User user, String newPass);
}