package br.ufc.crateus.eda.st;

public class BinarySearchTreeMap<K extends Comparable<K>, V> implements OrderedMap<K, V> {
	
	private class Node {
		K key;
		V value;
		Node left;
		Node right;
		int count;
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.count = 1;
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
		
		r.count = count(r.left) + count(r.right) + 1;
		return r;
	}
	
	private int count(Node n) {
		return (n != null)? n.count : 0;
	}

	private Node getNode(Node r, K key) {
		if (r == null) return null;
		
		int cmp = key.compareTo(r.key);
		if (cmp < 0) return getNode(r.left, key);
		if (cmp > 0) return getNode(r.right, key);
		return r;
	}

	@Override
	public V get(K key) {
		Node node = getNode(root, key);
		return (node != null)? node.value : null;
	}

	@Override
	public boolean contains(K key) {
		return get(key) != null;
	}
	
	private Node removeMin(Node r) {
		if (r.left == null) return r.right;
		r.left = removeMin(r.left);
		r.count = count(r.left) + count(r.right) + 1;
		return r;
	}

	@Override
	public void removeMin() {
		root = removeMin(root);
	}
	
	private Node removeMax(Node r) {
		if (r.right == null) return r.left;
		r.right = removeMax(r.right);
		r.count = count(r.left) + count(r.right) + 1;
		return r;
	}

	@Override
	public void removeMax() {
		root = removeMax(root);
	}
	
	private Node remove(Node r, K key) {
		if (r == null) return null;
		
		int cmp = key.compareTo(r.key); 
		if (cmp < 0) r.left = remove(r.left, key);
		else if (cmp > 0) r.right = remove(r.right, key);
		else {
			if (r.left == null) return r.right;
			if (r.right == null) return r.left;
			Node t = r;
			r = maxNode(r.left);
			r.right = t.right;
			r.left = removeMax(t.left);
		}
		
		r.count = count(r.left) + count(r.right) + 1;
		return r;
	}

	@Override
	public void remove(K key) {
		root = remove(root, key);
	}

	@Override
	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	private Node minNode(Node r) {
		if (r == null) return null;
		while (r.left != null) r = r.left;
		return r;
	}

	@Override
	public K min() {
		Node m = minNode(root);
		return (m != null)? m.key : null;
	}
	
	private Node maxNode(Node r) {
		if (r == null) return null;
		while (r.right != null) r = r.right;
		return r;
	}

	@Override
	public K max() {
		Node m = maxNode(root);
		return (m != null)? m.key : null;
	}
	
	private K floor(Node r, K e) {
		if (r == null) return null;
		int cmp = e.compareTo(r.key);
		if (cmp < 0) return floor(r.left, e);
		if (cmp > 0) {
			K tmp = floor(r.right, e);
			return (tmp != null)? tmp : r.key;
		}
		return r.key;
	}

	@Override
	public K floor(K e) {
		return floor(root, e);
	}
	
	private K ceiling(Node r, K e) {
		if (r == null) return null;
		int cmp = e.compareTo(r.key);
		if (cmp > 0) return ceiling(r.right, e);
		if (cmp < 0) {
			K tmp = ceiling(r.left, e);
			return (tmp != null)? tmp : r.key;
		}
		return r.key;
	}

	@Override
	public K ceiling(K e) {
		return ceiling(root, e);
	}


}
