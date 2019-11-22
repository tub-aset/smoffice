package store;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DataStore {

	private String path = "data.csv";

	private File csvFile;

	public DataStore() {

		csvFile = new File(path);

		if (!csvFile.exists()) {
			try {
				csvFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void put(String sensorID) {
		try {
			PrintWriter csvWriter = new PrintWriter(csvFile);

			StringBuilder sb = new StringBuilder();

			sb.append(sensorID);
			sb.append(",");
			sb.append("Test");

			csvWriter.write(sb.toString());

			csvWriter.flush();
			csvWriter.close();

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<String[]> get() {
		String row = "";
		List<String[]> entries = new ArrayList<String[]>();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				entries.add(data);
			}
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entries;
	}

}
