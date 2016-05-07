package com.example.trim.contentprovider;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Context mContext = null;
    private ListView mListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
                float Rotation = view.getRotation();
                MyLog.i("Rotation = "+Rotation);
                Toast.makeText(mContext, "Replace with your own action"+Rotation, Toast.LENGTH_SHORT).show();

                view.setRotation(Rotation+90);
                MyLog.i("Rotation = "+view.getRotation());
            }
        });

        mListView = (ListView) findViewById(R.id.listView);
//        final ArrayList<Message> arrayList = new ArrayList<Message>();
//        for(int i = 0; i<10; i++){
//            arrayList.add(new Message(""+10000+i, "Hello LinChengChun,how are you?",
//                    new Date().toString(), null));
//            SystemClock.sleep(500);
//        }
        final ArrayList<Message> arrayList = readMessage();
        AppBaseAdapter<Message> adapter = new MessageAdapter(mContext, arrayList);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Message msg;
                MyLog.i("view: "+view.toString());
                MyLog.i("position: "+position);
                MyLog.i("id: "+id);

                Message msg = arrayList.get(position);

                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                intent.putExtra("address", msg.getAddress());
                intent.putExtra("body", msg.getBody());
                intent.putExtra("time", msg.getTime());
                startActivity(intent);

                CustomToast.showToast(mContext, Toast.LENGTH_SHORT, "position = "+position);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_settings:
                MyLog.i("Setting item is being click");
                CustomToast.showToast(mContext, Toast.LENGTH_SHORT, "action: "+ item.getTitle());
                break;
            case R.id.action_control:
                MyLog.i("action: "+ item.getTitle());
                CustomToast.showToast(mContext, Toast.LENGTH_SHORT, "action: "+ item.getTitle());
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    ArrayList<Message> readMessage(){
        ArrayList<Message> arrayList = new ArrayList<>();
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = resolver.query(uri, null, null, null, null);

        int i = 0;
        while (cursor.moveToNext()){
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String body = cursor.getString(cursor.getColumnIndex("body"));
            MyLog.e("短信。。。"+address + body);

            arrayList.add(new Message(address, body, new Date().toString(), null));
        }
        return arrayList;
    }
}
