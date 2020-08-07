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
import es.caib.carpeta.jpa.validator.EstadisticaValidator;

import es.caib.carpeta.back.form.webdb.EstadisticaForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.carpeta.model.entity.Estadistica;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EstadisticaWebValidator extends AbstractWebValidator<EstadisticaForm, Estadistica>
     implements Validator, EstadisticaFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected EstadisticaValidator<Estadistica> validator = new EstadisticaValidator<Estadistica>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.AccesLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.AccesLocal accesEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.EntitatLocal entitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.EstadisticaLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.EstadisticaLocal estadisticaEjb;



  public EstadisticaWebValidator() {
    super();    
  }
  
  @Override
  public Estadistica getBeanOfForm(EstadisticaForm form) {
    return  form.getEstadistica();
  }

  @Override
  public Class<EstadisticaForm> getClassOfForm() {
    return EstadisticaForm.class;
  }

  @Override
  public void validate(EstadisticaForm __form, Estadistica __bean, Errors errors) {

    WebValidationResult<EstadisticaForm> wvr;
    wvr = new WebValidationResult<EstadisticaForm>(errors);

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


  public void validate(EstadisticaForm __form, Estadistica __bean, Errors errors,
    WebValidationResult<EstadisticaForm> wvr, boolean isNou) {

    BeanValidatorResult<Estadistica> __vr = new BeanValidatorResult<Estadistica>();
    validator.validate(__vr, __bean,
      isNou, accesEjb, entitatEjb, estadisticaEjb);

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

  public EstadisticaValidator<Estadistica> getValidator() {
    return validator;
  }

  public void setValidator(EstadisticaValidator<Estadistica> validator) {
    this.validator = validator;
  }

}