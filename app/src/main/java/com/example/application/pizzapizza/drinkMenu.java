package com.example.application.pizzapizza;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class drinkMenu extends AppCompatActivity {
    ListView listView;
    String dTitle[]={"coffee","tea","moka","lemon","nescafe","oreo","cappuccino"};
    String dDescription[]={"coffee description","tea description","moka description","lemon description","nescafe description","oreo description","cappuccino description"};
    int dImage[]={R.drawable.coffee,R.drawable.tea,R.drawable.moka,R.drawable.lemon,R.drawable.nescafe,R.drawable.oreo,R.drawable.cappuccino};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu);

        listView = findViewById(R.id.drink_list);
        myAdapter adapter = new myAdapter(this,dTitle,dDescription,dImage);
        listView.setAdapter(adapter);

    }

    class myAdapter extends ArrayAdapter<String>{
        Context context;
        String nTitle[];
        String nDescription[];
        int nImage[];


        public myAdapter(@NonNull Context c, String Title[], String Description[] , int Image[]) {
            super(c, R.layout.activity_my_row, R.id.textView1 ,Title );
            this.context = c ;
            this.nTitle = Title;
            this.nDescription = Description;
            this.nImage = Image;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.activity_my_row , parent ,false);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);
            ImageView myImage = row.findViewById(R.id.image);

            myTitle.setText(nTitle[position]);
            myDescription.setText(nDescription[position]);
            myImage.setImageResource(nImage[position]);

            return row;
        }


    }

}
