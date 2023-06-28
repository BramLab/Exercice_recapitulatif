package be.bruxellesformation.exercice_recapitulatif.model;

import java.util.Random;

public class Tryout {


    public void testBasicFight(){
        // Personage setup with initial characteristics
        Personage h1 = new Human();
        System.out.println(h1.toString());

        Personage w1 = new Wolf();
        System.out.println(w1.toString());

        h1.fight(w1);
        System.out.println(h1.toString());
        System.out.println(w1.toString());
    }

    public void test100FightsEach(){
        Personage[] humans = new Human[100];
        for(int i=0; i<100; i++) humans[i] = new Human();
        System.out.println(humans[5].toString());
        Personage[] wolves = new Wolf[100];
        for(int i=0; i<100; i++) wolves[i] = new Wolf();
    }

    public void diceProbe(){
        Dice dice = new Dice(1,6);
        dice.highestFromRolls(3,4);
        dice.highest3From4Rolls();

        int a = 0, b = 1;
        System.out.println( (a+b)/2 - Math.abs(a-b)/2 );
        a = 1; b = 2;
        System.out.println( (a+b)/2 - Math.abs(a-b)/2 );
        a = 2; b = 3;
        System.out.println( (a+b)/2 - Math.abs(a-b)/2 );
        a = 0; b = 2;
        System.out.println( (a+b)/2 - Math.abs(a-b)/2 );
        a = 1; b = 3;
        System.out.println( (a+b)/2 - Math.abs(a-b)/2 );
        a = 2; b = 4;
        System.out.println( (a+b)/2 - Math.abs(a-b)/2 );
        a = 0; b = 3;
        System.out.println( (a+b)/2 - Math.abs(a-b)/2 );
        a = 1; b = 4;
        System.out.println( (a+b)/2 - Math.abs(a-b)/2 );
        a = 2; b = 5;
        System.out.println( (a+b)/2 - Math.abs(a-b)/2 );

        // create random object
        Random ran = new Random();
        // generating integer
        double nxt = ran.nextGaussian();
        // Printing the random Number
        System.out.println("The next Gaussian value generated is : " + nxt);

    }

}
