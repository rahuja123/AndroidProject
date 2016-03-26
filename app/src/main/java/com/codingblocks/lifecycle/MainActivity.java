package com.codingblocks.lifecycle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    List<String> datalist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Main Activity", "Created");
        listView = (ListView)findViewById(R.id.listView);
        datalist= new ArrayList<String>();

        for(int i=0; i<25; i++){
            datalist.add("Names " + (i+1));
        }
        adapter= new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, datalist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //doubt what is parent
                String name = adapter.getItem(position);
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
            }
            //we have created anonymous class method and object create karlia hai.
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Confirm");

                builder.setPositiveButton("OK?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        datalist.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "item deleted", Toast.LENGTH_SHORT).show();
                    }

                });
                builder.create().show();
            return true;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Main Activity", "Started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Main Activity", "Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Main Activity", "Paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Main Activity", "Stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Main Activity", "Destroyed");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==  R.id.add_item){
            final AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Add Element");
            LayoutInflater inflater= getLayoutInflater();
            View view= inflater.inflate(R.layout.elemntadd, null);
            builder.setView(view);

            final TextView text= (TextView)view.findViewById(R.id.editText);
            builder.setPositiveButton("OK?", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String hello= text.getText().toString();

                    datalist.add(hello);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, hello, Toast.LENGTH_SHORT).show();

                }
            });
            builder.setNegativeButton("Cancel?", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "item not added", Toast.LENGTH_SHORT).show();
                }
            });
            builder.create().show();

          datalist.add("new name");
            adapter.notifyDataSetChanged();

      }
       else if(item.getItemId()==  R.id.delete_item){
            AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Confirm");
            LayoutInflater inflater= getLayoutInflater();
            View view= inflater.inflate(R.layout.dialouge, null);

            builder.setView(view);

            builder.setPositiveButton("OK?", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    datalist.remove(datalist.size() - 1);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "item deleted", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("Cancel?", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "item not deleted", Toast.LENGTH_SHORT).show();
                }
            });

            builder.create().show();

        }

        return true;
    }



}