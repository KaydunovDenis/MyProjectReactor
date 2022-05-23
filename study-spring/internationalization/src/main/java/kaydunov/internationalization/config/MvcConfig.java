package kaydunov.internationalization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Properties;

@Configuration
@ComponentScan("kaydunov.internationalization.config")
public class MvcConfig implements WebMvcConfigurer {

    /**
     * The LocaleResolver interface has implementations that determine the current locale based on the session,
     * cookies, the Accept-Language header, or a fixed value.
     * In our example, we have used the session based resolver SessionLocaleResolver
     * and set a default locale with value US.
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    /**
     * Next, we need to add an interceptor bean that will switch to a new locale based
     * on the value of the lang parameter appended to a request
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    public static String getDataProperties (String param) throws Exception {
        Properties props=new Properties();
        props.load(new InputStreamReader(new FileInputStream("system.properties"), "UTF-8"));
        return props.getProperty(param);
    }
}
