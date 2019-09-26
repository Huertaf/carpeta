<%@include file="/WEB-INF/views/includes.jsp"%>

<div class="collapse navbar-collapse" id="navbarCollapse">

    <ul class="navbar-nav movil">
        <sec:authorize access="isAuthenticated()">
            <li class="nav-item dropdown colorVerde">
                <a class="nav-link dropdown movil" href="#" id="menu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="oi oi-caret-bottom float-right flechaSubmenu" title="" alt="" aria-hidden="true"></span><p class="mb-0 float-right botonCorto"><fmt:message key="menu.gestiones"/></p><span class="oi oi-briefcase" title="<fmt:message key="menu.gestiones"/>" alt="<fmt:message key="menu.gestiones"/>" aria-hidden="true"></span></a>
                <div class="dropdown-menu dropdown-menu-right marg0" aria-labelledby="menu">
                    <a class="dropdown-item movil" href="<c:url value="/tramite/list"/>"><span class="oi oi-document" title="" alt="" aria-hidden="true"></span> <fmt:message key="menu.tramites"/></a>
                    <a class="dropdown-item movil" href="<c:url value="/registro/list"/>"><span class="oi oi-book" title="" alt="" aria-hidden="true"></span> <fmt:message key="menu.registros"/></a>
                </div>
            </li>
        </sec:authorize>

        <li class="nav-item colorVerde">
            <a class="nav-link movil accBoton" href="<c:url value="/accesibilidad"/>"><img src="${pageContext.request.contextPath}/static/img/ico_accessibilitat.svg" class="iconoCabecera accIcon" title="<fmt:message key="menu.accesibilidad"/>" alt="<fmt:message key="menu.accesibilidad"/>"/><p class="mb-0 float-right botonCorto"><fmt:message key="menu.accesibilidad"/></p></a>
        </li>

        <li class="nav-item dropdown colorVerde">
            <a class="nav-link dropdown movil" href="#" id="idiomas" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="oi oi-caret-bottom float-right flechaSubmenu" title="" alt="" aria-hidden="true"></span><p class="mb-0 float-right botonCorto"><fmt:message key="menu.idioma"/></p><span class="oi oi-globe" title="<fmt:message key="menu.idioma"/>" alt="<fmt:message key="menu.idioma"/>" aria-hidden="true"></span></a>
            <div class="dropdown-menu dropdown-menu-right marg0 minW0" aria-labelledby="idiomas">
                <c:if test="${pageContext.response.locale.language == 'ca'}">
                    <a class="dropdown-item movil" href="<c:url value="${requestScope.requestURI}?idioma=es"/>"><fmt:message key="idioma.castellano"/></a>
                    <a class="dropdown-item movil desactivado"><fmt:message key="idioma.catala"/></a>
                </c:if>
                <c:if test="${pageContext.response.locale.language == 'es'}">
                    <a class="dropdown-item movil" href="<c:url value="${requestScope.requestURI}?idioma=ca"/>"><fmt:message key="idioma.catala"/></a>
                    <a class="dropdown-item movil desactivado"><fmt:message key="idioma.castellano"/></a>
                </c:if>

            </div>
        </li>
        <sec:authorize access="isAuthenticated()">
            <li class="nav-item colorVerde">
                <c:url var="logoutUrl" value="/logout" />
                <form action="${logoutUrl}" id="logout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
                <a class="nav-link movil" href="javascript:sortirCarpeta()"><span class="oi oi-power-standby" title="<fmt:message key="menu.salir"/>" alt="<fmt:message key="menu.salir"/>" aria-hidden="true"></span> <p class="mb-0 float-right botonCorto"><fmt:message key="menu.salir"/></p></a>
            </li>
        </sec:authorize>
    </ul>

</div>