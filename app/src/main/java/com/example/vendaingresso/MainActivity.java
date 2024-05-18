package com.example.vendaingresso;// MainActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vendaingresso.R;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private EditText editCodigo;
    private EditText editValor;
    private EditText editFuncao;
    private Button btnComprar;
    private Ingresso ingresso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        editCodigo = findViewById(R.id.editCodigo);
        editValor = findViewById(R.id.editValor);
        editFuncao = findViewById(R.id.editFuncao);
        btnComprar = findViewById(R.id.btnComprar);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioIngressoVIP) {
                    editFuncao.setVisibility(View.VISIBLE);
                } else {
                    editFuncao.setVisibility(View.GONE);
                }
            }
        });

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = editCodigo.getText().toString();
                float valor = Float.parseFloat(editValor.getText().toString());
                int selectedId = radioGroup.getCheckedRadioButtonId();

                if (selectedId == R.id.radioIngresso) {
                    ingresso = new Ingresso(codigo, valor);
                } else if (selectedId == R.id.radioIngressoVIP) {
                    String funcao = editFuncao.getText().toString();
                    ingresso = new IngressoVIP(codigo, valor, funcao);
                }

                Intent intent = new Intent(MainActivity.this, PurchaseDetailsActivity.class);
                intent.putExtra("ingresso", ingresso.toString());
                startActivity(intent);
            }
        });
    }
}
