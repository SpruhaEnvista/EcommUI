package com.envista.msi.api.SampleCode;

/**
 * Created by Sreenivas on 6/19/2017.
 */
import java.io.File;
import javax.xml.bind.*;

public class Demo {

    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Transition.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        File xml = new File("C:\\Users\\user\\Desktop\\Sample\\Sample.xml");
        Transition transition = (Transition) unmarshaller.unmarshal(xml);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(transition, System.out);
    }

}