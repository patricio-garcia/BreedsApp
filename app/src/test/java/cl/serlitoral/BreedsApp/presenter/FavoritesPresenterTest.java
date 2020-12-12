package cl.serlitoral.BreedsApp.presenter;

import cl.serlitoral.BreedsApp.data.model.Favorite;
import cl.serlitoral.BreedsApp.data.repository.BreedRepository;
import cl.serlitoral.BreedsApp.domain.usecase.FavoritePresenter;
import cl.serlitoral.BreedsApp.domain.presenter.iFavoritePresenterView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FavoritesPresenterTest {

    @Mock
    private iFavoritePresenterView presenterView;
    @Mock
    private BreedRepository repository;

    private List<Favorite> favList;
    private FavoritePresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        presenter = new FavoritePresenter(presenterView,repository);
    }

    @Test
    public void showFavorites_emptyList() {
        //Given
        favList = new ArrayList<>();

        //When
        presenterView.showFavorites(favList);

        //Then
        Mockito.verify(presenterView, Mockito.times(1)).showFavorites(favList);
    }
    @Test
    public void showFavorites_null() {
        //Given
        favList = null;

        //When
        presenterView.showFavorites(favList);

        //Then
        Mockito.verify(presenterView, Mockito.times(1)).showFavorites(favList);
    }
    @Test
    public void showFavorites_withData() {
        //Given
        favList =new ArrayList<>();
        Favorite favorite=new Favorite();
        favorite.setBreed("labrador");
        favorite.setUrlImage("imagen");
        favorite.setTimeStamp("timestamp");
        favList.add(favorite);

        //When
        presenterView.showFavorites(favList);

        //Then
        Mockito.verify(presenterView, Mockito.times(1)).showFavorites(favList);
    }
}