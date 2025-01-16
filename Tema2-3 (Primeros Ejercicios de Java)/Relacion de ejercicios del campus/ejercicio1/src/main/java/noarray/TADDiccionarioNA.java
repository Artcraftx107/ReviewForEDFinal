package noarray;

import java.util.NoSuchElementException;

public class TADDiccionarioNA <K, V>{
    private static class Nodo<K, V>{
        K clave;
        V value;
        Nodo<K, V> siguiente;

        Nodo(K clave, V value){
            this.clave=clave;
            this.value=value;
            this.siguiente=null;
        }
    }

    private Nodo<K, V> head;
    private int size;

    public TADDiccionarioNA(){
        head=null;
        size=0;
    }

    public void add(K clave, V value){
        Nodo<K, V> actual = head;
        boolean found = false;

        while(actual!=null && !found){
            if(actual.clave.equals(clave)){
                actual.value=value;
                found=true;
            }else{
                actual=actual.siguiente;
            }
        }

        if(!found){
            Nodo<K, V> newNode = new Nodo<>(clave, value);
            newNode.siguiente=head;
            head=newNode;
            size++;
        }
    }

    public V obtain(K clave){
        if(isEmpty()){
            throw new IllegalStateException("El diccionario esta vacio");
        }

        Nodo<K, V> actual = head;
        boolean found = false;
        while(actual!=null && !found){
            if(actual.clave.equals(clave)){
                found=true;
            }else{
                actual=actual.siguiente;
            }
        }

        if(!found){
            throw new NoSuchElementException("La clave "+clave+" no esta en el diccionario");
        }

        return actual.value;
    }

    public void delete(K clave){
        if(isEmpty()){
            throw new IllegalStateException("El diccionario esta vacio");
        }

        if(head.clave.equals(clave)){
            head=head.siguiente;
            size--;
        }else{
            Nodo<K, V> actual = head;
            boolean found = false;
            while(actual!=null && !found){
                if(actual.clave.equals(clave)){
                    found=true;
                    actual=actual.siguiente;
                    size--;
                }else{
                    actual=actual.siguiente;
                }
            }

            if(!found){
                throw new NoSuchElementException("La clave "+clave+" no esta en el diccionario");
            }
        }
    }

    public boolean containsKey(K clave){
        boolean found = false;
        Nodo<K, V> actual = head;
        while(actual!=null && !found){
            if(actual.clave.equals(clave)){
                found=true;
            }else{
                actual=actual.siguiente;
            }
        }

        return found; 
    }
    private boolean isEmpty() {
        return size==0;
    }
}
