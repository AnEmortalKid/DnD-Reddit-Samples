package com.anemortalkid.whileloop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class WhileLoop {

	public static void main(String[] args) {

		// assume this is the data from the file
		List<String> dataList = Arrays.<String> asList("John", "Farmer", "Johnny", "Jacob", "Smith", "Smithereens",
				"Mike", "Runner", "NikeMike");
		Iterator<String> dataIterator = dataList.iterator();

		// kinda like while file has next
		SomeData data = new SomeData();
		List<SomeData> parsedData = new ArrayList<>();

		// this helps us keep track of what stage we are at
		int counter = 1;
		while (dataIterator.hasNext()) {
			String line = dataIterator.next();
			switch (counter) {
			case 1:
				data.setName(line);
				counter++;
				break;
			case 2:
				data.setLabel(line);
				counter++;
				break;
			case 3:
				data.setNickName(line);
				parsedData.add(data);
				data = new SomeData();
				counter = 1;
				break;
			// you would have more cases, 1through 6. On 6 you want to set the
			// last piece of info and create a new object
			}
		}

		// Java 8, printline. just printing the objects out
		parsedData.forEach(System.out::println);

	}

	/**
	 * Just some object that holds some arbitrary data, like your 6 data object
	 * thing
	 */
	private static class SomeData {
		private String name;
		private String label;
		private String nickName;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		@Override
		public String toString() {
			return "SomeData [name=" + name + ", label=" + label + ", nickName=" + nickName + "]";
		}

	}
}
