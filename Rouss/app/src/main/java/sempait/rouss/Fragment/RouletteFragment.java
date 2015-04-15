package sempait.rouss.Fragment;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import sempait.rouss.Base.BaseActivity;
import sempait.rouss.R;
import sempait.rouss.Services.volley.Manager.SorteoTask;
import sempait.rouss.Utils.ConfigurationClass;
import sempait.rouss.Utils.DialogCatalog;

/**
 * Created by martin on 29/03/15.
 */
public class RouletteFragment extends BaseFragment {


    private ViewGroup mView;
    private ImageView mImgRoulette;
    private ImageView mRoulette;
    private int mFinal = 2520;
    private int mItemPremio;
    private SoundPool soundPool;
    private int streamId;
    private GestureDetector gdt;
    private boolean flag = false;
    private boolean flagOnResume = false;
    private SorteoTask mSorteTask;


    public static RouletteFragment newInstance() {

        return new RouletteFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gdt = new GestureDetector(new GestureListener());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = (ViewGroup) inflater.inflate(R.layout.fragment_roulette, container, false);

        mImgRoulette = (ImageView) mView.findViewById(R.id.imgBtnRoulette);
        mRoulette = (ImageView) mView.findViewById(R.id.roulette);
        mItemPremio = Integer.valueOf(ConfigurationClass.getcodigoPremio(mContext));
        ((BaseActivity) mContext).getActionBar().setTitle("Ruleta");
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        streamId = soundPool.load(mContext, R.raw.ruleta, 0);


        setStateRouletteAndShowMsj();


        return mView;
    }

    private void setStateRouletteAndShowMsj() {
        mItemPremio = Integer.valueOf(ConfigurationClass.getcodigoPremio(mContext));
        if (mItemPremio == 7 || mItemPremio == 8 || mItemPremio == 9 || mItemPremio == 10) {

            showMsj();

        }


    }

    @Override
    public void onResume() {

        super.onResume();

        if (flagOnResume)
            executeSorteoService();
        else
            flagOnResume = true;


    }


    private void showMsj() {

        switch (mItemPremio) {


            case 7:
                DialogCatalog.mensajeError("No puede jugar en este  momento, has acumulado todos los premios permitidos", mContext);
                break;
            case 8:
                DialogCatalog.mensajeError("No puede jugar en este momento, has superado la cantidad de oportunidades permitidas", mContext);
                break;

            case 9:
                DialogCatalog.mensajeError("No puede jugar en este momento, se han terminado los premios para este sorteo", mContext);
                break;

            case 10:
                DialogCatalog.mensajeError("No puede jugar en este  momento, no hay sortes abiertos", mContext);
                break;


        }

        mRoulette.setEnabled(false);
        mImgRoulette.setEnabled(false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mRoulette.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                gdt.onTouchEvent(event);
                return true;
            }
        });


        mImgRoulette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                setAnimation();
            }
        });

    }

    private void setAnimation() {
        setSound();
        startAnimation();


    }


    public void startAnimation() {


        final RotateAnimation firstAnimation = new RotateAnimation(0.0f, getFinalPosition(), Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        firstAnimation.setDuration(5000);
        firstAnimation.setFillAfter(true);
        firstAnimation.setFillEnabled(true);
        firstAnimation.setInterpolator(mContext, R.anim.decelerate_interpolator);


        firstAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {


                mImgRoulette.setClickable(false);
                firstAnimation.reset();


            }

            @Override
            public void onAnimationEnd(Animation animation) {


                mImgRoulette.setClickable(true);


                ((BaseActivity) mContext).replaceInnerFragmentFromBotton(ResultFragment.newInstance(), true);

            }

            ;

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        mRoulette.startAnimation(firstAnimation);


    }


    public int getFinalPosition() {

        mFinal = 2520;

        switch (mItemPremio) {

            case 1:
                mFinal = mFinal - 60;

                break;
            case 2:
                mFinal = mFinal - 120;

                break;
            case 3:
                mFinal = mFinal - 180;

                break;
            case 4:
                mFinal = mFinal - 240;

                break;
            case 5:
                mFinal = mFinal - 300;

                break;
            case 6:
                mFinal = mFinal - 360;

                break;


        }

        return mFinal;
    }


    public void setSound() {


        new Thread(new Runnable() {

            public void run() {


                soundPool.play(streamId, 1, 1, 1, 0, 1f);
                for (float x = 0; x <= 0.5f; x += 0.1) {
                    soundPool.setRate(streamId, 1 - x);
                    SystemClock.sleep(1000);
                }

            }

        }).start();


    }

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                setAnimation();
                return false; // Right to left
//            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//                return false; // Left to right
//            }
//
//                if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
//                    startAnimation();
//                    return false; // Bottom to top
            } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                setAnimation();
                return false; // Top to bottom
            }
            return false;
        }
    }

    public void executeSorteoService() {


        mSorteTask = new SorteoTask(mContext) {

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                setStateRouletteAndShowMsj();
            }
        };
        mSorteTask.mCodigoUsuario = ConfigurationClass.getUserCod(mContext);
        mSorteTask.mCodigoSorteo = ConfigurationClass.getCodigoSorteo(mContext);
        mSorteTask.execute();


    }


}
