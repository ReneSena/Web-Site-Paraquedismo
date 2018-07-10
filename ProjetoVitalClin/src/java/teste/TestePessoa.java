/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import modelo.*;

/**
 *
 * @author bruno
 */
public class TestePessoa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        
   String data = "15/02/2015";
   String hora = "09:00";
 
   DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
   
   LocalDate hoje = LocalDate.now(); // pega data de hoje
   
   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm");
   
   System.out.println(hoje);
   
   LocalTime agora = LocalTime.now();
   
   int horas = agora.getHour(); // pega as horas de agora
   
   String horaCerta = hora.substring(0,2);
    String horaCerta1 = hora.substring(1);
     String horaTotal = horaCerta + horaCerta1;
   
    int h1 = Integer.parseInt(horaCerta);
   //int h2 = Integer.parseInt(hora.substring(1));
   
   ///System.out.println(hoje.format(formatador));
   
   System.out.println(hoje);
   

  
   
   
  // System.out.println(agora.format(dtf));
   
   //System.out.println(horarioagora.format(dtf);
   
   
   
   
}
}
