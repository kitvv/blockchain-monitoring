# blockchain-monitoring
**Continuous integration:** [![Build Status](https://travis-ci.org/blockchain-monitoring/blockchain-monitoring.svg?branch=master)](https://travis-ci.org/blockchain-monitoring/blockchain-monitoring)

http://blockchain-monitoring.org

![blockchain-monitoring-architecture](http://blockchain-monitoring.org/images/architecture.png)

## About
Monitoring for blockchain "fabric" network.

Load and start Docker images to run Grafana + InfluexDB for monitoring status network.
Add dashboards and datasources to grafana.		
Add user and db to influxdb

## How to use

### Start
        $ ./start /opt/path/to/file/template_network-config.yaml

   '/opt/path/to/file/template_network-config.yaml' - path to fabric_config_file, for example https://github.com/blockchain-monitoring/blockchain-monitoring/blob/master/docker/template_network-config.yaml)

### Down
        $ ./down

## Advanced
Please checkout project:

Use this docker-compose.yml to up "blockchain-monitoring" https://github.com/blockchain-monitoring/blockchain-monitoring/blob/master/docker/docker-compose.yml

### Pay attention! in docker-compose we use networks "dev_net"
    
    networks:
      dev_net:
      external: true

    services:
      monitoring:
        networks:
            - dev_net

in docker-compose we will pull our stable project https://store.docker.com/community/images/blockchainmonitoring/blockchain-monitoring 

$FABRIC_NET_CONFIG is path to your network config file. (for example network config file: https://github.com/blockchain-monitoring/blockchain-monitoring/blob/master/docker/template_network-config.yaml)

In template_network-config.yaml please change "ip" and "name" on your network.


    export FABRIC_NET_CONFIG=/opt/path/to/file/template_network-config.yaml
    docker-compose up

after run docker we need to go http://localhost:3000 (login: admin, password: admin)
we can see our monitoring dashboard for your network.
