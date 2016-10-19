package com.example.qqlogin;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private static final int TO_THREE_REQUEST = 1;
	EditText qqnumber, userPwd;
	Button login;
	TextView registe;
	private CheckBox rememberPwd;
	//全局的数据存储对象
	SharedPreferences sp;
	Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		qqnumber = (EditText) findViewById(R.id.userNumber);
		userPwd = (EditText) findViewById(R.id.userPwd);
		login = (Button) findViewById(R.id.login);
		registe = (TextView) findViewById(R.id.register);
		
		rememberPwd=(CheckBox) findViewById(R.id.rememberPwd);
		sp=getSharedPreferences("QQUserInfo2", MODE_PRIVATE);
		editor=sp.edit();
		
		login.setOnClickListener(this);
		registe.setOnClickListener(this);

		String number=sp.getString("num", "");
		String password=sp.getString("num", "");
		
		if (number!=null&&password!=null) {
			if (sp.getBoolean("isRememSelect", false)) {
				qqnumber.setText(number);
				userPwd.setText(password);
				rememberPwd.setSelected(true);
			}else {
				rememberPwd.setSelected(false);
			}
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.login:
			String str1 = qqnumber.getText().toString();
			String str2 = userPwd.getText().toString();
			
			if (str1.trim().length() > 0 && str2.trim().length() > 0) {
				if (str1.equals("123") && str2.equals("abc")) {
					//
					if (rememberPwd.isSelected()) {
						editor.putString("num", str1);
						editor.putString("pwd", str2);
						editor.putBoolean("isRememSelect", true);
						editor.commit();
					}else {
						editor.clear();
						editor.commit();
					}
					
					Intent intent = new Intent(MainActivity.this,
							SecondActivity.class);
					/*
					 * 将键和值放入intent中,这种方法只能传到一个新的页面
					 */
					intent.putExtra("name", str1);
					/*
					 * 第二种方法，将值放入bundle，在将bundle放入intent（这种方法麻烦，不推荐）
					 */
					// Bundle bundle=new Bundle();
					// bundle.putString("name", str1);
					// intent.putExtras(bundle);
					
					startActivity(intent);
					
				} else {
					Toast.makeText(MainActivity.this, "用户名或密码错误",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(MainActivity.this, "用户名或密码不能为空",
						Toast.LENGTH_SHORT).show();
			}
			break;

		case R.id.register:
			Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
			// 请求码>=0，则当现在启动的新页面被关闭时，当前页面会回调一个方法onActivityResult()
			// 请求码推荐使用静态常量
			startActivityForResult(intent, TO_THREE_REQUEST);
			break;
		
		}
	}

	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		/*
		 * 当请求码=请求常量，且结果码=结果常量 时即可匹配到所要的数据
		 */
		if (requestCode == TO_THREE_REQUEST
				&& resultCode == ThirdActivity.FROM_THREE_RESULT) {// 结果码常量设为public在这里就可调用
			if (data != null) {// 返回的意图不为空
				String number = data.getStringExtra("number");
				String pwd = data.getStringExtra("pwd");

				qqnumber.setText(number);
				userPwd.setText(pwd);
			}
		}
	}
}
