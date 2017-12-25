package dbase.config;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // возвращает корневую конфигурации приложения (сервисы и дао-уровень)
        return  new Class<?>[] {AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // возвращает конфигураци сервлета(веб-уровень, который включает в себя контроллеры)
        return new Class<?>[]{DispatcherServletConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        // возвращает путь, на который мэппится данный сервлет
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters(){
        // создание фильтра кодировки, который позволит работать с русскими символами
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        // создание фильтра, который добавляет поддержку  HTTP методов(например,таких как PUT)
        HiddenHttpMethodFilter httpMethodFilter = new HiddenHttpMethodFilter();
        return new Filter[]{characterEncodingFilter, httpMethodFilter};
    }
}