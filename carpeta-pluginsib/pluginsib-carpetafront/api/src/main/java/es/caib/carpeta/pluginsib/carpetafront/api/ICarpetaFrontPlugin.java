package es.caib.carpeta.pluginsib.carpetafront.api;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.pluginsib.core.IPlugin;

/**
 * 
 * 
 * @author anadal
 */
public interface ICarpetaFrontPlugin extends IPlugin {

    public static final String CARPETAFRONT_PROPERTY_BASE = IPLUGINSIB_BASE_PROPERTIES + "carpetafront.";

    public String getTitle(Locale locale);

    public String getSubTitle(Locale locale);

    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception;

    public String getStartUrl(String absolutePluginRequestPath, String relativePluginRequestPath,
            HttpServletRequest request, String administrationID, String administrationEncriptedID) throws Exception;

    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, String administrationID, String administrationEncriptedID, Locale locale, boolean isGet)
            throws Exception;


}