CREATE keyspace if NOT exists weather WITH replication = {'class' : 'SimpleStrategy','replication_factor': 1 };
