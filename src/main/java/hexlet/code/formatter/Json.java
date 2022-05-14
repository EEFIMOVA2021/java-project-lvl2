package hexlet.code.formatter;

import java.util.Map;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {
    public static String render(List<Map<String, Object>> list) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = "";
        for (Map<String, Object> map: list) {
            result += objectMapper.writeValueAsString(map);
        }
        return result;
    }
}
