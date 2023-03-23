package interceptors.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class APILoggingInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                            throws Exception {
        long startTime = System.currentTimeMillis();
        System.out.println("Request URL::" + request.getRequestURL().toString()
                            + ":: Start Time=" + System.currentTimeMillis());
        request.setAttribute("startTime", startTime);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                            @Nullable ModelAndView modelAndView) throws Exception {
        //dopo che ho eseguito la logica all'interno del controller
        //voglio semplicemente stampare il tempo:
        System.out.println("Request URL::" +  request.getRequestURL().toString() +
                           "Sent to Handler :: Current Time=" +System.currentTimeMillis());
    }

    /**
     * Concetto di "operazioni di logging?"
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                 @Nullable Exception ex) throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        System.out.println("Request URL::" +  request.getRequestURL().toString() +
                           ":: End Time=" +System.currentTimeMillis());
        System.out.println("Request URL::" +  request.getRequestURL().toString() +
                           "Time Taken=" + (System.currentTimeMillis() - startTime));
        if(ex != null){
            System.out.println("The exeption is" + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
