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
import java.util.Set;

public interface Map<K, V> {
	public record Entry<K, V>(K key, V  value) {}
	
	V put(K key, V value);
	boolean hasEntry( K key );
	V get(K key);
	V remove(K key);
	void clear();
	int size();
	boolean isEmpty();
	Set<Entry<K, V>> entrySet();
}