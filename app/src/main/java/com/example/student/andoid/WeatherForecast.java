package com.example.student.andoid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherForecast extends AppCompatActivity {
    private final String ACTIVITY_NAME = "WeatherForecast";
    private ProgressBar progressBar;
    private ImageView weatherImage;
    private TextView currentTempText;
    private TextView minTempText;
    private TextView maxTempText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        minTempText = (TextView) findViewById(R.id.minTemp);
        maxTempText = (TextView) findViewById(R.id.maxTemp);
        currentTempText = (TextView) findViewById(R.id.currentWeather);
        weatherImage = (ImageView) findViewById(R.id.weatherImage);

        ForecastQuery forecast = new ForecastQuery();
        forecast.execute();
    }


    private class ForecastQuery extends AsyncTask<String, Integer, String> {
        private String minTemp;
        private String maxTemp;
        private String currentTemp;
        private String weatherIcon;
        private Bitmap weather;
        private final String URL_STRING = "http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric";
        private final String IMG_BASE_URL = "http://openweathermap.org/img/w/";

        public boolean fileExistance(String fname) {
            File file = getBaseContext().getFileStreamPath(fname);
            return file.exists();
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection conn = null;
            /*
             Given a string representation of a URL, sets up a connection and gets
             an input stream.
             */
            InputStream in = null;
            try {
                URL url = new URL(URL_STRING);
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                in = conn.getInputStream();
             /*
              XmlPullParser initialized to use the provided InputStream as its input
             */
                XmlPullParser parser = Xml.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(in, null);
                parser.nextTag();
                String name = "";

                while (parser.next() != XmlPullParser.END_DOCUMENT) {
                    if (parser.getEventType() != XmlPullParser.START_TAG) {
                        continue;
                    }
                    name = parser.getName();
                    // Starts by looking for the entry tag
                    if (name.equals("temperature")) {
                        minTemp = parser.getAttributeValue(null, "min");
                        Log.i(ACTIVITY_NAME, "minTemp : " + minTemp);
                        publishProgress(25);
                        maxTemp = parser.getAttributeValue(null, "max");
                        Log.i(ACTIVITY_NAME, "maxTemp : " + maxTemp);
                        publishProgress(50);
                        //delay
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        currentTemp = parser.getAttributeValue(null, "value");
                        Log.i(ACTIVITY_NAME, "currentTemp : " + currentTemp);
                        publishProgress(75);
                    } else if (name.equals("weather")) {
                        weatherIcon = parser.getAttributeValue(null, "icon");
                        Log.i(ACTIVITY_NAME, "weatherIcon : " + weatherIcon);
                    }
                }

            } catch (Exception e) {
                Log.e(ACTIVITY_NAME, "Broken Link");
            }

            String fileName = weatherIcon + ".png";
            Log.i(ACTIVITY_NAME, fileName);

            if (fileExistance(fileName)) {
                Log.i(ACTIVITY_NAME, "Getting file from local");
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(getBaseContext().getFileStreamPath(fileName));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                weather = BitmapFactory.decodeStream(fis);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                publishProgress(100);

            } else {
                Log.i(ACTIVITY_NAME, "Getting file from internet");
                Bitmap image = HTTPUtils.getImage(IMG_BASE_URL + fileName);
                FileOutputStream outputStream = null;
                try {
                    outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                    image.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                    outputStream.flush();
                    outputStream.close();
                } catch (Exception e) {
                    Log.e(ACTIVITY_NAME, "File not found");
                }
                weather = image;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                publishProgress(100);
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String degree = Character.toString((char) 0x00B0);
            currentTempText.setText(currentTempText.getText() + currentTemp + degree + "C");
            minTempText.setText(minTempText.getText() + minTemp + degree + "C");
            maxTempText.setText(maxTempText.getText() + maxTemp + degree + "C");
            weatherImage.setImageBitmap(weather);

            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.INVISIBLE);


        }
    }

    private static class HTTPUtils {
        public static Bitmap getImage(URL url) {
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    return BitmapFactory.decodeStream(connection.getInputStream());
                } else
                    return null;
            } catch (Exception e) {
                return null;
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }

        public static Bitmap getImage(String urlString) {
            try {
                URL url = new URL(urlString);
                return getImage(url);
            } catch (MalformedURLException e) {
                return null;
            }
        }

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}