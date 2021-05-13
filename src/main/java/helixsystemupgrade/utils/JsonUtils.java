package helixsystemupgrade.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import javax.json.*;
import java.io.InputStream;
import java.util.List;

public class JsonUtils {

    public static String convertObjectToJson(Object object) {
        ObjectMapper objMapper = new ObjectMapper();
        try {
            // Converting the Java object into a JSON string
            String jsonStringOfObject = objMapper.writeValueAsString(object);
            return jsonStringOfObject;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public static String createJsonArray(List<?> array) {
        ObjectMapper objMapper = new ObjectMapper();
        ArrayNode jsonArray = objMapper.createArrayNode();

        for (Object object : array) {
            jsonArray.addPOJO(object);
        }
        return jsonArray.toString();
    }

    public static String getRandomObjectFromArray(String filepath) {
        try {
            InputStream inputStream = JsonUtils.class.getClassLoader().getResourceAsStream(filepath);
            JsonReader jsonReader = Json.createReader(inputStream);
            JsonStructure jsonValue = jsonReader.read();

            if (jsonValue.getValueType() == JsonValue.ValueType.ARRAY) {
                JsonArray jsonArray = (JsonArray) jsonValue;
                int randomIndex = NumberUtils.getRandomNumberInRange(0, jsonArray.size());
                JsonObject jsonObject = jsonArray.getJsonObject(randomIndex);
                return  jsonObject.toString();
            }
            throw new Exception("ERROR: Parsed JSONValue was not a JSONArray!");

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
