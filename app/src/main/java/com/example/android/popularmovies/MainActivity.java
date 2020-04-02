package com.example.android.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.android.popularmovies.data.Movie;
import com.example.android.popularmovies.utils.JsonUtils;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    // TODO (3) Use AsyncTask to replace dummy data
    // TODO (5) Implement menu and sort method

    private ArrayList<Movie> mMovies;

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovies = createMoviesArray();

        mRecyclerView = findViewById(R.id.rv_poster_layout);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mMovieAdapter = new MovieAdapter(mMovies, this);
        mRecyclerView.setAdapter(mMovieAdapter);

    }

    private ArrayList<Movie> createMoviesArray() {
        String json = "{\"page\":1,\"total_results\":10000,\"total_pages\":500," +
                "\"results\":[{\"popularity\":862.68,\"vote_count\":12040," +
                "\"video\":false,\"poster_path\":\"\\/5vHssUeVe25bMrof1HyaPyWgaP.jpg\"," +
                "\"id\":245891,\"adult\":false," +
                "\"backdrop_path\":\"\\/lvjRFFyNLdaMWIMYQvoebeO1JlF.jpg\"," +
                "\"original_language\":\"en\",\"original_title\":\"John Wick\"," +
                "\"genre_ids\":[28,53],\"title\":\"John Wick\",\"vote_average\":7.2," +
                "\"overview\":\"Ex-hitman John Wick comes out of retirement to track " +
                "down the gangsters that took everything from him.\"," +
                "\"release_date\":\"2014-10-22\"},{\"popularity\":611.507," +
                "\"vote_count\":5013,\"video\":false," +
                "\"poster_path\":\"\\/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg\",\"id\":420818," +
                "\"adult\":false,\"backdrop_path\":\"\\/nRXO2SnOA75OsWhNhXstHB8ZmI3" +
                ".jpg\",\"original_language\":\"en\",\"original_title\":\"The Lion " +
                "King\",\"genre_ids\":[12,10751],\"title\":\"The Lion King\"," +
                "\"vote_average\":7.1,\"overview\":\"Simba idolizes his father, King " +
                "Mufasa, and takes to heart his own royal destiny. But not everyone in " +
                "the kingdom celebrates the new cub's arrival. Scar, Mufasa's " +
                "brother—and former heir to the throne—has plans of his own. The battle" +
                " for Pride Rock is ravaged with betrayal, tragedy and drama, " +
                "ultimately resulting in Simba's exile. With help from a curious pair " +
                "of newfound friends, Simba will have to figure out how to grow up and " +
                "take back what is rightfully his.\",\"release_date\":\"2019-07-12\"}," +
                "{\"popularity\":444.914,\"vote_count\":2770,\"video\":false," +
                "\"poster_path\":\"\\/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg\",\"id\":419704," +
                "\"adult\":false,\"backdrop_path\":\"\\/5BwqwxMEjeFtdknRV792Svo0K1v" +
                ".jpg\",\"original_language\":\"en\",\"original_title\":\"Ad Astra\"," +
                "\"genre_ids\":[18,878],\"title\":\"Ad Astra\",\"vote_average\":6," +
                "\"overview\":\"The near future, a time when both hope and hardships " +
                "drive humanity to look to the stars and beyond. While a mysterious " +
                "phenomenon menaces to destroy life on planet Earth, astronaut Roy " +
                "McBride undertakes a mission across the immensity of space and its " +
                "many perils to uncover the truth about a lost expedition that decades " +
                "before boldly faced emptiness and silence in search of the unknown.\"," +
                "\"release_date\":\"2019-09-17\"},{\"popularity\":368.26," +
                "\"vote_count\":1115,\"video\":false," +
                "\"poster_path\":\"\\/8WUVHemHFH2ZIP6NWkwlHWsyrEL.jpg\",\"id\":338762," +
                "\"adult\":false,\"backdrop_path\":\"\\/ocUrMYbdjknu2TwzMHKT9PBBQRw" +
                ".jpg\",\"original_language\":\"en\",\"original_title\":\"Bloodshot\"," +
                "\"genre_ids\":[28,878],\"title\":\"Bloodshot\",\"vote_average\":7.2," +
                "\"overview\":\"After he and his wife are murdered, marine Ray Garrison" +
                " is resurrected by a team of scientists. Enhanced with nanotechnology," +
                " he becomes a superhuman, biotech killing machine—'Bloodshot'. As Ray " +
                "first trains with fellow super-soldiers, he cannot recall anything " +
                "from his former life. But when his memories flood back and he " +
                "remembers the man that killed both him and his wife, he breaks out of " +
                "the facility to get revenge, only to discover that there's more to the" +
                " conspiracy than he thought.\",\"release_date\":\"2020-02-20\"}," +
                "{\"popularity\":432.581,\"vote_count\":1926,\"video\":false," +
                "\"poster_path\":\"\\/y95lQLnuNKdPAzw9F9Ab8kJ80c3.jpg\",\"id\":38700," +
                "\"adult\":false,\"backdrop_path\":\"\\/upUy2QhMZEmtypPW3PdieKLAHxh" +
                ".jpg\",\"original_language\":\"en\",\"original_title\":\"Bad Boys for " +
                "Life\",\"genre_ids\":[28,80,53],\"title\":\"Bad Boys for Life\"," +
                "\"vote_average\":6.8,\"overview\":\"Marcus and Mike are forced to " +
                "confront new threats, career changes, and midlife crises as they join " +
                "the newly created elite team AMMO of the Miami police department to " +
                "take down the ruthless Armando Armas, the vicious leader of a Miami " +
                "drug cartel.\",\"release_date\":\"2020-01-15\"},{\"popularity\":243" +
                ".798,\"vote_count\":2377,\"video\":false," +
                "\"poster_path\":\"\\/h4VB6m0RwcicVEZvzftYZyKXs6K.jpg\",\"id\":495764," +
                "\"adult\":false,\"backdrop_path\":\"\\/pbOOOT0ASXjP4aJZr5NyOjK9qix" +
                ".jpg\",\"original_language\":\"en\",\"original_title\":\"Birds of Prey" +
                " (and the Fantabulous Emancipation of One Harley Quinn)\"," +
                "\"genre_ids\":[28,35,80],\"title\":\"Birds of Prey (and the " +
                "Fantabulous Emancipation of One Harley Quinn)\",\"vote_average\":7.1," +
                "\"overview\":\"It's open season on Harley Quinn when her explosive " +
                "breakup with the Joker puts a big fat target on her back. Unprotected " +
                "and on the run, Quinn faces the wrath of narcissistic crime boss Black" +
                " Mask, his right-hand man, Victor Zsasz, and every other thug in the " +
                "city. But things soon even out for Harley when she becomes unexpected " +
                "allies with three deadly women -- Huntress, Black Canary and Renee " +
                "Montoya.  Birds of Prey (and the Fantabulous Emancipation of One " +
                "Harley Quinn) is a 2020 American superhero film based on the DC Comics" +
                " team Birds of Prey. It is the eighth film in the DC Extended Universe" +
                " and a follow-up to Suicide Squad (2016).\"," +
                "\"release_date\":\"2020-02-05\"},{\"popularity\":190.892," +
                "\"vote_count\":11260,\"video\":false," +
                "\"poster_path\":\"\\/6WBIzCgmDCYrqh64yDREGeDk9d3.jpg\",\"id\":98," +
                "\"adult\":false,\"backdrop_path\":\"\\/33N3GgT58J8GIvYBBOUEttDS6dW" +
                ".jpg\",\"original_language\":\"en\",\"original_title\":\"Gladiator\"," +
                "\"genre_ids\":[28,12,18],\"title\":\"Gladiator\",\"vote_average\":8.1," +
                "\"overview\":\"In the year 180, the death of emperor Marcus Aurelius " +
                "throws the Roman Empire into chaos.  Maximus is one of the Roman " +
                "army's most capable and trusted generals and a key advisor to the " +
                "emperor.  As Marcus' devious son Commodus ascends to the throne, " +
                "Maximus is set to be executed.  He escapes, but is captured by slave " +
                "traders.  Renamed Spaniard and forced to become a gladiator, Maximus " +
                "must battle to the death with other men for the amusement of paying " +
                "audiences.\",\"release_date\":\"2000-05-01\"},{\"popularity\":218.421," +
                "\"vote_count\":1499,\"video\":false," +
                "\"poster_path\":\"\\/8ZX18L5m6rH5viSYpRnTSbb9eXh.jpg\",\"id\":619264," +
                "\"adult\":false,\"backdrop_path\":\"\\/3tkDMNfM2YuIAJlvGO6rfIzAnfG" +
                ".jpg\",\"original_language\":\"es\",\"original_title\":\"El hoyo\"," +
                "\"genre_ids\":[18,878],\"title\":\"The Platform\",\"vote_average\":7" +
                ".2,\"overview\":\"A mysterious place, an indescribable prison, a deep " +
                "hole. An unknown number of levels. Two inmates living on each level. A" +
                " descending platform containing food for all of them. An inhuman fight" +
                " for survival, but also an opportunity for solidarity…\"," +
                "\"release_date\":\"2019-11-08\"},{\"popularity\":207.817," +
                "\"vote_count\":1819,\"video\":false," +
                "\"poster_path\":\"\\/aQvJ5WPzZgYVDrxLX4R6cLJCEaQ.jpg\",\"id\":454626," +
                "\"adult\":false,\"backdrop_path\":\"\\/stmYfCUGd8Iy6kAMBr6AmWqx8Bq" +
                ".jpg\",\"original_language\":\"en\",\"original_title\":\"Sonic the " +
                "Hedgehog\",\"genre_ids\":[28,35,878,10751],\"title\":\"Sonic the " +
                "Hedgehog\",\"vote_average\":7.4,\"overview\":\"Based on the global " +
                "blockbuster videogame franchise from Sega, Sonic the Hedgehog tells " +
                "the story of the world’s speediest hedgehog as he embraces his new " +
                "home on Earth. In this live-action adventure comedy, Sonic and his new" +
                " best friend team up to defend the planet from the evil genius Dr. " +
                "Robotnik and his plans for world domination.\"," +
                "\"release_date\":\"2020-02-12\"},{\"popularity\":203.21," +
                "\"vote_count\":3687,\"video\":false," +
                "\"poster_path\":\"\\/db32LaOibwEliAmSL2jjDF6oDdj.jpg\",\"id\":181812," +
                "\"adult\":false,\"backdrop_path\":\"\\/jOzrELAzFxtMx2I4uDGHOotdfsS" +
                ".jpg\",\"original_language\":\"en\",\"original_title\":\"Star Wars: " +
                "The Rise of Skywalker\",\"genre_ids\":[28,12,878],\"title\":\"Star " +
                "Wars: The Rise of Skywalker\",\"vote_average\":6.5,\"overview\":\"The " +
                "surviving Resistance faces the First Order once again as the journey " +
                "of Rey, Finn and Poe Dameron continues. With the power and knowledge " +
                "of generations behind them, the final battle begins.\"," +
                "\"release_date\":\"2019-12-18\"},{\"popularity\":117.335," +
                "\"vote_count\":7949,\"video\":false," +
                "\"poster_path\":\"\\/eivQmS3wqzqnQWILHLc4FsEfcXP.jpg\",\"id\":363088," +
                "\"adult\":false,\"backdrop_path\":\"\\/6P3c80EOm7BodndGBUAJHHsHKrp" +
                ".jpg\",\"original_language\":\"en\",\"original_title\":\"Ant-Man and " +
                "the Wasp\",\"genre_ids\":[28,12,35,9648,878,10751],\"title\":\"Ant-Man" +
                " and the Wasp\",\"vote_average\":7,\"overview\":\"Just when his time " +
                "under house arrest is about to end, Scott Lang once again puts his " +
                "freedom at risk to help Hope van Dyne and Dr. Hank Pym dive into the " +
                "quantum realm and try to accomplish, against time and any chance of " +
                "success, a very dangerous rescue mission.\"," +
                "\"release_date\":\"2018-07-04\"},{\"popularity\":181.955," +
                "\"vote_count\":312,\"video\":false," +
                "\"poster_path\":\"\\/33VdppGbeNxICrFUtW2WpGHvfYc.jpg\",\"id\":481848," +
                "\"adult\":false,\"backdrop_path\":\"\\/yFRpUmsreYO5Bc0HVBTsJsHIIox" +
                ".jpg\",\"original_language\":\"en\",\"original_title\":\"The Call of " +
                "the Wild\",\"genre_ids\":[12,18,10751],\"title\":\"The Call of the " +
                "Wild\",\"vote_average\":6.7,\"overview\":\"\"," +
                "\"release_date\":\"2020-02-19\"},{\"popularity\":253.929," +
                "\"vote_count\":7137,\"video\":false," +
                "\"poster_path\":\"\\/c9XxwwhPHdaImA2f1WEfEsbhaFB.jpg\",\"id\":351286," +
                "\"adult\":false,\"backdrop_path\":\"\\/3s9O5af2xWKWR5JzP2iJZpZeQQg" +
                ".jpg\",\"original_language\":\"en\",\"original_title\":\"Jurassic " +
                "World: Fallen Kingdom\",\"genre_ids\":[28,12,878],\"title\":\"Jurassic" +
                " World: Fallen Kingdom\",\"vote_average\":6.5,\"overview\":\"Three " +
                "years after the demise of Jurassic World, a volcanic eruption " +
                "threatens the remaining dinosaurs on the isla Nublar, so Claire " +
                "Dearing, the former park manager, recruits Owen Grady to help prevent " +
                "the extinction of the dinosaurs once again.\"," +
                "\"release_date\":\"2018-06-06\"},{\"popularity\":151.56," +
                "\"vote_count\":707,\"video\":false," +
                "\"poster_path\":\"\\/f4aul3FyD3jv3v4bul1IrkWZvzq.jpg\",\"id\":508439," +
                "\"adult\":false,\"backdrop_path\":\"\\/xFxk4vnirOtUxpOEWgA1MCRfy6J" +
                ".jpg\",\"original_language\":\"en\",\"original_title\":\"Onward\"," +
                "\"genre_ids\":[12,16,35,14,10751],\"title\":\"Onward\"," +
                "\"vote_average\":7.9,\"overview\":\"In a suburban fantasy world, two " +
                "teenage elf brothers embark on an extraordinary quest to discover if " +
                "there is still a little magic left out there.\"," +
                "\"release_date\":\"2020-02-29\"},{\"popularity\":165.576," +
                "\"vote_count\":456,\"video\":false," +
                "\"poster_path\":\"\\/dvjFM3GgYm3gDZ6Ulw0JurDYs4r.jpg\",\"id\":13004," +
                "\"adult\":false,\"backdrop_path\":\"\\/mtZh7DHYcwiE8lQ2XrcaMd78CId" +
                ".jpg\",\"original_language\":\"en\",\"original_title\":\"Barbie and " +
                "the Diamond Castle\",\"genre_ids\":[16,10751],\"title\":\"Barbie and " +
                "the Diamond Castle\",\"vote_average\":7.1,\"overview\":\"Liana and " +
                "Alexa (Barbie and Teresa) are best friends who share everything, " +
                "including their love of singing. One day while walking through the " +
                "forest home from the village, the girls meet an old beggar who gives " +
                "them a magical mirror. As they clean the mirror and sing, a musical " +
                "apprentice muse named Melody appears in the mirror's surface, and " +
                "tells the girls about the secret of the Diamond Castle.\"," +
                "\"release_date\":\"2008-01-01\"},{\"popularity\":228.712," +
                "\"vote_count\":4,\"video\":false," +
                "\"poster_path\":\"\\/cYmzXBl5WWifG9Cxs1MKRPQlywY.jpg\",\"id\":105397," +
                "\"adult\":false,\"backdrop_path\":null,\"original_language\":\"en\"," +
                "\"original_title\":\"滅門慘案 II 借種\",\"genre_ids\":[]," +
                "\"title\":\"Daughter of Darkness 2\",\"vote_average\":4.8," +
                "\"overview\":\"Another gruesome case involving a woman who's a sole " +
                "survivor of a brutal massacre. A local constable questions the woman " +
                "about her whereabouts and what might have happened the night before. " +
                "As he gets the young woman to talk, she spins a tale of debauchery, " +
                "madness and people so vile that you wished that they'll never see " +
                "daylight again. Why is this woman so reluctant to talk? Who else might" +
                " be involved in this ghastly murder? How will this bumbling cop get " +
                "any solid leads or useful clues from this shell shocked woman?\"," +
                "\"release_date\":\"1994-01-01\"},{\"popularity\":161.446," +
                "\"vote_count\":1049,\"video\":false," +
                "\"poster_path\":\"\\/4U7hpTK0XTQBKT5X60bKmJd05ha.jpg\",\"id\":570670," +
                "\"adult\":false,\"backdrop_path\":\"\\/uZMZyvarQuXLRqf3xdpdMqzdtjb" +
                ".jpg\",\"original_language\":\"en\",\"original_title\":\"The Invisible" +
                " Man\",\"genre_ids\":[27,878,53],\"title\":\"The Invisible Man\"," +
                "\"vote_average\":7.1,\"overview\":\"When Cecilia's abusive ex takes " +
                "his own life and leaves her his fortune, she suspects his death was a " +
                "hoax. As a series of coincidences turn lethal, Cecilia works to prove " +
                "that she is being hunted by someone nobody can see.\"," +
                "\"release_date\":\"2020-02-26\"},{\"popularity\":141.804," +
                "\"vote_count\":2794,\"video\":false," +
                "\"poster_path\":\"\\/bB42KDdfWkOvmzmYkmK58ZlCa9P.jpg\",\"id\":512200," +
                "\"adult\":false,\"backdrop_path\":\"\\/hreiLoPysWG79TsyQgMzFKaOTF5" +
                ".jpg\",\"original_language\":\"en\",\"original_title\":\"Jumanji: The " +
                "Next Level\",\"genre_ids\":[12,35,14],\"title\":\"Jumanji: The Next " +
                "Level\",\"vote_average\":6.8,\"overview\":\"As the gang return to " +
                "Jumanji to rescue one of their own, they discover that nothing is as " +
                "they expect. The players will have to brave parts unknown and " +
                "unexplored in order to escape the world’s most dangerous game.\"," +
                "\"release_date\":\"2019-12-04\"},{\"popularity\":198.562," +
                "\"vote_count\":409,\"video\":false," +
                "\"poster_path\":\"\\/jjMJy5OxJHpFIaboCklDIYdcfpD.jpg\",\"id\":504562," +
                "\"adult\":false,\"backdrop_path\":\"\\/2XuYDWxnnE7bnTlNntc5JeA2kId" +
                ".jpg\",\"original_language\":\"en\",\"original_title\":\"Motherless " +
                "Brooklyn\",\"genre_ids\":[18,9648,53],\"title\":\"Motherless " +
                "Brooklyn\",\"vote_average\":6.9,\"overview\":\"New York City, 1957. " +
                "Lionel Essrog, a private detective living with Tourette syndrome, " +
                "tries to solve the murder of his mentor and best friend, armed only " +
                "with vague clues and the strength of his obsessive mind…\"," +
                "\"release_date\":\"2019-10-31\"},{\"popularity\":84.21," +
                "\"vote_count\":2,\"video\":false," +
                "\"poster_path\":\"\\/79kQIrL36dIoE4uzHyYPqwBvraN.jpg\",\"id\":643560," +
                "\"adult\":false,\"backdrop_path\":\"\\/cZOED0ioodG7turLqLwnIlBQD8W" +
                ".jpg\",\"original_language\":\"en\",\"original_title\":\"Maddy\"," +
                "\"genre_ids\":[99],\"title\":\"Maddy\",\"vote_average\":5.5," +
                "\"overview\":\"Madeline Stuart is a fashion celebrity who has walked " +
                "the runway at the New York Fashion Week, has 700 000+ followers on " +
                "Facebook and is covered by international media world wide. This " +
                "documentary follows Madeline on her journey to becoming the world's " +
                "first professional supermodel with Down syndrome, challenging our " +
                "perception of identity, beauty and disability.\"," +
                "\"release_date\":\"2020-03-20\"}]}";
        ArrayList<Movie> moviesArray = new ArrayList<>();
        try {
            moviesArray = JsonUtils.parseJsonResponse(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return moviesArray;
    }

    @Override
    public void onItemClick(Movie movie) {
        Context context = this;
        Class detailActivity = DetailActivity.class;

        Intent intent = new Intent(context, detailActivity);
        intent.putExtra("Clicked movie", movie);

        startActivity(intent);
    }
}
