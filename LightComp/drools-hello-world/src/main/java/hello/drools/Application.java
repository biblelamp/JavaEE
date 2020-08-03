package hello.drools;

import hello.drools.model.Product;
import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.WorkingMemory;
import org.drools.core.rule.Package;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Application {

    static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main( String[] args ) throws IOException, DroolsParserException {
        new Application();
    }

    public Application() throws IOException, DroolsParserException {

        String ruleFile = "/rule/rules.drl";
        InputStream resourceAsStream = getClass().getResourceAsStream(ruleFile);
        Reader reader = new InputStreamReader(resourceAsStream);

        PackageBuilder packageBuilder = new PackageBuilder();
        packageBuilder.addPackageFromDrl(reader);
        Package rulesPackage = packageBuilder.getPackage();
        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage(rulesPackage);

        WorkingMemory workingMemory = ruleBase.newStatefulSession();

        Product product = new Product();
        product.setType("gold");

        workingMemory.insert(product);
        workingMemory.fireAllRules();

        log.info("The discount for the '{}' is {}%", product.getType(), product.getDiscount());
    }
}
