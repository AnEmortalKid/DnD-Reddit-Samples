package com.anemortalkid.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FileReadingSample {

	private static void readFileJ8(String filePath) {
		File file = new File(filePath);
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(file.toURI()));
			lines.forEach(System.out::println);
		} catch (IOException e) {
			// not much we can do
			e.printStackTrace();
		}
	}

	private static void readFileJ7(String filePath) {
		File file = new File(filePath);
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			// not much we can do
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String filePath = "PATH_TO_FILE";
		readFileJ8(filePath);
		readFileJ7(filePath);
	}
}
