package br.com.vemser.devdojo.dojo.repository;

import br.com.vemser.devdojo.dojo.config.ConexaoBancoDeDados;
import br.com.vemser.devdojo.dojo.entity.TipoUsuario;
import br.com.vemser.devdojo.dojo.entity.Usuario;
import br.com.vemser.devdojo.dojo.exceptions.BancoDeDadosException;
import br.com.vemser.devdojo.dojo.exceptions.RegraDeNegocioException;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Annotation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository implements Repositorio<Integer, Usuario> {

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
    public Usuario adicionar(Usuario usuario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            usuario.setId(proximoId);

            String sql = "INSERT INTO USUARIO_DOJO\n" +
                    "(ID, NOME, DATA_NASCIMENTO, SENHA, DATA_CRIACAO, EMAIL, TIPO_CLIENTE, ATIVO)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, usuario.getId());
            stmt.setString(2, usuario.getNome());
            stmt.setDate(3, Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(4, usuario.getSenha());
            stmt.setDate(5, Date.valueOf(usuario.getDataCriacao()));
            stmt.setString(6, usuario.getEmail());
            stmt.setString(7, usuario.getTipoUsuario().getTipos());
            stmt.setString(8, usuario.getAtivo());

            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } catch (RegraDeNegocioException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usuario;
    }

    @Override
    public boolean remover(Integer id) throws RegraDeNegocioException, BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM USUARIO_DOJO WHERE ID = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);

            int res = stmt.executeUpdate();
            System.out.println("removerUsuarioPorId.res=" + res);

            return res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean editar(Integer id, Usuario usuario) throws BancoDeDadosException, RegraDeNegocioException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PESSOA SET ");
            sql.append(" nome = ?,");
            sql.append(" data_nascimento = ?,");
            sql.append(" senha = ?,");
            sql.append(" data_criacao = ? ");
            sql.append(" email = ? ");
            sql.append(" tipo_cliente = ? ");
            sql.append(" ativo = ? ");
            sql.append(" WHERE id_pessoa = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, usuario.getNome());
            stmt.setDate(2, Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(3, usuario.getSenha());
            stmt.setDate(4, Date.valueOf(usuario.getDataCriacao()));
            stmt.setString(5, usuario.getEmail());
            stmt.setString(6, usuario.getTipoUsuario().getTipos());
            stmt.setString(7, usuario.getAtivo());
            stmt.setInt(8, id);

            int res = stmt.executeUpdate();

            return res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Usuario> listar() throws BancoDeDadosException {
        List<Usuario> usuarios = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM USUARIO_DOJO";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(res.getInt("id"));
                usuario.setNome(res.getString("nome"));
                usuario.setDataNascimento(res.getDate("data_nascimento").toLocalDate());
                usuario.setSenha(res.getString("senha"));
                usuario.setDataCriacao(res.getDate("data_criacao").toLocalDate());
                usuario.setEmail(res.getString("email"));
                usuario.setTipoUsuario(TipoUsuario.valueOf(res.getString("tipo_cliente")));
                usuario.setAtivo(res.getString("ativo"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usuarios;
    }

    public Usuario findById(Integer id) throws BancoDeDadosException {
        Connection con = null;
        Usuario usuario = new Usuario();
        try {
            con = conexaoBancoDeDados.getConnection();
            String sql = "SELECT * FROM USUARIO_DOJO WHERE ID = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                usuario.setId(res.getInt("id"));
                usuario.setNome(res.getString("nome"));
                usuario.setDataNascimento(res.getDate("data_nascimento").toLocalDate());
                usuario.setSenha(res.getString("senha"));
                usuario.setDataCriacao(res.getDate("data_criacao").toLocalDate());
                usuario.setEmail(res.getString("email"));
                usuario.setTipoUsuario(TipoUsuario.valueOf(res.getString("tipo_cliente")));
                usuario.setAtivo(res.getString("ativo"));
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usuario;

    }

    public List<Usuario> findByTipo(TipoUsuario tipo) throws BancoDeDadosException {

        Connection con = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            con = conexaoBancoDeDados.getConnection();
            String sql = "SELECT * FROM USUARIO_DOJO WHERE tipo_cliente = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, tipo.getTipos());

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(res.getInt("id"));
                usuario.setNome(res.getString("nome"));
                usuario.setDataNascimento(res.getDate("data_nascimento").toLocalDate());
                usuario.setSenha(res.getString("senha"));
                usuario.setDataCriacao(res.getDate("data_criacao").toLocalDate());
                usuario.setEmail(res.getString("email"));
                usuario.setTipoUsuario(TipoUsuario.valueOf(res.getString("tipo_cliente")));
                usuario.setAtivo(res.getString("ativo"));
                usuarios.add(usuario);

            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usuarios;

    }

}

