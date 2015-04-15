package sempait.rouss.Fragment;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import sempait.rouss.Base.BaseActivity;
import sempait.rouss.R;
import sempait.rouss.Services.volley.Manager.PutParticipanteTask;
import sempait.rouss.Utils.ConfigurationClass;

/**
 * Created by martin on 10/04/15.
 */
public class ResultFragment extends BaseFragment {

    private View mView;
    private ImageView mImgResult;
    private PutParticipanteTask mPutParticipanteTask;
    private SoundPool soundPool;
    private int streamId;


    public static ResultFragment newInstance() {

        return new ResultFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BaseActivity) mContext).getActionBar().setTitle("Gracias por participar");
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        streamId = soundPool.load(mContext, R.raw.aplausos, 0);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = (ViewGroup) inflater.inflate(R.layout.fragment_result, container, false);

        mImgResult = (ImageView) mView.findViewById(R.id.img_result);

        mImgResult.setImageResource(setImageResult());

        setSound();

        executePutParticipanteService();


        return mView;
    }

    private int setImageResult() {

        int mIdImage = R.drawable.seralaproxima;

        switch (Integer.valueOf(ConfigurationClass.getcodigoPremio(mContext))) {

            case 1:
                mIdImage = R.drawable.trago;

                break;
            case 2:
                mIdImage = R.drawable.descuento;

                break;
            case 3:
                mIdImage = R.drawable.espumante;

                break;
            case 4:
                mIdImage = R.drawable.seralaproxima;


                break;
            case 5:
                mIdImage = R.drawable.entrada;

                break;
            case 6:
                mIdImage = R.drawable.p2x1;

                break;


        }


        return mIdImage;
    }

    private void executePutParticipanteService() {

        mPutParticipanteTask = new PutParticipanteTask(mContext);

        mPutParticipanteTask.mCodigoPremio = ConfigurationClass.getcodigoPremio(mContext);
        mPutParticipanteTask.mCodigoSorteo = ConfigurationClass.getCodigoSorteo(mContext);
        mPutParticipanteTask.mCodigoUsuario = ConfigurationClass.getUserCod(mContext);

        mPutParticipanteTask.execute();

    }

    public void setSound() {


        new Thread(new Runnable() {

            public void run() {

                float y = 0;


                soundPool.play(streamId, 1, 1, 1, 0, 1f);
                SystemClock.sleep(3000);
                soundPool.autoPause();

//                for (float x = 0; x <= 3000; ) {
//                    y = y - 0.1f;
//                    soundPool.setVolume(streamId, y, y);
//                    SystemClock.sleep(1000);
//
//
//                }


            }

        }).start();


    }

}
