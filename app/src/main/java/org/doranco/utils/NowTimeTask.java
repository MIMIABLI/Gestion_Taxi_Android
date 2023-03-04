package org.doranco.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class NowTimeTask {

    private Date currentTime;
    private SimpleDateFormat simpleDateFormat;
    private String dateNomString;
    private TimerTask timerTask;
    private String nowTime;
    private Timer timer;

    public NowTimeTask() {
        timer.schedule(getNowDateTimeAtEverySeconde(), 0, 1000);
    }

    private TimerTask getNowDateTimeAtEverySeconde() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
               nowTime = getNowDateTime();
            }
        };
        return timerTask;
    }

    private String getNowDateTime() {
        currentTime = Calendar.getInstance().getTime();
        simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss"  );
        dateNomString = simpleDateFormat.format(currentTime);
        return dateNomString;
    }

    public String getNowTime() {
        return nowTime;
    }
}
