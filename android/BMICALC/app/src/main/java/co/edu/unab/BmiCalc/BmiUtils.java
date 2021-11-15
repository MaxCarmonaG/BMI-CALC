package co.edu.unab.BmiCalc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BmiUtils {

    static float bmi;

    public static String calcBmi(float weight, float height) {
        bmi = weight / (height * height);
        return String.format("%, .2f", bmi);
    }

    public static String dateGenerator() {
        Date date = new Date();
        String pattern = "dd/MM/yyy";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static String recBmi() {
        if(bmi < 18.5) {
            return "You're in the underweight range";
        } else if (bmi < 24.9) {
            return "You're in the healthy weight range";
        } else if (bmi < 29.9) {
            return "You're in the overweight range";
        } else {
            return "You're in the obese range";
        }
    }

}
