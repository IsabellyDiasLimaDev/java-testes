package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import br.com.alura.leilao.util.builder.LeilaoBuilder;
import br.com.alura.leilao.util.builder.UsuarioBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

class LeilaoDaoTest {

    private LeilaoDao dao;
    private EntityManager em;

    @BeforeEach
    public void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new LeilaoDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    void deveriaCadastrarUmLeilao(){
        Usuario usuario = criarUsuario();
        Leilao leilao = new LeilaoBuilder()
                .comNome("Mochila")
                .comValorInicial(new BigDecimal("500"))
                .comData(LocalDate.now())
                .comUsuario(usuario).criar();
        leilao = dao.salvar(leilao);
        Leilao salvo = dao.buscarPorId(leilao.getId());
        Assertions.assertNotNull(salvo);

    }
    @Test
    void deveriaAtualizarUmLeilao(){
        Usuario usuario = criarUsuario();
        Leilao leilao = new LeilaoBuilder()
                .comNome("Mochila")
                .comValorInicial(new BigDecimal("500"))
                .comData(LocalDate.now())
                .comUsuario(usuario).criar();
        leilao = dao.salvar(leilao);

        leilao.setNome("Celular");
        leilao.setValorInicial(new BigDecimal("1000"));
        leilao = dao.salvar(leilao);

        Leilao salvo = dao.buscarPorId(leilao.getId());

        Assertions.assertEquals("Celular",salvo.getNome());
        Assertions.assertEquals(new BigDecimal("1000"), salvo.getValorInicial());

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
