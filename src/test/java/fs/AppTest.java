package fs;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {
    @Test
    void demonstrationReportsExpectedDeterministicValues() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (PrintStream out = new PrintStream(buffer, true, StandardCharsets.UTF_8)) {
            App.run(out);
        }
        String output = buffer.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("Total size: 350100 bytes"));
        assertTrue(output.contains("PDF files >= 100000 bytes: [docs/report.pdf]"));
        assertTrue(output.contains("Statistics: files=7, directories=8, maxDepth=3"));
    }
}
