package com.qt.qualithon.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qt.qualithon.TestSession;
import com.qt.qualithon.api.omdb.OMDbAPI;
import com.qt.qualithon.model.Movie;
import com.qt.qualithon.ui.imdb.MoviePage;
import com.qt.qualithon.ui.imdb.WebApp;

/**
 * Test movie web page on imdb and rottentomato to check if the movie data is
 * correct compared to OMDb http://www.omdbapi.com/
 **/
public class MovieSearchTest {

	public TestSession testSession;

	@BeforeMethod
	public void testSessionSetUp() {
		// init browser test session
		this.testSession = TestSession.ChromeTestSession();
	}

	@AfterMethod
	public void testTearDown() {
		// close browser test session

		this.testSession.driver().quit();
	}

	/**
	 * returns a popular movies names as data provider for tests
	 *
	 * @return poular movie titles data provider object array
	 *
	 **/
	@DataProvider
	public Object[][] popularMovieTitles() {
		return new Object[][] { { "A Clockwork Orange" }, { "The Dark Knight Rises" } };
	}

	/**
	 * test that user should be able to search movie titles by exact title name and
	 * see the movie metadata
	 *
	 * @param title movie title to search
	 *
	 **/
	// test passed
	@Test(dataProvider = "popularMovieTitles", enabled = true)
	public void testSearchByExactMovieTitleReturnsMovieAsFirstResult(String title) {
		// get MoviePage from imdb/rottentomato
		MoviePage movieOnImdbWeb = new WebApp(this.testSession).launch().search(title).firstMovieResult();

		assertThat(movieOnImdbWeb.title()).isEqualTo(title);
	}

	/**
	 * test that release year on movie page is correct compared to the movie
	 * metadata on OMDb Test Data
	 *
	 * @param title movie title to search
	 *
	 **/
	// test passed
	@Test(dataProvider = "popularMovieTitles", enabled = true)
	public void testMovieMetadataOnWebHasCorrectReleaseYear(String title) throws Exception {
		// get MoviePage from imdb/rottentomato
		MoviePage movieOnImdbWeb = new WebApp(this.testSession).launch().search(title).firstMovieResult();

		// get Movie metadata from http://www.omdbapi.com/
		Movie movie = new OMDbAPI().getMovie(title);
		assertThat(movieOnImdbWeb.releaseYear()).isEqualTo(movie.releaseYear());
	}

	/**
	 * test that director name on movie page is correct compared to the movie
	 * metadata on OMDb Test Data
	 *
	 * @param title movie title to search
	 *
	 **/
	// test case passed
	@Test(dataProvider = "popularMovieTitles", enabled = true)
	public void testMovieMetadataOnWebHasCorrectDirectorName(String title) throws Exception {
		// get MoviePage from imdb/rottentomato
		MoviePage movieOnImdbWeb = new WebApp(this.testSession).launch().search(title).firstMovieResult();

		// get Movie metadata from http://www.omdbapi.com/
		Movie movie = new OMDbAPI().getMovie(title);
		assertThat(movieOnImdbWeb.director()).isEqualTo(movie.director());
	}

	/**
	 * test that writers on movie page are correct compared to the movie metadata on
	 * OMDb Test Data
	 *
	 * @param title movie title to search
	 *
	 **/
	// test passed
	@Test(dataProvider = "popularMovieTitles", enabled = true)
	public void testMovieMetadataOnWebHasCorrectWriters(String title) throws Exception {
		// get MoviePage from imdb/rottentomato
		MoviePage movieOnImdbWeb = new WebApp(this.testSession).launch().search(title).firstMovieResult();

		// get Movie metadata from http://www.omdbapi.com/
		Movie movie = new OMDbAPI().getMovie(title);
		assertThat(movieOnImdbWeb.writers()).isEqualTo(movie.writers());
	}

	/**
	 * test that movie genres on movie page are correct compared to the movie
	 * metadata on OMDb Test Data
	 *
	 * @param title movie title to search
	 *
	 **/
	// test passed
	@Test(dataProvider = "popularMovieTitles", enabled = true)
	public void testMovieMetadataOnWebHasCorrectGenres(String title) throws Exception {
		// get MoviePage from imdb/rottentomato
		MoviePage movieOnImdbWeb = new WebApp(this.testSession).launch().search(title).firstMovieResult();

		// get Movie metadata from http://www.omdbapi.com/
		Movie movie = new OMDbAPI().getMovie(title);
		assertThat(movieOnImdbWeb.genres()).isEqualTo(movie.genres());
	}

	/**
	 * test that maturity rating on movie page is correct compared to the maturity
	 * rating in OMDb Test Data API
	 *
	 * @param title movie title to search
	 *
	 **/
	@Test(dataProvider = "popularMovieTitles", enabled = true)
	public void testMovieMetadataOnWebHasCorrectMaturityRating(String title) throws Exception {
		// IMPLEMENTED
		MoviePage movieOnImdbWeb = new WebApp(this.testSession).launch().search(title).firstMovieResult();

		Movie movie = new OMDbAPI().getMovie(title);

		assertThat(movieOnImdbWeb.maturityRating()).isEqualTo(movie.maturityRating());

		// throw new Exception("Test Pending");
	}

	/**
	 * test that movie rating score on movie page (IMDB Rating, Tomatometer) is
	 * correct compared to the movie rating score in OMDb Test Data API
	 *
	 * @param title movie title to search
	 *
	 **/
	@Test(dataProvider = "popularMovieTitles", enabled = true)
	public void testMovieMetadataOnWebHasCorrectMovieRatingScore(String title) throws Exception {
		// IMPLEMENTED
		MoviePage movieOnImdbWeb = new WebApp(this.testSession).launch().search(title).firstMovieResult();

		Movie movie = new OMDbAPI().getMovie(title);
		assertThat(movieOnImdbWeb.score()).isEqualTo(movie.score());

		// throw new Exception("Test Pending");
	}
}
