#!/usr/bin/env bash

cd /

nohup ./init_grafana.sh
nohup ./init_influx.sh
java -jar fabric-monitoring.jar
