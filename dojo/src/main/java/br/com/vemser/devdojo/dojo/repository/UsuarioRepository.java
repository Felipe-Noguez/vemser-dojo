package br.com.vemser.devdojo.dojo.repository;

import br.com.vemser.devdojo.dojo.config.ConexaoBancoDeDados;
import br.com.vemser.devdojo.dojo.entity.Usuario;
import br.com.vemser.devdojo.dojo.exceptions.BancoDeDadosException;
import br.com.vemser.devdojo.dojo.exceptions.RegraDeNegocioException;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class UsuarioRepository implements Repositorio<Long, Usuario>{

    private ConexaoBancoDeDados conexaoBancoDeDados;

    public UsuarioRepository(ConexaoBancoDeDados conexaoBancoDeDados) {
        this.conexaoBancoDeDados = conexaoBancoDeDados;
    }


    @Override
    public Integer getProximoId(Connection connection) throws RegraDeNegocioException, SQLException {
        String sql = "SELECT SEQ_USUARIO_DOJO.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Usuario adicionar(Usuario object) throws BancoDeDadosException, RegraDeNegocioException {
        return null;
    }

    @Override
    public boolean remover(Long id) throws RegraDeNegocioException, BancoDeDadosException {
        return false;
    }

    @Override
    public boolean editar(Long id, Usuario usuario) throws RegraDeNegocioException, BancoDeDadosException {
        return false;
    }

    @Override
    public List<Usuario> listar() throws RegraDeNegocioException, BancoDeDadosException {
        return null;
    }
}
