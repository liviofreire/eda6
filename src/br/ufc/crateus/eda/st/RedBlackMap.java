package br.ufc.crateus.eda.st;

public class RedBlackMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	protected class RBNode extends Node {
		boolean color;
		
		public RBNode(K key, V value) {
			super(key, value);
			this.color = RED;
		}		
	}
	
	@Override
	protected Node put(Node r, K key, V value) {
		if (r == null) return new RBNode(key, value);
		
		if (key.compareTo(r.key) < 0) r.left = put(r.left, key, value);
		else if (key.compareTo(r.key) > 0) r.right = put(r.right, key, value);
		else r.value = value;
		
		if (!isRed(r.left) && isRed(r.right)) r = rotateLeft(r);
		if (isRed(r.left) && isRed(r.left.left)) r = rotateRight(r);
		if (isRed(r.left) && isRed(r.right)) flipColors(r);
		
		r.count = count(r.left) + count(r.right) + 1;
		return r;
	}
	

	@SuppressWarnings("unchecked")
	private void setColor(Node n, boolean color) {
		RBNode rb = (RBNode) n;
		rb.color = color;
	}
	
	@SuppressWarnings("unchecked")
	private boolean getColor(Node n) {
		RBNode rb = (RBNode) n;
		return rb.color;
	}
	
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		setColor(x, getColor(h));
		setColor(h, RED);
		
		h.count = count(h.left) + count(h.right) + 1;
		x.count = h.count + count(x.right) + 1;
		return x;
	}
	
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		setColor(x, getColor(h));
		setColor(h, RED);
		
		h.count = count(h.left) + count(h.right) + 1;
		x.count = h.count + count(x.right) + 1;
		return x;
	}
	
	private void flipColors(Node h) {
		setColor(h, RED);
		setColor(h.left, BLACK);
		setColor(h.right, BLACK);
	}
	
	private boolean isRed(Node node)  {
		if (node == null) return false;
		return getColor(node) == RED;
	}

}
