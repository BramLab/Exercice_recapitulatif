package be.bruxellesformation.exercice_recapitulatif;

import be.bruxellesformation.exercice_recapitulatif.model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ShorewoodGame {

    private Board board;
    ArrayList<Personage> personages = new ArrayList<>();
    Hero hero;

    public static void main(String[] args)
    {
        ShorewoodGame shorewoodGame = new ShorewoodGame();
        shorewoodGame.setupGame();
        shorewoodGame.gameloop();
    }

    public void setupGame(){
        int boardSize = 15;
        int startNumberOfMonsters = 10;
        int minCellsBetween = 2;

        board = new Board(boardSize,boardSize);
        addHero();
        addMonsters(startNumberOfMonsters);
        assignCoordinates(minCellsBetween, boardSize);
    }

    public void gameloop() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        int steps = 0;

        do {
            board.display(personages,true, true);

            System.out.printf(  "Step %d. %d HP. %d skin, %d gold.\n", steps
                    , hero.getLifetimeHealthPoints(), hero.getLeather(), hero.getGold()  );
            steps++;

            userInput = userInput2Coords(steps, scanner);

            ifTooCloseThenFight();

            treatLifeDeathAndHealth();

        } while (!userInput.equals("x") && hero.getLifetimeHealthPoints() > 0);

    }

    private void treatLifeDeathAndHealth() {
        if (hero.getLifetimeHealthPoints() > 0){
            hero.setLifetimeHealthPoints(hero.getOriginalHealthPoints());
        } else {
            System.out.println("Oh no, you died.");
        }
        for (int personageNo = 0; personageNo <= personages.size()-1; personageNo++)
            if (personages.get(personageNo).getLifetimeHealthPoints()<=0) personages.remove(personageNo);
    }

    private String userInput2Coords(Scanner scanner) {
        String userInput;
        System.out.print("Navigate with ZQSD keys +enter. X to eXit. ");
        userInput = scanner.nextLine();
        userInput = userInput.toLowerCase();

        int x = hero.getCoordinateX();
        int y = hero.getCoordinateY();
        switch (userInput) {
            case "z" -> y -= 1;
            case "q" -> x -= 1;
            case "s" -> y += 1;
            case "d" -> x += 1;
            default -> {/*maybe later*/}
        }
        if (x >= 0 && x < board.getSizeX() && y >= 0 && y < board.getSizeY()) {
            hero.setCoordinateX(x);
            hero.setCoordinateY(y);
        }
        return userInput;
    }

    private void ifTooCloseThenFight() {
        for (int personageNo = 0; personageNo <= personages.size()-1; personageNo++) {
            if (   (Math.abs(personages.get(personageNo).getCoordinateX() - hero.getCoordinateX()) == 1)
                    && (Math.abs(personages.get(personageNo).getCoordinateY() - hero.getCoordinateY()) == 0)
                    || (Math.abs(personages.get(personageNo).getCoordinateX() - hero.getCoordinateX()) == 0)
                    && (Math.abs(personages.get(personageNo).getCoordinateY() - hero.getCoordinateY()) == 1)  ) {
                System.out.println(hero.toString());
                System.out.println(personages.get(personageNo).toString());
                do {
                    hero.fight(personages.get(personageNo));
                    personages.get(personageNo).fight(hero);
                } while (hero.getLifetimeHealthPoints() > 0 && personages.get(personageNo).getLifetimeHealthPoints() > 0);
                System.out.println(hero.toString());
                System.out.println(personages.get(personageNo).toString());
            }
        }
    }

    private void addHero() {
        Dice dice2 = new Dice(1,2);
        dice2.roll();
        hero = (dice2.roll() == 1?new Human():new Dwarf());
        personages.add(hero);
    }

    private void addMonsters(int maxMonsters) {
        for(int monsterNo = 0; monsterNo < maxMonsters; monsterNo++) {
            Dice dice3 = new Dice(1, 3);
            int roll = dice3.roll();
            Monster monster = switch (roll) {
                case 1 -> new Wolf();
                case 2 -> new Orc();
                case 3 -> new Dragon();
                default -> null;
            };
            personages.add(monster);
        }
    }

    private void assignCoordinates(int minCellsBetween, int boardSize) {
        Dice diceBoardSize = new Dice(0,boardSize-1);
        for (int personageNo = 0; personageNo<personages.size();personageNo++ ) {
            int proposedX;
            int proposedY;
            boolean tooClose;
            do {
                tooClose = false;
                proposedX = diceBoardSize.roll();
                proposedY = diceBoardSize.roll();
                for (int prevPersonageNo = 0; prevPersonageNo <= personageNo && !tooClose; prevPersonageNo++) {
                    if ((Math.abs(personages.get(prevPersonageNo).getCoordinateX() - proposedX) <= minCellsBetween)
                            && (Math.abs(personages.get(prevPersonageNo).getCoordinateY() - proposedY) <= minCellsBetween))
                        tooClose = true;
                }
            } while (tooClose);
            personages.get(personageNo).setCoordinateX(proposedX);
            personages.get(personageNo).setCoordinateY(proposedY);
        }
    }

}
