#!/bin/bash
# Seeds the dev-store-product table in LocalStack with sample products,
# 25 items per batch-write-item call.
set -e

export AWS_ACCESS_KEY_ID=test
export AWS_SECRET_ACCESS_KEY=test
export AWS_DEFAULT_REGION=us-east-1
ENDPOINT=http://localhost:4566
TABLE=dev-store-product
TOTAL=${1:-200}

CATEGORIES=(electronics books clothing grocery toys)

i=1
while [ $i -le $TOTAL ]; do
  items=""
  for _ in $(seq 1 25); do
    [ $i -gt $TOTAL ] && break
    CATEGORY=${CATEGORIES[$((i % 5))]}
    PRICE="$((i % 90 + 10)).99"
    STOCK=$((i % 500))
    items+="{\"PutRequest\": {\"Item\": {
      \"productId\":     {\"S\": \"product-$i\"},
      \"name\":          {\"S\": \"Product $i\"},
      \"category\":      {\"S\": \"$CATEGORY\"},
      \"price\":         {\"S\": \"$PRICE\"},
      \"stockQuantity\": {\"N\": \"$STOCK\"},
      \"createdAt\":     {\"S\": \"2024-01-01 10:00:00:000 +0000\"}
    }}},"
    i=$((i + 1))
  done
  aws dynamodb batch-write-item --endpoint-url $ENDPOINT --no-cli-pager \
    --request-items "{\"$TABLE\": [${items%,}]}" > /dev/null
  echo "seeded $((i - 1))/$TOTAL products"
done

echo "seed complete"
