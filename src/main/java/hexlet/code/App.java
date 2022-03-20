package hexlet.code;

import hexlet.code.Differ;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.io.FileNotFoundException;
//import java.util.Scanner;
//import java.util.ArrayList;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
//import java.util.Map;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;

@Command(name = "App", version = "App 1.0",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")

public class App implements Runnable{
    @Option(names = { "-f", "--format" }, description = "output format [default: stylish]")
    String format = "stylish";

    @Parameters(paramLabel = "filepath1",
            description = "path to first file")
    private static String filepath1;

    @Parameters(paramLabel = "filepath2",
            description = "path to second file")
    private static String filepath2;

    public String getGreeting() {
        return "Hello World!";
    }

    @Override
    public void run() {
    }

    public static void main(String[] args) throws Exception {
        //System.out.println(new App().getGreeting());
        int exitCode = new CommandLine(new App()).execute(args);
        String diff = Differ.generate(filepath1, filepath2);
        System.exit(exitCode);
    }
}
