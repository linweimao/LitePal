package com.lwm.litepaltest;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class QueryActivity extends AppCompatActivity {

    private static final String TAG = "QueryActivity";
    private Button queryData, queryData1, queryData2, queryData3, queryData4,
            queryData5, queryData6, queryData7, queryData8, queryData9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        initView();
        query();
    }

    private void initView() {
        queryData = (Button) findViewById(R.id.query_data);
        queryData1 = (Button) findViewById(R.id.query_data1);
        queryData2 = (Button) findViewById(R.id.query_data2);
        queryData3 = (Button) findViewById(R.id.query_data3);
        queryData4 = (Button) findViewById(R.id.query_data4);
        queryData5 = (Button) findViewById(R.id.query_data5);
        queryData6 = (Button) findViewById(R.id.query_data6);
        queryData7 = (Button) findViewById(R.id.query_data7);
        queryData8 = (Button) findViewById(R.id.query_data8);
        queryData9 = (Button) findViewById(R.id.query_data9);
    }


    public void query() {
        // 打印查询到的数据
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 查询 Book 表的所有数据,
                // 与 Cursor cursor = db.query("Book",null,null,null,null,null,null);功能一样
                // findAll() 方法通过 Book.class 参数指定查询 Book 表，
                // 另外, findAll() 方法的返回值实体个 Book 类型的List 集合,也就是说 LitePal 自动帮我们完成了赋值操作
                List<Book> books = LitePal.findAll(Book.class);
                for (Book book : books) {
                    Log.d(TAG, "book name is: " + book.getName());
                    Log.d(TAG, "book author is: " + book.getAuthor());
                    Log.d(TAG, "book pages is: " + book.getPages());
                    Log.d(TAG, "book price is: " + book.getPrice());
                    Log.d(TAG, "book press is: " + book.getPress());
                }
            }
        });

        // 打印第一条数据
        queryData1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book firstBook = LitePal.findFirst(Book.class);
                Log.d(TAG, "book name is: " + firstBook.getName());
                Log.d(TAG, "book author is: " + firstBook.getAuthor());
                Log.d(TAG, "book pages is: " + firstBook.getPages());
                Log.d(TAG, "book price is: " + firstBook.getPrice());
                Log.d(TAG, "book press is: " + firstBook.getPress());
            }
        });

        // 打印最后一条数据
        queryData2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book lastBook = LitePal.findLast(Book.class);
                Log.d(TAG, "book name is: " + lastBook.getName());
                Log.d(TAG, "book author is: " + lastBook.getAuthor());
                Log.d(TAG, "book pages is: " + lastBook.getPages());
                Log.d(TAG, "book price is: " + lastBook.getPrice());
                Log.d(TAG, "book press is: " + lastBook.getPress());
            }
        });

        // select() 方法用于指定查询哪几列，对应了 SQL中的 select 关键字。查询name和author两列的数据
        queryData3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.select("name", "author").find(Book.class);
                Log.d(TAG, "book name is: " + books.get(0).getName());
                Log.d(TAG, "book author is: " + books.get(0).getAuthor());
            }
        });

        // where() 方法用于指定查询的约束条件，对应了SQL当中的where关键字。比如查询页数大于400的数据
        queryData4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.where("pages > ?", "400").find(Book.class);
                Log.d(TAG, "book name is: " + books.get(0).getName());
                Log.d(TAG, "book author is: " + books.get(0).getAuthor());
                Log.d(TAG, "book pages is: " + books.get(0).getPages());
                Log.d(TAG, "book price is: " + books.get(0).getPrice());
                Log.d(TAG, "book press is: " + books.get(0).getPress());
            }
        });

        // order() 方法用于指定结果的排序方式，对应了SQL中的 order by关键字。比如将查询结果按照书价格从高到底进行排序
        queryData5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.order("price desc").find(Book.class);
                for (Book book : books) {
                    Log.d(TAG, "book name is: " + book.getName());
                    Log.d(TAG, "book author is: " + book.getAuthor());
                    Log.d(TAG, "book pages is: " + book.getPages());
                    Log.d(TAG, "book price is: " + book.getPrice());
                    Log.d(TAG, "book press is: " + book.getPress());
                }
            }
        });

        // limit() 方法用于指定查询结果的数量
        queryData6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.limit(3).find(Book.class);
                Log.d(TAG, "book name is: " + books.get(0).getName());
                Log.d(TAG, "book author is: " + books.get(0).getAuthor());
                Log.d(TAG, "book pages is: " + books.get(0).getPages());
                Log.d(TAG, "book price is: " + books.get(0).getPrice());
                Log.d(TAG, "book press is: " + books.get(0).getPress());
            }
        });

        // offset() 方法用于指定查询结果的偏移量
        queryData7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.limit(3).offset(1).find(Book.class);
                Log.d(TAG, "book name is: " + books.get(0).getName());
                Log.d(TAG, "book author is: " + books.get(0).getAuthor());
                Log.d(TAG, "book pages is: " + books.get(0).getPages());
                Log.d(TAG, "book price is: " + books.get(0).getPrice());
                Log.d(TAG, "book press is: " + books.get(0).getPress());
            }
        });

        // 连缀组合查询
        queryData8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.select("name", "author", "pages")
                        .where("pages > ?", "400")
                        .order("pages")
                        .limit(10)
                        .offset(10)
                        .find(Book.class);
                for (Book book : books) {
                    Log.d(TAG, "book name is: " + book.getName());
                    Log.d(TAG, "book author is: " + book.getAuthor());
                    Log.d(TAG, "book pages is: " + book.getPages());
                    Log.d(TAG, "book price is: " + book.getPrice());
                    Log.d(TAG, "book press is: " + book.getPress());
                }
            }
        });

        // 使用原生SQL查询
        queryData9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = LitePal.findBySQL("select * from Book where pages > ? and price < ?", "400", "20");
                if (cursor.moveToFirst()) {
                    do {
                        // 遍历 Cursor 对象,取出数据并打印
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("name"));
                        String press = cursor.getString(cursor.getColumnIndex("name"));
                        Log.d(TAG, "book name is: " + name);
                        Log.d(TAG, "book author is: " + author);
                        Log.d(TAG, "book pages is: " + pages);
                        Log.d(TAG, "book price is: " + price);
                        Log.d(TAG, "book press is: " + press);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

    }
}
