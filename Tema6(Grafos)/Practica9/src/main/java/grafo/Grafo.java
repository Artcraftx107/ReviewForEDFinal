package grafo;

import java.util.Set;

public interface Grafo<V> {
	public record Arista<V>( V origen, V destino ){}
	
	void addVertice( V v );
	boolean existeVertice( V v );
	void borraVertice( V v );
	void addArista( Arista<V> a );
	boolean existeArista( Arista<V> a );
	void borraArista( Arista<V> a );
	Set<V> vecinos( V v );
	Set<V> vertices();
	Set<Arista<V>> aristas();	
}
