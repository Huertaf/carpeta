import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';
import axios from "axios";

class IniciPublic extends Component {

    constructor(){
        super();
        this.state = {
            nomEntitat: []
        }
    }

    componentWillMount() {
        var url = window.location.href + "webui/nomEntitat";
        axios.get(url).then(res => {
            this.setState({ nomEntitat: res.data })
        });
    }

    componentWillReceiveProps(lng) {
        var url = window.location.href + "webui/nomEntitat";
        axios.get(url).then(res => {
            this.setState({ nomEntitat: res.data })
        });
    }


    render() {

        const { t } = this.props;

        let entitatNom = this.state.nomEntitat;

        return (
            <div className="container-contenido homePage">

                <div className="row mr-0 ml-0">
                    <div className="infoNoMenu">
                        <h2><p className="titol h2">{t('paginaIniciTitol')} {entitatNom}</p></h2>

                        <div className="col-md-5 border-0 float-left p-0">
                            <p className="lh15 font-weight-bolder"><span class="oi oi-person"></span>{t('paginaIniciQuefer')}</p>
                            <ul className="lh15 pl-5 pt-3">
                                <li><span class="oi oi-arrow-right"></span>{t('paginaIniciQuefer1')}</li>
                                <li><span class="oi oi-arrow-right"></span>{t('paginaIniciQuefer2')}</li>
                                <li><span class="oi oi-arrow-right"></span>{t('paginaIniciQuefer3')}</li>
                                <li><span class="oi oi-arrow-right"></span>{t('paginaIniciQuefer4')}</li>
                            </ul>

                            <p className="titol h5 margen-top-clave"><span class="oi oi-account-login"></span>{t('paginaIniciAcces')}</p>

                            <p className="lh15">{t('paginaIniciClave')}</p>
                            <div className="pt-3 imc--logoclave"></div>
                            <ul className="lh15 pl-5 pt-3 opcionesAcceso">
                                <li><span class="oi oi-arrow-right"></span>{t('paginaIniciClave1')}</li>
                                <li><span class="oi oi-arrow-right"></span>{t('paginaIniciClave2')}</li>
                                <li><span class="oi oi-arrow-right"></span>{t('paginaIniciClave3')}</li>
                            </ul>
                            <br className="clearBoth" />
                        </div>

                        <div className="col-md-5 border-0 columna2Inici">

                            <p className="margen-top-clave pb-3">
                                <a className="btn btn-primary carpeta-btn botoAccedirCarpeta" href="javascript: var loc = new URL(window.location.href);  window.location.href=('prelogin?urlbase=' + encodeURIComponent(loc.protocol + '//' + loc.host) )" role="button"><span
                                    className="oi oi-account-login" title=""
                                    aria-hidden="true"></span> {t('paginaIniciBotoAccedir')}</a>
                            </p>

                            <p className="titol h5">{t('tramitacioAnonimaTitol')}</p>
                            <p className="lh15 pb-3"><a href="#" id="tramitacioModalBtn" data-toggle="modal" data-target="#tramitacioModal"><span class="oi oi-external-link"></span>{t('tramitacioEnllaz')}</a></p>
                            
                            <div class="modal fade" id="tramitacioModal" tabindex="-1" aria-labelledby="tramitacioLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="tramitacioModalLabel">{t('iniciarTramitacioAnonimaTitol')}</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="alert alert-danger" role="alert">{t('errorTramitacioAnonima')}</div> 
                                        <p>{t('descTramitacioAnonima')}</p>
                                        <input type="text" id="clauAnonima"></input>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">{t('cancelarTramitacioAnonima')}</button>
                                        <button type="button" id="iniciarTramitacioBtn" class="btn btn-primary">{t('iniciarTramitacioAnonimaBtn')}</button>
                                    </div>
                                    </div>
                                </div>
                            </div>



                            <p className="titol h5">{t('paginaIniciProblemes')}</p>
                            <p className="lh15">{t('paginaIniciAjuda')}</p>
                            <ul className="lh15 pl-5 pt-3">
                                <li><span class="oi oi-arrow-right"></span> {t('paginaIniciAjuda1')} <a href="http://clave.gob.es/clave_Home/clave.html"
                                                                title={t('paginaIniciAjudaClaveText')}
                                                                alt={t('paginaIniciAjudaClaveText')} target="_blank"
                                                                rel="noopener noreferrer">{t('paginaIniciAjudaClave')}</a>
                                </li>
                                <li><span class="oi oi-arrow-right"></span> {t('paginaIniciAjuda2')}</li>
                                <li><span class="oi oi-arrow-right"></span> {t('paginaIniciAjuda3')} <a
                                    href="https://ssweb.seap.minhap.es/ayuda/consulta/Claveciudadanos"
                                    title={t('paginaIniciAjudaBustiaText')} alt={t('paginaIniciAjudaBustiaText')}
                                    target="_blank" rel="noopener noreferrer">{t('paginaIniciAjudaBustia')}</a></li>
                            </ul>

                        </div>

                    </div>
                </div>

            </div>
        );

    }
}

export default withTranslation()(IniciPublic);