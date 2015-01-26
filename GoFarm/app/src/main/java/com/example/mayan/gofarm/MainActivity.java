package com.example.mayan.gofarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    public static final int MONEY_DATA_KEY = 1;
    public static final int Cucumber_DATA_KEY = 2;
    public static final int Carrots_DATA_KEY = 3;
    public static final int Eggplant_DATA_KEY = 4;
    public static final int Strawberries_DATA_KEY = 5;
    public static final int Watermelon_DATA_KEY = 6;
    public static final int MELON_DATA_KEY = 7;

    public static final String DATA_BASE_BULDING = "database_bulding";
    public static final String DATA_BASE_DATA = "dtatbase_data";
    public static final String DATA_BASE_TEXTURE = "database_texture";

    public static final int NumOfFarmHor = 8;
    public static final String MYFARM_KEY = "Mt Farm Key";
    public static final String BULDING_KEY = "bulding key";

    private int px;
    public static final int ID_OF_TOBUILDVUTTON = 100001;
    public static final int ID_OF_GO_TO_MARMET = 100002;
    public static final int ID_OF_GO_TO_EARTH = 100003;

    private static float Money;
    private static float Cucumber_Quantity;
    private static float Carrots_Quantity;
    private static float Eggplant_Quantity;
    private static float Strawberries_Quantity;
    private static float Watermelon_Quantity;
    private static float Melon_Quantity;

    public static final String MONEY_KEY = "money";
    public static final String Cucumber_KEY = "cucumber";
    public static final String Carrots_KEY = "carrots";
    public static final String Eggplant_KEY = "eggplant";
    public static final String Strawberries_KEY = "strawberries";
    public static final String Watermelon_KEY = "watermelon";
    public static final String MELON_KEY = "melon";

    public static final String TO_SAVE_KE = "to save key";
    public static final String TEXTURE_KEY = "texture";

    private ImageButton ToBuildButton;
    private ImageButton FieldTarget;

    private Bundle myFarm;
    private static Bundle ToSave;
    private static Bundle Bulding;
    private Bundle Texture;

    public static Bundle getBulding() {
        return Bulding;
    }

    public static float getMoney() {
        return Money;
    }
    public static void setMoney(float money) {
        Money = money;
    }
    public static float getCucumber_Quantity() {
        return Cucumber_Quantity;
    }
    public static void setCucumber_Quantity(float cucumber_Quantity) {
        Cucumber_Quantity = cucumber_Quantity;
    }
    public static float getCarrots_Quantity() {
        return Carrots_Quantity;
    }
    public static void setCarrots_Quantity(float carrots_Quantity) {
        Carrots_Quantity = carrots_Quantity;
    }
    public static float getEggplant_Quantity() {
        return Eggplant_Quantity;
    }
    public static void setEggplant_Quantity(float eggplant_Quantity) {
        Eggplant_Quantity = eggplant_Quantity;
    }
    public static float getStrawberries_Quantity() {
        return Strawberries_Quantity;
    }
    public static void setStrawberries_Quantity(float strawberries_Quantity) {
        Strawberries_Quantity = strawberries_Quantity;
    }
    public static float getWatermelon_Quantity() {
        return Watermelon_Quantity;
    }
    public static void setWatermelon_Quantity(float watermelon_Quantity) {
        Watermelon_Quantity = watermelon_Quantity;
    }
    public static float getMelon_Quantity() {
        return Melon_Quantity;
    }
    public static void setMelon_Quantity(float melon_Quantity) {
        Melon_Quantity = melon_Quantity;
    }
    public static Bundle getToSave() {
        return ToSave;
    }
    public static void setToSave(Bundle toSave) {
        ToSave = toSave;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());

        init(savedInstanceState);

        if(ToSave == null)
            ToSave = new Bundle();
        updateTextView();

        /*try {
            Toast.makeText(this, "hey", Toast.LENGTH_LONG).show();
            Texture = savedInstanceState.getBundle(TO_SAVE_KE).getBundle(TEXTURE_KEY);
            Toast.makeText(this, Texture.toString(), Toast.LENGTH_LONG).show();
            smartSetUp();
            Toast.makeText(this, "OOO", Toast.LENGTH_LONG).show();
        }catch (Exception e) {e.getMessage();}*/

        getDataFromDataBase();

        Intent intent = new Intent(this, IncomingService.class);
        startService(intent);
        registerReceiver(new UpdateBR(), new IntentFilter("com.GoFarm.MainActivity.UpdateBR"));

        //setMoney(500);
    }

    public void initTexture(Bundle texture) {

            Texture = new Bundle();

            for(int i=0; i<40; i++) {
                Texture.putInt(((Integer)i).toString(), R.drawable.null_fram);
            }
    }

    public static String getMYFARM_KEY() {
        return MYFARM_KEY;
    }

    public void updateTextView() {
        ((TextView)findViewById(R.id.Money)).setText(new Integer(((Float)Money).intValue()).toString());
        ((TextView)findViewById(R.id.cucumber)).setText(new Integer(((Float)Cucumber_Quantity).intValue()).toString());
        ((TextView)findViewById(R.id.carrot)).setText(new Integer(((Float)Carrots_Quantity).intValue()).toString());
        ((TextView)findViewById(R.id.eggplant)).setText(new Integer(((Float)Eggplant_Quantity).intValue()).toString());
        ((TextView)findViewById(R.id.strawberry)).setText(new Integer(((Float)Strawberries_Quantity).intValue()).toString());
        ((TextView)findViewById(R.id.watermelon)).setText(new Integer(((Float)Watermelon_Quantity).intValue()).toString());
        ((TextView)findViewById(R.id.melon)).setText(new Integer(((Float)Melon_Quantity).intValue()).toString());
    }

    public int getDP(int pixel) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixel, getResources().getDisplayMetrics());
    }

    public void init(Bundle save) {

        onInitHouseButton(ID_OF_TOBUILDVUTTON, ID_OF_GO_TO_MARMET, ID_OF_GO_TO_EARTH);

        Bundle s = null;
        try {
            s = save.getBundle(TO_SAVE_KE);
        }catch (Exception e) {}

        if(s != null) {
            loadSetUp(s);
        }

        if((Bulding == null || myFarm == null) && save == null)
            newSetUp();
    }

    public void loadSetUp(Bundle save) {
        try {
            Bulding = save.getBundle(BULDING_KEY);
            myFarm = save.getBundle(MYFARM_KEY);
            Money = save.getFloat(MONEY_KEY);
            Cucumber_Quantity = save.getFloat(Cucumber_KEY);
            Carrots_Quantity = save.getFloat(Carrots_KEY);
            Eggplant_Quantity = save.getFloat(Eggplant_KEY);
            Strawberries_Quantity = save.getFloat(Strawberries_KEY);
            Watermelon_Quantity = save.getFloat(Watermelon_KEY);
            Melon_Quantity = save.getFloat(MELON_KEY);
            Texture = save.getBundle(TEXTURE_KEY);
        }catch (Exception e){}


    }

    public void onInitHouseButton(int ID_OF_TOBUILDVUTTON, int ID_OF_GO_TO_MARMET, int ID_OF_GO_TO_ERTH) {
        ImageButton buttonBuild = new ImageButton(this);
        buttonBuild.setId(ID_OF_TOBUILDVUTTON);
        buttonBuild.setEnabled(false);
        buttonBuild.setImageResource(R.drawable.to_build);
        ToBuildButton = buttonBuild;
        buttonBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBuild(v);
            }
        });

        ImageButton buttonMarket = new ImageButton(this);
        buttonMarket.setId(ID_OF_GO_TO_MARMET);
        buttonMarket.setImageResource(R.drawable.market);
        buttonMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMarket(v);
            }
        });

        ImageButton buttonEarth = new ImageButton(this);
        buttonEarth.setId(ID_OF_GO_TO_ERTH);
        buttonEarth.setImageResource(R.drawable.earth);
        buttonEarth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEarth(v);
            }
        });

        ((LinearLayout)findViewById(R.id.House)).addView(buttonMarket, new LayoutParams(getDP(70), getDP(60)));
        ((LinearLayout)findViewById(R.id.House)).addView(buttonBuild, new LayoutParams(getDP(70), getDP(60)));
        ((LinearLayout)findViewById(R.id.House)).addView(buttonEarth, new LayoutParams(getDP(70), getDP(60)));
    }

    public void getDataFromDataBase() {
        try {
            MySQL sql = new MySQL(this, DATA_BASE_DATA);
            sql.open();
            Cursor cursor = sql.getAllValues();
            cursor.moveToNext();
            setMoney((float) cursor.getDouble(cursor.getColumnIndex(sql.KEY_VALUE)));
            cursor.moveToNext();
            setCucumber_Quantity((float) cursor.getDouble(cursor.getColumnIndex(sql.KEY_VALUE)));
            cursor.moveToNext();
            setCarrots_Quantity((float) cursor.getDouble(cursor.getColumnIndex(sql.KEY_VALUE)));
            cursor.moveToNext();
            setEggplant_Quantity((float) cursor.getDouble(cursor.getColumnIndex(sql.KEY_VALUE)));
            cursor.moveToNext();
            setStrawberries_Quantity((float) cursor.getDouble(cursor.getColumnIndex(sql.KEY_VALUE)));
            cursor.moveToNext();
            setMelon_Quantity((float) cursor.getDouble(cursor.getColumnIndex(sql.KEY_VALUE)));
            cursor.moveToNext();
            setWatermelon_Quantity((float) cursor.getDouble(cursor.getColumnIndex(sql.KEY_VALUE)));
            cursor.close();
            sql.close();
        }catch (Exception e) {}
    }

    public void getBuildeingFormDataBase() {
        Bulding = new Bundle();
        MySQL sql = new MySQL(this, DATA_BASE_BULDING);
        sql.open();
        Cursor cursor = sql.getAllValues();

        for(int i=0; i<NumOfFarmHor*5; i++) {
            int key = cursor.getInt(cursor.getColumnIndex(sql.KEY_VALUE));

            if(key == new CucumberSmallFarm().getKey()) {
                Bulding.putSerializable(((Integer) i).toString(), new CucumberSmallFarm());
            }else if(key == new CarrotsSmallFarm().getKey()) {
                Bulding.putSerializable(((Integer)i).toString(), new CarrotsSmallFarm());
            }else if(key == new EggplantSmallFarm().getKey()) {
                Bulding.putSerializable(((Integer)i).toString(), new EggplantSmallFarm());
            }else if(key == new StraeberriesSmallFarm().getKey()) {
                Bulding.putSerializable(((Integer)i).toString(), new StraeberriesSmallFarm().getKey());
            }else if(key == new WatermelonSmallFarm().getKey()) {
                Bulding.putSerializable(((Integer)i).toString(), new WatermelonSmallFarm().getKey());
            }else if(key == new MelonSmallFarm().getKey()) {
                Bulding.putSerializable(((Integer)i).toString(), new MelonSmallFarm().getKey());
            }else {
                Bulding.putSerializable(((Integer)i).toString(), new null_bulding());
            }


        }

        cursor.close();
        sql.close();
    }

    /*public void loadSetUp() {
        int count = 0;

        addFarmsToLine((LinearLayout)findViewById(R.id.FarmHor1), count);
        count += NumOfFarmHor;
        addFarmsToLine((LinearLayout)findViewById(R.id.FarmHor2), count);
        count += NumOfFarmHor;
        addFarmsToLine((LinearLayout)findViewById(R.id.FarmHor3), count);
        count += NumOfFarmHor;
        addFarmsToLine((LinearLayout)findViewById(R.id.FarmHor4), count);
        count += NumOfFarmHor;
        addFarmsToLine((LinearLayout)findViewById(R.id.FarmHor5), count);

    }*/

    public void addFarmsToLine(LinearLayout Line, int start) {

        LayoutParams params = new LayoutParams(px, px);

        for(int i=start; i<start+NumOfFarmHor+1; i++) {
            Line.addView((ButtonFarm)myFarm.getSerializable(((Integer)i).toString()));
        }

    }

    public void newSetUp() {
        setMoney(300);
        setCucumber_Quantity(0);
        setCarrots_Quantity(0);
        setEggplant_Quantity(0);
        setStrawberries_Quantity(0);
        setWatermelon_Quantity(0);
        setMelon_Quantity(0);

        myFarm = new Bundle();
        Bulding = new Bundle();


        setUp();

    }

    public void smartSetUp() {
        int count = 0;
        oneSetUp(R.id.FarmHor1, count);
        count += NumOfFarmHor;
        oneSetUp(R.id.FarmHor2, count);
        count += NumOfFarmHor;
        oneSetUp(R.id.FarmHor3, count);
        count += NumOfFarmHor;
        oneSetUp(R.id.FarmHor4, count);
        count += NumOfFarmHor;
        oneSetUp(R.id.FarmHor5, count);
        Toast.makeText(this, "R", Toast.LENGTH_LONG).show();
    }

    private void oneSetUp(int id, int start) {
        LinearLayout layout = (LinearLayout)findViewById(id);
        LayoutParams params = new LayoutParams(px, px);

        for(int i= start; i<start+NumOfFarmHor+1; i++) {
            ButtonFarm farm = new ButtonFarm(this);

            farm.setId(i);
            farm.setImageResource(Texture.getInt(((Integer)i).toString()));
            farm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickEdit(v);
                }
            });

            ((LinearLayout)findViewById(id)).addView(farm, params);
        }
        Toast.makeText(this, "hey", Toast.LENGTH_LONG).show();
    }

    public void setUp() {
        initTexture(Texture);
        int Count = 0;
        for (int i = 0; i < NumOfFarmHor; i++) {
            ButtonFarm farm = new ButtonFarm(this);

            farm.setId(Count);
            farm.setImageResource(R.drawable.null_fram);
            farm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickEdit(v);
                }
            });

            LinearLayout.LayoutParams params = new LayoutParams(px, px);

            getBulding().putSerializable(((Integer) Count).toString(), new null_bulding());
            myFarm.putSerializable(((Integer)Count).toString(), farm);

            ((LinearLayout)findViewById(R.id.FarmHor1)).addView(farm, params);
            Count++;
        }

        for (int i = 0; i < NumOfFarmHor; i++) {
            ButtonFarm farm = new ButtonFarm(this);
            farm.setId(Count);
            farm.setImageResource(R.drawable.null_fram);
            farm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickEdit(v);
                }
            });

            LinearLayout.LayoutParams params = new LayoutParams(px, px);

            getBulding().putSerializable(((Integer) Count).toString(), new null_bulding());
            myFarm.putSerializable(((Integer)Count).toString(), farm);

            ((LinearLayout)findViewById(R.id.FarmHor2)).addView(farm, params);
            Count++;
        }

        for (int i = 0; i < NumOfFarmHor; i++) {
            ButtonFarm farm = new ButtonFarm(this);
            farm.setId(Count);
            farm.setImageResource(R.drawable.null_fram);
            farm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickEdit(v);
                }
            });

            LinearLayout.LayoutParams params = new LayoutParams(px, px);

            getBulding().putSerializable(((Integer) Count).toString(), new null_bulding());
            myFarm.putSerializable(((Integer)Count).toString(), farm);

            ((LinearLayout)findViewById(R.id.FarmHor3)).addView(farm, params);
            Count++;
        }

        for (int i = 0; i < NumOfFarmHor; i++) {
            ButtonFarm farm = new ButtonFarm(this);
            farm.setId(Count);
            farm.setImageResource(R.drawable.null_fram);
            farm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickEdit(v);
                }
            });

            LinearLayout.LayoutParams params = new LayoutParams(px, px);

            getBulding().putSerializable(((Integer) Count).toString(), new null_bulding());
            myFarm.putSerializable(((Integer)Count).toString(), farm);

            ((LinearLayout)findViewById(R.id.FarmHor4)).addView(farm, params);
            Count++;
        }

        for (int i = 0; i < NumOfFarmHor; i++) {
            ButtonFarm farm = new ButtonFarm(this);
            farm.setId(Count);
            farm.setImageResource(R.drawable.null_fram);
            farm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickEdit(v);
                }
            });

            LinearLayout.LayoutParams params = new LayoutParams(px, px);

            getBulding().putSerializable(((Integer) Count).toString(), new null_bulding());
            myFarm.putSerializable(((Integer)Count).toString(), farm);

            ((LinearLayout)findViewById(R.id.FarmHor5)).addView(farm, params);
            Count++;
        }
    }

    public void onClickEdit(View view) {
        if(ToBuildButton != null)
            ToBuildButton.setEnabled(true);

        FieldTarget = (ImageButton)view;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBundle(TO_SAVE_KE, ToSave);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putBundle(TO_SAVE_KE, ToSave);
    }

    public void saveData(Bundle save) {
        save.putBundle(BULDING_KEY, Bulding);
        save.putBundle(MYFARM_KEY, myFarm);
        save.putFloat(MONEY_KEY, Money);
        save.putFloat(Cucumber_KEY, Carrots_Quantity);
        save.putFloat(Carrots_KEY, Carrots_Quantity);
        save.putFloat(Eggplant_KEY, Eggplant_Quantity);
        save.putFloat(Strawberries_KEY, Strawberries_Quantity);
        save.putFloat(Watermelon_KEY, Watermelon_Quantity);
        save.putFloat(MELON_KEY, Watermelon_Quantity);
        save.putBundle(TEXTURE_KEY, Texture);
    }

    public void goToBuild(View view) {

        if(FieldTarget != null) {

            HorizontalScrollView scrollView = new HorizontalScrollView(this);
            scrollView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT , getDP(70)));

            LinearLayout layout = new LinearLayout(this);
            layout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, getDP(70)));
            scrollView.addView(layout);

            ((LinearLayout)findViewById(R.id.House)).removeAllViews();

            ((LinearLayout)findViewById(R.id.House)).addView(scrollView);

            ImageButton buttonCucFarm1 = new ImageButton(this);
            buttonCucFarm1.setImageResource(R.drawable.farm_cucumber_1);
            buttonCucFarm1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBuildSmartEditor(new CucumberSmallFarm(), R.drawable.farm_cucumber_1);
                }
            });

            ImageButton buttonCarrFarm1 = new ImageButton(this);
            buttonCarrFarm1.setImageResource(R.drawable.farm_carrots_1);
            buttonCarrFarm1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBuildSmartEditor(new CarrotsSmallFarm(), R.drawable.farm_carrots_1);
                }
            });

            ImageButton buttonEggp1 = new ImageButton(this);
            buttonEggp1.setImageResource(R.drawable.farm_eggplant_1);
            buttonEggp1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBuildSmartEditor(new EggplantSmallFarm(), R.drawable.farm_eggplant_1);
                }
            });

            ImageButton buttonStarw1 = new ImageButton(this);
            buttonStarw1.setImageResource(R.drawable.strawberry_1);
            buttonStarw1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBuildSmartEditor(new StraeberriesSmallFarm(), R.drawable.strawberry_1);
                }
            });

            ImageButton buttonWater1 = new ImageButton(this);
            buttonWater1.setImageResource(R.drawable.farm_watermelon_1);
            buttonWater1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBuildSmartEditor(new WatermelonSmallFarm(), R.drawable.farm_watermelon_1);
                }
            });

            ImageButton buttonMelon1 = new ImageButton(this);
            buttonMelon1.setImageResource(R.drawable.farm_melon_1);
            buttonMelon1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBuildSmartEditor(new MelonSmallFarm(), R.drawable.farm_melon_1);
                }
            });

            ImageButton buttonCancel = new ImageButton(this);
            buttonCancel.setImageResource(R.drawable.cancel);
            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    postToBuild((LinearLayout)findViewById(R.id.House));
                }
            });

            LayoutParams params = new LayoutParams(getDP(70), getDP(60));

            layout.addView(buttonCucFarm1, params);
            layout.addView(buttonCarrFarm1, params);
            layout.addView(buttonEggp1, params);
            layout.addView(buttonStarw1, params);
            layout.addView(buttonMelon1, params);
            layout.addView(buttonWater1, params);
            layout.addView(buttonCancel, params);
        }else {
            Toast.makeText(this, "Chose A Field", Toast.LENGTH_LONG).show();
        }

    }

    private void postToBuild(LinearLayout House) {
        House.removeAllViews();
        onInitHouseButton(ID_OF_TOBUILDVUTTON, ID_OF_GO_TO_MARMET, ID_OF_GO_TO_EARTH);
    }

    public void goToMarket(View view) {

        startActivityForResult(new Intent(this, MarketActivity.class), 6);

    }

    public void goToEarth(View view) {

    }

    public Bundle getTexture() {
        return Texture;
    }

    public void setTexture(Bundle texture) {
        Texture = texture;
    }

    public class UpdateBR extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            saveData();
            //saveBulding();


            updateTextView();

        }


    }

    public void saveBulding() {
        MySQL sql = new MySQL(this, DATA_BASE_BULDING);
        sql.open();
        sql.destroyMe();

        for(int i=0; i<NumOfFarmHor*5; i++) {
            sql.insertNewData(i, ((Bulding)Bulding.getSerializable(((Integer)i).toString())).getKey());
        }
        sql.close();
    }

    public void saveData() {
        MySQL sql = new MySQL(getBaseContext(), DATA_BASE_DATA);
        sql.open();
        sql.destroyMe();

        sql.insertNewData(MONEY_DATA_KEY, getMoney());
        sql.insertNewData(Cucumber_DATA_KEY, getCucumber_Quantity());
        sql.insertNewData(Carrots_DATA_KEY, getCarrots_Quantity());
        sql.insertNewData(Eggplant_DATA_KEY, getEggplant_Quantity());
        sql.insertNewData(Strawberries_DATA_KEY, getStrawberries_Quantity());
        sql.insertNewData(MELON_DATA_KEY, getMelon_Quantity());
        sql.insertNewData(Watermelon_DATA_KEY, getWatermelon_Quantity());

        sql.close();
    }

    public ImageButton getFieldTarget() {
        return FieldTarget;
    }

    public synchronized boolean minusMoney(int minus) {
        if(Money >= minus) {
            Money -= minus;
            return true;
        }
        return false;
    }

    public void onBuildSmartEditor(Bulding TypeOfFarm, int resImage) {
        ImageButton FieldTarget = getFieldTarget();
        if(minusMoney(TypeOfFarm.getPrice())) {

            if(Bulding.getSerializable(((Integer)FieldTarget.getId()).toString()) instanceof null_bulding) {
                Bulding.putSerializable(((Integer)FieldTarget.getId()).toString(), TypeOfFarm);
                FieldTarget.setImageResource(resImage);
                Texture.putInt(((Integer)FieldTarget.getId()).toString(), resImage);
            }else {
                Toast.makeText(getBaseContext(), "This Field Building", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(getBaseContext(), "Not Enough Money", Toast.LENGTH_SHORT).show();
            Toast.makeText(getBaseContext(), "This Building Is Costs "+TypeOfFarm.getPrice(), Toast.LENGTH_LONG).show();
        }

        postToBuild((LinearLayout)findViewById(R.id.House));
    }
}
