package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		int[] freqTable = new int[700];
		String input = FileHandler.readInputFromFile("test.txt");


		// Build frequency table
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			freqTable[c]++;
		}

		// Build Huffman tree and generate Huffman codes
		Node root = HuffmanCompression.buildHuffmanTree(freqTable);
		String[] huffmanCodes = new String[700];
		HuffmanCompression.generateHuffmanCodes(root, "", huffmanCodes);

		// Encode the input string
		StringBuilder encodedString = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) > 0) {
				encodedString.append(huffmanCodes[input.charAt(i)]);
			}
		}

		System.out.println("Encoding the string: " + input.substring(0, 50) + " ...");
		System.out.println("Encoded string: " + encodedString.substring(0, 50) + " ...");

		// Check that the encoded string only contains 0s and 1s
		for (int i = 0; i < encodedString.length(); i++) {
			if (encodedString.charAt(i) != '0' && encodedString.charAt(i) != '1') {
				System.out.println("Error: Encoded string contains characters other than 0 and 1.");
				System.out.println("Index: " + i + ", character: " + encodedString.charAt(i));
				System.exit(1);
			}
		}

		// Compress the encoded bitstring to a char array
		char[] compressed = compressBinaryString(encodedString.toString()).toCharArray();
		System.out.println("Compressed: " + Arrays.toString(compressed).substring(0, 50) + " ...");

		// Write the frequency table and the encoded string to a file
		FileHandler.writeCompressedFile("compressed.dat", freqTable, compressed);

		System.out.println("\nDecoding now...\n");
		decodeTest();

	}

	public static String compressBinaryString(String input) {
		StringBuilder compressed = new StringBuilder();
		for (int i = 0; i < input.length(); i += 8) {
			String chunk = input.substring(i, Math.min(i + 8, input.length()));
			int decimalValue = Integer.parseInt(chunk, 2);
			char c = (char) decimalValue;
			compressed.append(c);
		}
		return compressed.toString();
	}

	public static String decompressBinaryString(String compressed) {
		StringBuilder decompressed = new StringBuilder();
		for (int i = 0; i < compressed.length(); i++) {
			int decimalValue = compressed.charAt(i);
			String binaryChunk = Integer.toBinaryString(decimalValue);
			// Ensure that the binary chunk is 8 bits long by adding leading zeros if needed.
			while (binaryChunk.length() < 8) {
				binaryChunk = "0" + binaryChunk;
			}
			decompressed.append(binaryChunk);
		}
		return decompressed.toString();
	}

	public static void compareFileContents(String decodedString) {
		String originalString = "";
		try {
			originalString = new String(Files.readAllBytes(Paths.get("test.txt")));
		} catch (IOException e) {
			System.out.println("Error reading file " + e);
			System.exit(1);
		}

		if (originalString.equals(decodedString)) {
			System.out.println("Decoded string matches original string!");
		} else {
			System.out.println("\nDecoded string does NOT match original string.");
		}
	}

	public static void compareFileSizes() {
		File originalFile = new File("test.txt");
		File compressedFile = new File("compressed.dat");
		File decompressedFile = new File("testDecoded.txt");

		System.out.println("\nOriginal file size: " + originalFile.length() + " bytes");
		System.out.println("Compressed file size: " + compressedFile.length() + " bytes");
		System.out.println("Decompressed file size: " + decompressedFile.length() + " bytes");
		System.out.printf("Compression ratio: %.3f\n", (double) originalFile.length() / compressedFile.length());
	}

	public static void decodeTest() {

		// Read frequency table and encoded string from file
		String[] freqTableAndEncodedString = FileHandler.readCompressedFile("compressed.dat");
		String freqTableString = freqTableAndEncodedString[0];
		String encodedString = decompressBinaryString(freqTableAndEncodedString[1]);

		System.out.println("Decoded binary string: " + encodedString.substring(0, 50) + " ...");

		// Convert frequency table String back to an int array
		int[] freqTable = Arrays.stream(freqTableString.substring(1, freqTableString.length() - 1).split(", "))
				.mapToInt(Integer::parseInt)
				.toArray();

		// Build Huffman tree from frequency table and decode the encoded string
		Node root = HuffmanCompression.buildHuffmanTree(freqTable);
		String decodedString = HuffmanCompression.decode(encodedString, root);
		System.out.println("Decoded string: " + decodedString.substring(0, 50) + " ...");

		// Write decoded string to file
		FileHandler.writeOutputToFile("testDecoded.txt", decodedString);

		// Compare decoded string to original string
		compareFileContents(decodedString);

		compareFileSizes();
	}
}