import csv
import json

def csv_to_json(csv_file, json_file, table_name):
    cnt = 25
    with open(csv_file, mode='r') as f:
        reader = csv.DictReader(f)
        items = []
        for row in reader:
            # Convert CSV rows to DynamoDB 'PutRequest' format
            item = {
                "PutRequest": {
                    "Item": {k: {"S": v} for k, v in row.items()}  # Assuming all fields are strings (S)
                }
            }
            items.append(item)
            if len(items) >= 1:
                break

    with open(json_file, mode='w') as jsonf:
        json.dump({table_name: items}, jsonf)

# Example usage
csv_to_json('uat payment history table data dump.csv', 'data.json', 'dev-savings-payment-history')

