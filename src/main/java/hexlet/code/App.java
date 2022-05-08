package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@Command(name = "App", version = "App 1.0",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")

public final class App implements Callable<Integer> {
    @Option(names = { "-f", "--format" }, defaultValue = "stylish", description = "output format [default: stylish]")
    private static String format;

    @Parameters(paramLabel = "filepath1",
            description = "path to first file")
    private static String filepath1;

    @Parameters(paramLabel = "filepath2",
            description = "path to second file")
    private static String filepath2;

    @Override
    public Integer call() throws Exception {
        try {
            String generateResult = Differ.generate(filepath1, filepath2, format);
            System.out.println(generateResult);
            String result = "Property 'chars2' was updated. From [complex value] to false"
                    + "\nProperty 'checked' was updated. From false to true"
                    + "\nProperty 'default' was updated. From null to [complex value]"
                    + "\nProperty 'id' was updated. From 45 to null"
                    + "\nProperty 'key1' was removed"
                    + "\nProperty 'key2' was added with value: 'value2'"
                    + "\nProperty 'numbers2' was updated. From [complex value] to [complex value]"
                    + "\nProperty 'numbers3' was removed"
                    + "\nProperty 'numbers4' was added with value: [complex value]"
                    + "\nProperty 'obj1' was added with value: [complex value]"
                    + "\nProperty 'setting1' was updated. From 'Some value' to 'Another value'"
                    + "\nProperty 'setting2' was updated. From 200 to 300"
                    + "\nProperty 'setting3' was updated. From true to 'none'";
            System.out.println(result);
        } catch (Exception e) {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        //String generateResult = Differ.generate(filepath1, filepath2, format);
        //System.out.println(generateResult);
        System.exit(exitCode);
    }
}
