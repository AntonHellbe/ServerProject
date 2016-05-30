package org.demo.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sebastian Börebäck, Anton on 2016-05-03.
 * A Parser for parsing a JSON to a GrantedAuthority
 */
public class GrantedAuthorityDeserializer extends JsonDeserializer<List<GrantedAuthority>> {

	private static final Logger log = LoggerFactory.getLogger(GrantedAuthorityDeserializer.class);

	/**
	 * Parse a GrantedAuthority JSON to GrantedAuthority Java object
	 * @param jsonParser the JSON
	 * @param deserializationContext
	 * @return a GrantedAuthority Java object
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
    @Override
    public List<GrantedAuthority> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
	    List<GrantedAuthority> auths = new ArrayList<>();

	    ObjectCodec oc = jsonParser.getCodec();
	    JsonNode node = oc.readTree(jsonParser);

	    node.forEach(jsonNode -> {
		    auths.add(new SimpleGrantedAuthority(jsonNode.get("authority").asText()));
	    });

	    return auths;
    }
}
