# swagger-client
Enter description here

This Python package is automatically generated by the [Swagger Codegen](https://github.com/swagger-api/swagger-codegen) project:

- API version: 1.0.0
- Package version: 1.0.0
- Build package: io.swagger.codegen.languages.PythonClientCodegen

## Requirements.

Python 2.7 and 3.4+

## Installation & Usage
### pip install

If the python package is hosted on Github, you can install directly from Github

```sh
pip install git+https://github.com//.git
```
(you may need to run `pip` with root permission: `sudo pip install git+https://github.com//.git`)

Then import the package:
```python
import swagger_client 
```

### Setuptools

Install via [Setuptools](http://pypi.python.org/pypi/setuptools).

```sh
python setup.py install --user
```
(or `sudo python setup.py install` to install the package for all users)

Then import the package:
```python
import swagger_client
```

## Getting Started

Please follow the [installation procedure](#installation--usage) and then run the following:

```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi(swagger_client.ApiClient(configuration))
body = swagger_client.SensorData() # SensorData | Sensor data object

try:
    # Add new sensor data to the server
    api_instance.add_sensor_data(body)
except ApiException as e:
    print("Exception when calling DefaultApi->add_sensor_data: %s\n" % e)

```

## Documentation for API Endpoints

All URIs are relative to *https://ics.aset.tu-berlin.de/v1*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*DefaultApi* | [**add_sensor_data**](docs/DefaultApi.md#add_sensor_data) | **POST** /sensordata | Add new sensor data to the server
*DefaultApi* | [**get_sensor_data**](docs/DefaultApi.md#get_sensor_data) | **GET** /sensordata | Get all sensor data


## Documentation For Models

 - [SensorData](docs/SensorData.md)


## Documentation For Authorization

 All endpoints do not require authorization.


## Author


