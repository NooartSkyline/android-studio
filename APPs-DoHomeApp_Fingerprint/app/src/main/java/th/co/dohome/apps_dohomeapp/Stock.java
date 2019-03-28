package th.co.dohome.apps_dohomeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Stock extends AppCompatActivity {

    private RadioGroup rg_displaygenera_stock;
    private RadioButton rb_displaygenera_stock;
    private ImageView iv_stock_stock_barcode;
    private TextView tv_layoutsearchproduct_article_barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_stock);

        rg_displaygenera_stock = findViewById(R.id.rg_stock_stockl);
        iv_stock_stock_barcode = findViewById(R.id.iv_stock_stock_barcode);
        tv_layoutsearchproduct_article_barcode = findViewById(R.id.tv_layoutsearchproduct_article_barcode);

//---------------------------------------QR CODE-------------------------------------------
        final Activity activity = this;
        iv_stock_stock_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
//                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);  //scan เฉพาะ QR CODE
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {
//                Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
                tv_layoutsearchproduct_article_barcode.setText(result.getContents().toString());
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void rb_checket_stock(View view) {

        int rb = rg_displaygenera_stock.getCheckedRadioButtonId();
        rb_displaygenera_stock = findViewById(rb);
        Toast.makeText(this, rb_displaygenera_stock.getText(), Toast.LENGTH_SHORT).show();
    }
}
//---------------------------------------QR CODE-------------------------------------------