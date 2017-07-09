package com.example.anu.kshamata;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.HashMap;
import java.util.Locale;

public class story extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


        public static TextToSpeech.OnInitListener listener;

        public static String speak=" Your Summarized Text is ";
        public TextToSpeech myTTS;
        public static int narratorFlag = 0;
        public final int MY_DATA_CHECK_CODE = 3;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_history);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);


            myTTS=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int initStatus) {
                    if (initStatus == TextToSpeech.SUCCESS) {
                        myTTS.setLanguage(Locale.US);
                    }

                    else if (initStatus == TextToSpeech.ERROR) {
                        Toast.makeText(story.this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
                    }



                }
            });


            Intent checkTTSIntent = new Intent();
            checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
            startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);






            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });



//            final String s = getIntent().getStringExtra("EXTRA_SESSION_ID");
//            GetSummary(s);







            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent checkTTSIntent = new Intent();
                    checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
                    startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
                    Log.d("fab.onClick","Narrator");
                    String wordsToBeSpoken;
                    Log.d("NarratorFlag", Integer.toString(narratorFlag));

//                    if(narratorFlag == 0) {
//
//                        wordsToBeSpoken = s;
//                        if (wordsToBeSpoken !=null)
//                        {
//                            if(wordsToBeSpoken != "AutoSummarizer")
//                            {speakWords(wordsToBeSpoken);
//                                Log.d("Narrator if","check");}
//                            else
//                                speakWords("Thank You!");
//                        }
//                        else {
//                            Log.d("No Info to display", "Narrator");
//                            Toast.makeText(story.this, "No Info to display", Toast.LENGTH_SHORT).show();
//                            speakWords("No Info to display");
//                        }
//                    }





                }
            });











        }
//
//        private void GetSummary(String info) {
//            TextView textView = (TextView) findViewById(R.id.getsum);
//            textView.setText(info);
//        }


        public void stop(View v)
        {
            myTTS.stop();
        }



        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return false;
        }







        public void speakWords(String string){
            Log.d("Speakwords","Should work now");
            Log.d("String to be spoken", string);

        /*myTTS.speak(string, TextToSpeech.QUEUE_FLUSH, null);*/
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                {Log.i("SDK ver","ok if");
                    ttsGreater21(string);}
            } else {
                Log.i("SDK ver","ok else");
                ttsUnder20(string);
            }
        }


        @SuppressWarnings("deprecation")
        public void ttsUnder20(String text){
            HashMap<String, String> map = new HashMap<>();
            map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
            myTTS.speak(text, TextToSpeech.QUEUE_FLUSH, map);
        }





        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public void ttsGreater21(String text){
            String utteranceId=story.this.hashCode() + "";
            Log.d("Utter",utteranceId);
            myTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
        }


        @Override
        protected void onDestroy() {
            super.onDestroy();

        }













    }

