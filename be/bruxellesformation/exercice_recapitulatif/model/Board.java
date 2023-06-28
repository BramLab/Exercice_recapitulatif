package be.bruxellesformation.exercice_recapitulatif.model;

import java.util.List;

public class Board {

    private final String[][] boardArray;
    private final int sizeX;
    private final int sizeY;

    public Board(int sizeX, int sizeY) {
        boardArray = new String[sizeX][sizeY];
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        cleanBoard();
    }

    // region getters, setters
    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
    // endregion

    public void cleanBoard(){
        for (int xBoard=0;xBoard<sizeX;xBoard++)
            for(int yBoard=0;yBoard<sizeY;yBoard++)
                boardArray[xBoard][yBoard] = " - ";
    }

    public void placePersonages(List<Personage> personages){
        //for(int i = 0; i<personages.length; i++)
        //    board[personages[i].getCoordinateX()][personages[i].getCoordinateY()] = " " + personages[i].displayString() + " ";
        // "Enhanced for":
        for (Personage personage : personages)
            boardArray[personage.getCoordinateX()][personage.getCoordinateY()] = personage.displayString();
    }

    public void display(boolean withEdges, boolean withContent) {
        System.out.print("\n   ");
        if (withEdges) for (int xBoard = 0; xBoard<getSizeX(); xBoard++) System.out.print(" " + (char)(xBoard+65)  + " ");
        System.out.println();
        for (int yBoard=0;yBoard<getSizeX();yBoard++) {
            if (withEdges) System.out.print((yBoard>9?"":" ") + yBoard + " ");
            for (int xBoard = 0; xBoard < getSizeX(); xBoard++){
                System.out.print(withContent?(boardArray[xBoard][yBoard]):" - ");
            }
            System.out.println();
        }
    }

    public void display(List<Personage> personages, boolean withEdges, boolean withContent) {
        cleanBoard();
        placePersonages(personages);
        display(withEdges, withContent);
    }

}