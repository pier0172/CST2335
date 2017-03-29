package com.example.student.andoid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class TestToolbar extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "TestToolBar";
    Context ctx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        ctx = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
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
            case R.id.menu_1:
                Log.d("Toolbar", "Option 1 selected");

                Toast.makeText(this, "You selected item 1", Toast.LENGTH_LONG).show();
//                Toolbar toolbar = (Toolbar) findViewById(R.id.menu_1);
//                setSupportActionBar(toolbar);
//
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
                //Start an activity…
             //   mi = Toast.LENGTH_SHORT;
               // Toast.makeText(this, "You selected item 3", Toast.LENGTH_LONG).show();

                AlertDialog.Builder builder2 = new AlertDialog.Builder(ctx);
                // Get the layout inflater
              //  LayoutInflater inflater =  getActivity().getLayoutInflater(); ??????????????????????need

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout

                //need?????????????????????????????????
//                builder2.setView(inflater.inflate(R.layout.custom_dialog, null))
//                        // Add action buttons
//                        .setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int id) {
//                                // sign in the user ...
//                            }
//                        })
//                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                MessageFragment.this.getDialog().cancel(); //  LoginDialogFragment
//                            }
//                        });

                break;
            case R.id.menu_4:
                Log.d("Toolbar", "Option 3 selected");
                //Start an activity…
                //   mi = Toast.LENGTH_SHORT;
                Toast.makeText(this, "Version 1.0, by Your Cyndie", Toast.LENGTH_LONG).show();

            break;

        }
        return true;
    }


}
