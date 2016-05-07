package com.example.trim.contentprovider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    private TextView tvSender = null;
    private TextView tvContent = null;
    private TextView tvTime = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Intent intent = getIntent();

        tvSender = (TextView) findViewById(R.id.tvSender);
        tvContent = (TextView) findViewById(R.id.tvContent);
        tvTime = (TextView) findViewById(R.id.tvTime);

        String address = intent.getStringExtra("address");
        String body = intent.getStringExtra("body");
        String time = intent.getStringExtra("time");

        tvSender.setText(address);
        tvContent.setText(body);
        tvTime.setText(time);
    }
}
