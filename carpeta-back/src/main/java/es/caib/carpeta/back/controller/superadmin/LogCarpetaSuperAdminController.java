package es.caib.carpeta.back.controller.superadmin;

import es.caib.carpeta.back.controller.webdb.LogCarpetaController;
import es.caib.carpeta.back.form.webdb.LogCarpetaFilterForm;
import es.caib.carpeta.back.form.webdb.LogCarpetaForm;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.jpa.LogCarpetaJPA;
import es.caib.carpeta.model.fields.LogCarpetaFields;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 19/10/2020
 */

@Controller
@RequestMapping(value = "/superadmin/logCarpeta")
@SessionAttributes(types = { LogCarpetaForm.class, LogCarpetaFilterForm.class })
public class LogCarpetaSuperAdminController extends LogCarpetaController {
   @Override
   public String getTileForm() {
      return "logCarpetaFormSuperAdmin";
   }


   @Override
   public String getTileList() {
      return "logCarpetaListSuperAdmin";
   }


   @Override
   public String getSessionAttributeFilterForm() {
      return "LogCarpetaSuperAdmin_FilterForm";
   }

   @Override
   public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
      return null;
   }



   @Override
   public LogCarpetaFilterForm getLogCarpetaFilterForm(Integer pagina, ModelAndView mav,
                                                       HttpServletRequest request) throws I18NException {
      LogCarpetaFilterForm logCarpetaFilterForm = super.getLogCarpetaFilterForm(pagina, mav, request);

      if(logCarpetaFilterForm.isNou()) {
         logCarpetaFilterForm.addHiddenField(LOGID);
         logCarpetaFilterForm.addHiddenField(EXCEPCIO);
         logCarpetaFilterForm.addHiddenField(ERROR);
         logCarpetaFilterForm.addHiddenField(PETICIO);
         logCarpetaFilterForm.setAddButtonVisible(false);
         logCarpetaFilterForm.setDeleteSelectedButtonVisible(false);
         logCarpetaFilterForm.setDeleteButtonVisible(false);
         logCarpetaFilterForm.setEditButtonVisible(false);
         logCarpetaFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-eye",
            "genapp.viewtitle", getContextWeb() + "/view/{0}", "btn-info"));
         logCarpetaFilterForm.addGroupByField(TIPUS);
         logCarpetaFilterForm.addGroupByField(ESTAT);
         logCarpetaFilterForm.setOrderBy(LogCarpetaFields.DATAINICI.javaName);
         logCarpetaFilterForm.setOrderAsc(false);

      }

      return logCarpetaFilterForm;
   }

   @Override
   public LogCarpetaForm getLogCarpetaForm(LogCarpetaJPA _jpa,
                                           boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
      LogCarpetaForm logCarpetaForm = super.getLogCarpetaForm(_jpa, __isView, request, mav);

      if(logCarpetaForm.isNou()){
         logCarpetaForm.getLogCarpeta().setEntitatID(LoginInfo.getInstance().getEntitatID());
      }

      logCarpetaForm.setAllFieldsReadOnly(LogCarpetaFields.ALL_LOGCARPETA_FIELDS);
      logCarpetaForm.setSaveButtonVisible(false);
      logCarpetaForm.setDeleteButtonVisible(false);


      return logCarpetaForm;
   }


   @Override
   public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
                                                        ModelAndView mav, Where where)  throws I18NException {
      List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();


      for (int i = 0; i < Constants.TIPUS_LOG_ALL.length; i++) {
         int v = Constants.TIPUS_LOG_ALL[i];
         __tmp.add(new StringKeyValue("" + v, I18NUtils.tradueix("logcarpeta.tipus." + v)));
      }

      return __tmp;

   }



   @Override
   public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
                                                        ModelAndView mav, Where where)  throws I18NException {
      List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
      for (int i = 0; i < Constants.ESTAT_LOG_ALL.length; i++) {
         int v = Constants.ESTAT_LOG_ALL[i];
         __tmp.add(new StringKeyValue("" + v, I18NUtils.tradueix("logcarpeta.estat." + v)));
      }
      return __tmp;
   }
}