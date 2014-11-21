/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

macario
 */
package com.itep.cabure.controler;

import com.itep.cabure.dao.*;
import com.itep.cabure.entidades.*;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author fernanda.franklin
 */
public class TestesBanco {

    public static void main(String[] lala) {

        
        // <editor-fold defaultstate="collapsed" desc="Retorna Pessoa">
        GenericDAO<Pessoa> pessoaDAO = new GenericDAO<Pessoa>();

        Pessoa acessaPessoa = new Pessoa();
        List<Pessoa> pessoas = (List<Pessoa>) pessoaDAO.acessaRegistrosLike(acessaPessoa, "nome", "%MARIA%", "nome");
        
        if (pessoas ==null){
            System.out.println("busca nula");
        }else {
        
        for(Pessoa ps : pessoas){
            System.out.println(ps.getNome());
        }
        
        }
        
        
        
        

        // <editor-fold defaultstate="collapsed" desc="Grupo">
//        GenericDAO<Grupo> grupoDAO = new GenericDAO<Grupo>();
//        Grupo grupoPessoa = new Grupo();

        // Retorna o grupo cujo nome é igual à Colaborador
//        Grupo grupo = new Grupo();
//        grupoPessoa = (Grupo) grupoDAO.acessaRegistro(grupo, "nomeGrupo", "Colaborador");
//        
//        pessoa.setGrupo(grupoPessoa);
//        grupoDAO.fechar();
//
//        pessoaDAO.salvar(pessoa);
//        pessoaDAO.fechar();
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Empresa">
//        Empresa empresa = new Empresa();
//        GenericDAO<Empresa> empresaDAO = new GenericDAO<Empresa>();
//
//        empresa.setCnpj(insertCnpj);
//        empresa.setRazaoSocial(razSocialEmp);
//        empresa.setInscricao(inscricaoEst);
//        
//        GenericDAO<Grupo> grupoDAOEmp = new GenericDAO<Grupo>();
//        Grupo grupoEmpreendedor = new Grupo();
//
//        grupoEmpreendedor = (Grupo) grupoDAO.acessaRegistro(grupo, "nomeGrupo", "Empreendedor");
//
//        empresa.setGrupo(grupoEmpreendedor);
//        grupoDAOEmp.fechar();
//
//        empresaDAO.salvar(empresa);
//        empresaDAO.fechar();
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Endereco">
//        Endereco endereco = new Endereco();
//        Endereco enderecoEmp = new Endereco();
//        GenericDAO<Endereco> enderecoDAO = new GenericDAO<Endereco>();
//        
//        LogControler logPessoa = new LogControler();
//        
//        Usuario user = new Usuario();
//        
//        GenericDAO<Usuario> userDAO = new GenericDAO<Usuario>();
//        
//        Usuario usuario = (Usuario) userDAO.acessaRegistro(user, "usuario", "121.862.636-45");
//        
//        System.out.println("O usuario é ----> " + usuario.getUsuario());
//        
//        userDAO.fechar();
//        
//       // Pessoa pessoaBuscada = logPessoa.getPessoaLogada(usuario);
//        
//        
//               
////        Endereco buscaEndereco = (Endereco) enderecoDAO.acessaRegistroObjeto(endereco, "pessoa", pessoaBuscada);
////        
////        System.out.println("O endereco é ------> " + buscaEndereco.getLogradouro());
//                
//        enderecoDAO.fechar();


        // <editor-fold defaultstate="collapsed" desc="Usuario">
//        Usuario usuario = new Usuario();
//        GenericDAO<Usuario> usuarioDAO = new GenericDAO<Usuario>();
//
//        usuario.setUsuario(pessoa.getCpf());
//        usuario.setSenha(senhaUsuario);
//        usuario.setGrupo(grupoPessoa);
//
//        usuarioDAO.salvar(usuario);
//        usuarioDAO.fechar();

        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Valida">
       
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Log Sistema">
//        LogControler logUsuario = new LogControler();
//        logUsuario.setLog(usuario, PadroesLog.CADASTRO_USUARIO);
//        logUsuario.setLog(usuarioEmp, PadroesLog.CADASTRO_USUARIO);
        // </editor-fold> 
        // <editor-fold defaultstate="collapsed" desc="Arquivos">
//        Arquivos arquivos = new Arquivos();
//        GenericDAO<Arquivos> arquivosDAO = new GenericDAO<Arquivos>();
//
//        arquivos.setCaminhoArquivo("C:/arquivos");
//        arquivos.setNomeArquivo("Arquivos Teste");
//        arquivos.setTamanhoArquivo(23);
//        arquivos.setTipoArquivo("txt");
//
//        arquivosDAO.salvar(arquivos);
//        arquivosDAO.fechar();
    	// </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Publicacao">
//        Publicacao publicacao = new Publicacao();
//        GenericDAO<Publicacao> publicacaoDAO = new GenericDAO<Publicacao>();
//
//        publicacao.setCoordPublicacao("Coordenadas da Publicacaoo");
//        publicacao.setDescricaoPublicacao("Teste de Publicacao");
//
//        publicacaoDAO.salvar(publicacao);
//        publicacaoDAO.fechar();
    	// </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Arquivos Publicados">
//        ArquivosPublicados arquivosPubli = new ArquivosPublicados();
//        GenericDAO<ArquivosPublicados> arquivosPubliDAO = new GenericDAO<ArquivosPublicados>();
//
//        ArquivospublicadosPK arquivosPubliPk = new ArquivospublicadosPK();
//
//        arquivosPubliPk.setIdArquivosPublicados(arquivos);
//        arquivosPubliPk.setIdPublicacao(publicacao);
//
//        arquivosPubli.setIdsArquivosPublicados(arquivosPubliPk);
//
//        arquivosPubliDAO.salvar(arquivosPubli);
//        arquivosPubliDAO.fechar();

        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Pessoa Publicacao">
//        PessoaPublicacao pessoaPubli = new PessoaPublicacao();
//        GenericDAO<PessoaPublicacao> pessoaPubliDAO = new GenericDAO<PessoaPublicacao>();
//
//        pessoaPubli.setDataPublicada(data);
//
//        PessoaPublicacaoPK pessoaPubliPK = new PessoaPublicacaoPK();
//
//        pessoaPubliPK.setIdPessoa(pessoa);
//        pessoaPubliPK.setIdPublicacao(publicacao);
//
//        pessoaPubli.setIdsPessoaPubli(pessoaPubliPK);
//
//        pessoaPubliDAO.salvar(pessoaPubli);
//        pessoaPubliDAO.fechar();
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Empresa Publicacao">
//        PublicacaoEmpresa publiEmpresa = new PublicacaoEmpresa();
//        GenericDAO<PublicacaoEmpresa> publiEmpresaDAO = new GenericDAO<PublicacaoEmpresa>();
//
//        publiEmpresa.setDataPublicada(data);
//
//        PublicacaoEmpresaPK publiEmpresaPK = new PublicacaoEmpresaPK();
//
//        publiEmpresaPK.setIdEmpresa(empresa);
//        publiEmpresaPK.setIdPublicacao(publicacao);
//
//        publiEmpresa.setIdsPubliEmpresa(publiEmpresaPK);
//
//        publiEmpresaDAO.salvar(publiEmpresa);
//        publiEmpresaDAO.fechar();
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Relatorio">
//        RelatorioEmitido relatorioEmitido = new RelatorioEmitido();
//        GenericDAO<RelatorioEmitido> relatorioEmiDAO = new GenericDAO<RelatorioEmitido>();
//
//        relatorioEmitido.setCodigoRelatorio("CodPessoa");
//        relatorioEmitido.setDataEmissao(data);
//        relatorioEmitido.setShapeRelatorio("shape");
//        relatorioEmitido.setPessoa(pessoa);
//
//        relatorioEmiDAO.salvar(relatorioEmitido);
//
//        RelatorioEmitido relatorioEmitidoEmp = new RelatorioEmitido();
//        relatorioEmitidoEmp.setCodigoRelatorio("CodEmpresa");
//        relatorioEmitidoEmp.setDataEmissao(data);
//        relatorioEmitidoEmp.setShapeRelatorio("shape");
//
//        relatorioEmitidoEmp.setEmpresa(empresa);
//        relatorioEmiDAO.salvar(relatorioEmitidoEmp);
//
//        relatorioEmiDAO.fechar();
        // </editor-fold>
    }

}
