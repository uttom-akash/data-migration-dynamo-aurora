#!/bin/bash
set -e
cd "$(dirname "$0")"

echo "starting docker for savings-dps"
docker compose up -d --wait
echo "started docker for savings-dps"

echo "creating DynamoDB tables"
bash dynamodb.sh
echo "creating DynamoDB tables complete"

echo "seeding sample data"
bash seed-data.sh
echo "seeding complete"

# optional local DynamoDB UI (npm install -g dynamodb-admin)
if command -v dynamodb-admin >/dev/null 2>&1; then
    DYNAMO_ENDPOINT=http://localhost:4566 dynamodb-admin --open
fi
