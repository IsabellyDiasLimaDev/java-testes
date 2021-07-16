import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
    @Test
    public void deveriaSomarDoisNumerosPositivos() {
        var calc = new Calculadora();
        int soma = calc.soma(10, 3);

        Assertions.assertEquals(13, soma);
    }
}
