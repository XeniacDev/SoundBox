package ir.imuon.soundbox;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    BufferedReader bufferedReader;

    //    public static ArrayList<String> artistsId = new ArrayList<>();
//    public static ArrayList<String> countriesId = new ArrayList<>();
    public static ArrayList<String> countriesName = new ArrayList<>();
//    public static ArrayList<String> genresId = new ArrayList<>();
//    public static ArrayList<String> genre_selectionId = new ArrayList<>();
//    public static ArrayList<String> listeningId = new ArrayList<>();
//    public static ArrayList<String> tracksId = new ArrayList<>();
//    public static ArrayList<String> albumsId = new ArrayList<>();
//    public static ArrayList<String> labelsId = new ArrayList<>();
//    public static ArrayList<String> favouritesId = new ArrayList<>();
//    public static ArrayList<String> usersId = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checking Internet connection
        checkConnection();

        // Get data from server

        // Checking user log in status

    }

    private void checkConnection() {
        if (connectionChecker()) {
            new GetData().execute();
            loginCheck();
        } else {
            dialog();
        }
    }

    private void loginCheck() {
        boolean loginCheck;

        SharedPreferences preferences =
                getSharedPreferences(LoginActivity.LOG_IN_CHECK, MODE_PRIVATE);
        loginCheck = preferences.getBoolean(LoginActivity.LOG_IN_KEY, false);

        if (loginCheck) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }


    private boolean connectionChecker() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()
                == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()
                        == NetworkInfo.State.CONNECTED) {
            return true;
        } else {
            return false;
        }
    }

    private void dialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SplashActivity.this);
        alertDialog.setTitle(R.string.alertDialog_title);
        alertDialog.setMessage(R.string.alertDialog_message);
        alertDialog.setIcon(R.drawable.ic_error_black);
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton(R.string.alertDialog_positiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                checkConnection();
            }
        });

        alertDialog.setNegativeButton(R.string.alertDialog_negativeButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                finish();
            }
        });

        AlertDialog dialog = alertDialog.create();
        dialog.show();

    }


    public class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            getJsonData();

            return null;
        }
    }

    private void getJsonData() {

        String password = "5lfPt~zMmyVd";

        try {

            // Send variable to server
            String getData = URLEncoder.encode("password", "UTF-8") +
                    "=" + URLEncoder.encode(password, "UTF-8");

            URL url = new URL("https://cafedl.com/SoundBox/get_data.php");

            URLConnection urlConnection = url.openConnection();
            urlConnection.setDoOutput(true);

            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(urlConnection.getOutputStream());
            outputStreamWriter.write(getData);
            outputStreamWriter.flush();

            // Get answer from server
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            StringBuilder stringBuilder = new StringBuilder();
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            String result = stringBuilder.toString();
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            for (int i = 0; i < getData.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                artistsId.add(jsonObject.getString("artists.id"));
//                countriesId.add(jsonObject.getString("countries.id"));
                countriesName.add(jsonObject.getString("countries.name"));
//                genresId.add(jsonObject.getString("genres.id"));
//                genre_selectionId.add(jsonObject.getString("genre_selectionId.id"));
//                listeningId.add(jsonObject.getString("listening.id"));
//                tracksId.add(jsonObject.getString("tracks.id"));
//                albumsId.add(jsonObject.getString("albums.id"));
//                labelsId.add(jsonObject.getString("labels.id"));
//                favouritesId.add(jsonObject.getString("favourites.id"));
//                usersId.add(jsonObject.getString("users.email"));

                Log.i("CountyNames", jsonObject.getString("countries.name"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
