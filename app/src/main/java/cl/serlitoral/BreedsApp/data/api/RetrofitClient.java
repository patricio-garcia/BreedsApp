package cl.serlitoral.BreedsApp.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final  String BASE_URL="https://dog.ceo/api/";
    private static Retrofit retrofit=null;


    private RetrofitClient (){

    }

    public static iDogDatabase getRetrofitInstance(){
        if (retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(iDogDatabase.class);
    }
}
