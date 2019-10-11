package com.example.questionnairemvp;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.JsonToken;
import android.util.LruCache;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.example.questionnairemvp.MVP.QuestionnaireFragment.QuestionnaireFragment;
import com.example.questionnairemvp.MVP.UsersFragment.UsersFragment;
import com.example.questionnairemvp.ROOM.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        Internal storage
        File file = getApplicationContext().getCacheDir();
        File file1 = getApplicationContext().getFilesDir();
        File inputStream = getApplicationContext().getFileStreamPath("a.txt");

        Environment.getExternalStorageDirectory();
        getApplicationContext().getExternalCacheDir();
        Object
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSized = maxMemory / 8;

        LruCache <String , String>  stringStringLruCache =
                new LruCache<String , String>(cacheSized){
                    @Override
                    protected String create(String key) {
                        return super.create(key);
                    }
                };


        Context context;
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("setting", MODE_PRIVATE );
        Editor editor = sharedPreferences.edit();
        editor.putString("1","1");
        editor.putInt("2", 2);
        editor.apply();

        SharedPreferences sharedPreferences1 = getApplication().getSharedPreferences("setting", MODE_PRIVATE);
        int value = sharedPreferences.getInt("2",0 );
        String s = sharedPreferences.getString("1", null);
 */

        Socket socket = new Socket();
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL("http://mail.ru");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            try (InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream())) {
            }
            finally {
                if (httpURLConnection != null){
                    httpURLConnection.disconnect();
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        setContentView(R.layout.activity_main);
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
            QuestionnaireFragment questionnaireFragment =  new QuestionnaireFragment();
            setFragmentMainActivity(questionnaireFragment);
        } else if (id == R.id.nav_gallery) {
            UsersFragment usersFragment =  new UsersFragment();
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

    private void setFragmentMainActivity (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragmentContainer = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragmentContainer == null){
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

    public void createJSON (String json) throws JSONException {
        JSONTokener jsonTokener = new JSONTokener(json);
        JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
        Users users =  new Users();
        users.setId(jsonObject.getInt(""));
        users.setName(jsonObject.getString(""));

        JSONArray jsonArray = jsonObject.getJSONArray("");
        for (int i = 0; i < jsonArray.length(); i++) {

        }

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder().build();

        MainActivity.this.getLoaderManager();
        Message message = new Message();
        /*try (Response response = okHttpClient.newCall(request).execute()) {

        }

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });*/
    }
}
