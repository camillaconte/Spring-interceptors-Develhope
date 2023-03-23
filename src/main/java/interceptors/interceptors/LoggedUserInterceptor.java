package interceptors.interceptors;

import interceptors.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class LoggedUserInterceptor implements HandlerInterceptor {

    /**
     * Per fini didattici inseriamo qui della logica che normalmente andrebbe altrove
     * (poi lo vedremo più avanti)
     */

    /**
     * A fine video Lorenzo accenna al concetto di
     * "payload in entrata e payload in uscita": approfondire!!!
     */

    public static List<User> usersList = new ArrayList<>(Arrays.asList(
            new User(1, "Lorenzo", "De Francesco", "Pordenone"),
            new User(2, "Mario", "Rossi", "Palermo"),
            new User(3, "Luca", "Bianchi", "Milano"),
            new User(4, "Anna", "Verdi", "Roma")
    ));


    /*
     * Vediamo cosa farà questo metodo:
     * - abbiamo preso uno userId
     * - l'abbiamo TRASFORMATO IN UN UTENTE
     * - l'abbiamo passato al Controller
     * - così il Controller si ritrova già l'utente PRONTO per lavorarci sopra
     *   e non ha necessità di andare a fare la richiesta sul DB
     *   oppure di verificare che effettivamente quell'utente rispetti
     *   una condizione di autorizzazione: c'è un INTERCEPTOR che lo fa per lui
     *
     *   N.B. Nella pratica all'inizio di ogni metodo dell'interceptor
     *   dovrei specificare per quali chiamate API si applicano
     *   (altrimenti vengono applicati a tutte le chiamate)
     */

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String userIdString = request.getHeader("userId");
        if(userIdString == null) {
            return false;
        }
        long userId = Long.parseLong(userIdString);
        Optional<User> user = usersList.stream().filter(singleUser -> {
            return singleUser.getId() == userId;
        }).findFirst();
        if (user.isPresent()){
            //nel caso ci sia l'utente, vado a impostarlo all'interno della request
            //è molto importante perché in questo modo posso evitare
            //di dover ogni volta inserire una logica al DB per prendere gli utenti
            //anche perché quando lavoreremo a un progetto più complicato
            //vedremo che la logica non è così semplice
            //in quanto ci potrebbe essere anche un sottostrato di caching! (cque lo vedremo!)

            //n.b. inserisci prima della chiave "user" anche il nome del corrispondente interceptor
            //perché se avessi più interceptor e chiavi con lo stesso nome potrei fare confusione!
            request.setAttribute("LoggedUserInterceptor-user", user.get());
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
    }
}
