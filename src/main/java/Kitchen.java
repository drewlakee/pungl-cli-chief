import org.apache.commons.cli.*;
import chiefs.AbstractChief;
import chiefs.DefaultChief;
import chiefs.portions.PortionType;
import chiefs.portions.HelpPortion;
import chiefs.portions.PdfMergePortion;

public class Kitchen {

    public static void main(String[] args) {
        Options options = buildCliOptions();
        CommandLine cli = parse(options, args);
        AbstractChief chief = new DefaultChief(cli);

        if (cli.hasOption("h") || cli.hasOption("help") || cli.getOptions().length == 0) {
            HelpPortion helpPortion = new HelpPortion(chief, options);
            chief.addPortion(helpPortion);
        }

        if (cli.hasOption("m") || cli.hasOption("merge")) {
            PdfMergePortion pdfMergePortion = new PdfMergePortion(chief);
            pdfMergePortion.setPortionType(PortionType.INTERMEDIATE);
            chief.addPortion(pdfMergePortion);
        }

        chief.work();
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
