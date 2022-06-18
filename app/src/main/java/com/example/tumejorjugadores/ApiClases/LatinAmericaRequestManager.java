package com.example.tumejorjugadores.ApiClases;

import android.content.Context;
import android.widget.Toast;
import com.example.tumejorjugadores.R;
import com.example.tumejorjugadores.ApiModel.NewsApiResponse;
import com.example.tumejorjugadores.Interfaces.OnFetchDataListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class LatinAmericaRequestManager {

    Context context;
//Retrofit adapta una interfaz Java a las llamadas HTTP mediante el uso de anotaciones
// en los métodos
// declarados para definir cómo se realizan las solicitudes. Cree instancias usando el
// constructor y pase su interfaz para crear para generar una implementación
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public LatinAmericaRequestManager(Context context) {
        this.context = context;
    }

    public void getNewsHeadlines(OnFetchDataListener<NewsApiResponse> listener, String category, String query){

        CallNewsApi callNewsApi = retrofit.create(CallNewsApi.class);

        Call<NewsApiResponse> call = callNewsApi.callHeadlines(category, "es","sports", query, context.getString(R.string.api_key));

        try {
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }

                    listener.onFetchData(response.body().getArticles(), response.message());
                }


                @Override

                public void onFailure(Call<NewsApiResponse> call, Throwable t) {

                    Toast.makeText(context, "Ha fallado", Toast.LENGTH_SHORT).show();


                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public interface CallNewsApi {
        @GET("top-headlines")
        Call<NewsApiResponse> callHeadlines(
                @Query("country")String pais,
                @Query("language")String idioma,
                @Query("category")String tipoNoticia,
                @Query("q")String query,
                @Query("apiKey")String apiKey
        );
    }
}
