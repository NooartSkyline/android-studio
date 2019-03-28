package th.co.dohome.apps_dohomeapp.MyClass;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import th.co.dohome.apps_dohomeapp.R;


public  class Classs {
    SharedPreferences g_SharedPreferences;
    SharedPreferences.Editor editor;
    static Context g_Context;
    public Classs(Context context) {
        this.g_Context = context;
        g_SharedPreferences = context.getSharedPreferences(String.valueOf(R.string.SharePreService), Context.MODE_PRIVATE);
        editor =g_SharedPreferences.edit();
    }


    public  void myAlert(String title, String message , Bitmap...bitmaps){
       AlertDialog.Builder dialog =  new AlertDialog.Builder(g_Context);
        dialog.setMessage(message);
        dialog.setTitle(title);
        if(bitmaps!=null) {
            Drawable d = new BitmapDrawable(g_Context.getResources(), String.valueOf(bitmaps));
            dialog.setIcon(d);
        }else{
            dialog.setIcon(R.drawable.ic_add_alert_black_24dp);
        }
        dialog.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public static String URLService() {
        return "http://192.168.0.12/ServiceStockCounting/WebService.asmx";
    }
}
