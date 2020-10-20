
package es.caib.carpeta.jpa;

import es.caib.carpeta.model.dao.ILogCarpetaManager;
import es.caib.carpeta.model.entity.LogCarpeta;
import es.caib.carpeta.model.fields.LogCarpetaFields;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.TableName;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class LogCarpetaJPAManager
		 extends AbstractJPAManager<LogCarpeta, Long>
		 implements LogCarpetaIJPAManager, ILogCarpetaManager, LogCarpetaFields {




  private static final long serialVersionUID = 1505320484L;

	 public static final TableName<LogCarpeta> _TABLENAME =  new TableName<LogCarpeta>("LogCarpetaJPA");


  @PersistenceContext
  protected EntityManager __em;

  public LogCarpetaJPAManager() {
  }

  protected LogCarpetaJPAManager(EntityManager __em) {
    this.__em = __em;
  }

  @Override
  public EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return LogCarpetaJPA. class;
	}



	public TableName<LogCarpeta> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public LogCarpeta[] listToArray(List<LogCarpeta> list)  {
		if(list == null) { return null; };
		return list.toArray(new LogCarpeta[list.size()]);
	};

	public synchronized LogCarpeta create( java.lang.String _descripcio_, java.lang.Long _entitatID_, java.lang.Long _pluginID_, int _tipus_, int _estat_, java.lang.Long _temps_, java.sql.Timestamp _dataInici_, java.lang.String _peticio_, java.lang.String _error_, java.lang.String _excepcio_) throws I18NException {
		LogCarpetaJPA __bean =  new LogCarpetaJPA(_descripcio_,_entitatID_,_pluginID_,_tipus_,_estat_,_temps_,_dataInici_,_peticio_,_error_,_excepcio_);
		return create(__bean);
	}



 public void delete(long _logID_) {
   delete(findByPrimaryKey(_logID_));
 }




	public LogCarpeta findByPrimaryKey(long _logID_) {
	  return __em.find(LogCarpetaJPA.class, _logID_);  
	}
	@Override
	protected LogCarpeta getJPAInstance(LogCarpeta __bean) {
		return convertToJPA(__bean);
	}


	public static LogCarpetaJPA convertToJPA(LogCarpeta __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof LogCarpetaJPA) {
	    return (LogCarpetaJPA)__bean;
	  }
	  
	  return LogCarpetaJPA.toJPA(__bean);
	}


}