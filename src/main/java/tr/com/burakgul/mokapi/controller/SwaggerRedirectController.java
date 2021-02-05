package tr.com.burakgul.mokapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerRedirectController {

    @RequestMapping("/")
    public String redirectSwagger() {
        return "redirect:/swagger-ui/";
    }
}
