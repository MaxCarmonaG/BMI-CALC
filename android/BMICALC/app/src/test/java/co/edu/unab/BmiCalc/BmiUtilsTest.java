package co.edu.unab.BmiCalc;

import static org.junit.Assert.*;

import org.junit.Test;

public class BmiUtilsTest {

    @Test
    public void calcBmi() {
        final float weight = 80.0F;
        final float height = 1.8F;
        final String result = String.format("%, .2f", 24.69);
        assertEquals(result , BmiUtils.calcBmi(weight, height));
    }
}