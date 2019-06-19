<!-- Menú principal-->
<%@include file="/WEB-INF/views/includes.jsp"%>
<div class="collapse navbar-collapse" id="navbarsExampleDefault">

    <ul class="navbar-nav movil">
        <sec:authorize access="isAuthenticated()">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle movil" href="#" id="menu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="${pageContext.request.contextPath}/static/img/folder-open-regular.svg" width="15" class="icono" alt="<fmt:message key="menu.gestiones"/>"/> <fmt:message key="menu.gestiones"/></a>
                <div class="dropdown-menu marg0" aria-labelledby="menu">
                    <a class="dropdown-item movil" href="<c:url value="/tramites"/>"><img src="${pageContext.request.contextPath}/static/img/file-alt-regular.svg" width="15" class="icono" alt="<fmt:message key="menu.tramites.no.acabados"/>"/> <fmt:message key="menu.tramites.no.acabados"/></a>
                    <a class="dropdown-item movil" href="<c:url value="/registros"/>"><img src="${pageContext.request.contextPath}/static/img/file-regular.svg" width="15" class="icono" alt="<fmt:message key="menu.registros"/>"/> <fmt:message key="menu.registros"/></a>
                </div>
            </li>
        </sec:authorize>

        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle movil" href="#" id="idiomas" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="${pageContext.request.contextPath}/static/img/idioma.svg" width="15" class="icono" alt="<fmt:message key="menu.idioma"/>"/> <fmt:message key="menu.idioma"/></a>
            <div class="dropdown-menu marg0" aria-labelledby="idiomas">
                <c:if test="${pageContext.response.locale.language == 'ca'}">
                    <a class="dropdown-item movil" href="<c:url value="/?idioma=es"/>"><fmt:message key="idioma.castellano"/></a>
                    <a class="dropdown-item movil disabled"><fmt:message key="idioma.catala"/></a>
                </c:if>
                <c:if test="${pageContext.response.locale.language == 'es'}">
                    <a class="dropdown-item movil" href="<c:url value="/?idioma=ca"/>"><fmt:message key="idioma.catala"/></a>
                    <a class="dropdown-item movil disabled"><fmt:message key="idioma.castellano"/></a>
                </c:if>

            </div>
        </li>
        <sec:authorize access="isAuthenticated()">
            <li class="nav-item">
                <c:url var="logoutUrl" value="/logout" />
                <form action="${logoutUrl}" id="logout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>

                <a class="nav-link movil" href="javascript:sortirCarpeta()"><img src="${pageContext.request.contextPath}/static/img/power-off-solid.svg" width="15" class="icono" alt=""/> <fmt:message key="menu.salir"/></a>
            </li>
        </sec:authorize>
    </ul>

</div>