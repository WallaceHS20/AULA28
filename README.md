# CLASSE NumberAscOrder
~~~~java
package br.edu.fatec.sjc;
import java.util.ArrayList;
import java.util.List;

public class NumberAscOrder<T extends Number> {
    private CustomStack<T> pilhaRecebida;
    private List<T> ordenar = new ArrayList<T>();
    public NumberAscOrder(CustomStack<T> pilha) {
        pilhaRecebida = pilha;
    }
    public List<T> sort() throws StackEmptyException {
        if (pilhaRecebida.size() == 0) {
            return ordenar;
        }
        Integer numeroDeElementos = pilhaRecebida.size();
        for (Integer i = 0; i < numeroDeElementos; i++) {
            T valor = pilhaRecebida.pop();
            ordenar.add(valor);
        }
        ordenar.sort(null);
        return ordenar;
    }
}
~~~~

# CLASSE Test

~~~~java
package br.edu.fatec.sjc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NumberAscOrderTest {
    private NumberAscOrder<Integer> pilhaOrdenada;
    Integer[] numeros = {6,2,1,4,3,5};
    Integer[] ordenados = {1,2,3,4,5,6};
    List<Integer> listaOrdenada= Arrays.asList(ordenados);
    List<Integer> listaVazia =new ArrayList<Integer>();
    Integer i=0;
    @Mock
    CustomStack<Integer> pilha;

    @BeforeEach
    public void setup() {
        pilhaOrdenada = new NumberAscOrder<Integer>(pilha);
        i=0;
    }

    @Test
    public void validarOrdenacao() throws StackEmptyException {
        Mockito.when(pilha.pop()).thenAnswer(invocations -> {
            Integer item = numeros[i];
            i++;
            return item;
        });
        Mockito.when(pilha.size()).thenReturn(6);
        assertEquals(pilhaOrdenada.sort(), listaOrdenada);
    }

    @Test
    public void validarPilhaVazia() throws StackEmptyException {
        Mockito.when(pilha.size()).thenReturn(0);;
        assertEquals(pilhaOrdenada.sort(), listaVazia);
    }
}




~~~~

![image](https://github.com/WallaceHS20/AULA28/assets/101594950/9aa1438b-db6f-4db7-a751-e3534433461c)


