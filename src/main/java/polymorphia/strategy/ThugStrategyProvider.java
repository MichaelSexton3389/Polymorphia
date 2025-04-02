package polymorphia.strategy;

import csci.ooad.polymorphia.intf.CommandFactory;
import csci.ooad.polymorphia.intf.Strategy;
import csci.ooad.polymorphia.intf.StrategyProvider;

public class ThugStrategyProvider implements StrategyProvider {

    @Override
    public String getName() {
        return "Thug";
    }

    @Override
    public Strategy create(CommandFactory commandFactory) {
        if (commandFactory == null) {
            throw new IllegalArgumentException("CommandFactory cannot be null.");
        }
        return new ThugStrategy(commandFactory);
    }
}
