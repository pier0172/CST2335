package com.example.student.andoid;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {

    private static final String ACTIVITY_NAME = "ChatWindow";
    protected ListView theList;
    protected EditText editChatText;
    protected ChatDatabaseHelper chatDatabaseHelper;
    protected SQLiteDatabase db;

    private FrameLayout frameLayout;

    private boolean checkFrameExist;
    private boolean isTablet;
    private static String TAG = "LISTVIEW";
    ChatAdapter messageAdapter;
    private Cursor results;
    int index;
    long dbid;

    ArrayList<String> arrayList = new ArrayList<String>();

    //protected String dataItems[]  = new String[] { "Item1", "Item2", "Item 3", "item4", " ","more items"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //in this case, “this” is the ChatWindow, which is-A Context object

        setContentView(R.layout.activity_chat_window); //chooses tablet or phone layout

//        frameLayout = (FrameLayout) findViewById(R.id.frame);
//        if(frameLayout == null)
//            checkFrameExist = false;
//        else
//            checkFrameExist = true;
        isTablet = (findViewById(R.id.frame) != null); //find out if this is a phone or tablet

        editChatText = (EditText) findViewById(R.id.editChatText);

         chatDatabaseHelper = new ChatDatabaseHelper(this);

         db = chatDatabaseHelper.getWritableDatabase();


        // editChatText = (EditText) findViewById(R.id.editChatText);

        theList = (ListView) findViewById(R.id.theList);


         messageAdapter = new ChatAdapter(this);   //not final??????????
        theList.setAdapter(messageAdapter);


        // lab5
         results = db.query(false, chatDatabaseHelper.TABLE_NAME,
                new String[]{chatDatabaseHelper.KEY_ID, chatDatabaseHelper.KEY_MESSAGE},
                chatDatabaseHelper.KEY_MESSAGE + " not null", null, null, null, null, null);   //NOT SURE
        int rows = results.getCount(); //number of rows returned
        //int keyMassage = results.getColumnIndex(chatDatabaseHelper.KEY_MESSAGE);
        results.moveToFirst(); //move to first result

        while (!results.isAfterLast()) {
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + results.getString(results.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            arrayList.add(results.getString(results.getColumnIndex(chatDatabaseHelper.KEY_MESSAGE)));   // To print message list on screen
            results.moveToNext();
            Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + results.getColumnCount());
        }
        // not sure 
        String columnName;
        for (int i = 0; i > results.getColumnCount(); i++) {
            Log.i(ACTIVITY_NAME, "column name =" + results.getColumnName(i));

        }     //column name



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




        // lab7 #6

        theList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                dbid= messageAdapter.getItemId(i);
                Log.d(TAG, "onItemClick: " + i + " " + l);


                Bundle bun = new Bundle();
                bun.putLong("ID", l );//l is the database ID of selected item
                bun.putString("MESSAGE", arrayList.get(i));

                //step 2, if a tablet, insert fragment into FrameLayout, pass data
                if(isTablet) {
                    MessageFragment frag = new MessageFragment();  //?? not sure

                    frag.setArguments(bun);


                    getFragmentManager().beginTransaction().replace(R.id.frame, frag).commit();  // was fragmentholder
                }
                //step 3 if a phone, transition to empty Activity that has FrameLayout
                else //isPhone
                {
                    Intent intnt = new Intent(ChatWindow.this, MessageDetails.class);
                    intnt.putExtra("ID" , l); //pass the Database ID to next activity
                    intnt.putExtra("MESSAGE" , arrayList.get(i)); //pass the Database meessagge to next activity
                    startActivityForResult(intnt, 5); //go to view fragment details
                }
            }
        });
        //step 1, find out if you are on a phone or tablet.
        isTablet = (findViewById(R.id.frame) != null); //find out if this is a phone or tablet


       // messageAdapter.notifyDataSetChanged(); //tells the list to update the data             not--sure

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
        public long getItemId(int position){
            results.moveToPosition(position);

            return results.getLong(results.getColumnIndex(chatDatabaseHelper.KEY_ID));
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

   // public void deleteDb(long index, long dbid){
    public void deleteDb(){


        Log.d("INDEX LOOKS: ", index+ "");

        Log.d("DBID LOOKS: ", dbid+ ""); //dbid
        db.delete(ChatDatabaseHelper.TABLE_NAME , ChatDatabaseHelper.KEY_ID + "="+ dbid, null); //databse Name?????????????  KEY_MESSAGE  TABLE_NAME
        arrayList.remove(index);
        Cursor results = db.query(false, chatDatabaseHelper.TABLE_NAME,
                new String[]{chatDatabaseHelper.KEY_ID, chatDatabaseHelper.KEY_MESSAGE},
                chatDatabaseHelper.KEY_MESSAGE + " not null", null, null, null, null, null);
        messageAdapter.notifyDataSetChanged();
    }

//        public void deleteDb(){
//        //Log.d("INDEX LOOKS: "+ "");
//        //arrayList.remove(index);
//        //Log.d("DBID LOOKS: ", dbid+ ""); //dbid
//        db.delete(ChatDatabaseHelper.TABLE_NAME , ChatDatabaseHelper.KEY_ID + "="+ dbid, null); //databse Name?????????????  KEY_MESSAGE  TABLE_NAME
//        messageAdapter.notifyDataSetChanged();
//    }


    // remove fragment()  ???????????????????????????????????????????????
    //fragment transaction
    //fragment manager

    //lab7 #8

    public void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode,responseCode,data);
        if (requestCode == 5)
            Log.i(ACTIVITY_NAME, "Return to StartActivity.onActivityResult");
       deleteDb( );
    }
}

