<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="${pageContext.response.locale.language}" style="" class=" js flexbox flexboxlegacy hashchange backgroundsize boxshadow textshadow opacity cssanimations cssgradients csstransforms csstransitions fontface generatedcontent localstorage svg" lang="${pageContext.response.locale.language}">
<head>
    <title>Carpeta Ciutadana</title>
    <link rel="shortcut icon" type="image/x-ico" href="<c:url value="/src/assets/images/icon.png"/>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="${pageContext.response.locale.language}"/>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/react/15.4.2/react.js" charset="utf-8"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/react/15.4.2/react-dom.js" charset="utf-8"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/babel-standalone/6.21.1/babel.min.js" charset="utf-8"></script>

    <!-- Scripts -->
    <!-- google analytics -->
    <script type="text/javascript" async="" src="<c:url value="/src/assets/js/ga.js"/>"></script>
    <script src="<c:url value="/src/assets/js/jquery-3.5.0.js"/>"></script>
    <script src="<c:url value="/src/assets/js/bootstrap.min.js"/>"></script>
    <!-- Datetimpicker -->
    <script src="<c:url value="/src/assets/js/moment-with-locales.min.js"/>"></script>
    <script src="<c:url value="/src/assets/js/bootstrap-datetimepicker.js"/>"></script>
    <!-- Necessari per les cookies -->
    <script type="text/javascript" src="<c:url value="/src/assets/js/jquery.slimscroll.min.js"/>"></script>
    <script src="<c:url value="/src/assets/js/modernizr.js"/>"></script>
    <!-- Del Goib -->
    <script src="<c:url value="/src/assets/js/globales.js"/>" type="text/javascript"></script>
    <!-- Bootstrap compatibilitat amb IE -->
    <script src="<c:url value="/src/assets/js/respond.js"/>" type="text/javascript"></script>

    <script src="<c:url value="/src/assets/js/jquery.dataTables.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/src/assets/js/datetime-moment.js"/>" type="text/javascript"></script>

    <!-- Scripts per Components React -->
    <script src="<c:url value="/src/utils/carpeta_react.js"/>" type="text/javascript"></script>

    <!-- ESTILS -->
    <link href="<c:url value="/src/assets/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/src/assets/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/src/assets/css/goib.css"/>" rel="stylesheet">
    <link href="<c:url value="/src/assets/css/carpeta.css"/>" rel="stylesheet">
    <link href="<c:url value="/src/assets/css/open-iconic-bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/src/assets/css/jquery.dataTables.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/src/assets/css/datatables.min.css"/>" rel="stylesheet">

</head>

