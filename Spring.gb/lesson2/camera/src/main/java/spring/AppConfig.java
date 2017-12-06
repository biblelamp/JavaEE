package spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import spring.lifecycle.TestBFPP;

@Configuration
@ComponentScan({"spring", "spring.lifecycle"})
public class AppConfig { }