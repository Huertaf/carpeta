import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import axios from "axios";
import i18n from "i18next";
import {  Link } from "react-router-dom";
import * as Constants from './Constants';

/**
 * 
 * @author anadal Migració a ROUTER
 */


class MenuRapid extends Component {

    constructor(){
        super();
        this.state = {
            items: [] // plugins, menupseudoplugin, seccions
        }
        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        console.log(" CANVIAT IDIOMA EN MenuRapid A ]" + lng+ "[");
        this.componentDidMount();
    }


    componentDidMount() {

        // 0 == Nivell Arell
        var baseURL = sessionStorage.getItem('contextPath');
        var autenticat = sessionStorage.getItem('autenticat');
		var url = (autenticat === '1') ? baseURL + `/webui/fullinfosortedauth/0` : baseURL + `/webui/fullinfosorted/0`;
		axios.get(url).then(res => {
			var fulldata = res.data;
			this.setState({ items: fulldata.items });
		});
    }


    render() {

        const { t } = this.props;
        var autenticat = sessionStorage.getItem('autenticat');
        const plugins = this.state.plugins;
        var urlBase = sessionStorage.getItem('contextPath');

        let allItems = []; 
        let mostrar = "";

        if(autenticat === '1'){
            this.state.items.forEach((s, i) => {
                switch(s.tipus) {
                    
                    case 0: // Plugin react
                    case 1: // Plugin html
                        allItems.push(
                            <li key={i} className="nav-item pr-4">
                                <Link className="navCarpeta" to={{pathname: Constants.PLUGINHTML_PATH + s.context, nomPagina: "plugin" }} >
                                    <img src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""} alt="" title="" className="imc-icona" />
                                    <span className="menuRapidView">{s.nom}</span>
                                    </Link>
                            </li>
                        );
                        break;

                    case 3: // Seccio
                        allItems.push(
                            <li key={i} className="nav-item pr-4" > 
                                <Link to={{pathname: Constants.SECCIO_PATH + s.context, nomPagina: "seccio" }}>
                                    <img src={s.iconaID} title={s.nom} alt={s.descripcio} className="imc-icona" />
                                    <span className="menuRapidView">{s.nom}</span>
                                </Link>
                            </li>
                        );
                        break;

                    case 4: // PseudoPlugin
                        allItems.push(
                            <li key={i} className="nav-item pr-4" key={i}>
                                <a className="navCarpeta" href={s.url} target="_blank" title={s.label}>
                                    <img src={s.urllogo} alt="" title={s.label} className="imc-icona" />
                                    <span className="menuRapidView">{s.label}</span>
                                </a>
                            </li>
                        );
                        break;
                }
            });

            mostrar = <div>
                        <nav className="navbar navbar-expand-sm bg-white p-0 fixo" id="menuRapid">
                            <ul className="navbar-nav mRapidGlobal" style={{ padding: '0.5rem!important'}} id="llistaMenuRapid">
                                {allItems}
                            </ul>
                        </nav>
                    </div>;
        }

        return (
            <div id = "menuRapid">
                {mostrar}
            </div>
        );
    }
}

export default withTranslation()(MenuRapid);