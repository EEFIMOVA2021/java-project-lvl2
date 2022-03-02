package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "App", version = "App 1.0",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")

public class App implements Runnable{
    @Option(names = { "-f", "--format=format" }, description = "output format [default: stylish]")
    String format = "stylish";

    @Parameters(paramLabel = "filepath1",
            description = "path to first file")
    String filepath1;

    @Parameters(paramLabel = "filepath2",
            description = "path to second file")
    String filepath2;

    public String getGreeting() {
        return "Hello World!";
    }

    @Override
    public void run() {
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
