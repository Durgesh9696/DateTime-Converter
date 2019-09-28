package com.durgeshparekh.datetimeconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        Log.e(TAG, "Time: "+ time);

    }

    /* input ist time and get utc time */
    public void utcClicked(View view) {
//        String istTime = "2019-06-20T10:30:53.870Z";
        String istTime = "2019-06-29T14:58:14.566Z";
        String utc = getUTC(istTime);
        Log.e("UTC ",utc );
        Date date = getDateWithServerTimeStamp(utc);
        Log.e(TAG, "utcClicked: "+ date);
    }

//    yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
    /* ist to utc conversion method*/
    private String getUTC(String istMsg) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = null;
        try {
            date = df.parse(istMsg);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        String formattedDate = df.format(date);

        return formattedDate;
    }

    /* input utc time and get ist */
    public void istClicked(View view) {
        String utcTime = "2019-06-29T09:28:14.566Z";
//        String utcTime = "05:00:53";
        String ist = getIST(utcTime);
        Log.e("IST ", ist );

    }

    /* utc to ist conversion */
    private String getIST(String dateStr) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = df.parse(dateStr);

            Log.e(TAG, "getIST: "+ date.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        df.setTimeZone(TimeZone.getDefault());

        String formattedDate = df.format(date);

        return formattedDate;
    }

    public void stringToDateClicked(View view) {
        String dateString = "2019-06-29T14:58:14.566Z";

        Date date = getDateWithServerTimeStamp(dateString);

        Log.e(TAG, "stringToDateClicked: "+ date );
//        Thu Jun 20 16:00:53 GMT+05:30 2019
    }

    private Date getDateWithServerTimeStamp(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public void dateToStringClicked(View view) {
        String dateString = "2019-06-29T14:58:14.566Z";

        Date date = getDateWithServerTimeStamp(dateString);

        String backToString = getStringTimeStampWithDate(date);
        Log.e("LOG", "Date To String Conversion " + backToString);

    }

    private String getStringTimeStampWithDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        return dateFormat.format(date);

    }
}
