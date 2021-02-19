import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import { HashRouter, Switch, Route, Link } from "react-router-dom";
import axios from "axios";

class EnllasosPeuCentral extends Component {

    constructor(){
        super();
        this.state = {
            enllasosPeuCentral: []
        }
    }

    componentWillMount() {  

        var baseURL = sessionStorage.getItem('contextPath');
        var url =  baseURL + "/webui/centralfooterlinks";
        axios.get(url).then(res => {
            this.setState({ enllasosPeuCentral: res.data })
        });
    }

    componentWillReceiveProps(lng) {
        var baseURL = sessionStorage.getItem('contextPath');
        var url = baseURL + "/webui/centralfooterlinks";
        axios.get(url).then(res => {
            this.setState({ enllasosPeuCentral: res.data })
        });
    }

    render() {
        var autenticat = this.props.autenticat;        
        const {t} = this.props;

        var mapa;
		if (autenticat === '1'){
			mapa = <li><a href="javascript:newMapaWeb('contingut', '1');">{ t('peuMapa') }</a></li>;
		} 
		if(autenticat === '0'){
			mapa = <li><a href="javascript:newMapaWeb('contingut', '0');">{ t('peuMapa') }</a></li>;
		}

        let enllasosPeuCentral;
        
        if(!this.state.enllasosPeuCentral.length){
            enllasosPeuCentral = "";
        } else{
            enllasosPeuCentral = this.state.enllasosPeuCentral.map((s, i) => (
                <li>
                    <a href={s.url}>
                        <span>{s.label}</span>
                        <img src={s.urllogo} title="" alt="" />
                        
                    </a>
                </li>
            ))
        }

        return (
            <ul>
                <li><Link to={"/mapaweb/"} > { t('peuMapa') }</Link></li>
                {enllasosPeuCentral}
            </ul>
        );
    }
}

export default withTranslation()(EnllasosPeuCentral);