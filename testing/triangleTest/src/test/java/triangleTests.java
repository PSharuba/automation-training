import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class triangleTests {
    private final Triangle triangle = new Triangle();

    @Test
    void testOnStartExp1() {
        assertEquals(1, triangle.exists());
    }
    @Test
    void testSetRightSides() {
        assertTrue(triangle.setSides(1,1,1));
    }
    @Test
    void testSetWrongSides() {
        assertFalse(triangle.setSides(0,1,1));
    }
    @Test
    void testWithSetSides1Exp1() {
        triangle.setSides(3,4,5);
        assertEquals(1, triangle.exists());
    }
    @Test
    void testWithSetSides2Exp0() {
        triangle.setSides(3,4,1);
        assertEquals(0, triangle.exists());
    }
    @Test
    void testWithSetSides3Exp0() {
        triangle.setSides(40,1,39);
        assertEquals(0, triangle.exists());
    }
    @Test
    void testWithSetSides4ExpM1() {
        triangle.setSides(1,2,4);
        assertEquals(-1, triangle.exists());
    }
    @Test
    void testWithSetSides5ExpM1() {
        triangle.setSides(2,6,3);
        assertEquals(-1, triangle.exists());
    }


}
