package be.bruxellesformation.exercice_recapitulatif.model;
//java.security.SecureRandom

import java.security.SecureRandom;
import java.util.Arrays;

public class Dice {
    private final int diceMin;
    private final int diceMax;
    private final SecureRandom secureRandom = new SecureRandom();

    public Dice(int min, int max) {
        this.diceMin = min;
        this.diceMax = max;
    }

    public int roll(){
        return secureRandom.nextInt(diceMax-diceMin+1)+diceMin;
    }

    public int highest3From4Rolls(){
        int current;
        int total = 0;
        int lowest = 10;
        for (int i = 0; i < 4; i++) {
            current = roll();
            total += current;
            lowest = (lowest+current)/2 - Math.abs(lowest-current)/2; //*seems to* work also with ints.
        }
        return total - lowest;
    }

    // Alternatives
    public int highest3From4RollsAlt(){
        int a= roll(), b= roll(), c= roll(), d= roll();
        int smallest = (a<b?(a<c?(a<d?a:d):(c<d?c:d)):(b<c?b:(c<d?c:d)));
        return a+b+c+d - smallest;
    }
    public int highestFromRolls(int howManyHighest, int howManyThrows){
        int[] arr = new int[howManyThrows];
        int total = 0;
        for (int i = 0; i < howManyThrows; i++) arr[i] = roll();
        Arrays.sort(arr);
        for (int i = howManyThrows-1; i >= (howManyThrows - howManyHighest); i--) {
            total += arr[i];
        }
        return total;
    }

}
