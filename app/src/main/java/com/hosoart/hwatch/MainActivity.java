package com.hosoart.hwatch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	static Vibrator vibrator;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn =(Button)findViewById(R.id.button);
		EditText edit = (EditText)findViewById(R.id.edit_text);

		btn.setText("this is test");
		Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
	}

	public void onClick(View view) {
		switch(view.getId()){
			case R.id.button :
				Toast.makeText(this, "this is test", Toast.LENGTH_SHORT).show();
				vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
				vibrator.vibrate(100);
				Intent intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
				break;
		}
	}
}
