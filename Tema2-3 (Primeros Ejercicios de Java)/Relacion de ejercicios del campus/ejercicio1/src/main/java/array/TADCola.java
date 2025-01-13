package array;

public class TADCola<E> {
    private static final int INT_CAP = 10;
    private E[] elementos;
    private int head;
    private int size;
    private int tail;

    public TADCola(){
        elementos = (E[]) new Object[INT_CAP];
        head=0;
        size=0;
        tail=0;
    }

    public void putIn(E item){
        if(size==elementos.length){
            resize(elementos.length*2); 
        }

        elementos[tail] = item;
        tail=(tail+1)% elementos.length;
        size++;
    }

    public E removeFromQueue(){
        if(isEmpty()){
            throw new IllegalStateException("La cola esta vacia");
        }

        E item = elementos[head];
        elementos[head] = null;
        head=(head+1) % elementos.length;
        size--;

        if(size > 0 && size <= elementos.length/4 && elementos.length / 2 >= INT_CAP){
            resize(elementos.length/2);
        }

        return item;
    }

    public E getFirst(){
        if(isEmpty()){
            throw new IllegalStateException("La cola esta vacia");
        }
        return elementos[head];
    }

    private boolean isEmpty() {
        return size==0;
    }

    private void resize(int i) {
        E[] newArray = (E[]) new Object[i];

        for(int j = 0; j<size; j++){
            newArray[j] = elementos[(head+j)% elementos.length];
        }

        elementos=newArray;
        head=0;
        tail=size;
    }
}
