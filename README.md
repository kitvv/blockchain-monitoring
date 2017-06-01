[Blockchain Monitoring](http://blockchain-monitoring.org)
================
## version '1.3-1.0.0-alpha2'


**Continuous integration:** [![Build Status](https://travis-ci.org/blockchain-monitoring/blockchain-monitoring.svg?branch=master)](https://travis-ci.org/blockchain-monitoring/blockchain-monitoring)

"Blockchain Monitoring" is an open source project designed for **Hyperledger Fabric v1.0**. 

It provides convenient and demonstrative way to represent information
about blockchain fabric network activities.

![demo](http://blockchain-monitoring.org/images/demo.png)
![demo](http://blockchain-monitoring.org/images/dashboard-performance.png)

# About
Project consists of Grafana, Influx DB and "Blockchain Monitoring" as own, which collects and aggregates telemetry from Fabric.
## Requirements
You need Docker and maybe Docker-compose to run "Blockchain Monitoring" and open 3000 and 8086 ports. That's all.
## Installation
You can download docker image with command: `docker pull blockchainmonitoring/blockchain-monitoring:latest`

With docker-compose create docker-compose.yaml file:
```yaml
version: '2'

services:
  monitoring:
    container_name: blockchain-monitoring
    image: blockchainmonitoring/blockchain-monitoring:latest
    volumes:
      - $FABRIC_NET_CONFIG:/etc/conf/net-config.yaml
    ports:
      - "3000:3000"
      - "8086:8086"
```
and net-config.yaml file:
```yaml
organisations:
- name: 'foo'
  ca:
    name: 'ca-foo'
    address: 'http://172.25.0.177:7054'
  enroll:
    login: 'fadmin'
    pass: 'foo'
    msp: 'foo'
  peers:
    - name: 'peer-foo'
      address: 'grpc://172.25.0.104:7051'

    - name: 'peer-foo-02'
      address: 'grpc://172.25.0.105:7051'

    - name: 'peer-foo-03'
      address: 'grpc://172.25.0.106:7051'

- name: 'bar'
  ca:
    name: 'ca-foo'
    address: 'http://ca-foo:7054'
  enroll:
    login: 'badmin'
    pass: 'bar'
    msp: 'bar'
  peers:
    - name: 'peer-bar'
      address: 'grpc://172.25.0.107:7051'

channels:
- name: 'pubfoochan'
  msp:
  - 'foo'
  - 'bar'

  endorsers:
  - name: 'peer-foo'
    msp: 'foo'
    address: 'grpc://172.25.0.104:7051'

  - name: 'peer-foo-02'
    msp: 'foo'
    address: 'grpc://172.25.0.105:7051'

  - name: 'peer-foo-03'
    msp: 'foo'
    address: 'grpc://172.25.0.106:7051'

  - name: 'peer-bar'
    msp: 'bar'
    address: 'grpc://172.25.0.107:7051'

  orderers:
  - name: 'foo-orderer'
    msp:
    - 'foo'
    - 'bar'
    address: 'grpc://172.25.0.102:7050'

  events:
  - name: 'ev-peer-foo'
    msp: 'foo'
    address: 'grpc://172.25.0.104:7053'

  - name: 'ev-peer-foo-02'
    msp: 'foo'
    address: 'grpc://172.25.0.105:7053'

  - name: 'ev-peer-foo-03'
    msp: 'foo'
    address: 'grpc://172.25.0.106:7053'

  - name: 'ev-peer-bar'
    msp: 'bar'
    address: 'grpc://172.25.0.107:7053'

  chaincodes:
  - name: 'prettycode'
    path: 'github.xyz/thebestcode/prettycode'
    version: '1.0'
```

This file describes fabric network configuration and contains two main sections: organization and channels.
Orgranization section provides information about fabric-CA, fabric-peers name and address, MSP-ID. 
Next section channels show us which peers are connected to channel, their addresses, names and msp-id.

Also you need to set environment variable $FABRIC_NET_CONFIG for net-config.yaml file (**it must be absolute path**) and after that just write:
```bash
docker-compose up
```
If monitoring seccessully started you can access to it by visiting http://localhost:3000 admin:admin

## Use monitoring in your code
"Blockchain Monitoring" provides you simple API, written in Java. 
Visit [link](https://github.com/blockchain-monitoring/blockchain-monitoring-api) for more information.

## Email notification
For example, I set if we get invoke or query send me email notification and attach graph of metrics

## Crash reports
If you want to share crash information with blockchain monitoring owners, please add following env variables:

`REPORTS_ENABLED=true`

`USER_EMAIL=<your contact email>`

Following information will be send:
* user email
* date
* external ip address
* stacktrace
* exception name
* exception message

### Invoke
![demo](http://blockchain-monitoring.org/images/invoke-alert.png)
### Query
![demo](http://blockchain-monitoring.org/images/query-alert.png)
