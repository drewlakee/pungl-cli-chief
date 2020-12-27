package processes.fibers;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import processes.AbstractProcess;

public class HelpFiber extends AbstractFiber {

    private final Options options;

    public HelpFiber(AbstractProcess process, Options options) {
        super(process);

        this.options = options;
    }

    @Override
    public void process(CommandLine cli) {
        System.out.println("using: [--help] [--merge] [--stacktrace] <command [command ...]> [<arguments>] \n");
        for (Option option : options.getOptions()) {
            String optionString = String.format("\t-%s, --%s %s", option.getOpt(), option.getLongOpt(), option.getDescription());
            System.out.println(optionString);
        }
    }
}
