package es.caib.carpeta.logic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import es.caib.carpeta.utils.Constants;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.jpa.EntitatJPA;
import es.caib.carpeta.jpa.PluginJPA;
import es.caib.carpeta.logic.utils.PluginInfo;
import es.caib.carpeta.model.entity.Entitat;
import es.caib.carpeta.model.entity.Idioma;
import es.caib.carpeta.model.entity.Plugin;
import es.caib.carpeta.model.fields.EntitatFields;
import es.caib.carpeta.model.fields.PluginEntitatFields;
import es.caib.carpeta.model.fields.PluginEntitatQueryPath;
import es.caib.carpeta.model.fields.PluginFields;

/**
 * 
 * @author anadal
 *
 */
@PermitAll
@Stateless
public class UtilitiesForFrontLogicaEJB implements UtilitiesForFrontLogicaLocal {

    @EJB(mappedName = es.caib.carpeta.ejb.EntitatLocal.JNDI_NAME)
    protected es.caib.carpeta.ejb.EntitatLocal entitatEjb;

    @EJB(mappedName = es.caib.carpeta.ejb.IdiomaLocal.JNDI_NAME)
    protected es.caib.carpeta.ejb.IdiomaLocal idiomaEjb;

    @EJB(mappedName = PluginDeCarpetaFrontLogicaLocal.JNDI_NAME)
    protected PluginDeCarpetaFrontLogicaLocal pluginCarpetaFrontEjb;

    @EJB(mappedName = es.caib.carpeta.ejb.PluginEntitatLocal.JNDI_NAME)
    protected es.caib.carpeta.ejb.PluginEntitatLocal pluginEntitatEjb;

    /**
     * Retorna codi i nom en l'idioma seleccionat
     */
    @Override
    public List<StringKeyValue> getEntitats(String language) throws I18NException {

        List<Entitat> entitats = entitatEjb.select(EntitatFields.ACTIVA.equal(true));

        List<StringKeyValue> skv = new ArrayList<StringKeyValue>(entitats.size());

        for (Entitat entitat : entitats) {

            EntitatJPA e = (EntitatJPA) entitat;

            skv.add(new StringKeyValue(entitat.getCodi(), e.getNom().getTraduccio(language).getValor()));

        }

        return skv;

    }

    /**
     * Retorna codi i nom en l'idioma seleccionat
     */
    @Override
    public List<Idioma> getIdiomes() throws I18NException {

        List<Idioma> idiomes = idiomaEjb.select();

        return idiomes;

    }

    @Override
    public List<PluginInfo> getFrontPlugins(String codiEntitat, String language) throws I18NException {

        // XYZ ZZZ TODO Millorar amb una subquery
        List<Long> pluginsEntitat = pluginEntitatEjb.executeQuery(PluginEntitatFields.PLUGINID,
                new PluginEntitatQueryPath().ENTITAT().CODI().equal(codiEntitat));

        List<Plugin> plugins = pluginCarpetaFrontEjb.getAllPlugins(PluginFields.PLUGINID.in(pluginsEntitat));

        List<PluginInfo> pluginsInfo = new ArrayList<PluginInfo>(plugins.size());

        for (Plugin plugin : plugins) {
            PluginJPA p = (PluginJPA) plugin;
            pluginsInfo.add(new PluginInfo(String.valueOf(plugin.getPluginID()), p.getNom().getTraduccio(Constants.IDIOMA_CATALA).getValor(),
                    p.getNom().getTraduccio(Constants.IDIOMA_CASTELLA).getValor(), p.getNom().getTraduccio(Constants.IDIOMA_ANGLES).getValor(),
                    p.getDescripcio().getTraduccio(Constants.IDIOMA_CATALA).getValor(), p.getDescripcio().getTraduccio(Constants.IDIOMA_CASTELLA).getValor(),
                    p.getDescripcio().getTraduccio(Constants.IDIOMA_ANGLES).getValor()));
        }

        return pluginsInfo;
    }

}