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

public class pizzaMenu extends AppCompatActivity {
    ListView listView ;
    String ptitle[] = {"margreta","mix","seafood","sodaa","vegetables","mshkl gbn","margreta","margreta","margreta"};
    String pdescription[] = {"margreta description","mix description","seafood description","sodaa description","vegetables description","mshkl gbn description","margreta description","margreta description","margreta description"};
    int pimage[] ={R.drawable.mix,R.drawable.pizza,R.drawable.pizzaalb,R.drawable.seafood,R.drawable.sodaa,R.drawable.vegatables,R.drawable.mshklgbn,R.drawable.mshklgbn,R.drawable.mshklgbn};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_menu);

        listView = (ListView) findViewById(R.id.pizzaList);
        Adapter adb = new Adapter(this,ptitle,pdescription,pimage);
        listView.setAdapter(adb);

    }
     class Adapter extends ArrayAdapter<String>{
        Context context ;
        String xtitle[] ;
        String xdescription[];
        int ximage[];

         @NonNull
         @Override
         public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
             LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             View row = inflater.inflate(R.layout.activity_my_row , parent , false);
             TextView myTitle = row.findViewById(R.id.textView1);
             TextView myDescirption = row.findViewById(R.id.textView2);
             ImageView myImage = row.findViewById(R.id.image);

             myTitle.setText(xtitle[position]);
             myDescirption.setText(xdescription[position]);
             myImage.setImageResource(ximage[position]);

             return row;
         }


        public Adapter(@NonNull Context c, String title[], String description[] , int image[]) {
            super(c, R.layout.activity_my_row, R.id.textView1 , title);
            context=c;
            this.xtitle= title;
            this.xdescription =  description;
            this.ximage =image;


        }

     }


}
