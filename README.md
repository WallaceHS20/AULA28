# CLASSE NumberAscOrder
~~~~java
package br.edu.fatec.sjc;

import java.util.List;
import java.util.Collections;


public class NumberAscOrder {
    private CustomStack<? extends Comparable> stack;


    public NumberAscOrder(CustomStack<? extends Comparable> stack) {
        this.stack = stack;
    }

    public List<? extends Comparable> sort() {
        List<? extends Comparable> list = stack.toList(); 
        Collections.sort(list);
        return list;
    }
}
~~~~

# CLASSE Test

~~~~java
package br.edu.fatec.sjc;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;

// Classe de testes para NumberAscOrder.
public class NumberAscOrderTest {

    private CustomStack<Integer> mockStack; // Mock de CustomStack para simular comportamentos.
    private NumberAscOrder numberAscOrder; // Instância de NumberAscOrder que será testada.

    // Método de configuração que é executado antes de cada teste.
    @Before
    public void setUp() {
        mockStack = mock(CustomStack.class); // Criação do mock para CustomStack.
        numberAscOrder = new NumberAscOrder(mockStack); // Inicializa NumberAscOrder com o mock.
    }

    // Teste para verificar a ordenação de uma pilha preenchida.
    @Test
    public void testSortWithFilledStack() {
        // Configuração do mock para retornar uma lista específica quando toList é chamado.
        when(mockStack.toList()).thenReturn(Arrays.asList(5, 3, 4, 1, 2, 6));
        List<Integer> sortedList = (List<Integer>) numberAscOrder.sort(); // Executa o método sort.
        // Verifica se cada elemento na lista ordenada é menor ou igual ao próximo.
        for (int i = 1; i < sortedList.size(); i++) {
            assertTrue(sortedList.get(i-1) <= sortedList.get(i));
        }
    }

    // Teste para verificar a ordenação de uma pilha vazia.
    @Test
    public void testSortWithEmptyStack() {
        // Configuração do mock para retornar uma lista vazia.
        when(mockStack.toList()).thenReturn(Arrays.asList());
        List<Integer> sortedList = (List<Integer>) numberAscOrder.sort(); // Executa o método sort.
        // Verifica se a lista resultante está vazia.
        assertTrue(sortedList.isEmpty());
    }

    // Teste para verificar a exceção quando um elemento é adicionado a uma pilha cheia.
    @Test
    public void testPushToFullStack() throws StackFullException {
        CalculableStrategy<Integer> strategy = element -> element; // Estratégia de cálculo simples que retorna o elemento.
        CustomStack<Integer> stack = new CustomStack<>(1, strategy); // Cria uma pilha com limite de um elemento.

        stack.push(1); // Adiciona um elemento à pilha.
        // Verifica se uma exceção é lançada ao tentar adicionar outro elemento à pilha cheia.
        assertThrows(StackFullException.class, () -> stack.push(2));
    }

~~~~

![image](https://github.com/WallaceHS20/AULA28/assets/101594950/0a9bc01c-b2ca-42e0-b17f-398e1f79e9e0)

