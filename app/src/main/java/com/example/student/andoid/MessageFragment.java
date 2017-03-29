package com.example.student.andoid;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Student on 2017-03-21.
 */

public class MessageFragment extends Fragment {
    private static final String ARG_PARAM1 = "ID";
    String message;
    int id; //long
    protected static final String ACTIVITY_NAME = "MessageFragment";
    private long dbid;
    Context parent;
    public ChatWindow window;

    public MessageFragment(){
        super();
    }
    public MessageFragment(ChatWindow window){
        this.window = window;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Step 3, create fragment onCreation, pass data from Intent Extras to FragmentTransction
        Bundle data = getArguments();
        message = data.getString("MESSAGE");
        id = data.getInt("ID");
        dbid = data.getLong("DBID");
        Log.d("DBID IN FRAGMENT", dbid +" ");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parent = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View gui = inflater.inflate(R.layout.fragment_fragment_layout1, null); // or messageDetail

        TextView messageDetailsText = (TextView) gui.findViewById(R.id.messageDetailsText);
        messageDetailsText.setText("You clicked on message:" + message);

        TextView iDMessageText = (TextView) gui.findViewById(R.id.iDMessageText);
        iDMessageText.setText("You clicked on ID:" + id);


        //  find button and add click listener
        Button deleteMsgButton = (Button) gui.findViewById(R.id.deleteMessageButton);


        deleteMsgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if(getActivity().getClass() == ChatWindow.class){
//                   ((ChatWindow)getActivity()).deleteDb(id, dbid);
                if (window !=null){
                    window.deleteDb();
                    window.removeFragment(MessageFragment.this);

                }else {
                    Intent i = new Intent(getActivity(), ChatWindow.class);
                    i.putExtra("DBID", dbid);
                    i.putExtra("ID", id);
                    getActivity().setResult(Activity.RESULT_OK, i);
                    getActivity().finish();

                }
                }
            });


        return gui;
    }
}

