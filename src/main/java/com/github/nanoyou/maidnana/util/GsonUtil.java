package com.github.nanoyou.maidnana.util;

import com.github.nanoyou.maidnana.entity.Body;
import com.github.nanoyou.maidnana.entity.PlainBody;
import com.github.nanoyou.maidnana.entity.TemplateBody;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GsonUtil {
    public static Gson gson = new GsonBuilder().setPrettyPrinting()
            .registerTypeAdapter(UUID.class, new TypeAdapter<UUID>() {

                @Override
                public void write(JsonWriter out, UUID value) throws IOException {
                    out.value(value.toString());
                }

                @Override
                public UUID read(JsonReader in) throws IOException {
                    return UUID.fromString(in.nextString());
                }
            })
            .registerTypeHierarchyAdapter(Body.class, new TypeAdapter<Body>() {

                @Override
                public void write(JsonWriter out, Body value) {
                    var r = new HashMap<String, Object>();
                    if (value instanceof PlainBody) {
                        var body = (PlainBody) value;
                        r.put("type", "plain");
                        r.put("content", body.getContent());
                    } else {
                        var body = (TemplateBody) value;
                        r.put("type", "template");
                        r.put("template", body.getTemplateID());
                        r.put("var", body.getVar());
                    }
                    gson.toJson(r, Map.class, out);
                }

                @SuppressWarnings("all")
                @Override
                public Body read(JsonReader in) {
                    Map<String, Object> r = gson.fromJson(in, Map.class);
                    var type = (String) r.get("type");
                    Body body;
                    if ("plain".equals(type)) {
                        PlainBody plainBody = new PlainBody();
                        plainBody.setContent((String) r.get("content"));
                        body = plainBody;
                    } else {
                        TemplateBody templateBody = new TemplateBody();
                        var uuid = (UUID) r.get("template");
                        templateBody.setTemplateID(uuid);
                        templateBody.setVar(new HashMap<String, String>((Map) r.get("var")));

                        body = templateBody;
                    }
                    return body;
                }
            })
            .create();
}
