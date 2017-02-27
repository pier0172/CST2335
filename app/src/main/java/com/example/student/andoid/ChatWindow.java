package com.example.student.andoid;


        import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {

    protected ListView theList;
    protected EditText editChatText;
//    protected Button sendButton;
    //protected int numItems = 4;
    // private static String TAG = "LISTVIEW";

    ArrayList<String> arrayList = new ArrayList<String>();

    //protected String dataItems[]  = new String[] { "Item1", "Item2", "Item 3", "item4", " ","more items"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //in this case, “this” is the ChatWindow, which is-A Context object
        setContentView(R.layout.activity_chat_window);
        // ListView theList = (ListView)findViewById(R.id.theList);
        editChatText = (EditText)findViewById(R.id.editChatText);

        theList = (ListView)findViewById(R.id.theList);
        //final EditText editChatText = (EditText)findViewById(R.id.editChatText);


        final ChatAdapter messageAdapter =new ChatAdapter( this );   //not final??????????
        theList.setAdapter (messageAdapter);

        // setContentView(R.layout.activity_chat_window);

        // Button sendButton = (Button)findViewById(R.id.sendButton);
        // Button sendButton = (Button)findViewById(R.id.sendButton);


//        theList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d(TAG, "onItemClick: " + i + " " + l);
//               //messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/getView()
//            }
//        });

        //editChatText = (EditText)findViewById(R.id.editChatText);

        Button sendButton = (Button)findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                arrayList.add(editChatText.getText().toString());
                //  Intent intent = new Intent(StartActivity.this, ListItemsActivity.class);

                //   startActivityForResult(intent,5);
                // editChatText.setText("");
                // theList.addView();

                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/getView()
                editChatText.setText("");

            }

        });

    }

    // #5
    private class ChatAdapter extends ArrayAdapter<String> {
        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }


        //#6
        public int getCount() {
            // return theList.getCount();
            return arrayList.size();
        }


        public String getItem(int position) {
            //return theList.get(position);
            return arrayList.get(position);
        }


        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if (position % 2 == 0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
                TextView message = (TextView) result.findViewById(R.id.message_text);
                message.setText(getItem(position)); // get the string at position
                return result;
            }
            else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);

                TextView message = (TextView) result.findViewById(R.id.message_text);
                message.setText(getItem(position)); // get the string at position
                return result;
            }
            // return result;

            // return convertView;  // not sure wat return??????
        }

    }

}
