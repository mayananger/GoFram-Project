package com.example.mayan.gofarm;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Mayan on 18/01/2015.
 */
public class IncomingService extends Service{

    private boolean Run;
    public static boolean OneRun = false;

    /*public static final String MONEY_KEY = "0";
    public static final String CUCUMBER_KEY = "1";
    public static final String CARROTS_KEY = "2";
    public static final String EGGPLANT_KEY = "3";
    public static final String MELON_KEY = "4";
    public static final String STRAWBERRIES_KEY = "5";
    public static final String WATERMELON_KEY = "6";*/

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Run = true;

        new IncomingThread().start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Run = false;
        super.onDestroy();

    }

    public boolean isRun() {
        return Run;
    }

    class IncomingThread extends Thread {

        @Override
        public void run() {
            if(OneRun)
                return;

            OneRun = true;

            float inco_Cucumber=0, inco_Carrots=0, inco_Eggplant=0, inco_Starwberries=0, inco_Watermelon=0, inco_Melon=0;

            while (Run) {
                Bundle bundle = MainActivity.getBulding();

                //Log.d("asaaaaaaaaaaaaaaaaaaa" , "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");

                for(int i=0; i<5*MainActivity.NumOfFarmHor; i++) {
                    try {
                        Bulding bulding = (Bulding) bundle.getSerializable(((Integer) i).toString());

                        if (bulding.getTybe() instanceof Cucumber) {
                            inco_Cucumber += bulding.getIncoming();
                        } else if (bulding.getTybe() instanceof Carrots) {
                            inco_Carrots += bulding.getIncoming();
                        } else if (bulding.getTybe() instanceof Eggplant) {
                            inco_Eggplant += bulding.getIncoming();
                        } else if (bulding.getTybe() instanceof Strawberries) {
                            inco_Starwberries += bulding.getIncoming();
                        } else if (bulding.getTybe() instanceof Watermelon) {
                            inco_Watermelon += bulding.getIncoming();
                        } else if (bulding.getTybe() instanceof Melon) {
                            inco_Melon += bulding.getIncoming();
                        }
                    }catch (Exception e){}
                }
                int time = 30;
                MainActivity.setCucumber_Quantity(MainActivity.getCucumber_Quantity()+(inco_Cucumber/time));
                MainActivity.setCarrots_Quantity(MainActivity.getCarrots_Quantity()+(inco_Carrots/time));
                MainActivity.setEggplant_Quantity(MainActivity.getEggplant_Quantity()+(inco_Eggplant/time));
                MainActivity.setStrawberries_Quantity(MainActivity.getStrawberries_Quantity()+(inco_Starwberries/time));
                MainActivity.setWatermelon_Quantity(MainActivity.getWatermelon_Quantity()+(inco_Watermelon/time));
                MainActivity.setMelon_Quantity(MainActivity.getMelon_Quantity()+(inco_Melon/time));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {e.printStackTrace();}

                inco_Cucumber=0;
                inco_Carrots=0;
                inco_Eggplant=0;
                inco_Starwberries=0;
                inco_Watermelon=0;
                inco_Melon=0;

                sendBroadcast(new Intent().setAction("com.GoFarm.MainActivity.UpdateBR"));
            }

            OneRun = false;

        }

        /*public void saveData(Bundle save) {
            save.putBundle(MainActivity.BULDING_KEY, MainActivity.getBulding());
            save.putBundle(MainActivity.MYFARM_KEY, MainActivity.getMYFARM_KEY());
            save.putFloat(MainActivity.MONEY_KEY, MainActivity.getMoney());
            save.putFloat(MainActivity.Cucumber_KEY, Carrots_Quantity);
            save.putFloat(MainActivity.Carrots_KEY, Carrots_Quantity);
            save.putFloat(MainActivity.Eggplant_KEY, Eggplant_Quantity);
            save.putFloat(MainActivity.Strawberries_KEY, Strawberries_Quantity);
            save.putFloat(MainActivity.Watermelon_KEY, Watermelon_Quantity);
            save.putFloat(MainActivity.MELON_KEY, Watermelon_Quantity);
        }*/
    }
}