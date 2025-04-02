package polymorphia.strategy;

import csci.ooad.polymorphia.intf.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ThugStrategyTest {

    @Mock
    CommandFactory commandFactory;
    @Mock
    ICharacter character;
    @Mock
    ICharacter otherCharacter;
    @Mock
    Command command;
    @Mock
    IFood steak;
    @Mock
    IFood candy;
    @Mock
    IArmor armor;
    @Mock
    Room room;

    @Test
    void testFightCommand() {
        // Arrange
        when(character.getCurrentRoom()).thenReturn(room);
        when(room.getCharacters()).thenReturn(List.of(character, otherCharacter));
        when(commandFactory.createFightCommand(character, otherCharacter)).thenReturn(command);

        // Act
        ThugStrategy strategy = new ThugStrategy(commandFactory);
        Command selectedCommand = strategy.generateCommand(character);

        // Assert
        verify(commandFactory).createFightCommand(character, otherCharacter);
    }

    @Test
    void testEatingMostNutritiousFood() {
        // Arrange
        when(character.getCurrentRoom()).thenReturn(room);
        when(room.getCharacters()).thenReturn(List.of(character)); // Alone in the room
        when(room.getFoodItems()).thenReturn(List.of(candy, steak));
        when(steak.healthValue()).thenReturn(2.0);
        when(candy.healthValue()).thenReturn(1.0);
        when(commandFactory.createEatCommand(character, steak)).thenReturn(command);

        // Act
        ThugStrategy strategy = new ThugStrategy(commandFactory);
        Command selectedCommand = strategy.generateCommand(character);

        // Assert
        verify(commandFactory).createEatCommand(character, steak);
    }

    @Test
    void testWearCommand() {
        // Arrange
        when(character.getCurrentRoom()).thenReturn(room);
        when(room.getCharacters()).thenReturn(List.of(character)); // Alone in the room
        when(room.getFoodItems()).thenReturn(List.of()); // No food
        when(room.getArmoredSuits()).thenReturn(List.of(armor));
        when(commandFactory.createWearCommand(character, armor)).thenReturn(command);

        // Act
        ThugStrategy strategy = new ThugStrategy(commandFactory);
        Command selectedCommand = strategy.generateCommand(character);

        // Assert
        verify(commandFactory).createWearCommand(character, armor);
    }

    @Test
    void testMoveCommand() {
        // Arrange
        when(character.getCurrentRoom()).thenReturn(room);
        when(room.getCharacters()).thenReturn(List.of(character)); // Alone in the room
        when(room.getFoodItems()).thenReturn(List.of()); // No food
        when(room.getArmoredSuits()).thenReturn(List.of()); // No armor
        when(room.getNeighborRoomNames()).thenReturn(List.of("Room2"));
        when(commandFactory.createMoveCommand(character, "Room2")).thenReturn(command);

        // Act
        ThugStrategy strategy = new ThugStrategy(commandFactory);
        Command selectedCommand = strategy.generateCommand(character);

        // Assert
        verify(commandFactory).createMoveCommand(character, "Room2");
    }

    @Test
    void testDoNothingCommand() {
        // Arrange
        when(character.getCurrentRoom()).thenReturn(room);
        when(room.getCharacters()).thenReturn(List.of(character)); // Alone in the room
        when(room.getFoodItems()).thenReturn(List.of()); // No food
        when(room.getArmoredSuits()).thenReturn(List.of()); // No armor
        when(room.getNeighborRoomNames()).thenReturn(List.of()); // No neighbors
        when(commandFactory.createDoNothingCommand()).thenReturn(mock(Command.class)); // Mock DoNothingCommand

        // Act
        ThugStrategy strategy = new ThugStrategy(commandFactory);
        Command selectedCommand = strategy.generateCommand(character);

        // Assert
        verify(commandFactory).createDoNothingCommand(); // Verify DoNothingCommand is called
    }

    @Test
    void testNoCharactersToFight() {
        // Arrange
        when(character.getCurrentRoom()).thenReturn(room);
        when(room.getCharacters()).thenReturn(List.of(character)); // Only the character is in the room
        when(commandFactory.createDoNothingCommand()).thenReturn(command);

        // Act
        ThugStrategy strategy = new ThugStrategy(commandFactory);
        Command selectedCommand = strategy.generateCommand(character);

        // Assert
        verify(commandFactory).createDoNothingCommand();
    }

    @Test
    void testConstructorThrowsExceptionForNullCommandFactory() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ThugStrategy(null));
        assertEquals("CommandFactory cannot be null.", exception.getMessage());
    }

    @Test
    void testMoveCommandWhenAloneInRoom() {
        // Arrange
        when(character.getCurrentRoom()).thenReturn(room);
        when(room.getCharacters()).thenReturn(List.of(character)); // Only character in the room
        when(room.getNeighborRoomNames()).thenReturn(List.of("Room1")); // Neighbor room exists
        when(commandFactory.createMoveCommand(character, "Room1")).thenReturn(command); // Mock move command

        // Act
        ThugStrategy strategy = new ThugStrategy(commandFactory);
        Command selectedCommand = strategy.generateCommand(character);

        // Assert
        verify(commandFactory).createMoveCommand(character, "Room1"); // Ensure move command is called
    }

}