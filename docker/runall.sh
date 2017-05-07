#!/usr/bin/env bash

cd /

nohup ./grafana_runner.sh
nohup ./influx_runner.sh
java -jar fabric-monitoring.jar
