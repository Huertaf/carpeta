
package es.caib.carpeta.jpa;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.carpeta.model.entity.*;
import es.caib.carpeta.model.fields.*;
import es.caib.carpeta.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class EntitatJPAManager
		 extends AbstractJPAManager<Entitat, Long>
		 implements EntitatIJPAManager, IEntitatManager, EntitatFields {




  private static final long serialVersionUID = 1660227099L;

	 public static final TableName<Entitat> _TABLENAME =  new TableName<Entitat>("EntitatJPA");


  @PersistenceContext
  protected EntityManager __em;

  public EntitatJPAManager() {
  }

  protected EntitatJPAManager(EntityManager __em) {
    this.__em = __em;
  }

  @Override
  public EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return EntitatJPA. class;
	}



	public TableName<Entitat> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Entitat[] listToArray(List<Entitat> list)  {
		if(list == null) { return null; };
		return list.toArray(new Entitat[list.size()]);
	};

	public synchronized Entitat create( long _nomID_, java.lang.String _codi_, java.lang.String _codiDir3_, boolean _activa_, java.lang.Long _logoMenuID_, java.lang.String _colorMenu_, java.lang.String _textePeu_, long _logoPeuID_, java.lang.String _versio_, java.lang.String _commit_, java.lang.Long _fitxerCssID_, java.lang.String _context_) throws I18NException {
		EntitatJPA __bean =  new EntitatJPA(_nomID_,_codi_,_codiDir3_,_activa_,_logoMenuID_,_colorMenu_,_textePeu_,_logoPeuID_,_versio_,_commit_,_fitxerCssID_,_context_);
		return create(__bean);
	}



 public void delete(long _entitatID_) {
   delete(findByPrimaryKey(_entitatID_));
 }




	public Entitat findByPrimaryKey(long _entitatID_) {
	  return __em.find(EntitatJPA.class, _entitatID_);  
	}
	@Override
	protected Entitat getJPAInstance(Entitat __bean) {
		return convertToJPA(__bean);
	}


	public static EntitatJPA convertToJPA(Entitat __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof EntitatJPA) {
	    return (EntitatJPA)__bean;
	  }
	  
	  return EntitatJPA.toJPA(__bean);
	}

  @Override
  public Entitat create(Entitat transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.create(transientInstance);
  }


  @Override
  public Entitat update(Entitat transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.update(transientInstance);
  }


  private void processTranslations(Entitat transientInstance) {
    if (transientInstance != null) {
      if (transientInstance.getNomID() == 0) {
        if (transientInstance instanceof EntitatJPA) {
          EntitatJPA _jpa = (EntitatJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getNom();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setNomID(_trad.getTraduccioID());
          }
        }
      }
    }
  }


}