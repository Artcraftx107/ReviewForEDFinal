package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class TADConjunto<E> {
    private static int INT_CAP = 10;
    private E[] elementos;
    private int size;

    public TADConjunto(){
        elementos=(E[]) new Object[INT_CAP];
        size=0;
    }

    public void add(E item){
        if(!isIn(item)){
            if(size==elementos.length){
                resize(elementos.length*2);
            }

            elementos[size++] = item;
        }
    }

    public void remove(E item) {
        if (isEmpty()) {
            throw new IllegalStateException("El conjunto esta vacio");
        }
        int pos = search(item);
        if(!isIn(item)){
            throw new NoSuchElementException("El elemento "+item+" no esta en el conjunto");
        }

        System.arraycopy(elementos, pos+1, elementos, pos, size-pos-1);
    }

    private int search(E item) {
        int pos = 0;
        boolean found = false;
        while(pos<size && !found){
            if(elementos[pos].equals(item)){
                found = true;
            }else{
                pos++;
            }
        }

        if(!found){
            pos=-1;
        }
        return pos;
    }

    private boolean isEmpty() {
        return size==0;
    }

    private boolean isIn(E item) {
        return search(item)!=-1;
    }

    private void resize(int i) {
        elementos = Arrays.copyOf(elementos, i);
    }

    public TADConjunto<E> diferenciaSimetrica(TADConjunto<E> otherSet){
        Set<E> conjunto1 = new HashSet<>(Arrays.asList(Arrays.copyOf(elementos, size)));
        Set<E> conjunto2 = new HashSet<>(Arrays.asList(otherSet.elementos));

        conjunto1.removeAll(conjunto2);
        conjunto2.removeAll(conjunto1);

        TADConjunto<E> resultado = new TADConjunto<>();

        for(E elemento : conjunto1){
            resultado.add(elemento);
        }
        for(E elemento2 : conjunto2){
            resultado.add(elemento2);
        }
        return resultado; 
    }
}
