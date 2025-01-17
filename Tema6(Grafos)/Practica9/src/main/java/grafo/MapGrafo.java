package grafo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapGrafo<V> extends AbstractGrafo<V> {
	private Map<V, Set<V>> grafo;

	public MapGrafo() {
		grafo = new HashMap<>();
	}

	@Override
	public void addVertice(V v) {
		if (!existeVertice(v)) {
			grafo.put(v, new HashSet<>());
		}
	}

	@Override
	public boolean existeVertice(V v) {
		return grafo.containsKey(v);
	}

	@Override
	public void borraVertice(V v) {
		if (existeVertice(v)) {
			Set<V> vecinos = grafo.remove(v);
			for (V vecino : vecinos) {
				grafo.get(vecino).remove(v);
			}
		}
	}

	@Override
	public void addArista(Arista<V> a) {
		if (existeVertice(a.origen()) && existeVertice(a.destino())) {
			grafo.get(a.origen()).add(a.destino());
			grafo.get(a.destino()).add(a.origen());
		} else {
			throw new IllegalArgumentException("Uno o ambos vértices no existen");
		}
	}

	@Override
	public boolean existeArista(Arista<V> a) {
		return grafo.get(a.origen()).contains(a.destino());
	}

	@Override
	public void borraArista(Arista<V> a) {
		if (existeArista(a)) {
			grafo.get(a.origen()).remove(a.destino());
			grafo.get(a.destino()).remove(a.origen());
		}
	}

	@Override
	public Set<V> vecinos(V v) {
		return grafo.getOrDefault(v, new HashSet<>());
	}

	@Override
	public Set<V> vertices() {
		return new HashSet<>(grafo.keySet());
	}

	@Override
	public Set<Arista<V>> aristas() {
		Set<Arista<V>> aristas = new HashSet<>();
		for (V v : grafo.keySet()) {
			for (V vecino : grafo.get(v)) {
				if (v.hashCode() < vecino.hashCode()) {
					aristas.add(new Arista<>(v, vecino));
				}
			}
		}
		return aristas;
	}
}
