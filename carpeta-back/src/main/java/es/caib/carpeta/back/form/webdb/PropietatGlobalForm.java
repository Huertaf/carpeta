package es.caib.carpeta.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.carpeta.back.form.CarpetaBaseForm;
import es.caib.carpeta.jpa.PropietatGlobalJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PropietatGlobalForm extends CarpetaBaseForm {
  
  private PropietatGlobalJPA propietatGlobal;
  
  public PropietatGlobalForm() {
  }
  
  public PropietatGlobalForm(PropietatGlobalForm __toClone) {
    super(__toClone);
      this.propietatGlobal = __toClone.propietatGlobal;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
  }
  
  public PropietatGlobalForm(PropietatGlobalJPA propietatGlobal, boolean nou) {
    super(nou);
    this.propietatGlobal = propietatGlobal;
  }
  
  public PropietatGlobalJPA getPropietatGlobal() {
    return propietatGlobal;
  }
  public void setPropietatGlobal(PropietatGlobalJPA propietatGlobal) {
    this.propietatGlobal = propietatGlobal;
  }
  
  
  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  
} // Final de Classe 