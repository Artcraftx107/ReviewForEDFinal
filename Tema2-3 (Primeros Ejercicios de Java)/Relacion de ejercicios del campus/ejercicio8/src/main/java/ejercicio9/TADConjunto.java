package ejercicio9;

public class TADConjunto<E> {
    private TADListaEX9<E> listaEX9;

    public TADConjunto(){
        listaEX9 = new TADListaEX9<>();
    }

    public void add(E item){
        if(!listaEX9.contiene(item)){
            listaEX9.add(item);
        }
    }

    public void remove(E item){
        if(listaEX9.contiene(item)){
            listaEX9.eliminarValor(item);
        }
    }

    public boolean contains(E item){
        return listaEX9.contiene(item);
    }

    public boolean isEmpty() { return listaEX9.isEmpty(); }
    public void eliminarTodo() { listaEX9.eliminarTodo(); }
    public void invertir() { listaEX9.invertir(); }
}
