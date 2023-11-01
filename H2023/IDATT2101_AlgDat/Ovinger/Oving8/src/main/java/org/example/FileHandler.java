package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileHandler {

	public static String readInputFromFile(String filePath) {
		try {
			return new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			System.out.println("Error reading file " + e);
			System.exit(1);
			return null;
		}
	}

	public static void writeOutputToFile(String file, String decodedString) {
		try {
			Files.write(Paths.get(file), decodedString.getBytes());
		} catch (IOException e) {
			System.out.println("Error writing to file " + e);
			System.exit(1);
		}
	}

	public static void writeCompressedFile(String filePath, int[] freqTable, char[] encodedString) {
		try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
			StringBuilder combinedData = new StringBuilder();

			// Convert the frequency table to a string
			combinedData.append(Arrays.toString(freqTable)).append('\n');

			// Append the encoded string
			combinedData.append(encodedString);

			outputStream.write(combinedData.toString().getBytes());
			System.out.println("Compressed data written to " + filePath);
		} catch (IOException e) {
			System.out.println("Error writing to file " + e);
		}
	}

	public static String[] readCompressedFile(String filePath) {
		try (FileInputStream inputStream = new FileInputStream(filePath)) {
			byte[] data = new byte[inputStream.available()];
			inputStream.read(data);

			String combinedData = new String(data);

			return combinedData.split("\n", 2);
		} catch (IOException e) {
			System.out.println("Error reading file " + e);
			return null;
		}
	}
}
