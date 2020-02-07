import grovepi
import hp206c

import sensors.grove_i2c_digital_light_sensor as lightsensor
from sensors import SI1145

from sensors.SensorThread import Sensor, AnalogValueSensor
from swagger_client import Configuration


class DigitalTemperatureHumiditySensorThread(Sensor):

    def __init__(self, sensorport, sourceId, conf=Configuration()):
        Sensor.__init__(self, sourceId, conf)
        self.sensorport = sensorport

    def getSensorData(self):
        [temp, humidity] = grovepi.dht(self.sensorport, 1) #1 is for the white sensor, 0 would be blue

        return [{"name": "temperature", "value": temp, "unit": "C"},
                {"name": "humidity", "value": humidity, "unit": "%"}]


class LoudnessSensorThread(AnalogValueSensor):

    def __init__(self, sensorport, sourceId, conf=Configuration()):
        AnalogValueSensor.__init__(self, sensorport, sourceId, name="loudness", conf=conf)

class AirQualitySensorThread(AnalogValueSensor):

    def __init__(self, sensorport, sourceId, conf=Configuration()):
        AnalogValueSensor.__init__(self, sensorport, sourceId, name="air", conf=conf)

class BarometerSensorThread(Sensor):

    def __init__(self, sourceId, conf=Configuration()):
        Sensor.__init__(self, sourceId, conf)
        self.h = hp206c.hp206c()

    def getSensorData(self):
        ret = self.h.isAvailable()
        if self.h.OK_HP20X_DEV == ret:
            temp = self.h.ReadTemperature()
            pressure = self.h.ReadPressure()
            altitude = self.h.ReadAltitude()

            return [{"name": "temperature_barometer", "value": temp, "unit": "C"},
                    {"name": "pressure", "value": pressure, "unit": "hPa"},
                    {"name": "altitude", "value": altitude, "unit": "m"}]
        else:
            return []

class SunlightSensorThread(Sensor):

    def __init__(self, sourceId, conf=Configuration()):
        Sensor.__init__(self, sourceId, conf)
        self.sensor = SI1145.SI1145()

    def getSensorData(self):
        vis = self.sensor.readVisible()
        IR = self.sensor.readIR()
        UV = self.sensor.readUV()
        uvIndex = UV / 100.0

        return [{"name": "visibility", "value": vis, "unit": ""},
                {"name": "infrared", "value": IR, "unit": ""},
                {"name": "UV-Index", "value": uvIndex, "unit": ""}]

class DigitalLightSensorThread(Sensor):

    def __init__(self, sourceId, conf=Configuration()):
        Sensor.__init__(self, sourceId, conf)
        lightsensor.init()

    def getSensorData(self):
        lux = lightsensor.readVisibleLux()
        vis_ir = lightsensor.channel0
        ir = lightsensor.channel1
        gain = lightsensor.gain_m
        timing_ms = lightsensor.timing_ms
        return [{"name": "Vis+IR", "value": vis_ir, "unit": ""},
                {"name": "IR", "value": ir, "unit": ""},
                {"name": "Gain (Lightsensor)", "value": gain, "unit": ""},
                {"name": "Timing (Lightsensor)", "value": timing_ms, "unit": "ms"}]


class MotionSensor(Sensor):

    def __init__(self, sensorport, sourceId, conf=Configuration()):
        Sensor.__init__(self, sourceId, conf)
        self.sensorport = sensorport
        grovepi.pinMode(self.sensorport, "INPUT")

    def getSensorData(self):
        motion = grovepi.digitalRead(self.sensorport)
        if motion == 0 or motion == 1:
            if motion == 1:
                return [{"name": "motion", "value": 1, "unit": ""}]
            else:
                return [{"name": "motion", "value": 0, "unit": ""}]
        else:
            return []

class DustSensorThread(Sensor):

    def __init__(self, sourceId, conf=Configuration()):
        Sensor.__init__(self, sourceId, conf)
        grovepi.dust_sensor_en()

    def getSensorData(self):
        [time, lpo, pcs] = grovepi.dust_sensor_read()

        return [{"name": "time (dust)", "value": time, "unit": ""},
                {"name": "LPO (dust)", "value": lpo, "unit": "%"},
                {"name": "Pieces (dust)", "value": pcs, "unit": "pcs/0.01cf"}]
