package cl.serlitoral.BreedsApp.data.repository;

import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import cl.serlitoral.BreedsApp.data.api.RetrofitClient;
import cl.serlitoral.BreedsApp.data.model.BreedImage;
import cl.serlitoral.BreedsApp.data.model.BreedList;
import cl.serlitoral.BreedsApp.data.model.Favorite;
import cl.serlitoral.BreedsApp.domain.usecase.BreedPresenter;
import cl.serlitoral.BreedsApp.domain.usecase.FavoritePresenter;
import cl.serlitoral.BreedsApp.domain.usecase.ImagePresenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreedRepository {

    private static final String TAG = "LogBreedRespo";
    private BreedPresenter breedPresenter;
    private ImagePresenter imagePresenter;
    private FavoritePresenter favPresenter;
    private List<String> breedsImages = new ArrayList<>();
    private FirebaseFirestore dbFav = FirebaseFirestore.getInstance();

    public void setFavPresenter(FavoritePresenter favPresenter) {
        this.favPresenter = favPresenter;
    }
    public void setImagePresenter(ImagePresenter imagePresenter) {
        this.imagePresenter = imagePresenter;
    }

    public void setBreedPresenter(BreedPresenter breedPresenter) {
        this.breedPresenter = breedPresenter;
    }

    public void lodadBreedList(){
        RetrofitClient.getRetrofitInstance().getAllBreeds().enqueue(new Callback<BreedList>() {
            @Override
            public void onResponse(Call<BreedList> call, Response<BreedList> response) {
                List<String> breeds=new ArrayList<String>();
                breeds.addAll(response.body().getMessage().keySet());
                breedPresenter.showBreed(breeds);
        }

            @Override
            public void onFailure(Call<BreedList> call, Throwable t) {
                //TODO: Sin implementar
            }
        });
    }

    public void loadBreedPictures(String pBreed){
        RetrofitClient.getRetrofitInstance().getBreedDetail(pBreed).enqueue(new Callback<BreedImage>(){
            @Override
            public void onResponse(Call<BreedImage> call, Response<BreedImage> response) {
                breedsImages.addAll(response.body().getMessage());
                imagePresenter.showBreed(breedsImages);
            }

            @Override
            public void onFailure(Call<BreedImage> call, Throwable t) {
                //TODO: Sin implementar
            }
        });
    }

    public void loadNewFavorite(String image, String breed){
        Map<String,Object> favorite=new HashMap<>();
        favorite.put("breed",breed);
        favorite.put("urlPicture",image);
        favorite.put("timeStamp",new Date().toString() );

        dbFav.collection("favorites")
                .add(favorite)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
    }

    public void downloadAllFavorites(){
        List<Favorite> listFavorites=new ArrayList<>();
        dbFav.collection("favorites")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.getData());
                            Favorite favorite=setFavorite(document);
                            listFavorites.add(favorite);
                            Log.d(TAG, "onComplete: Lista Favoritos:a añadido " + favorite.toString() );
                            Log.d(TAG, "onComplete: La lista tiene actualmente "+ listFavorites.size()+" elementos");
                        }
                        Log.d(TAG, "onComplete: enviando lista favoritos al presenter"+listFavorites.toString());
                        Log.d(TAG, "onComplete: La lista tiene actualmente "+listFavorites.size()+" elementos");
                        favPresenter.showFavorites(listFavorites);


                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }

                });

    }




    private Favorite setFavorite(QueryDocumentSnapshot document){
        Favorite favorite=new Favorite();
        favorite.setBreed(document.getString("breed"));
        favorite.setTimeStamp(document.getString("timeStamp"));
        favorite.setUrlImage(document.getString("urlPicture"));
        return favorite;
    }
}
