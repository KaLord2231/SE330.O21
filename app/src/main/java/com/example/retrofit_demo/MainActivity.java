package com.example.retrofit_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit_demo.ApiService.api;
import com.example.retrofit_demo.model.Post;
import com.example.retrofit_demo.model.location;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tvip;
    private TextView tvcity;
    private TextView tvregion;
    private TextView tvcountry;
    private TextView tvloc;
    private TextView tvorg;
    private TextView tvpostal;
    private TextView tvtimezone;
    private Button btnCallAPI;
    private TextView tvPostResult;
    private Button btnCallAPIPOST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvip=findViewById(R.id.tv_ip);
        tvcity=findViewById(R.id.tv_city);
        tvregion=findViewById(R.id.tv_region);
        tvcountry=findViewById(R.id.tv_country);
        tvloc=findViewById(R.id.tv_loc);
        tvorg=findViewById(R.id.tv_org);
        tvpostal=findViewById(R.id.tv_postal);
        tvtimezone=findViewById(R.id.tv_timezone);
        btnCallAPI=findViewById(R.id.btnCallAPI);
        tvPostResult=findViewById(R.id.tv_post_result);
        btnCallAPIPOST=findViewById(R.id.btnCallAPI_Post);
        btnCallAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickCallAPI();
            }
        });
        btnCallAPIPOST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPost();
            }
        });

    }

    private void ClickCallAPI() {
        api.Api.callAPI().enqueue(new Callback<location>() {
            @Override
            public void onResponse(Call<location> call, Response<location> response) {
                Toast.makeText(MainActivity.this,"Call API Success",Toast.LENGTH_SHORT).show();
                location Location = response.body();
                if (Location!=null){
                    tvip.setText(Location.getIp());
                    tvcity.setText(Location.getCity());
                    tvregion.setText(Location.getRegion());
                    tvcountry.setText(Location.getCountry());
                    tvloc.setText(Location.getLoc());
                    tvorg.setText(Location.getOrg());
                    tvpostal.setText(Location.getPostal());
                    tvtimezone.setText(Location.getTimezone());
                }
            }

            @Override
            public void onFailure(Call<location> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Call API Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void sendPost(){
        Post post = new Post(10,101,"SE330.O21","Ngon ngu lap trinh Java");
        api.Api.sendPost(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Toast.makeText(MainActivity.this,"Call API Success",Toast.LENGTH_SHORT).show();
                Post postResult= response.body();
                if(postResult!=null){
                    tvPostResult.setText(postResult.toString());
                }

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Call API Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}