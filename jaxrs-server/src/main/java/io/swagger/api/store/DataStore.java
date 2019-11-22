package io.swagger.api.store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import io.swagger.model.SensorData;

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

	public void put(SensorData entry) {
		try {
			FileWriter fr = new FileWriter(csvFile, true);
			BufferedWriter br = new BufferedWriter(fr);
			PrintWriter csvWriter = new PrintWriter(br);

			StringBuilder sb = new StringBuilder();

			sb.append(entry.getSourceId());
			sb.append(",");
			sb.append(entry.getSensorId());
			sb.append(",");
			sb.append(entry.getTimestamp());
			sb.append(",");
			sb.append(entry.getUnit());
			sb.append(",");
			sb.append(entry.getValue());
			sb.append("\r\n");

			csvWriter.write(sb.toString());

			csvWriter.flush();
			csvWriter.close();
			br.close();
			fr.close();

		} catch (IOException e) {
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

//	public void generateRandomData() {
//		double temp = Math.random() * 30;
//		for (int i = 10; i < 60; i++) {
//			String time = "2019-11-22T09:" + i + ":00.000Z";
//			temp = temp + Math.random() * 2 - 1;
//			DataEntry entry = new SensorData("1", "1", time, "C", temp);
//			put(entry);
//		}
//	}

}
