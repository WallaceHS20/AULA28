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
