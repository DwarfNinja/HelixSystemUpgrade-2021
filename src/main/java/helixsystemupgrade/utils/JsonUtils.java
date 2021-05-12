package helixsystemupgrade.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    public static String convertObjectToJson(Object object) {
        ObjectMapper objMapper = new ObjectMapper();

        try {
            // Converting the Java object into a JSON string
            String jsonStringOfObject = objMapper.writeValueAsString(object);
            // Displaying Java object into a JSON string
            return jsonStringOfObject;
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return "ERROR: JsonProcessingException!";
        }
    }
}
