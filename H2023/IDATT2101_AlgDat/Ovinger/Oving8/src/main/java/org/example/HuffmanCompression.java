package org.example;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HuffmanCompression {

	public static Node buildHuffmanTree(int[] freqTable) {
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return n1.frequency - n2.frequency;
			}
		});

		// Create leaf nodes and add them to the priority queue
		for (char c = 0; c < 256; c++) {
			if (freqTable[c] > 0) {
				priorityQueue.offer(new Node(c, freqTable[c]));
			}
		}

		// Build the Huffman tree
		while (priorityQueue.size() > 1) {
			Node left = priorityQueue.poll();
			Node right = priorityQueue.poll();
			Node parent = new Node(left.frequency + right.frequency, left, right);
			priorityQueue.offer(parent);
		}

		return priorityQueue.poll();
	}

	public static void generateHuffmanCodes(Node root, String code, String[] huffmanCodes) {
		if (root == null) {
			return;
		}

		if (root.isLeaf()) {
			huffmanCodes[root.data] = code;
		}

		generateHuffmanCodes(root.leftNode, code + '0', huffmanCodes);
		generateHuffmanCodes(root.rightNode, code + '1', huffmanCodes);
	}

	public static String decode(String encodedString, Node root) {
		Node currentNode = root;
		StringBuilder decodedString = new StringBuilder();

		for (int i = 0; i < encodedString.length(); i++) {
			char bit = encodedString.charAt(i);

			if (bit == '0') {
				currentNode = currentNode.leftNode;
			} else if (bit == '1') {
				currentNode = currentNode.rightNode;
			}

			if (currentNode.isLeaf()) {
				decodedString.append(currentNode.data);
				currentNode = root;
			}
		}

		return decodedString.toString();
	}
}

