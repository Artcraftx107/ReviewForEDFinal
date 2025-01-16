package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class TADMulticonjunto<E> {
    private static final int INT_CAP=10;
    private E[] elements;
    private int[] counts;
    private int size;

    public TADMulticonjunto(){
        elements=(E[]) new Object[INT_CAP];
        counts=new int[INT_CAP];
        size=0;
    }

    public void add(E item){
        int pos = search(item);
        if(pos!=-1){
            counts[pos]++;
        }else{
            if(size== elements.length){
                resize(elements.length*2);
            }
            elements[size]=item;
            counts[size]=1;
            size++;
        }
    }

    public void remove(E item){
        if(isEmpty()){
            throw new IllegalStateException("El multiconjunto esta vacio");
        }

        int pos = search(item);
        if(pos==-1){
            throw new NoSuchElementException("El elemento "+item+" no esta en el multiconjunto");
        }

        if(counts[pos]>1){
            counts[pos]--;
        }else{
            System.arraycopy(elements, pos+1, elements, pos, size-pos-1);
            System.arraycopy(counts, pos+1, counts, pos, size-pos-1);
            elements[--size]=null;
            counts[size]=0;
        }
    }

    public boolean contains(E item){
        return search(item)!=-1;
    }

    public int count(E item){
        int pos = search(item);
        int aux = 0;
        if(pos!=-1){
            aux=counts[pos];
        }
        return aux;
    }

    private int search(E item) {
        int pos = 0;
        boolean found = false;
        while(pos<size && !found){
            if(elements[pos].equals(item)){
                found=true;
            }else{
                pos++;
            }
        }
        if(!found){
            pos=-1;
        }
        return pos;
    }

    private boolean isEmpty(){
        return size==0;
    }

    private void resize(int newCap){
        elements= Arrays.copyOf(elements, newCap);
        counts=Arrays.copyOf(counts, newCap);
    }

    public TADConjunto<E> toConjunto(){
        Set<E> conjunto = new HashSet<>();
        for(int i = 0; i<size; i++){
            conjunto.add(elements[i]);
        }
        TADConjunto<E> resultado = new TADConjunto<>();
        for(E elemento : conjunto){
            resultado.add(elemento);
        }

        return resultado;
    }
}
