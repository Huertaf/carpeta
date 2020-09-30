package es.caib.carpeta.back.controller.common;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.utils.Configuracio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 
 * @autor anadal
 * 
 */
@Controller
public class PrincipalController {

	protected final Logger log = Logger.getLogger(getClass());

	@RequestMapping(value = "/common/principal.html")
	public ModelAndView principal(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Boolean initialized = (Boolean) session.getAttribute("inicialitzat");

		if (initialized == null) {
			HtmlUtils.saveMessageInfo(request, "MessageInfo : Benvingut a Carpeta");
			session.setAttribute("inicialitzat", true);
		}

		return new ModelAndView("principal");

	}

	@RequestMapping(value = "/canviarIdioma/{idioma}", method = RequestMethod.GET)
	public ModelAndView canviarIdioma(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(name = "idioma") String idioma) throws Exception {
		es.caib.carpeta.back.utils.CarpetaSessionLocaleResolver.setLocaleManually(request, idioma);
		return new ModelAndView("principal");
	}

	@RequestMapping(value = "/canviarPipella", method = RequestMethod.GET)
	public ModelAndView canviarPipella(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return canviarPipella(request, response, null);
	}

	@RequestMapping(value = "/canviarPipella/{pipella}", method = RequestMethod.GET)
	public ModelAndView canviarPipella(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String pipella) throws Exception {

		if (pipella != null && pipella.trim().length() != 0) {

			// TODO GENAPP Afegir altres pipelles !!!!!
			/*
			 * if ("ROLE_ADEN".equals(pipella)) { //return new ModelAndView("role_aden");
			 * return new ModelAndView(new RedirectView("/aden/peticionscaducades/list/1",
			 * true)); }
			 */

			if ("superadmin".equals(pipella)) {
				return new ModelAndView(new RedirectView("/superadmin/buit", true));
			}

			if ("adminentitat".equals(pipella)) {
				return new ModelAndView(new RedirectView("/adminentitat/buit", true));
			}

			if ("webdb".equals(pipella)) {
				return new ModelAndView("webdb");
			}

			if (Configuracio.isDesenvolupament() && "desenvolupament".equals(pipella)) {
				return new ModelAndView("desenvolupament");
			}

			log.error("S'ha accedit a canviarPipella amb un paràmetre desconegut: " + pipella);
		}

		return new ModelAndView("principal");
	}
	
	@RequestMapping(value = "/canviarEntitat/{idEntitat}", method = RequestMethod.GET)
	public ModelAndView canviarEntitat(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@PathVariable String idEntitat) throws Exception {

		LoginInfo loginInfo = LoginInfo.getInstance();
		loginInfo.setEntitatID(Long.parseLong(idEntitat,10));
		
		HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();	
		httpRequest.getSession().setAttribute("loginInfo", loginInfo);
		
		return new ModelAndView("principal");
	
	}

	@RequestMapping(value = "/avislegal", method = RequestMethod.GET)
	public ModelAndView avislegal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("avislegal");
	}

	@RequestMapping(value = "/accessibilitat", method = RequestMethod.GET)
	public ModelAndView accessibilitat(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("accessibilitat");
	}

	@RequestMapping(value = "/mapaweb", method = RequestMethod.GET)
	public ModelAndView mapaweb(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("mapaweb");
	}

	@RequestMapping(value = "/protecciodades", method = RequestMethod.GET)
	public ModelAndView protecciodades(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("protecciodades");
	}
}