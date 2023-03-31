import org.junit.Test;

import static org.junit.Assert.*;

public class GrepTest {

    @Test public void test1() {
        Grep grep = new Grep(true, false, false, "ая.", "files\\text.txt");
        assertEquals(
                "     Рожок и песня удалая...\n" +
                "     И прочая. Ее сомнений\n" +
                "     Спадает глыба снеговая.\n" +
                "     Настала осень золотая.\n",
                grep.findLines()
        );
    }
}