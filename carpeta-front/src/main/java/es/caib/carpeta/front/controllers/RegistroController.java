package es.caib.carpeta.front.controllers;

import es.caib.carpeta.core.service.RegWeb3Service;
import es.caib.carpeta.core.utils.Paginacion;
import es.caib.carpeta.front.config.UsuarioAutenticado;
import es.caib.regweb3.ws.api.v3.AsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.ResultadoBusquedaWs;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistroController {

    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    RegWeb3Service regWeb3Service;

    /**
     *
     */
    @RequestMapping(value = "/registros", method = RequestMethod.GET)
    public String listadoRegistros() {
        return "redirect:/registros/1";
    }

    /**
     *
     * @param pageNumber
     * @param authentication
     * @return
     */
    @RequestMapping(value = { "/registros/{pageNumber}"}, method = RequestMethod.GET)
    public ModelAndView registros(@PathVariable("pageNumber") Integer pageNumber, Authentication authentication) {

        ModelAndView mav = new ModelAndView("registros");

        UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado)authentication.getPrincipal();

        try {
            ResultadoBusquedaWs asientos = regWeb3Service.obtenerAsientosCiudadano(usuarioAutenticado.getUsuarioClave().getNif(), pageNumber-1);

            if(asientos != null){
                Paginacion paginacion = new Paginacion(asientos.getTotalResults(), pageNumber);
                paginacion.setListado(asientos.getResults());

                mav.addObject("paginacion", paginacion);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(value="/registro/**", method = RequestMethod.GET)
    public ModelAndView registro(HttpServletRequest request, Authentication authentication) {

        ModelAndView mav = new ModelAndView("registroDetalle");

        String numeroRegistro = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        log.info("Numero registro:" + numeroRegistro.replace("/registro/",""));

        UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado)authentication.getPrincipal();

        try {
            AsientoRegistralWs asiento = regWeb3Service.obtenerAsientoCiudadano(usuarioAutenticado.getUsuarioClave().getNif(), numeroRegistro.replace("/registro/",""));
log.info("anexos: " + asiento.getAnexos().size());
log.info("interesados: " + asiento.getInteresados().size());

            mav.addObject("asiento", asiento);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mav;
    }

}
