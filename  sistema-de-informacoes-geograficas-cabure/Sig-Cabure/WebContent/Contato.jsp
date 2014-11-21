
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="description" content="Sistema de geolocalização realizado em parceria com CPRH, Governo de Pernambuco e ITEP que oferece um serviço diferenciado e gratuito para a população">
    <meta name="keywords" content="SIG, Sistema de Informações Geoambientais, Caburé, Pernambuco, Geoambiental, Geolocalização, CPRH, Governo de Pernambuco, ITEP, Instituto de Tecnologia de Pernambuco">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<noscript>
     Para completa funcionalidade deste site é necessário habilitar o JavaScript.
     Aqui estão as <a href="http://www.enable-javascript.com/pt/" target="_blank">
     instruções de como habilitar o JavaScript no seu navegador</a>.
    </noscript>
	<script src="js/jquery.min.js"></script>
    
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
	<link href="style.css" type="text/css" media="all" rel="stylesheet"/>
	<title>SIG Caburé - Contato</title>
    <script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-49390512-1', 'pe.gov.br');
  ga('send', 'pageview');

</script>
</head>
<body> 
    <!---------- Barra do GovernoPE ------------------>
    <iframe src="http://www2.ati.pe.gov.br/SiteAti-theme/topo-governo/topo-governo.html" scrolling="no" frameborder="0" width="100%" height="58px"></iframe>
    <!------ /barra do GovernoPE ------------------->
  	<!---------- Wrappper ------------------>
    <div class="wrapper">
        <!---------- Topo ------------------>
        <header>
            <div class="block-container">
                <div class="logo">
                <a href="index.html" title="SIG Caburé"><img src="images/logo-cabure.fw.png" alt="SIG Caburé"/></a>
                </div>
                
                <div class="access-button">
                <a href="http://sigcabure.cprh.pe.gov.br" title="SIG Caburé"><img src="images/header-btn-access-sig.fw.png" alt="Acesse o SIG Caburé"/></a>
                </h1>
            </div>
        </header>
        <!---------- /Topo ------------------>
        <section class="back-home">
        	<div class="inner-container">
            	<div class="back-button">
                        <a href="index.html"><span><img src="images/ico-btn-voltar.fw.png" alt="Voltar"/> Voltar</span></a>             
                </div>
            </div>
        </section>
    
    
        <section class="content-title">
        	<div class="inner-container">
            	<h2>Fale Conosco</h2>
            </div>
        </section>
        
        <section class="maincontent">
        	<div class="inner-container">
               
                <p class="description">Esta área é reservada para você entrar em contato com a CPRH. Envie suas opiniões, comentários, mensagens e também sugestões e críticas. 
				Participe da construção e do desenvolvimento do nosso portal. É com a colaboração de todos que podemos crescer e oferecer mais e melhores serviços à sociedade.</p>
				<form name="form" method ="post" onsubmit="return validate();" action="contato.do">
					<div class="left-content">
                        <input type="text" name='nome' placeholder="Nome (*)" class="required"  required >
                        <input type="text" name='email' placeholder="Email (*)" class="required"  required >
                        <select type="text" name="uf" class="inputs" placeholder="Estado" > 
                        <option value="AC"> AC</option>
                        <option value="AL"> AL</option>
                        <option value="AM"> AM</option>
                        <option value="AP"> AP</option>
                        <option value="BA"> BA</option>
                        <option value="CE"> CE</option>
                        <option value="DF"> DF</option>
                        <option value="ES"> ES</option>
                        <option value="GO"> GO</option>
                        <option value="MA"> MA</option>
                        <option value="MG"> MG</option>
                        <option value="MS"> MS</option>
                        <option value="MT"> MT</option>
                        <option value="PA"> PA</option>
                        <option value="PB"> PB</option>
                        <option value="PE" selected="selected"> PE</option>
                        <option value="PI"> PI</option>
                        <option value="PR"> PR</option>
                        <option value="RJ"> RJ</option>
                        <option value="RN"> RN</option>
                        <option value="RO"> RO</option>
                        <option value="RR"> RR</option>
                        <option value="RS"> RS</option>
                        <option value="SC"> SC</option>
                        <option value="SE"> SE</option>
                        <option value="SP"> SP</option>
                        <option value="TO"> TO</option>
                        <input type="text" name="cidade" class="inputs" placeholder="Cidade" > 
                        <input type="text" name="prof" class="inputs" placeholder="Profissão" > 
                        </select>
					</div>
                    <div class="right-content">
                    <textarea id="mensagem" name='msg' placeholder="Mensagem (*)" rows="9" cols="50" required ></textarea>
                    </div>
                	<p class="req"> (*) Campos de preenchimento obrigatório</p>
                    <input id="submit" type="submit" value="Enviar">
				</form>
        </section>
	<!---------- /Wrappper ------------------>
    <!---------- Início Rodapé ------------------>
	<script type="text/javascript" src="js/validate.js"></script>
    <footer>
   		<div class="strip"></div>
        <div class="block-container">
            <div class="socials">
            	<span>
				<!--<a href="#">-->
                        <img src="images/ico-footer-email.fw.png" alt="Enviar Mensagem" title="Enviar Mensagem" />
                <!-- </a>-->
                </span>
                <span>
                    <a href="http://twitter.com/CPRH_PE" target="_blank">
                        <img src="images/ico-footer-twitter.fw.png" alt="Siga-nos no Twitter" title="Siga-nos no Twitter" />
                    </a>
                </span>
                <span>
                <a href="http://www.facebook.com/CPRHPE" target="_blank">
                    <img src="images/ico-footer-facebook.fw.png" alt="Curta no Facebook" title="Curta no Facebook" />
                </a>
                </span>
                <span class="vline">
                <img src="images/ico-footer-ouvidoria.fw.png" alt="Entre em contato com a Ouvidoria" title="Ouvidoria" /> (81) 3182-8923</span>
                
                
            </div>
            <div class="hline"></div>
            <div class="realizacao">
            	<p>Realização</p>
                <span>
                    <a href="http://www.cprh.pe.gov.br/" target="_blank">
                        <img src="images/logo-cprh.fw.png" alt="Agência Estadual do Meio Ambiente" title="Agência Estadual do Meio Ambiente" />
                    </a>
                </span>
                <span>
                <a href="http://www.pe.gov.br/" target="_blank">
                    <img src="images/logo-governo-pe-secretaria-ma.fw.png" alt="Governo de Pernambuco" title="Governo de Pernambuco" />
                </a>
                </span>
            </div>
            <div class="hline"></div>
            <div class="development">
            	<p>Desenvolvimento</p>
                <br><br>
                <span>
                    <a href="http://www.itep.br" target="_blank">
                        <img src="images/logo-itep.fw.png" alt="ITEP" />
                    </a>
                    
                </span> <br><br><br>
                <p><a class="goToHeader" href="javascript:;"><img src="images/btn-go-to-header.fw.png" alt="Voltar para Topo" title="Voltar para Topo" /></a><p>
            </div>
       </div>
	   </div>
    </footer>
    <!---------- /Rodapé ------------------>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
    <script>
		$('.goToHeader').click(function(){
			$('html, body').animate({scrollTop: 0},'slow');
		});
	</script>
</body>

</html>