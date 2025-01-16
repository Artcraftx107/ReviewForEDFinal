package noarray;

public class TADMulticonjuntoNA<E> {
    private static class Nodo<E>{
        E dato;
        int cont;
        Nodo<E> siguiente;

        Nodo(E dato){
            this.dato=dato;
            this.cont=1;
            this.siguiente=null;
        }
    }

    private Nodo<E> head;
    private int size;

    public TADMulticonjuntoNA(){
        head=null;
        size=0;
    }

    public void add(E item){
        if (contains(item)) {
            Nodo<E> actual = head;
            boolean found = false;
            while(actual!=null && !found){
                if(actual.dato.equals(item)){
                    actual.cont++;
                    found=true;
                }
                actual=actual.siguiente;
            }
        }else{
            Nodo<E> newNode = new Nodo<>(item);
            newNode.siguiente=head;
            head=newNode;
            size++;
        }
    }

    public void remove(E item){
        if(isEmpty()){
            throw new IllegalStateException("El multiconjunto esta vacio");
        }

        if(head.dato.equals(item)){
            if(head.cont>1){
                head.cont--;
            }else{
                head=head.siguiente;
                size--;
            }
        }else{
            Nodo<E> actual = head;
            boolean found = false;
            while(actual!=null && !found){
                if(actual.dato.equals(item)){
                    if(actual.cont>1){
                        actual.cont--;
                    }else{
                        actual=actual.siguiente;
                        size--;
                    }
                    found=true;
                }
                actual=actual.siguiente;
            }
        }
    }

    private boolean isEmpty() {
        return size==0; 
    }

    public boolean contains(E item){
        Nodo<E> actual = head;
        boolean found = false;
        while(actual!=null && !found){
            if(actual.dato.equals(item)){
                found=true;
            }
            actual=actual.siguiente;
        }
        return found;
    }

    public int counter(E item){
        Nodo<E> actual = head;
        int countAux = 0;
        while(actual!=null){
            if(actual.dato.equals(item)){
                countAux=actual.cont;
            }
            actual=actual.siguiente;
        }
        return countAux;
    }
}
