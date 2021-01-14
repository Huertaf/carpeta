<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<div class="buitContainer">
    <p><img src="<c:url value="/img/carpeta.png"/>"  alt="Carpeta" title="Carpeta"/></p>
    <h3><spring:message code="buit.benvinguda" /></h3>
    
     <c:if test="${(pipella eq 'adminentitat')}">
       
     <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <h5><spring:message code="propietatGlobal.adminentitat.titol.plural" /></h5>
                    <p class="card-text"><spring:message code="propietatGlobal.adminentitat.descripcio" /></p>
                    <ul>
                        <li><a href="<c:url value="/adminentitat/propietatglobal/list"/>"><spring:message
            code="propietatGlobal.adminentitat.titol.plural" /></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
            <div class="card-body">
                <h5><spring:message code="menu.adminentitat.usuariEntitat.titol" /></h5>
                <p class="card-text"><spring:message code="menu.adminentitat.usuariEntitat.descripcio" /></p>
                <ul>
                    <li><a href="<c:url value="/adminentitat/usuarientitat/list"/>"><spring:message
            code="usuariEntitat.usuariEntitat.plural" /></a></li>
                </ul>
            </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
            <div class="card-body">
                <h5><spring:message code="menu.adminentitat.pluginEntitat.titol" /></h5>
                <p class="card-text"><spring:message code="menu.adminentitat.pluginEntitat.descripcio" /></p>
                <ul>
                    <li><a href="<c:url value="/adminentitat/pluginEntitat/list"/>"><spring:message
            code="pluginEntitat.pluginEntitat.plural" /></a></li>
                </ul>
            </div>
            </div>
        </div>
    </div>
    
    <div class="row">
        <div class="col">
            <div class="card">
            <div class="card-body">
                <h5><spring:message code="menu.adminentitat.enllasos.titol" /></h5>
                <p class="card-text"><spring:message code="menu.adminentitat.enllasos.descripcio" /></p>
                <ul>
                    <li><a href="<c:url value="/adminentitat/enllaz/list"/>"><spring:message
            code="enllaz.enllaz.plural" /></a></li>
                </ul>
            </div>
        </div>
        </div>
        <div class="col">
            <div class="card">
            <div class="card-body">
                <h5><spring:message code="avis.avis.plural" /></h5>
                <p class="card-text"><spring:message code="menu.adminentitat.avis.descripcio" /></p>
                <ul>
                    <li><a href="<c:url value="/adminentitat/avis/list"/>"><spring:message
            code="avis.avis.plural" /></a></li>
                </ul>
            </div>
        </div>
        </div>
        <div class="col"></div>
    </div>
    
   
   </c:if> 
   
   <c:if test="${(pipella eq 'superadmin')}">
    
   <div class="row"> 
 
        <div class="col">
            <div class="card">
	            <div class="card-body">
	                <h5><spring:message code="menu.superadmin.entitats.titol" /></h5>
	                <p class="card-text"><spring:message code="menu.superadmin.entitats.descripcio" /></p>
	                <ul>
	                    <li><a href="<c:url value="/superadmin/entitat/list"/>"><spring:message code="entitat.entitat.plural" /></a></li>
	                    <li><a href="<c:url value="/superadmin/usuari/list"/>"><spring:message code="usuari.usuari.plural" /></a></li>
	                    <li><a href="<c:url value="/superadmin/usuarientitat/list"/>"><spring:message code="usuariEntitat.usuariEntitat.plural" /></a></li>
	                </ul>
	            </div>
	        </div>
        </div>
 
        <div class="col">
            <div class="card">
	            <div class="card-body">
	                <h5><spring:message code="plugin.tipus.1.plural" /></h5>
	                <p class="card-text"><spring:message code="menu.superadmin.plugins.descripcio" /></p>
	                <ul>
	                    <li><a href="<c:url value="/superadmin/pluginfront/list"/>"><spring:message code="plugin.tipus.1.plural" /></a></li>
	                    <li><a href="<c:url value="/superadmin/logCarpeta/list"/>"><spring:message code="logCarpeta.logCarpeta.plural" /></a></li>
	                </ul>
	            </div>
	        </div>
        </div>
        
        
        <div class="col">
	        <div class="card">
	            <div class="card-body">
	                <h5><spring:message code="avis.avis.plural" /></h5>
	                <p class="card-text"><spring:message code="menu.superadmin.avis.descripcio" /></p>
	                <ul>
	                    <li><a href="<c:url value="/superadmin/avis/list"/>"><spring:message code="avis.avis.plural" /></a></li>
	                </ul>
	            </div>
	        </div>
        </div>
   </div>
   
   <div class="row">
        
        <div class="col">
            <div class="card">
	            <div class="card-body">
	                <h5><spring:message code="menu.superadmin.system.titol" /></h5>
	                <p class="card-text"><spring:message code="menu.superadmin.system.descripcio" /></p>
	                <ul>
	                    <li><a href="<c:url value="/superadmin/propietatglobal/list"/>"><spring:message code="propietatGlobal.propietatGlobal.plural" /></a></li>
	                    <li><a href="<c:url value="/superadmin/systemproperties"/>"><spring:message code="superadmin.systemproperties" /></a></li>
	                    <li><a href="<c:url value="/superadmin/fitxerorfes/list"/>"><spring:message code="fitxers.orfes" /></a></li>
                        <li><a href="<c:url value="/superadmin/idioma/list"/>"><spring:message code="idioma.idioma.plural" /></a></li>
	                </ul>
	            </div>
	        </div>
        </div>
        <div class="col">
            <div class="card">
	            <div class="card-body">
	                <h5><spring:message code="auditoria.auditoria.plural" /></h5>
	                <p class="card-text"><spring:message code="menu.superadmin.auditoria.descripcio" /></p>
	                <ul>
	                    <li><a href="<c:url value="/superadmin/auditoria/list"/>"><spring:message code="auditoria.auditoria.plural" /></a></li>
	                    <li><a href="<c:url value="/superadmin/acces/list"/>"><spring:message code="acces.acces.plural" /></a></li>
	                </ul>
	            </div>
	        </div>
        </div>
        <div class="col"></div>
    
    </c:if>    
    
</div>
