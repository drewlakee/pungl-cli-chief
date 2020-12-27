package chiefs.portions;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import chiefs.AbstractChief;

public class HelpPortion extends AbstractPortion {

    private final Options options;

    public HelpPortion(AbstractChief process, Options options) {
        super(process);
        this.options = options;
    }

    @Override
    public void cook(CommandLine cli) {
        System.out.println("how to cook: [--help] [--merge] [--stacktrace] <command [command ...]> [<arguments>] \n");
        for (Option option : options.getOptions()) {
            String optionString = String.format("\t-%s, --%s %s", option.getOpt(), option.getLongOpt(), option.getDescription());
            System.out.println(optionString);
        }
    }
}
