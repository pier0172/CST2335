package com.example.student.andoid;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Student on 2017-03-21.
 */

public class MessageFragment extends Fragment {
String message;
    Long id;
    protected static final String ACTIVITY_NAME = "MessageFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Step 3, create fragment onCreation, pass data from Intent Extras to FragmentTransction
Bundle data = getArguments();
       message = data.getString("MESSAGE");
        id = data.getLong("ID");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View gui = inflater.inflate(R.layout.fragment_fragment_layout1, null); // or messageDetail

        TextView messageDetailsText = (TextView)gui.findViewById(R.id.messageDetailsText);
        messageDetailsText.setText("You clicked on message:" + message);

        TextView iDMessageText = (TextView)gui.findViewById(R.id.iDMessageText);
        iDMessageText.setText("You clicked on ID:" + id);



      //  find button and add click listener
//        Button sendButton = (Button) findViewById(R.id.deleteMessageButton);
//
//
//        sendButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(MessageFragment.this, ListItemsActivity.class);
//                //startActivity(intent);
//                getActivity().setResult(int resultCode, Intent data)
//                startActivityForResult(intent,5);
//
//
//                Log.i(ACTIVITY_NAME, "Return to StartActivity.onActivityResult");
//
//            }
//
//        });


        return gui;
    }

}


