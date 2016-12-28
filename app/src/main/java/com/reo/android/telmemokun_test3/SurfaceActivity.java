package com.reo.android.telmemokun_test3;

import java.util.ArrayList;

import com.reo.android.telmemokun_test2.R;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SurfaceActivity extends Activity{

	private final static int REQUEST_CODE = 4827485;

	private final int CALL_ITEM = 0;
	private final int CANCEL_ITEM = 1;

	private String resultData;
	private static String resultsString = "";
	private static String keyword = "";

	private static Intent data;

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.surface);
		Intent i = getIntent();
		resultData = i.getStringExtra("INPUTDATA");
		Intent j = getIntent();
		keyword = j.getStringExtra("KEYWORD");
	}

	public SurfaceActivity(){
		
	}

	public void onClickButton1(View view){
		Intent intent = new Intent(
				SurfaceActivity.this,
				MainActivity.class);

		startActivity(intent);
	}

	public void onClicktel(View view){
        try {
            Intent intent = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(
                RecognizerIntent.EXTRA_PROMPT,
                "合言葉は？");
            
            startActivityForResult(intent, REQUEST_CODE);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(SurfaceActivity.this,
                "ActivityNotFoundException", Toast.LENGTH_LONG).show();
            }
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case CALL_ITEM:
			Intent intent = new Intent(
					Intent.ACTION_CALL,Uri.parse("tel:" + resultData));
			startActivity(intent);
			break;
		case CANCEL_ITEM:
			// 何もしないでキャンセル
			break;
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		menu.add(0,CALL_ITEM, 0, R.string.call);
		menu.add(0,CANCEL_ITEM,1,R.string.cancel);
		return true;
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if ((REQUEST_CODE == requestCode) && (RESULT_OK == resultCode)){
			ArrayList<String> results =
					data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(results.get(0));
			resultsString = stringBuffer.toString().trim();
		       if (keyword.equals("")){
		           resultsString = "";
		            Toast.makeText(this, "合言葉を入力してください・・・", Toast.LENGTH_LONG).show();
		    		Intent intent = new Intent(
		    				SurfaceActivity.this,
		    				MainActivity.class);

		    		startActivity(intent);
		       }
		       else if (!(resultsString.equals(keyword))){
		           resultsString = "";
		           Toast.makeText(this, "合言葉が違います・・・", Toast.LENGTH_LONG).show();
		   			Intent intent = new Intent(
		   			SurfaceActivity.this,
		   			MainActivity.class);

		   			startActivity(intent);
		       }
		       else if (resultsString.equals(keyword)){
					resultsString = "";
		           Toast.makeText(this, "【認証完了】", Toast.LENGTH_LONG).show();
		    			Intent intent = new Intent(
		    					Intent.ACTION_CALL,Uri.parse("tel:" + resultData));
		    			startActivity(intent);
		    	   }
		       }
		super.onActivityResult(requestCode, resultCode, data);
	}
}
