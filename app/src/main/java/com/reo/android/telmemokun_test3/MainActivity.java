package com.reo.android.telmemokun_test3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.reo.android.telmemokun_test3.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends Activity{
		

	public final String TEL1_DATA = "data1tel.txt";
	public final String TEL2_DATA = "data2tel.txt";
	public final String TEL3_DATA = "data3tel.txt";
	public final String TEL4_DATA = "data4tel.txt";
	public final String TEL5_DATA = "data5tel.txt";
	public final String SPELL_DATA = "data6spell.txt";
	
	private final int SETTING_ITEM = 0;
	private final int CANCEL_ITEM = 1;

	private static int PATTERN_ZERO = 0;
	private static int PATTERN_ONE = 11111;
	private static int PATTERN_TWO = 22222;	
	private static int PATTERN_THREE = 33333;
	private static int PATTERN_FOUR = 44444;
	private static int PATTERN_FIVE = 55555;
	
	private static String TEL_NUMBER_ONE = "";
	private static String TEL_NUMBER_TWO = "";
	private static String TEL_NUMBER_THREE = "";
	private static String TEL_NUMBER_FOUR = "";
	private static String TEL_NUMBER_FIVE = "";
	private static String SPELL_SUMMON = "";
	
	private static int speak = PATTERN_ZERO;
    
	EditText edittext1;
	EditText edittext2;
	EditText edittext3;
	EditText edittext4;
	EditText edittext5;
	EditText edittextsummon;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RelativeLayout layout = new RelativeLayout(this);
		WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
	    Display disp = wm.getDefaultDisplay();
		layout.setBackgroundColor(Color.GRAY);
		RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup1);
		group.check(R.id.radioButton1);
	}
	
	public MainActivity(){
		readFile1();
		readFile2();
		readFile3();
		readFile4();
		readFile5();
		readFile6();
	}
	
	public void onClick_set(View view){
			set_TEL_NUMBER_ONE();
			set_TEL_NUMBER_TWO();
			set_TEL_NUMBER_THREE();
			set_TEL_NUMBER_FOUR();
			set_TEL_NUMBER_FIVE();
			set_SUMMON();
			writeFiles();
			telSpeak();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		menu.add(0,SETTING_ITEM, 0, R.string.setting_done);
		menu.add(0,CANCEL_ITEM,1,R.string.cancel);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case SETTING_ITEM:
			set_TEL_NUMBER_ONE();	
			set_TEL_NUMBER_TWO();
			set_TEL_NUMBER_THREE();
			set_TEL_NUMBER_FOUR();
			set_TEL_NUMBER_FIVE();
			set_SUMMON();
			writeFiles();
			telSpeak();
			break;
		case CANCEL_ITEM:
			// 何もしないでキャンセル
			break;
		}
		return true;
	}
	
	public void set_TEL_NUMBER_ONE(){
		EditText edittext1 = (EditText) findViewById(R.id.editText1);
		TEL_NUMBER_ONE = edittext1.getText().toString().trim();
	}

	public void set_TEL_NUMBER_TWO(){
		EditText edittext2 = (EditText) findViewById(R.id.editText2);
		TEL_NUMBER_TWO = edittext2.getText().toString().trim();
	}

	public void set_TEL_NUMBER_THREE(){
		EditText edittext3 = (EditText) findViewById(R.id.editText3);
		TEL_NUMBER_THREE = edittext3.getText().toString().trim();
	}

	public void set_TEL_NUMBER_FOUR(){
		EditText edittext4 = (EditText) findViewById(R.id.editText4);
		TEL_NUMBER_FOUR = edittext4.getText().toString().trim();
	}

	public void set_TEL_NUMBER_FIVE(){
		EditText edittext5 = (EditText) findViewById(R.id.editText5);
		TEL_NUMBER_FIVE = edittext5.getText().toString().trim();
	}
	
	public void set_SUMMON(){
		EditText edittextsummon = (EditText)findViewById(R.id.editText_summon);
		SPELL_SUMMON = edittextsummon.getText().toString().trim();
	}
		
	public String get_TEL_NUMBER_ONE(){
		return TEL_NUMBER_ONE;
	}

	public String get_TEL_NUMBER_TWO(){
		return TEL_NUMBER_TWO;
	}

	public String get_TEL_NUMBER_THREE(){
		return TEL_NUMBER_THREE;
	}

	public String get_TEL_NUMBER_FOUR(){
		return TEL_NUMBER_FOUR;
	}

	public String get_TEL_NUMBER_FIVE(){
		return TEL_NUMBER_FIVE;
	}
	
	public int getSpeak(){
		return speak;
	}
	
	public String getSummon(){
		return SPELL_SUMMON;
	}
	
	public void telSpeak(){	
		RadioButton radiobutton1 = (RadioButton)findViewById(R.id.radioButton1);
		RadioButton radiobutton2 = (RadioButton)findViewById(R.id.radioButton2);
		RadioButton radiobutton3 = (RadioButton)findViewById(R.id.radioButton3);
		RadioButton radiobutton4 = (RadioButton)findViewById(R.id.radioButton4);
		RadioButton radiobutton5 = (RadioButton)findViewById(R.id.radioButton5);
		
		edittext1 = (EditText)findViewById(R.id.editText1);
		edittext2 = (EditText)findViewById(R.id.editText2);		
		edittext3 = (EditText)findViewById(R.id.editText3);
		edittext4 = (EditText)findViewById(R.id.editText4);
		edittext5 = (EditText)findViewById(R.id.editText5);

		edittextsummon = (EditText)findViewById(R.id.editText_summon);
		
		if (radiobutton1.isChecked() == true){
			speak = PATTERN_ONE;
		}
		else if(radiobutton2.isChecked() == true){
			speak = PATTERN_TWO;
		}
		else if (radiobutton3.isChecked() == true){
			speak = PATTERN_THREE;
		}
		else if (radiobutton4.isChecked() == true){
			speak = PATTERN_FOUR;
		}
		else if (radiobutton5.isChecked() == true){
			speak = PATTERN_FIVE;
		}
		if (getSpeak() == PATTERN_ONE){
			
				Intent intent1 = new Intent(
						MainActivity.this,
						SurfaceActivity.class);
				intent1.putExtra("KEYWORD", edittextsummon.getText().toString().trim());
				intent1.putExtra(
						"INPUTDATA",edittext1.getText().toString().trim());
				startActivity(intent1);		
		}
		else if (getSpeak() == PATTERN_TWO){
			
			Intent intent2 = new Intent(
					MainActivity.this,
					SurfaceActivity.class);
			intent2.putExtra("KEYWORD", edittextsummon.getText().toString().trim());
			intent2.putExtra(
					"INPUTDATA",edittext2.getText().toString().trim());
			startActivity(intent2);		
		}
		else if (getSpeak() == PATTERN_THREE){
			
			Intent intent3 = new Intent(
					MainActivity.this,
					SurfaceActivity.class);
			intent3.putExtra("KEYWORD", edittextsummon.getText().toString().trim());
			intent3.putExtra(
					"INPUTDATA",edittext3.getText().toString().trim());
			startActivity(intent3);		
		}
		else if (getSpeak() == PATTERN_FOUR){
			
			Intent intent4 = new Intent(
					MainActivity.this,
					SurfaceActivity.class);
			intent4.putExtra("KEYWORD", edittextsummon.getText().toString().trim());
			intent4.putExtra(
					"INPUTDATA",edittext4.getText().toString().trim());
			startActivity(intent4);		
		}
		else if (getSpeak() == PATTERN_FIVE){
			
			Intent intent5 = new Intent(
					MainActivity.this,
					SurfaceActivity.class);
			intent5.putExtra("KEYWORD", edittextsummon.getText().toString().trim());
			intent5.putExtra(
					"INPUTDATA",edittext5.getText().toString().trim());
			startActivity(intent5);		
		}
	}

	public void writeFile1(String str){
		FileOutputStream out1 = null;
		BufferedWriter writer;
		try{
			out1 = this.openFileOutput(TEL1_DATA, Context.MODE_PRIVATE | Context.MODE_APPEND);
			writer = new BufferedWriter(new OutputStreamWriter(out1));
			writer.write(str);
			writer.flush();
			}catch(FileNotFoundException ex){
				Log.e("FileNotFoundException", ex.getMessage());
			}catch(IOException ex){
				Log.e("IOException", ex.getMessage());
			}finally{
				try{
					if (out1 != null){
						out1.close();
					}
				}catch(IOException ex){
					Log.e("IOException", ex.getMessage());
				}
			}
		}

	public void writeFile2(String str){
		FileOutputStream out2 = null;
		BufferedWriter writer;
		try{
			out2 = this.openFileOutput(TEL2_DATA, Context.MODE_PRIVATE | Context.MODE_APPEND);
			writer = new BufferedWriter(new OutputStreamWriter(out2));
			writer.write(str);
			writer.flush();
			}catch(FileNotFoundException ex){
				Log.e("FileNotFoundException", ex.getMessage());
			}catch(IOException ex){
				Log.e("IOException", ex.getMessage());
			}finally{
				try{
					if (out2 != null){
						out2.close();
					}
				}catch(IOException ex){
					Log.e("IOException", ex.getMessage());
				}
			}
		}
	
	public void writeFile3(String str){
		FileOutputStream out3 = null;
		BufferedWriter writer;
		try{
			out3 = this.openFileOutput(TEL3_DATA, Context.MODE_PRIVATE | Context.MODE_APPEND);
			writer = new BufferedWriter(new OutputStreamWriter(out3));
			writer.write(str);
			writer.flush();
			}catch(FileNotFoundException ex){
				Log.e("FileNotFoundException", ex.getMessage());
			}catch(IOException ex){
				Log.e("IOException", ex.getMessage());
			}finally{
				try{
					if (out3 != null){
						out3.close();
					}
				}catch(IOException ex){
					Log.e("IOException", ex.getMessage());
				}
			}
		}	

	public void writeFile4(String str){
		FileOutputStream out4 = null;
		BufferedWriter writer;
		try{
			out4 = this.openFileOutput(TEL4_DATA, Context.MODE_PRIVATE | Context.MODE_APPEND);
			writer = new BufferedWriter(new OutputStreamWriter(out4));
			writer.write(str);
			writer.flush();
			}catch(FileNotFoundException ex){
				Log.e("FileNotFoundException", ex.getMessage());
			}catch(IOException ex){
				Log.e("IOException", ex.getMessage());
			}finally{
				try{
					if (out4 != null){
						out4.close();
					}
				}catch(IOException ex){
					Log.e("IOException", ex.getMessage());
				}
			}
		}	
	
	public void writeFile5(String str){
		FileOutputStream out5 = null;
		BufferedWriter writer;
		try{
			out5 = this.openFileOutput(TEL5_DATA, Context.MODE_PRIVATE | Context.MODE_APPEND);
			writer = new BufferedWriter(new OutputStreamWriter(out5));
			writer.write(str);
			writer.flush();
			}catch(FileNotFoundException ex){
				Log.e("FileNotFoundException", ex.getMessage());
			}catch(IOException ex){
				Log.e("IOException", ex.getMessage());
			}finally{
				try{
					if (out5 != null){
						out5.close();
					}
				}catch(IOException ex){
					Log.e("IOException", ex.getMessage());
				}
			}
		}	
	
	public void writeFile6(String str){
		FileOutputStream out6 = null;
		BufferedWriter writer;
		try{
			out6 = this.openFileOutput(SPELL_DATA, Context.MODE_PRIVATE | Context.MODE_APPEND);
			writer = new BufferedWriter(new OutputStreamWriter(out6));
			writer.write(str);
			writer.flush();
			}catch(FileNotFoundException ex){
				Log.e("FileNotFoundException", ex.getMessage());
			}catch(IOException ex){
				Log.e("IOException", ex.getMessage());
			}finally{
				try{
					if (out6 != null){
						out6.close();
					}
				}catch(IOException ex){
					Log.e("IOException", ex.getMessage());
				}
			}
		}	
	
	public void writeFiles(){
		edittext1 = (EditText)findViewById(R.id.editText1);
		edittext2 = (EditText)findViewById(R.id.editText2);		
		edittext3 = (EditText)findViewById(R.id.editText3);
		edittext4 = (EditText)findViewById(R.id.editText4);
		edittext5 = (EditText)findViewById(R.id.editText5);

		edittextsummon = (EditText)findViewById(R.id.editText_summon);
		
		writeFile1(edittext1.getText().toString().trim());
		writeFile2(edittext2.getText().toString().trim());
		writeFile3(edittext3.getText().toString().trim());
		writeFile4(edittext4.getText().toString().trim());
		writeFile5(edittext5.getText().toString().trim());
		writeFile6(edittextsummon.getText().toString().trim());
	}
	
	public String readFile1(){
		FileInputStream in1 = null;
		BufferedReader reader;
		String textData1 = "";
		try{
			in1 = this.openFileInput(TEL1_DATA);
			reader = new BufferedReader(new InputStreamReader(in1));
			StringBuffer text1 = new StringBuffer();
			String str1 = reader.readLine();
			if (str1 != null){
				text1.append(str1);
			}
			textData1 = text1.substring(0);
		}catch (FileNotFoundException ex){
			Log.e("FileNotFoundExcepton", ex.getMessage());
		}catch (IOException ex){
			Log.e("IOException", ex.getMessage());
		}finally{
			try{
				if (in1 != null){
					in1.close();
				}
			}catch(IOException ex){
				Log.e("IOException", ex.getMessage());
			}
			return textData1;
		}
	}
	public String readFile2(){
		FileInputStream in2 = null;
		BufferedReader reader;
		String textData2 = "";
		try{
			in2 = this.openFileInput(TEL2_DATA);
			reader = new BufferedReader(new InputStreamReader(in2));
			StringBuffer text2 = new StringBuffer();
			String str2 = reader.readLine();
			if (str2 != null){
				text2.append(str2);
			}
			textData2 = text2.substring(0);
		}catch (FileNotFoundException ex){
			Log.e("FileNotFoundExcepton", ex.getMessage());
		}catch (IOException ex){
			Log.e("IOException", ex.getMessage());
		}finally{
			try{
				if (in2 != null){
					in2.close();
				}
			}catch(IOException ex){
				Log.e("IOException", ex.getMessage());
			}
			return textData2;
		}
	}
	public String readFile3(){
		FileInputStream in3 = null;
		BufferedReader reader;
		String textData3 = "";
		try{
			in3 = this.openFileInput(TEL3_DATA);
			reader = new BufferedReader(new InputStreamReader(in3));
			StringBuffer text3 = new StringBuffer();
			String str3 = reader.readLine();
			if (str3 != null){
				text3.append(str3);
			}
			textData3 = text3.substring(0);
		}catch (FileNotFoundException ex){
			Log.e("FileNotFoundExcepton", ex.getMessage());
		}catch (IOException ex){
			Log.e("IOException", ex.getMessage());
		}finally{
			try{
				if (in3 != null){
					in3.close();
				}
			}catch(IOException ex){
				Log.e("IOException", ex.getMessage());
			}
			return textData3;
		}
	}
	public String readFile4(){
		FileInputStream in4 = null;
		BufferedReader reader;
		String textData4 = "";
		try{
			in4 = this.openFileInput(TEL4_DATA);
			reader = new BufferedReader(new InputStreamReader(in4));
			StringBuffer text4 = new StringBuffer();
			String str4 = reader.readLine();
			if (str4 != null){
				text4.append(str4);
			}
			textData4 = text4.substring(0);
		}catch (FileNotFoundException ex){
			Log.e("FileNotFoundExcepton", ex.getMessage());
		}catch (IOException ex){
			Log.e("IOException", ex.getMessage());
		}finally{
			try{
				if (in4 != null){
					in4.close();
				}
			}catch(IOException ex){
				Log.e("IOException", ex.getMessage());
			}
			return textData4;
		}
	}
	public String readFile5(){
		FileInputStream in5 = null;
		BufferedReader reader;
		String textData5 = "";
		try{
			in5 = this.openFileInput(TEL5_DATA);
			reader = new BufferedReader(new InputStreamReader(in5));
			StringBuffer text5 = new StringBuffer();
			String str5 = reader.readLine();
			if (str5 != null){
				text5.append(str5);
			}
			textData5 = text5.substring(0);
		}catch (FileNotFoundException ex){
			Log.e("FileNotFoundExcepton", ex.getMessage());
		}catch (IOException ex){
			Log.e("IOException", ex.getMessage());
		}finally{
			try{
				if (in5 != null){
					in5.close();
				}
			}catch(IOException ex){
				Log.e("IOException", ex.getMessage());
			}
			return textData5;
		}
	}
	public String readFile6(){
		FileInputStream in6 = null;
		BufferedReader reader;
		String textData6 = "";
		try{
			in6 = this.openFileInput(SPELL_DATA);
			reader = new BufferedReader(new InputStreamReader(in6));
			StringBuffer text6 = new StringBuffer();
			String str6 = reader.readLine();
			if (str6 != null){
				text6.append(str6);
			}
			textData6 = text6.substring(0);
		}catch (FileNotFoundException ex){
			Log.e("FileNotFoundExcepton", ex.getMessage());
		}catch (IOException ex){
			Log.e("IOException", ex.getMessage());
		}finally{
			try{

				if (in6 != null){
					in6.close();
				}
			}catch(IOException ex){
				Log.e("IOException", ex.getMessage());
			}
			return textData6;
		}
	}
}