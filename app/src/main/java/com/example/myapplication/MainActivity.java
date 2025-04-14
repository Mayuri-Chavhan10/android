package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    Button bt_pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure activity_main.xml exists

        // Initialize Razorpay SDK
        Checkout.preload(getApplicationContext());

        bt_pay = findViewById(R.id.bt_pay);

        String samount = "100"; // ₹100
        int amount = Math.round(Float.parseFloat(samount) * 100); // in paise

        bt_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checkout checkout = new Checkout();

                checkout.setKeyID("rzp_test_UhpnPJHz6IuoBA"); // Your test key

                // Optional: Set logo
                // checkout.setImage(R.drawable.rzp_logo);

                JSONObject object = new JSONObject();
                try {
                    object.put("name", "Chorus Razorpay");
                    object.put("description", "Test Payment");
                    object.put("theme.color", "#0093DD");
                    object.put("currency", "INR");
                    object.put("amount", amount); // Amount in paise
                    object.put("prefill.contact", "9404355299");
                    object.put("prefill.email", "chorusraz@rzp.com");

                    checkout.open(MainActivity.this, object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Payment Successful");
        builder.setMessage("Payment ID: " + s);
        builder.show();
    }

    @Override
    public void onPaymentError(int code, String response) {
        Toast.makeText(this, "Payment Failed: " + response, Toast.LENGTH_SHORT).show();
    }
}
