package es.caib.carpeta.logic;

import javax.ejb.Local;

import es.caib.carpeta.pluginsib.carpetafront.api.ICarpetaFrontPlugin;


/**
 * 
 * @author anadal
 *
 */
@Local
public interface PluginDeCarpetaFrontLogicaLocal extends AbstractPluginLogicaLocal<ICarpetaFrontPlugin> {

    public static final String JNDI_NAME = "java:app/carpeta-logic/PluginDeCarpetaFrontLogicaEJB!es.caib.carpeta.logic.PluginDeCarpetaFrontLogicaLocal";

   
}