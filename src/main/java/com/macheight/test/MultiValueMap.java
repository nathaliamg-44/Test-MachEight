package com.macheight.test;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class MultiValueMap<K, V> extends HashMap<K, List<V>> {

	public void add(K key, V value) {
		List<V> list = get(key);
		if (list == null) {
			list = new ArrayList<>(200);
		}
		list.add(value);
		put(key, list);
	}
}
