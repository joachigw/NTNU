package org.example;

public class Node {
	char data;
	int frequency;
	Node leftNode;
	Node rightNode;

	public Node(char data, int frequency) {
		this.data = data;
		this.frequency = frequency;
	}

	public Node(int frequency, Node left, Node right) {
		this.frequency = frequency;
		this.leftNode = left;
		this.rightNode = right;
	}

	boolean isLeaf() {
		return leftNode == null && rightNode == null;
	}
}
