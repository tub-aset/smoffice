import datetime
import time

import grovepi

from swagger_client import DefaultApi, ApiClient, Configuration, SensorData
from swagger_client.rest import ApiException

class Sensor:

    def __init__(self, sourceId, conf=Configuration()):
        self.sourceId = sourceId
        self.conf = conf
        self.apiClient = ApiClient(configuration=self.conf)
        self.api = DefaultApi(api_client=self.apiClient)


    def getSensorData(self):
        pass

    def sendData(self):
        data_array = self.getSensorData()
        print(data_array)
        timestamp = datetime.datetime.now(datetime.timezone.utc)

        for data in data_array:
            sensorData = SensorData(source_id=self.sourceId,
                                        sensor_id=data["name"],
                                        value=data["value"],
                                        unit=data["unit"],
                                        timestamp=timestamp)
            try:
                self.api.add_sensor_data(sensorData)
            except:
                pass

class AnalogValueSensor(Sensor):

    def __init__(self, sensorport, sourceId, name, unit="", conf=Configuration()):
        Sensor.__init__(self, sourceId, conf)
        self.sensorport = sensorport
        self.name = name
        self.unit = unit

    def getSensorData(self):
        sensor_value = grovepi.analogRead(self.sensorport)

        return [{"name": self.name, "value": sensor_value, "unit": self.unit}]