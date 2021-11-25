package co.edu.unab.BmiCalc.repository;

import co.edu.unab.BmiCalc.network.Bmi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface GetBmiCategory {

    @Headers({
            "x-rapidapi-host: body-mass-index-bmi-calculator.p.rapidapi.com",
            "x-rapidapi-key: b8cab71407msh6527b37aa07e0aep192bc5jsn4ac9743da66f"
    })
    @GET("weight-category")
    Call<Bmi> getBmiCat(@Query("bmi") String bmi);
}
