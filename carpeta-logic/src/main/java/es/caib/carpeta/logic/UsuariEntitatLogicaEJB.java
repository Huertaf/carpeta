package es.caib.carpeta.logic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;

/*
import es.caib.carpeta.ejb.AnnexEJB;
import es.caib.carpeta.ejb.FitxerLocal;
import es.caib.carpeta.jpa.AnnexJPA;
import es.caib.carpeta.model.entity.AnnexFirmat;
import es.caib.carpeta.model.fields.AnnexFields;
import es.caib.carpeta.model.fields.AnnexFirmatFields;

import org.fundaciobit.genapp.common.i18n.I18NException;

*/

import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.ejb.UsuariEntitatEJB;
import es.caib.carpeta.jpa.UsuariEntitatJPA;
import es.caib.carpeta.model.entity.UsuariEntitat;
import es.caib.carpeta.model.fields.UsuariEntitatFields;

/**
 * 
 * @author anadal
 *
 */
@Stateless
public class UsuariEntitatLogicaEJB extends UsuariEntitatEJB implements UsuariEntitatLogicaLocal {

	@Override
	@PermitAll
	public List<UsuariEntitatJPA> findAllByUsuariId(@NotNull long usuarioID) throws I18NException {

		List<UsuariEntitat> list = select(UsuariEntitatFields.USUARIID.equal(usuarioID));

		List<UsuariEntitatJPA> list2 = new ArrayList<UsuariEntitatJPA>(list.size());
		for (UsuariEntitat usuariEntitat : list) {
			list2.add((UsuariEntitatJPA) usuariEntitat);
		}

		return list2;

	}

}