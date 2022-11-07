package models.chatClients.fileOperations;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import models.Message;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JsonChatFileOperations implements ChatFileOperations {
    private final Gson gson;
    private static final String MESSAGE_FILE = "./messages.json";

    public JsonChatFileOperations() {
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(LocalDateTime.class, new LocalDatetimeSerializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDatetimeDeserializer())
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void writeMessages(List<Message> messages) {
        String jsonText = gson.toJson(messages);

        try {
            FileWriter writer = new FileWriter(MESSAGE_FILE);
            writer.write(jsonText);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Message> readMessages() {
        List<Message> messages;
        try {
            FileReader reader = new FileReader(MESSAGE_FILE);
            BufferedReader bufferedReader = new BufferedReader(reader);

            StringBuilder jsonText = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonText.append(line);
            }

            Type targetType = new TypeToken<ArrayList<Message>>(){}.getType();
            messages = gson.fromJson(jsonText.toString(), targetType);

            return messages;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}

class LocalDatetimeSerializer implements JsonSerializer<LocalDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");

    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(formatter.format(localDateTime));
    }
}

class LocalDatetimeDeserializer implements JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalDateTime.parse(
                jsonElement.getAsString(),
                DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss")
        );
    }
}