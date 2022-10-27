package br.com.vemser.devdojo.dojo.services;

import br.com.vemser.devdojo.dojo.dto.UsuarioCreateDTO;
import br.com.vemser.devdojo.dojo.dto.UsuarioDTO;
import br.com.vemser.devdojo.dojo.entity.Usuario;
import br.com.vemser.devdojo.dojo.exceptions.BancoDeDadosException;
import br.com.vemser.devdojo.dojo.exceptions.RegraDeNegocioException;
import br.com.vemser.devdojo.dojo.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final ObjectMapper objectMapper;

    private final UsuarioRepository usuarioRepository;


    public UsuarioDTO adicionar(UsuarioCreateDTO usuarioCreateDTOCreateDTO) throws RegraDeNegocioException {

        Usuario usuarioEntity = objectMapper.convertValue(usuarioCreateDTOCreateDTO,Usuario.class);

        UsuarioDTO usuarioDTO = objectMapper.convertValue(usuarioRepository.adicionar(usuarioEntity)
                ,UsuarioDTO.class);

        return usuarioDTO;
    }

    public List<UsuarioDTO> listar() throws RegraDeNegocioException  {
        return usuarioRepository.listar().stream()
                .map(usuario -> objectMapper.convertValue(usuario,UsuarioDTO.class))
                .toList();
    }

    public UsuarioDTO atualizar(Integer id, UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException {
        Usuario usuarioAtualizar = findById(id);
        Usuario usuarioEntity = objectMapper.convertValue(usuarioCreateDTO, Usuario.class);

        usuarioAtualizar.setNome(usuarioEntity.getNome());
        usuarioAtualizar.setEmail(usuarioEntity.getEmail());
        usuarioAtualizar.setDataNascimento(usuarioEntity.getDataNascimento());
        usuarioAtualizar.setSenha(usuarioEntity.getSenha());
        usuarioAtualizar.setDataCriacao(usuarioEntity.getDataCriacao());
        usuarioAtualizar.setAtivo(usuarioEntity.getAtivo());
        usuarioAtualizar.setTipoUsuario(usuarioEntity.getTipoUsuario());
        return objectMapper.convertValue(usuarioAtualizar,UsuarioDTO.class);
    }

    public void delete(Integer id) throws RegraDeNegocioException, BancoDeDadosException {
        Usuario usuarioDelete = findById(id);
        UsuarioCreateDTO usuarioCreateDTO = objectMapper.convertValue(usuarioDelete,UsuarioCreateDTO.class);

        usuarioRepository.remover(usuarioDelete.getId());
    }

    public UsuarioDTO findById(Integer id) throws RegraDeNegocioException {
        Usuario usuario = usuarioRepository.listar().stream()
                .filter((usuarioAtualizar -> usuarioAtualizar.getId()==id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException(("Usuario não encontrado")));
        return  usuario;
    }
}
