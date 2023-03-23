package interceptors.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String doSomeTests(){
    return "Tests are completed";
    }

    //OUTPUT in consolle dopo aver inviato la richiesta tramite postman:
    //Request URL::http://localhost:8080/test:: Start Time=1679550344137

    /**
     * Ma in questo caso come potrebbe servirmi l'interceptor?
     * Ad esempio se sono in ambiente produttivo e voglio bloccare
     * tutte le richieste all'URL /test, perché non ho più bisogno dell'endpoint di test
     * Aggiungo un altro interceptor!
     */

}
