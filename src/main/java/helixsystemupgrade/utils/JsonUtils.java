package helixsystemupgrade.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import javax.json.*;
import java.io.FileReader;
import java.util.List;

public class JsonUtils {

    public static String convertObjectToJson(Object object) {
        ObjectMapper objMapper = new ObjectMapper();
        try {
            // Converting the Java object into a JSON string
            String jsonStringOfObject = objMapper.writeValueAsString(object);
            // Displaying Java object into a JSON string
            return jsonStringOfObject;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "ERROR [JsonProcessingException]: Error parsing Json!";
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

    public static String getRandomObjectFromArray() {
        JSONParser jsonParser = new JSONParser();

        try {
            FileReader fileReader = new FileReader("C:/Users/Cendur Oyib/IdeaProjects/HelixSystemUpgrade-2021/src/main/resources/all-products.json");
//            JSONArray jsonArray = (JSONArray) jsonParser.parse(fileReader);
//            int randomIndex = NumberUtils.getRandomNumberInRange(0, jsonArray.size());
//            return jsonArray.get(randomIndex);

            JsonReader jsonReader = Json.createReader(fileReader);
            JsonStructure jsonValue = jsonReader.read();

            if (jsonValue.getValueType() == JsonValue.ValueType.ARRAY) {
                JsonArray jsonArray = (JsonArray) jsonValue;
                int randomIndex = NumberUtils.getRandomNumberInRange(0, jsonArray.size());
                JsonObject jsonObject = (JsonObject) jsonArray.getJsonObject(randomIndex);
                return  jsonObject.toString();
            }
            return "SHEESH";

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR [FileNotFoundException]: File not found!";
        }
    }
}
