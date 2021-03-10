
import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import BarraMenu from './BarraMenu';
import MenuDesllisant from './MenuDesllisant';
import MenuLateral from './MenuLateral';
import MenuRapid from './MenuRapid';
import Peu from './Peu';
import Breadcrumb from './Breadcrumb';
import AvisosFront from './AvisosFront';
import Inici from './Inici';
import MapaWeb from './MapaWeb';
import PluginHtml from './PluginHtml';
import PluginReact from './PluginReact';
import Accessibilitat from './Accessibilitat';
import NivellAutenticacio from './NivellAutenticacio';
import * as Constants from './Constants';

// NO ESBORRAR LA SEGÜENT LINIA  !!!!!!
import i18n from './i18n';

import { HashRouter, Switch, Route, useLocation, useHistory } from "react-router-dom";
import { withRouter } from "react-router";



import LlistatDePlugins from './LlistatDePlugins';

/**
 * @author anadal Migracio A Routes i passar de index.jsp a Index.js
 */



//@withRouter
class Index extends Component {



    constructor(props) {
        super(props);
    }
  


    render() {


        var auth = sessionStorage.getItem('autenticat');


        console.log(" XYZ ZZZ   \n INDEX :: Auth Val: " + auth + "\n");


        var user = sessionStorage.getItem('usuariNomComplet');

        var infoUsuari;
        var estilContingut = (auth === '1')? { paddingTop: '3em'} : {paddingTop: '0em'};
        if (auth === '1' && user != null) {
            infoUsuari = <div className="imc-titol usuari" style={{minHeight: '70px'}}>
                <nav className="imc--contingut">
                    <h3>
                        <span className="estilUsuari">
                            {/* <c:if test="${user != null}"> */}
                            <span className="oi oi-person pr-2" aria-hidden="true"> </span>{user}
                            {/* <!-- Nivell d'autenticació --> */}
                            <span id="nivellAutenticacio" className="imc--autenticacio">
                                <NivellAutenticacio />
                            </span>
                            {/* </c:if> */}
                        </span>
                    </h3>
                </nav>
            </div>;
        } else {
            infoUsuari = '';
        }

    
        const currentlocation = this.props.location;
        //console.log("\n\n   XXXXXXXXXX   LOCATION: " + currentlocation.pathname);



        const styleContenidor = (auth === '1')? { marginTop: '5.5em'} : { marginTop: '2em'};

        return (
            <div>
                {/* -- Menú vertical */}
                <MenuLateral />

                {/* Contingut dret */}
                <div className="contenedor" id="contenedor" style={styleContenidor}>

                    {/* Capçalera */}
                    <BarraMenu />

                    {infoUsuari}

                    {/* Menú Ràpid */}
                    <MenuRapid  />

                    {/* Menú desplegable */}
                    <div className="imc-marc" id="imc-marc" tabIndex="-1" aria-hidden="true">
                        <div className="imc--fons" id="imc--fons"/>
                        <div className="imc-marc-menu" id="imc-marc-menu" aria-hidden="true">
                            <MenuDesllisant  />
                        </div>
                    </div>

                    {/* Zona Contingut */}
                    <div className="imc-continguts" style={estilContingut} id="continguts" >

                        {/* Molla de pa */}
                        <Breadcrumb  currentlocation={currentlocation} />

                        {/* Avisos Front */}
                        <AvisosFront  />

                        <div id="contingutok" className="pt-2">
                            <Switch>
                                <Route exact path="/" component={Inici} refresh="true" />

                                <Route exact path="/auth" component={Accessibilitat} refresh="true" />

                                <Route path="/accessibilitat" component={Accessibilitat}  />

                                <Route path="/mapaweb" component={MapaWeb} />

                                <Route
                                    path= {Constants.SECCIO_PATH + ":seccioContext" + Constants.PLUGINHTML_PATH + ":pluginContext"}
                                    render={(props) => {
                                        return <PluginHtml {...props} seccioContext={props.match.params.seccioContext} pluginContext={props.match.params.pluginContext} />
                                    }}
                                />

                                <Route
                                    path={Constants.SECCIO_PATH + ":seccioContext" + Constants.PLUGINREACT_PATH + ":pluginContext"}
                                    render={(props) => {
                                        return <PluginReact {...props} seccioContext={props.match.params.seccioContext} pluginContext={props.match.params.pluginContext}  />
                                    }}
                                />

                                <Route
                                    path= {Constants.SECCIO_PATH + ":seccioContext"}
                                    render={(props) => {
                                        return <LlistatDePlugins {...props} seccioContext={props.match.params.seccioContext}  />
                                    }}
                                />
                                <Route
                                    path={ Constants.PLUGINHTML_PATH + ":pluginContext" }
                                    render={(props) => {
                                        return <PluginHtml {...props} seccioContext={0}  />
                                    }}
                                />

                                <Route
                                    path={ Constants.PLUGINREACT_PATH + ":pluginId" }
                                    render={(props) => {
                                        return <PluginReact {...props} seccioContext={0} pluginContext={props.match.params.pluginContext}  />
                                    }}
                                />
                            </Switch>
                        </div>

                    </div>
                    {/* Fi Zona Contingut */}

                    {/* Peu */}
                    <Peu />


                </div>
            </div>
        );
    }
}

export default withTranslation()(withRouter(Index));

