package es.caib.carpeta.persistence;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 20/05/2020
 */

//https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/

@Entity
//@SequenceGenerator(name="usuent-sequence",sequenceName = "CAR_USUARIOENTIDAD_SEQ", allocationSize = 1)
@Table(name = "CAR_USUARIOENTIDAD",
   indexes = {
      @Index(name = "CAR_USUARIOENTIDAD_PK_I", columnList = "ID"),
      @Index(name = "CAR_USUENT_USUARIO_FK_I", columnList = "USUARIO"),
      @Index(name = "CAR_USUENT_ENTIDAD_FK_I", columnList = "ENTIDAD")
   }
)


@Schema(name = "UsuarioEntidad")
public class UsuarioEntidad implements Serializable {


   @EmbeddedId
   private UsuarioEntidadId id;

   @NotNull
   @ManyToOne(fetch = FetchType.LAZY)
   @MapsId("usuarioId")
   @JoinColumn(name = "USUARIO", nullable = false,
      foreignKey = @ForeignKey(name = "CAR_USUENT_USUARIO_FK"))
   @JsonbTransient
   private Usuario usuario;

   @NotNull
   @ManyToOne(fetch = FetchType.LAZY)
   @MapsId("entidadId")
   @JoinColumn(name = "ENTIDAD", nullable = false,
      foreignKey = @ForeignKey(name = "CAR_USUENT_ENTIDAD_FK"))
   @JsonbTransient
   private Entidad entidad;



   /**
    *  Indica si el usuarioEntidad está activo
    */
   @NotNull
   @Column(name = "ACTIVO", nullable = false)
   private Boolean activo = true;



   public UsuarioEntidad() {
   }

   public UsuarioEntidad(Usuario usuario, Entidad entidad) {
      this.usuario = usuario;
      this.entidad = entidad;
      this.id = new UsuarioEntidadId(usuario.getId(), entidad.getId());
   }



   public UsuarioEntidadId getId() {
      return id;
   }

   public void setId(UsuarioEntidadId id) {
      this.id = id;
   }

   public Usuario getUsuario() {
      return usuario;
   }

   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }

   public Entidad getEntidad() {
      return entidad;
   }

   public void setEntidad(Entidad entidad) {
      this.entidad = entidad;
   }

   public Boolean getActivo() {
      return activo;
   }

   public void setActivo(Boolean activo) {
      this.activo = activo;
   }

   @Transient
   public String getNombreCompleto(){
      return getUsuario().getNombreCompleto();
   }



   @Override
   public boolean equals(Object o) {
      if (this == o) return true;

      if (o == null || getClass() != o.getClass())
         return false;

      UsuarioEntidad that = (UsuarioEntidad) o;
      return Objects.equals(usuario, that.usuario) &&
         Objects.equals(entidad, that.entidad);
   }

   @Override
   public int hashCode() {
      return Objects.hash(usuario, entidad);
   }


   @Override
   public String toString() {
      return "UsuarioEntidad{" +
         "usuario=" + usuario.getId() +
         ", entidad='" + entidad.getId() + '\'' +
         '}';
   }
}