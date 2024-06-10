package com.example.frontend.models;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class BudgetDeserializer implements JsonDeserializer<Budget> {
    @Override
    public Budget deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String ref = jsonObject.get("referencia").getAsString();
        if (ref.startsWith("OBN")) {
            return context.deserialize(jsonObject, BudgetNewBuild.class);
        } else if (ref.endsWith("CO")) {
            return context.deserialize(jsonObject, BudgetReformKitchen.class);
        } else if (ref.endsWith("A")) {
            return context.deserialize(jsonObject, BudgetReformBathroom.class);
        } else {
            throw new JsonParseException(ref);
        }
    }
}
