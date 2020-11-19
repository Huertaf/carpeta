package es.caib.carpeta.back.security;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.*;

import es.caib.carpeta.back.preparer.BasePreparer;
import es.caib.carpeta.commons.utils.Constants;
import static es.caib.carpeta.commons.utils.Constants.ESTAT_LOG_ERROR;
import static es.caib.carpeta.commons.utils.Constants.ESTAT_LOG_OK;
import static es.caib.carpeta.commons.utils.Constants.TIPUS_AUDIT_ENTRADA_BACK;
import static es.caib.carpeta.commons.utils.Constants.TIPUS_ESTAD_ENTRADA_BACK;
import static es.caib.carpeta.commons.utils.Constants.TIPUS_LOG_AUTENTICACIO_BACK;
import es.caib.carpeta.ejb.EntitatLocal;
import es.caib.carpeta.ejb.PropietatGlobalLocal;
import es.caib.carpeta.jpa.EntitatJPA;
import es.caib.carpeta.jpa.EstadisticaJPA;
import es.caib.carpeta.jpa.UsuariEntitatJPA;
import es.caib.carpeta.jpa.UsuariJPA;
import es.caib.carpeta.logic.AuditoriaLogicaLocal;
import es.caib.carpeta.logic.EstadisticaLogicaLocal;
import es.caib.carpeta.logic.LogCarpetaLogicaLocal;
import es.caib.carpeta.logic.UsuariEntitatLogicaLocal;
import es.caib.carpeta.logic.UsuariLogicaLocal;
import es.caib.carpeta.logic.utils.EjbManager;
import es.caib.carpeta.model.fields.*;

/**
 *
 * @author anadal(u80067)
 *
 */
