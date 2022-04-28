package hexlet.code;

import java.util.Map;
import java.util.List;
import hexlet.formatter.Json;
import hexlet.formatter.Plain;
import hexlet.formatter.Stylish;

public class Formatter {
    public static String getStringFormat(List<Map<String, Object>> list, String format) throws Exception {
        if (format.equals("plain")) {
            return Plain.getStringValuesPlain(list);
        } else if (format.equals("json")) {
            return Json.getStringValuesJson(list);
        } else {
            return Stylish.getStringValuesStylish(list);
        }
    }
}
