package com.root.ashad.appmaker.news;

import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView Fajrv,Dhuhrv,Asrv,Maghribv,Ishav,Imsakv,Midnightv,dateofhizriv;
//    Spinner palce,meethod;


    //https://newsapi.org/v2/top-headlines?
    //country=us&
//    'apiKey=API_KEY
//    String baseurl="http://api.aladhan.com/v1/timingsByCity?city=";
//
//    String midlle="&country=";
//    String method="&method=2";
    //http://api.aladhan.com/v1/timingsByCity?city=Dubai&country=&method=8


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fajrv=findViewById(R.id.Fajr);
        Dhuhrv=findViewById(R.id.Dhuhr);
        Asrv=findViewById(R.id.Asr);
        Maghribv=findViewById(R.id.Maghrib);
        Ishav=findViewById(R.id.Isha);
        Imsakv=findViewById(R.id.Imsak);
        Midnightv=findViewById(R.id.Midnight);
        dateofhizriv=findViewById(R.id.dateofhizri);
//        nootification();
//        meethod=findViewById(R.id.meethod);
//        palce=findViewById(R.id.place);
//        String methodofstring[]={"Shia Ithna-Ansari","University of Islamic Sciences, Karachi","Islamic Society of North America","Muslim World League","Umm Al-Qura University, Makkah","Egyptian General Authority of Survey","Institute of Geophysics, University of Tehran","Kuwait","Qatar","Majlis Ugama Islam Singapura, Singapore","Union Organization islamic de France","Diyanet İşleri Başkanlığı, Turkey","Custom"};
//        ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,methodofstring);
//        meethod.setAdapter(adapter);
//        String placestring[]={"ranchi ,jharkhand","write to want another"};
//        ArrayAdapter<String> adapter1=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,placestring);
//        palce.setAdapter(adapter1);



        String myurl="http://api.aladhan.com/v1/timings?latitude=23.3441&longitude=85.3096&method=2";
        Log.i("url","is  "+myurl);
        final JsonObjectRequest js=new JsonObjectRequest(Request.Method.GET, myurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    String data=response.getString("data");
                    Log.i("data ","is "+data);
                    JSONObject timingofjson=new JSONObject(data);

                    String timings=timingofjson.getString("timings");
                    Log.i("timing","is "+timings);

                    JSONObject jsonofallprayertiming=new JSONObject(timings);
                    String Fajr=jsonofallprayertiming.getString("Fajr");

                    Fajrv.setText("Fajr :"+Fajr);

                    String Dhuhr=jsonofallprayertiming.getString("Dhuhr");
                    Dhuhrv.setText("Dhuhr :"+Dhuhr);
                    String Asr=jsonofallprayertiming.getString("Asr");
                    Asrv.setText("Asr :"+Asr);
                    String Maghrib=jsonofallprayertiming.getString("Maghrib");
                    Maghribv.setText("Maghrib :"+Maghrib);
                    String Isha=jsonofallprayertiming.getString("Isha");
                    Ishav.setText("Isha :"+Isha);
                    String Imsak=jsonofallprayertiming.getString("Imsak");
                    Imsakv.setText("Imsak :"+Imsak);
                    String Midnight=jsonofallprayertiming.getString("Midnight");
                    Midnightv.setText("Midnight :"+Midnight);
                    Log.i("fazr","is" +Fajr);

                    JSONObject dateofjson=new JSONObject(data);
                    String date=dateofjson.getString("date");
                    Log.i("date ","is "+date);
                    JSONObject hijriofjson=new JSONObject(date);
                    String hijri=hijriofjson.getString("hijri");
                    Log.i("hizri","is "+hijri);
                    JSONObject dateofjsonofhizri=new JSONObject(hijri);
                    String dateofhizri=dateofjsonofhizri.getString("date");
                    dateofhizriv.setText("date :"+dateofhizri);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        mysingleton.getInstance(getApplicationContext()).addToRequestque(js);

//        String methodinstring=meethod.getSelectedItem().toString();
//
//        String plkaceinstring=palce.getSelectedItem().toString();
//        //0 - Shia Ithna-Ansari
//        //1 - University of Islamic Sciences, Karachi
//        //2 - Islamic Society of North America
//        //3 - Muslim World League
//        //4 - Umm Al-Qura University, Makkah
//        //5 - Egyptian General Authority of Survey
//        //7 - Institute of Geophysics, University of Tehran
//        //8 - Gulf Region
//        //9 - Kuwait
//        //10 - Qatar
//        //11 - Majlis Ugama Islam Singapura, Singapore
//        //12 - Union Organization islamic de France
//        //13 - Diyanet İşleri Başkanlığı, Turkey
//        //99 - Custom.
//        if (methodinstring.equals("Shia Ithna-Ansari"))
//        {
//            actualmethoid="0";
//            Toast.makeText(getApplicationContext(), "Showing in ", Toast.LENGTH_LONG).show();
//        }
//        else
//        {
//            actualmethoid="99";
//        }



    }
//
//    private void nootification()
//    {
//        Log.i("as","as");
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this,"M_ch_id")
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("as")
//                .setContentText("as")
//                .setPriority(Notification.PRIORITY_MAX)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//
//// notificationId is a unique int for each notification that you must define
//        notificationManager.notify(0, mBuilder.build());
//
//
//    }

    int Notification_ID=1;
    public void showNotification (String from, String notification, Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                Notification_ID,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );


        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID);
        Notification mNotification = builder
                .setContentTitle(from)
                .setContentText(notification)

//                .setTicker("Hearty365")
//                .setContentInfo("Info")
                //     .setPriority(Notification.PRIORITY_MAX)

                .setContentIntent(pendingIntent)

                .setAutoCancel(true)
//                .setDefaults(Notification.DEFAULT_ALL)
//                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.ic_launcher))
                .build();

        notificationManager.notify(/*notification id*/Notification_ID, mNotification);

    }

}
