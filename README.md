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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NumberAscOrderTest {

    private CustomStack<Integer> mockStack; // Mock de CustomStack para simular comportamentos.
    private NumberAscOrder numberAscOrder; // Instância de NumberAscOrder que será testada.
    private Random random = new Random();

    // Método de configuração que é executado antes de cada teste.
    @Before
    public void setUp() {
        mockStack = mock(CustomStack.class); // Criação do mock para CustomStack.
        numberAscOrder = new NumberAscOrder(mockStack); // Inicializa NumberAscOrder com o mock.
    }

    // VERIFICAR SE LISTA PREENCHIDA CORRETAMENTE COM O SIZE
    @Test
    public void testSortWithFilledStack() {
        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            randomNumbers.add(random.nextInt(100));  // Gera um número aleatório entre 0 e 99.
        }

        // Configura o mock para retornar a lista de números aleatórios.
        when(mockStack.toList()).thenReturn(randomNumbers);

        System.out.println("Antes da ordenação: " + mockStack.toList());

        List<Integer> sortedList = (List<Integer>) numberAscOrder.sort();

        System.out.println("Após a ordenação: " + sortedList);

        // Verifica se a lista está ordenada corretamente.
        for (int i = 1; i < sortedList.size(); i++) {
            assertTrue(sortedList.get(i - 1) <= sortedList.get(i));
        }
    }


    // TESTE PILHA VAZIA
    @Test
    public void testSortWithEmptyStack() {
        // Configuração MOCK LISTA VAZIA
        when(mockStack.toList()).thenReturn(Arrays.asList());
        List<Integer> sortedList = (List<Integer>) numberAscOrder.sort(); // EXECUTA O SORT
        // LISTA VAZIA ?
        assertTrue(sortedList.isEmpty());
    }

    // TESTE DE VERIFICAÇÃO SE PILHA ESTÁ CHEIA
    @Test
    public void testPushToFullStack() throws StackFullException {
        CalculableStrategy<Integer> strategy = element -> element; // Estratégia de cálculo simples que retorna o elemento.
        CustomStack<Integer> stack = new CustomStack<>(1, strategy); // PILHA COM 1 ELEMENTO

        stack.push(1);
        // TENTATIVA DE ADICIONAR COM PILHA CHEIA
        assertThrows(StackFullException.class, () -> stack.push(2));
    }
}


~~~~

![image](https://github.com/WallaceHS20/AULA28/assets/101594950/0a9bc01c-b2ca-42e0-b17f-398e1f79e9e0)

