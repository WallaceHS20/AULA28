package br.edu.fatec.sjc;

import java.util.List;
import java.util.Collections;


public class NumberAscOrder {
    private CustomStack<? extends Comparable> stack;


    public NumberAscOrder(CustomStack<? extends Comparable> stack) {
        this.stack = stack;
    }

    public List<? extends Comparable> sort() {
        List<? extends Comparable> list = stack.toList(); // Presumindo que CustomStack tenha um método toList()
        Collections.sort(list);
        return list;
    }
}
