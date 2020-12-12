package cl.serlitoral.BreedsApp.data.api;

import cl.serlitoral.BreedsApp.data.model.BreedList;
import cl.serlitoral.BreedsApp.data.model.BreedImage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface iDogDatabase {

    @GET("breeds/list/all/")
    Call<BreedList> getAllBreeds();
    @GET("breed/{breed}/images/")
    Call <BreedImage> getBreedDetail(@Path("breed") String breed);
}
