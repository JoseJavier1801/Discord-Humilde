package dev.IESFranciscodelosRios.Utils;

import dev.IESFranciscodelosRios.Domain.Model.UserList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLManager {
    public static <T> boolean writeXML(UserList c, String fichero, Class<UserList> userClass) {
        boolean result = false;
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(c.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            m.marshal(c, new File("XML\\"+fichero));
            result = true;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Metodo para leer los datos de los datos de los ficheros XML
     * @return
     */
    public static <T> T readXML(T c, String fichero) {
        File file=new File("XML\\"+fichero);
        if(file.exists()){
            JAXBContext context;
            try {
                context = JAXBContext.newInstance(c.getClass());
                Unmarshaller m = context.createUnmarshaller();
                c= (T) m.unmarshal(new File("XML\\"+fichero));
            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return c;
    }
}

