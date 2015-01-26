package com.example.mayan.gofarm;

import java.io.Serializable;

/**
 * Created by Mayan on 18/01/2015.
 */
abstract public class Bulding implements Serializable{
    public static final int INCOMING_SMALL_FARM = 10;
    public static final int INCOMING_MEDIUM_FARM = 25;
    public static final int INCOMING_LARGE_FARM = 60;

    private int price;
    private int Incoming;
    private Sort Tybe;
    //private int ResImage;

    public abstract int getKey();

    public Bulding(int incomind, Sort sort, int Price) {
        setIncoming(incomind);
        setTybe(sort);
        setPrice(Price);
        //setResImage(Image);
    }

    public int getIncoming() {
        return Incoming;
    }
    public void setIncoming(int incoming) {
        Incoming = incoming;
    }
    public Sort getTybe() {
        return Tybe;
    }
    public void setTybe(Sort tybe) {
        Tybe = tybe;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /*public int getResImage() {
        return ResImage;
    }
    public void setResImage(int resImage) {
        ResImage = resImage;
    }*/
}

class CucumberSmallFarm extends Bulding{
    public static final int MY_KEY_DATA = 77;
    public CucumberSmallFarm() {
        super(INCOMING_SMALL_FARM, new Cucumber(), INCOMING_SMALL_FARM*new Cucumber().Price*10);
    }

    @Override
    public int getKey() {
        return this.MY_KEY_DATA;
    }
}

class CarrotsSmallFarm extends Bulding {
    public static final int MY_KEY_DATA = 78;
    public CarrotsSmallFarm() {
        super(INCOMING_SMALL_FARM, new Carrots(), INCOMING_SMALL_FARM*new Carrots().Price*10);
    }

    @Override
    public int getKey() {
        return this.MY_KEY_DATA;
    }
}

class EggplantSmallFarm extends  Bulding  {
    public static final int MY_KEY_DATA = 79;
    public EggplantSmallFarm() {
        super(INCOMING_SMALL_FARM, new Eggplant(), INCOMING_SMALL_FARM*new Eggplant().Price*10);
    }

    @Override
    public int getKey() {
        return this.MY_KEY_DATA;
    }
}

class StraeberriesSmallFarm extends  Bulding  {
    public static final int MY_KEY_DATA = 80;
    public StraeberriesSmallFarm() {
        super(INCOMING_SMALL_FARM, new Strawberries(), INCOMING_SMALL_FARM*new Strawberries().Price*10);
    }

    @Override
    public int getKey() {
        return this.MY_KEY_DATA;
    }
}

class WatermelonSmallFarm extends  Bulding  {
    public static final int MY_KEY_DATA = 97;
    public WatermelonSmallFarm() {
        super(INCOMING_SMALL_FARM, new Watermelon(), INCOMING_SMALL_FARM*new Watermelon().Price*10);
    }

    @Override
    public int getKey() {
        return this.MY_KEY_DATA;
    }
}

class MelonSmallFarm extends  Bulding  {
    public static final int MY_KEY_DATA = 98;
    public MelonSmallFarm() {
        super(INCOMING_SMALL_FARM, new Melon(), INCOMING_SMALL_FARM*new Melon().Price*10);
    }

    @Override
    public int getKey() {
        return this.MY_KEY_DATA;
    }
}

