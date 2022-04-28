package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parseFile(String data, String format)  throws Exception {
        Map<String, Object> map = null;
        if (format.equals("json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            map = objectMapper.readValue(data, Map.class);
        } else {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            map = objectMapper.readValue(data, Map.class);
        }
        return map;
    }
}
