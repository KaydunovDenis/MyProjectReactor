package kaydunov.internationalization.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Let's create a controller mapping that will return a simple HTML page called international.
 * html that we want to see in two different languages:
 */
@Controller
public class PageController {

    @GetMapping("/international")
    public String getInternationalPage() {
        return "international";
    }
}