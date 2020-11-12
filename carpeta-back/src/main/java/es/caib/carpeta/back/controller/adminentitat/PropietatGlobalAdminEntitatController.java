package es.caib.carpeta.back.controller.adminentitat;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

import es.caib.carpeta.back.form.webdb.*;

import es.caib.carpeta.back.controller.superadmin.PropietatGlobalSuperAdminController;
import es.caib.carpeta.back.security.LoginInfo;
import static es.caib.carpeta.commons.utils.Constants.TIPUS_AUDIT_AFEGIR_PROPGLOB;
import es.caib.carpeta.jpa.PropietatGlobalJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/adminentitat/propietatglobal")
@SessionAttributes(types = { PropietatGlobalForm.class, PropietatGlobalFilterForm.class })
public class PropietatGlobalAdminEntitatController extends PropietatGlobalSuperAdminController {

    @Override
    protected boolean isSuperAdmin() {
        return false;
    }
    
    @Override
    public String getEntityNameCode() {
        return "propietatGlobal.adminentitat.titol";
      }

    @Override
    public String getEntityNameCodePlural() {
        return "propietatGlobal.adminentitat.titol.plural";
    }



    @Override
    public PropietatGlobalJPA create(HttpServletRequest request, PropietatGlobalJPA propietatGlobal) throws Exception, I18NException, I18NValidationException {
        PropietatGlobalJPA propietatGlobalJPA = super.create(request, propietatGlobal);

        try{
            auditoriaLogicaEjb.crearAuditoria(TIPUS_AUDIT_AFEGIR_PROPGLOB,LoginInfo.getInstance().getEntitatID(), LoginInfo.getInstance().getUsuariPersona().getUsuariID(),"",null);
        }catch(I18NException e){

            String msg = "Error creant auditoria "+ "("+  e.getMessage() + ")";
            log.error(msg, e);
        }

        return propietatGlobalJPA;

    }

}
