package org.fundaciobit.pluginsib.carpetafront.regweb32;

import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.BindingProvider;

import es.caib.carpeta.pluginsib.carpetafront.api.AbstractCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;
import es.caib.regweb3.ws.api.v3.AsientoWs;
import es.caib.regweb3.ws.api.v3.RegWebAsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.RegWebAsientoRegistralWsService;
import es.caib.regweb3.ws.api.v3.ResultadoBusquedaWs;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;


/**
 * @author anadal
 * @author mgonzalez
 */
public class Regweb32CarpetaFrontPlugin extends AbstractCarpetaFrontPlugin {

    public static final String REGWEB3_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "regweb32.";

    
    
    public static final String MIME_JPG  = "image/jpeg";
    public static final String MIME_XML1  = "text/xml";
    public static final String MIME_XML2  = "application/xml";
    public static final String MIME_PDF  = "application/pdf";
    public static final String MIME_PNG  = "image/png";
    public static final String MIME_RTF  = "text/rtf";
    public static final String MIME_RTF2  = "application/rtf";
    public static final String MIME_SVG  = "image/svg+xml";
    public static final String MIME_TIFF = "image/tiff";
    public static final String MIME_TXT  = "text/plain";
    public static final String MIME_ODT  = "application/vnd.oasis.opendocument.text";
    public static final String MIME_XLSX  = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String MIME_PPTX  = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
    public static final String MIME_ODP  = "application/vnd.oasis.opendocument.presentation";
    public static final String MIME_ODS  = "application/vnd.oasis.opendocument.spreadsheet";
    public static final String MIME_ODG  = "application/vnd.oasis.opendocument.graphics";
    public static final String MIME_DOCX  = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    public static final String MIME_HTML  = "text/html";
    public static final String MIME_DEFAULT  = "application/octet-stream";
    
    public static final Map<String, String> TEXTO_REDUCIDO_BY_TIPO_MIME = new HashMap<String, String>() {{
        put(MIME_JPG, "JPG");
        put(MIME_XML1, "XML");
        put(MIME_XML2, "XML");
        put(MIME_PDF, "PDF");
        put(MIME_PNG, "PNG");
        put(MIME_RTF, "RTF");
        put(MIME_RTF2, "RTF2");
        put(MIME_SVG, "SVG");
        put(MIME_TIFF, "TIFF");
        put(MIME_TXT, "TXT");
        put(MIME_ODT, "ODT");
        put(MIME_XLSX, "XLSX(EXCEL)");
        put(MIME_PPTX, "PPTX");
        put(MIME_ODP, "ODP");
        put(MIME_ODS, "ODS");
        put(MIME_ODG, "ODG");
        put(MIME_DOCX, "DOCX");
        put(MIME_HTML, "HTML");
        put(MIME_DEFAULT, "OCTET-STREAM");
    }};



