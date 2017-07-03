package com.example.MenuExample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.*;
import android.widget.*;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.*;

import android.support.v4.app.NotificationCompat;
//import android.app.PendingIntent;
import android.content.Context;
//import android.content.Intent;
import android.app.NotificationManager;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView1=(TextView)findViewById(R.id.view1);
        registerForContextMenu(textView1); //Context Menu

        //work on listView
        // ListView on item selected listener.
        ListView listView = (ListView)findViewById(R.id.lvDocs);
        listView.setOnItemClickListener(new OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Create PopUp Menu
                PopupMenu popup = new PopupMenu(MainActivity.this, view);
                popup.getMenuInflater().inflate(R.menu.popup_main, popup.getMenu());
                popup.show();

                //pop up listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
            }
        });

    }



    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this,"Email List Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                Toast.makeText(this,"Contact List Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item3:
                Toast.makeText(this,"FAQ Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item4:
                Toast.makeText(this,"Share Selected",Toast.LENGTH_LONG).show();
                return true;
            default:
                Toast.makeText(getApplicationContext(),"Unknown option",Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");

       // String action_contact[] = getResources().getStringArray(R.array.context_call);
       // for (int i=0; i<action_contact.length; i++){
       //     menu.add(0, v.getId(), i+1, action_contact[i]);//groupId, itemId, order, title
       // }

        menu.add(0, v.getId(), 1, "Call");//groupId, itemId, order, title
        menu.add(0, v.getId(), 2, "SMS");


    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
       /* if(item.getTitle()=="Call"){
            Toast.makeText(getApplicationContext(),"calling code",Toast.LENGTH_LONG).show();
        }
        else if(item.getTitle()=="SMS"){
            Toast.makeText(getApplicationContext(),"sending sms code",Toast.LENGTH_LONG).show();
        }else{
            return false;
        } */

        Toast.makeText(this, "Menu Item " + item.getOrder() + ": " + item.getTitle() + " is selected", Toast.LENGTH_LONG).show();
        return true;
    }

    public void onClickPopUp(View view){
        PopupMenu popup = new PopupMenu(MainActivity.this, view);
        popup.getMenuInflater().inflate(R.menu.popup_main, popup.getMenu());
        popup.show();
    }


    public void onClickNotification(View view) {
        //basic lines
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.schedule);
       // builder.setContentTitle("Notifications for Android Studio Workshop");
        //builder.setContentText("Today topic is creating notification");

        // Add as notification
        //NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
       // manager.notify(0, builder.build());


        //DO more - new method code
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        String[] events = new String[6];
        events[0] = new String("Dear Student,");
        events[1] = new String("Congratulations and you have made it!");
        events[2] = new String("3rd line");
        events[3] = new String("4th line...");
        events[4] = new String("5th line...");
        events[5] = new String("From Teacher");

        // Sets a title for the Inbox style big view
        inboxStyle.setBigContentTitle("Big Title Details:");
        // Moves events into the big view
        for (int i=0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }
        builder.setStyle(inboxStyle);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }





}


