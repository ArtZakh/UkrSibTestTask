import model.xml.Client;
import model.xml.Transaction;
import model.xml.TransactionList;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class XmlReaderTest {

    @Test
    public void should_read_xml_file_correctly() throws JAXBException, XMLStreamException {
        TransactionList transactionList = XmlReaderApplication.parseXmlFile("src/main/resources/Java_test.xml");
        assertEquals(transactionList.getTransactions().size(), 12);
        List<Client> clients = transactionList.getTransactions()
                .stream()
                .map(Transaction::getClient)
                .collect(Collectors.toList());
        assertEquals(clients.size(), 12);
    }

    @Test(expected = XMLStreamException.class)
    public void should_throw_exception_when_file_absent() throws JAXBException, XMLStreamException {
        TransactionList transactionList = XmlReaderApplication.parseXmlFile("src/main/resources/.xml");
    }



}
