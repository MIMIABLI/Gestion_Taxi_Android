package org.doranco.gesttion_reserv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {
    private ImageView play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_chauffeur);
            this.play = (ImageView) findViewById(R.id.play);

            play.setOnClickListener(new View.OnClickListener(){
                @Override
                public void  onClick(View view) {
                    Intent otherActivity = new Intent(getApplicationContext(), MainActivity2.class);
                    startActivity(otherActivity);
                    finish();

                }
            });
    }
}