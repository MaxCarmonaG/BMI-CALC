package co.edu.unab.BmiCalc.repository;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;

import co.edu.unab.BmiCalc.network.Bmi;
import co.edu.unab.BmiCalc.network.BmiApiClient;
import retrofit2.Call;
import retrofit2.Response;

public class GetBmiCategoryTest {
    GetBmiCategory apiBmi = BmiApiClient.getClient().create(GetBmiCategory.class);

    @Test
    public void getBmiCategory() {
        String bmi = "24.69";
        Call<Bmi> call = apiBmi.getBmiCat(bmi);
        try {
            Response<Bmi> response = call.execute();
            Bmi bmiCat = response.body();

            assertTrue(response.isSuccessful() && bmiCat.getWeightCategory()!=null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}