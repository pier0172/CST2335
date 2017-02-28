package com.example.student.andoid;

        import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
    String fileName;
    protected static final String ACTIVITY_NAME = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final  SharedPreferences prefs = getSharedPreferences( "fileName", Context.MODE_PRIVATE); //String fileName, int mode
        String text = prefs.getString("DefaultEmail", "email@domain.com");
        final EditText emailField = (EditText)findViewById(R.id.emailField);
        emailField.setText(text);

        int numTimeRun= prefs.getInt("timerun", 0);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });


        Button button2 = (Button)findViewById(R.id.button2);  // button??  reference

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                startActivity(intent);

                SharedPreferences.Editor edit = prefs.edit();
                String email = emailField.getText().toString();
                edit.putString("DefaultEmail", email );
                edit.commit();

                setResult(0);

                finish();

            }
        });

        //  protected static final String ACTIVITY_NAME = "StartActivity";
        final String LoginActivity = "LoginActivity";
        Log.i(LoginActivity, "In onCreate()");



    }



    protected void  onResume(){
        super.onResume();
        final String LoginActivity = "LoginActivity";
        Log.i(LoginActivity, "In onResume()");
    }

    protected void	onStart(){
        super.onStart();
        final String LoginActivity = "LoginActivity";
        Log.i(LoginActivity, "In onStart()");


//        SharedPreferences sharedPref =  getActivity().getPreferences(Context.MODE_PRIVATE);
//        int defaultValue = getResources().getInteger(R.string.saved_high_score_default);
//        long highScore = sharedPref.getInt(getString(R.string.saved_high_score), defaultValue);




    }

//    private SharedPreferences getSharedPreferences(String fileName, int mode) {
//        return SharedPreferences();
//    }

    protected void	onPause(){
        super.onPause();
        final String LoginActivity = "LoginActivity";
        Log.i(LoginActivity, "In onPause()");
    }

    protected void	onStop(){
        super.onStop();
        final String LoginActivity = "LoginActivity";
        Log.i(LoginActivity, "In onStop()");
    }

    protected void	onDestroy(){
        super.onDestroy();
        final String LoginActivity = "LoginActivity";
        Log.i(LoginActivity, "In onDestroy()");
    }


}

