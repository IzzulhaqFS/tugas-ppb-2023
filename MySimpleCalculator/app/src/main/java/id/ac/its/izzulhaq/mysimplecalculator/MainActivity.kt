package id.ac.its.izzulhaq.mysimplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var edtNum1: EditText
    private lateinit var edtNum2: EditText
    private lateinit var btnAddition: Button
    private lateinit var btnSubtraction: Button
    private lateinit var btnMultiplication: Button
    private lateinit var btnDivision: Button
    private lateinit var btnClear: Button
    private lateinit var tvResult: TextView

    private val operation = View.OnClickListener {
        val num1 = edtNum1.text.toString().toDouble()
        val num2 = edtNum2.text.toString().toDouble()

        val result = when (it.id) {
            R.id.btn_addition -> {
                num1 + num2
            }
            R.id.btn_subtraction -> {
                num1 - num2
            }
            R.id.btn_multiplication -> {
                num1 * num2
            }
            R.id.btn_division -> {
                num1 / num2
            }
            else -> {
                0.0
            }
        }

        tvResult.text = result.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtNum1 = findViewById(R.id.edt_number_1)
        edtNum2 = findViewById(R.id.edt_number_2)
        btnAddition = findViewById(R.id.btn_addition)
        btnSubtraction = findViewById(R.id.btn_subtraction)
        btnMultiplication = findViewById(R.id.btn_multiplication)
        btnDivision = findViewById(R.id.btn_division)
        btnClear = findViewById(R.id.btn_clear)
        tvResult = findViewById(R.id.tv_result)

        btnAddition.setOnClickListener(operation)
        btnSubtraction.setOnClickListener(operation)
        btnMultiplication.setOnClickListener(operation)
        btnDivision.setOnClickListener(operation)

        btnClear.setOnClickListener {
            clear()
        }
    }

    private fun clear() {
        edtNum1.text.clear()
        edtNum2.text.clear()
        tvResult.text = "0.0"
    }
}