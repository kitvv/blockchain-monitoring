#!/bin/bash

cd docker/
docker-compose down
cd -
docker rmi blockchainmonitoring/blockchain-monitoring 
