package com.example.qqlogin;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class SecondActivity extends Activity {

	TextView welcome;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		welcome=(TextView) findViewById(R.id.welcom);
		//获取从上个页面来到的intent
		Intent intent=getIntent();
		//取出intent键对应的值
		String str=intent.getStringExtra("name");
		welcome.setText(str+"欢迎您！");
	}

}
