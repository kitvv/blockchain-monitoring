#!/bin/bash

echo "FABRIC_NET_CONFIG=$1"

cd docker/
export FABRIC_NET_CONFIG=$1
docker-compose up
