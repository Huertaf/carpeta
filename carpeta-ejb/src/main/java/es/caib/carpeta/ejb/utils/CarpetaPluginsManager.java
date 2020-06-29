package es.caib.carpeta.ejb.utils;

import org.apache.log4j.Logger;

import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;

import es.caib.carpeta.commons.utils.Constants;

import org.fundaciobit.pluginsib.core.utils.PluginsManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal
 * 
 */
public class CarpetaPluginsManager implements Constants {

	protected static Logger log = Logger.getLogger(CarpetaPluginsManager.class);

	public static final String USERINFORMATION_PLUGIN_KEY = CARPETA_PROPERTY_BASE + "userinformationplugin";

	public static IUserInformationPlugin userInformationPlugin = null;

	public static IUserInformationPlugin getUserInformationPluginInstance() throws I18NException {
		if (userInformationPlugin == null) {
			final String propertyPlugin = USERINFORMATION_PLUGIN_KEY;
			Object pluginInstance = PluginsManager.instancePluginByProperty(propertyPlugin, CARPETA_PROPERTY_BASE, System.getProperties());
			if (pluginInstance == null) {
				throw new I18NException("plugin.donotinstantiateplugin", new I18NArgumentCode("plugin.userinfo"));
			}
			userInformationPlugin = (IUserInformationPlugin) pluginInstance;
		}
		return userInformationPlugin;
	}

}