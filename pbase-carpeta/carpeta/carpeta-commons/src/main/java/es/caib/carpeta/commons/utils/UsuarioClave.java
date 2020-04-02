package es.caib.carpeta.core.utils;

public class UsuarioClave {

    private String nombre;
    private String apellido1;
    private String apellido2;
    private String nif;
    private String metodoAutentificacion;
    private String qaa;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getMetodoAutentificacion() {
        return metodoAutentificacion;
    }

    public void setMetodoAutentificacion(String metodoAutentificacion) {
        this.metodoAutentificacion = metodoAutentificacion;
    }

    public String getQaa() {
        return qaa;
    }

    public void setQaa(String qaa) {
        this.qaa = qaa;
    }

    public String getNombreCompleto(){

        String nombreCompleto = "";

        if(StringUtils.isNotEmpty(getApellido1()) && StringUtils.isNotEmpty(getApellido2())){
            nombreCompleto += getApellido1() + " " + getApellido2()+", ";
        }else if(StringUtils.isNotEmpty(getApellido1())){
            nombreCompleto += getApellido1() + ", ";
        }

        nombreCompleto += getNombre();

        return nombreCompleto;
    }
}