#!/usr/bin/env bash
docker run -p 8081:8081 oklimenko/api-gateway-zuul
#to change port to 9001 uncomment this:
#docker run -p 9001:9001 oklimenko/docker-spring --server.port=9001