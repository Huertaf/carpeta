<%@include file="/WEB-INF/views/includes.jsp"%>

<nav>
    <div class="container">

        <div class="row">
            <!-- Trámites no enviados -->
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <button class="box-part text-center" onclick="goTo('<c:url value="/tramite/pendientes"/>')">
                    <span class="oi oi-pencil imagenMenu" title="" alt="" aria-hidden="true"></span>
                    <div class="tituloMenu"><fmt:message key="menu.tramites.no.acabados"/></div>
                    <div class="text">
                        <span><fmt:message key="menu.lorem"/></span>
                    </div>
                </button>
            </div>

            <!-- Trámites enviados -->
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <button class="box-part text-center" onclick="goTo('<c:url value="/tramite/finalizados"/>')">
                    <span class="oi oi-share-boxed imagenMenu" title="" alt="" aria-hidden="true"></span>
                    <div class="tituloMenu"><fmt:message key="menu.tramites.enviados"/></div>
                    <div class="text">
                        <span><fmt:message key="menu.lorem"/></span>
                    </div>
                </button>
            </div>

        </div>
    </div>

</nav>
