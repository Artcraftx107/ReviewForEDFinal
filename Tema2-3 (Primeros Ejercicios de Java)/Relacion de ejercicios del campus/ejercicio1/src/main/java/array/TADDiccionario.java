package array;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class TADDiccionario <K, V> {
    private static final int INT_CAP = 10;
    private K[] claves;
    private V[] values;
    private int size;

    public TADDiccionario(){
        claves=(K[]) new Object[INT_CAP];
        values=(V[]) new Object[INT_CAP];
        size=0;
    }

    public void add(K clave, V value){
        int pos = search(clave);
        if(pos!=-1){
            values[pos]=value;
        }else{
            if(size== claves.length){
                resize(claves.length*2);
            }
            claves[size]=clave;
            values[size]=value;
            size++;
        }
    }

    public V obtainValue(K clave){
        if(isEmpty()){
            throw new IllegalStateException("El diccionario esta vacio");
        }

        int pos = search(clave);
        if(pos==-1){
            throw new NoSuchElementException("La clave "+clave+" no esta anyadida al diccionario");
        }

        return values[pos];
    }

    public void remove(K clave){
        if(isEmpty()){
            throw new IllegalStateException("El diccionario esta vacio");
        }

        int pos = search(clave);
        if(pos==-1){
            throw new NoSuchElementException("La clave "+clave+" no esta anyadida al diccionario");
        }

        System.arraycopy(claves, pos+1, claves, pos, size-pos-1);
        System.arraycopy(claves, pos+1, values, pos, size-pos-1);
        claves[--size] = null;
        values[size] = null;
    }

    private int search(K clave) {
        int pos = 0;
        boolean found = false;
        while(pos<size && !found){
            if(claves[pos].equals(clave)){
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

    public boolean containsKey(K clave){
        return search(clave)!=-1;
    }

    private boolean isEmpty() {
        return size==0;
    }

    private void resize(int newCap){
        claves= Arrays.copyOf(claves, newCap);
        values=Arrays.copyOf(values, newCap);
    }
}
