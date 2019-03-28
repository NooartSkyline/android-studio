package th.co.dohome.apps_dohomeapp;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.gson.Gson;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import th.co.dohome.apps_dohomeapp.Fingerprint.FingerPrintHandler;

public class LoginActivity extends AppCompatActivity {

//----------------------------Login-------------------------------
//
//    private ArrayList<ItemCustomSpinnerBranch> mList_branch;
//    private ArrayList<ItemCustomSpinnerBranchSite> mList_branch_site;
//    private ArrayList<ItemCustomSpinnerSloc> mList_sloc;
//
//    private Button btn_login_login;
//    private TextView tv_login_user,et_login_password;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout_activity_login);
//
//        tv_login_user = findViewById(R.id.tv_login_user);
//        et_login_password = findViewById(R.id.et_login_password);
//
//        btn_login_login = findViewById(R.id.btn_login_login);
//
//        btn_login_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String user,pass;
//
//                tv_login_user.setText("art");
//                et_login_password.setText("1234");
//
//                user = tv_login_user.getText().toString();
//                pass = et_login_password.getText().toString();
//
//                try {
//                    if (user.equals("art") || pass.equals("1234")) {
//                        Intent intent = new Intent(LoginActivity.this, Main_manu.class);
//                        intent.putExtra("key", "value");
//                        startActivity(intent);
//                        Toast.makeText(LoginActivity.this, "Login สำเร็จ", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(LoginActivity.this, "Login ไม่สำเร็จ", Toast.LENGTH_SHORT).show();
//                    }
//                }catch (Exception ex){
//                    Toast.makeText(LoginActivity.this, String.valueOf(ex), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }
//}


//----------------------------Fingerprint-------------------------------


    private KeyStore keyStore;

    // Variable used for storing the key in the Android Keystore container
//    private static final String KEY_NAME = "AndroHub";
    private static final String KEY_NAME = "Skyline";

    private Cipher cipher;
    private ImageView fingerprint_logo;
    // การสั่น
    private Vibrator vibrator;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fingerprint);
        // Initializing both Android Keyguard Manager and Fingerprint Manager
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        //Find id of Error text
        TextView errorText = findViewById(R.id.error_message);
        fingerprint_logo = findViewById(R.id.fingerprint_logo);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);



        // Check whether the device has a Fingerprint sensor.
        if (!fingerprintManager.isHardwareDetected()) {
            /**
             * An error message will be displayed if the device does not contain the fingerprint hardware.
             * However if you plan to implement a default authentication method,
             * you can redirect the user to a default authentication activity from here or can skip this method.
             * Example:
             * Intent intent = new Intent(this, YourActivity.class);
             * startActivity(intent);
             * finish();
             */
            errorText.setText(getResources().getString(R.string.fingerprint_not_exist));
        } else {
            // Checks whether fingerprint permission is set on manifest
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                //If permission is not set show error message
                errorText.setText(getResources().getString(R.string.fingerprint_not_enabled));
            } else {
                // Check whether at least one fingerprint is registered on your device
                if (!fingerprintManager.hasEnrolledFingerprints()) {
                    //If no fingerprint is registered show error message
                    errorText.setText(getResources().getString(R.string.fingerprint_not_registered));
                } else {
                    // Checks whether lock screen security is enabled or not
                    if (!keyguardManager.isKeyguardSecure()) {
                        //Show error message when screen security is disabled
                        errorText.setText(getResources().getString(R.string.lock_screen_setting_disabled));
                    } else {

                        //else generate keystore key
                        generateKey();

                        //Now initiate Cipher, if cipher is initiated successfully then proceed
                        if (cipherInit()) {
                            FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                            FingerPrintHandler helper = new FingerPrintHandler(this, errorText,fingerprint_logo,vibrator);//Set Fingerprint Handler class
                            helper.startAuth(fingerprintManager, cryptoObject);//now start authentication process
                        }
                    }
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected void generateKey() {
        try {
            // Get the reference to the key store
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }

        KeyGenerator keyGenerator;
        try {
            // Key generator to generate the key
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException("Failed to get KeyGenerator instance", e);
        }

        try {
            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException |
                InvalidAlgorithmParameterException
                | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }

        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }

    }
}





