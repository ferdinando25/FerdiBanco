package simulacoes.ferdibanco.com.ferdibanco;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class CarActivity
        extends AppCompatActivity {

    protected AlertDialog.Builder mensagem;
    private EditText edtValorEntr;
    private EditText edtQtdParcela;
    private EditText edtValorVeic;
    private EditText edtRendaLiqMen;
    private RadioButton rbNovoCar;
    private Double valorTotalFinal;
    private Intent thisIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        edtValorEntr = (EditText) findViewById(R.id.edtValorEntrVeic);
        edtQtdParcela = (EditText) findViewById(R.id.edtQtdParcelaVeic);
        edtValorVeic = (EditText) findViewById(R.id.edtValorVeic);
        edtRendaLiqMen = (EditText) findViewById(R.id.edtRendaLiqMenVeic);
        rbNovoCar = (RadioButton) findViewById(R.id.rbNovoCar);
        thisIntent = new Intent(CarActivity.this, FinalActivity.class);
        findViewById(R.id.btnCalcular).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                validateData();

            }
        });
    }

    private void validateData() {
        boolean dadosConfirmados;
        if (edtValorVeic.getText().toString().isEmpty()) {
            edtValorVeic.setError("Campo vazio!");
            dadosConfirmados = false;
        } else {
            dadosConfirmados = true;
        }

        if (edtValorEntr.getText().toString().isEmpty()) {
            edtValorEntr.setError("Campo vazio!");
            dadosConfirmados = false;
        } else {
            dadosConfirmados = true;
        }
        if (Double.parseDouble(edtQtdParcela.getText().toString()) <= 0) {
            edtQtdParcela.setError("Valor inválido de parcelas!");
            dadosConfirmados = false;
        } else {
            dadosConfirmados = true;
        }

        if (edtQtdParcela.getText().toString().isEmpty()) {
            edtQtdParcela.setError("Campo vazio!");
            dadosConfirmados = false;
        } else {
            dadosConfirmados = true;
        }

        if (edtRendaLiqMen.getText().toString().isEmpty()) {
            edtRendaLiqMen.setError("Campo vazio!");
            dadosConfirmados = false;
        } else {
            dadosConfirmados = true;
        }

        if (edtValorEntr.getText().toString().equals(edtValorVeic.getText().toString())) {
            edtValorEntr.setError("Entrada não pode ser igual ao valor do veículo!");
            dadosConfirmados = false;
        } else {
            dadosConfirmados = true;
        }


        if ((!edtValorEntr.getText().toString().isEmpty() && !edtValorVeic.getText().toString().isEmpty()) &&
                (Double.parseDouble(edtValorEntr.getText().toString())
                        < 0.05 * Double.parseDouble(edtValorVeic.getText().toString()))) {
            edtValorEntr.setError("Entrada não pode ser menor que 5% do valor do veículo!");
            dadosConfirmados = false;
        }

        if (dadosConfirmados) {
            if (Double.parseDouble(edtRendaLiqMen.getText().toString()) <= 3500) {
                thisIntent.putExtra("juro", "0.06");
            } else if (Double.parseDouble(edtRendaLiqMen.getText().toString()) <= 5000) {
                thisIntent.putExtra("juro", "0.05");
            } else {
                thisIntent.putExtra("juro", "0.04");
            }
            valorTotalFinal = Double.parseDouble(edtValorVeic.getText().toString());
            if (rbNovoCar.isChecked()) {
                valorTotalFinal = valorTotalFinal * 1.05;
                thisIntent.putExtra("valorTotal", valorTotalFinal);
            } else {
                thisIntent.putExtra("valorTotal", valorTotalFinal);
            }
            thisIntent.putExtra("parcelas", edtQtdParcela.getText().toString());
            thisIntent.putExtra("entrada", edtValorEntr.getText().toString());
            thisIntent.putExtra("rendaLiq", edtRendaLiqMen.getText().toString());
            this.finish();
            startActivity(thisIntent);
        }

    }
}