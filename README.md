# CLASSE CustomStack
~~~~java
package br.edu.fatec.sjc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CustomStack<T extends  Number> {
    private final Integer limit;
    private int index = 0;
    private List<T> elements;
    private CalculableStrategy<T> calculableStrategy;
    Integer a;
    public CustomStack(int pLimit, CalculableStrategy<T> pCalculableStratey) {
            this.limit = pLimit;
            this.elements = new ArrayList<>();
            this.calculableStrategy = pCalculableStratey;
    }

    public void push(T element) throws StackFullException {
        if(this.size() == this.limit) {
            throw new StackFullException();
        }
        this.elements.add(calculableStrategy.calculateValue(element));
        ++index;
    }

    public T pop() throws StackEmptyException {
        if(this.isEmpty()) {
            throw new StackEmptyException();
        }
        return this.elements.remove(--index);
    }

    public List<T> toList() {
        return new ArrayList<>(this.elements);
    }

    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    public T top() {
        return this.elements.get((index-1));
    }

    public int size() {
        return this.elements.size();
    }
}
~~~~

# CLASSE Test

~~~~java
package br.edu.fatec.sjc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StackTest {

    private CustomStack<Double> stack;
    @Mock
    CalculableStrategy<Double> calculableStrategy;

    @BeforeEach
    public void setup() {
        stack = new CustomStack<>(5, calculableStrategy);
    }

    @Test
    public void validatePushOneElementInStack() {
        Double elementValue = 5.0;
        Double value = 0.0;
        Mockito.when(
                calculableStrategy.calculateValue(Mockito.anyDouble())
        ).thenReturn(5.0);
        try {
            stack.push(elementValue);
            assertFalse(stack.isEmpty());
            assertEquals(elementValue, stack.top());
            assertEquals(1, stack.size());
            value = stack.pop();
        } catch(StackEmptyException | StackFullException ex) {
            fail();
        }
        assertEquals(elementValue, value);
        assertEquals(0.0, stack.size());
        Mockito.verify(calculableStrategy, Mockito.times(1))
                .calculateValue(Mockito.anyDouble());
    }

    @Test
    public void validatePushOneElementWithNullValueInStack() {
        Mockito.when(calculableStrategy.calculateValue(Mockito.isNull()))
                .thenThrow((NullPointerException.class));

        Assertions.assertThrows(NullPointerException.class, () -> stack.push(null));

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());

        Mockito.verify(calculableStrategy, Mockito.times(1))
                .calculateValue(Mockito.isNull());
    }

    @Test
    public void validatePushTwoElementAndRemoveOneOfThenInStack() {
        Double secondElementValue = 10.0;
        Double value = 0.0;
        Mockito.when(
                calculableStrategy.calculateValue(Mockito.anyDouble())
        ).thenReturn(10.0);
        try {
            stack.push(5.0);
            stack.push(secondElementValue);
            assertFalse(stack.isEmpty());
            assertEquals(secondElementValue, stack.top());
            assertEquals(2, stack.size());
            value = stack.pop();
        } catch(StackEmptyException | StackFullException ex) {
            fail();
        }
        assertEquals(secondElementValue, value);
        assertEquals(1, stack.size());
    }

    @Test
    public void validatePushOneElementAndRemoveTwoElementsInStack() {
        Double secondElementValue = 10.0;
        Mockito.when(
                calculableStrategy.calculateValue(Mockito.anyDouble())
        ).thenReturn(10.0);
        try {
            stack.push(secondElementValue);
            stack.pop();
        } catch(StackEmptyException | StackFullException ex) {
            fail();
        }
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertThrows(StackEmptyException.class, () -> {
            stack.pop();
        });
    }

    @Test
    public void validatePushTwoElementForStackWithLimitSizeEqualOne() {
        this.stack = new CustomStack<>(1, calculableStrategy);
        Mockito.when(
                calculableStrategy.calculateValue(Mockito.anyDouble())
        ).thenReturn(20.0);
        try {
            stack.push(5.0);
        } catch(StackFullException ex) {
            fail();
        }
        assertThrows(StackFullException.class, () -> {
            stack.push(10.0);
        });
    }
}

~~~~

![image](https://github.com/WallaceHS20/AULA28/assets/101594950/0a9bc01c-b2ca-42e0-b17f-398e1f79e9e0)

