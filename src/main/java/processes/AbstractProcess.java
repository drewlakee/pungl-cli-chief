package processes;

import org.apache.commons.cli.CommandLine;
import processes.fibers.AbstractFiber;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractProcess {

    protected final CommandLine cli;

    protected final List<AbstractFiber> fibers;

    public AbstractProcess(CommandLine cli) {
        this.fibers = new ArrayList<>();
        this.cli = cli;
    }

    public void addFiber(AbstractFiber fiber) {
        fibers.add(fiber);
    }

    public abstract void execute();
}
