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
		//��ȡ���ϸ�ҳ��������intent
		Intent intent=getIntent();
		//ȡ��intent����Ӧ��ֵ
		String str=intent.getStringExtra("name");
		welcome.setText(str+"��ӭ����");
	}

}
