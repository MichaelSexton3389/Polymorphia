package polymorphia.strategy;

import csci.ooad.polymorphia.intf.CommandFactory;
import csci.ooad.polymorphia.intf.Strategy;
import csci.ooad.polymorphia.intf.StrategyProvider;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ThugStrategyProviderTest {

    @Test
    void testProviderLoading() {
        ServiceLoader<StrategyProvider> loader = ServiceLoader.load(StrategyProvider.class);
        List<StrategyProvider> services = new ArrayList<>();

        for (StrategyProvider service : loader) {
            services.add(service);

            assertEquals(ThugStrategyProvider.class.getName(), service.getClass().getName());

            // Mock CommandFactory
            CommandFactory commandFactory = mock(CommandFactory.class);

            // Ensure the strategy is created successfully
            Strategy strategy = service.create(commandFactory);
            assertNotNull(strategy);
            assertTrue(strategy instanceof ThugStrategy);
        }

        // Ensure at least one service was loaded
        assertFalse(services.isEmpty());
    }

    @Test
    void testCreateThrowsExceptionForNullCommandFactory() {
        ThugStrategyProvider provider = new ThugStrategyProvider();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> provider.create(null));
        assertEquals("CommandFactory cannot be null.", exception.getMessage());
    }

    @Test
    void testGetName() {
        ThugStrategyProvider provider = new ThugStrategyProvider();

        String name = provider.getName();

        assertEquals("Thug", name);
    }
}
