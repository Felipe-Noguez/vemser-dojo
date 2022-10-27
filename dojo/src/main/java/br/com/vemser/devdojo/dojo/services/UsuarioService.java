package br.com.vemser.devdojo.dojo.services;

public class UsuarioService {

    public UsuarioDTO adicionar(UsuarioCreateDTO usuarioCreateDTOCreateDTO) throws BancoDeDadosException {

        Usuario usuarioEntity = objectMapper.convertValue(usuarioCreateDTOCreateDTO,Usuario.class);

        UsuarioDTO usuarioDTO = objectMapper.convertValue(usuarioRepository.adicionar(usuarioEntity)
                ,UsuarioDTO.class);

        return usuarioDTO;
    }

}
