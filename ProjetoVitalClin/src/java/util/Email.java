/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modelo.Agendamento;
import modelo.Cliente;
import modelo.Usuario;

/**
 *
 * @author bruno
 */
public class Email {
     public boolean enviarBoasVindas(Cliente cliente) {

        try {
            Properties props = System.getProperties();

            String from = "grupovitalclin@gmail.com";
            String host = "smtp.gmail.com";
            String pass = "toninho123";
            String nome = cliente.getNome();
            String emailDestino = cliente.getUsuario().getEmail();

            String to = emailDestino; //aqui sera o email para quem vc vai enviar a mensagem

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", from);
            props.put("mail.smtp.password", pass);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            // To get the array of addresses
            InternetAddress obj = new InternetAddress(to);

            message.addRecipient(Message.RecipientType.TO, obj);

            message.setSubject("Clínica VitalBeauty - Seja Bem vindo");
            //message.setText("OlÃ¡ teste");
            
            message.setContent("<html><body><h1>Olá, " + nome + "</h1><h2>Seja bem vindo(a) a nossa clínica...</h2><p>"
                    + " Esperamos que goste de nossos tratamentos.</p><h1>Clínica Vital Beauty</h1></body></html>", "text/html; charset=utf-8");
            

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (AddressException ae) {
            ae.printStackTrace();
            return false;
        } catch (MessagingException me) {
            me.printStackTrace();
            return false;
        }
    }
     
     
        public boolean enviarDadosAgendamento(Agendamento agendamento) {

        try {
            Properties props = System.getProperties();

            String from = "grupovitalclin@gmail.com";
            String host = "smtp.gmail.com";
            String pass = "toninho123";
            String nome = agendamento.getCliente().getNome();
            String tratamento = agendamento.getTratamento().getDescricao();
            LocalDate data = agendamento.getData();
            String hora = agendamento.getHora();
            String emailDestino = agendamento.getCliente().getUsuario().getEmail();

            String to = emailDestino ; //aqui sera o email para quem vc vai enviar a mensagem

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", from);
            props.put("mail.smtp.password", pass);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            // To get the array of addresses
            InternetAddress obj = new InternetAddress(to);

            message.addRecipient(Message.RecipientType.TO, obj);

            message.setSubject("Cliníca VitalBeauty - Detalhes do Agendamento");
            
             DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            message.setContent("<html><body><h1>Olá, " + nome + "</h1><h2>Seu agendamento foi realizado com sucesso!!!</h2><p>"
                    + "Você agendou o tratamento " + tratamento + " para o dia <strong>" + data.format(formatador) + "</strong> ás <strong>" + hora + "</strong> horas. Aguardamos sua presença! </a></p><h3>Clínica vital beauty</h3></body></html>", 
                    "text/html; charset=utf-8");

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (AddressException ae) {
            ae.printStackTrace();
            return false;
        } catch (MessagingException me) {
            me.printStackTrace();
            return false;
        }
    }
        
}
