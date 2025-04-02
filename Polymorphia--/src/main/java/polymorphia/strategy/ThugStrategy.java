package polymorphia.strategy;

import java.util.Comparator;
import java.util.Optional;

import csci.ooad.polymorphia.intf.Command;
import csci.ooad.polymorphia.intf.CommandFactory;
import csci.ooad.polymorphia.intf.IArmor;
import csci.ooad.polymorphia.intf.ICharacter;
import csci.ooad.polymorphia.intf.IFood;
import csci.ooad.polymorphia.intf.Strategy;

public class ThugStrategy implements Strategy {

    private CommandFactory commandFactory;

    public ThugStrategy(CommandFactory commandFactory) {
        if (commandFactory == null) {
            throw new IllegalArgumentException("CommandFactory cannot be null.");
        }
        this.commandFactory = commandFactory;
    }

    @Override
    public Command generateCommand(ICharacter iCharacter) {
        // Try to fight first
        Optional<ICharacter> targetCharacter = iCharacter.getCurrentRoom().getCharacters().stream()
                .filter(c -> !c.equals(iCharacter))
                .findFirst();

        if (targetCharacter.isPresent()) {
            return commandFactory.createFightCommand(iCharacter, targetCharacter.get());
        }

        // Then try to eat
        Optional<IFood> targetFood = iCharacter.getCurrentRoom().getFoodItems().stream()
                .max(Comparator.comparingDouble(IFood::healthValue));

        if (targetFood.isPresent()) {
            return commandFactory.createEatCommand(iCharacter, targetFood.get());
        }
        // Then try to wear armor
        Optional<IArmor> targetArmor = iCharacter.getCurrentRoom().getArmoredSuits().stream().findFirst();

        if (targetArmor.isPresent()) {
            return commandFactory.createWearCommand(iCharacter, targetArmor.get());
        }
        // Then try to move
        if (iCharacter.getCurrentRoom().getCharacters().stream().allMatch(c -> c.equals(iCharacter))) {
            Optional<String> targetRoom = iCharacter.getCurrentRoom().getNeighborRoomNames().stream().findFirst();

            if (targetRoom.isPresent()) {
                return commandFactory.createMoveCommand(iCharacter, targetRoom.get());
            }
        }
        return commandFactory.createDoNothingCommand();
    }

}
