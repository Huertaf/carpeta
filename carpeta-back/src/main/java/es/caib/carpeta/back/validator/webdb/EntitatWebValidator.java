package es.caib.carpeta.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import java.util.List;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.carpeta.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.carpeta.jpa.validator.EntitatValidator;

import es.caib.carpeta.back.form.webdb.EntitatForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.carpeta.model.entity.Entitat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EntitatWebValidator extends AbstractWebValidator<EntitatForm, Entitat>
     implements Validator, EntitatFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected EntitatValidator<Entitat> validator = new EntitatValidator<Entitat>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.EntitatLocal entitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.PluginLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.PluginLocal pluginEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.TraduccioLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.TraduccioLocal traduccioEjb;



  public EntitatWebValidator() {
    super();    
  }
  
  @Override
  public Entitat getBeanOfForm(EntitatForm form) {
    return  form.getEntitat();
  }

  @Override
  public Class<EntitatForm> getClassOfForm() {
    return EntitatForm.class;
  }

  @Override
  public void validate(EntitatForm __form, Entitat __bean, Errors errors) {

java.util.List<Field<?>> _ignoreFields = new java.util.ArrayList<Field<?>>();
_ignoreFields.add(NOMID);
    WebValidationResult<EntitatForm> wvr;
    wvr = new WebValidationResult<EntitatForm>(errors, _ignoreFields);

    boolean isNou;
    {
        Object objNou = errors.getFieldValue("nou");
        if (objNou == null) {
            isNou = false;
        } else { 
         Boolean nou = Boolean.parseBoolean((String)objNou);
         isNou =  nou != null && nou.booleanValue();
        }
    }

    validate(__form, __bean , errors, wvr, isNou);
  }


  public void validate(EntitatForm __form, Entitat __bean, Errors errors,
    WebValidationResult<EntitatForm> wvr, boolean isNou) {

  {
      es.caib.carpeta.jpa.EntitatJPA __jpa;
      __jpa = (es.caib.carpeta.jpa.EntitatJPA)__bean;
    {
      // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
      es.caib.carpeta.jpa.TraduccioJPA tradJPA = __jpa.getNom();
      if (tradJPA != null) {
        // TODO ERROR
        java.util.Map<String,es.caib.carpeta.jpa.TraduccioMapJPA> _trad = tradJPA.getTraduccions();
        int countNotNull = 0;
        for (String _idioma : _trad.keySet()) {
          es.caib.carpeta.jpa.TraduccioMapJPA _map = _trad.get(_idioma);
          if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
          } else {
            countNotNull++;
          }
        }

          if (countNotNull  == _trad.size()) {
            // OK Tot esta ple
          } else {
            for (String _idioma : _trad.keySet()) {
              es.caib.carpeta.jpa.TraduccioMapJPA _map = _trad.get(_idioma);
              if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
                errors.rejectValue("entitat.nom", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
                errors.rejectValue("entitat.nom.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
              }
            }
          }
      } else {
        errors.rejectValue("entitat.nom", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
      }
    }

  }
    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
      CommonsMultipartFile logoCapBackID = ((EntitatForm)__form).getLogoCapBackID();
      if (logoCapBackID == null || logoCapBackID.isEmpty()) {
        errors.rejectValue(get(LOGOCAPBACKID), "genapp.validation.required",
          new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(LOGOCAPBACKID)) },
          null);
      }

      CommonsMultipartFile logoPeuBackID = ((EntitatForm)__form).getLogoPeuBackID();
      if (logoPeuBackID == null || logoPeuBackID.isEmpty()) {
        errors.rejectValue(get(LOGOPEUBACKID), "genapp.validation.required",
          new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(LOGOPEUBACKID)) },
          null);
      }

      CommonsMultipartFile logoLateralFrontID = ((EntitatForm)__form).getLogoLateralFrontID();
      if (logoLateralFrontID == null || logoLateralFrontID.isEmpty()) {
        errors.rejectValue(get(LOGOLATERALFRONTID), "genapp.validation.required",
          new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(LOGOLATERALFRONTID)) },
          null);
      }

      CommonsMultipartFile iconID = ((EntitatForm)__form).getIconID();
      if (iconID == null || iconID.isEmpty()) {
        errors.rejectValue(get(ICONID), "genapp.validation.required",
          new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(ICONID)) },
          null);
      }

    }
    BeanValidatorResult<Entitat> __vr = new BeanValidatorResult<Entitat>();
    validator.validate(__vr, __bean,
      isNou, entitatEjb, pluginEjb, traduccioEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }


  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public EntitatValidator<Entitat> getValidator() {
    return validator;
  }

  public void setValidator(EntitatValidator<Entitat> validator) {
    this.validator = validator;
  }

}