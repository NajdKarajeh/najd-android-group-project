package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String urladdress="http://192.168.1.70/loginreg/rooms.php";
    String[] roomNumber;
    String[] price;
    String[] imagepath;
    ListView listView;
    BufferedInputStream is;
    String line=null;
    String result=null;

    EditText search;

  public static  ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = (ListView) findViewById(R.id.lview);
        search = (EditText) findViewById(R.id.edSearch);




        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData();
        CustomListView customListView=new CustomListView(this,roomNumber,price,imagepath);
        listView.setAdapter(customListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                TextView textView = (TextView) view.findViewById(R.id.textViewRoomNumber);
                TextView textViewPrice = (TextView) view.findViewById(R.id.textViewPrice);
                imageView = (ImageView) findViewById(R.id.imageViewRoomPhoto);



                Intent intent = new Intent(HomeActivity.this, RoomActivity.class);
                Bundle b = new Bundle();


                imageView.invalidate();
                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
               // Toast.makeText(getApplicationContext(), drawable.toString(), Toast.LENGTH_SHORT).show();
                Bitmap bitmap = drawable.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                b.putString("key", textView.getText().toString().trim()); //Your id
                b.putString("price", textViewPrice.getText().toString().trim()); //Your id
                intent.putExtra("image",byteArray);
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }


        });

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                try{
                    String roomNo= search.getText().toString();
                    int index = Integer.parseInt(roomNo.substring(3));
                listView.performItemClick(listView.getAdapter().getView(index-1, null, null), index-1, listView.getItemIdAtPosition(3));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        });

    }




    private void collectData()
    {
//Connection
        try{

            URL url=new URL(urladdress);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        //content
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuilder sb=new StringBuilder();
            while ((line=br.readLine())!=null){
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();

        }

//JSON
        try{
            JSONArray ja=new JSONArray(result);
            JSONObject jo=null;
            roomNumber=new String[ja.length()];
            price=new String[ja.length()];
            imagepath=new String[ja.length()];

            for(int i=0;i<=ja.length();i++){
                jo=ja.getJSONObject(i);
                roomNumber[i]=jo.getString("number");
                price[i]=jo.getString("price_per_night");
                imagepath[i]=jo.getString("photo");
            }
        }
        catch (Exception ex)
        {

            ex.printStackTrace();
        }


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
