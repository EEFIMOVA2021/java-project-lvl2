package hexlet.code;

import java.util.Map;
import java.util.List;

import hexlet.code.formatter.Json;
import hexlet.code.formatter.Plain;
import hexlet.code.formatter.Stylish;

public class Formatter {
    public static String format(List<Map<String, Object>> list, String format) throws Exception {
        switch (format) {
            case "plain":
                return Plain.render(list);
            case "json":
                return Json.render(list);
            default:
                return Stylish.render(list);
        }
    }
}
