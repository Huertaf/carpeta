import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';
import ExpirarSessio from "./ExpirarSessio";

class PluginReact extends Component {


     constructor(props) {
        super(props);

       this.state = {
            loaded: false            
        };
    }

   componentDidMount() {
     i18n.on('languageChanged', function(lng) { 
            console.log('PLUGIN REACT  Idioma canviat a ' + lng );
          
            if (window['changeLanguagePlugin']) {
                console.log('changeLanguagePlugin => EXISTEIX !!!!');
                window['changeLanguagePlugin'](lng);
            } else {
                console.log('changeLanguagePlugin => NOOOO EXISTEIX !!!!');
            }
              
         }
     );
   }


	render() {

		const {t} = this.props;
		const pluginID = this.props.pluginID;

        if (!this.state.loaded) {

            console.log("CARREGA INICIAL DEL PLUGIN !!!!!");

            var urlBase = window.location.href;
            var url = urlBase + "pluginfront/showreactplugin/" + pluginID + "/" + i18n.language;

            $(document).ready(function () {
                sessionStorage.setItem('idioma', i18n.language);
                $('#contentplugin').load(url);
            });
            
            this.setState({loaded: true });
        } else {
            console.log("PLUGIN JA CARREGAT !!!!!");
        }
            

		return (
            <div>
			<div id="contentplugin"></div>

			<div>
				<ExpirarSessio />
				<div id="substituir"></div>
			</div>
            </div>

		);

	}
}

export default withTranslation()(PluginReact);