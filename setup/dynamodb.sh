#!/bin/bash
# Creates the DynamoDB source table in LocalStack.
set -e

export AWS_ACCESS_KEY_ID=test
export AWS_SECRET_ACCESS_KEY=test
export AWS_DEFAULT_REGION=us-east-1
ENDPOINT=http://localhost:4566

aws dynamodb create-table --endpoint-url $ENDPOINT \
  --table-name dev-store-product \
  --attribute-definitions AttributeName=productId,AttributeType=S \
  --key-schema AttributeName=productId,KeyType=HASH \
  --billing-mode PAY_PER_REQUEST \
  --no-cli-pager

echo "created table dev-store-product"
