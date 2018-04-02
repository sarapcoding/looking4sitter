package com.dad;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class SitterProfile extends JsonDeserializer{
    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        long id = node.get("id").asLong();
        String login = node.get("login").textValue();
        String nombre = node.get("nombre").textValue();
        int tarifa = node.get("tarifa").asInt();
        String provincia = node.get("provincia").textValue();
        
        Usuario sitter = new Usuario();
        sitter.setLogin(login);
        sitter.setNombre(nombre);
        sitter.setId(id);
        sitter.setTarifa(tarifa);
        sitter.setProvincia(provincia);
        return sitter;
    }
}
