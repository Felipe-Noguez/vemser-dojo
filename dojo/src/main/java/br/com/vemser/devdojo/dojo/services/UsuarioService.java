package br.com.vemser.devdojo.dojo.services;

import br.com.vemser.devdojo.dojo.dto.UsuarioCreateDTO;
import br.com.vemser.devdojo.dojo.dto.UsuarioDTO;
import br.com.vemser.devdojo.dojo.entity.Usuario;
import br.com.vemser.devdojo.dojo.exceptions.BancoDeDadosException;
import br.com.vemser.devdojo.dojo.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final ObjectMapper objectMapper;

    private final UsuarioRepository usuarioRepository;


    public UsuarioDTO adicionar(UsuarioCreateDTO usuarioCreateDTOCreateDTO) throws BancoDeDadosException {

        Usuario usuarioEntity = objectMapper.convertValue(usuarioCreateDTOCreateDTO,Usuario.class);

        UsuarioDTO usuarioDTO = objectMapper.convertValue(usuarioRepository.adicionar(usuarioEntity)
                ,UsuarioDTO.class);

        return usuarioDTO;
    }

}
