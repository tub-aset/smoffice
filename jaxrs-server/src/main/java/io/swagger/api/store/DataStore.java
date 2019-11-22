package io.swagger.api.store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	public List<SensorData> get() throws ParseException {
		String row = "";
		List<SensorData> entries = new ArrayList<SensorData>();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				DateFormat format = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");
				Date date = format.parse(data[2]);
				SensorData entry = new SensorData();
				entry.setSensorId(data[0]);
				entry.setSourceId(data[1]);
				entry.setTimestamp(date);
				entry.setUnit(data[3]);
				entry.setValue(Double.valueOf(data[4]));
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
//		Date date = new Date();
//
//		for (int i = 10; i < 60; i++) {
//			date = date.
//			temp = temp + Math.random() * 2 - 1;
//			SensorData entry = new SensorData();
//			entry.setSensorId("1");
//			entry.setSourceId("1");
//			entry.setTimestamp(date);
//			entry.setValue(temp);
//			put(entry);
//		}
//	}

}
