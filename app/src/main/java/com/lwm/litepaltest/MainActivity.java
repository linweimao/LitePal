package com.lwm.litepaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        Button addData = (Button) findViewById(R.id.add_data);
        Button updateData = (Button) findViewById(R.id.update_data);
        Button deleteButton = (Button) findViewById(R.id.delete_data);
        Button queryButton = (Button) findViewById(R.id.query_data);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
            }
        });

        // 添加数据
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();
            }
        });

//        // 这种更新方式只能对已存储的对象进行操作,限制性比较大
//        updateData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Book book = new Book();
//                book.setName("The Lost Symbol");
//                book.setAuthor("Dan Brown");
//                book.setPages(510);
//                book.setPrice(19.95);
//                book.setPress("Unknow");
//                book.save();
//                book.setPrice(10.99);
//                book.save();
//            }
//        });

        // 比较灵巧的更新方式
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name=? and author = ?", "The Lost Symbol", "Dan Brown");
                book.save();
            }
        });

//        // 将某个字段的数据更新为默认值（例如：int的默认值为0 , boolean的默认值为false , String的默认值为null）
//        updateData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Book book = new Book();
//                book.setToDefault("pages");
//                book.updateAll();
//            }
//        });

        // 删除数据
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Book.class, "price < ?", "15");
            }
        });

        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QueryActivity.class);
                startActivity(intent);
            }
        });

    }
}
