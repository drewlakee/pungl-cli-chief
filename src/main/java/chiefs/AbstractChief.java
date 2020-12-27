package chiefs;

import org.apache.commons.cli.CommandLine;
import chiefs.portions.AbstractPortion;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractChief {

    protected final CommandLine cli;

    protected final List<AbstractPortion> portions;

    public AbstractChief(CommandLine cli) {
        this.portions = new ArrayList<>();
        this.cli = cli;
    }

    public void addPortion(AbstractPortion portion) {
        portions.add(portion);
    }

    public abstract void work();
}
