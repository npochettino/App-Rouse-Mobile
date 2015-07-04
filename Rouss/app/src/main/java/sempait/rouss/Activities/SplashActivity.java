package sempait.rouss.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import sempait.rouss.Base.BaseActivity;
import sempait.rouss.Base.BaseInnerActivity;
import sempait.rouss.Models.Sorteo;
import sempait.rouss.R;
import sempait.rouss.Services.volley.Manager.PromocionesTask;
import sempait.rouss.Services.volley.Manager.SorteoTask;
import sempait.rouss.Utils.ConfigurationClass;
import sempait.rouss.Utils.DialogCatalog;

/**
 * Created by martin on 19/03/15.
 */
public class SplashActivity extends BaseInnerActivity {

    public static final int SPLASH_TIME_DELAY = 3000;

    private SorteoTask mSorteTask;
    private Sorteo mSorteoActual = new Sorteo();
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        setContentView(R.layout.activity_splash);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                executeServicePromocion();


            }
        }, 0);






    }

    private void executeServicePromocion() {

        PromocionesTask mPromocionesTask = new PromocionesTask(this) {


            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                if (result != null) {
                    String url = "http://rouss.sempait.com.ar/assets/rouss/publicidad/" + result;
//                    String url ="http://agenciasanluis.com/notas/wp-content/uploads/2013/09/AGAPORNIS-FERIA-ALIMENTOS1.jpg";
                    ((BaseActivity) mContext).imageLoader.displayImage(url, (ImageView) findViewById(R.id.img_splash), new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String s, View view) {

                        }

                        @Override
                        public void onLoadingFailed(String s, View view, FailReason failReason) {

                            continueToApp();


                        }

                        @Override
                        public void onLoadingComplete(String s, View view, Bitmap bitmap) {




                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {


                                    continueToApp();




                                }
                            }, SPLASH_TIME_DELAY);








                        }

                        @Override
                        public void onLoadingCancelled(String s, View view) {

                        }
                    });
//                    SystemClock.sleep(3000);
//                    ((ImageView) findViewById(R.id.img_Promocion)).setVisibility(View.GONE);

                } else {
                    continueToApp();


                }


            }
        };

        mPromocionesTask.execute();
    }


    public void executeSorteoService() {


        mSorteTask = new SorteoTask(SplashActivity.this) {

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                if (result != null) {
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } else
                    DialogCatalog.mensajeError("Hubo un problema, intentelo nuevamente en unos minutos", SplashActivity.this);

            }
        };


        mSorteTask.mCodigoUsuario = ConfigurationClass.getUserCod(SplashActivity.this);
        mSorteTask.mCodigoSorteo = ConfigurationClass.getCodigoSorteo(SplashActivity.this);
        mSorteTask.execute();


    }

    public void continueToApp() {


        if (ConfigurationClass.getUserCod(getApplicationContext()) != null)
            executeSorteoService();
        else {

            SplashActivity.this.startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();

        }

    }
}
