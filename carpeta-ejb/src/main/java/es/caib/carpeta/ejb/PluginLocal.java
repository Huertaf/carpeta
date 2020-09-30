
package es.caib.carpeta.ejb;

import javax.ejb.Local;

import es.caib.carpeta.jpa.PluginJPA;
import es.caib.carpeta.jpa.PluginIJPAManager;
import es.caib.carpeta.model.dao.IPluginManager;

@Local
public interface PluginLocal extends PluginIJPAManager,IPluginManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/PluginEJB!es.caib.carpeta.ejb.PluginLocal";

    public PluginJPA findByPrimaryKey(Long _ID_);
}