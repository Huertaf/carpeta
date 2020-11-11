package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.Date;

import es.caib.carpeta.ejb.AuditoriaEJB;
import es.caib.carpeta.jpa.AuditoriaJPA;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 10/11/2020
 */
@Stateless
public class AuditoriaLogicaEJB extends AuditoriaEJB implements AuditoriaLogicaLocal {


    @Override
    public void crearAuditoria(@NotNull int tipus, Long entitatID, Long usuariID, String ticketLoginIB, Integer pluginID) throws I18NException {

        AuditoriaJPA auditoriaJPA = new AuditoriaJPA();


        auditoriaJPA.setDataAudit(new Timestamp(new Date().getTime()));
        auditoriaJPA.setTipus(tipus);
        auditoriaJPA.setEntitatID(entitatID);
        auditoriaJPA.setUsuariID(usuariID);
        auditoriaJPA.setTicketLoginIB(ticketLoginIB);
        auditoriaJPA.setPluginID(pluginID);

        create(auditoriaJPA);


    }


}