package grafo;

import java.util.Objects;

public abstract class AbstractGrafo<V> implements Grafo<V> {
	@Override
    public boolean equals(Object o) {
 
 	return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertices(), aristas());
    }

    @Override
    public String toString() {
        return "AbstractGrafo{" +
                "vertices=" + vertices() +
                ", aristas=" + aristas() +
                '}';
    }
    
}
