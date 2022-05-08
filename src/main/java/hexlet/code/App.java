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
