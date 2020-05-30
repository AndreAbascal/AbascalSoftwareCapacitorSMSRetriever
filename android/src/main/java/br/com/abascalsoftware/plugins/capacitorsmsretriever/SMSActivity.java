package br.com.abascalsoftware.plugins.capacitorsmsretriever;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import android.content.BroadcastReceiver;
//import android.support.v4.content.LocalBroadcastManager;
//import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class SMSActivity extends Activity implements SMSReceiver.OTPReceiveListener {

    public static final String TAG = SMSActivity.class.getSimpleName();

    private SMSReceiver smsReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        AppSignatureHashHelper appSignatureHashHelper = new AppSignatureHashHelper(this);

        // This code requires one time to get Hash keys do comment and share key
        Log.i(TAG, "HashKey: " + appSignatureHashHelper.getAppSignatures().get(0));

        startSMSListener();
    }


    /**
     * Starts SmsRetriever, which waits for ONE matching SMS message until timeout
     * (5 minutes). The matching SMS message will be sent via a Broadcast Intent with
     * action SmsRetriever#SMS_RETRIEVED_ACTION.
     */
    private void startSMSListener() {
        try {
            smsReceiver = new SMSReceiver();
            smsReceiver.setOTPListener(this);

            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
            this.registerReceiver(smsReceiver, intentFilter);

            SmsRetrieverClient client = SmsRetriever.getClient(this);

            Task<Void> task = client.startSmsRetriever();
            task.addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    // API successfully started
                }
            });

            task.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Fail to start API
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onOTPReceived(String otp) {
		// showToast("OTP Received: " + otp);
		Log.d(TAG,"OTP Received: "+otp);

        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver);
            smsReceiver = null;
		}
		// sendActivityResult(AppCompatActivity.RESULT_OK, otp);
		sendActivityResult(Activity.RESULT_OK, otp);
    }

    @Override
    public void onOTPTimeOut() {
		// showToast("OTP Time out");
		Log.d(TAG,"OTP Time out");
		// sendActivityResult(AppCompatActivity.RESULT_CANCELED, "TIMEOUT");
		sendActivityResult(Activity.RESULT_CANCELED, "TIMEOUT");
    }

    @Override
    public void onOTPReceivedError(String error) {
		// showToast(error);
		Log.d(TAG,"OTP ERROR: "+error);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver);
        }
	}
	
	public void sendActivityResult(int resultCode, String response) {
		Log.d(TAG,"SMSActivity sendActivityResult... resultCode: "+resultCode);
		Log.d(TAG,"SMSActivity sendActivityResult... response: "+response);
        Intent intent = new Intent();
        intent.putExtra("data", response);
		setResult(resultCode, intent);
		unregisterReceiver(smsReceiver);
        finish();
    }


	/*
	private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	*/
}