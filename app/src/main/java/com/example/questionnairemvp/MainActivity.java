package com.example.questionnairemvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.questionnairemvp.Constants.Constants;
import com.example.questionnairemvp.MVP.QuestionnaireFragment.QuestionnaireFragment;
import com.example.questionnairemvp.MVP.UsersFragment.UsersFragment;
import com.example.questionnairemvp.TestDP.BattleComponent;
import com.example.questionnairemvp.TestDP.Bolton;
import com.example.questionnairemvp.TestDP.DaggerBattleComponent;
import com.example.questionnairemvp.TestDP.Stark;
import com.example.questionnairemvp.TestDP.War;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .callTimeout(10, TimeUnit.SECONDS)
            .build();
    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runOkHttp();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                runHttpAsynchro();
            }
        }).start();

        new  Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getAllHeaders ();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //getStringToTheService();
                    //runRequestWithFile();
                    getParseResult();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        /*test di
        Stark stark = new Stark();
        Bolton bolton = new Bolton();
        War war = new War(stark, bolton);
        war.prepare();
        war.report();*/
        //BattleComponent battleComponent =
        BattleComponent battleComponent= DaggerBattleComponent.create();
        Stark stark = battleComponent.getStark();
        War war = battleComponent.getWar();



        /*
        * */

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        toolbar.post(new Runnable() {
            @Override
            public void run() {

            }
        });

        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            QuestionnaireFragment questionnaireFragment = new QuestionnaireFragment();
            setFragmentMainActivity(questionnaireFragment);
        } else if (id == R.id.nav_gallery) {
            UsersFragment usersFragment = new UsersFragment();
            setFragmentMainActivity(usersFragment);
        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragmentMainActivity(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragmentContainer = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragmentContainer == null) {
            fragmentTransaction
                    .add(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            fragmentTransaction
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    /*
     * */
    public void runOkHttp() throws IOException {
        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("e" + response);
            }
            Headers headers = response.headers();
            for (int i = 0; i < headers.size(); i++) {
                Log.d(Constants.ConstantsGlobal.TAG, String.format("Header : %s", headers.name(i)));
            }
            assert response.body() != null;
            Log.d(Constants.ConstantsGlobal.TAG, String.format("Body : %s", response.body().string()));
        }
    }

    public void runHttpAsynchro() {
        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("exception" + response);
                    }

                    Headers headers = response.headers();
                    for (int i = 0; i < headers.size(); i++) {
                        Log.d(Constants.ConstantsGlobal.TAG, String.format("Header : %s", headers.name(i)));
                    }
                    assert responseBody != null;
                    Log.d(Constants.ConstantsGlobal.TAG, String.format("Body : %s", responseBody.string()));
                }
            }
        });
    }

    public void getAllHeaders() throws IOException {

        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept_roman", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.format("Exeption response : %s ", response));
            }

            Headers headers = response.headers();
            for (int i = 0; i < headers.size(); i++) {
                Log.d(Constants.ConstantsGlobal.TAG, String.format("Headers : %s", headers.value(i)));
            }

            Log.d(Constants.ConstantsGlobal.TAG, String.format("Header : %s", response.header("Server")));
            Log.d(Constants.ConstantsGlobal.TAG, String.format("Header : %s", response.header("Date")));
            Log.d(Constants.ConstantsGlobal.TAG, String.format("Header : %s", response.headers("Vary")));

        }
    }

    public void getStringToTheService() throws IOException {
        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";
        //create request
        RequestBody body;
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Response problem");
            }
            assert response.body() != null;
            Log.d(Constants.ConstantsGlobal.TAG, String.format("Body : %s ", response.body().string()));
        }
    }

    // post file
    public void runRequestWithFile() throws IOException {
        File file = new File("my_file.txt");
        //request
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();
        // response
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException();
            }
            assert response.body() != null;
            Log.d(Constants.ConstantsGlobal.TAG, String.format("Body : %s ", response.body().string()));
        }
    }

    // use moshi for json parsing
    public void getParseResult() throws IOException {
        Request request = new Request.Builder()
                .url("https://api.github.com/gists/c2a7c39532239ff261be")
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            assert response.body() != null;
            Gist gist = gistJsonAdapter.fromJson(response.body().source());

            assert gist != null;
            for (Map.Entry<String, GistFile> entry : gist.files.entrySet()) {
                Log.d(Constants.ConstantsGlobal.TAG, String.format("Key : %s , Value : %s, , File name : %s", entry.getKey(), entry.getValue().truncated, entry.getValue().filename));
            }
            //Picasso.get().load().into();
        }
    }

    static class Gist {
        Map<String, GistFile> files;
    }

    static class GistFile {
        String filename;
        boolean truncated;
    }


    // texting rx

}
