package org.demo.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.demo.model.RfidKey;

import java.io.IOException;

/**
 * @author Sebastian Börebäck on 2016-05-03.
 * Deserializes json for RFIDKey
 */
public class RfidKeyDeserializer extends JsonDeserializer<RfidKey> {


    @Override
    public RfidKey deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

	    RfidKey key = new RfidKey();
	    key.setId(node.get("id").asText());
	    key.setEnabled(node.get("enabled").asBoolean());
	    return  key;
    }
}