@Component
public class AuthenticationSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	protected final Logger log = Logger.getLogger(getClass());



	/*
	 * public static final Set<String> allowedApplicationContexts = new
	 * HashSet<String>();
	 */

	@Override
	public synchronized void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		long temps = System.currentTimeMillis();
		StringBuilder peticio = new StringBuilder();

		peticio.append("Usuari: no definit").append(System.getProperty("line.separator"));
		peticio.append("classe: ").append(getClass().getName()).append(System.getProperty("line.separator"));

		SecurityContext sc = SecurityContextHolder.getContext();
		Authentication au = sc.getAuthentication();

		LogCarpetaLogicaLocal logCarpetaEjb;
		EstadisticaLogicaLocal estadisticaEjb;
		AuditoriaLogicaLocal auditoriaEjb;

		try {
			logCarpetaEjb = EjbManager.getLogCarpetaLogicaEJB();
			estadisticaEjb = EjbManager.getEstadisticaLogicaEJB();
			auditoriaEjb = EjbManager.getAuditoriaLogicaEJB();
		} catch (I18NException e) {
			// TODO traduccio
			throw new LoginException("Error carregant LogCarpetaLogicaEJB " + e.getMessage(), e);
		}
		try {
			logCarpetaEjb.crearLog("Iniciam Autenticació al back", ESTAT_LOG_OK, TIPUS_LOG_AUTENTICACIO_BACK, System.currentTimeMillis() - temps, null, "", peticio.toString(), "", null);
		}catch (I18NException ie){
			throw new LoginException("Error creant el log");
		}

		if (au == null) {
			// TODO traduccio


			try {
				logCarpetaEjb.crearLog("Autenticació al back", ESTAT_LOG_ERROR, TIPUS_LOG_AUTENTICACIO_BACK,System.currentTimeMillis() - temps ,null, "NO PUC ACCEDIR A LA INFORMACIO de AUTENTICACIO", peticio.toString(),"",null);
			}catch (I18NException ie){
				throw new LoginException("Error creant el log");
			}

			throw new LoginException("NO PUC ACCEDIR A LA INFORMACIO de AUTENTICACIO");
		}

		User user = (User) au.getPrincipal();

		String username = user.getUsername();
		peticio = new StringBuilder();
		peticio.append("Usuari: " ).append(username).append(System.getProperty("line.separator"));
		peticio.append("classe:").append(getClass().getName()).append(System.getProperty("line.separator"));

		log.debug(" =================================================================");
		log.info(" ============ Login Usuari: " + username);

		try {
			LoginInfo loginInfo = LoginInfo.getInstance();

			if (!username.equals(loginInfo.getUsuariPersona().getUsername())) {
				throw new LoginException("Amb aquest navegador ja s'ha autenticat amb un altre usuari."
						+ " Tanqui el navegador completament.");
			}

			try {
				logCarpetaEjb.crearLog("Autenticació al back de l'usuari: " + username, ESTAT_LOG_ERROR, TIPUS_LOG_AUTENTICACIO_BACK,System.currentTimeMillis() - temps ,null, "Amb aquest navegador ja s'ha autenticat amb un altre usuari. Tanqui el navegador completament."
					, peticio.toString(),"",null);
			}catch (I18NException ie){
				throw new LoginException("Error creant el log");
			}
		} catch (Throwable e) {
			log.debug(" XYZ ZZZ ZZZ S'ha produit un error consultant la informació de login actual: " + e.getMessage());

		}

		final boolean isDebug = log.isDebugEnabled();

		// Cercam si té el ROLE_ADMIN o ROLE_SUPER
		Collection<GrantedAuthority> seyconAuthorities = user.getAuthorities();
		boolean containsRoleAdEn = false;
		boolean containsRoleSuper = false;

		if (seyconAuthorities.size() == 0) {
			log.info(" =======  XYZ ZZZ NO TE CAP ROL =========== ");

		} else {

			for (GrantedAuthority grantedAuthority : seyconAuthorities) {
				String rol = grantedAuthority.getAuthority();

				log.info("XYZ ZZZ ZZZ Rol SEYCON : " + rol);

				if (Constants.ROLE_ADMIN.equals(rol)) {
					containsRoleAdEn = true;
				}
				if (Constants.ROLE_SUPER.equals(rol)) {
					containsRoleSuper = true;
				}
			}
		}

		UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;
		UsuariLogicaLocal usuariPersonaLogicaEjb;
		EntitatLocal entitatLogicaEjb;
		PropietatGlobalLocal propietatGlobalEjb;
		try {
			usuariEntitatLogicaEjb = EjbManager.getUsuariEntitatLogicaEJB();
			usuariPersonaLogicaEjb = EjbManager.getUsuariPersonaLogicaEJB();
			entitatLogicaEjb = EjbManager.getEntitatLogicaEJB();
			propietatGlobalEjb =  EjbManager.getPropietatLogicaEJB();
		} catch (I18NException e) {
			// TODO traduccio
			throw new LoginException("No puc accedir al gestor d´obtenció de" + " informació d´usuari-entitat per "
					+ username + ": " + e.getMessage(), e);
		}

		UsuariJPA usuariPersona;
		try {
			usuariPersona = usuariPersonaLogicaEjb.findByUsername(username);
		} catch (I18NException e1) {
			String msg = I18NUtils.getMessage(e1);
			log.error("Error llegint si l'usuari es troba a la BBDD: " + msg, e1);

			try {
				logCarpetaEjb.crearLog("Autenticació al back de l'usuari: " + username, ESTAT_LOG_ERROR,TIPUS_LOG_AUTENTICACIO_BACK, System.currentTimeMillis() - temps ,e1,"Error llegint si l'usuari es troba a la BBDD: " + msg,peticio.toString(),"",null);
			}catch (I18NException ie){
				throw new LoginException("Error creant el log");
			}

			usuariPersona = null;
		}
		boolean necesitaConfigurar = false;

		if (usuariPersona == null) {
		    log.info(" La persona amb username  '" +username + "' no esta registrada !!!!");
			// Revisar si és un Administrador que entra per primera vegada

			{
				try {
					UsuariJPA persona;
					// XYZ ZZZ ZZZ ZZZ TODO
					persona = usuariPersonaLogicaEjb.getUserInfoFromUserInformation(username);

					if (persona == null) {
					    log.error("No hem trobat informació de la Persona amb username  '"
					           + username + "' dins del sistema de UserInformation");
					    
					} else {

						UsuariEntitatJPA usuariEntitat = null;

						log.info("Contains role AdminEntitat: " + containsRoleAdEn );
						if (containsRoleAdEn) {
							
							String defaultEntityCode = EjbManager.getDefaultEntityCode(propietatGlobalEjb);
							log.info("Default Entity Code => " +  defaultEntityCode);
							
							if (defaultEntityCode != null && defaultEntityCode.trim().length() != 0) {
							    
							    Long defaultEntity = entitatLogicaEjb.executeQueryOne(EntitatFields.ENTITATID, EntitatFields.CODI.equal(defaultEntityCode));
							
							    log.info("Default Entity ID => " +  defaultEntity);

    							if (defaultEntity != null) {
    								usuariEntitat = new UsuariEntitatJPA();
    								// usuariEntitat.setActiu(true);
    								usuariEntitat.setEntitatID(defaultEntity);    								
    								usuariEntitat.setActiu(true);
    								//usuariEntitat.setUsuari(persona);
    							}
							}

						}

						necesitaConfigurar = true;
						
						persona = usuariPersonaLogicaEjb.crearUsuari(persona);

						if (usuariEntitat == null) {
						    usuariPersona = persona;
						} else {
						    usuariEntitat.setUsuariID(persona.getUsuariID());
							usuariEntitat = (UsuariEntitatJPA)usuariEntitatLogicaEjb.create(usuariEntitat);
							usuariPersona = persona;
							
						}
						log.info("necesitaConfigurarUsuari = " + necesitaConfigurar);
					}

				} catch (Throwable e) {
					usuariPersona = null;
					String msg;
					if (e instanceof I18NException) {
						msg = I18NUtils.getMessage((I18NException) e);
					} else if (e instanceof I18NValidationException) {
						msg = I18NUtils.getMessage((I18NValidationException) e);
					} else {
						msg = e.getMessage();
					}

					try {
						logCarpetaEjb.crearLog("Autenticació al back de l'usuari: " + username, ESTAT_LOG_ERROR,TIPUS_LOG_AUTENTICACIO_BACK, System.currentTimeMillis() - temps ,e,"Error llegint informacio del plugin de User Information o creant l'usuari a la BBDD: " + msg,peticio.toString(),"",null);
					}catch (I18NException ie){
						throw new LoginException("Error creant el log");
					}

					log.error("Error llegint informacio del plugin de User Information o creant l'usuari a la BBDD: " + msg, e);
				}
			}

		}

		Set<UsuariEntitatJPA> usuariEntitats = null;
		if (usuariPersona != null) {
			// usuariEntitats = usuariPersona.getUsuariEntitatJPAs();

			try {
				usuariEntitats = new HashSet<UsuariEntitatJPA>(
						usuariEntitatLogicaEjb.findAllByUsuariIdWithEntitat(usuariPersona.getUsuariID()));
			} catch (I18NException e) {
				log.error(I18NUtils.getMessage(e) , e);
				try {
					logCarpetaEjb.crearLog("Autenticació al back de l'usuri " + username, ESTAT_LOG_ERROR,TIPUS_LOG_AUTENTICACIO_BACK, System.currentTimeMillis() - temps ,e,"",peticio.toString(),"",null);
				}catch (I18NException ie){
					throw new LoginException("Error creant el log");
				}
			}
			
			log.info("Total UsuariEntitats:" + usuariEntitats.size());
			log.info("isCAR_ADMIN: " + containsRoleAdEn);
		}

		if (usuariEntitats == null) {
			usuariEntitats = new HashSet<UsuariEntitatJPA>();
		}

		if (!containsRoleAdEn && usuariEntitats.size() != 0) {
			// L'usuari " + name + " està assignat a una o varies
			// entitats però no té el rol ROLE_ADEN";
			I18NTranslation translation = new I18NTranslation("error.sensecaradmin", username);
			log.error("");
			log.error(I18NUtils.tradueix(translation));
			log.error("Authentication Info:\n" + au);
			log.error("");

			try {
				logCarpetaEjb.crearLog("Autenticació al back de l'usuari: "+ username, ESTAT_LOG_ERROR,TIPUS_LOG_AUTENTICACIO_BACK, System.currentTimeMillis() - temps ,null,I18NUtils.tradueix(translation),peticio.toString(),"",null);
			}catch (I18NException ie){
				throw new LoginException("Error creant el log");
			}

			// Com enviar-ho a la PAGINA WEB
			BasePreparer.loginErrorMessage.put(username, translation);

			usuariEntitats = new HashSet<UsuariEntitatJPA>();
		}

		// Seleccionam l'entitat per defecte i verificam que les entitats disponibles
		// siguin correctes
		Map<Long, EntitatJPA> entitats = new HashMap<Long, EntitatJPA>();
		Map<Long, Set<GrantedAuthority>> rolesPerEntitat = new HashMap<Long, Set<GrantedAuthority>>();
		rolesPerEntitat.put((Long) null, new HashSet<GrantedAuthority>(seyconAuthorities));
		Map<Long, UsuariEntitatJPA> usuariEntitatPerEntitatID = new HashMap<Long, UsuariEntitatJPA>();
		EntitatJPA entitatPredeterminada = null;
		for (UsuariEntitatJPA usuariEntitat : usuariEntitats) {

			EntitatJPA entitat = usuariEntitat.getEntitat();

			if (entitat == null) {
				// XYZ ZZZ ZZZ
//        log.info("Configuracio.getDefaultEntity() = ]" + PropietatGlobalUtil.getDefaultEntity() + "[");
//        log.info("ROLES = ]" + Arrays.toString(seyconAuthorities.toArray())+ "[");
//        log.warn("Entitat val null");
				continue;
			}

			Long entitatID = entitat.getEntitatID();
			if (isDebug) {
				log.debug("--------------- Entitat " + entitatID);
			}
			
			// Check deshabilitada
			// XYZ ZZZ ZZZ
			/*
			 * if (!entitat.isActiva()) { log.warn("L'entitat " + entitat.getNom() +
			 * " esta deshabilitada."); continue; } if (!usuariEntitat.isActiu()) {
			 * log.warn("L'entitat " + entitat.getNom() +
			 * " esta deshabilitatda per l'usuari " + usuariPersona.getId()); continue; }
			 */

			// Per si no n'hi ha cap per defecte
			if (entitatPredeterminada == null) {
				entitatPredeterminada = entitat;
			}
			// Cercam Rols Virtuals ROLE_ADMIN de carpeta
			if (containsRoleAdEn) {

				Set<GrantedAuthority> rolescarpeta = new TreeSet<GrantedAuthority>(GRANTEDAUTHORITYCOMPARATOR);
				rolescarpeta.addAll(seyconAuthorities);

				// XYZ ZZZ ZZZ
				/*
				 * if (usuariEntitat.isAden()) { rolescarpeta.add(new
				 * SimpleGrantedAuthority(Constants.CAR_ADEN)); }
				 */

				rolesPerEntitat.put(entitatID, rolescarpeta);
			} else {
				log.debug("No te el role seycon CAR_ADMIN");
				rolesPerEntitat.put(entitatID, new HashSet<GrantedAuthority>());
			}
			// Entitat per defecte
			// XYZ ZZZ ZZZ
//      if (usuariEntitat.isPredeterminat()) {
//        entitatPredeterminada = entitat;
//      }
			
			// Entitats
			entitats.put(entitatID, usuariEntitat.getEntitat());
			// Usuari Entitat
			usuariEntitat.setUsuari(usuariPersona);
			usuariEntitatPerEntitatID.put(entitatID, usuariEntitat);
		}

		log.info(" XYZ ZZZ  entitats.size()  => " + entitats.size());
		log.info(" XYZ ZZZ  containsRoleSuper  => " + containsRoleSuper);
		log.info(" XYZ ZZZ  !containsRoleSuper => " + !containsRoleSuper);

		if (entitats.size() == 0 && containsRoleAdEn) {

			if (usuariEntitats.size() == 0) {
				// L'usuari " + name + " no té cap entitat associada. Consulti amb
				// l'Administrador
				I18NTranslation translation = new I18NTranslation("error.senseentitat", username);
				BasePreparer.loginErrorMessage.put(username, translation);
				try {
					logCarpetaEjb.crearLog("Autenticació al back de l'usuari: " + username, ESTAT_LOG_ERROR,TIPUS_LOG_AUTENTICACIO_BACK, System.currentTimeMillis() - temps ,null,I18NUtils.tradueix(translation),peticio.toString(),"",null);
				}catch (I18NException ie){
					throw new LoginException("Error creant el log");
				}
			} else {
				// Les entitats a les que pertany estan desactivades
				// "L'usuari " + name + " no té cap entitat vàlida associada");
				I18NTranslation translation = new I18NTranslation("error.senseentitatvalida", username);
				BasePreparer.loginErrorMessage.put(username, translation);
				try {
					logCarpetaEjb.crearLog("Autenticació al back de l'usuari:" + username, ESTAT_LOG_ERROR,TIPUS_LOG_AUTENTICACIO_BACK, System.currentTimeMillis() - temps ,null,I18NUtils.tradueix(translation),peticio.toString(),"",null);
				}catch (I18NException ie){
					throw new LoginException("Error creant el log");
				}
			}
		}

		Long entitatIDActual = null;
		String entitatCodiActual = "" ;
		if (entitatPredeterminada != null) {
			entitatIDActual = entitatPredeterminada.getEntitatID();
			entitatCodiActual = entitatPredeterminada.getCodi();
			if (isDebug) {
				log.debug(">>>>>> Entitat predeterminada " + entitatIDActual);
			}
		}

		LoginInfo loginInfo;
		// create a new authentication token

		log.info("user => " + user);
		log.info("usuariPersona => " + usuariPersona);
		log.info("entitatIDActual => " + entitatIDActual);		
		log.info("rolesPerEntitat  => " + rolesPerEntitat);
		log.info(" usuariEntitatPerEntitatID => " + usuariEntitatPerEntitatID);
		log.info("necesitaConfigurar => " + necesitaConfigurar);

		loginInfo = new LoginInfo(username, user, usuariPersona, entitatIDActual, entitats, rolesPerEntitat,
				usuariEntitatPerEntitatID, necesitaConfigurar);

		// and set the authentication of the current Session context
		SecurityContextHolder.getContext().setAuthentication(loginInfo.generateToken());


		try {

			//ESTADISTICA
			List<EstadisticaJPA> estadisticas = estadisticaEjb.findEstadistica(TIPUS_ESTAD_ENTRADA_BACK,null,new Date(),null);

			if(estadisticas != null && !estadisticas.isEmpty()) {

				estadisticaEjb.incrementarComptador(estadisticas.get(0));
			}else{
				estadisticaEjb.crearEstadistica(null, TIPUS_ESTAD_ENTRADA_BACK,null);
			}

			//LOG
			logCarpetaEjb.crearLog("Autenticació al back - Usuari: " + username , ESTAT_LOG_OK,TIPUS_LOG_AUTENTICACIO_BACK, System.currentTimeMillis() - temps ,null,"",peticio.toString(),entitatCodiActual,null);

			//AUDITORIA
			auditoriaEjb.crearAuditoria(TIPUS_AUDIT_ENTRADA_BACK,null, loginInfo.getUsuariPersona().getUsuariID(),"",null);
		}catch (I18NException ie){
			throw new LoginException("Error creant el log o l'estadistica");
		}


		if (isDebug) {
			log.debug(">>>>>> Final del Process d'autenticació.");
		}
		log.debug(" =================================================================");

	}

	public static final Comparator<GrantedAuthority> GRANTEDAUTHORITYCOMPARATOR = new Comparator<GrantedAuthority>() {
		@Override
		public int compare(GrantedAuthority o1, GrantedAuthority o2) {
			return -o1.getAuthority().compareTo(o2.getAuthority());
		}
	};

}
