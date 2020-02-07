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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.swagger.model.SensorData;
import io.swagger.model.SensorTypes;

public class DataStore {

	private String path = "data.csv";

	private File csvFile;

	public static final String dateformat = "yyyy-mm-dd'T'hh:mm:ss.sss";

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
			final String dateformat = "yyyy-mm-dd'T'hh:mm:ss.sss";
			DateFormat format = new SimpleDateFormat(dateformat);
			String strDate = format.format(entry.getTimestamp()) + "Z";
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

	public List<SensorData> getAll() {
		String row = "";
		List<SensorData> entries = new ArrayList<SensorData>();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				entries.add(extractValues(data));
			}
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entries;
	}

	public List<SensorData> getAllStart(String rawStartDate) {
		String row = "";
		List<SensorData> entries = new ArrayList<SensorData>();
		try {
			Date startDate = convertDatestring(rawStartDate);
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				Date curDate;
				curDate = convertDatestring(data[2]);
				if (startDate.compareTo(curDate) < 0) {
					entries.add(extractValues(data));
				}
			}
			csvReader.close();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return entries;
	}

	public List<SensorData> getAllStartEnd(String rawStartDate, String rawEndDate) {
		String row = "";
		List<SensorData> entries = new ArrayList<SensorData>();
		try {
			Date startDate = convertDatestring(rawStartDate);
			Date endDate = convertDatestring(rawEndDate);
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				Date curDate;
				curDate = convertDatestring(data[2]);
				if (startDate.compareTo(curDate) < 0 && endDate.compareTo(curDate) > 0) {
					entries.add(extractValues(data));
				}
			}
			csvReader.close();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return entries;
	}

	public List<SensorData> getSource(String sourceID) {
		String row = "";
		List<SensorData> entries = new ArrayList<SensorData>();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				if (data[0].contentEquals(sourceID)) {
					entries.add(extractValues(data));
				}
			}
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entries;
	}

	public List<SensorData> getSourceStart(String sourceID, String rawStartDate) {
		String row = "";
		List<SensorData> entries = new ArrayList<SensorData>();
		try {
			Date startDate = convertDatestring(rawStartDate);
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				Date curDate = convertDatestring(data[2]);
				if (data[0].contentEquals(sourceID) && startDate.compareTo(curDate) < 0) {
					entries.add(extractValues(data));
				}
			}
			csvReader.close();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return entries;
	}

	public List<SensorData> getSourceStartEnd(String sourceID, String rawStartDate, String rawEndDate) {
		String row = "";
		List<SensorData> entries = new ArrayList<SensorData>();
		try {
			Date startDate = convertDatestring(rawStartDate);
			Date endDate = convertDatestring(rawEndDate);
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				Date curDate = convertDatestring(data[2]);
				if (data[0].contentEquals(sourceID) && startDate.compareTo(curDate) < 0
						&& endDate.compareTo(curDate) > 0) {
					entries.add(extractValues(data));
				}
			}
			csvReader.close();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return entries;
	}

	public List<SensorData> getSensor(String sensorID) {
		String row = "";
		List<SensorData> entries = new ArrayList<SensorData>();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				if (data[1].contentEquals(sensorID)) {
					entries.add(extractValues(data));
				}
			}
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entries;
	}

	public List<SensorData> getSensorStart(String sensorID, String rawStartDate) {
		String row = "";
		List<SensorData> entries = new ArrayList<SensorData>();
		try {
			Date startDate = convertDatestring(rawStartDate);
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				Date curDate = convertDatestring(data[2]);
				if (data[1].contentEquals(sensorID) && startDate.compareTo(curDate) < 0) {
					entries.add(extractValues(data));
				}
			}
			csvReader.close();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return entries;
	}

	public List<SensorData> getSensorStartEnd(String sensorID, String rawStartDate, String rawEndDate) {
		String row = "";
		List<SensorData> entries = new ArrayList<SensorData>();
		try {
			Date startDate = convertDatestring(rawStartDate);
			Date endDate = convertDatestring(rawEndDate);
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				Date curDate = convertDatestring(data[2]);
				if (data[1].contentEquals(sensorID) && startDate.compareTo(curDate) < 0
						&& endDate.compareTo(curDate) > 0) {
					entries.add(extractValues(data));
				}
			}
			csvReader.close();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return entries;
	}

	public List<SensorData> getSourceSensor(String sensorID, String sourceID) {
		String row = "";
		List<SensorData> entries = new ArrayList<SensorData>();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				if (data[0].contentEquals(sourceID) && data[1].contentEquals(sensorID)) {
					entries.add(extractValues(data));
				}
			}
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entries;
	}

	public List<SensorData> getSourceSensorStart(String sensorID, String sourceID, String rawStartDate) {
		String row = "";
		List<SensorData> entries = new ArrayList<SensorData>();
		try {
			Date startDate = convertDatestring(rawStartDate);
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				Date curDate = convertDatestring(data[2]);
				if (data[0].contentEquals(sourceID) && data[1].contentEquals(sensorID)
						&& startDate.compareTo(curDate) < 0) {
					entries.add(extractValues(data));
				}
			}
			csvReader.close();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return entries;
	}

	public List<SensorData> getSourceSensorStartEnd(String sensorID, String sourceID, String rawStartDate,
			String rawEndDate) {
		String row = "";
		List<SensorData> entries = new ArrayList<SensorData>();
		try {
			Date startDate = convertDatestring(rawStartDate);
			Date endDate = convertDatestring(rawEndDate);
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				Date curDate = convertDatestring(data[2]);
				if (data[0].contentEquals(sourceID) && data[1].contentEquals(sensorID)
						&& startDate.compareTo(curDate) < 0 && endDate.compareTo(curDate) > 0) {
					entries.add(extractValues(data));
				}
			}
			csvReader.close();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return entries;
	}

	private SensorData extractValues(String[] data) {
		SensorData entry = new SensorData();
		entry.setSourceId(data[0]);
		entry.setSensorId(data[1]);
		Date date;
		try {
			date = convertDatestring(data[2]);
			entry.setTimestamp(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		entry.setUnit(data[3]);
		entry.setValue(Double.valueOf(data[4]));
		return entry;
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

	public Date getLastLoggedDate() {
		String row = "";
		Date date = null;
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			while ((row = csvReader.readLine()) != null) {
				String rawDate = row.split(",")[2];
				DateFormat format = new SimpleDateFormat(dateformat);
				date = format.parse(rawDate);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public Date convertDatestring(String rawDate) throws ParseException {
		final String dateformat = "yyyy-mm-dd'T'hh:mm:ss.sss";
		DateFormat format = new SimpleDateFormat(dateformat);
		System.out.println(rawDate);
		Date date = format.parse(rawDate);
		System.out.println(format.format(date));
		return date;
	}
	
	public void generateRandomData() {
		double temp = Math.random() * 30;
		for (int i = 10; i < 60; i++) {
			String time = "2019-11-22T09:" + i + ":00.000";
			temp = temp + Math.random() * 2 - 1;
			SensorData entry = new SensorData();
			entry.setSourceId("1");
			entry.setSensorId("1");
			Date date;
			try {
				date = convertDatestring(time);
				entry.setTimestamp(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			entry.setUnit("C");
			entry.setValue(temp);
			put(entry);
		}
	}

}
