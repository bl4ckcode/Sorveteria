package blackcode.carlosalves.sorveteria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edtQtdLeite;
    private EditText edtQtdLeiteBolaSorvete;
    private EditText edtQtdLeitePicoleCremoso;
    private EditText edtQtdHorasProducao;
    private EditText edtQtdHorasBolaSorvete;
    private EditText edtQtdHorasBolaPicoleCremoso;
    private EditText edtQtdHorasBolaPicoleFruta;
    private EditText edtQtdMcUnidadePicole;
    private EditText edtQtdFzsBolaSorvete;
    private EditText edtQtdDemandaSorvete;
    private EditText edtQtdDemandaPicoleFruta;
    private EditText edtQtdDemandaPicoleCremoso;
    private TextView txtViewCalcularSolucao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    private void findViews() {
        edtQtdLeite = findViewById(R.id.edt_qtd_leite);
        edtQtdLeiteBolaSorvete = findViewById(R.id.edt_qtd_leite_bola_sorvete);
        edtQtdLeitePicoleCremoso = findViewById(R.id.edt_qtd_leite_picole_cremoso);
        edtQtdHorasProducao = findViewById(R.id.edt_qtd_horas_producao);
        edtQtdHorasBolaSorvete = findViewById(R.id.edt_qtd_horas_bola_sorvete);
        edtQtdHorasBolaPicoleCremoso = findViewById(R.id.edt_qtd_horas_bola_picole_cremoso);
        edtQtdHorasBolaPicoleFruta = findViewById(R.id.edt_qtd_horas_bola_picole_fruta);
        edtQtdMcUnidadePicole = findViewById(R.id.edt_qtd_mc_unidade_picole);
        edtQtdFzsBolaSorvete = findViewById(R.id.edt_qtd_fzs_bola_sorvete);
        edtQtdDemandaSorvete = findViewById(R.id.edt_qtd_demanda_sorvete);
        edtQtdDemandaPicoleFruta = findViewById(R.id.edt_qtd_demanda_picole_fruta);
        edtQtdDemandaPicoleCremoso = findViewById(R.id.edt_qtd_demanda_picole_cremoso);
        txtViewCalcularSolucao = findViewById(R.id.txtView_calcular_solucao);
    }
}
