package hexlet.code;

import java.util.Map;
import java.util.List;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String dataFromFile1 = FileReader.getDataFromFile(filepath1);
        String dataFromFile2 = FileReader.getDataFromFile(filepath2);
        Map<String, Object> map1 = Parser.parseFile(dataFromFile1, getFileFormat(filepath1));
        Map<String, Object> map2 = Parser.parseFile(dataFromFile2, getFileFormat(filepath1));
        List<Map<String, Object>> resList = Tree.build(map1, map2);
        String generateResult = Formatter.format(resList, format);
        return generateResult;
    }

    private static String getFileFormat(String filepath) {
        Pattern pattern = Pattern.compile("\\..+");
        Matcher matcher = pattern.matcher(filepath);
        String format = "";
        while (matcher.find()) {
            format += filepath.substring(matcher.start(), matcher.end());
        }
        return format.substring(1);
    }
}
