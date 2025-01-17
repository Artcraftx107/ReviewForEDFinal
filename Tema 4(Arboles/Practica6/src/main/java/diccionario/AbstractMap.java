/*******************************
 * Copyright (C) [2024] [J.L. Triviño]
 *
 * Este programa es software libre: puedes redistribuirlo y/o modificarlo 
 * bajo los términos de la Licencia Pública General de GNU según lo publicado 
 * por la Free Software Foundation, ya sea la versión 3 de la Licencia, o 
 * (a tu elección) cualquier versión posterior.
 *
 * Este programa se distribuye con la esperanza de que sea útil, pero 
 * SIN NINGUNA GARANTÍA; ni siquiera la garantía implícita de 
 * COMERCIABILIDAD o IDONEIDAD PARA UN PROPÓSITO PARTICULAR. 
 * Consulta la Licencia Pública General de GNU para obtener más detalles.
 *
 * Deberías haber recibido una copia de la Licencia Pública General de GNU
 * junto con este programa. Si no es así, consulta <http://www.gnu.org/licenses/>.
 */

package diccionario;

import java.util.Arrays;
import java.util.Objects;

public abstract class AbstractMap<K, V> implements Map<K, V> {
	protected int size = 0; // Tamaño del mapa

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int size() {
		return size;
	}


	// Devuelve un array con los pares de clave, valor del diccionario
	// ordenador por clave
	protected abstract Map.Entry<K,V>[] toArray();

	public String toString() {
		return getClass().getName()+"{" +
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
		if (this == obj) {
			return true; // Si son la misma referencia, son iguales
		}
		if (!(obj instanceof Map)) {
			return false; // Si no es un mapa, no son iguales
		}
		Map<K,V> other = (Map<K, V>) obj;

		// Compara tamaños primero
		if (this.size() != other.size()) {
			return false;
		}

		// Verifica que todas las entradas coincidan
		boolean same = true;
		for (Map.Entry<K, V> entry : this.entrySet()) {
			K key = entry.key();
			V value = entry.value();

			// Verifica si la clave está presente y si los valores son iguales
			if (!other.hasEntry(key)) {
				same = false;
			}
			if(!other.get(key).equals(value)){
				same = false;
			}
		}
		return same;
	}
}
