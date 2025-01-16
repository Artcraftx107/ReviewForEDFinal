package array;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class TADLista<E> {
    private static final int INT_CAP = 10;
    private E[] elements;
    private int size;

    public TADLista(){
        elements=(E[]) new Object[INT_CAP];
        size=0;
    }

    public void add(E item){
        if(size == elements.length){
            resize(elements.length*2);
        }

        elements[size++]=item;
    }

    private void resize(int i) {
        elements = Arrays.copyOf(elements, i);
    }

    public void remove(E item){
        if(isEmpty()){
            throw new IllegalStateException("La lista esta vacia");
        }
        int pos = search(item);

        System.arraycopy(elements, pos+1, elements, pos, size-pos-1);
        elements[--size]=null;
    }

    private int search(E item) {
        if(isEmpty()){
            throw new IllegalStateException("La lista esta vacia");
        }

        boolean found = false;

        int currentPos = 0;

        while(currentPos<size && !found){
            if(elements[currentPos].equals(item)){
                found=true;
            }else{
                currentPos++;
            }
        }

        if(!found){
            throw new NoSuchElementException("El elemento "+item+" no esta en la lista");
        }

        return currentPos;
    }


    private boolean isEmpty() {
        return size==0;
    }

    public boolean contiene(E item){
        return search(item)!=-1;
    }

    public void eliminarValor(E item){
        if(contiene(item)){
            remove(item);
        }
    }

    public void eliminarTodo(){
        for(int i = size; i>=0; i--){
            remove(elements[i]);
        }
    }

    public void invertir(){
        for(int i = 0; i<size; i++){
            E temp = elements[i];
            elements[i] = elements[size-i-1];
            elements[size-i-1] = temp; 
        }
    }
}
