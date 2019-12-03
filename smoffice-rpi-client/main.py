import time
import datetime

from swagger_client import DefaultApi, ApiClient, Configuration, SensorData
from swagger_client.rest import ApiException

from sensors.sensors import DigitalTemperatureHumiditySensorThread,LoudnessSensorThread,AirQualitySensorThread,\
    BarometerSensorThread,SunlightSensorThread,DigitalLightSensorThread,MotionSensor,DustSensorThread

if __name__ == '__main__':
    conf = Configuration()
    conf.host = "http://192.168.178.35:8080/v1"
    sourceId = "pi-1"

    sensors = [
        DigitalTemperatureHumiditySensorThread(sensorport=4, sourceId=sourceId, conf=conf),
        LoudnessSensorThread(sensorport=0, sourceId=sourceId, conf=conf),
        AirQualitySensorThread(sensorport=2, sourceId=sourceId, conf=conf),
#        BarometerSensorThread(sourceId=sourceId, conf=conf),
        SunlightSensorThread(sourceId=sourceId, conf=conf),
#        DigitalLightSensorThread(sourceId=sourceId, conf=conf),
        MotionSensor(sensorport=8, sourceId=sourceId, conf=conf),
        DustSensorThread(sourceId=sourceId, conf=conf)
    ]

    while True:
        for sensor in sensors:
            sensor.sendData()
        time.sleep(1)