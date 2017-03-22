package com.example.student.andoid;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static com.example.student.andoid.R.id.editChatText;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentLayout1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentLayout1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLayout1 extends Fragment {

    protected static final String ACTIVITY_NAME = "FragmentLayout1";
    //Immediately create the fragment and insert it in the framelayout
    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);



        //Step 3, create fragment onCreation, pass data from Intent Extras to FragmentTransction
        MessageDetails frag = new MessageDetails();
        Bundle bun = getArguments(  );

        bun.getLong("ID");
        bun.getString("Message");
       /*getFragmentManager().beginTransaction().add(R.id.fragmentHolder, frag).commit();




        Button sendButton = (Button) findViewById(R.id.deleteMessageButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FragmentLayout1.this, ListItemsActivity.class);
                //startActivity(intent);
                getActivity().setResult(int resultCode, Intent data)
                startActivityForResult(intent,5);


                Log.i(ACTIVITY_NAME, "Return to StartActivity.onActivityResult");

            }

        });

*/
    }

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup container, Bundle bund)
    {
       View toReturn = li.inflate(R.layout.fragment_fragment_layout1, null);  //fragmentdetails
        return toReturn;
    }


}
