package io.swagger.api.store;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

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

	public void put(DataEntry entry) {
		try {
			PrintWriter csvWriter = new PrintWriter(csvFile);

			StringBuilder sb = new StringBuilder();

			sb.append(entry.getSourceID());
			sb.append(",");
			sb.append(entry.getSensorID());
			sb.append(",");
			sb.append(entry.getTimestamp());
			sb.append(",");
			sb.append(entry.getUnit());
			sb.append(",");
			sb.append(entry.getValue());

			csvWriter.write(sb.toString());

			csvWriter.flush();
			csvWriter.close();

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<DataEntry> get() {
		String row = "";
		List<DataEntry> entries = new ArrayList<DataEntry>();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				DataEntry entry = new DataEntry(data[0], data[1], data[2], data[3], Double.valueOf(data[4]));
				entries.add(entry);
			}
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entries;
	}

}
