CREATE keyspace if NOT exists weather WITH replication = {'class' : 'SimpleStrategy','replication_factor': 1 };

CREATE TABLE IF NOT exists weather.weather_details(
incident_number text,
latitude float,
longitude float,
temp float,
condition_desc text,
date_time text,
zip text,
PRIMARY KEY(incident_number,datetime)
);

