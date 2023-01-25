package com.example.calculmental.model;

import androidx.annotation.NonNull;

public class Calcul
{
    private static int valMin = 0;
    private static int valMax = 10;

    private final int val1;
    private final int val2;
    private final int operation; //0 : addition 1 : soustraction 2 : multiplication

    public Calcul()
    {
        int range = Calcul.valMax - Calcul.valMin+1;

        this.val1      = Calcul.valMin + (int) (Math.random()*range);
        this.val2      = Calcul.valMin + (int) (Math.random()*range);
        this.operation = (int) (Math.random()*3);
    }

    public int getAnswer()
    {
        switch (this.operation)
        {
            case 0: return this.val1 + this.val2;
            case 1: return this.val1 - this.val2;
            case 2: return this.val1 * this.val2;
        }

        return 0;
    }

    public boolean checkAnswer(int ans) {return ans == this.getAnswer();}

    @NonNull
    @Override
    public String toString()
    {
        char charOperation = ' ';

        switch (this.operation)
        {
            case 0: charOperation = '+'; break;
            case 1: charOperation = '-'; break;
            case 2: charOperation = 'x'; break;
        }

        return this.val1 + " " + charOperation + " " + this.val2 + " = ?";
    }
}
