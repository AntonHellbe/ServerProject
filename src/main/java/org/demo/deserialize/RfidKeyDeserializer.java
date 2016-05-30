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
 * @author Sebastian Börebäck, Anton Hellbe on 2016-05-03.
 * Deserializes json for RFIDKey
 */
public class RfidKeyDeserializer extends JsonDeserializer<RfidKey> {

    /**
     * Serliazing for RFID-key, its needed when we receive a new Account-object the program wont know how to map up an RFID-key object
     * this class tells how to map the RFID-object.
     * @param jsonParser the JSON data recieved
     * @param deserializationContext not used
     * @return an RFID-key object
     * @throws IOException throws exception if needed
     * @throws JsonProcessingException throws exception if needed
     */


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
