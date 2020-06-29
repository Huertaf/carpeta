package es.caib.carpeta.back.preparer;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.security.RunAs;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import es.caib.carpeta.back.security.LoginInfo;

import es.caib.carpeta.commons.utils.Constants;

/**
 * @author anadal
 *
 */
@RunAs("CAR_USER")
@Component
public class BasePreparer implements ViewPreparer, Constants {
    
    public static Map<String,I18NTranslation> loginErrorMessage = new HashMap<String,I18NTranslation>();


	protected final Logger log = Logger.getLogger(getClass());

	@Override
	public void execute(Request tilesRequest, AttributeContext attributeContext) throws PreparerException {

		Map<String, Object> request = tilesRequest.getContext("request");

		// Informació de Login
		LoginInfo loginInfo = LoginInfo.getInstance();
		// Posa dins la sessió la informació de Login
		request.put("loginInfo", loginInfo);

		// URL
		// TODO ficarho dins cache (veure Capperpare.java)
		HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		httpRequest.getSession().setAttribute("loginInfo", loginInfo);
		{



        Device currentDevice = DeviceUtils.getRequiredCurrentDevice(httpRequest);
	    if(currentDevice.isMobile()) {
	      
	      log.info("\n\nXYZ ZZZ IS MOBILE = true \n\n");
	      
	      httpRequest.getSession().setAttribute("isMobile", true);      
          request.put("isMobile", true);
	    } else {
	      log.info("\n\nXYZ ZZZ IS MOBILE = false \n\n");
	    }

	    // Error de Login
	    final String username = loginInfo.getUsuariPersona().getUsername();
	    
	    I18NTranslation trans = loginErrorMessage.get(username);
	    if (trans == null) {
	      String msg = (String)httpRequest.getSession().getAttribute("loginerror");
	      if (msg != null) {
	        HtmlUtils.saveMessageError(httpRequest, msg);
	      }
	    } else {
	      loginErrorMessage.remove(username);
	      String msg = I18NUtils.tradueix(trans);
	      HtmlUtils.saveMessageError(httpRequest, msg);
	      httpRequest.getSession().setAttribute("loginerror", msg);
	    }



			request.put("urlActual", httpRequest.getServletPath());

			// Compatibilitat IE8
			String userAgent = httpRequest.getHeader("User-Agent");
			if (userAgent != null) {
				int index = userAgent.toUpperCase().indexOf("MSIE");
				if (index != -1) {
					try {
						String ieversion = userAgent.substring(index + 4, userAgent.indexOf(";", index + 4));
						if (Float.parseFloat(ieversion) < 9.0f) {
							request.put("IE8", true);
						}
					} catch (Throwable e) {
						log.debug(e);
					}
				}
			}
		}

		// Language
		Locale loc = LocaleContextHolder.getLocale();
		request.put("lang", loc.toString()); // LANG i si es necessari el country
		request.put("onlylang", loc.getLanguage()); // només el LANG

		// Pipella
		request.put("pipella", attributeContext.getAttribute("pipella"));

		// TODO GENAPP
		// Warning for each ROLE

		// Avisos
		Map<String, Long> avisos = new HashMap<String, Long>();
		// avisos.put(rol, <<Number of warnings>>);
		request.put("avisos", avisos);

		if (attributeContext.getAttribute("menu") != null) {
			request.put("menu_tile", attributeContext.getAttribute("menu").toString());
		}

		// attributeContext.putAttribute("menu", new
		// Attribute("/WEB-INF/jsp/moduls/menu_inici.jsp"));

		request.put("contingut_tile", attributeContext.getAttribute("contingut").toString());

	}

}