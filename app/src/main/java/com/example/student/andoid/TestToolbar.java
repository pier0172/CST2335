package com.example.student.andoid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.student.andoid.R.id.menu_1;

public class TestToolbar extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "TestToolBar";
    Context ctx;
    private TextView newMessage;
String message= "initial message";
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        ctx = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hi there!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public boolean onCreateOptionsMenu (Menu m){

        getMenuInflater().inflate(R.menu.toolbar_menu, m );
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem mi) {

     int id =   mi.getItemId();

        switch (id) {
            case menu_1:
                Log.d("Toolbar", "Option 1 selected");

              //  Toast.makeText(this, "You selected item 1", Toast.LENGTH_LONG).show();

                Snackbar.make(fab, message, Snackbar.LENGTH_LONG).show();
//                                .setAction("Action", null).show();

//                Toolbar toolbar = (Toolbar) findViewById(R.id.menu_3);
//                setSupportActionBar(toolbar);

//                FloatingActionButton menu1 = (FloatingActionButton) findViewById(R.id.menu_1);
//                menu1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Snackbar.make(view, "You selected item 1", Snackbar.LENGTH_LONG)
//                                .setAction("Action", null).show();
//                    }
//                });

                break;
            case R.id.menu_2:
                Log.d("Toolbar", "Option 2 selected");

                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Do you want to go back?");
// Add the buttons
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(TestToolbar.this, StartActivity.class);
                        startActivityForResult(intent,5);
                        // User clicked OK button
//                        public void onClick(DialogInterface dialog, int id) {
//                            Log.i("Yes", "Yes");
//                        }
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();


                break;
            case R.id.menu_3:
                Log.d("Toolbar", "Option 3 selected");
                newLayoutDialog().show();

//                final AlertDialog.Builder builder3 = new AlertDialog.Builder(ctx);
//                // Get the layout inflater
//               LayoutInflater inflater =  TestToolbar.this.getLayoutInflater(); //??????????????????????need
//
//
//
//                builder3.setView(inflater.inflate(R.layout.custom_dialog, null))
//                       //  Add action buttons
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int id) {
//
//                            }
//                        })
//                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                           public void onClick(DialogInterface dialog, int id) {
////                                TestToolbar.this.getDialog().cancel(); //  LoginDialogFragment
//                            }
//                        });
//                //builder3.setView(  v );
//                builder3.create().show();

                break;
            case R.id.menu_4:
                Log.d("Toolbar", "Option 3 selected");

                Toast.makeText(this, "Version 1.0, by Cyndie", Toast.LENGTH_LONG).show();

            break;

        }
        return true;
    }


    public Dialog newLayoutDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        builder.setView(dialogView);

        final EditText newMessage = (EditText) dialogView.findViewById(R.id.newMessage);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                message = newMessage.getText().toString();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do nothing
            }
        });
        return builder.create();
    }

}
