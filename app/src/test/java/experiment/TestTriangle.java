package experiment;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestTriangle {

    private Triangle equilateral;
    private Triangle isosceles;
    private Triangle rightAngled;
    private Triangle scalene;

    private Triangle invalidZero;
    private Triangle invalidNegative;
    private Triangle invalidInequality;

    private Triangle minimalValid;
    private Triangle barelyInvalid;
    private Triangle bigInequality;

    @BeforeAll
    /*
* The method run once before any of the test methods in the class.
     */
    public static void setUpBeforeAll() throws Exception {

    }

    @AfterAll
    /*
* The method will be run after all the tests in the class have been run
     */
    public static void tearDownAfterAll() throws Exception {
    }

    @BeforeEach
    /*
* Initializes common objects. The method will be run before the Test method.
     */
    public void setUp() throws Exception {

        // Valid triangles
        equilateral = new Triangle(5, 5, 5);
        isosceles = new Triangle(5, 5, 4);
        rightAngled = new Triangle(3, 4, 5);
        scalene = new Triangle(3, 4, 6);

        // Invalid triangles
        invalidZero = new Triangle(5, 5, 0);
        invalidNegative = new Triangle(5, 5, -1);
        invalidInequality = new Triangle(1, 2, 3);

        // Boundary cases
        minimalValid = new Triangle(1, 1, 1);
        barelyInvalid = new Triangle(1, 1, 2);
        bigInequality = new Triangle(1, 2, 10);
    }

    @AfterEach
    /*
* Cleanup method. This method will be run after the Test method is
completed
     */
    public void tearDown() throws Exception {
        equilateral = isosceles = rightAngled = scalene = null;
        invalidZero = invalidNegative = invalidInequality = null;
        minimalValid = barelyInvalid = bigInequality = null;
    }

    @Test
    /*
* Tests whether the triangle specified in the fixture (setUp)
* is right-angled.
*
* A public void method that is attached to be run as a test case.
* To run the method, JUnit first constructs a fresh instance of the class
then
* invokes the annotated method. Any exceptions thrown by the test will be
reported
* by JUnit as a failure. If no exceptions are thrown, the test is assumed to
have
* succeeded.
     */
    public void testClassify() {
        assertTrue(equilateral.isEquilateral(), "");
        assertTrue(isosceles.isIsosceles(), "");
        assertTrue(rightAngled.isRightAngled(), "");
        assertTrue(scalene.isScalene(), "");
        assertTrue(invalidZero.isImpossible(), "");
        assertTrue(invalidNegative.isImpossible(), "");
        assertTrue(invalidInequality.isImpossible(), "Dissatisfies inequality theorem, is impossible.");
    }

    @Test
    public void testIsosceles() {
        assertTrue(equilateral.isIsosceles(), "");
        assertTrue(isosceles.isIsosceles(), "");
        assertFalse(rightAngled.isIsosceles(), "");
        assertFalse(invalidZero.isIsosceles(), "");
        assertFalse(invalidNegative.isIsosceles(), "");
    }

    @Test
    public void testEquilateral() {
        assertTrue(equilateral.isEquilateral(), "");
        assertFalse(scalene.isEquilateral(), "");
        assertFalse(invalidZero.isEquilateral(), "");
        assertFalse(invalidNegative.isEquilateral(), "");
    }

    @Test
    public void testRightAngled() {
        assertTrue(rightAngled.isRightAngled());
        assertFalse(scalene.isRightAngled());
        assertFalse(invalidZero.isRightAngled());
        assertFalse(invalidNegative.isRightAngled());
    }

    @Test
    public void testScalene() {
        assertTrue(rightAngled.isScalene());
        assertFalse(scalene.isScalene());
        assertFalse(invalidZero.isScalene());
        assertFalse(invalidNegative.isScalene());
    }

    @Test
    public void testImpossible() {
        assertTrue(invalidInequality.isImpossible(), "");
        assertTrue(invalidNegative.isImpossible(), "");
        assertTrue(invalidZero.isImpossible(), "");
        assertFalse(scalene.isImpossible(), "");
    }

    @Test
    public void testSetSideLengths() {
        try {
            Triangle test = scalene.setSideLengths(3, 4, 5);
            assertEquals("3,4,5", test.getSideLengths());

            assertEquals("5,5,4", test.setSideLengths(5, 5, 4).getSideLengths());

            assertEquals("5,5,5", test.setSideLengths(5, 5, 5).getSideLengths());

            test.setSideLengths(1, 2, 3);
            assertEquals("1,2,3", test.setSideLengths(1, 2, 3).getSideLengths());

            test.setSideLengths(5, 5, 0);
            assertEquals("5,5,0", test.setSideLengths(5, 5, 0).getSideLengths());

            test.setSideLengths(5, 5, -1);
            assertEquals("5,5,-1", test.setSideLengths(5, 5, -1).getSideLengths());

        } catch (Exception e) {
            fail("Exception while setting side lengths.");
        }
    }

    @Test
    public void testGetSideLengths() {
        assertEquals("3,4,5", rightAngled.getSideLengths());
        assertEquals("5,5,4", isosceles.getSideLengths());
        assertEquals("5,5,5", equilateral.getSideLengths());
        assertEquals("1,2,3", invalidInequality.getSideLengths());
        assertEquals("5,5,0", invalidZero.getSideLengths());
        assertEquals("5,5,-1", invalidNegative.getSideLengths());
    }

    @Test
    public void testGetPerimeter() {
        assertEquals(12, rightAngled.getPerimeter());
        assertEquals(14, isosceles.getPerimeter());
        assertEquals(15, equilateral.getPerimeter());
        assertEquals(-1, invalidInequality.getPerimeter());
        assertEquals(-1, invalidZero.getPerimeter());
        assertEquals(-1, invalidNegative.getPerimeter());
    }

    @Test
    public void testGetArea() {
        Triangle isosceles2 = new Triangle(5, 5, 6);
        Triangle equilateral2 = new Triangle(6, 6, 6);
        assertEquals(6.0, rightAngled.getArea());
        assertEquals(12, isosceles2.getArea());
        assertEquals(15.588457268119896, equilateral2.getArea());
        assertEquals(-1.0, invalidInequality.getArea());
        assertEquals(-1.0, invalidZero.getArea());
        assertEquals(-1.0, invalidNegative.getArea());
        
    }

    @Test
    public void testBoundaries() {
        Triangle validNearZero = new Triangle(1, 1, 1);
        Double smallArea = Math.sqrt(3) / 4;
        assertEquals(smallArea, validNearZero.getArea());

        Triangle closeToValid = new Triangle(1, 1, 2);
        assertEquals(-1.0, closeToValid.getArea());

        Triangle invalidBigInequality = new Triangle(1, 2, 10);
        assertEquals(-1.0, invalidBigInequality.getArea());
    }
}
