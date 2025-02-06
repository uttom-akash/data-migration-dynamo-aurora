#!/bin/bash

# Check if docker-compose is installed
if ! command -v docker compose &> /dev/null
then
    echo "docker-compose could not be found, please install it first."
    exit 1
fi

# Execute docker-compose in detached mode with the provided arguments
docker compose up -d "$@"

# Check if docker-compose is up
if [ "$(docker compose ps -q)" ]; then
  echo "executing s3 script"
  ./s3/s3.sh
else
    echo "docker-compose is not running."
    exit 1
fi

# Check if docker-compose is up
if [ "$(docker compose ps -q)" ]; then
  echo "executing ssm script"
  ./ssm/ssm-parameters.sh
else
    echo "docker-compose is not running."
    exit 1
fi

if [ "$(docker compose ps -q)" ]; then
  echo "executing sqs script"
  ./sqs/sqs.sh
else
    echo "docker-compose is not running."
    exit 1
fi

sudo install -d -m 777 /opt/vanguard/savings/logs