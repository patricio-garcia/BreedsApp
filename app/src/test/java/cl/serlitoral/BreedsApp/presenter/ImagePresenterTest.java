package cl.serlitoral.BreedsApp.presenter;

import cl.serlitoral.BreedsApp.data.repository.BreedRepository;
import cl.serlitoral.BreedsApp.domain.presenter.iBreedPresenterView;
import cl.serlitoral.BreedsApp.domain.usecase.ImagePresenter;

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
public class ImagePresenterTest {
    @Mock
    private iBreedPresenterView presenterView;
    @Mock
    private BreedRepository repository;

    private String breed;
    private String image;
    private List<String> breeds;
    private ImagePresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        presenter = new ImagePresenter(presenterView, repository, breed);
        image = "image";
        breed = "breed";
    }

    @Test
    public void showBreed_empty() {
        //Given
        breeds = new ArrayList<>();

        //When
        presenterView.showBreed(breeds);

        //Then
        Mockito.verify(presenterView, Mockito.times(1)).showBreed(breeds);
        Mockito.verify(repository, Mockito.never()).loadNewFavorite(image, breed);

    }

    @Test
    public void showBreed_null() {
        //Given
        breeds = null;

        //When
        presenterView.showBreed(breeds);

        //Then
        Mockito.verify(presenterView, Mockito.times(1)).showBreed(breeds);
        Mockito.verify(repository, Mockito.never()).loadNewFavorite(image,breed);

    }
    @Test
    public void showBreed_withData() {
        //Given
        breeds = new ArrayList<>();
        breeds.add("hound");
        breeds.add("beagle");

        //When
        presenterView.showBreed(breeds);

        //Then
        Mockito.verify(presenterView,Mockito.times(1)).showBreed(breeds);
        Mockito.verify(repository, Mockito.never()).loadNewFavorite(image,breed);

    }
    @Test
    public void addFavorite() {
        //Given
        breeds = new ArrayList<>();

        //When
        presenter.addFavorite(image,breed);

        //Then
        Mockito.verify(repository, Mockito.times(1)).loadNewFavorite(image,breed);
        Mockito.verify(presenterView, Mockito.never()).showBreed(breeds);

    }
}