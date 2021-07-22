import br.com.alura.leilao.dao.LeilaoDao;
import br.com.alura.leilao.model.Leilao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class HelloWorldMockito {
    @Test
    void hello(){
        LeilaoDao mock = Mockito.mock(LeilaoDao.class);
        List<Leilao> todos = mock.buscarTodos();
        Assertions.assertTrue(todos.isEmpty());
    }
}
