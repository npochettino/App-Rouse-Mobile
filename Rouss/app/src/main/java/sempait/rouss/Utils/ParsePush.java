package sempait.rouss.Utils;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

import sempait.rouss.Activities.SplashActivity;

public class ParsePush extends Application {


    @Override
    public void onCreate() {
        super.onCreate();


        Parse.initialize(this, "meTN9fiV0dNY0AahE91RjNM2kfiTAkDsnU1KWgmC",
                "0bCkAyuIy0hzTp3iCMQwTZyPhrKmMbGHhmr4w1Zp");

        PushService.setDefaultPushCallback(this, SplashActivity.class);

        PushService.subscribe(this, "SempaIT", SplashActivity.class);



        ParseInstallation.getCurrentInstallation().put("title", "Rouss Night CLub");

    }

}
