package com.example.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.example.test.client.SOAPTest_Client;

@Configuration
public class SoapConfig {
    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        marshaller.setContextPath("com.example.test.wsdl");

        return  marshaller;
    }

    @Bean
    public SOAPTest_Client obtenerClienteSoap(Jaxb2Marshaller marshaller){
    	SOAPTest_Client soapClient = new SOAPTest_Client();

        soapClient.setDefaultUri("https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx");
        soapClient.setMarshaller(marshaller);

        soapClient.setUnmarshaller(marshaller);

        return soapClient;
    }
}
