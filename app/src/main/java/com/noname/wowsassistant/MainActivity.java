package com.noname.wowsassistant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private String status;
    private String access_token;
    private String nickname;
    private int account_id;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String params = intent.getStringExtra("params");
        if (params!=null) {
            String[] param_array = params.split("&");
            String[][] splited_param_array = new String[param_array.length-1][2];
            for (int i=1; i<param_array.length; i++) {
                String[] line = param_array[i].split("=");
                splited_param_array[i-1][0] = line[0];
                splited_param_array[i-1][1] = line[1];
            }
            for (int j = 0; j < splited_param_array.length; j++) {
                    if (splited_param_array[j][0].equals("status"))
                        status = splited_param_array[j][1];
                    else if (splited_param_array[j][0].equals("access_token"))
                        access_token = splited_param_array[j][1];
                    else if (splited_param_array[j][0].equals("nickname"))
                        nickname = splited_param_array[j][1];
                    else if (splited_param_array[j][0].equals("account_id"))
                        account_id = Integer.valueOf(splited_param_array[j][1]);
            }
            TextView textView = (TextView) findViewById(R.id.nicknameText);
            textView.setText(nickname);

        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void wievSearch(View view) {
        Intent intent = new Intent(this, SearchPlayerActivity.class);
        startActivity(intent);
    }

    public void viewEncyclopedia(View view) {
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main2 Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public void goToCabinet(View view) {
        Intent intent;
        if(status!=null){
            intent=new Intent(this,CabinetActivity.class);
            intent.putExtra("nickname",nickname);
            intent.putExtra("account_id",account_id);
            intent.putExtra("access_token",access_token);
        }else{
            intent=new Intent(this,LoginActivity.class);
        }
        startActivity(intent);
    }
}
