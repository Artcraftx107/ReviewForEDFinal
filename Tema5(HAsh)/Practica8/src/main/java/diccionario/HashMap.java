package diccionario;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HashMap<K, V> extends AbstractMap<K, V> {
    private static final int DEFAULT_CAPACITY = 100;
    private static final float MAX_LOAD_FACTOR = 0.75f;
    private static final float MIN_LOAD_FACTOR = 0.25f;

    private K[] keys;
    private V[] values;
    private int capacity;
    private int threshold;

    @SuppressWarnings("unchecked")
    public HashMap() {
        capacity = DEFAULT_CAPACITY;
        keys = (K[]) new Object[capacity];
        values = (V[]) new Object[capacity];
        threshold = (int) (capacity * MAX_LOAD_FACTOR);
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }
        int index = computeHash(key);
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                return values[index];
            }
            index = (index + 1) % capacity;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }
        if (size >= threshold) {
            resize(2 * capacity);
        }
        int index = computeHash(key);
        while (keys[index] != null && !keys[index].equals(key)) {
            index = (index + 1) % capacity;
        }
        if (keys[index] == null) {
            keys[index] = key;
            values[index] = value;
            size++;
        } else {
            values[index] = value;
        }
        return value;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }
        int index = computeHash(key);
        while (keys[index] != null && !keys[index].equals(key)) {
            index = (index + 1) % capacity;
        }
        if (keys[index] != null) {
            V oldValue = values[index];
            keys[index] = null;
            values[index] = null;
            size--;
            return oldValue;
        }
        return null;
    }

    private int computeHash(K key) {
        return (key == null ? 0 : Math.abs(key.hashCode())) % capacity;
    }

    private void resize(int newCapacity) {
        capacity = newCapacity;
        threshold = (int) (capacity * MAX_LOAD_FACTOR);
        K[] newKeys = (K[]) new Object[capacity];
        V[] newValues = (V[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newKeys[i] = keys[i];
            newValues[i] = values[i];
        }
        keys = newKeys;
        values = newValues;
    }

    @Override
    protected Map.Entry<K, V>[] toArray() {
        @SuppressWarnings("unchecked")
        Map.Entry<K, V>[] entries = (Map.Entry<K, V>[]) new Map.Entry[size];
        for (int i = 0; i < size; i++) {
            entries[i] = new Entry<>(keys[i], values[i]);
        }
        return entries;
    }

    @Override
    public void clear() {
        size = 0;
        Arrays.fill(keys, null);
        Arrays.fill(values, null);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entrySet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            entrySet.add(new Entry<>(keys[i], values[i]));
        }
        return entrySet;
    }

    @Override
    public boolean hasEntry(K key) {
        return get(key) != null;
    }
}


