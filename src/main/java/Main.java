import org.apache.commons.cli.*;
import processes.AbstractProcess;
import processes.DefaultProcess;
import processes.fibers.FiberType;
import processes.fibers.HelpFiber;
import processes.fibers.PdfMergeFiber;

public class Main {

    public static void main(String[] args) {
        Options options = buildCliOptions();
        CommandLine cli = parse(options, args);
        AbstractProcess process = new DefaultProcess(cli);

        if (cli.hasOption("h") || cli.hasOption("help") || cli.getOptions().length == 0) {
            HelpFiber helpFiber = new HelpFiber(process, options);
            process.addFiber(helpFiber);
        }

        if (cli.hasOption("m") || cli.hasOption("merge")) {
            PdfMergeFiber pdfMergeFiber = new PdfMergeFiber(process);
            pdfMergeFiber.setFiberType(FiberType.INTERMEDIATE);
            process.addFiber(pdfMergeFiber);
        }

        process.execute();
    }

    public static CommandLine parse(Options options, String[] arguments) {
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, arguments);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        return cmd;
    }

    public static Options buildCliOptions() {
        Options options = new Options();
        options.addOption("m", "merge", false, "\t\tMerge all input pdf files");
        options.addOption("s", "stacktrace", false, "\tShow error stack traces");
        options.addOption("h", "help", false, "\t\tShow options list");
        return options;
    }
}
