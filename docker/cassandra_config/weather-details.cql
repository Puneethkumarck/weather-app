CREATE keyspace if NOT exists weather WITH replication = {'class' : 'SimpleStrategy','replication_factor': 1 };

CREATE TABLE IF NOT exists weather.weather_details(
id text,
incidentNumber text,
temperature float,
conditionDescription text,
datetime time,
zip text,
PRIMARY KEY(incidentNumber,datetime)
);

