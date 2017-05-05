FROM ubuntu

ENV INFLUXDB_VERSION 1.2.3
ENV GRAFANA_VERSION 4.2.0
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle


ENV DOWNLOAD_URL https://s3-us-west-2.amazonaws.com/grafana-releases/release/grafana_${GRAFANA_VERSION}_amd64.deb

RUN apt-get update && \
    apt-get -y --no-install-recommends install libfontconfig curl ca-certificates wget gnupg software-properties-common && \
    apt-get clean && \
    curl ${DOWNLOAD_URL} > /tmp/grafana.deb && \
    dpkg -i /tmp/grafana.deb && \
    rm /tmp/grafana.deb && \
    curl -L https://github.com/tianon/gosu/releases/download/1.7/gosu-amd64 > /usr/sbin/gosu && \
    chmod +x /usr/sbin/gosu && \
    apt-get autoremove -y && \
    rm -rf /var/lib/apt/lists/* && \
    gpg \
        --keyserver hkp://ha.pool.sks-keyservers.net \
        --recv-keys 05CE15085FC09D18E99EFB22684A14CF2582E0C5



RUN wget -q https://dl.influxdata.com/influxdb/releases/influxdb_${INFLUXDB_VERSION}_amd64.deb.asc && \
    wget -q https://dl.influxdata.com/influxdb/releases/influxdb_${INFLUXDB_VERSION}_amd64.deb && \
    gpg --batch --verify influxdb_${INFLUXDB_VERSION}_amd64.deb.asc influxdb_${INFLUXDB_VERSION}_amd64.deb && \
    dpkg -i influxdb_${INFLUXDB_VERSION}_amd64.deb && \
    rm -f influxdb_${INFLUXDB_VERSION}_amd64.deb*

RUN \
  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  add-apt-repository -y ppa:webupd8team/java && \
  apt-get update && \
  apt-get install -y oracle-java8-installer && \
  rm -rf /var/lib/apt/lists/* && \
  rm -rf /var/cache/oracle-jdk8-installer

COPY docker/config/ /etc/grafana/scripts/
COPY docker/influxdb.conf /etc/influxdb/influxdb.conf
COPY docker/influx_runner.sh /influx_runner.sh
COPY docker/grafana_runner.sh /grafana_runner.sh
COPY build/libs/monitoring-1.0.0.jar /monitoring.jar
COPY docker/runall.sh /runall.sh

VOLUME ["/var/lib/grafana", "/var/log/grafana", "/etc/grafana", "/var/lib/influxdb", "/etc/conf/net-config.yaml"]

EXPOSE 3000
EXPOSE 8086

ENTRYPOINT ["/runall.sh"]
