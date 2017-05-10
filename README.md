# blockchain-monitoring
http://blockchain-monitoring.org

## About
Monitoring for blockchain "fabric" network.

Load and start Docker images to run Grafana + InfluexDB for monitoring status network.
Add dashboards and datasources to grafana.		
Add user and db to influxdb

## How to use

Please checkout project:

Use this docker-compose.yml to up "blockchain-monitoring" https://github.com/blockchain-monitoring/blockchain-monitoring/blob/master/docker/docker-compose.yml

in docker-compose we will pull our stable project https://store.docker.com/community/images/blockchainmonitoring/blockchain-monitoring 

$FABRIC_NET_CONFIG is path to your network config file. (for example network config file: https://github.com/blockchain-monitoring/blockchain-monitoring/blob/master/docker/template_network-config.yaml)

after run docker we need to go http://localhost:3000 (login: admin, password: admin)
we can see our monitoring dashboard for your network.
