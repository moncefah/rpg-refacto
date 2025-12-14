package re.forestier.edu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;


public class MainTest {

    @Test
    void constructor_throwsBecauseUtilityClass() {
        IllegalStateException ex = assertThrows(IllegalStateException.class, Main::new);
        assertEquals("Main class", ex.getMessage());
    }

    @Test
    void testMainExecution() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Appeler le main
        Main.main(new String[]{});   
        System.setOut(originalOut);  

        
    }
}
