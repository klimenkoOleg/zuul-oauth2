#!/usr/bin/env bash
docker build -t oklimenko/api-gateway-zuul .
docker tag oklimenko/api-gateway-zuul oklimenko/api-gateway-zuul:0.4.0