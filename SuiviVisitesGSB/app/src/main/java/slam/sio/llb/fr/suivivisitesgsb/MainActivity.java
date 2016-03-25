package slam.sio.llb.fr.suivivisitesgsb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private ImageView imageViewVisites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewVisites = (ImageView) findViewById(R.id.imgVisites);
        imageViewVisites.setOnClickListener(imageClick);
    }


            private View.OnClickListener imageClick = new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i;
                    switch (v.getId()) {
                        // Cas du clic sur l'image Visite
                        case R.id.imgVisites:
                            i = new Intent(getApplicationContext(), AfficheVisites.class);
                            startActivity(i);
                            break;
                    }
                }
            };

}
