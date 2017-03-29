package com.example.student.andoid;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

public class StartActivity extends Activity {

    protected static final String ACTIVITY_NAME = "StartActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //  protected static final String ACTIVITY_NAME = "StartActivity";
        Log.i(ACTIVITY_NAME, "In onCreate()");

        Button button = (Button)findViewById(R.id.button);  // button??  reference

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.i("Button1", "Return to StartActivity.onActivityResult");

                Intent intent = new Intent(StartActivity.this, ListItemsActivity.class);
                //startActivity(intent);
                startActivityForResult(intent,5);


                Log.i(ACTIVITY_NAME, "Return to StartActivity.onActivityResult");
               /* Intent dataBack = new Intent();
                dataBack.putExtra("Payment", "Declined");
                setResult(5, dataBack);
                finish();*/
            }
        });
      /*  button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(0);


                finish();

            }
        });*/
        // StartChat button
        Button startChat = (Button)findViewById(R.id.startChat);  // button??  reference

        startChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(StartActivity.this, ChatWindow.class);

                startActivityForResult(intent2,5);

                Log.i(ACTIVITY_NAME, "User clicked Start Chat");

            }
        });

        Button weatherButton = (Button)findViewById(R.id.weatherButton);  // button??  reference

        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent3 = new Intent(StartActivity.this, WeatherForecast.class);
               // startActivity(intent);
                startActivityForResult(intent3,5);


                Log.i(ACTIVITY_NAME, "User clicked StartActivity.WeatherForecast");

            }
        });


        // lab 8

        Button testToolbar = (Button)findViewById(R.id.testToolbar);  // button??  reference

        testToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.i("Button1", "Return to StartActivity.onActivityResult");

                Intent intent4 = new Intent(StartActivity.this, TestToolbar.class);
                //startActivity(intent);
                startActivityForResult(intent4,5);


                Log.i(ACTIVITY_NAME, "User clicked StartActivity.TestToolbar");


            }
        });
    }

    protected void  onResume(){
        super.onResume();

        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    protected void	onStart(){
        super.onStart();

        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    protected void	onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    protected void	onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    protected void	onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

    public void onActivityResult(int requestCode, int responseCode, Intent data)
    {
        if (requestCode==5)
            Log.i(ACTIVITY_NAME, "Return to StartActivity.onActivityResult");
        if(responseCode == Activity.RESULT_OK){
            String messagePassed = data.getStringExtra("Response");
            Toast toast = Toast.makeText(this, messagePassed, Toast.LENGTH_LONG); //this is the ListActivity
            toast.show(); //display your message box
        }
    }

}