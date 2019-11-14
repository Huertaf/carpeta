<%@include file="/WEB-INF/views/includes.jsp"%>

<nav>
    <div class="container">

        <div class="row">
            <!-- Notificaciones -->
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <button class="box-part text-center" onclick="goToWindow('${notificacionesUrl}')">
                    <span class="oi oi-envelope-closed imagenMenu" title="<fmt:message key="menu.notificaciones"/>" alt="<fmt:message key="menu.notificaciones"/>" aria-hidden="true"></span>
                    <div class="tituloMenu"><fmt:message key="menu.notificaciones"/></div>
                    <div class="text">
                        <span><fmt:message key="menu.lorem"/></span>
                    </div>
                </button>
            </div>

            <!-- Otras notificaciones -->
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <button class="box-part text-center" onclick="goToWindow('${zonaperUrl}')">
                    <span class="oi oi-box imagenMenu" title="<fmt:message key="menu.notificaciones.otras"/>" alt="<fmt:message key="menu.notificaciones.otras"/>" aria-hidden="true"></span>
                    <div class="tituloMenu"><fmt:message key="menu.notificaciones.otras"/></div>
                    <div class="text">
                        <span><fmt:message key="menu.lorem"/></span>
                    </div>
                </button>
            </div>

        </div>
    </div>

</nav>