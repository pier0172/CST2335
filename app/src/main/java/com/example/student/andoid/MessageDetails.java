package com.example.student.andoid;

import android.app.Activity;
import android.os.Bundle;

public class MessageDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);

        MessageFragment frag = new MessageFragment();

        frag.setArguments( getIntent().getExtras() );


        getFragmentManager().beginTransaction().add(R.id.activity_message_details, frag).commit();
    }
}
