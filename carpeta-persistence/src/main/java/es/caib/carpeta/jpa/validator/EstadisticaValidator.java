package es.caib.carpeta.jpa.validator;

import org.apache.log4j.Logger;

import es.caib.carpeta.model.entity.Estadistica;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.carpeta.model.fields.EstadisticaFields;
import es.caib.carpeta.model.fields.AccesFields;
import es.caib.carpeta.model.fields.EntitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class EstadisticaValidator<I extends Estadistica>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements EstadisticaFields {

    protected final Logger log = Logger.getLogger(getClass());


  public EstadisticaValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.carpeta.model.dao.IAccesManager __accesManager
    ,es.caib.carpeta.model.dao.IEntitatManager __entitatManager
    ,es.caib.carpeta.model.dao.IEstadisticaManager __estadisticaManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,ENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ACCIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ACCIO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATAESTADISTICA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATAESTADISTICA)));

    // Check size
    if (__vr.getFieldErrorCount(ELEMENT) == 0) {
      java.lang.String __element = __target__.getElement();
      if (__element!= null && __element.length() > 255) {
        __vr.rejectValue(ELEMENT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ELEMENT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(ENTITATID) == 0) {
      java.lang.Long __entitatid = __target__.getEntitatID();
      Long __count_ = null;
      try { __count_ = __entitatManager.count(EntitatFields.ENTITATID.equal(__entitatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(ENTITATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitat.entitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitat.entitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entitatid)));
      }
    }

    if (__vr.getFieldErrorCount(ACCESID) == 0) {
      java.lang.Long __accesid = __target__.getAccesID();
      if (__accesid != null ) {
        Long __count_ = null;
        try { __count_ = __accesManager.count(AccesFields.ACCESID.equal(__accesid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(ACCESID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("acces.acces"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("acces.accesID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__accesid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}