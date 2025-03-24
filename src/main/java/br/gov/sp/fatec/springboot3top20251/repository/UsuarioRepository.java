package br.gov.sp.fatec.springboot3top20251.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springboot3top20251.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    public Optional<Usuario> findByNome(String nomeUsuario);

    @Query("select u from Usuario u where u.nome = ?1")
    public Optional<Usuario> buscarUsuarioPorNome(String nome);

    public Optional<Usuario> findByNomeAndSenha(String nome, String senha);

    @Query("select u from Usuario u where u.nome = ?1 and u.senha = ?2")
    public Optional<Usuario> buscarUsuarioPorNomeESenha(String nome, String senha);

    public List<Usuario> findByNomeContainsIgnoreCase(String nome);

    @Query("select u from Usuario u where u.nome like %?1%")
    public List<Usuario> buscarUsuarioCujoNomeContenha(String nome);
    
}
