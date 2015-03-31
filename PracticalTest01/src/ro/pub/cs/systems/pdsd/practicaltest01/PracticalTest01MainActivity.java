package ro.pub.cs.systems.pdsd.practicaltest01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class PracticalTest01MainActivity extends Activity {

	private class MyTextListener implements TextWatcher {

		private View v;
		private CheckBox box;
		public MyTextListener(View v, CheckBox box) {
			this.v = v;
			this.box = box;
		}
		
		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			int id = v.getId();
			switch(id) {
			case R.id.email:
				EditText emailText = (EditText)v;
				isEmailValid(emailText);
				break;
			
			case R.id.phone:
				EditText phoneText = (EditText)v;
				if (phoneText.getText().toString() == null) {
	                phoneText.setError("Invalid Phone Number");
	                box.setChecked(false);
				} else if (phoneText.getText().toString().equals("")) {
	                phoneText.setError("Invalid Phone Number");
	                box.setChecked(false);
				}
				boolean isPhone = PhoneNumberUtils.isGlobalPhoneNumber(phoneText.getText().toString());
				if (isPhone) {
					box.setChecked(true);
				} else {
	                phoneText.setError("Invalid Phone Number");
	                box.setChecked(false);
				}
								
				break;
			default:
					break;
			}
		}
		
		private void isEmailValid(EditText edt) {
	          if (edt.getText().toString() == null) {
	                edt.setError("Invalid Email Address");
	                box.setChecked(false);
	          } else if (is_Email_Valid(edt.getText().toString()) == false) {
	                edt.setError("Invalid Email Address");
	                box.setChecked(false);
	          } else {
	        	  box.setChecked(true);
	        	  
	          }
		}
		
		private boolean is_Email_Valid(String text) {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(text)
                    .matches();
		}
	}
	
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);
        
        
        final EditText emailText = (EditText)findViewById(R.id.email);
        final EditText phoneText = (EditText)findViewById(R.id.phone);
        final CheckBox box1 = (CheckBox)findViewById(R.id.check1);
        final CheckBox box2 = (CheckBox)findViewById(R.id.check2);
        Button button = (Button)findViewById(R.id.button1);
        
        if (savedInstanceState != null) {
        	String text1 = savedInstanceState.getString("text1");
        	if (text1 != null) {
        		emailText.setText(text1);
        	} else {
        		emailText.setText("");
        	}
        	
        	String text2 = savedInstanceState.getString("text2");
        	if (text2 != null) {
        		phoneText.setText(text2);
        	} else {
        		phoneText.setText("");
        	}
        	
        	Boolean isBox1 = savedInstanceState.getBoolean("check1");
        	Boolean isBox2 = savedInstanceState.getBoolean("check2");
        	
        	if (isBox1 != null) {
        		box1.setChecked(isBox1);
        	} else {
        		box1.setChecked(false);
        	}
        	
        	if (isBox2 != null) {
        		box2.setChecked(isBox2);
        	} else {
        		box2.setChecked(false);
        	}
        	
        } else {
        	emailText.setText("");
        	phoneText.setText("");
        	box1.setChecked(false);
        	box2.setChecked(false);
        }
        
        MyTextListener textListener = new MyTextListener(emailText, box1);
        MyTextListener phoneListener = new MyTextListener(phoneText, box2);
        
        
        emailText.addTextChangedListener(textListener);
        phoneText.addTextChangedListener(phoneListener);
        
        button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent activate = new Intent("ro.pub.cs.systems.pdsd.practicaltest01.intent.action.PracticalTest01SecondaryActivity");
				activate.putExtra("emailValid", box1.isChecked());
				activate.putExtra("phoneValid", box2.isChecked());
				startActivityForResult(activate, 1);
				
			}
		});
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	switch(requestCode) {
    	case 1:
    		Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
    		
    		break;
    	default:
    		break;
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.practical_test01_main, menu);
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
    
    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        EditText emailText = (EditText)findViewById(R.id.email);
        EditText phoneText = (EditText)findViewById(R.id.phone);
        CheckBox box1 = (CheckBox)findViewById(R.id.check1);
        CheckBox box2 = (CheckBox)findViewById(R.id.check2);
        
        bundle.putString("text1", emailText.getText().toString());
        bundle.putString("text2", phoneText.getText().toString());
        bundle.putBoolean("check1", box1.isChecked());
        bundle.putBoolean("check2", box2.isChecked());
        
        
        System.out.println(emailText.getText().toString());
        System.out.println(phoneText.getText().toString());
        System.out.println(box1.isChecked());
        System.out.println(box2.isChecked());
        
    }
}
