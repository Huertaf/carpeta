import React, { Component } from "react";
import ReactDOM from "react-dom";
import './i18n';
import MenuLateral from './MenuLateral';
import BarraMenu from './BarraMenu';
import MenuDesllisant from './MenuDesllisant';
import Peu from './Peu';
import MollaPa from './MollaPa';
import Inici from './Inici';
import Accessibilitat from './Accessibilitat';
import TramitsPendents from './TramitsPendents';
import Registres from './Registres';
import DetallRegistre from './DetallRegistre';
import Notificacions from './Notificacions';
import MapaWeb from './MapaWeb';
import DadesPersonals from './DadesPersonals';
import Breadcrumb from './Breadcrumb';
import Plugin from './Plugin';
import * as breadcrumbPaths from './utils/breadcrumbPaths';
import * as breadcrumbPathsAut from './utils/breadcrumbPathsAut';


ReactDOM.render(
  <MenuLateral />,
  document.getElementById("menuLateral")
);

ReactDOM.render(
  <BarraMenu />,
  document.getElementById("barraMenu")
);

ReactDOM.render(
  <MenuDesllisant autenticat='0'/>,
  document.getElementById("menuDesllisant")
);

ReactDOM.render(
  <Peu />,
  document.getElementById("peu")
);

ReactDOM.render(
  <MollaPa />,
  document.getElementById("mollaPa")
);

ReactDOM.render(
  <Breadcrumb items={breadcrumbPaths.Inici} autenticat='0'/>,
  document.getElementById("mollaPa")
);

ReactDOM.render(
  <Inici autenticat='0'/>,
  document.getElementById("contingut")
);


newIniciReact  = function createReactCompInici(nomComponent, param) {
    ReactDOM.render(<Inici autenticat={param}/>, document.getElementById(nomComponent));
	ReactDOM.render(<Breadcrumb items={breadcrumbPaths.Inici} autenticat={param}/>, document.getElementById("mollaPa"));
};

newAccessibilitatReact  = function createReactCompAccessibilitat(nomComponent, param) {
    ReactDOM.render(<Accessibilitat />, document.getElementById(nomComponent));
	if (param === '0'){
	  ReactDOM.render(<Breadcrumb items={breadcrumbPaths.Accessibilitat} autenticat={param}/>, document.getElementById("mollaPa"));
	}
	if (param === '1'){
	  ReactDOM.render(<Breadcrumb items={breadcrumbPathsAut.Accessibilitat} autenticat={param}/>, document.getElementById("mollaPa"));
	}
};

newTramitsPendentsReact  = function createReactCompTramitsPendents(nomComponent, param) {
    ReactDOM.render(<TramitsPendents />, document.getElementById(nomComponent));
	ReactDOM.render(<Breadcrumb items={breadcrumbPathsAut.TramitsPendents} autenticat={param}/>, document.getElementById("mollaPa"));
};

newRegistresReact  = function createReactCompRegistres(nomComponent, param) {
    ReactDOM.render(<Registres />, document.getElementById(nomComponent));
	ReactDOM.render(<Breadcrumb items={breadcrumbPathsAut.Registres} autenticat={param}/>, document.getElementById("mollaPa"));
};

newDetallRegistreReact  = function createReactCompDetallRegistre(nomComponent, param) {
    ReactDOM.render(<DetallRegistre id={param} />, document.getElementById(nomComponent));
	ReactDOM.render(<Breadcrumb items={breadcrumbPathsAut.DetallRegistre} autenticat={param}/>, document.getElementById("mollaPa"));
};

newNotificacionsReact  = function createReactCompNotificacions(nomComponent, param) {
    ReactDOM.render(<Notificacions />, document.getElementById(nomComponent));
	ReactDOM.render(<Breadcrumb items={breadcrumbPathsAut.Notificacions} autenticat={param}/>, document.getElementById("mollaPa"));
};

newMapaWebReact  = function createReactCompMapaWeb(nomComponent, param) {
    ReactDOM.render(<MapaWeb />, document.getElementById(nomComponent));
	if (param === '0'){
	  ReactDOM.render(<Breadcrumb items={breadcrumbPaths.MapaWeb} autenticat={param}/>, document.getElementById("mollaPa"));
	}
	if (param === '1'){
	  ReactDOM.render(<Breadcrumb items={breadcrumbPathsAut.MapaWeb} autenticat={param}/>, document.getElementById("mollaPa"));
	}
};

newDadesPersonalsReact  = function createReactCompDadesPersonals(nomComponent, param) {
    ReactDOM.render(<DadesPersonals />, document.getElementById(nomComponent));
	ReactDOM.render(<Breadcrumb items={breadcrumbPathsAut.DadesPersonals} autenticat={param}/>, document.getElementById("mollaPa"));
};

newMenuDesllisantReact  = function createReactCompMenuDesllisant(nomComponent, param) {
    ReactDOM.render(<MenuDesllisant autenticat={param}/>, document.getElementById(nomComponent));
};

newPeuReact  = function createReactCompPeu(nomComponent, param) {
    ReactDOM.render(<Peu autenticat={param}/>, document.getElementById(nomComponent));
};

newPluginReact  = function createReactCompPlugins(nomComponent, param, pluginID) {
    ReactDOM.render(<Plugin autenticat={param} pluginID={pluginID}/>, document.getElementById(nomComponent));
	ReactDOM.render(<Breadcrumb items={breadcrumbPathsAut.Plugin} autenticat={param}/>, document.getElementById("mollaPa"));
};