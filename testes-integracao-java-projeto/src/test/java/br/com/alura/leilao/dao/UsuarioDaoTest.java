package br.com.alura.leilao.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.alura.leilao.util.builder.UsuarioBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;

class UsuarioDaoTest {

    private UsuarioDao dao;
    private EntityManager em;

    @BeforeEach
    public void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new UsuarioDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    void deveriaEncontrarUsuarioCadastrado() {
        Usuario usuario = criarUsuario();
        Usuario encontrado = this.dao.buscarPorUsername(usuario.getNome());
        Assert.assertNotNull(encontrado);
    }

    @Test
    void naoDeveriaEncontrarUsuarioNaoCadastrado() {
        criarUsuario();
        Assert.assertThrows(NoResultException.class, () -> this.dao.buscarPorUsername("beltrano"));
    }

    @Test
    void deveriaRemoverUsuario(){
        Usuario usuario = criarUsuario();
        dao.deletar(usuario);
        Assert.assertThrows(NoResultException.class, () -> this.dao.buscarPorUsername(usuario.getNome()));
    }

    private Usuario criarUsuario() {
        Usuario usuario = new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@email.com")
                .comSenha("1234567").criar();

        em.persist(usuario);
        return usuario;
    }
}
