package interceptors.controllers;

import interceptors.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    /**
     * Attenzione: quando mando la richiesta tramite POSTMAN,
     * perché mi restituisca effettivamente un JSON
     * che rappresenta l'utente
     * devo aver settato nella sezione Header
     * "userId" e il numero di id che mi interessa!!!
     */
    @GetMapping
    public User get(HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        return user;
    }
}
