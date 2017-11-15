package blackcode.carlosalves.sorveteria;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private EditText edtQtdLucroSorvete;
    private EditText edtQtdLucroPicoleCremoso;
    private EditText edtQtdLucroPicoleFruta;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions();
        findViews();
    }

    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 0);
        }
    }

    private void findViews() {
        edtQtdLucroSorvete = findViewById(R.id.edt_qtd_lucro_sorvete);
        edtQtdLucroPicoleCremoso = findViewById(R.id.edt_qtd_lucro_picole_cremoso);
        edtQtdLucroPicoleFruta = findViewById(R.id.edt_qtd_lucro_picole_fruta);
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
        TextView txtViewCalcularSolucao = findViewById(R.id.txtView_calcular_solucao);
        txtViewCalcularSolucao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    String url = "https://pls69.herokuapp.com/fuckCristiane";

                    final JSONObject json = new JSONObject();
                    json.put("maxmin", "MAX");

                    JSONArray jsonArrayZ = new JSONArray();
                    jsonArrayZ.put(Double.parseDouble(edtQtdLucroSorvete.getText().toString()));
                    jsonArrayZ.put(Double.parseDouble(edtQtdLucroPicoleCremoso.getText().toString()));
                    jsonArrayZ.put(Double.parseDouble(edtQtdLucroPicoleFruta.getText().toString()));

                    json.put("Z", jsonArrayZ);

                    json.put("rests", 0);

                    int rests = 0;

                    JSONArray jsonArrayR0 = new JSONArray();
                    jsonArrayR0.put(Double.parseDouble(edtQtdLeiteBolaSorvete.getText().toString()));
                    jsonArrayR0.put(Double.parseDouble(edtQtdLeitePicoleCremoso.getText().toString()));
                    jsonArrayR0.put(0.0);
                    jsonArrayR0.put("<=");
                    jsonArrayR0.put(Double.parseDouble(edtQtdLeite.getText().toString()) * 1000);

                    json.put("R" + rests, jsonArrayR0);
                    rests++;

                    JSONArray jsonArrayR1 = new JSONArray();
                    jsonArrayR1.put(Double.parseDouble(edtQtdHorasBolaSorvete.getText().toString()));
                    jsonArrayR1.put(0.0);
                    jsonArrayR1.put(0.0);
                    jsonArrayR1.put("<=");
                    jsonArrayR1.put(Double.parseDouble(edtQtdHorasProducao.getText().toString()) * 60);

                    json.put("R" + rests, jsonArrayR1);
                    rests++;

                    JSONArray jsonArrayR2 = new JSONArray();
                    jsonArrayR2.put(0.0);
                    jsonArrayR2.put(Double.parseDouble(edtQtdHorasBolaPicoleCremoso.getText().toString()));
                    jsonArrayR2.put(Double.parseDouble(edtQtdHorasBolaPicoleFruta.getText().toString()));
                    jsonArrayR2.put("<=");
                    jsonArrayR2.put(Double.parseDouble(edtQtdHorasProducao.getText().toString()) * 60);

                    json.put("R" + rests, jsonArrayR2);
                    rests++;

                    JSONArray jsonArrayR3 = new JSONArray();
                    jsonArrayR3.put(0.0);
                    jsonArrayR3.put(1.0);
                    jsonArrayR3.put(1.0);
                    jsonArrayR3.put("<=");
                    jsonArrayR3.put(Double.parseDouble(edtQtdMcUnidadePicole.getText().toString()));

                    json.put("R" + rests, jsonArrayR3);
                    rests++;

                    JSONArray jsonArrayR4 = new JSONArray();
                    jsonArrayR4.put(1.0);
                    jsonArrayR4.put(0.0);
                    jsonArrayR4.put(0.0);
                    jsonArrayR4.put("<=");
                    jsonArrayR4.put(Double.parseDouble(edtQtdFzsBolaSorvete.getText().toString()));

                    json.put("R" + rests, jsonArrayR4);
                    rests++;

                    Double demandaSorvete = Double.parseDouble(edtQtdDemandaSorvete.getText().toString());
                    if (demandaSorvete != 0) {
                        JSONArray jsonArrayR5 = new JSONArray();
                        jsonArrayR5.put(1.0);
                        jsonArrayR5.put(0.0);
                        jsonArrayR5.put(0.0);
                        jsonArrayR5.put(">=");
                        jsonArrayR5.put(demandaSorvete);

                        json.put("R" + rests, jsonArrayR5);
                        rests++;
                    }

                    Double demandaPicoleCremoso = Double.parseDouble(edtQtdDemandaPicoleCremoso.getText().toString());
                    if (demandaPicoleCremoso != 0) {
                        JSONArray jsonArrayR6 = new JSONArray();
                        jsonArrayR6.put(0.0);
                        jsonArrayR6.put(1.0);
                        jsonArrayR6.put(0.0);
                        jsonArrayR6.put(">=");
                        jsonArrayR6.put(demandaPicoleCremoso);

                        json.put("R" + rests, jsonArrayR6);
                        rests++;
                    }

                    Double demandaPicoleFruta = Double.parseDouble(edtQtdDemandaPicoleFruta.getText().toString());
                    if (demandaPicoleFruta != 0) {
                        JSONArray jsonArrayR7 = new JSONArray();
                        jsonArrayR7.put(0.0);
                        jsonArrayR7.put(0.0);
                        jsonArrayR7.put(1.0);
                        jsonArrayR7.put(">=");
                        jsonArrayR7.put(demandaPicoleFruta);

                        json.put("R" + rests, jsonArrayR7);
                        rests++;
                    }

                    JSONArray jsonArrayR8 = new JSONArray();
                    jsonArrayR8.put(1.0);
                    jsonArrayR8.put(1.0);
                    jsonArrayR8.put(1.0);
                    jsonArrayR8.put(">=");
                    jsonArrayR8.put(0.0);

                    json.put("R" + rests, jsonArrayR8);
                    rests++;

                    json.put("rests", rests);

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                    builder.setTitle("Resposta");
                                    builder.setMessage(response);
                                    builder.setPositiveButton("OK", null);
                                    builder.create().show();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("VOLLEY", error.toString());
                        }
                    }) {
                        @Override
                        public String getBodyContentType() {
                            return "application/json; charset=utf-8";
                        }

                        @Override
                        public byte[] getBody() throws AuthFailureError {
                            try {
                                return json.toString() == null ? null : json.toString().getBytes("utf-8");
                            } catch (UnsupportedEncodingException uee) {
                                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                                        json.toString(), "utf-8");
                                return null;
                            }
                        }

                        @Override
                        protected Response<String> parseNetworkResponse(NetworkResponse response) {
                            String parsed;

                            try {
                                parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                            } catch (UnsupportedEncodingException e) {
                                parsed = new String(response.data);
                            }

                            return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
                        }
                    };

                    queue.add(stringRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
