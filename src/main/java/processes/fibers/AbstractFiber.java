package processes.fibers;

import org.apache.commons.cli.CommandLine;
import processes.AbstractProcess;
import processes.FiberType;

public abstract class AbstractFiber {

    protected FiberType fiberType = FiberType.DETERMINED;

    protected AbstractProcess process;

    public AbstractFiber(AbstractProcess process) {
        this.process = process;
    }

    public void setFiberType(FiberType fiberType) {
        this.fiberType = fiberType;
    }

    public boolean typeIs(FiberType fiberType) {
        return this.fiberType == fiberType;
    }

    public abstract void process(CommandLine cli);
}
