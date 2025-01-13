package noarray;

public class TADColaNA<E> {
    private static class Nodo<E>{
        E dato;
        Nodo<E> siguiente;

        Nodo(E data){
            this.dato=data;
            this.siguiente=null;
        }
    }

    private Nodo<E> head;
    private Nodo<E> tail;
    private int size;

    public TADColaNA(){
        head=null;
        size=0;
        tail=null;
    }

    public void putIn(E item){
        Nodo<E> newNode = new Nodo<>(item);

        if(isEmpty()){
            head=newNode;
        }else{
            tail.siguiente=newNode;
        }
        tail=newNode;
        size++;
    }

    public E removeFromQueue(){
        if(isEmpty()){
            throw new IllegalStateException("La cola esta vacia");
        }

        E item = head.dato;
        head=head.siguiente;
        size--;

        if(isEmpty()){
            tail=null;
        }
        return item;
    }

    public E getFirst(){
        if(isEmpty()){
            throw new IllegalStateException("La cola esta vacia");
        }

        return head.dato;
    }

    private boolean isEmpty() {
        return size==0;
    }

    public int getSize() {
        return size;
    }
}
