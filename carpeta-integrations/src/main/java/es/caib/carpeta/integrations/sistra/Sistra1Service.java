package es.caib.carpeta.integrations.sistra;

import es.caib.carpeta.commons.utils.UsuarioClave;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitePersistente;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitesPersistentes;
import es.caib.zonaper.ws.v2.model.usuarioautenticadoinfo.UsuarioAutenticadoInfo;
import es.caib.zonaper.ws.v2.services.BackofficeFacade;
import es.caib.zonaper.ws.v2.services.BackofficeFacadeService;
import org.fundaciobit.plugins.utils.XTrustProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Sistra1Service {

    private static final Logger log = LoggerFactory.getLogger(Sistra1Service.class);

    private static final String SISTRA1_URL = "";
    private static final String SISTRA1_USER = "";
    private static final String SISTRA1_PASS = "";
    private static final Boolean development = true;


    public List<TramitePersistente> obtenerTramites(String documento, Date fechaInicio, Date fechaFin) throws Exception{

        BackofficeFacade backofficeFacade =  getBackofficeFacade();

        GregorianCalendar inicio = new GregorianCalendar();
        inicio.setTime(fechaInicio);

        GregorianCalendar hoy = new GregorianCalendar();
        hoy.setTime(fechaFin);

        TramitesPersistentes tramites = backofficeFacade.obtenerPersistentes(documento,
                DatatypeFactory.newInstance().newXMLGregorianCalendar(inicio),DatatypeFactory.newInstance().newXMLGregorianCalendar(hoy));


        return tramites.getTramitePersistente();
    }


    public String obtenerTiquetAcceso(String idSesionTramitacion, UsuarioClave usuario) throws Exception {

        BackofficeFacade backofficeFacade = getBackofficeFacade();

        UsuarioAutenticadoInfo usuarioAutenticadoInfo = new UsuarioAutenticadoInfo();
        usuarioAutenticadoInfo.setNombre(usuario.getNombre());
        usuarioAutenticadoInfo.setApellido1(usuario.getApellido1());
        usuarioAutenticadoInfo.setApellido2(usuario.getApellido2());
        usuarioAutenticadoInfo.setMetodoAutenticacion(usuario.getMetodoAutentificacion());
        usuarioAutenticadoInfo.setNif(usuario.getNif());

        String url = backofficeFacade.obtenerTiquetAcceso(idSesionTramitacion,usuarioAutenticadoInfo);

        return url;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    private BackofficeFacade getBackofficeFacade() throws Exception{

        final URL wsdl = new URL(SISTRA1_URL + "?wsdl");

        if(SISTRA1_URL.startsWith("https") && development){
            XTrustProvider.install();
        }

        BackofficeFacadeService service = new BackofficeFacadeService(wsdl);
        BackofficeFacade backofficeFacade =  service.getBackofficeFacade();

        BindingProvider bindingProvider = (BindingProvider) backofficeFacade;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, SISTRA1_URL);
        bindingProvider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, SISTRA1_USER);
        bindingProvider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, SISTRA1_PASS);

        return backofficeFacade;
    }
}