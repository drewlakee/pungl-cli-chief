package chiefs.portions;

import org.apache.commons.cli.CommandLine;
import chiefs.AbstractChief;

public abstract class AbstractPortion {

    protected PortionType portionType = PortionType.DETERMINED;

    protected AbstractChief chief;

    public AbstractPortion(AbstractChief master) {
        this.chief = master;
    }

    public void setPortionType(PortionType portionType) {
        this.portionType = portionType;
    }

    public boolean typeIs(PortionType portionType) {
        return this.portionType == portionType;
    }

    public abstract void cook(CommandLine cli);
}
