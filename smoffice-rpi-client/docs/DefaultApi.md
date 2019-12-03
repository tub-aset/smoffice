# swagger_client.DefaultApi

All URIs are relative to *https://ics.aset.tu-berlin.de/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**add_sensor_data**](DefaultApi.md#add_sensor_data) | **POST** /sensordata | Add new sensor data to the server
[**get_sensor_data**](DefaultApi.md#get_sensor_data) | **GET** /sensordata | Get all sensor data


# **add_sensor_data**
> add_sensor_data(body)

Add new sensor data to the server



### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
body = swagger_client.SensorData() # SensorData | Sensor data object

try:
    # Add new sensor data to the server
    api_instance.add_sensor_data(body)
except ApiException as e:
    print("Exception when calling DefaultApi->add_sensor_data: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SensorData**](SensorData.md)| Sensor data object | 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: application/xml, application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_sensor_data**
> list[SensorData] get_sensor_data(source_id=source_id, sensor_id=sensor_id, latest_only=latest_only)

Get all sensor data

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
source_id = 'source_id_example' # str |  (optional)
sensor_id = 'sensor_id_example' # str |  (optional)
latest_only = true # bool | If true, return only the latest data for each sensor (optional)

try:
    # Get all sensor data
    api_response = api_instance.get_sensor_data(source_id=source_id, sensor_id=sensor_id, latest_only=latest_only)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->get_sensor_data: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **source_id** | **str**|  | [optional] 
 **sensor_id** | **str**|  | [optional] 
 **latest_only** | **bool**| If true, return only the latest data for each sensor | [optional] 

### Return type

[**list[SensorData]**](SensorData.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: application/xml, application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

