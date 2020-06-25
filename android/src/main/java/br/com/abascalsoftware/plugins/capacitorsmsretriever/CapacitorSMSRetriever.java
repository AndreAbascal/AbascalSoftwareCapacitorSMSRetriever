package br.com.abascalsoftware.plugins.capacitorsmsretriever;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.lang.Math;

@NativePlugin(
	permissions = {

	},
	requestCodes = {
		CapacitorSMSRetriever.INTENT_START,
        CapacitorSMSRetriever.INTENT_STOP
	}
)
public class CapacitorSMSRetriever extends Plugin implements SMSReceiver.OTPReceiveListener {

	public static final int INTENT_START = 30629;
	public static final int INTENT_STOP = 2;
	private SMSReceiver smsReceiver;
	private String TAG = "CapacitorSMSRetriever";

    @PluginMethod()
    public void start(PluginCall call) {
        Log.d(TAG,"SMS RETRIEVER STARTED!");
		saveCall(call);
        AppSignatureHashHelper appSignatureHashHelper = new AppSignatureHashHelper(getContext());
		Log.i(TAG, "HashKey: " + appSignatureHashHelper.getAppSignatures().get(0));
		startSMSListener();
		//call.success();
	}

    @PluginMethod()
    public void stop(PluginCall call){
        Log.d(TAG,"SMS RETRIEVER STOPPING...");
        this.stopSMSListener();
        call.resolve();
    }
	
	private void startSMSListener() {
        try {
            smsReceiver = new SMSReceiver();
            smsReceiver.setOTPListener(this);

            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
            getContext().registerReceiver(smsReceiver, intentFilter);

            SmsRetrieverClient client = SmsRetriever.getClient(getContext());

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
					Log.d(TAG,"startSMSListener... Failure: "+e.getMessage());
                    // Fail to start API
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopSMSListener(){
        if(smsReceiver != null){
            getContext().unregisterReceiver(smsReceiver);
        }
    }


    @Override
    public void onOTPReceived(String otp) {
		Log.d(TAG,"OTP Received: "+otp);
        if (smsReceiver != null) {
            getContext().unregisterReceiver(smsReceiver);
            smsReceiver = null;
		}
        PluginCall savedCall = getSavedCall();
		if (savedCall == null) {
			return;
		}
		JSObject obj = new JSObject();
		obj.put("codigo", otp);
		savedCall.resolve(obj);
        savedCall.release(getBridge());
    }

    @Override
    public void onOTPTimeOut() {
		Log.d(TAG,"OTP Time out");
		PluginCall savedCall = getSavedCall();
		if (savedCall == null) {
			return;
		}
		savedCall.reject("TIMEOUT");
        savedCall.release(getBridge());
    }

    @Override
    public void onOTPReceivedError(String error) {
		Log.d(TAG,"OTP ERROR: "+error);
    }

	/*
	@Override
    public void onDestroy() {
		if (smsReceiver != null) {
            getContext().unregisterReceiver(smsReceiver);
        }
        super.onDestroy();
	}
	*/
}
