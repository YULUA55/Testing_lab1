package functions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import static java.lang.Double.NaN;

public class FunctionTest extends Assert {

    private HashMap<Double, Double> arrayTestValues = new HashMap<>();
    private static final double DELTA = 0.01;

    @Before
    public void setUp() {

        //checking 0-value
        arrayTestValues.put( 0d, 0d);

        //checking the rigth side
        arrayTestValues.put(Math.PI / 6, 0.5);
        arrayTestValues.put(Math.PI / 4, Math.sqrt(2) / 2);
        arrayTestValues.put(Math.PI / 3, Math.sqrt(3) / 2);
        arrayTestValues.put(Math.PI / 2, 1d);
        arrayTestValues.put(2*Math.PI / 3, Math.sqrt(3) / 2);
        arrayTestValues.put(3* Math.PI / 4, Math.sqrt(2) / 2);
        arrayTestValues.put(5*Math.PI / 6, 0.5);

        //checking the left side
        arrayTestValues.put(-Math.PI / 6, -0.5);
        arrayTestValues.put(-Math.PI / 4, -Math.sqrt(2) / 2);
        arrayTestValues.put(-Math.PI / 3, -Math.sqrt(3) / 2);
        arrayTestValues.put(-Math.PI / 2, -1d);
        arrayTestValues.put(-2*Math.PI / 3, -Math.sqrt(3) / 2);
        arrayTestValues.put(-3* Math.PI / 4, -Math.sqrt(2) / 2);
        arrayTestValues.put(-5*Math.PI / 6, -0.5);

        //checking the boundary values
        arrayTestValues.put(-Math.PI, 0d);
        arrayTestValues.put(Math.PI, 0d);

        //check for NaN, Infinity
        arrayTestValues.put(NaN, NaN);
        arrayTestValues.put(Double.POSITIVE_INFINITY, NaN);
        arrayTestValues.put(Double.NEGATIVE_INFINITY, NaN);

        //outside values
        arrayTestValues.put(7*Math.PI / 6, NaN);
        arrayTestValues.put(-7*Math.PI / 6, NaN);

    }

    @After
    public void tearDown() {
        arrayTestValues.clear();
    }

    @Test
    public void testSin() {
        double expected, actual;
        for (Map.Entry<Double, Double> entry : arrayTestValues.entrySet()) {
            expected = entry.getValue();

            actual = Function.sin(entry.getKey());
            System.out.println("x = " + entry.getKey() + " actual = " + actual
                    + " expected = " + expected);
            assertEquals(expected, actual, DELTA);
        }
    }

}
