public class Cell{
    public boolean isBee, isRevealed, isMarked, isEmpty;
    public int numberOfSB;


    public Cell(boolean isBee) {
        this.isBee = isBee;
        this.isRevealed = false;
        this.isMarked = false;
        this.isEmpty = false;
        this.numberOfSB = 0;
    }

}