<body>

    <!-- Menú vertical -->
    <div>
        <div class="imc-logo">
            <div>
                <a href="javascript:window.location.reload(true)" class="imc--goib" title="<fmt:message key="entitats.carpeta"/>">
                    <img src="<c:url value="/src/assets/images/icon.png"/>" title="" alt="" class="logo-govern" style="max-width: 70%;"/>
                    <span><fmt:message key="entitats.carpeta"/></span>
                </a>
            </div>
        </div>
    </div>

    <!-- Contingut dret -->
    <div class="contenedor">

        <!-- Capçalera -->
        <div>
            <header class="imc-titol">
                <nav class="imc--contingut">
                    <h1><span class="text-uppercase"><fmt:message key="entitats.carpeta"/></span></h1>
                    <ul>
                        <li><button type="button" class="imc-bt-menu" id="imc-bt-menu" title="<fmt:message key="entitats.menu"/>"></button></li>
                    </ul>
                </nav>
            </header>
        </div>

        <!-- Menú desplegable -->
        <div>
            <div class="imc-marc" id="imc-marc" tabindex="-1" aria-hidden="true">
                <div class="imc--fons"></div>
                <div class="imc-marc-menu" id="imc-marc-menu" aria-hidden="true">
                    <div class="imc-cercador" id="imc-cercador"></div>
                    <li>
                        <li class="imc-marc-ico imc--idioma" style="padding-left: 1em;">
                            <c:forEach var="idioma" items="${idiomes}" varStatus="loop">
                                <c:choose>
                                    <c:when test="${idioma.idiomaID=='ca'}">
                                        <fmt:message key="entitats.catala" var="nomCatala"/>
                                        <c:set var="nomIdioma" value="${nomCatala}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${idioma.idiomaID=='es'}">
                                                <fmt:message key="entitats.castellano" var="nomCastella"/>
                                                <c:set var="nomIdioma" value="${nomCastella}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:if test="${idioma.idiomaID=='en'}">
                                                    <fmt:message key="entitats.english" var="nomAngles"/>
                                                    <c:set var="nomIdioma" value="${nomAngles}"/>
                                                </c:if>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${lang==idioma.idiomaID}">
                                        <strong class="lletraIdioma">${nomIdioma}</strong>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="boton-menu lletraIdioma" onclick="location.href='<c:url value="/entitat?lang=${idioma.idiomaID}"/>'">
                                            ${nomIdioma}
                                        </button>
                                    </c:otherwise>
                                </c:choose>

                                <c:if test="${!loop.last}">
                                    &nbsp;\&nbsp;
                                </c:if>

                            </c:forEach>
                        </li>

                    </ul>
                </div>
            </div>
        </div>

        <!-- Zona Contingut -->
        <div class="imc-continguts">
            <div class="pt-2">
                <div class="container-contenido homePage">
                    <div class="row mr-0 ml-0">
                        <div class="infoNoMenu">
                            <p class="lh15"><fmt:message key="entitats.llista"/></p>
                            <div class="col-md-12 border-0 float-left pt-3 pl-0">
                                <div class="card-body imc--llista--capses">
                                    <div class="row mb-0">

                                        <c:forEach items="${entitats}" var="entitat" varStatus="index">
                                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0">
                                                <button class="card col-md-12 align-items-lg-center capsaPlugin pt-3" onclick="location.href='<c:url value="/e/${entitat.codi}"/>'">
                                                    <span class="card-title titol pl-1 h3">
                                                        <img src="<c:url value="/webui/entityicon/${entitat.entitatID}"/>" alt="" title="" class="imc-icona">
                                                    </span>
                                                    <span class="titolPlugin titol h3 titolCentrat">${entitat.nom.getTraduccio(lang).valor}</span>
                                                    <span class="card-text mb-3 mt-3 alignCenter"><i>${(empty entitat.descripcio)?'':entitat.descripcio.getTraduccio(lang).valor}</i></span>
                                                </button>
                                            </div>
                                        </c:forEach>

                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Fi Zona Contingut -->

        <!-- Peu -->
        <div class="capsaPeu">
            <footer class="imc-peu">
                <div class="imc--contingut">
                    <div class="imc-peu-govern">

                    </div>
                    <div class="imc-peu-opcions">
                        <div class="pb-3 col2peu">
                            <button type="button" id="dialogAjuda" data-toggle="modal" data-target="#ajudaModal" class="botoSuport"><fmt:message key="entitats.ajuda"/></button>
                        </div>
                    </div>
                    <div class="imc-peu-xarxes">

                    </div>
                </div>
                <div class="modal fade" id="ajudaModal" tabindex="-1" aria-labelledby="ajudaLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <p class="card-title titol h2"><fmt:message key="entitats.ajuda.titol"/></p>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">×</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <ul class="pl-3 ajuda">
                                    <li class="pb-2 liAjuda"><fmt:message key="entitats.ajuda.suportweb1"/>  <a href='http://www.caib.es/sites/suportinformatic/es/inicio-5586/?campa=yes'><fmt:message key="entitats.ajuda.suportweb2"/></a></li>
                                    <li class="pb-2 liAjuda"><fmt:message key="entitats.ajuda.telefon1"/>  <p class="text-verd"> <fmt:message key="entitats.ajuda.telefon2"/></p></li>
                                    <li class="pb-2 liAjuda"><fmt:message key="entitats.ajuda.correu1"/>  <a href="mailto:'suport@caib.es'"><fmt:message key="entitats.ajuda.correu2"/></a></li>
                                    <li class="pb-2 liAjuda"><fmt:message key="entitats.ajuda.faq1"/>  <a href="http://www.faq.com"><fmt:message key="entitats.ajuda.faq2"/></a></li>
                                    <li class="pb-2 liAjuda"><fmt:message key="entitats.ajuda.consulta1"/>  <p class="text-verd"> <fmt:message key="entitats.ajuda.consulta2"/></p></li>
                                    <li class="pb-2 liAjuda"><fmt:message key="entitats.ajuda.problemes1"/>  <p class="text-verd"> <fmt:message key="entitats.ajuda.problemes2"/></p></li>
                                </ul>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="botoSuport" data-dismiss="modal" aria-label="Close"><fmt:message key="entitats.ajuda.tanca"/></button>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>
        </div>


    </div>

<!-- js react -->
<script src = "<c:url value="/dist/reactjs_main.js"/>" type="text/javascript"></script>
<!-- menú lateral -->
<script src="<c:url value="/src/assets/js/menu-lateral.js"/>" type="text/javascript"></script>
<!-- acceptar cookies -->
<script src="<c:url value="/src/assets/js/aceptar_cookies.js"/>" type="text/javascript"></script>

</body>
</html>
