<%@include file="/WEB-INF/views/includes.jsp"%>
<footer id="footer">
    <div class="row mr-auto ml-3 mr-3 pieResponsive">

        <div class="col-4 pt-2 elementPieResponsive">
            <strong class="text-uppercase font-weight-bold h6"><fmt:message key="pie.govern"/></strong><br>
            <strong class="h6"><fmt:message key="pie.versio"/> 0.1</strong>
        </div>

        <div class="col-4 text-center pt-2 text-decoration-none bg-transparent text-uppercase p-2 opcionesPie elementPieResponsive">
            <ul class="list-unstyled d-inline-flex justify-content-center mb-0">
<%--                <li><a href="" data-toggle="modal" data-target="#modalContacto"><fmt:message key="soporte.ayuda"/></a></li>--%>
                <li><a href="<c:url value="/mapaWeb"/>" class="text-dark linkPie"><fmt:message key="pie.mapa"/></a></li>
<%--                <li><a href="" class="text-dark linkPie" target="_blank"><fmt:message key="lopd.lopd"/></a></li>--%>
                <li><a href="https://www.caib.es/govern/external/informacio.do?path=/webcaib/govern_illes/avis_legal/legalgovern&lang=${pageContext.response.locale.language}&fitxa=3437402" class="text-dark linkPie" target="_blank"><fmt:message key="pie.legal"/></a></li>
            </ul>
        </div>

        <div class="col-4 text-right d-inline-flex justify-content-end elementPieResponsive">

            <p class="mr-3 pt-2 quitarTablet"><fmt:message key="pie.seguir"/></p>
            <ul class="list-unstyled d-inline-flex">

                    <li>
                        <a class="iconoRedes botonRed logo-youtube" href="https://www.youtube.com/user/CanalIllesBalears" title="YouTube">
                            <svg viewBox="0 0 90 90" xml:space="preserve">
                                <title>YouTube</title>
                                            <g>
                                                <path d="M70.939,65.832H66l0.023-2.869c0-1.275,1.047-2.318,2.326-2.318h0.315c1.282,0,2.332,1.043,2.332,2.318
                                                    L70.939,65.832z M52.413,59.684c-1.253,0-2.278,0.842-2.278,1.873V75.51c0,1.029,1.025,1.869,2.278,1.869
                                                    c1.258,0,2.284-0.84,2.284-1.869V61.557C54.697,60.525,53.671,59.684,52.413,59.684z M82.5,51.879v26.544
                                                    C82.5,84.79,76.979,90,70.23,90H19.771C13.02,90,7.5,84.79,7.5,78.423V51.879c0-6.367,5.52-11.578,12.271-11.578H70.23
                                                    C76.979,40.301,82.5,45.512,82.5,51.879z M23.137,81.305l-0.004-27.961l6.255,0.002v-4.143l-16.674-0.025v4.073l5.205,0.015v28.039
                                                    H23.137z M41.887,57.509h-5.215v14.931c0,2.16,0.131,3.24-0.008,3.621c-0.424,1.158-2.33,2.388-3.073,0.125
                                                    c-0.126-0.396-0.015-1.591-0.017-3.643l-0.021-15.034h-5.186l0.016,14.798c0.004,2.268-0.051,3.959,0.018,4.729
                                                    c0.127,1.357,0.082,2.939,1.341,3.843c2.346,1.69,6.843-0.252,7.968-2.668l-0.01,3.083l4.188,0.005L41.887,57.509L41.887,57.509z
                                                     M58.57,74.607L58.559,62.18c-0.004-4.736-3.547-7.572-8.356-3.74l0.021-9.239l-5.209,0.008l-0.025,31.89l4.284-0.062l0.39-1.986
                                                    C55.137,84.072,58.578,80.631,58.57,74.607z M74.891,72.96l-3.91,0.021c-0.002,0.155-0.008,0.334-0.01,0.529v2.182
                                                    c0,1.168-0.965,2.119-2.137,2.119h-0.766c-1.174,0-2.139-0.951-2.139-2.119V75.45v-2.4v-3.097h8.954v-3.37
                                                    c0-2.463-0.063-4.925-0.267-6.333c-0.641-4.454-6.893-5.161-10.051-2.881c-0.991,0.712-1.748,1.665-2.188,2.945
                                                    c-0.444,1.281-0.665,3.031-0.665,5.254v7.41C61.714,85.296,76.676,83.555,74.891,72.96z M54.833,32.732
                                                    c0.269,0.654,0.687,1.184,1.254,1.584c0.56,0.394,1.276,0.592,2.134,0.592c0.752,0,1.418-0.203,1.998-0.622
                                                    c0.578-0.417,1.065-1.04,1.463-1.871l-0.099,2.046h5.813V9.74H62.82v19.24c0,1.042-0.858,1.895-1.907,1.895
                                                    c-1.043,0-1.904-0.853-1.904-1.895V9.74h-4.776v16.674c0,2.124,0.039,3.54,0.102,4.258C54.4,31.385,54.564,32.069,54.833,32.732z
                                                     M37.217,18.77c0-2.373,0.198-4.226,0.591-5.562c0.396-1.331,1.107-2.401,2.137-3.208c1.027-0.811,2.342-1.217,3.941-1.217
                                                    c1.345,0,2.497,0.264,3.459,0.781c0.967,0.52,1.713,1.195,2.23,2.028c0.527,0.836,0.885,1.695,1.076,2.574
                                                    c0.195,0.891,0.291,2.235,0.291,4.048v6.252c0,2.293-0.092,3.98-0.271,5.051c-0.177,1.074-0.557,2.07-1.146,3.004
                                                    c-0.58,0.924-1.329,1.615-2.237,2.056c-0.918,0.445-1.968,0.663-3.154,0.663c-1.325,0-2.441-0.183-3.361-0.565
                                                    c-0.923-0.38-1.636-0.953-2.144-1.714c-0.513-0.762-0.874-1.69-1.092-2.772c-0.219-1.081-0.323-2.707-0.323-4.874L37.217,18.77
                                                    L37.217,18.77z M41.77,28.59c0,1.4,1.042,2.543,2.311,2.543c1.27,0,2.308-1.143,2.308-2.543V15.43c0-1.398-1.038-2.541-2.308-2.541
                                                    c-1.269,0-2.311,1.143-2.311,2.541V28.59z M25.682,35.235h5.484l0.006-18.96l6.48-16.242h-5.998l-3.445,12.064L24.715,0h-5.936
                                                    l6.894,16.284L25.682,35.235z"></path>
                                            </g>
                                        </svg>
                            <span class="d-none">YouTube</span>
                        </a>

                        <a class="iconoRedes botonRed logo-instagram" href="https://www.instagram.com/goib" title="Instagram">
                            <svg viewBox="0 0 169.063 169.063" xml:space="preserve">
                                <title>Instagram</title>
                                            <g>
                                                <path d="M122.406,0H46.654C20.929,0,0,20.93,0,46.655v75.752c0,25.726,20.929,46.655,46.654,46.655h75.752
                                                    c25.727,0,46.656-20.93,46.656-46.655V46.655C169.063,20.93,148.133,0,122.406,0z M154.063,122.407
                                                    c0,17.455-14.201,31.655-31.656,31.655H46.654C29.2,154.063,15,139.862,15,122.407V46.655C15,29.201,29.2,15,46.654,15h75.752
                                                    c17.455,0,31.656,14.201,31.656,31.655V122.407z"></path>
                                                <path d="M84.531,40.97c-24.021,0-43.563,19.542-43.563,43.563c0,24.02,19.542,43.561,43.563,43.561s43.563-19.541,43.563-43.561
                                                    C128.094,60.512,108.552,40.97,84.531,40.97z M84.531,113.093c-15.749,0-28.563-12.812-28.563-28.561
                                                    c0-15.75,12.813-28.563,28.563-28.563s28.563,12.813,28.563,28.563C113.094,100.281,100.28,113.093,84.531,113.093z"></path>
                                                <path d="M129.921,28.251c-2.89,0-5.729,1.17-7.77,3.22c-2.051,2.04-3.23,4.88-3.23,7.78c0,2.891,1.18,5.73,3.23,7.78
                                                    c2.04,2.04,4.88,3.22,7.77,3.22c2.9,0,5.73-1.18,7.78-3.22c2.05-2.05,3.22-4.89,3.22-7.78c0-2.9-1.17-5.74-3.22-7.78
                                                    C135.661,29.421,132.821,28.251,129.921,28.251z"></path>
                                            </g>
                                        </svg>
                            <span class="d-none">Instagram</span>
                        </a>
                    </li>


                    <li class="pl-1">
                        <a class="iconoRedes botonRed logo-twitter" href="http://www.twitter.com/goib">
                            <svg viewBox="0 0 430.117 430.117" xml:space="preserve" >
                                <title>Twitter</title>
                                            <g>
                                                <path d="M381.384,198.639c24.157-1.993,40.543-12.975,46.849-27.876
                                                    c-8.714,5.353-35.764,11.189-50.703,5.631c-0.732-3.51-1.55-6.844-2.353-9.854c-11.383-41.798-50.357-75.472-91.194-71.404
                                                    c3.304-1.334,6.655-2.576,9.996-3.691c4.495-1.61,30.868-5.901,26.715-15.21c-3.5-8.188-35.722,6.188-41.789,8.067
                                                    c8.009-3.012,21.254-8.193,22.673-17.396c-12.27,1.683-24.315,7.484-33.622,15.919c3.36-3.617,5.909-8.025,6.45-12.769
                                                    C241.68,90.963,222.563,133.113,207.092,174c-12.148-11.773-22.915-21.044-32.574-26.192
                                                    c-27.097-14.531-59.496-29.692-110.355-48.572c-1.561,16.827,8.322,39.201,36.8,54.08c-6.17-0.826-17.453,1.017-26.477,3.178
                                                    c3.675,19.277,15.677,35.159,48.169,42.839c-14.849,0.98-22.523,4.359-29.478,11.642c6.763,13.407,23.266,29.186,52.953,25.947
                                                    c-33.006,14.226-13.458,40.571,13.399,36.642C113.713,320.887,41.479,317.409,0,277.828
                                                    c108.299,147.572,343.716,87.274,378.799-54.866c26.285,0.224,41.737-9.105,51.318-19.39
                                                    C414.973,206.142,393.023,203.486,381.384,198.639z"></path>
                                            </g>
                                        </svg>
                            <span class="d-none">Twitter</span>
                        </a>

                    <a href="https://www.facebook.com/GovernIllesBalears/" class="iconoRedes botonRed logo-facebook" title="Facebook" target="_blank">
                        <svg viewBox="0 0 430.113 430.114" xml:space="preserve">
                                        <g>
                                            <path d="M158.081,83.3c0,10.839,0,59.218,0,59.218h-43.385v72.412h43.385v215.183h89.122V214.936h59.805
                                                c0,0,5.601-34.721,8.316-72.685c-7.784,0-67.784,0-67.784,0s0-42.127,0-49.511c0-7.4,9.717-17.354,19.321-17.354
                                                c9.586,0,29.818,0,48.557,0c0-9.859,0-43.924,0-75.385c-25.016,0-53.476,0-66.021,0C155.878-0.004,158.081,72.48,158.081,83.3z"></path>
                                        </g>
                                    </svg>
                        <span class="d-none">Facebook</span>
                    </a>
                </li>

                </ul>

        </div>

    </div>

</footer>