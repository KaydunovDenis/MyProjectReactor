package kaydunov.internationalization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * By default, a Spring Boot application will look for message files containing internationalization
 * keys and values in the src/main/resources folder.
 * <p>
 * The file for the default locale will have the name messages.properties, and files for
 * each locale will be named messages_XX.properties, where XX is the locale code.
 * <p>
 * The keys for the values that will be localized have to be the same in every file,
 * with values appropriate to the language they correspond to.
 */
@SpringBootApplication
public class InternationalizationApp {

    public static void main(String[] args) {
        SpringApplication.run(InternationalizationApp.class, args);
    }


}
