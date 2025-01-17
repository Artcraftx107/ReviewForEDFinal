package ejercicio8;

public class TADPila<E> {
    private TADLista<E> lista;

    public TADPila(){
        lista=new TADLista<>();
    }

    public void push(E item){
        lista.add(item);
    }

    public E pop(){
        if(lista.isEmpty()){
            throw new IllegalStateException("La pila esta vacia");
        }

        E item = lista.elements[lista.size-1];
        lista.remove(item);
        return item;
    }

    public E peek(){
        if(lista.isEmpty()){
            throw new IllegalStateException("La pila esta vacia");
        }
        return lista.elements[lista.size-1];
    }

    public boolean isEmpty(){
        return lista.isEmpty();
    }
}
