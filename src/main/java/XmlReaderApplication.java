import model.xml.TransactionList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

public class XmlReaderApplication {

    public static void main(String[] args) throws JAXBException, XMLStreamException {
        TransactionList transactionList = parseXmlFile(args[0]);
        saveEntitiesToDb(transactionList);
    }

    public static TransactionList parseXmlFile(String path) throws XMLStreamException, JAXBException {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        StreamSource xml = new StreamSource(path);
        XMLStreamReader streamReader = factory.createXMLStreamReader(xml);
        streamReader.nextTag();

        while (!streamReader.getLocalName().equals("transactions")) {
            streamReader.nextTag();
        }

        JAXBContext context = JAXBContext.newInstance(TransactionList.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        JAXBElement<TransactionList> jb = unmarshaller.unmarshal(streamReader, TransactionList.class);
        streamReader.close();
        return jb.getValue();
    }

    public static void saveEntitiesToDb(TransactionList transactionList){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        transactionList.getTransactions().forEach(session::save);
        tx1.commit();
        session.close();
    }
}
