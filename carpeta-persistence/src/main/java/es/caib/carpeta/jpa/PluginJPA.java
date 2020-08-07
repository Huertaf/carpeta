
package es.caib.carpeta.jpa;
import es.caib.carpeta.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "car_plugin" )
@SequenceGenerator(name="CARPETA_SEQ", sequenceName="car_carpeta_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class PluginJPA implements Plugin {



private static final long serialVersionUID = 190357384L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CARPETA_SEQ")
	@Index(name="car_plugin_pk_i")
	@Column(name="pluginid",nullable = false,length = 19)
	long pluginID;

	@Index(name="car_plugin_nomid_fk_i")
	@Column(name="nomid",nullable = false,length = 19)
	long nomID;

	@Column(name="classe",nullable = false,length = 255)
	java.lang.String classe;

	@Column(name="propietats",length = 2147483647)
  @Lob
	java.lang.String propietats;

	@Column(name="actiu",nullable = false,length = 1)
	boolean actiu;

	@Column(name="tipus",nullable = false,length = 10)
	int tipus;



  /** Constructor Buit */
  public PluginJPA() {
  }

  /** Constructor amb tots els camps  */
  public PluginJPA(long pluginID , long nomID , java.lang.String classe , java.lang.String propietats , boolean actiu , int tipus) {
    this.pluginID=pluginID;
    this.nomID=nomID;
    this.classe=classe;
    this.propietats=propietats;
    this.actiu=actiu;
    this.tipus=tipus;
}
  /** Constructor sense valors autoincrementals */
  public PluginJPA(long nomID , java.lang.String classe , java.lang.String propietats , boolean actiu , int tipus) {
    this.nomID=nomID;
    this.classe=classe;
    this.propietats=propietats;
    this.actiu=actiu;
    this.tipus=tipus;
}
  public PluginJPA(Plugin __bean) {
    this.setPluginID(__bean.getPluginID());
    this.setNomID(__bean.getNomID());
    this.setClasse(__bean.getClasse());
    this.setPropietats(__bean.getPropietats());
    this.setActiu(__bean.isActiu());
    this.setTipus(__bean.getTipus());
	}

	public long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public long getNomID() {
		return(nomID);
	};
	public void setNomID(long _nomID_) {
		this.nomID = _nomID_;
	};

	public java.lang.String getClasse() {
		return(classe);
	};
	public void setClasse(java.lang.String _classe_) {
		this.classe = _classe_;
	};

	public java.lang.String getPropietats() {
		return(propietats);
	};
	public void setPropietats(java.lang.String _propietats_) {
		this.propietats = _propietats_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Plugin) {
      Plugin __instance = (Plugin)__obj;
      __result = true;
      __result = __result && (this.getPluginID() == __instance.getPluginID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:pluginid | Table: car_log | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "plugin")
	private Set<LogCarpetaJPA> logCarpetas = new HashSet<LogCarpetaJPA>(0);
	public  Set<LogCarpetaJPA> getLogCarpetas() {
    return this.logCarpetas;
  }

	public void setLogCarpetas(Set<LogCarpetaJPA> logCarpetas) {
	  this.logCarpetas = logCarpetas;
	}


// IMP Field:traduccioid | Table: car_traduccio | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
	@ForeignKey(name="car_plugin_traduccio_nom_fk")
	@JoinColumn(name = "nomid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false)
	private TraduccioJPA nom;

	public TraduccioJPA getNom() {
    return this.nom;
  }

	public  void setNom(TraduccioJPA nom) {
    this.nom = nom;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.carpeta.jpa.TraduccioMapJPA> getNomTraduccions() {
    return this.nom.getTraduccions();
  }

  public void setNomTraduccions(java.util.Map<String, es.caib.carpeta.jpa.TraduccioMapJPA> __traduccions__) {
    this.nom.setTraduccions(__traduccions__);
  }



 // ---------------  STATIC METHODS ------------------
  public static PluginJPA toJPA(Plugin __bean) {
    if (__bean == null) { return null;}
    PluginJPA __tmp = new PluginJPA();
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setClasse(__bean.getClasse());
    __tmp.setPropietats(__bean.getPropietats());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setTipus(__bean.getTipus());
		return __tmp;
	}


  public static PluginJPA copyJPA(PluginJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PluginJPA> copyJPA(java.util.Set<PluginJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<PluginJPA> __tmpSet = (java.util.Set<PluginJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PluginJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PluginJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PluginJPA copyJPA(PluginJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PluginJPA __tmp = (PluginJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"LogCarpetaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.logCarpetas) || org.hibernate.Hibernate.isInitialized(__jpa.getLogCarpetas())) ) {
      __tmp.setLogCarpetas(LogCarpetaJPA.copyJPA(__jpa.getLogCarpetas(), __alreadyCopied,"PluginJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.nom) || org.hibernate.Hibernate.isInitialized(__jpa.getNom()) ) ) {
      __tmp.setNom(TraduccioJPA.copyJPA(__jpa.getNom(), __alreadyCopied,"PluginJPA"));
    }

    return __tmp;
  }




}