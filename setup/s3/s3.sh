#aws --endpoint-url=http://localhost:4566 s3 mb s3://bucket

#aws --endpoint-url=http://localhost:4566 s3 cp ./s3/server.truststore s3://bucket

aws --endpoint-url=http://localhost:4566 s3 mb s3://dev
aws --endpoint-url=http://localhost:4566 s3 cp ./s3/signature-public-certificate-idlc.cer  s3://dev/jks/signature-public-certificate-idlc.cer
aws --endpoint-url=http://localhost:4566 s3 cp ./s3/sit-savings-keystore.pfx  s3://dev/jks/sit-savings-keystore.pfx
