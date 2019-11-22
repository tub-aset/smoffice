package io.swagger.api.store;


public class DataEntry {
	
	private String sourceID;
	private String sensorID;
	private String timestamp;
	private String unit;
	private double value;
	
	public DataEntry(String sourceID, String sensorID, String timestamp, String unit, double value) {
		this.setSourceID(sourceID);
		this.setSensorID(sensorID);
		this.setTimestamp(timestamp);
		this.setUnit(unit);
		this.setValue(value);
	}

	public String getSourceID() {
		return sourceID;
	}

	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
	}

	public String getSensorID() {
		return sensorID;
	}

	public void setSensorID(String sensorID) {
		this.sensorID = sensorID;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
