package processes;

import org.apache.commons.cli.CommandLine;
import processes.fibers.AbstractFiber;
import processes.fibers.FiberType;

public class DefaultProcess extends AbstractProcess {

    public DefaultProcess(CommandLine cli) {
        super(cli);
    }

    @Override
    public void execute() {
        for (AbstractFiber fiber : fibers) {
            fiber.process(cli);

            if (fiber.typeIs(FiberType.DETERMINED)) {
                return;
            }
        }
    }
}
