package org.example;

import java.util.Comparator;

public class HuffmanComparator implements Comparator<Node> {
	@Override
	public int compare(Node node1, Node node2) {
		return node1.frequency - node2.frequency;
	}
}
