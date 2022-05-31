import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class RleProgramTest {
    @Test
    public void countRunsTest() {
        byte[] flatData = {15,15,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,6,2,2};
        int groups = 6; // expected result
        assertEquals(groups, RleProgram.countRuns(flatData));
    }

    @Test
    public void encodeRleTest() {
        byte[] flatData = {15,15,15,4,4,4,4,4,4}; // input
        byte[] rleData = {3,15,6,4};

        byte[] actualResult = RleProgram.encodeRle(flatData);

        assertArrayEquals(rleData, actualResult);
    }

    @Test
    public void decodeRleTest() {
        byte[] flatData = {1,15,15,15,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,2,2};
        byte[] rleData = {1,1,3,15,15,4,15,4,1,4,2,2};

        byte[] actualResult = RleProgram.decodeRle(rleData);

        assertArrayEquals(flatData, actualResult);
    }
}
