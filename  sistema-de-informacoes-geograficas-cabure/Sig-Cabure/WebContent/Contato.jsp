<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form name="form" method ="post" onsubmit="return validate();" action="sendmail.php">
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


</body>
</html>