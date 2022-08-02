package com.ak.pro11;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7;
    Button b1,b2,b3;
    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB=new DatabaseHelper(this);
        b1= findViewById(R.id.button);
        b2= findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        e1= findViewById(R.id.editText);
        e2= findViewById(R.id.editText2);
        e3= findViewById(R.id.editText3);
        e4= findViewById(R.id.editText4);
        e5= findViewById(R.id.editText5);
        e6= findViewById(R.id.editText6);
        e7=(EditText)findViewById(R.id.editText7);
        AddData();
        viewAll();
        DeleteAll();
    }
    public void AddData(){
        b1.setOnClickListener(  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted= myDB.insertData(Integer.parseInt(e1.getText().toString()),e2.getText().toString(),e3.getText().toString(),e4.getText().toString(),e5.getText().toString());
                if(isInserted==true){
                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void DeleteAll(){
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str1 = Integer.parseInt(e6.getText().toString());
                boolean res = myDB.deleteData(str1);
                if(res==true){
                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void viewAll(){
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str=Integer.parseInt(e7.getText().toString());
                Cursor res= myDB.getAllData(str);
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this,"Nothing Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                while (res.moveToNext()){
                    Toast.makeText(MainActivity.this,"ID: "+res.getString(0)+"\n"+
                            "Roll No.: "+res.getString(1)+"\n"+
                            "Branch: "+res.getString(2)+"\n"+
                            "Marks: "+res.getString(3)+"\n"+
                            "Percentage: "+res.getString(4)+"\n",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
