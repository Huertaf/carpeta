package org.fundaciobit.pluginsib.carpetafront.notib;

import es.caib.zonaper.ws.v2.model.elementoexpediente.ElementoExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.TipoElementoExpediente;

import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import es.caib.carpeta.pluginsib.carpetafront.api.AbstractCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

/**
 * @author anadal
 */
public class NotibCarpetaFrontPlugin extends AbstractCarpetaFrontPlugin {

    public static final String NOTIB_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "notib.";

    /**
     *
     */
    public NotibCarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     */
    public NotibCarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public NotibCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getTitle(Locale locale) {
        return getTraduccio("title", locale);
    }

    @Override
    public String getSubTitle(Locale locale) {
        return getTraduccio("subtitle", locale);
    }

    @Override
    public String getResourceBundleName() {
        return "carpetafrontnotib";
    }

    @Override
    public String getStartUrl(String absolutePluginRequestPath, String relativePluginRequestPath,
            HttpServletRequest request, UserData userData, String administrationIDEncriptat) throws Exception {

        registerUserData(userData);

        String startURL = absolutePluginRequestPath + "/" + OPCIONS;

        log.info(" getStartUrl( ); => " + startURL);
        return startURL;
    }

    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        log.info("NotibCarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
        log.info("NotibCarpetaFrontPlugin::requestCarpetaFront => administrationID: " + userData.getAdministrationID());
        log.info("NotibCarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: "
                + administrationEncriptedID);

        if (query.startsWith(OPCIONS)) {

            opcions(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(COMUNICACIONS)) {

            comunicacions(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);
        } else {

            super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet);
        }

    }

    protected boolean isDevelopment() {
        return "true".equals(getProperty(NOTIB_PROPERTY_BASE + "development"));
    }

