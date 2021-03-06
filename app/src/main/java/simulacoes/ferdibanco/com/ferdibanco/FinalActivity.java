package simulacoes.ferdibanco.com.ferdibanco;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {
    protected Intent thisIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        thisIntent = getIntent();
        calcular();
    }

    public void irParaPrincipal(View view) {
        Intent intent = new Intent(FinalActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        this.finish();
        startActivity(intent);
    }

    public void calcular() {

        TextView txtAprov = (TextView) findViewById(R.id.txtAprov);
        TextView txtValorParc = (TextView) findViewById(R.id.txtValorParc);
        TextView txtValorTot = (TextView) findViewById(R.id.txtValorTot);

        Double devedor, parcela, total, renda;
        Double qtdParcela =  Double.parseDouble(thisIntent.getStringExtra("parcelas"));
        Double valorTotal = thisIntent.getDoubleExtra("valorTotal", 0.0);
        Double valorEntrada = Double.parseDouble(thisIntent.getStringExtra("entrada"));
        Double rendaLiq = Double.parseDouble(thisIntent.getStringExtra("rendaLiq"));
        Double juro = Double.parseDouble(thisIntent.getStringExtra("juro"));
        renda = rendaLiq*0.3;
        devedor = valorTotal - valorEntrada;
        parcela = devedor * juro * qtdParcela;
        total = parcela * qtdParcela;
        if (parcela >= renda) {
            txtAprov.setText("Não aprovado");
        } else {
            txtAprov.setText("Aprovado");
        }
        txtValorParc.setText("R$"+String.format("%.2f", parcela));
        txtValorTot.setText("R$"+String.format("%.2f", total));
    }
}
