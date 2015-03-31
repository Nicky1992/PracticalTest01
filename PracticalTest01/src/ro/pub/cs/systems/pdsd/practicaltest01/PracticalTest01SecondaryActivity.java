package ro.pub.cs.systems.pdsd.practicaltest01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01SecondaryActivity extends Activity {

	private class MyButtonListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			
			
			switch(v.getId()) {
			case R.id.ok:
				setResult(Activity.RESULT_OK, new Intent());
				finish();
				break;
			case R.id.cancel:
				setResult(Activity.RESULT_CANCELED, new Intent());
				finish();
				break;
			default:
				break;
			}
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_secondary);
		
		MyButtonListener listener = new MyButtonListener();
		TextView phoneValidation = (TextView)findViewById(R.id.phoneValid);
		TextView emailValidation = (TextView)findViewById(R.id.emailValid);
		
		Button ok = (Button)findViewById(R.id.ok);
		Button cancel = (Button)findViewById(R.id.cancel);
		
		if (ok != null) {
			ok.setOnClickListener(listener);
		}
		
		if (cancel != null) {
			cancel.setOnClickListener(listener);
		}
		
		Intent main = getIntent();
		if (main != null) {
			boolean email = main.getBooleanExtra("emailValid", false);
			boolean phone = main.getBooleanExtra("phoneValid", false);
			
			emailValidation.setText(String.valueOf(email));
			phoneValidation.setText(String.valueOf(phone));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_secondary, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
