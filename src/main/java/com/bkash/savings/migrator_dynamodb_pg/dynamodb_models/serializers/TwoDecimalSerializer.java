package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Objects;

/**
 * This is a custom JSON serializer class which will be used to precise a {@link Double} <br>
 * typed field value to the two decimal point. e.g. 10.50 <br><br>
 *
 * @author Mahmudul Hasan Rana
 * @see JsonSerializer
 * @since 2023-10-31
 */
public class TwoDecimalSerializer extends JsonSerializer<Double> {

    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Format the double value with two decimal places and write it to the JSON generator
        if (Objects.nonNull(value)) gen.writeNumber(String.format("%.2f", value));
    }

}
