
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.LogCarpeta;


public class LogCarpetaBean implements LogCarpeta {



private static final long serialVersionUID = 1007346690L;

	long logID;// PK
	java.lang.String descripcio;
	java.lang.Long entitatID;
	java.lang.Long pluginID;
	int tipus;
	int estat;
	java.lang.Long temps;
	java.sql.Timestamp dataInici;
	java.lang.String peticio;
	java.lang.String error;
	java.lang.String excepcio;


  /** Constructor Buit */
  public LogCarpetaBean() {
  }

  /** Constructor amb tots els camps  */
  public LogCarpetaBean(long logID , java.lang.String descripcio , java.lang.Long entitatID , java.lang.Long pluginID , int tipus , int estat , java.lang.Long temps , java.sql.Timestamp dataInici , java.lang.String peticio , java.lang.String error , java.lang.String excepcio) {
    this.logID=logID;
    this.descripcio=descripcio;
    this.entitatID=entitatID;
    this.pluginID=pluginID;
    this.tipus=tipus;
    this.estat=estat;
    this.temps=temps;
    this.dataInici=dataInici;
    this.peticio=peticio;
    this.error=error;
    this.excepcio=excepcio;
}
  /** Constructor sense valors autoincrementals */
  public LogCarpetaBean(java.lang.String descripcio , java.lang.Long entitatID , java.lang.Long pluginID , int tipus , int estat , java.lang.Long temps , java.sql.Timestamp dataInici , java.lang.String peticio , java.lang.String error , java.lang.String excepcio) {
    this.descripcio=descripcio;
    this.entitatID=entitatID;
    this.pluginID=pluginID;
    this.tipus=tipus;
    this.estat=estat;
    this.temps=temps;
    this.dataInici=dataInici;
    this.peticio=peticio;
    this.error=error;
    this.excepcio=excepcio;
}
  /** Constructor dels valors Not Null */
  public LogCarpetaBean(long logID , java.lang.String descripcio , int tipus , int estat , java.sql.Timestamp dataInici) {
    this.logID=logID;
    this.descripcio=descripcio;
    this.tipus=tipus;
    this.estat=estat;
    this.dataInici=dataInici;
}
  public LogCarpetaBean(LogCarpeta __bean) {
    this.setLogID(__bean.getLogID());
    this.setDescripcio(__bean.getDescripcio());
    this.setEntitatID(__bean.getEntitatID());
    this.setPluginID(__bean.getPluginID());
    this.setTipus(__bean.getTipus());
    this.setEstat(__bean.getEstat());
    this.setTemps(__bean.getTemps());
    this.setDataInici(__bean.getDataInici());
    this.setPeticio(__bean.getPeticio());
    this.setError(__bean.getError());
    this.setExcepcio(__bean.getExcepcio());
	}

	public long getLogID() {
		return(logID);
	};
	public void setLogID(long _logID_) {
		this.logID = _logID_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.Long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.Long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.Long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(java.lang.Long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public int getEstat() {
		return(estat);
	};
	public void setEstat(int _estat_) {
		this.estat = _estat_;
	};

	public java.lang.Long getTemps() {
		return(temps);
	};
	public void setTemps(java.lang.Long _temps_) {
		this.temps = _temps_;
	};

	public java.sql.Timestamp getDataInici() {
		return(dataInici);
	};
	public void setDataInici(java.sql.Timestamp _dataInici_) {
		this.dataInici = _dataInici_;
	};

	public java.lang.String getPeticio() {
		return(peticio);
	};
	public void setPeticio(java.lang.String _peticio_) {
		this.peticio = _peticio_;
	};

	public java.lang.String getError() {
		return(error);
	};
	public void setError(java.lang.String _error_) {
		this.error = _error_;
	};

	public java.lang.String getExcepcio() {
		return(excepcio);
	};
	public void setExcepcio(java.lang.String _excepcio_) {
		this.excepcio = _excepcio_;
	};



  // ======================================

  public static LogCarpetaBean toBean(LogCarpeta __bean) {
    if (__bean == null) { return null;}
    LogCarpetaBean __tmp = new LogCarpetaBean();
    __tmp.setLogID(__bean.getLogID());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setTemps(__bean.getTemps());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setPeticio(__bean.getPeticio());
    __tmp.setError(__bean.getError());
    __tmp.setExcepcio(__bean.getExcepcio());
		return __tmp;
	}



}
