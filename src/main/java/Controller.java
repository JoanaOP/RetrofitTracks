import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller{

    static final String BASE_URL = "http://localhost:8080/dsaApp/";
    Track myTrack = new Track("Bohemian rhapsody", "queen");

    public void start() throws IOException {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        TracksAPI tracksAPI = retrofit.create(TracksAPI.class);

        Call<List<Track>> call = tracksAPI.loadTracks();
        Call<Track> call2 = tracksAPI.addTrack(myTrack);
        Call<Track> call3 = tracksAPI.getTrack(myTrack.getId());
        Call<ResponseBody> call4 = tracksAPI.deleteTrack(myTrack.getId());
        myTrack.setSinger("Queen");
        myTrack.setTitle("Bohemian Rhapsody");
        Call<ResponseBody> call5 = tracksAPI.updateTrack(myTrack);


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
            public void onResponse(Call<Track> call2, Response<Track> response) {
                if(response.isSuccessful()) {
                    Track track = response.body();
                    System.out.println("Track afegida:");
                    System.out.println(track.getId());
                    System.out.println(track.getTitle());
                    System.out.println(track.getSinger());
                }
                else {
                    System.out.println(response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<Track> call2, Throwable throwable) {
                throwable.printStackTrace();
            }
        };

        Callback<Track> callback3 = new Callback<Track>() {
            @Override
            public void onResponse(Call<Track> call3, Response<Track> response) {
                if(response.isSuccessful()) {
                    Track track = response.body();
                    System.out.println("Track rebuda:");
                    System.out.println(track.getId());
                    System.out.println(track.getTitle());
                    System.out.println(track.getSinger());
                }
                else {
                    System.out.println(response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<Track> call3, Throwable throwable) {
                throwable.printStackTrace();
            }
        };

        Callback<ResponseBody> callback4 = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call4, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    System.out.println("Track borrada");
                }
                else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call4, Throwable throwable) {
                throwable.printStackTrace();
            }
        };

        Callback<ResponseBody> callback5 = new Callback<ResponseBody>(){
            @Override
            public void onResponse(Call<ResponseBody> call5, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    System.out.println("Track updated");
                }
                else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call5, Throwable throwable) {
                throwable.printStackTrace();
            }
        };

        call.enqueue(callback);
        System.in.read();
        call2.enqueue(callback2);
        System.in.read();
        call3.enqueue(callback3);
        System.in.read();
        call5.enqueue(callback5);
        System.in.read();
        call4.enqueue(callback4);



    }

}

