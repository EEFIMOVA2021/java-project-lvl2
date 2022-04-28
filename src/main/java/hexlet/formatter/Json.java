package hexlet.formatter;

import java.util.Map;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {
    public static String getStringValuesJson(List<Map<String, Object>> list) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = "";
        for (Map<String, Object> map: list) {
            result += objectMapper.writeValueAsString(map);
        }
        return result;
    }
}
