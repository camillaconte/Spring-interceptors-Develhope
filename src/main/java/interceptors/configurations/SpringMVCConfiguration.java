package interceptors.configurations;

import interceptors.interceptors.APILoggingInterceptor;
import interceptors.interceptors.LoggedUserInterceptor;
import interceptors.interceptors.TestInterceptor;
//import jdk.incubator.vector.VectorOperators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class SpringMVCConfiguration implements WebMvcConfigurer {

    /**
     * Ora inietto il mio interceptor
     */

    @Autowired
    private APILoggingInterceptor apiLoggingInterceptor;

    @Autowired
    private TestInterceptor testInterceptor;

    @Autowired
    private LoggedUserInterceptor loggedUserInterceptor;

    /**
     * NB: L'ordine degli interceptors all'interno del metodo che segue
     * ne determina anche l'ordine di esecuzione!
     * Quindi:
     * - prima viene eseguito il preHandle del primo, poi il preHandle del secondo e così via...
     * - poi viene eseguito il postHandle del primo, poi il postHandle del secondo e così via...
     * - poi viene eseguito il afterCompletion del primo, poi l'afterCompletion del secondo e così via...
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiLoggingInterceptor);
        //se ho più di un interceptor, basta aggiungere una riga!
        registry.addInterceptor(testInterceptor);
        registry.addInterceptor(loggedUserInterceptor);
    }
}
