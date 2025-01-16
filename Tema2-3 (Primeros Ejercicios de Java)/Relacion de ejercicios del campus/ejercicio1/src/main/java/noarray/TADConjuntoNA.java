package noarray;

public class TADConjuntoNA <E>{
    public static class Nodo<E>{
        E dato;
        Nodo<E> siguiente;

        Nodo(E dato){
            this.dato=dato;
            this.siguiente=null;
        }
    }

    private Nodo<E> head;
    private int size;

    public TADConjuntoNA(){
        head=null;
        size=0;
    }

    public void add(E item){
        if(!contains(item)){
            Nodo<E> newNodo = new Nodo<>(item);
            newNodo.siguiente = head;
            head = newNodo;
            size++;
        }
    }

    public void remove(E item){
        if(isEmpty()){
            throw new IllegalStateException("El conjunto esta vacio");
        }

        if(head.dato.equals(item)){
            head = head.siguiente;
            size--;
        }else{
            Nodo<E> actual = head;
            boolean found = false;

            while(actual.siguiente != null && !found){
                if(actual.siguiente.dato.equals(item)){
                    found=true;
                }else{
                    actual=actual.siguiente;
                }
            }

            if(found){
                actual.siguiente = actual.siguiente.siguiente;
                size--;
            }
        }
    }

    private boolean contains(E item){
        Nodo<E> actual = head;
        boolean found = false;
        while(actual!=null && !found){
            if(actual.dato.equals(item)){
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
