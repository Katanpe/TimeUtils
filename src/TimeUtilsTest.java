import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class TimeUtilsTest {
	private TimeUtils aika = new TimeUtils();

	@ParameterizedTest(name="Luvun {0} aika on {1}")
	@CsvSource({ "0, 0:00:00", "3600, 1:00:00", "3661, 1:01:01"})
	public void testTime(int luku, String tulos) {
	String messu = luku + ":n " + "aika on laskettu väärin.";
	String tulosAika = aika.secToTime(luku);
	assertEquals(tulos, tulosAika, messu);
	}
	
    @ParameterizedTest
    @ValueSource(ints = {-1, -3600})
    public void testNegativeInput(int input) {
        assertThrows(IllegalArgumentException.class, () -> aika.secToTime(input));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"abc", "1.5", "123xyz", "!!", "32.$%^"})
    public void testNonIntegerInput(String input) {
        assertThrows(NumberFormatException.class, () -> {
            int inputValue = Integer.parseInt(input);
            aika.secToTime(inputValue);
        });
    }
    
    @ParameterizedTest
    @CsvSource({ "0, 0, 0, 0:00:00", "1, 5, 5, 1:05:05", "2, 10, 20, 2:10:20", "6, 15, 8, 6:15:08"})
    public void testTimeFormatting(int hh, int mm, int ss, String expected) {
        int totalSeconds = hh * 3600 + mm * 60 + ss;
        String result = aika.secToTime(totalSeconds);
        assertEquals(expected, result);
    }
}
