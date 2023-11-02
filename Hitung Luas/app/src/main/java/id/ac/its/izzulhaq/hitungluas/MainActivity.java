package id.ac.its.izzulhaq.hitungluas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EditText edtLength;
    private EditText edtWidth;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Hitung Luas Persegi Panjang");

        edtLength = findViewById(R.id.edt_length);
        edtWidth = findViewById(R.id.edt_width);
        Button btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(view -> {
            String lenght = edtLength.getText().toString().trim();
            String width = edtWidth.getText().toString().trim();

            DecimalFormat df = new DecimalFormat("#.##");

            double l = Double.parseDouble(lenght);
            double w = Double.parseDouble(width);

            double area = l * w;

            String resultArea = "Luas = " + df.format(area);

            tvResult.setText(resultArea);
        });
    }
}