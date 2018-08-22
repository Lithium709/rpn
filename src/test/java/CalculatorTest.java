import com.olysyi.rpn.Calculator;
import com.olysyi.rpn.CalculatorException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void givenTwoNumbersThenPushInStack() throws CalculatorException {
        calculator = new Calculator();

        calculator.eval("5 2");
        assertEquals(2, calculator.getValuesStack().poll(), 0);
        assertEquals(5, getTop(), 0);
    }
    @Test
    public void testSubstraction() throws CalculatorException {
        // substraction
        calculator.eval("5 2 -");
        assertEquals(1, getSize());
        assertEquals(3, getTop(), 0);
        calculator.eval("3 -");
        assertEquals(1, getSize());
        assertEquals(0, getTop(), 0);
    }


    @Test
    public void givenFiveNumberAndMutiplyThenShouldBeFourNumbers() throws CalculatorException {
        // negative
        calculator.eval("1 2 3 4 5 *");
        assertEquals(4, getSize());
    }

    @Test
    public void givenTreeAndFourAndSubstractSignThenMinusOne() throws CalculatorException {

        calculator.eval("3 4 -");
        assertEquals(1, getSize());
        assertEquals(-1, getTop(), 0);
    }

    @Test
    public void testMixedOperatorsAndOperands() throws CalculatorException {

        // division
        calculator.eval("7 12 2 /");
        assertEquals(6, getTop(), 0);

        calculator.eval("*");
        assertEquals(1, getSize());
        assertEquals(42, getTop(), 0);

        calculator.eval("4 /");
        assertEquals(1, getSize());
        assertEquals(10.5, getTop(), 0);
    }

    @Test
    public void givenFiveNumbersAndFourMultipySignsThenGetProduct() throws CalculatorException {

        //multiplication
        calculator.eval("1 2 3 4 5");
        calculator.eval("* * * *");
        assertEquals(1, getSize());
        assertEquals(120, getTop(), 0);
    }

    @Test
    public void testInsuficientParameters() {
        try {
            calculator.eval("1 2 3 * 5 + * * 6 5");
        } catch (CalculatorException e) {
            assertEquals("operator * (position: 8): insufficient parameters", e.getMessage());
        }
        assertEquals(1, getSize());
        assertEquals(11, getTop(), 0);
    }

    @Test(expected = CalculatorException.class)
    public void testOnlyOperators() throws CalculatorException {
        calculator.eval("+ +");
    }

    @Test(expected = CalculatorException.class)
    public void testInvalidCharacters() throws CalculatorException {
        calculator.eval("2 a +");
    }

    @Test(expected = CalculatorException.class)
    public void testNoSpaces() throws CalculatorException {
        calculator.eval("22+");
    }

    @Test(expected = CalculatorException.class)
    public void testNoSpaces2() throws CalculatorException {
        calculator.eval("2 2+ 3");
    }

    @Test(expected = CalculatorException.class)
    public void testDivideByZero() throws CalculatorException {
        calculator.eval("1 0 /");
    }

    @Test(expected = CalculatorException.class)
    public void testNullInput() throws CalculatorException {
        calculator.eval(null);
    }

    private Double getTop() {
        return calculator.getValuesStack().peek();
    }

    private int getSize() {
        return calculator.getValuesStack().size();
    }
}

