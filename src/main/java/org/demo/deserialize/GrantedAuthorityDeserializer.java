package org.demo.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;

/**
 * Created by Anton on 2016-05-03.
 */
public class GrantedAuthorityDeserializer extends JsonDeserializer<GrantedAuthority> {

    @Override
    public GrantedAuthority deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        return null;
    }
}
