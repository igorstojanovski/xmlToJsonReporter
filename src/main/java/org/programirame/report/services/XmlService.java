package org.programirame.report.services;

import org.programirame.report.ReportGenerationException;
import org.programirame.report.model.xml.MainType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Xml related service methods common to all report generators.
 *
 */
public class XmlService {

    /**
     * Generates a main type model based on the give file. The given file should contain xml that is valid according
     * to the sample.xsd schema. Otherwise, parsing will be incorrect and exception will be thrown.
     *
     * @param dataSource file containg the data to transform
     */
    public MainType getMainType(File dataSource) throws ReportGenerationException {
        MainType unmarshal;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(MainType.class);

            XMLInputFactory xif = XMLInputFactory.newFactory();
            xif.setProperty(XMLInputFactory.SUPPORT_DTD, false);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            XMLStreamReader xsr = xif.createXMLStreamReader(new FileInputStream(dataSource));

            unmarshal = (MainType) jaxbUnmarshaller.unmarshal(xsr);
        } catch (JAXBException | XMLStreamException | FileNotFoundException e) {
            throw new ReportGenerationException("Error while generating the main type object", e);
        }

        return unmarshal;
    }
}
