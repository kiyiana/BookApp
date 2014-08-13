package com.nyul.androidgroupapp3.bookkeeper;

import static java.lang.Thread.sleep;

import java.util.HashMap;

import android.content.Intent;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import com.nyul.androidgroupapp3.dialogue.DialogueMaker;
import com.nyul.androidgroupapp3.dialogue.SelectionListener;


public class MainActivity extends ParentActivity {
	DialogueMaker dialogueMaker = new DialogueMaker(this);
	
	
	
	
	protected void onClickButton1(View v) {
		
		final String[] items = { "Item1", "Item2", "Item3", "Item4", "Item5", "Item9" };
		final SelectionListener listener = dialogueMaker.options("Title", "Message", items);
		
		Thread t = new Thread( new MultiOptionsHandler(listener) );		
		t.start();
	}
	
	protected void onClickButton2(View v) {
		dialogueMaker.message("The Title", "The Message");
	}
	
	@Override
	protected void triggerBookActivity(View v) {
		startActivity(new Intent(getApplicationContext(),BookActivity.class));
		return;}
		
		@Override
		protected void triggerBookActivity2(View v) {
			startActivity(new Intent(getApplicationContext(),BookActivity2.class));
			return;
	}
	
	class SelectedOptionsHandler implements Runnable{
		private final SelectionListener listener;
		private final TextView tV = (TextView) findViewById(R.id.textView1);
		
		public SelectedOptionsHandler(SelectionListener listener) {
			this.listener = listener;
		}

		@Override
		public void run() {
			for(final String s : listener.getCheckedOptionsList()){			
				tV.setText(tV.getText() + "\n" + s);
			}
		}
		
	}
	
	class MultiOptionsHandler implements Runnable{
		private final SelectionListener listener;
		private final TextView tV = (TextView) findViewById(R.id.textView1);				
		
		public MultiOptionsHandler(SelectionListener listener) {
			this.listener = listener; 
		}

		@Override
		public void run() {
			Looper.prepare();
			while( ! listener.wasOkPressed() ){
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					log(e.toString());
				}
			}
			
			tV.post(new SelectedOptionsHandler(listener));			
		}
		
	}

	@Override
	protected void triggerYourProfile(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(getApplicationContext(),YourProfile.class));
		return;
	
	}

}