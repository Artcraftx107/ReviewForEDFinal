package noarray;

import java.util.NoSuchElementException;

public class TADListaNA<E> {
    private static class Nodo<E>{
        E dato;
        Nodo<E> siguiente;

        Nodo(E dato){
            this.dato=dato;
            this.siguiente=null;
        }
    }

    private Nodo<E> head;
    private int size;

    public TADListaNA(){
        head=null;
        size=0;
    }

    public void addAtEnd(E item){
        Nodo<E> newNodo = new Nodo<>(item);

        if(head==null){
            head=newNodo;
        }else{
            Nodo<E> actual = head;
            while(actual.siguiente!=null){
                actual=actual.siguiente;
            }
            actual.siguiente=newNodo;
        }
        size++;
    }

    public boolean remove(E item){
        boolean found = false;
        if(head==null){
            throw new IllegalStateException("La lista esta vacia");
        }

        if(head.dato.equals(item)){
            head=head.siguiente;
            size--;
        }else{
            Nodo<E> actual = head;
            while(actual.siguiente!=null && !found){
                if(actual.dato.equals(item)){
                    actual=actual.siguiente;
                    size--;
                    found=true;
                }
                actual=actual.siguiente;
            }
        }

        if(!found){
            throw new NoSuchElementException("El elemento "+item+" no esta en la lista");
        }

        return found; 
    }
}
