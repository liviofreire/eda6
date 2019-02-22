package br.ufc.crateus.eda.st;

public class BinarySearchTreeMap<K extends Comparable<K>, V> implements OrderedMap<K, V> {
	
	private class Node {
		K key;
		V value;
		Node left;
		Node right;
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
	
	Node root;
	
	@Override
	public void put(K key, V value) {
		root = put(root, key, value);
	}
	
	private Node put(Node r, K key, V value) {
		if (r == null) return new Node(key, value);
		
		if (key.compareTo(r.key) < 0) r.left = put(r.left, key, value);
		else if (key.compareTo(r.key) > 0) r.right = put(r.right, key, value);
		else r.value = value;
		
		return r;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(K key) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K min() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K max() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K ceiling(K val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K floor(K val) {
		// TODO Auto-generated method stub
		return null;
	}

}
