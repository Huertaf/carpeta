<%@include file="/WEB-INF/views/includes.jsp"%>
<div class="imc-missatge" id="imc-missatge" data-accio="carregant" aria-hidden="true" role="alertdialog">
    <div class="imc--c">

        <div class="imc--text">

            <h2><span><fmt:message key="mensaje.cargar"/></span></h2>

            <div></div>

            <ul class="imc--botonera">
                <li>
                    <button type="button" class="imc-bt imc--ico imc--principal" data-tipus="accepta"><span><fmt:message key="mensaje.aceptar"/></span></button>
                </li>
                <li>
                    <button type="button" class="imc-bt imc--cancela" data-tipus="cancela"><span><fmt:message key="mensaje.cancelar"/></span></button>
                </li>
                <li>
                    <button type="button" class="imc-bt imc--cancela" data-tipus="tanca"><span><fmt:message key="mensaje.cerrar"/></span></button>
                </li>
            </ul>

        </div>

    </div>
</div>