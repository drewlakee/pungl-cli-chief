import org.apache.commons.cli.*;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static Options options;

    public static void main(String[] args) {
        CommandLine cmd = parse(args);

        if (cmd.hasOption("h") || cmd.hasOption("help") || cmd.getArgs().length == 0) {
            System.out.println("Options: \n");
            for (Option option : options.getOptions()) {
                String optionString = String.format("\t-%s, --%s \n\t%s\n", option.getOpt(), option.getLongOpt(), option.getDescription());
                System.out.println(optionString);
            }

            return;
        }

        if (cmd.hasOption("m") || cmd.hasOption("merge")) {
            List<String> mergeTargets = cmd.getArgList();

            if (mergeTargets.isEmpty()) {
                System.out.println("Command hasn't file arguments to merge");
                return;
            }

            PDFMergerUtility mergerUtility = new PDFMergerUtility();
            List<File> mergedFiles = new ArrayList<>();
            for (String pathToTarget : mergeTargets) {
                File target = new File(pathToTarget);
                if (target.isFile()) {

                    if (!target.getName().endsWith(".pdf")) {
                        System.out.printf("%s file is not pdf", target.getName());
                        return;
                    }

                    try {
                        mergedFiles.add(target);
                        mergerUtility.addSource(target);
                        System.out.printf("%s merged%n", target);
                    } catch (FileNotFoundException e) {
                        if (cmd.hasOption("s") || cmd.hasOption("stacktrace")) {
                            e.printStackTrace();
                        } else {
                            System.out.println("Merge process failed");
                        }
                    }
                } else {
                    System.out.println(String.format("%s is not file", pathToTarget));
                    return;
                }
            }

            mergerUtility.setDestinationFileName("merged-" + mergedFiles.get(0).getName());

            try {
                mergerUtility.mergeDocuments(null);
            } catch (IOException e) {
                if (cmd.hasOption("s") || cmd.hasOption("stacktrace")) {
                    e.printStackTrace();
                } else {
                    System.out.println("Merge process failed");
                }
            }

            return;
        }
    }

    public static CommandLine parse(String[] arguments) {
        CommandLineParser parser = new DefaultParser();

        initCMDOptions();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, arguments);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        return cmd;
    }

    public static void initCMDOptions() {
        options = new Options();
        options.addOption("m", "merge", false, "Merge all input pdf files");
        options.addOption("s", "stacktrace", false, "Show error stack traces");
        options.addOption("h", "help", false, "Show options list");
    }
}