    public String getDetalleTitle(Locale locale) {
        return getTraduccio("detalletitle", locale);
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- L L I S T A T D E O P C I O N S ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String OPCIONS = "opcions";

    public void opcions(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");

            String webpage = getOpcionsPage(absolutePluginRequestPath, relativePluginRequestPath, userData, locale,
                    isGet);

            try {
                response.getWriter().println(webpage);
                response.flushBuffer();
            } catch (IOException e) {
                log.error("Error obtening writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {
            log.error("Error llistant registres: " + e.getMessage(), e);
        }

    }

    protected String getOpcionsPage(String absolutePluginRequestPath, String relativePluginRequestPath,
            UserData userData, Locale locale, boolean isGet) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        InputStream input = this.getClass().getResourceAsStream("/webpage/opcions.html");

        String plantilla = IOUtils.toString(input, "UTF-8");

        String[] traduccions = { "menu.notificaciones", "notificaciones.descripcion", "menu.notificacion",
                "notificaciones.notificaciones",

                "menu.notificaciones.otras", "notificaciones.otras" };

        for (String t : traduccions) {
            map.put(t.replace('.', '_'), getTraduccio(t, locale));
        }

        // GOVERN CENTRAL
        map.put("notificacionesUrl", getPropertyRequired(NOTIB_PROPERTY_BASE + "notificaciones.url"));

        // CAIB
        String comunicacionesURL = absolutePluginRequestPath + "/" + COMUNICACIONS;

        map.put("comunicacionesURL", comunicacionesURL);

        String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

        String fullPage = encapsulaEnPaginaHtml(absolutePluginRequestPath, locale, map, generat);

        return fullPage;

    }

    protected String encapsulaEnPaginaHtml(String absolutePluginRequestPath, Locale locale, Map<String, Object> map,
            String generat) throws IOException {
        String fullPage;
        {

            InputStream inputfp = this.getClass().getResourceAsStream("/webpage/plantilla.html");

            String fullPageTemplate = IOUtils.toString(inputfp, "UTF-8");

            map.put("title", getTitle(locale));

            map.put("resources", absolutePluginRequestPath + "/" + WEBRESOURCECOMMON);

            map.put("contingut", generat);

            map.put("lang", locale.getLanguage());

            fullPage = TemplateEngine.processExpressionLanguage(fullPageTemplate, map, locale);

        }
        return fullPage;
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- Comunicacions GOVERN ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String COMUNICACIONS = "comunicacions";

    public void comunicacions(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");

            String numeroRegistroFormateado = request.getParameter("numeroRegistroFormateado");

            String webpage = getComunicacionsPage(absolutePluginRequestPath, numeroRegistroFormateado, userData,
                    locale);

            try {
                response.getWriter().println(webpage);
                response.flushBuffer();
            } catch (IOException e) {
                log.error("Error obtening writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {
            log.error("Error llistant registres: " + e.getMessage(), e);
        }

    }

    public String getComunicacionsPage(String absolutePluginRequestPath, String numeroRegistroFormateado,
            UserData userData, Locale locale) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        // TRADUCCIONS

        String[] traduccions = { "comunicacion.listado", "comunicacion.vacio", "comunicacion.descripcion.comunicacion",
                "comunicacion.fecha", "comunicacion.descripcion" };

        for (String t : traduccions) {
            map.put(t.replace('.', '_'), getTraduccio(t, locale));
        }

        List<TipoElementoExpediente> coms = new ArrayList<TipoElementoExpediente>();

        coms.add(TipoElementoExpediente.COMUNICACION);
        coms.add(TipoElementoExpediente.NOTIFICACION);

        Sistra1ServiceImpl sistra1Service = getSistra1ServiceImpl();

        List<ElementoExpediente> comunicaciones = sistra1Service.obtenerElementosExpediente(coms,
                Sistra1ServiceImpl.ELEMENTO_TODOS, userData.getAdministrationID(), locale);
        
        /*
        if (comunicaciones.isEmpty()) {
            
            ElementoExpediente ee = new ElementoExpediente();
            ee.setDescripcion("HOLA CARA DESC");

            GregorianCalendar calendar = new GregorianCalendar();

            calendar.setTime(new Date());

            ee.setFecha(  DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
            
            ee.setPendiente(true);
            ee.setTipo(TipoElementoExpediente.COMUNICACION);
            ee.setUrl("http://google.es");
            
            comunicaciones.add(ee);
            comunicaciones.add(ee);

        }
        */
        

        map.put("comunicacions", comunicaciones);
        
        

        InputStream input = this.getClass().getResourceAsStream("/webpage/notib.html");

        String plantilla = IOUtils.toString(input, "UTF-8");

        String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

        String fullPage = encapsulaEnPaginaHtml(absolutePluginRequestPath, locale, map, generat);

        return fullPage;

    }

    /**
     * Mètode que retorna la icona del plugin
     * 
     * @param locale
     * @return
     */
    @Override
    public FileInfo getIcon(Locale locale) {

        InputStream input;

        final String logoName = "logo-notib.png";
        FileInfo fileInfo = null;
        String resource = "/" + LOGORESOURCE; // resource a on es troba l'icona
        try {

            // Agafa la icona del resource
            input = this.getClass().getResourceAsStream(resource + "/" + logoName);
            if (input != null) {
                fileInfo = new FileInfo(logoName, "image/png", IOUtils.toByteArray(input));
            }

        } catch (Exception e) {
            log.error("Error llegint recurs : " + resource + "/" + logoName + ": " + e.getMessage(), e);

        }
        return fileInfo;

    }

    private Sistra1ServiceImpl sistra1ServiceImpl = null;

    protected synchronized Sistra1ServiceImpl getSistra1ServiceImpl() throws Exception {

        if (sistra1ServiceImpl == null) {

            String url = getPropertyRequired(NOTIB_PROPERTY_BASE + "sistra1.url");
            String user = getPropertyRequired(NOTIB_PROPERTY_BASE + "sistra1.user");
            String pass = getPropertyRequired(NOTIB_PROPERTY_BASE + "sistra1.pass");
            boolean development = isDevelopment();

            Sistra1ServiceImpl tmp = new Sistra1ServiceImpl(url, user, pass, development);

            sistra1ServiceImpl = tmp;
        }

        return sistra1ServiceImpl;

    }

}