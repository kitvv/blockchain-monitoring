#!/usr/bin/env bash

cd /

./init_grafana.sh &
./init_influx.sh &
java -jar fabric-monitoring.jar
