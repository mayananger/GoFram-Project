package com.example.mayan.gofarm;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MarketActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
    }


    public void cucumberSell(View view) {
        int q = (int)MainActivity.getCucumber_Quantity();
        MainActivity.setCucumber_Quantity(MainActivity.getCucumber_Quantity()-q);
        MainActivity.setMoney(MainActivity.getMoney()+(q*new Cucumber().Price));
    }

    public void carrotSell(View view) {
        int q = (int)MainActivity.getCarrots_Quantity();
        MainActivity.setCarrots_Quantity(MainActivity.getCarrots_Quantity()-q);
        MainActivity.setMoney(MainActivity.getMoney()+(q*new Carrots().Price));

    }

    public void eggplantSell(View view) {
        int q = (int)MainActivity.getEggplant_Quantity();
        MainActivity.setEggplant_Quantity(MainActivity.getEggplant_Quantity()-q);
        MainActivity.setMoney(MainActivity.getMoney()+(q*new Eggplant().Price));

    }

    public void strawberrySell(View view) {
        int q = (int)MainActivity.getStrawberries_Quantity();
        MainActivity.setStrawberries_Quantity(MainActivity.getStrawberries_Quantity()-q);
        MainActivity.setMoney(MainActivity.getMoney()+(q*new Strawberries().Price));

    }

    public void melonSell(View view) {
        int q = (int)MainActivity.getMelon_Quantity();
        MainActivity.setMelon_Quantity(MainActivity.getMelon_Quantity()-q);
        MainActivity.setMoney(MainActivity.getMoney()+(q*new Melon().Price));

    }

    public void watermelonSell(View view) {
        int q = (int)MainActivity.getWatermelon_Quantity();
        MainActivity.setWatermelon_Quantity(MainActivity.getWatermelon_Quantity()-q);
        MainActivity.setMoney(MainActivity.getMoney()+(q*new Watermelon().Price));

    }

    public void Beak(View view) {
        setResult(RESULT_OK);
        finish();
    }


}
