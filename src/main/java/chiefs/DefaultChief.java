package chiefs;

import org.apache.commons.cli.CommandLine;
import chiefs.portions.AbstractPortion;
import chiefs.portions.PortionType;

public class DefaultChief extends AbstractChief {

    public DefaultChief(CommandLine cli) {
        super(cli);
    }

    @Override
    public void work() {
        for (AbstractPortion portion : portions) {
            portion.cook(cli);

            if (portion.typeIs(PortionType.DETERMINED)) {
                return;
            }
        }
    }
}
