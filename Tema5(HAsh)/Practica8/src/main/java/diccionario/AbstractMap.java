package diccionario;

import java.util.Objects;
import java.util.Arrays;

public abstract class AbstractMap<K, V> implements Map<K, V> {
	protected int size = 0; // Tamaño del mapa

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	// Devuelve un array con los pares de clave, valor del diccionario ordenado por clave
	protected abstract Map.Entry<K, V>[] toArray();

	public String toString() {
		return getClass().getName() + "{" +
				"elements=" + Arrays.toString(toArray()) +
				", size=" + size +
				'}';
	}

	@Override
	public int hashCode() {
		var elements = toArray();
		int result = Objects.hash(size);
		result = 31 * result + Arrays.hashCode(elements);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		AbstractMap<?, ?> other = (AbstractMap<?, ?>) obj;
		return size == other.size && Arrays.equals(toArray(), other.toArray());
	}
}

