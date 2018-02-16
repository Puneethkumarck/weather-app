#!/bin/bash

if [[ $1 = 'cassandra' ]]; then

	for f in docker-entrypoint-initdb.d/*; do
	  case "$f" in
	    *.cql) echo "$0: running $f" && until cqlsh -f "$f"; do >&2 echo "Cassandra is unavailable - sleeping"; sleep 2; done & ;;
	  esac
	  echo
	done
	
fi

exec /docker-entrypoint.sh "$@"