<%-- 
    Document   : pagina para acesso as funcionalidades do cabure
    Description: Página de inicio de sessão do usuário
    Created on : 22/08/2014, 08:40:41
    Author     : macario.granja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="dao" class="com.itep.cabure.dao.UsuarioDAO" />
<jsp:useBean id="daoGrupo" class="com.itep.cabure.dao.GrupoDAO" />
<jsp:useBean id="daoPessoa" class="com.itep.cabure.dao.PessoaDAO" />
<jsp:useBean id="daoEmpresa" class="com.itep.cabure.dao.EmpresaDAO" />

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="description"
              content="Sistema de geolocalização realizado em parceria com CPRH, Governo de Pernambuco e ITEP que oferece um serviço diferenciado e gratuito para a população">
        <meta name="keywords"
              content="SIG, Sistema de Informações Geoambientais, Caburé, Pernambuco, Geoambiental, Geolocalização, CPRH, Governo de Pernambuco, ITEP, Instituto de Tecnologia de Pernambuco">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>SIG Caburé - Sistema de Informações Geoambientais de
            Pernambuco</title>

        <link rel="stylesheet" type="text/css"
              href="http://js.arcgis.com/3.8/js/dojo/dojo/resources/dojo.css">
        <link rel="stylesheet" type="text/css"
              href="http://js.arcgis.com/3.8/js/esri/css/esri.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <link rel="stylesheet" type="text/css"
              href="//js.arcgis.com/3.7/js/dojo/dijit/themes/claro/claro.css">
        <link rel="icon" href="img/favicon.ico">
        <script type="text/javascript">
            /*
             var url2 = document.referrer;
             if( url2 == "http://cabure.cprh.pe.gov.br/" )
             {
             window.location="http://sigcabure.cprh.pe.gov.br/";
             }
             else if( url2 == "http://www.cabure.cprh.pe.gov.br/" )
             {
             window.location="http://sigcabure.cprh.pe.gov.br/";
             }else if( url2 == "" ){
             window.location="http://cabure.cprh.pe.gov.br/";
             }
             */
        </script>


    </head>
    <body class="claro" id="bootstrap"
          style="background-image: url('images/top-back-texture.fw.png'); width: 100%; height: 100%;">
        <div class="header" id="header">
            <div class="headerLogo">
                <br /> <a href="http://sigcabure.cprh.pe.gov.br/"> <img
                        src="images/logo-sig-cabure.fw.png" height="83px"
                        width="197px" />
                </a>
            </div>
            <%-- Div butoes Publico --%>

            <c:forEach var="itemUser" items="${dao.lista}">
                <c:if test="${sessionScope.SESSION_IDENTIFICADOR_LOGADO == itemUser.usuario}">
                    <c:forEach var="itemGrupo" items="${daoGrupo.lista}">
                        <c:if test="${itemUser.grupo.grupo == itemGrupo.grupo}">
                            
                            <c:if test="${itemGrupo.nomeGrupo == 'Colaborador'}">
                                <div id="buttons">
                                    <!-- Dijit Monte seu Mapa -->
                                    <div style="position: absolute; left: 338px; top: 50px; z-index: 0;">
                                        <div id="MonteMapaDijit"></div>
                                    </div>

                                    <div id="div1" style="position: absolute; left: 400px; top: 50px; z-index: 0;">
                                        <img src="images/line-menu-max.fw.png" />
                                    </div>

                                    <div id="monteMapaMinimize" style="position: absolute; left: 19%; top: 110px; z-index: 0; display: none; cursor: pointer;">
                                        <img src="images/ico-menu-selected.png" />
                                    </div>

                                    <!-- Ferramentas -->
                                    <div style="position: absolute; left: 407px; top: 50px; z-index: 0;">
                                        <div id="FerramentasDijit"></div>
                                    </div>

                                    <div id="div2" style="position: absolute; left: 469px; top: 50px; z-index: 0;">
                                        <img src="images/line-menu-max.fw.png" />
                                    </div>

                                    <!-- Localizar por Coordenadas -->
                                    <div style="position: absolute; left: 482px; top: 50px; z-index: 0;">
                                        <div id="LocalizadorBtnDijit"></div>
                                    </div>

                                    <div id="div3" style="position: absolute; left: 544px; top: 50px; z-index: 0;">
                                        <img src="images/line-menu-max.fw.png" />
                                    </div>

                                    <!-- Baixar Arquivos -->
                                    <div style="position: absolute; left: 554px; top: 50px; z-index: 0;">
                                        <div id="BaixarArquivoDijit"></div>
                                    </div>

                                    <div id="div4" style="position: absolute; left: 616px; top: 50px; z-index: 0;">
                                        <img src="images/line-menu-max.fw.png" />
                                    </div>

                                    <!-- Clip and Ship  -->
                                    <div style="position: absolute; left: 626px; top: 50px; z-index: 0; display: none;">
                                        <div id="ClipShipBtnDijit"></div>
                                    </div>

                                    <div id="div5" style="position: absolute; left: 688px; top: 50px; z-index: 0;">
                                        <img src="images/line-menu-max.fw.png" />
                                    </div>

                                    <div id="relatorioMinimize" style="position: absolute; left: 33.5%; top: 102px; z-index: 0; z-index: 1; width: 3%; display: none; cursor: pointer;">
                                        <img src="images/ico-menu-selected.png" />
                                    </div>

                                    <!-- Dijit Impressora -->
                                    <div
                                        style="position: absolute; left: 624px; top: 50px; z-index: 0;">
                                        <div id="printButtonDijit"></div>
                                    </div>

                                    <div id="div14" style="position: absolute; left: 760px; top: 50px; z-index: 0;">
                                        <img src="images/line-menu-max.fw.png" />
                                    </div>

                                    <div id="imprimirMinimize"
                                         style="position: absolute; left: 37.10%; top: 102px; z-index: 0; z-index: 1; width: 3%; display: none; cursor: pointer;">
                                        <img src="images/ico-menu-selected.png" />
                                    </div>

                                    <!-- Dijit Identify -->
                                    <div
                                        style="position: absolute; left: 696px; top: 50px; z-index: 0;">
                                        <div id='IdentifyDijit'></div>
                                    </div>

                                    <div id="identifyAtivo"
                                         style="position: absolute; left: 770px; top: 50px; z-index: 0; display: none;">
                                        <img src="images/ico-menu-identify.fw.ativo.png" />
                                    </div>

                                    <div id="identifyMinimize"
                                         style="position: relative; left: 40.8%; top: 102px; z-index: 0; z-index: 1; display: none; cursor: pointer; width: 3%;">
                                        <img src="images/ico-menu-selected.png" />
                                    </div>

                                    <div id="div13"
                                         style="position: absolute; left: 832px; top: 50px; z-index: 0;">
                                        <img src="images/line-menu-max.fw.png" />
                                    </div>

                                    <!-- Dijit Unidades de Conservação -->
                                    <div
                                        style="position: absolute; left: 766px; top: 50px; z-index: 0;">
                                        <div id='UConservDijit'></div>
                                    </div>

                                    <div id="uconservMinimize"
                                         style="position: absolute; left: 44.8%; top: 102px; z-index: 0; z-index: 1; width: 3%; display: none; cursor: pointer;">

                                        <img src="images/ico-menu-selected.png" />
                                    </div>

                                    <!-- Dijit de Busca -->
                                    <div
                                        style="position: absolute; right: 350px; top: 10px; z-index: 40;">
                                        <div id='findTaskDijit'></div>
                                    </div>

                                    <div id="divhome"
                                         style="position: absolute; right: 310px; top: 13px; z-index: 0;">
                                        <img src="images/ico-menu-home.fw.png" />
                                    </div>

                                    <div id="div7"
                                         style="position: absolute; right: 300px; top: 10px; z-index: 0;">
                                        <img src="images/line-menu-min.fw.png" />
                                    </div>

                                    <div id="div8"
                                         style="position: absolute; right: 250px; top: 13px; z-index: 0;">
                                        <a id="contato"
                                           style="font-size: 12px; color: #939393; border-color: #BBBBBB;"
                                           target="#" href="http://cabure.cprh.pe.gov.br/contato.php">Contato
                                        </a>
                                    </div>

                                    <div id="div9"
                                         style="position: absolute; right: 240px; top: 10px; z-index: 0;">
                                        <img src="images/line-menu-min.fw.png" />
                                    </div>

                                    <div id="div10"
                                         style="position: absolute; right: 200px; top: 13px; z-index: 0;">
                                        <a id="editarperfil"
                                           style="font-size: 12px; color: #939393; border-color: #BBBBBB;"
                                           href="#" onclick='dosomething()'>Ajuda </a>
                                    </div>

                                    <div id="div15"
                                         style="position: absolute; right: 190px; top: 10px; z-index: 0;">
                                        <img src="images/line-menu-min.fw.png" />
                                    </div>
                                    <div id="div16"
                                         style="position: absolute; right: 100px; top: 13px; z-index: 0;">
                                        <a id="editarperfil"
                                           style="font-size: 12px; color: #939393; border-color: #BBBBBB;"
                                           href="in/perfil.jsp" onclick='dosomething()'> 
                                            <c:forEach var="itemPessoa" items="${daoPessoa.lista}">
                                                <c:if test="${itemUser.usuario == itemPessoa.cpf}">
                                                    <c:out value="${itemPessoa.nome}" default="Indisponível" />
                                                </c:if>
                                            </c:forEach>
                                        </a>
                                    </div>

                                    <div id="div15"
                                         style="position: absolute; right: 85px; top: 10px; z-index: 0;">
                                        <img src="images/line-menu-min.fw.png" />
                                    </div>

                                    <div id="div16"
                                         style="position: absolute; right: 50px; top: 13px; z-index: 0;">
                                        <a id="editarperfil"
                                           style="font-size: 12px; color: #939393; border-color: #BBBBBB;"
                                           href="logout.jsp" onclick='dosomething()'> Sair </a>
                                    </div>

                                    <div id="div11"
                                         style="position: absolute; right: 180px; top: 50px; z-index: 0;">
                                        <br /> <a id="metadados"
                                                  style="font-size: 13px; color: #7E941B;" target="#"
                                                  href="http://200.17.134.119:8088/geoportal/catalog/main/home.page">Metadados
                                        </a>
                                    </div>

                                    <div id="div12"
                                         style="position: absolute; right: 160px; top: 60px; z-index: 0;">
                                        <img src="images/line-menu-med.fw.png" />
                                    </div>

                                    <div id="div6"
                                         style="position: absolute; right: 40px; top: 50px; z-index: 0;">
                                        <br /> <a
                                            href="http://200.238.107.205:8080/multiwork/controller?action=atendimentoonline&origem=atendimentoOnline&INCODIGOORGAO=72&command=INSERT&KeepThis=true&TB_iframe=true&height=500&width=800&modal=true"
                                            target="#"> <img
                                                src="images/ico-ouvidoria-ambiental.fw.png" />
                                        </a>
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${itemGrupo.nomeGrupo == 'Empreendedor'}">
                                <div id="buttons">
                                    <!-- Dijit Monte seu Mapa -->
                                    <div
                                        style="position: absolute; left: 338px; top: 50px; z-index: 0;">
                                        <div id="MonteMapaDijit"></div>
                                    </div>

                                    <div id="div1"
                                         style="position: absolute; left: 400px; top: 50px; z-index: 0;">
                                        <img src="images/line-menu-max.fw.png" />
                                    </div>

                                    <div id="monteMapaMinimize"
                                         style="position: absolute; left: 19%; top: 110px; z-index: 0; display: none; cursor: pointer;">
                                        <img src="images/ico-menu-selected.png" />
                                    </div>

                                    <!-- Ferramentas -->
                                    <div
                                        style="position: absolute; left: 407px; top: 50px; z-index: 0;">
                                        <div id="FerramentasDijit"></div>
                                    </div>

                                    <div id="div2"
                                         style="position: absolute; left: 469px; top: 50px; z-index: 0;">
                                        <img src="images/line-menu-max.fw.png" />
                                    </div>

                                    <!-- Localizar por Coordenadas -->
                                    <div
                                        style="position: absolute; left: 482px; top: 50px; z-index: 0;">
                                        <div id="LocalizadorBtnDijit"></div>
                                    </div>

                                    <div id="div3"
                                         style="position: absolute; left: 544px; top: 50px; z-index: 0;">
                                        <img src="images/line-menu-max.fw.png" />
                                    </div>

                                    <!-- Baixar Arquivos -->
                                    <div
                                        style="position: absolute; left: 554px; top: 50px; z-index: 0;">
                                        <div id="BaixarArquivoDijit"></div>
                                    </div>

                                    <div id="div4"
                                         style="position: absolute; left: 616px; top: 50px; z-index: 0;">
                                        <img src="images/line-menu-max.fw.png" />
                                    </div>
                                    <!-- Clip and Ship  -->
                                    <div
                                        style="position: absolute; left: 626px; top: 50px; z-index: 0;">
                                        <div id="ClipShipBtnDijit"></div>
                                    </div>

                                    <div id="div5"
                                         style="position: absolute; left: 688px; top: 50px; z-index: 0;">
                                        <img src="images/line-menu-max.fw.png" />
                                    </div>

                                    <!-- Dijit Impressora -->
                                    <div
                                        style="position: absolute; left: 698px; top: 50px; z-index: 0;">
                                        <div id="printButtonDijit"></div>
                                    </div>

                                    <div id="div14"
                                         style="position: absolute; left: 760px; top: 50px; z-index: 0;">
                                        <img src="images/line-menu-max.fw.png" />
                                    </div>

                                    <!-- Dijit Identify -->
                                    <div
                                        style="position: absolute; left: 770px; top: 50px; z-index: 0;">
                                        <div id='IdentifyDijit'></div>
                                    </div>

                                    <div id="identifyAtivo"
                                         style="position: absolute; left: 770px; top: 50px; z-index: 0; display: none;">
                                        <img src="images/ico-menu-identify.fw.ativo.png" />
                                    </div>

                                    <div id="div13"
                                         style="position: absolute; left: 832px; top: 50px; z-index: 0;">
                                        <img src="images/line-menu-max.fw.png" />
                                    </div>

                                    <!-- Dijit Identify Unidades de Conservação -->
                                    <div
                                        style="position: absolute; left: 842px; top: 50px; z-index: 0;">
                                        <div id='UConservDijit'></div>
                                    </div>

                                    <!-- Dijit de Busca -->
                                    <div
                                        style="position: absolute; right: 350px; top: 10px; z-index: 40;">
                                        <div id='findTaskDijit'></div>
                                    </div>

                                    <div id="divhome"
                                         style="position: absolute; right: 310px; top: 13px; z-index: 0;">
                                        <img src="images/ico-menu-home.fw.png" />
                                    </div>

                                    <div id="div7"
                                         style="position: absolute; right: 300px; top: 10px; z-index: 0;">
                                        <img src="images/line-menu-min.fw.png" />
                                    </div>

                                    <div id="div8"
                                         style="position: absolute; right: 250px; top: 13px; z-index: 0;">
                                        <a id="contato"
                                           style="font-size: 12px; color: #939393; border-color: #BBBBBB;"
                                           target="#" href="http://cabure.cprh.pe.gov.br/contato.php">Contato
                                        </a>
                                    </div>

                                    <div id="div9"
                                         style="position: absolute; right: 240px; top: 10px; z-index: 0;">
                                        <img src="images/line-menu-min.fw.png" />
                                    </div>

                                    <div id="div10"
                                         style="position: absolute; right: 200px; top: 13px; z-index: 0;">
                                        <a id="editarperfil"
                                           style="font-size: 12px; color: #939393; border-color: #BBBBBB;"
                                           href="#" onclick='dosomething()'>Ajuda </a>
                                    </div>

                                    <div id="div15"
                                         style="position: absolute; right: 190px; top: 10px; z-index: 0;">
                                        <img src="images/line-menu-min.fw.png" />
                                    </div>
                                    <div id="div16"
                                         style="position: absolute; right: 100px; top: 13px; z-index: 0;">
                                        <a id="editarperfil"
                                           style="font-size: 12px; color: #939393; border-color: #BBBBBB;"
                                           href="in/perfil.jsp" onclick='dosomething()'> 
                                              <c:forEach var="itemEmpresa" items="${daoEmpresa.lista}">
                                                <c:if test="${itemUser.usuario == itemEmpresa.cnpj}">
                                                    <c:out value="${itemEmpresa.razaoSocial}" default="Indisponível" />
                                                </c:if>
                                            </c:forEach> 
                                        </a>
                                    </div>

                                    <div id="div15"
                                         style="position: absolute; right: 85px; top: 10px; z-index: 0;">
                                        <img src="images/line-menu-min.fw.png" />
                                    </div>

                                    <div id="div16"
                                         style="position: absolute; right: 50px; top: 13px; z-index: 0;">
                                        <a id="editarperfil"
                                           style="font-size: 12px; color: #939393; border-color: #BBBBBB;"
                                           href="logout.jsp" onclick='dosomething()'>Sair </a>
                                    </div>

                                    <div id="div11"
                                         style="position: absolute; right: 180px; top: 50px; z-index: 0;">
                                        <br /> <a id="metadados"
                                                  style="font-size: 13px; color: #7E941B;" target="#"
                                                  href="http://200.17.134.119:8088/geoportal/catalog/main/home.page">Metadados
                                        </a>
                                    </div>

                                    <div id="div12"
                                         style="position: absolute; right: 160px; top: 60px; z-index: 0;">
                                        <img src="images/line-menu-med.fw.png" />
                                    </div>

                                    <div id="div6"
                                         style="position: absolute; right: 40px; top: 50px; z-index: 0;">
                                        <br /> <a
                                            href="http://200.238.107.205:8080/multiwork/controller?action=atendimentoonline&origem=atendimentoOnline&INCODIGOORGAO=72&command=INSERT&KeepThis=true&TB_iframe=true&height=500&width=800&modal=true"
                                            target="#"> <img
                                                src="images/ico-ouvidoria-ambiental.fw.png" />
                                        </a>
                                    </div>
                                </div>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:forEach>
            <c:if test="${sessionScope.SESSION_IDENTIFICADOR_LOGADO == null}">
                <div id="buttons">
                    <!-- Dijit Monte seu Mapa -->
                    <div style="position: absolute;left:338px;top:50px;z-index:0;">
                        <div id="MonteMapaDijit">
                        </div>
                    </div>

                    <div id="div1" style="position: absolute;left:400px;top:50px;z-index:0;">
                        <img src="images/line-menu-max.fw.png" />
                    </div>

                    <div id="monteMapaMinimize" style="position: absolute;left:18.25%;top:102px;z-index:0;  z-index:1; display:none; cursor:pointer;">
                        <img src="images/ico-menu-selected.png" />
                    </div>

                    <!-- Ferramentas -->
                    <div style="position: absolute;left:407px;top:50px;z-index:0;">
                        <div id="FerramentasDijit">
                        </div>
                    </div>

                    <div id="div2" style="position: absolute;left:469px;top:50px;z-index:0;">
                        <img src="images/line-menu-max.fw.png" />
                    </div>

                    <div id="ferramentasMinimize" style="position: absolute;left:21.9%;top:102px;z-index:0; z-index:1; display:none; cursor:pointer; width:3%;">
                        <img src="images/ico-menu-selected.png" />
                    </div>

                    <!-- Localizar por Coordenadas -->
                    <div style="position: absolute;left:482px;top:50px;z-index:0; display:none;">
                        <div id="LocalizadorBtnDijit">
                        </div>
                    </div>

                    <div id="div3" style="position: absolute;left:544px;top:50px;z-index:0;">
                        <img src="images/line-menu-max.fw.png" />
                    </div>

                    <div id="localizarMinimize" style="position: absolute; left:25.75%;top:102px;z-index:0; z-index:1; display:none; cursor:pointer; width:3%;">
                        <img src="images/ico-menu-selected.png" />
                    </div>

                    <!-- Baixar Arquivos -->
                    <div style="position: absolute;left:554px;top:50px;z-index:0; display:none;">
                        <div id="BaixarArquivoDijit">
                        </div>
                    </div>

                    <div id="div4" style="position: absolute;left:616px;top:50px;z-index:0;">
                        <img src="images/line-menu-max.fw.png" />
                    </div>

                    <div id="baixarArquivoMinimize" style="position: absolute;left:29.5%;top:102px;z-index:0; z-index:1; display:none; cursor:pointer; width:3%;">
                        <img src="images/ico-menu-selected.png" />
                    </div>

                    <!-- Clip and Ship  -->
                    <div style="position: absolute;left:626px;top:50px;z-index:0; display:none;">
                        <div id="ClipShipBtnDijit">
                        </div>
                    </div>

                    <div id="div5" style="position: absolute;left:688px;top:50px;z-index:0;">
                        <img src="images/line-menu-max.fw.png" />
                    </div>

                    <div id="relatorioMinimize" style="position: absolute;left:33.5%;top:102px;z-index:0; z-index:1; width:3%; display:none; cursor:pointer;">
                        <img src="images/ico-menu-selected.png" />
                    </div>

                    <!-- Dijit Impressora -->
                    <div style="position: absolute;left:478px;top:50px;z-index:0;">
                        <div id="printButtonDijit">
                        </div>
                    </div>

                    <div id="div14" style="position: absolute;left:760px;top:50px;z-index:0; display:none;">
                        <img src="images/line-menu-max.fw.png" />
                    </div>

                    <div id="imprimirMinimize" style="position: absolute;left:37.10%;top:102px;z-index:0; z-index:1; width:3%; display:none; cursor:pointer;">
                        <img src="images/ico-menu-selected.png" />
                    </div>

                    <!-- Dijit Identify -->
                    <div style="position: absolute;left:552px;top:50px;z-index:0;">
                        <div id='IdentifyDijit'></div>
                    </div>

                    <div id="identifyAtivo" style="position: absolute;left:770px;top:50px;z-index:0;display:none;">
                        <img src="images/ico-menu-identify.fw.ativo.png" />
                    </div>

                    <div id="identifyMinimize" style="position: relative;left:40.8%;top:102px;z-index:0; z-index:1; display:none; cursor:pointer; width:3%;">
                        <img src="images/ico-menu-selected.png" />
                    </div>	

                    <div id="div13" style="position: absolute;left:832px;top:50px;z-index:0; display:none;">
                        <img src="images/line-menu-max.fw.png" />
                    </div>

                    <!-- Dijit Unidades de Conservação -->
                    <div style="position: absolute;left:622px;top:50px;z-index:0;">
                        <div id='UConservDijit'></div>
                    </div>

                    <div id="uconservMinimize" style="position: absolute;left:44.8%;top:102px;z-index:0; z-index:1; width:3%; display:none;cursor:pointer;">

                        <img src="images/ico-menu-selected.png" />
                    </div>
                    <!-- Dijit de Busca -->
                    <div style="position: absolute; right: 350px; top: 10px; z-index: 40;">
                        <div id='findTaskDijit'></div>
                    </div>

                    <div id="divhome" style="position: absolute; right: 310px; top: 13px; z-index: 0;">
                        <img src="images/ico-menu-home.fw.png" />
                    </div>

                    <div id="div7" style="position: absolute; right: 300px; top: 10px; z-index: 0;">
                        <img src="images/line-menu-min.fw.png" />
                    </div>

                    <div id="div8" style="position: absolute; right: 250px; top: 13px; z-index: 0;">
                        <a id="contato"
                           style="font-size: 12px; color: #939393; border-color: #BBBBBB;"
                           target="#" href="http://cabure.cprh.pe.gov.br/contato.php">Contato
                        </a>
                    </div>

                    <div id="div9" style="position: absolute; right: 240px; top: 10px; z-index: 0;">
                        <img src="images/line-menu-min.fw.png" />
                    </div>

                    <div id="div10" style="position: absolute; right: 200px; top: 13px; z-index: 0;">
                        <a id="editarperfil" style="font-size: 12px; color: #939393; border-color: #BBBBBB;"
                           href="#" onclick='dosomething()'>Ajuda </a>
                    </div>

                    <div id="div15" style="position: absolute; right: 190px; top: 10px; z-index: 0;">
                        <img src="images/line-menu-min.fw.png" />
                    </div>
                    <div id="div16" style="position: absolute; right: 100px; top: 13px; z-index: 0;">
                        <a id="editarperfil" style="font-size: 12px; color: #939393; border-color: #BBBBBB;"
                           href="reg.jsp" onclick='dosomething()'> 
                            Autentique-se 
                        </a>
                    </div>

                    <div id="div15"
                         style="position: absolute; right: 85px; top: 10px; z-index: 0;">
                        <img src="images/line-menu-min.fw.png" />
                    </div>

                    <div id="div16"
                         style="position: absolute; right: 50px; top: 13px; z-index: 0;">
                        <a id="editarperfil"
                           style="font-size: 12px; color: #939393; border-color: #BBBBBB;"
                           href="logout.jsp" onclick='dosomething()'> Voltar </a>
                    </div>

                    <div id="div11" style="position: absolute; right: 180px; top: 50px; z-index: 0;">
                        <br /> <a id="metadados" style="font-size: 13px; color: #7E941B;" target="#"
                                  href="http://200.17.134.119:8088/geoportal/catalog/main/home.page">Metadados
                        </a>
                    </div>

                    <div id="div12"
                         style="position: absolute; right: 160px; top: 60px; z-index: 0;">
                        <img src="images/line-menu-med.fw.png" />
                    </div>

                    <div id="div6"
                         style="position: absolute; right: 40px; top: 50px; z-index: 0;">
                        <br /> <a
                            href="http://200.238.107.205:8080/multiwork/controller?action=atendimentoonline&origem=atendimentoOnline&INCODIGOORGAO=72&command=INSERT&KeepThis=true&TB_iframe=true&height=500&width=800&modal=true"
                            target="#"> <img
                                src="images/ico-ouvidoria-ambiental.fw.png" />
                        </a>
                    </div>
                </div>
            </c:if>
        </div>
        <div id="sidebarCollapseButton" class="sidebarCollapseButton close"
             style="display: none;"></div>
        <script type="text/javascript">
                var dojoConfig = {
                async: true,
                        packages: [
                    {
                        name: "agsjs",
                    location: "http://gmaps-utility-gis.googlecode.com/svn/tags/agsjs/latest/build/agsjs"
                        },
                    {
                        name: "viewer",
                                location: location.pathname.replace(/[^\/]+$/, '')
                    + "js/viewer"
                        },
                    {
                        name: "gis",
                                location: location.pathname.replace(/[^\/]+$/, '')
                    + "js/gis"
                        },
                    {
                        name: "dbootstrap",
                                location: location.pathname.replace(/[^\/]+$/, '')
                    + "js/dbootstrap"
                        },
                    {
                        name: "xstyle",
                                location: location.pathname.replace(/[^\/]+$/, '')
            + "js/dbootstrap"
                    }]
            };
        </script>
        <script src="//js.arcgis.com/3.8"></script>
        <script
        src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <script type="text/javascript">
                    require(['dbootstrap', 'viewer/Controller'], function (dbootstrap,
                Controller) {
            Controller.startup();
            });
        </script>
    </body>
</html>