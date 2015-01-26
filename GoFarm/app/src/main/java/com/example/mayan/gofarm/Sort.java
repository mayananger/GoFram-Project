package com.example.mayan.gofarm;

/**
 * Created by Mayan on 18/01/2015.
 */
abstract public class Sort {
    public final int Price;

    public Sort(int price) {
        Price = price;
    }
}

class Cucumber extends Sort{
    public Cucumber() {
        super(3);
    }
}

class Carrots extends Sort{
    public Carrots() {
        super(5);
    }
}

class Eggplant extends Sort{
    public Eggplant() {
        super(8);
    }
}

class Strawberries extends Sort{
    public Strawberries() {
        super(12);
    }
}

class Watermelon extends Sort{
    public  Watermelon() {
        super(16);
    }
}

class Melon extends Sort{
    public  Melon() {
        super(16);
    }
}
