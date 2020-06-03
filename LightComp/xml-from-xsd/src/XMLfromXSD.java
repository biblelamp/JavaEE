// from https://www.javacodegeeks.com/2011/02/jaxb-generate-xml-xsd.html
// generate classes from xsd file:
// xjc.exe expense.xsd

import generated.ItemT;
import generated.UserT;
import generated.ObjectFactory;
import generated.ItemListT;
import generated.ExpenseT;
import org.xml.sax.SAXException;

import java.math.BigDecimal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.validation.ValidatorHandler;

public class XMLfromXSD {
    public static void main(String[] args) throws JAXBException, SAXException {
        ObjectFactory factory = new ObjectFactory();

        UserT user = factory.createUserT();
        user.setUserName("Sergey");
        ItemT item = factory.createItemT();
        item.setItemName("Seagate External HDD");
        item.setPurchasedOn("August 24, 2010");
        item.setAmount(new BigDecimal("6776.5"));

        ItemListT itemList = factory.createItemListT();
        itemList.getItem().add(item);

        ExpenseT expense = factory.createExpenseT();
        expense.setUser(user);
        expense.setItems(itemList);

        // validate using Schema and xsd file
        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        Schema schema = schemaFactory
                .newSchema(new StreamSource(XMLfromXSD.class.getResourceAsStream("expence.xsd")));

        JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
        JAXBElement<ExpenseT> element = factory.createExpenseReport(expense);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setSchema(schema);
        marshaller.marshal(element, System.out);
    }
}