    /**
     *
     */
    public Regweb32CarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public Regweb32CarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public Regweb32CarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
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
        return "carpetafrontregweb32";
    }


    @Override
    public String getStartUrl(String absolutePluginRequestPath, String relativePluginRequestPath,
                              HttpServletRequest request, UserData userData, String administrationIDEncriptat) throws Exception {

        registerUserData(userData);
        
        String startURL = absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE;

        log.info(" getStartUrl( ); => " + startURL);
        return startURL;
    }

    public String getEntidad() throws Exception {

        return getPropertyRequired(REGWEB3_PROPERTY_BASE + "entidad");

    }

    //Propietat que indica la url de descarrega del justificant
    public String getConcsvUrl() {

        return getProperty(  REGWEB3_PROPERTY_BASE + "concsv.url");
    }


    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
                                    HttpServletRequest request, HttpServletResponse response, UserData userData,
                                    String administrationEncriptedID, Locale locale, boolean isGet) {


        log.info("Regweb32CarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
        log.info("Regweb32CarpetaFrontPlugin::requestCarpetaFront => administrationID: " + userData.getAdministrationID());
        log.info("Regweb32CarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: " + administrationEncriptedID);

        Regweb32DetallRegistre regweb32DetallRegistre = new Regweb32DetallRegistre();

        try {

        if (query.startsWith(LLISTAT_REGISTRES_PAGE)) {

            String pageNumber = request.getParameter("pageNumber");
            if(pageNumber == null){
                pageNumber = "1";
            }

            log.info("PAGE NUMBER " + pageNumber);

            llistatDeRegistres(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData, administrationEncriptedID, Integer.parseInt(pageNumber)-1, locale, isGet);

        } else if (query.startsWith(Regweb32DetallRegistre.DETALL_REGISTRE_PAGE)) {

            regweb32DetallRegistre.detallDeRegistre(absolutePluginRequestPath, relativePluginRequestPath, query,
                        request, response, userData, administrationEncriptedID, getEntidad(), getConcsvUrl(),getRegWebAsientoRegistralWsService(), locale, isGet);

        } else if (query.startsWith(JUSTIFICANT_REGISTRE_PAGE)) {

            regweb32DetallRegistre.justificantDeRegistre(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData, administrationEncriptedID,getEntidad(), getConcsvUrl(), getRegWebAsientoRegistralWsService(), locale, isGet);
        } else if (query.startsWith(ANNEXE_REGISTRE_PAGE)) {

            regweb32DetallRegistre.annexeDeRegistre(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData, administrationEncriptedID,getEntidad(), getConcsvUrl(), getRegWebAsientoRegistralWsService(),locale, isGet);
        } else{

            super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData, administrationEncriptedID, locale, isGet);
        }
        }catch (Exception e){
            log.error("Error detall registre: " + e.getMessage(), e);
        }


    }


    public boolean isDevelopment() {
        return "true".equals(getProperty(REGWEB3_PROPERTY_BASE + "development"));
    }


    public String getDetalleTitle(Locale locale) {
        return getTraduccio("detalletitle", locale);
    }



    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // -------------------     L L I S T A T   DE   REGISTRES                ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------


    protected static final String LLISTAT_REGISTRES_PAGE = "llistatRegistres32";
    

    List<AsientoWs> listRegistros = new ArrayList<>();

    int totalResults = 0;

    public void llistatDeRegistres(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
                                   HttpServletRequest request, HttpServletResponse response, UserData userData,
                                   String administrationEncriptedID, int pageNumber, Locale locale, boolean isGet) {

        try {


            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");


            String webpage = getLlistatDeRegistresPage(absolutePluginRequestPath, relativePluginRequestPath, userData, getEntidad(),pageNumber, locale, isGet);

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


    public String getLlistatDeRegistresPage(String absolutePluginRequestPath, String relativePluginRequestPath,UserData userData, String entidad, int pageNumber, Locale locale, boolean isGet) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        List<AsientoWs> registres;



        if (isDevelopment()) {
            registres = getRegistresDebug(userData.getAdministrationID(), entidad, pageNumber, locale);
        } else {
            registres = getRegistres(userData.getAdministrationID(), entidad, pageNumber, locale);
        }


        InputStream input = this.getClass().getResourceAsStream("/webpage/regweb32.html");

        String plantilla = IOUtils.toString(input, "UTF-8");


        // XYZ ZZZ
        map.put("resources", absolutePluginRequestPath + "/" + WEBRESOURCECOMMON);

        // XYZ ZZZ
        map.put("form_action", absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE);
        map.put("lang", locale.getLanguage());

        map.put("title", getTitle(locale));




        String[] traduccions = {"registro.listado", "registro.descripcion", "registro.numero",
           "registro.fecha", "registro.extracto", "registro.destinatario", "registro.vacio", "carpeta.acciones", "registro.detalle"};

        for (String t : traduccions) {
            map.put(t.replace('.', '_'), getTraduccio(t, locale));
        }


        String urlDetalle = absolutePluginRequestPath  + "/" + DETALL_REGISTRE_PAGE +"?numeroRegistroFormateado=";



        map.put("urlDetalle" , urlDetalle);

        map.put("registros", registres);

        //Calculam la paginació
        Paginacio paginacio = new Paginacio(this.totalResults, pageNumber);
        String firstUrl =  absolutePluginRequestPath  + "/" + LLISTAT_REGISTRES_PAGE +"?pageNumber=1";
        String lastUrl =  absolutePluginRequestPath  + "/" + LLISTAT_REGISTRES_PAGE +"?pageNumber="+paginacio.getTotalPages();
        String prevUrl =  absolutePluginRequestPath  + "/" + LLISTAT_REGISTRES_PAGE +"?pageNumber="+(paginacio.getCurrentIndex()-1);
        String nextUrl =  absolutePluginRequestPath  + "/" + LLISTAT_REGISTRES_PAGE +"?pageNumber="+(paginacio.getCurrentIndex()+1);
        String pageUrl = absolutePluginRequestPath  + "/" + LLISTAT_REGISTRES_PAGE +"?pageNumber=";


        map.put("firstUrl",firstUrl);
        map.put("lastUrl",lastUrl);
        map.put("prevUrl",prevUrl);
        map.put("nextUrl",nextUrl);
        map.put("pageUrl",pageUrl);

        map.put("paginacio",paginacio);


        String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

        return generat;

    }


    private List<AsientoWs> getRegistresDebug(String administrationID, String entidad, int pageNumber, Locale locale) throws Exception {


        List<AsientoWs> registros = this.getRegistres(administrationID, entidad,pageNumber, locale);


        if (registros == null || registros.isEmpty()) {
            log.info(" REGISTRES NULL o EMPTY: " + registros);
            
        } else {
            int x = 1;
            for (AsientoWs ar : registros) {

                log.info(" -------------  REGISTRE [" + x + " ] -------------------");
                log.info("ar.getNumeroRegistroFormateado() => " + (ar.getNumeroRegistro()));
                log.info("ar.getResumen() => " + ar.getExtracto());
                log.info("ar.getFechaRegistro(); => " + ar.getFechaRegistro());
                log.info("ar.getUnidadTramitacionDestinoDenominacion() => " + ar.getDenominacionDestino());

                x++;

            }

        }

        return registros;
    }


    public List<AsientoWs> getRegistres(String administrationID, String entidad, int pageNumber, Locale locale)
       throws Exception {

        RegWebAsientoRegistralWs service = getRegWebAsientoRegistralWsService();


        ResultadoBusquedaWs result = service.obtenerAsientosCiudadanoCarpeta(entidad, administrationID, pageNumber,locale.getLanguage());


        @SuppressWarnings("unchecked")
        List<AsientoWs> registros = (List<AsientoWs>) (List<?>) result.getResults();
        this.listRegistros = registros;
        this.totalResults = result.getTotalResults();
        return registros;
    }


    protected static final String DETALL_REGISTRE_PAGE = "detallRegistre32";
    protected static final String JUSTIFICANT_REGISTRE_PAGE = "justificantRegistre";
    protected static final String ANNEXE_REGISTRE_PAGE = "annexeRegistre";




    /**
     * @return
     * @throws Exception
     */
    public RegWebAsientoRegistralWs getRegWebAsientoRegistralWsService() throws Exception {

        final String regweb3Url = getPropertyRequired(REGWEB3_PROPERTY_BASE + "url");

        final String username = getPropertyRequired(REGWEB3_PROPERTY_BASE + "user");

        final String password = getPropertyRequired(REGWEB3_PROPERTY_BASE + "pass");

        final URL wsdl = new URL(regweb3Url + "?wsdl");


        RegWebAsientoRegistralWsService service = new RegWebAsientoRegistralWsService(wsdl);

        RegWebAsientoRegistralWs api = service.getRegWebAsientoRegistralWs();


        Map<String, Object> reqContext = ((BindingProvider) api).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, regweb3Url);
        reqContext.put(BindingProvider.USERNAME_PROPERTY, username);
        reqContext.put(BindingProvider.PASSWORD_PROPERTY, password);

        return api;
    }




    /**
     * Mètode que retorna la icona del plugin
     * @param locale
     * @return
     */
    @Override
    public  FileInfo getResourceIcon(Locale locale)  {

        return getImageFromResource(locale, "/logo/logo-regweb32.png", "image/png");

    }
    
    @Override
    public String getPropertyBase() {
        return REGWEB3_PROPERTY_BASE;
    }


}