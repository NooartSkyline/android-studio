package th.co.dohome.apps_dohomeapp.Fingerprint;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import th.co.dohome.apps_dohomeapp.Main_manu;
import th.co.dohome.apps_dohomeapp.R;


/*  Implement Fingerprint Authentication Callback to get access to Fingerprint methods  */
@TargetApi(Build.VERSION_CODES.M)
public class FingerPrintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;
    private TextView errorText;
    private Vibrator vibrator;
    private ImageView fingerprint_logo;


    public FingerPrintHandler(Context mContext, TextView errorText, ImageView fingerprint_logo, Vibrator vibrator) {

        context = mContext;
        this.errorText=errorText;
        this.fingerprint_logo=fingerprint_logo;
        this.vibrator=vibrator;
    }


    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    /*  Method will call on Fingerprint Auth Error  */
    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        this.update("Fingerprint Authentication error\n" + errString);
        fingerprint_logo.setImageResource(R.drawable.ic_fingerprint_red_24dp);
        vibrator.vibrate(200);//การสั่น
        YoYo.with(Techniques.Shake)
                .duration(700)
                .repeat(0) //จำนวนครั้งที่สั่น
                .playOn(fingerprint_logo);
    }

    /*  Method will call on Fingerprint Auth have some help  */
    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        this.update("Fingerprint Authentication help\n" + helpString);
        fingerprint_logo.setImageResource(R.drawable.ic_fingerprint_red_24dp);
        vibrator.vibrate(200);
        YoYo.with(Techniques.Shake)
                .duration(700)
                .repeat(0)
                .playOn(fingerprint_logo);
    }

    /*  Method will call on Fingerprint Auth Failed  */
    @Override
    public void onAuthenticationFailed() {
        this.update("Fingerprint Authentication failed. Please try again.");
        fingerprint_logo.setImageResource(R.drawable.ic_fingerprint_red_24dp);
        vibrator.vibrate(200);
        YoYo.with(Techniques.Shake)
                .duration(700)
                .repeat(0)
                .playOn(fingerprint_logo);
    }

    /*  Method will call on Fingerprint Auth Succeeded  */
    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        Log.d("Authentication", "Fingerprint Authentication successful.");
        fingerprint_logo.setImageResource(R.drawable.ic_fingerprint_green_24dp);

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {

                    sleep(1000);

                    onAuthSuccess();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();
    }

    /*  Trigger this method on FingerPrint get Success  */
    private void onAuthSuccess() {
        context.startActivity(new Intent(context, Main_manu.class));
        ((AppCompatActivity) context).finish();
    }


    /*  Method to update Error text message on Auth failed  */
    public void update(String e) {
        errorText.setText(e);
    }
}