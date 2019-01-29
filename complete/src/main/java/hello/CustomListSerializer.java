package hello;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomListSerializer extends StdSerializer<List<Item>> {

    public CustomListSerializer() {
        this(null);
    }

    public CustomListSerializer(Class<List<Item>> t) {
        super(t);
    }

    @Override
    public void serialize(List<Item> items, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        final List<Integer> ids = new ArrayList<>();
        items.forEach(item -> ids.add(item.getId()));
        jsonGenerator.writeObject(ids);
    }
}
