package com.example.qqlogin;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThirdActivity extends Activity {

	protected static final int FROM_THREE_RESULT = 2;
	EditText ed_number, ed_pwd, ed_confirmPwd;
	Button confirm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		ed_number = (EditText) findViewById(R.id.register_number);
		ed_pwd = (EditText) findViewById(R.id.register_pwd);
		ed_confirmPwd = (EditText) findViewById(R.id.register_acknowledgePwd);
		confirm = (Button) findViewById(R.id.register_ackonwledgeReg);

		confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String number = ed_number.getText().toString();
				String password = ed_pwd.getText().toString();
				String confirmPwd = ed_confirmPwd.getText().toString();
				if (number.trim().length()>0&&password.trim().length()>0) {
					if (password.equals(confirmPwd)) {
						Intent intent=getIntent();
						intent.putExtra("number", number);
						intent.putExtra("pwd", password);
						setResult(FROM_THREE_RESULT, intent);
						
						//将键值装入intent意图后，关闭该页面
						finish();
					} else {
						Toast.makeText(ThirdActivity.this, "密码不一致，请重新输入",
								Toast.LENGTH_SHORT).show();
						ed_confirmPwd.setText("");
					}
				}else {
					Toast.makeText(ThirdActivity.this, "账号和密码不能为空",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

}
