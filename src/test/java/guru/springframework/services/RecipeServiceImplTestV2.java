package guru.springframework.services;

import guru.springframework.repositories.RecipeRepository;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.client.ExpectedCount;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTestV2 {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    Recipe recipe;

    Set<Recipe> recipes;
    Set<Recipe> recipesEmpty;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipesEmpty = new HashSet<>();
        recipes = new HashSet<>();
        recipes.add(recipe);

        recipeService = new RecipeServiceImpl(recipeRepository);

        when(recipeRepository.findAll()).thenReturn(recipes);
    }

    @Test
    public void getRecipes() throws Exception {
        Set<Recipe> recipeSet = recipeService.getRecipes();

        assertEquals(1, recipeSet.size());
        assertEquals(recipe, recipeSet.toArray()[0]);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test(expected = Exception.class)
    public void getRecipesExeptionTesting() throws Exception {
        when(recipeRepository.findAll()).thenReturn(recipesEmpty);
        recipeService.getRecipes();
    }
}