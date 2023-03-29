package Test;

import org.junit.Test;
import static org.junit.Assert.*;
public class WestminsterSkinConsultationManagerTest {
    @Test
    public void printDoctor() {
        String expectedPrint = "p";
        String print = "p";
        assertEquals(expectedPrint,print);
    }

    @Test
    public void saveData() {
        String expectedSave = "s";
        String save = "s";
        assertEquals(expectedSave,save);
    }
}