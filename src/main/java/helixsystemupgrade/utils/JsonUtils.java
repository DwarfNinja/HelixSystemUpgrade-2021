package helixsystemupgrade.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import helixsystemupgrade.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public static String createJsonArray(List<Account> array) {
        ObjectMapper objMapper = new ObjectMapper();
        ArrayNode  jsonArray = objMapper.createArrayNode();

        for (Object object : array){
            jsonArray.addPOJO(object);
        }
        return jsonArray.toString();
    }
}
