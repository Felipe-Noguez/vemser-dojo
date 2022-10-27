package br.com.vemser.devdojo.dojo.controller;

import br.com.vemser.devdojo.dojo.dto.UsuarioCreateDTO;
import br.com.vemser.devdojo.dojo.dto.UsuarioDTO;
import br.com.vemser.devdojo.dojo.exceptions.RegraDeNegocioException;
import br.com.vemser.devdojo.dojo.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() throws RegraDeNegocioException {
        return new ResponseEntity<>(usuarioService.listar(), HttpStatus.OK);
    }
    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> pegar(@PathVariable(name = "idUsuario") Integer idUsuario) throws RegraDeNegocioException {
        return new ResponseEntity<>(usuarioService.findById(idUsuario), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<UsuarioDTO> adicionar(@Valid @RequestBody UsuarioCreateDTO usuario) throws RegraDeNegocioException {
        log.info("Adicionando o Usuário...");
        UsuarioDTO user = usuarioService.adicionar(usuario);
        log.info("Usuário adicionado com sucesso!");
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> editar(@PathVariable(name = "idUsuario") Integer idUsuario, @Valid @RequestBody UsuarioCreateDTO usuario) throws RegraDeNegocioException {
        log.info("Editando o Usuário...");
        UsuarioDTO user = usuarioService.atualizar(idUsuario, usuario);
        log.info("Usuário editado com sucesso!");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
