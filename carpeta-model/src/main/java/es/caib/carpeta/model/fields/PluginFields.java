
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PluginFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "car_plugin";


  public static final String _TABLE_MODEL = "plugin";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PLUGINID = new LongField(_TABLE_MODEL, "pluginID", "pluginid");  // PK
	 public static final LongField NOMID = new LongField(_TABLE_MODEL, "nomID", "nomid");
	 public static final StringField CLASSE = new StringField(_TABLE_MODEL, "classe", "classe");
	 public static final StringField PROPIETATS = new StringField(_TABLE_MODEL, "propietats", "propietats");
	 public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu");
	 public static final IntegerField TIPUS = new IntegerField(_TABLE_MODEL, "tipus", "tipus");


  public static final Field<?>[] ALL_PLUGIN_FIELDS = {
    PLUGINID,
    NOMID,
    CLASSE,
    PROPIETATS,
    ACTIU,
    TIPUS
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PLUGINID
  };
}
