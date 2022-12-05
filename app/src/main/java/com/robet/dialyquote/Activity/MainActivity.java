package com.robet.dialyquote.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.robet.dialyquote.API.APIRequestData;
import com.robet.dialyquote.API.RetroServer;
import com.robet.dialyquote.Adapter.AdapterQuote;
import com.robet.dialyquote.Model.QuoteModel;
import com.robet.dialyquote.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvQuote;
    private ProgressBar pbQuote;
    private List<QuoteModel> listQuote;
    private AdapterQuote adQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvQuote = findViewById(R.id.rv_quote);
        pbQuote = findViewById(R.id.pb_quote);
        rvQuote.setLayoutManager(new LinearLayoutManager(this));
        retriveData();

    }

//    membangun hubungan dengan API dan di tarik data nya dan disimpan ke variabel yang sudah dibuat
    private void retriveData(){
        pbQuote.setVisibility(View.VISIBLE);
        APIRequestData ARD = RetroServer.connectRetrofit().create(APIRequestData.class);
        Call<List<QuoteModel>> retrieveProcess = ARD.ardRetrive();
        retrieveProcess.enqueue(new Callback<List<QuoteModel>>() {
            @Override
//            jika diterima
            public void onResponse(Call<List<QuoteModel>> call, Response<List<QuoteModel>> response) {
                listQuote = response.body();
                adQuote = new AdapterQuote(listQuote, MainActivity.this);
                rvQuote.setAdapter(adQuote);
                pbQuote.setVisibility(View.GONE);
            }

            @Override
//            jika ditolak
            public void onFailure(Call<List<QuoteModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Connection Error! Cannot Reach Server", Toast.LENGTH_SHORT).show();
                pbQuote.setVisibility(View.GONE);
            }
        });

    }
}