import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import axios from "axios";
import i18n from "i18next";
import LlistatDePlugins from './LlistatDePlugins';


class MenuRapid extends Component {

    constructor(){
        super();
        this.state = {
            plugins: [],
            menupseudoplugin: [],
			seccions : []
        }
        this.mostrarNovaSeccio = this.mostrarNovaSeccio.bind(this);
    }

    componentDidMount() {
        var url = window.location.href + `pluginfront/veureplugins`;
        axios.get(url).then(res => {
            const plugins = res.data;
            this.setState({ plugins });
        });

        var url4 = window.location.href + `webui/menupseudoplugin/0`;
		axios.get(url4).then(res => {
			this.setState({ menupseudoplugin: res.data })
		});

		// 0 == Nivell Arell        
        var url5 = window.location.href + `webui/seccions/0`;
		axios.get(url5).then(res => {
			this.setState({ seccions: res.data })
        });
    }

    componentWillReceiveProps(lng) {
        var url = window.location.href + `pluginfront/veureplugins`;
        axios.get(url).then(res => {
            const plugins = res.data;
            this.setState({ plugins });
        });

        var url4 = window.location.href + `webui/menupseudoplugin/0`;
		axios.get(url4).then(res => {
			this.setState({ menupseudoplugin: res.data })
		});

		// 0 == Nivell Arell        
        var url5 = window.location.href + `webui/seccions/0`;
		axios.get(url5).then(res => {
			this.setState({ seccions: res.data })
        });
    }

    componentDidUpdate() {
        var aut = sessionStorage.getItem('autenticat');
        if (aut === '1') {
            document.getElementById('menuRapid').classList.remove('d-none');
        }else{
            document.getElementById('menuRapid').classList.add('d-none');
        }
    }

    mostrarNovaSeccio(seccioID) {

        console.log("RAPID Entra a mostrarNovaSeccio");
        //const { t } = this.props;  t={t}
        ReactDOM.render(<LlistatDePlugins seccioID={seccioID}  />, document.getElementById('contingut'));
        console.log("RAPID Surt mostrarNovaSeccio");

    }

    render() {

        const {t} = this.props;
        var autenticat = this.props.autenticat;
        const plugins = this.state.plugins;
        var urlBase = window.location.href;

        var gestionsHtml;
        var gestionsReact;
        var enllasosPseusoPluginMenu;
        var seccionsS;

        if(autenticat === '0'){


        } else if(autenticat === '1'){

            gestionsHtml = plugins.filter(s => s.reactComponent === 'false').map((s, i) => (
                <li className="nav-item pr-4" key={i}>
                    <a className="navCarpeta" href={"javascript:newPluginHtml('contingut', '1', '" + s.pluginID + "');"} title={s.nom}>
                        <img src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""} alt="" title="" className="imc-icona" />
                        <span className="menuRapidView">{s.nom}</span>
                    </a>
                </li>
            ));

            gestionsReact = plugins.filter(s => s.reactComponent === 'true').map((s, i) => (
                    <li className="nav-item pr-4" key={i}>
                        <a className="navCarpeta" href={"javascript:newPluginReact('contingut', '1', '" + s.pluginID + "');"} title={s.nom}>
                            <img src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""} alt="" title="" className="imc-icona" />
                            <span className="menuRapidView">{s.nom}</span>
                        </a>
                    </li>
            ));

            if(!this.state.menupseudoplugin.length){
                enllasosPseusoPluginMenu = "";
            } else {
                enllasosPseusoPluginMenu = this.state.menupseudoplugin.map((s, i) => (
                    <li className="nav-item pr-4" key={i}>
                        <a className="navCarpeta" href={s.url} target="_blank" title={s.label}>
                            <img src={s.urllogo} alt="" title={s.label} className="imc-icona" />
                            <span className="menuRapidView">{s.label}</span>
                        </a>
                    </li>
                ))
            }

            const seccions = this.state.seccions;
            
            seccionsS = seccions.map((s, i) => (
                <li className="nav-item pr-4" onClick={() => this.mostrarNovaSeccio(s.seccioID) } key={i}>
                    <a className="navCarpeta"    >
                        <img src={s.iconaID} title={s.nom} alt={s.descripcio} className="imc-icona" />
                        <span className="menuRapidView">{s.nom}</span>
                    </a>
                </li>
            ));
        }

        return (
            <div>
                <nav className="navbar navbar-expand-sm bg-white p-0 fixo" id="menuRapid">
                    <ul className="navbar-nav p-3 mRapidGlobal" id="llistaMenuRapid">
                        {seccionsS}
                        {gestionsHtml}
                        {gestionsReact}
                        {enllasosPseusoPluginMenu}
                    </ul>
                </nav>
            </div>
        );
    }
}

export default withTranslation()(MenuRapid);