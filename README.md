# Bulkhead pattern in action.

It is a application that reproduces de problem that the pattern solves and also shows the behavior of the application after to use the pattern.

Using Jmeter I put some load on a Tomcat server.
<img src="https://user-images.githubusercontent.com/63823685/185599850-5bbbc731-bf0d-4db7-ac1b-2a4811deaf5f.png"  width=50% height=50%>

After to simulate a problem in one application's service, the Server starts to run out of available resources and soon It can not process all the incoming requests. As a result the entire application fails due to a particular service error.

<img src="https://user-images.githubusercontent.com/63823685/185599383-5f244f52-2d75-41ff-a541-cb2854672053.png"  width=50% height=50%>

After to use an implementation of bulkhed pattern, the application keeps working well, just the client of the service that fails are affected.

<img src="https://user-images.githubusercontent.com/63823685/185600505-9c789c51-cfe6-42e7-a318-67fadc5324ee.png"  width=50% height=50%>

<img src="https://user-images.githubusercontent.com/63823685/185600933-1d1e6af4-f2c0-4008-b02c-084e4eb98d92.png"  width=50% height=50%>

<img src="https://user-images.githubusercontent.com/63823685/185600988-43e73b39-580d-4051-ae91-e83ab2b7b045.png"  width=50% height=50%>


## Docker image

The application uses a docker images which has installed Grafana and Influxdb. You can download the image from this project:

https://hub.docker.com/r/philhawthorne/docker-influxdb-grafana/

```
docker run -d   --name docker-influxdb-grafana   -p 3003:3003   -p 3004:8083   -p 8086:8086   -v /path/for/influxdb:/var/lib/influxdb   -v /path/for/grafana:/var/lib/grafana   philhawthorne/docker-influxdb-grafana:latest
```

```
docker start docker-influxdb-grafana
```

## Grafana
The dashboard file is under the folder grafana.

http://localhost:3003

Username: root
Password: root

## JMeter
The .jmx file used to create the requests generators is under the folder jmeter.
