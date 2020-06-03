// from https://www.javacodegeeks.com/2011/02/jaxb-generate-xml-xsd.html
// generate classes from xsd file:
// xjc.exe expense.xsd

import generated.ItemT;
import generated.UserT;
import generated.ObjectFactory;
import generated.ItemListT;
import generated.ExpenseT;

import java.math.BigDecimal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import javax.xml.validation.ValidatorHandler;

public class XMLfromXSD {
    public static void main(String[] args) throws JAXBException {
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

        // schema?
        Schema schema = new Schema() {
            @Override
            public Validator newValidator() {
                return null;
            }

            @Override
            public ValidatorHandler newValidatorHandler() {
                return null;
            }
        };

        JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
        JAXBElement<ExpenseT> element = factory.createExpenseReport(expense);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        //marshaller.setSchema();
        marshaller.marshal(element, System.out);
    }
}