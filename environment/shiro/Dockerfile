FROM ubuntu:16.04
LABEL maintainer="cL0und <cL0und@Syclover> , leixiao <leixiao@Syclover>"
ADD jre-8u251-linux-x64.tar.gz /
COPY sctf2020_jar-0.0.1-SNAPSHOT.jar /
RUN apt update && \
    apt install -y net-tools && \
    apt install -y iputils-ping && \
    apt install -y wget && \
    apt install -y netcat && \
    useradd -m ctfer
USER ctfer

