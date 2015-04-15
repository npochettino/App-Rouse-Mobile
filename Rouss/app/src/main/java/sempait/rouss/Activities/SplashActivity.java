package sempait.rouss.Activities;

import android.content.Intent;
import android.os.Bundle;

import sempait.rouss.Base.BaseInnerActivity;
import sempait.rouss.Models.Sorteo;
import sempait.rouss.R;
import sempait.rouss.Services.volley.Manager.SorteoTask;
import sempait.rouss.Utils.ConfigurationClass;

/**
 * Created by martin on 19/03/15.
 */
public class SplashActivity extends BaseInnerActivity {

    public static final int SPLASH_TIME_DELAY = 3000;

    private SorteoTask mSorteTask;
    private Sorteo mSorteoActual = new Sorteo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        if (ConfigurationClass.getUserName(getApplicationContext()) != null)
            executeSorteoService();
        else {
            SplashActivity.this.startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();

        }


    }


    public void executeSorteoService() {


        mSorteTask = new SorteoTask(SplashActivity.this) {

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        };


        mSorteTask.mCodigoUsuario = ConfigurationClass.getUserCod(SplashActivity.this);
        mSorteTask.mCodigoSorteo = ConfigurationClass.getCodigoSorteo(SplashActivity.this);
        mSorteTask.execute();


    }
}
