package com.example.queue.Queue.domain.DTOs.security;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Iterator;

public class CustomerAuthenticatedSerializer extends JsonDeserializer<CustomerAuthenticatedDto> {

    @Override
    public CustomerAuthenticatedDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {

        CustomerAuthenticatedDto authenticatedDto = new CustomerAuthenticatedDto();

        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        authenticatedDto.setId(node.get("id").asText());
        authenticatedDto.setName(node.get("name").asText());
        authenticatedDto.setEmail(node.get("email").asText());

        Iterator<JsonNode> elements = node.get("authorities").elements();
        while (elements.hasNext()) {
            JsonNode next = elements.next();
            JsonNode authority = next.get("authority");
            authenticatedDto.getAuthorities().add(new SimpleGrantedAuthority(authority.asText()));
        }
        return authenticatedDto;
    }
}