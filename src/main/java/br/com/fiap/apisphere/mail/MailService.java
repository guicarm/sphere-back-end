package br.com.fiap.apisphere.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.fiap.apisphere.user.User;

@Service
public class MailService {
    
    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void sendWellcomeMail(User user){

        var email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Boas Vindas");
        email.setText("""
                Olá, %s.

                Ficamos felizes em ver você por aqui!

                Att
                Sphere
        """.formatted(user.getName()));

        mailSender.send(email);
    }
}
