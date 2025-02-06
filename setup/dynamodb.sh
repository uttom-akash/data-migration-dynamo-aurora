aws dynamodb --endpoint-url http://localhost:4566 create-table --table-name dev-savings-payment-history --attribute-definitions AttributeName=savingsId,AttributeType=S AttributeName=paymentId,AttributeType=N AttributeName=rppPaymentId,AttributeType=N --key-schema AttributeName=savingsId,KeyType=HASH AttributeName=paymentId,KeyType=RANGE --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5\
  --local-secondary-indexes "[{\"IndexName\": \"lsi-rppPaymentId\",\"KeySchema\":[{\"AttributeName\":\"savingsId\",\"KeyType\":\"HASH\"}, {\"AttributeName\":\"rppPaymentId\",\"KeyType\":\"RANGE\"}], \"Projection\":{\"ProjectionType\":\"ALL\"}}]"

aws dynamodb --endpoint-url http://localhost:4566 update-table --table-name dev-savings-payment-history --attribute-definitions AttributeName=trxId,AttributeType=S --global-secondary-index-updates \
  "[{\"Create\":{\"IndexName\": \"gsi-trxId\",\"KeySchema\":[{\"AttributeName\":\"trxId\",\"KeyType\":\"HASH\"}], \
  \"ProvisionedThroughput\": {\"ReadCapacityUnits\": 10, \"WriteCapacityUnits\": 5      },\"Projection\":{\"ProjectionType\":\"ALL\"}}}]"

aws dynamodb --endpoint-url http://localhost:4566 update-table --table-name dev-savings-payment-history --attribute-definitions AttributeName=trxShortDate,AttributeType=S --global-secondary-index-updates \
  "[{\"Create\":{\"IndexName\": \"gsi-trxShortDate\",\"KeySchema\":[{\"AttributeName\":\"trxShortDate\",\"KeyType\":\"HASH\"}], \
  \"ProvisionedThroughput\": {\"ReadCapacityUnits\": 10, \"WriteCapacityUnits\": 5      },\"Projection\":{\"ProjectionType\":\"ALL\"}}}]"

aws dynamodb --endpoint-url http://localhost:4566 create-table --table-name dev-savings-account --attribute-definitions AttributeName=walletId,AttributeType=S AttributeName=savingsId,AttributeType=S AttributeName=fiAccountNo,AttributeType=S AttributeName=fiAccountId,AttributeType=S --key-schema AttributeName=walletId,KeyType=HASH AttributeName=savingsId,KeyType=RANGE --provisioned-throughput ReadCapacityUnits=500,WriteCapacityUnits=500\
  --local-secondary-indexes \
  "[
    {\"IndexName\": \"lsi-fiAccountNo\",\"KeySchema\":[{\"AttributeName\":\"walletId\",\"KeyType\":\"HASH\"}, {\"AttributeName\":\"fiAccountNo\",\"KeyType\":\"RANGE\"}], \"Projection\":{\"ProjectionType\":\"ALL\"}},
    {\"IndexName\": \"lsi-fiAccountId\",\"KeySchema\":[{\"AttributeName\":\"walletId\",\"KeyType\":\"HASH\"}, {\"AttributeName\":\"fiAccountId\",\"KeyType\":\"RANGE\"}], \"Projection\":{\"ProjectionType\":\"ALL\"}}
  ]"

aws dynamodb --endpoint-url http://localhost:4566 update-table --table-name dev-savings-account --attribute-definitions AttributeName=walletNo,AttributeType=S --global-secondary-index-updates \
  "[{\"Create\":{\"IndexName\": \"gsi-walletNo\",\"KeySchema\":[{\"AttributeName\":\"walletNo\",\"KeyType\":\"HASH\"}], \
  \"ProvisionedThroughput\": {\"ReadCapacityUnits\": 10, \"WriteCapacityUnits\": 5},\"Projection\":{\"ProjectionType\":\"ALL\"}}}]"

aws dynamodb --endpoint-url http://localhost:4566 update-table --table-name dev-savings-account --attribute-definitions AttributeName=rppSubscriptionRequestId,AttributeType=S --global-secondary-index-updates \
  "[{\"Create\":{\"IndexName\": \"gsi-rppSubscriptionRequestId\",\"KeySchema\":[{\"AttributeName\":\"rppSubscriptionRequestId\",\"KeyType\":\"HASH\"}], \
  \"ProvisionedThroughput\": {\"ReadCapacityUnits\": 500, \"WriteCapacityUnits\": 500},\"Projection\":{\"ProjectionType\":\"ALL\"}}}]"

aws dynamodb --endpoint-url http://localhost:4566 update-table --table-name dev-savings-account --attribute-definitions AttributeName=maturityShortDate,AttributeType=S --global-secondary-index-updates \
  "[{\"Create\":{\"IndexName\": \"gsi-maturityShortDate\",\"KeySchema\":[{\"AttributeName\":\"maturityShortDate\",\"KeyType\":\"HASH\"}], \
  \"ProvisionedThroughput\": {\"ReadCapacityUnits\": 10, \"WriteCapacityUnits\": 5},\"Projection\":{\"ProjectionType\":\"ALL\"}}}]"

aws dynamodb --endpoint-url http://localhost:4566 update-table --table-name dev-savings-account --attribute-definitions AttributeName=subscriptionRequestId,AttributeType=S --global-secondary-index-updates \
  "[{\"Create\":{\"IndexName\": \"gsi-subscriptionRequestId\",\"KeySchema\":[{\"AttributeName\":\"subscriptionRequestId\",\"KeyType\":\"HASH\"}], \
  \"ProvisionedThroughput\": {\"ReadCapacityUnits\": 10, \"WriteCapacityUnits\": 5},\"Projection\":{\"ProjectionType\":\"ALL\"}}}]"

aws dynamodb --endpoint-url http://localhost:4566 update-table \
    --table-name dev-savings-account \
    --attribute-definitions '[
      {
          "AttributeName": "startShortDate",
          "AttributeType": "S"
      },
      {
          "AttributeName": "currentState",
          "AttributeType": "S"
      }
    ]' \
    --global-secondary-index-updates '[
        {
            "Create": {
                "IndexName": "gsi-startShortDate-currentState",
                "KeySchema": [
                    {
                        "AttributeName": "startShortDate",
                        "KeyType": "HASH"
                    },
                    {
                        "AttributeName": "currentState",
                        "KeyType": "RANGE"
                    }
                ],
                "Projection": {
                    "ProjectionType": "ALL"
                },
                "ProvisionedThroughput": {
                    "ReadCapacityUnits": 10,
                    "WriteCapacityUnits": 5
                }
            }
        }
    ]'


aws dynamodb --endpoint-url http://localhost:4566 create-table --table-name dev-savings-nominee --attribute-definitions AttributeName=walletId,AttributeType=S AttributeName=id,AttributeType=S --key-schema AttributeName=walletId,KeyType=HASH AttributeName=id,KeyType=RANGE --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5