package hdt10;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTest {

    @Test
    public void testMain() {
        // Prepare input
        String input = "4\n";
        input += "1\nCity1\nCity2\n2\n4\n";
        input += "3\na\nCity1\nCity2\n";
        input += "3\nb\nCity3\nCity4\n10\n";
        input += "4\n";

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirect output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Run main method
        Main.main(null);

        // Restore standard input/output
        System.setIn(System.in);
        System.setOut(System.out);

        // Check output
        String[] lines = outContent.toString().split("\\r?\\n");
        assertEquals("La ciudad centro del grafo es: City3", lines[6].trim());
        assertEquals("Se ha establecido una nueva conexión entre City3 y City4 con distancia 10 KM", lines[16].trim());
    }
}

