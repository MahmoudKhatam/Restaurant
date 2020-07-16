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

public class mealMenu extends AppCompatActivity {
    ListView listView ;
    String mtitle[] = {"meat","chicken","macrona","shesh","burger","steak","steak","steak","steak","steak","steak","steak","steak"};
    String mdescribtion[] = {"meat description","chicken description","macrona description","shesh description","burger description","steak description","steak description","steak description","steak description","steak description","steak description","steak description","steak description"};
    int mimage[] = {R.drawable.meat , R.drawable.chiken , R.drawable.macrona , R.drawable.shesh , R.drawable.burger , R.drawable.steak , R.drawable.steakk , R.drawable.steakk , R.drawable.steakk , R.drawable.steakk , R.drawable.steakk , R.drawable.steakk , R.drawable.steakk };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_menu);

        listView = findViewById(R.id.listView);
        myAdabter adabter = new myAdabter(this,mtitle,mdescribtion,mimage);
        listView.setAdapter(adabter);

    }
    class myAdabter extends ArrayAdapter<String>{
        Context context;
        String rtitle[];
        String rdescription[];
        int rimage[];

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.activity_my_row,parent,false);
            TextView mytitle = row.findViewById(R.id.textView1);
            TextView mydescription = row.findViewById(R.id.textView2);
            ImageView myimage = row.findViewById(R.id.image);

            mytitle.setText(rtitle[position]);
            mydescription.setText(rdescription[position]);
            myimage.setImageResource(rimage[position]);


            return row;
        }

         myAdabter(Context c, String title[] , String description[] , int image[] ) {
            super(c, R.layout.activity_my_row,R.id.textView1,title);
            this.context = c ;
            this.rtitle = title ;
            this.rdescription = description ;
            this.rimage = image ;




        }
    }
}
