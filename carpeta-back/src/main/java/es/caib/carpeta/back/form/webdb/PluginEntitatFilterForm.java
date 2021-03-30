
package es.caib.carpeta.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.carpeta.back.form.CarpetaBaseFilterForm;

import es.caib.carpeta.model.fields.PluginEntitatFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PluginEntitatFilterForm extends CarpetaBaseFilterForm implements PluginEntitatFields {

  private java.lang.Long pluginEntitatIDDesde;

  public java.lang.Long getPluginEntitatIDDesde() {
    return this.pluginEntitatIDDesde;
  }

  public void setPluginEntitatIDDesde(java.lang.Long pluginEntitatIDDesde) {
    this.pluginEntitatIDDesde = pluginEntitatIDDesde;
  }


  private java.lang.Long pluginEntitatIDFins;

  public java.lang.Long getPluginEntitatIDFins() {
    return this.pluginEntitatIDFins;
  }

  public void setPluginEntitatIDFins(java.lang.Long pluginEntitatIDFins) {
    this.pluginEntitatIDFins = pluginEntitatIDFins;
  }


  private java.lang.Long pluginIDDesde;

  public java.lang.Long getPluginIDDesde() {
    return this.pluginIDDesde;
  }

  public void setPluginIDDesde(java.lang.Long pluginIDDesde) {
    this.pluginIDDesde = pluginIDDesde;
  }


  private java.lang.Long pluginIDFins;

  public java.lang.Long getPluginIDFins() {
    return this.pluginIDFins;
  }

  public void setPluginIDFins(java.lang.Long pluginIDFins) {
    this.pluginIDFins = pluginIDFins;
  }


  private java.lang.Long entitatIDDesde;

  public java.lang.Long getEntitatIDDesde() {
    return this.entitatIDDesde;
  }

  public void setEntitatIDDesde(java.lang.Long entitatIDDesde) {
    this.entitatIDDesde = entitatIDDesde;
  }


  private java.lang.Long entitatIDFins;

  public java.lang.Long getEntitatIDFins() {
    return this.entitatIDFins;
  }

  public void setEntitatIDFins(java.lang.Long entitatIDFins) {
    this.entitatIDFins = entitatIDFins;
  }


  private java.lang.Long seccioIDDesde;

  public java.lang.Long getSeccioIDDesde() {
    return this.seccioIDDesde;
  }

  public void setSeccioIDDesde(java.lang.Long seccioIDDesde) {
    this.seccioIDDesde = seccioIDDesde;
  }


  private java.lang.Long seccioIDFins;

  public java.lang.Long getSeccioIDFins() {
    return this.seccioIDFins;
  }

  public void setSeccioIDFins(java.lang.Long seccioIDFins) {
    this.seccioIDFins = seccioIDFins;
  }


  private java.lang.Integer ordreDesde;

  public java.lang.Integer getOrdreDesde() {
    return this.ordreDesde;
  }

  public void setOrdreDesde(java.lang.Integer ordreDesde) {
    this.ordreDesde = ordreDesde;
  }


  private java.lang.Integer ordreFins;

  public java.lang.Integer getOrdreFins() {
    return this.ordreFins;
  }

  public void setOrdreFins(java.lang.Integer ordreFins) {
    this.ordreFins = ordreFins;
  }


  public PluginEntitatFilterForm() {
  }
  
  public PluginEntitatFilterForm(PluginEntitatFilterForm __toClone) {
    super(__toClone);
    this.pluginEntitatIDDesde = __toClone.pluginEntitatIDDesde;
    this.pluginEntitatIDFins = __toClone.pluginEntitatIDFins;
    this.pluginIDDesde = __toClone.pluginIDDesde;
    this.pluginIDFins = __toClone.pluginIDFins;
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
    this.seccioIDDesde = __toClone.seccioIDDesde;
    this.seccioIDFins = __toClone.seccioIDFins;
    this.ordreDesde = __toClone.ordreDesde;
    this.ordreFins = __toClone.ordreFins;
    this.mapOfPluginForPluginID = __toClone.mapOfPluginForPluginID;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
    this.mapOfSeccioForSeccioID = __toClone.mapOfSeccioForSeccioID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }


  protected OrderBy[] defaultOrderBy = null;


  public OrderBy[] getDefaultOrderBy() {
    return this.defaultOrderBy;
  }

  public void setDefaultOrderBy(OrderBy[] defOrderBy) {
    this.defaultOrderBy = defOrderBy;
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

   // -----------------------
   // Maps de referencies.
   // -----------------------
  private Map<String, String> mapOfPluginForPluginID;

  public Map<String, String> getMapOfPluginForPluginID() {
    return this.mapOfPluginForPluginID;
  }

  public void setMapOfPluginForPluginID(Map<String, String> mapOfPluginForPluginID) {
    this.mapOfPluginForPluginID = mapOfPluginForPluginID;
  }



  private Map<String, String> mapOfEntitatForEntitatID;

  public Map<String, String> getMapOfEntitatForEntitatID() {
    return this.mapOfEntitatForEntitatID;
  }

  public void setMapOfEntitatForEntitatID(Map<String, String> mapOfEntitatForEntitatID) {
    this.mapOfEntitatForEntitatID = mapOfEntitatForEntitatID;
  }



  private Map<String, String> mapOfSeccioForSeccioID;

  public Map<String, String> getMapOfSeccioForSeccioID() {
    return this.mapOfSeccioForSeccioID;
  }

  public void setMapOfSeccioForSeccioID(Map<String, String> mapOfSeccioForSeccioID) {
    this.mapOfSeccioForSeccioID = mapOfSeccioForSeccioID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
