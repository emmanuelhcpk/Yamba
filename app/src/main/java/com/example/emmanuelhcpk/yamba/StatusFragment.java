package com.example.emmanuelhcpk.yamba;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emmanuelhcpk.lib.YambaClient;
import com.example.emmanuelhcpk.lib.YambaClientException;



public class StatusFragment extends Fragment implements OnClickListener{

    private Button botontweet;
    private EditText areatweet;
    private static final String TAG = "StatusActivity";


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_status, container, false);
        areatweet = (EditText) view.findViewById(R.id.areaTweet);
        botontweet = (Button) view.findViewById(R.id.botonTweet);

        botontweet.setOnClickListener(this);
        return view;
    }

    public void onClick(View view) {
        String status = areatweet.getText().toString();
        Log.d(TAG, "onClicked with status: " + status);
        new PostTask().execute(status);
    }
    private final class PostTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            YambaClient yambaCloud =
                    new YambaClient("student", "password");
            try {
                yambaCloud.postStatus(params[0]);
                return "Successfully posted";
            } catch (YambaClientException e) {
                e.printStackTrace();
                return "Failed to post to yamba service";
            }
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(StatusFragment.this.getActivity(),
                    result, Toast.LENGTH_LONG).show();
        }
    }








}
