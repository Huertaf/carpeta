<%@include file="/WEB-INF/views/includes.jsp"%>
<div class="infoContacto">

    <!-- Button trigger modal -->
    <strong><fmt:message key="soporte.ayuda"/> </strong><fmt:message key="soporte.contacto"/>
    <a style="font-weight: bolder; color: #32814b" class="btn btn-soporte" data-toggle="modal" data-target="#modalContacto" id="exampleModalCenterTitle">
        <fmt:message key="soporte.equipo"/>
    </a>

    <!-- Modal -->
    <div class="modal fade" id="modalContacto" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="modal-title h2" id="exampleModalLongTitle"><span class="oi oi-info" title="" aria-hidden="true"></span> <fmt:message key="soporte.mensaje.ayuda"/></span>
                    <button type="button" class="close" data-dismiss="modal" aria-label="<fmt:message key="soporte.mensaje.cerrar"/>">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="row col-12 datosSoporte">
                        <div class="row col-12"><fmt:message key="soporte.mensaje.necesita"/></div>

                        <ul class="dades">
                            <li>
                                <fmt:message key="soporte.mensaje.envia"/> <a href="mailto:otae@fundaciobit.org" target="_blank"><fmt:message key="soporte.mensaje.correo"/></a>
                            </li>
                            <li>
                                <fmt:message key="soporte.mensaje.llamar"/> <a href="tel:971177070"><fmt:message key="soporte.mensaje.telefono"/></a>.
                            </li>
                            <li>
                                <fmt:message key="soporte.mensaje.autenticacion"/> <a href="tel:012">012</a>.
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="soporte.mensaje.cerrar"/></button>
                </div>
            </div>
        </div>
    </div>

</div>