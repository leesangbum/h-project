package com.hosoart.hwatch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

	static Vibrator vibrator;
	private static final String TAG = "hosup_main_activity";
//	ExampleThread thread;
	Handler mHandler = null;
	Timer mTimer = null;
	TimerTask mTask = null;
	TextView txtv;
	int second = 0;
	boolean timer_is_running = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		btn =(Button)findViewById(R.id.button);
//		edit = (EditText)findViewById(R.id.edit_text);
		txtv = (TextView)findViewById(R.id.text_view);

		AdView mAdView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);

		Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

		Log.v(TAG,"MAIN Thread start");
		//thread = new ExampleThread(0);
		//thread.start();
	//		for(int i = 0; i <=3 ; i++) {
	//			thread = new ExampleThread(i);
	//			thread.start();
	//		}
	//
	//
	//		try{
	//			thread.join();
	//		}catch (InterruptedException e){
	//
	//		}
	//		Log.v(TAG,"MAIN Thread stop");

		mHandler = new Handler();





//		Thread t = new Thread(new Runnable() {
//			int second = 0;
//			@Override
//			public void run() {
//				//ui x
//
//				for (int i = 0; i < 100; i++){
//					second++;
//				}
//				mHandler.postDelayed(new Runnable() {
//					@Override
//					public void run() {
//						//ui o
//						btn.setText(Integer.toString(second));
//					}
//				},3000);
//			}
//		});
//		t.start();

	}

	public void onClick(View view) {
		switch(view.getId()){
			case R.id.btn_start :
				vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
				vibrator.vibrate(1000);

//				String text = edit.getText().toString();
//				Intent intent = new Intent(this, LoginActivity.class);
//				intent.putExtra("value", text);
//				startActivityForResult(intent,0);
				if(!timer_is_running){
					timer_is_running = true;
					mTimer = new Timer();
					mTask = new TimerTask() {

						@Override
						public void run() {
							Log.v(TAG,Integer.toString(second));
							mHandler.postDelayed(new Runnable() {
								@Override
								public void run() {
									txtv.setText(Integer.toString(second++));
								}
							},1000);
						}
					};
					mTimer.schedule(mTask,0,1000);
				}
				break;
			case R.id.btn_stop:

				if(timer_is_running){

					mTimer.cancel();
					mTimer.purge();
					mTimer = null;
					mTask = null;
					timer_is_running = false;

				}
				break;
			case R.id.btn_clear:
				second = 0;
				txtv.setText(Integer.toString(second));
			case R.id.main_layout:
				Toast.makeText(MainActivity.this,"Mainactivity", Toast.LENGTH_SHORT).show();
				break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode){
			case 0: //login
				Log.v(TAG,String.valueOf(resultCode));
				break;
			default :
				break;
		}
	}

	private class ExampleThread extends Thread {
		private static final String TAG_THREAD = "hosup_thread";
		private int threadNum = 0;
		public ExampleThread(int threadNum){
			this.threadNum = threadNum;

		}

		@Override
		public void run() {
			super.run();
			int second = 0;
			//while(true){
				second++;
				try{
					Log.v(TAG_THREAD, "start thread : ".concat(Integer.toString(threadNum)));
					Thread.sleep(1000);
				} catch (InterruptedException e){
					e.printStackTrace();
				}
				Log.v(TAG_THREAD, "stop thread : ".concat(Integer.toString(threadNum)));
			//}
		}
	}
}
