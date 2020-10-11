package com.security4.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private final Log log = LogFactory.getLog(this.getClass());
	//be kell állítani a feladó email címet (ki tudjuk venni az application.properties-ből)
	@Value("${spring.mail.username}")
	private String MESSAGE_FROM;
	
	//ezen keresztül az objektumon keresztül tudunk üzeneteket küldeni
	private JavaMailSender javaMailSender;

	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	//ezzel küldünk emailt, át kell adni azt az email címet ahová küldjük a mailt.
	public void sendMessage(String email) {
		//ezt az objektumot tudjuk feltölteni a tartalommal:
		/* - from email
		 * - to email
		 * - tárgy
		 * - üzenet*/
		SimpleMailMessage msg = null;
		try {
			msg = new SimpleMailMessage();
			msg.setFrom(MESSAGE_FROM);
			msg.setTo(email);
			msg.setSubject("Sikeres regisztrálás");
			msg.setText("Kedves " + email + "! \n\n Köszönjük, hogy regisztráltál az oldalunkra");
			javaMailSender.send(msg);
		} catch (Exception e) {
			log.error("hiba email küldéskor az alábbi címre: " + email + " " + e);
		}
	}
}
