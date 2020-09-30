import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';


function MenuDesllisant ({ t , autenticat}) {

    var boto_ca;
    if (i18n.language === 'ca') {
      boto_ca = <strong>{ t('menuIdioma_ca') }</strong>;
    } else {
      boto_ca = <button onClick={() => i18n.changeLanguage('ca')} className="boton-menu">{ t('menuIdioma_ca') }</button>;
    }

    var boto_es;
    if (i18n.language === 'es') {
      boto_es = <strong>{ t('menuIdioma_es') }</strong>;
    } else {
      boto_es = <button onClick={() => i18n.changeLanguage('es')} className="boton-menu">{ t('menuIdioma_es') }</button>;
    }
	
	const plugins = JSON.parse(sessionStorage.getItem('plugins'));

	var accessibilitat;
	var plug;
	var sortir;
	if (autenticat === '1'){
		accessibilitat = <li><a href="javascript:newAccessibilitat('contingut', '1');" className="imc-marc-ico imc--accessibilitat" id="imc-marc-accessibilitat" title={ t('menuAccessibilitat') }><span>{ t('menuAccessibilitat') }</span></a></li>;
		sortir = <li><a href="sortir" className="imc-marc-ico imc--sortir" id="imc-marc-sortir" title={ t('menuSortir') }><span>{ t('menuSortir') }</span></a></li>;
		
		if (i18n.language === 'ca') {
			plug = plugins.map(s => (<li><a href={"javascript:newPlugin('contingut', '1', '" + s.pluginID + "');"} className="imc-marc-ico imc--tramits" id="imc-marc-tramits" title={s.nomCa}><span>{s.nomCa}</span></a></li>));
		}

		if (i18n.language === 'es') {
		  plug = plugins.map(s => (<li><a href={"javascript:newPlugin('contingut', '1', '" + s.pluginID + "');"} className="imc-marc-ico imc--tramits" id="imc-marc-tramits" title={s.nomEs}><span>{s.nomEs}</span></a></li>));
		}
		
	} 
	if(autenticat === '0'){
		accessibilitat = <li><a href="javascript:newAccessibilitat('contingut', '0');" className="imc-marc-ico imc--accessibilitat" id="imc-marc-accessibilitat" title={ t('menuAccessibilitat') }><span>{ t('menuAccessibilitat') }</span></a></li>;
		plug = '';
		sortir = '';
	}


    return (
      <div className="imc-marc" id="imc-marc" tabIndex="-1" aria-hidden="true">
        <div className="imc--fons"></div>

        <div className="imc-marc-menu" id="imc-marc-menu" aria-hidden="true">
          <div className="imc-cercador" id="imc-cercador">
          </div>
          <ul>
            <li>
              <a href="http://www.caib.es/govern/cercadorAv.do?lang=ca" className="imc-marc-ico imc--avanzada" title={ t('menuRecerca') }>
                <span>{t('menuRecerca') }</span>
              </a>
            </li>
            <li className="imc-marc-ico imc--idioma">
              {boto_ca} \ {boto_es}
            </li>
            {accessibilitat}
			{plug}
			{sortir}
          </ul>
        </div>
      </div>
    );
}

export default withTranslation()(MenuDesllisant);