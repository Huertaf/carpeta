package es.caib.carpeta.front.security;

import es.caib.carpeta.core.service.SecurityService;
import es.caib.carpeta.core.utils.StringUtils;
import es.caib.carpeta.front.config.LoginRequestCache;
import es.caib.carpeta.front.utils.SesionHttp;
import es.caib.carpeta.utils.CarpetaConstantes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
public class LoginController {

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    SecurityService securityService;

    @Autowired
    private LoginRequestCache loginRequestCache; //Cache peticiones

    @Autowired
    private SesionHttp sesionHttp;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Value("${es.caib.carpeta.loginib.entidad}")
    private String entidad;


    @RequestMapping(value="/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (log.isInfoEnabled()) log.info("Dentro de LoginController");

        // Error login
        if ("true".equals(request.getParameter("error"))) {
            if (log.isErrorEnabled()) log.error("Error de login");
        }
        final SavedRequest savedRequest = loginRequestCache.getRequest(request, response);

        if(savedRequest != null && existeTicket(savedRequest)){
            return autenticarTicket(savedRequest, CarpetaConstantes.TICKET_USER_CLAVE);
        } else {

            if(savedRequest != null){
                if (log.isDebugEnabled()) log.debug("Punto de entrada: " + savedRequest.getRedirectUrl());

                sesionHttp.setUrlEntrada(getUrlEntrada(savedRequest.getRedirectUrl()));

                if (log.isDebugEnabled()) log.debug("iniciarSesionAutenticacion: " + savedRequest.getRedirectUrl());
            }

            String url = securityService.iniciarSesionAutentificacion();

            if (log.isDebugEnabled()) log.debug("Url autenticacion: " + url);

            return new ModelAndView("redirect:"+url);

        }

    }

    @RequestMapping(value="/redirigirLogin")
    public ModelAndView redirigirLogin(HttpServletRequest request, HttpServletResponse response) {

        if (log.isDebugEnabled()) log.debug("Dentro de redirigirLogin: " + sesionHttp.getUrlEntrada());

        // Gestionamos la url de entrada a la aplicación previa a autenticarse
        try {
            if(StringUtils.isNotEmpty(sesionHttp.getUrlEntrada())){
                response.sendRedirect(sesionHttp.getUrlEntrada());
            }else{
                response.sendRedirect("/carpeta");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return new ModelAndView("inicio");

    }

    @RequestMapping(value="/salir")
    public String salir(HttpServletRequest request) throws Exception{

        if (log.isInfoEnabled()) log.info("Dentro de salir");

        securityService.iniciarSesionLogout();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/inicio";

    }

    /**
     * Autentica via ticket.
     *
     * @param request Request
     * @param ticketUserName Usuario asociado al tipo de ticket
     * @return Vista que realiza el login automáticamente
     */
    private ModelAndView autenticarTicket(SavedRequest request, String ticketUserName) throws Exception {

        ModelAndView mav = new ModelAndView("loginTicket");

        // Obtenemos ticket de la peticion
        final String[] tickets = request.getParameterMap().get(CarpetaConstantes.TICKET_PARAM);
        if (tickets == null || tickets.length != 1) {
            throw new Exception("No existe ticket");
        }
        final String ticket = tickets[0];

        if (log.isDebugEnabled()) log.debug("Autenticando el ticket: " + tickets[0]);

        // Autenticamos automaticamente
        mav.addObject("ticketName", ticketUserName);
        mav.addObject("ticketValue",ticket);

        return mav;
    }

    /**
     * Comprueba si existe ticket en request.
     *
     * @param request Request
     * @return boolean
     */
    private boolean existeTicket(SavedRequest request) {

        final String[] tickets = request.getParameterMap().get(CarpetaConstantes.TICKET_PARAM);
        if (tickets == null || tickets.length != 1) {
            return false;
        }
        if (log.isInfoEnabled()) log.info("Existe un ticket de autenticacion: "+ tickets.length);

        return true;
    }

    /**
     *
     * @param urlEntrada
     * @return
     */
    private String getUrlEntrada(String urlEntrada){
        try {

            // Opcional: Comprobar si coincide con alguno de los mappings definidos
            /*for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : requestMappingHandlerMapping.getHandlerMethods().entrySet()) {
                System.out.println("Item : " + entry.getKey().getPatternsCondition() + " Count : " + entry.getValue().getReturnType());
            }*/

           URL url = new URL(urlEntrada);

           return url.getPath();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }
}
