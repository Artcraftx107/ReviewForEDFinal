package array;

public class TADColaPrioridad<E> {
    private static final int INT_CAP = 10;
    private ElementoConPrioridades<E>[] elementoConPrioridades;
    private int size;

    private static class ElementoConPrioridades<E>{
        E dato;
        int prioridad;

        ElementoConPrioridades(E dato, int prioridad){
            this.dato = dato;
            this.prioridad=prioridad;
        }
    }

    public TADColaPrioridad(){
        elementoConPrioridades = (ElementoConPrioridades<E>[]) new ElementoConPrioridades[INT_CAP];
        size=0;
    }

    public TADColaPrioridad<E> obtainPriority(int priority){
        TADColaPrioridad<E> resultante = new TADColaPrioridad<>();
        for(int i = 0; i<size; i++){
            if(elementoConPrioridades[i].prioridad==priority){
                resultante.insertInOrder(elementoConPrioridades[i].dato, priority);
            }
        }
        return resultante;
    }

    public void insertInOrder(E item, int prioridad){
        if(size == elementoConPrioridades.length){
            resize(elementoConPrioridades.length*2); 
        }

        ElementoConPrioridades<E> nuevoElem = new ElementoConPrioridades<>(item, prioridad);

        int j = size-1;
        while(j>=0 && elementoConPrioridades[j].prioridad>prioridad){
            elementoConPrioridades[j+1]=elementoConPrioridades[j];
            j--;
        }

        elementoConPrioridades[j+1] = nuevoElem;
        size++;
    }

    public E removeHighestPriority(){
        if(isEmpty()){
            throw new IllegalStateException("la cola esta vacia");
        }

        E item = elementoConPrioridades[0].dato;

        for(int i = 0; i<size; i++){
            elementoConPrioridades[i-1]=elementoConPrioridades[i];
        }

        elementoConPrioridades[--size]=null;

        return item;
    }

    public E getHighestPriority(){
        if(isEmpty()){
            throw new IllegalStateException("La cola esta vacia");
        }

        return elementoConPrioridades[0].dato;
    }

    private boolean isEmpty() {
        return size==0;
    }

    private void resize(int i) {
        ElementoConPrioridades<E>[] newArray = (ElementoConPrioridades<E>[]) new ElementoConPrioridades[i];

        System.arraycopy(elementoConPrioridades, 0, newArray, 0, size);

        elementoConPrioridades=newArray;
    }
}
