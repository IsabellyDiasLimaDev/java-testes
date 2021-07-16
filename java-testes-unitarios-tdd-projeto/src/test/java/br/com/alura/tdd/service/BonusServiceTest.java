package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BonusServiceTest {

    private BonusService service;
    private Funcionario funcionario;

    @BeforeEach
    public void inicializar(){
        this.service = new BonusService();
        this.funcionario = new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("1000000"));
    }

    @AfterEach
    public void finalizar(){
        System.out.println("fim");
    }

    @BeforeAll
    public static void antesDeTodos(){
        System.out.println("ANTES DE TODOS");
    }
    @AfterAll
    public static void depoisDeTodos(){
        System.out.println("DEPOIS DE TODOS");
    }

    @Test
    void calcularbonusDeveriaLancarExceptionParaCalculoSuperiorAMil() {
        //var bonus = service.calcularBonus(new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("1000000")));
        //assertEquals(BigDecimal.ZERO, bonus);
        assertThrows(IllegalArgumentException.class,
         () -> service.calcularBonus(funcionario));
        // outra maneira
//        try {
//            service.calcularBonus(new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("1000000")));
//            fail("Não deu a exception!!!");
//        } catch (Exception e) {
//            assertEquals("Funcionario com salario muito alto! Não pode receber o bonus!!!!!", e.getMessage());
//        }
    }

    @Test
    void bonusDeveriaSerDezPorCento() {
        funcionario.setSalario(new BigDecimal("2500"));
        var bonus = service.calcularBonus(funcionario);
        assertEquals(new BigDecimal("250.00"), bonus);

    }

    @Test
    void bonusDeveriaSerDezPorCentoParaDezMil() {
        funcionario.setSalario(new BigDecimal("10000"));
        var bonus = service.calcularBonus(funcionario);
        assertEquals(new BigDecimal("1000.00"), bonus);

    }
}