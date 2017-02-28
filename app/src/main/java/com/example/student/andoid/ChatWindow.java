package com.example.student.andoid;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    private static final String ACTIVITY_NAME = "ChatWindow";
    protected ListView theList;
    protected EditText editChatText;
    protected ChatDatabaseHelper chatDatabaseHelper;
    protected SQLiteDatabase db;
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

        editChatText = (EditText) findViewById(R.id.editChatText);

         chatDatabaseHelper = new ChatDatabaseHelper(this);

         db = chatDatabaseHelper.getWritableDatabase();


        // editChatText = (EditText) findViewById(R.id.editChatText);

        theList = (ListView) findViewById(R.id.theList);


        final ChatAdapter messageAdapter = new ChatAdapter(this);   //not final??????????
        theList.setAdapter(messageAdapter);


        // lab5
        Cursor results = db.query(false, chatDatabaseHelper.TABLE_NAME,
                new String[]{chatDatabaseHelper.KEY_ID, chatDatabaseHelper.KEY_MESSAGE},
                chatDatabaseHelper.KEY_MESSAGE + " not null", null, null, null, null, null);   //NOT SURE
        int rows = results.getCount(); //number of rows returned
        results.moveToFirst(); //move to first result

        while (!results.isAfterLast())
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + results.getString(results.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));

        Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + results.getColumnCount());

        // not sure 
        String columnName;
        for (int i = 0; i > results.getColumnCount(); i++) {
            Log.i(ACTIVITY_NAME, "column name =" + results.getColumnName(i));
        }     //column name

        //  lab5 from example
//        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this, R.layout.activity_chat_window, results,
//                new String[]{ chatDatabaseHelper.KEY_MESSAGE}, //from column    chatDatabaseHelper.KEY_ID,
//                new int[]{R.id.theList}, 0  //to layout ID
//        );
        //theList.setAdapter(messageAdapter);

//        //int numColumns = results.getColumnCount();//how many columns are returned
//        int keyID = results.getColumnIndex(chatDatabaseHelper.KEY_ID); //find the index of the name column
//        int keyMassage = results.getColumnIndex(chatDatabaseHelper.KEY_MESSAGE);
//        while (!results.isAfterLast()) {
//            int price = results.getInt(priceIndex); //get int from column 0
//            String name = results.getString(columnName);
//            Log.d("DATABASE ", "Price:" + Integer.toString(price) + " name:" + name);
//            results.moveToNext();
//        }


        //lab4
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

        Button sendButton = (Button) findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                arrayList.add(editChatText.getText().toString());


                ContentValues newValues = new ContentValues();
                newValues.put(chatDatabaseHelper.KEY_MESSAGE, editChatText.getText().toString());
                db.insert(chatDatabaseHelper.TABLE_NAME, "", newValues);

                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/getView()
                editChatText.setText("");

            }

        });
    }

    public void onDestroy(){
        //db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        super.onDestroy();
        db.close();
    }


    // #5
    private class ChatAdapter extends ArrayAdapter<String> {
        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }


        //#6
        public int getCount() {
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

        }

    }



}
