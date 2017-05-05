#!/usr/bin/env bash

cd /

./grafana_runner.sh &
./influx_runner.sh &
java -jar monitoring.jar
