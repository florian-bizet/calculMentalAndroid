package com.example.calculmental;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.calculmental.model.Calcul;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Calcul calculCourant;
    private int    points;

    /* Composants Android */
    private TextView twPoints;
    private TextView twCalcul;
    private EditText etResultat;
    private Button   btnConfirmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.points = 0;

        //Récupération des composants
        this.twPoints     = findViewById(R.id.main_tw_points    );
        this.twCalcul     = findViewById(R.id.main_tw_calcul    );
        this.etResultat   = findViewById(R.id.main_et_reponse   );
        this.btnConfirmer = findViewById(R.id.main_btn_confirmer);
        this.btnConfirmer.setEnabled(false);

        this.etResultat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.this.btnConfirmer.setEnabled(charSequence.length() != 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        this.etResultat.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {
                    MainActivity.this.btnConfirmer.performClick();
                    return true;
                }
                return false;
            }
        });

        this.btnConfirmer.setOnClickListener(this);

        this.update();
    }

    @Override
    public void onClick(View v)
    {
        this.checkAnswer();
    }

    public void checkAnswer()
    {
        int ans = Integer.parseInt(this.etResultat.getText().toString());
        if (this.calculCourant.checkAnswer(ans)) this.points++;

        this.update();
    }

    public void update()
    {
        //Création d'un nouveau calcul
        this.calculCourant = new Calcul();

        //MAJ des composants android
        String strPoints = getResources().getString(R.string.main_tw_points);

        this.twPoints.setText(String.format(strPoints, this.points));
        this.twCalcul.setText(this.calculCourant.toString());
        this.etResultat.setText("");
        this.btnConfirmer.setEnabled(false);
    }
}