package com.itep.cabure.controler;

import com.itep.cabure.dao.*;
import com.itep.cabure.entidades.*;

import java.util.Calendar;

/**
 *
 * Script para inicialização do banco de dados
 *
 * @author macario.granja
 *
 */
public class InicializacaoBanco {

    public static void main(String[] lala) {

        String insertCpf = "986.535.870-04";
        String insertCnpj = "06.747.309/0001-26";

        String nomeGrupoCol = "Colaborador";
        String descricaoGrupoCol = "Grupo com funcionalidades para usuários - Pessoas Físicas - Colaboradores.";
        
        String nomeGrupoEmp = "Empreendedor";
        String descricaoGrupoEmp = "Grupo com funcionalidades para usuários - Pessoas Físicas quanto Jurídicas - Empreendedores.";

        String nomefuncionalidade = "Shapes";
        String descFuncionalidade = "Permite desenhar shapes";

        String nomePessoa = "José Carlos Amaral";
        String numRG = "50.681.965";
        String orgaoEmissor = "ssp-mg";
        
        String razSocialEmp = "Google Earth LTDA";
        String inscricaoEst = "344.3345.4443";
        
        String cep = "37900-118";
        String logradouro = "Avenida Comendador Francisco Avelino Maia";
        int numero = 1034;
        String complemento = "Salas 01 e 02";
        String bairro = "Centro";
        String municipio = "Recife";
        String uf = "PE";
        
        String celular = "";
        String telefone = "(81) 3433-8767";
        String email = "fernanda.franklin@itep.br";
        
        String senhaUsuario = "123"; 

        Calendar data = Calendar.getInstance();

        // <editor-fold defaultstate="collapsed" desc="Grupo">
        Grupo grupoColaborador = new Grupo();
        Grupo grupoEmpreendedor = new Grupo();
        GenericDAO<Grupo> grupoDAO = new GenericDAO<Grupo>();

        grupoColaborador.setDescricaoGrupo(descricaoGrupoCol);
        grupoColaborador.setNomeGrupo(nomeGrupoCol);
        grupoDAO.salvar(grupoColaborador);
        
        grupoEmpreendedor.setNomeGrupo(nomeGrupoEmp);
        grupoEmpreendedor.setDescricaoGrupo(descricaoGrupoEmp);

        grupoDAO.salvar(grupoEmpreendedor);
        grupoDAO.fechar();
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Funcionalidade">
        Funcionalidade funcionalidade = new Funcionalidade();
        GenericDAO<Funcionalidade> funcionalidadeDAO = new GenericDAO<Funcionalidade>();

        funcionalidade.setFuncionalidade(nomefuncionalidade);
        funcionalidade.setDescricaoFunc(descFuncionalidade);

        funcionalidadeDAO.salvar(funcionalidade);
        funcionalidadeDAO.fechar();
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Grupo Funcionalidade">
        GrupoFuncionalidade grupoFuncionalidade = new GrupoFuncionalidade();
        GenericDAO<GrupoFuncionalidade> grupoFuncDAO = new GenericDAO<GrupoFuncionalidade>();

        grupoFuncionalidade.setDataInsercao(data);

        GrupoFuncionalidadePK grupoFuncPk = new GrupoFuncionalidadePK();

        grupoFuncPk.setIdGrupoFuncionalidade(grupoEmpreendedor);
        grupoFuncPk.setIdFuncionalidadeGrupo(funcionalidade);

        grupoFuncionalidade.setIdsGrupoFuncionade(grupoFuncPk);

        grupoFuncDAO.salvar(grupoFuncionalidade);
        grupoFuncDAO.fechar();
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Pessoa">
        Pessoa pessoa = new Pessoa();
        GenericDAO<Pessoa> pessoaDAO = new GenericDAO<Pessoa>();

        pessoa.setCpf(insertCpf);
        pessoa.setNome(nomePessoa);
        pessoa.setRg(numRG);
        pessoa.setOrgaoEmissor(orgaoEmissor);

        pessoa.setGrupo(grupoColaborador);

        pessoaDAO.salvar(pessoa);
        pessoaDAO.fechar();
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Empresa">
        Empresa empresa = new Empresa();
        GenericDAO<Empresa> empresaDAO = new GenericDAO<Empresa>();

        empresa.setCnpj(insertCnpj);
        empresa.setRazaoSocial(razSocialEmp);
        empresa.setInscricao(inscricaoEst);

        empresa.setGrupo(grupoEmpreendedor);

        empresaDAO.salvar(empresa);
        empresaDAO.fechar();
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Endereco">
        Endereco endereco = new Endereco();
        Endereco enderecoEmp = new Endereco();
        GenericDAO<Endereco> enderecoDAO = new GenericDAO<Endereco>();

        enderecoEmp.setCep(cep);
        enderecoEmp.setLogradouro(logradouro);
        enderecoEmp.setNumero(numero);
        enderecoEmp.setComplemento(complemento);
        enderecoEmp.setBairro(bairro);
        enderecoEmp.setMunicipio(municipio);
        enderecoEmp.setUf(uf);

        enderecoEmp.setEmpresa(empresa);
        enderecoDAO.salvar(enderecoEmp);
        
        cep = "50876-400";
        logradouro = "Rua das Creoulas";
        numero = 103;
        complemento = "Bloco 04 AP 607";
        bairro = "Torre";
        municipio = "Recife";
        uf = "PE";
                
        endereco.setCep(cep);
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setMunicipio(municipio);
        endereco.setUf(uf);

        endereco.setPessoa(pessoa);

        enderecoDAO.salvar(endereco);

//        enderecoDAO.getList(endereco);
//        System.out.println(" Municipio" + endereco.getMunicipio());

        enderecoDAO.fechar();
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Contato">
        Contato contatoEmp = new Contato();
        GenericDAO<Contato> contatoDAO = new GenericDAO<Contato>();

        contatoEmp.setCelular(celular);
        contatoEmp.setTelefoneEmpresa(telefone);
        contatoEmp.setEmail(email);
        //contato.setFax("453454-2222");
        contatoEmp.setEmpresa(empresa);
        contatoDAO.salvar(contatoEmp);
        
        celular = "(81) 8765-9843";
        telefone = "(81) 3433-8767";
        email = "macario.granja@itep.br";
        
        Contato contato = new Contato();
        contato.setCelular(celular);
        contato.setTelefoneEmpresa(telefone);
        contato.setEmail(email);
        
        contato.setPessoa(pessoa);


        contatoDAO.salvar(contato);
        contatoDAO.fechar();
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Usuario">
        Usuario usuario = new Usuario();
        GenericDAO<Usuario> usuarioDAO = new GenericDAO<Usuario>();

        usuario.setUsuario(pessoa.getCpf());
        usuario.setSenha(senhaUsuario);
        usuario.setGrupo(grupoColaborador);
//        usuario.setFlagAcesso(0);

        usuarioDAO.salvar(usuario);
        //usuarioDAO.fechar();
        
        Usuario usuarioEmp = new Usuario();
        usuarioEmp.setUsuario(empresa.getCnpj());
        usuarioEmp.setSenha(senhaUsuario);
        usuarioEmp.setGrupo(grupoEmpreendedor);
       // usuarioEmp.setFlagAcesso(0);
        
        usuarioDAO.salvar(usuarioEmp);
        usuarioDAO.fechar();
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Valida">
        Valida validaUsuario = new Valida();
        GenericDAO<Valida> validaUsuarioDAO = new GenericDAO<Valida>();

//        validaUsuario.setDataValida(data);
        validaUsuario.setIdUsuario(usuario);
       // validaUsuario.setLinkValida("asdfasferfgasdf");
        //validaUsuario.setFlag(0);

//        ValidaPK validaUsuarioPK = new ValidaPK();


        validaUsuarioDAO.salvar(validaUsuario);
        validaUsuarioDAO.fechar();

        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Log Sistema">
        LogControler logUsuario = new LogControler();
        logUsuario.setLog(usuario, PadroesLog.CADASTRO_USUARIO);
        logUsuario.setLog(usuarioEmp, PadroesLog.CADASTRO_USUARIO);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Arquivos">
        Arquivos arquivos = new Arquivos();
        GenericDAO<Arquivos> arquivosDAO = new GenericDAO<Arquivos>();

        arquivos.setCaminhoArquivo("C:/arquivos");
        arquivos.setNomeArquivo("Arquivos Teste");
        arquivos.setTamanhoArquivo(23);
        arquivos.setTipoArquivo("txt");

        arquivosDAO.salvar(arquivos);
        arquivosDAO.fechar();
    	// </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Publicacao">
        Publicacao publicacao = new Publicacao();
        GenericDAO<Publicacao> publicacaoDAO = new GenericDAO<Publicacao>();

        publicacao.setCoordPublicacao("Coordenadas da Publicacaoo");
        publicacao.setDescricaoPublicacao("Teste de Publicacao");

        publicacaoDAO.salvar(publicacao);
        publicacaoDAO.fechar();
    	// </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Arquivos Publicados">
        ArquivosPublicados arquivosPubli = new ArquivosPublicados();
        GenericDAO<ArquivosPublicados> arquivosPubliDAO = new GenericDAO<ArquivosPublicados>();

        ArquivospublicadosPK arquivosPubliPk = new ArquivospublicadosPK();

        arquivosPubliPk.setIdArquivosPublicados(arquivos);
        arquivosPubliPk.setIdPublicacao(publicacao);

        arquivosPubli.setIdsArquivosPublicados(arquivosPubliPk);

        arquivosPubliDAO.salvar(arquivosPubli);
        arquivosPubliDAO.fechar();

        // </editor-fold>
       
        // <editor-fold defaultstate="collapsed" desc="Pessoa Publicacao">
        PessoaPublicacao pessoaPubli = new PessoaPublicacao();
        GenericDAO<PessoaPublicacao> pessoaPubliDAO = new GenericDAO<PessoaPublicacao>();

        pessoaPubli.setDataPublicada(data);

        PessoaPublicacaoPK pessoaPubliPK = new PessoaPublicacaoPK();

        pessoaPubliPK.setIdPessoa(pessoa);
        pessoaPubliPK.setIdPublicacao(publicacao);

        pessoaPubli.setIdsPessoaPubli(pessoaPubliPK);

        pessoaPubliDAO.salvar(pessoaPubli);
        pessoaPubliDAO.fechar();
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Empresa Publicacao">
        PublicacaoEmpresa publiEmpresa = new PublicacaoEmpresa();
        GenericDAO<PublicacaoEmpresa> publiEmpresaDAO = new GenericDAO<PublicacaoEmpresa>();

        publiEmpresa.setDataPublicada(data);

        PublicacaoEmpresaPK publiEmpresaPK = new PublicacaoEmpresaPK();

        publiEmpresaPK.setIdEmpresa(empresa);
        publiEmpresaPK.setIdPublicacao(publicacao);

        publiEmpresa.setIdsPubliEmpresa(publiEmpresaPK);

        publiEmpresaDAO.salvar(publiEmpresa);
        publiEmpresaDAO.fechar();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Relatorio">
        RelatorioEmitido relatorioEmitido = new RelatorioEmitido();
        GenericDAO<RelatorioEmitido> relatorioEmiDAO = new GenericDAO<RelatorioEmitido>();

        relatorioEmitido.setCodigoRelatorio("CodPessoa");
        relatorioEmitido.setDataEmissao(data);
//        relatorioEmitido.setShapeRelatorio("shape");
        relatorioEmitido.setPessoa(pessoa);

        relatorioEmiDAO.salvar(relatorioEmitido);
        
        RelatorioEmitido relatorioEmitidoEmp = new RelatorioEmitido();
        relatorioEmitidoEmp.setCodigoRelatorio("CodEmpresa");
        relatorioEmitidoEmp.setDataEmissao(data);
      //  relatorioEmitidoEmp.setShapeRelatorio("shape");
        
        relatorioEmitidoEmp.setEmpresa(empresa);
        relatorioEmiDAO.salvar(relatorioEmitidoEmp);
        
        relatorioEmiDAO.fechar();
        // </editor-fold>
    }
}
