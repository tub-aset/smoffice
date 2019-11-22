package io.swagger.api.store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.swagger.model.SensorData;
import io.swagger.model.SensorTypes;

public class DataStore {

	private String path = "data.csv";

	private File csvFile;

	private String dateformat = "yyyy-mm-dd hh:mm:ss";

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
			DateFormat format = new SimpleDateFormat(dateformat);
			String strDate = format.format(entry.getTimestamp());
			sb.append(strDate);
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

	public List<SensorData> getAll() throws ParseException {
		String row = "";
		List<SensorData> entries = new ArrayList<SensorData>();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				SensorData entry = new SensorData();
				entry.setSourceId(data[0]);
				entry.setSensorId(data[1]);
				DateFormat format = new SimpleDateFormat(dateformat);
				Date date = format.parse(data[2]);
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

	public List<SensorData> getSource(String sourceID) throws ParseException {
		String row = "";
		List<SensorData> entries = new ArrayList<SensorData>();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				if (data[0].contentEquals(sourceID)) {
					SensorData entry = new SensorData();
					entry.setSourceId(data[0]);
					entry.setSensorId(data[1]);
					DateFormat format = new SimpleDateFormat(dateformat);
					Date date = format.parse(data[2]);
					entry.setTimestamp(date);
					entry.setUnit(data[3]);
					entry.setValue(Double.valueOf(data[4]));
					entries.add(entry);
				} else {
					continue;
				}
			}
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entries;
	}

	public List<SensorData> getSensor(String sensorID) throws ParseException {
		String row = "";
		List<SensorData> entries = new ArrayList<SensorData>();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				if (data[1].contentEquals(sensorID)) {
					SensorData entry = new SensorData();
					entry.setSourceId(data[0]);
					entry.setSensorId(data[1]);
					DateFormat format = new SimpleDateFormat(dateformat);
					Date date = format.parse(data[2]);
					entry.setTimestamp(date);
					entry.setUnit(data[3]);
					entry.setValue(Double.valueOf(data[4]));
					entries.add(entry);
				} else {
					continue;
				}
			}
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entries;
	}

	public List<SensorData> getSourceSensor(String sensorID, String sourceID) throws ParseException {
		String row = "";
		List<SensorData> entries = new ArrayList<SensorData>();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				if (data[0].contentEquals(sourceID) && data[1].contentEquals(sensorID)) {
					SensorData entry = new SensorData();
					entry.setSourceId(data[0]);
					entry.setSensorId(data[1]);
					DateFormat format = new SimpleDateFormat(dateformat);
					Date date = format.parse(data[2]);
					entry.setTimestamp(date);
					entry.setUnit(data[3]);
					entry.setValue(Double.valueOf(data[4]));
					entries.add(entry);
				} else {
					continue;
				}
			}
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entries;
	}

	public List<SensorTypes> getSensorTypes() {

		String row = "";
		Set<String> strSensors = new HashSet<String>();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				strSensors.add(data[1]);
			}
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<SensorTypes> sensors = new ArrayList<SensorTypes>();
		for (String sensor : strSensors) {
			SensorTypes entry = new SensorTypes();
			entry.setSensorId(sensor);
			sensors.add(entry);
		}

		return sensors;

	}

}
