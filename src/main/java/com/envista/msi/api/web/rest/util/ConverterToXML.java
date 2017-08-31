package com.envista.msi.api.web.rest.util;

import com.envista.msi.api.web.rest.dto.rtr.RequestDto;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.*;
import javax.xml.stream.*;
import java.io.*;

/**
 * Created by Sreenivas on 6/16/2017.
 */
public class ConverterToXML {

    private JAXBContext jaxbContext;

    public ConverterToXML() {
        try {
            // create context with ":" separated list of packages that
            // contain your JAXB ObjectFactory classes
            jaxbContext = JAXBContext.newInstance(
                    "com.intertech.consulting"
                            + ":org.w3._2003._05.soap_envelope");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String marshal(JAXBElement<?> jaxbElement) {
        try {
            // Marshallers are not thread-safe.  Create a new one every time.
            Marshaller marshaller = jaxbContext.createMarshaller();
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(jaxbElement, stringWriter);
            return stringWriter.toString();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    public static  void  main(String abs[]){

        RequestDto rdto = new RequestDto();
        rdto.setRequestId("12");
        ConverterToXML cx = new ConverterToXML();
        System.out.println();
    }
}
