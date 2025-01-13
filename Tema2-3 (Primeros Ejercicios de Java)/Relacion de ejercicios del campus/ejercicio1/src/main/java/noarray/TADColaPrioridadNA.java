package noarray;

import array.TADColaPrioridad;

public class TADColaPrioridadNA<E> {
    private static class Nodo<E>{
        E dato;
        int prioridad;
        Nodo<E> siguiente;

        Nodo(E dato, int prioridad){
            this.dato=dato;
            this.prioridad=prioridad;
            this.siguiente=null;
        }
    }

    private Nodo<E> head;

    public TADColaPrioridadNA(){
        head=null;
    }

    public void insertInOrder(E dato, int prioridad){
        Nodo<E> newNode = new Nodo<>(dato, prioridad);

        if(isEmpty() || head.prioridad>prioridad){
            newNode.siguiente=head;
            head=newNode;
        }else{
            Nodo<E> actual = head;
            while(actual.siguiente!=null && actual.siguiente.prioridad<=prioridad){
                actual=actual.siguiente;
            }
            newNode.siguiente=actual.siguiente;
            actual.siguiente=newNode;
        }
    }

    public E removeHighestPriority(){
        if(isEmpty()){
            throw new IllegalStateException("La cola esta vacia");
        }

        E item = head.dato;
        head=head.siguiente;
        return item;
    }

    public E viewHighestPriority(){
        if(isEmpty()){
            throw new IllegalStateException("La cola esta vacia");
        }
        return head.dato;
    }

    private boolean isEmpty() {
        return head==null;
    }
}
