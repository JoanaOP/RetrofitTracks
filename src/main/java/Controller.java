import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller{

    static final String BASE_URL = "http://localhost:8080/dsaApp/";
    Track track = new Track("Bohemian rhapsody", "queen");

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        TracksAPI tracksAPI = retrofit.create(TracksAPI.class);

        Call<List<Track>> call = tracksAPI.loadTracks();
        Call<Track> call2 = tracksAPI.addTrack(track);

        Callback<List<Track>> callback = new Callback<List<Track>>(){
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                if(response.isSuccessful()) {
                    List<Track> tracksList = response.body();
                    for(Track track:tracksList){
                        System.out.println(track.getTitle());
                        System.out.println(track.getId());
                        System.out.println(track.getSinger());
                    }

                    //tracksList.forEach(track -> System.out.println(track.title));
                } else {
                    System.out.println(response.errorBody());
                }
            }
            public void onFailure(Call<List<Track>> call, Throwable throwable) {
                throwable.printStackTrace();
            }

        };

        Callback<Track> callback2 = new Callback<Track>() {
            @Override
            public void onResponse(Call<Track> call, Response<Track> response) {
                Track track = response.body();
                System.out.println("Track afegida:");
                System.out.println(track.getId());
                System.out.println(track.getTitle());
                System.out.println(track.getSinger());

            }

            @Override
            public void onFailure(Call<Track> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        };

        call.enqueue(callback);
        call2.enqueue(callback2);

    }

}

