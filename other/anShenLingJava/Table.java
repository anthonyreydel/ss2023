import java.util.Random;
import java.util.ArrayList;

public class Table{
    public int numberOfBees;
    public int numberOfFlowers;
    public int x, y, numberOfCells;
    public Cell[] cells;

    public Table(int numberOfBees, int numberOfFlowers, int x, int y) {
        this.numberOfBees = numberOfBees;
        this.numberOfFlowers = numberOfFlowers;
        this.x = x;
        this.y = y;
        this.cells = generateCells();
    }

    /**
     * To Generate a List of Cells accroding to provided attributes.
     */
    public Cell[] generateCells() {
        //Determine wether the Table should be rectangle or hexagonal
            //If rectangle
                //
            //If hexagonal
                //

        if(this.x == -1){
            this.numberOfCells = (9*y*y-6*y+1)/4;
            int howManyColumns = (3*y-1)/2;
            int howManyNotEmptyCells = (2*y*y-y+1)/2;
            int howManyBees = this.numberOfBees;
            Random rd = new Random(System.currentTimeMillis());

            Cell[] tempCells = new Cell[this.numberOfCells];
            
            for(int i = 0; i < this.numberOfCells; i++){
                tempCells[i] = new Cell(false);
            }
            
            for(int j = 0; j < y; j++){
                int howManyNotEmptyCellsInARow =  (3*y-1)/2-Math.abs(j-(y+1)/2)*2;
                int howManyEmptyCellsInARow = Math.abs(j+1-(y+1)/2)*2;

                for(int i = 0; i < howManyNotEmptyCellsInARow; i++){
                    int currentPosition = j*howManyColumns + howManyEmptyCellsInARow/2 + i + 1;
                    tempCells[currentPosition].isEmpty = false;
                }
            }

            while(howManyBees > 0){
                int tempPosition = rd.nextInt(this.numberOfCells);
                if(!tempCells[tempPosition].isBee){
                    tempCells[tempPosition].isBee = true;
                    howManyBees--;
                } 
            }

            for(int i = 0; i < this.numberOfCells; i++){
                if(i-howManyColumns-1 >= 0 && i-howManyColumns-1 <this.numberOfCells){
                    if(tempCells[i-howManyColumns-1].isEmpty == false && tempCells[i-howManyColumns-1].isBee && (i+1)%x != 1){
                        tempCells[i].numberOfSB++;
                    }
                }
                if(i-howManyColumns >= 0 && i-howManyColumns <this.numberOfCells){
                    if(tempCells[i-howManyColumns].isEmpty == false && tempCells[i-howManyColumns].isBee){
                        tempCells[i].numberOfSB++;
                    }
                }
                if(i-howManyColumns+1 >= 0 && i-howManyColumns+1 <this.numberOfCells){
                    if(tempCells[i-howManyColumns+1].isEmpty == false && tempCells[i-howManyColumns+1].isBee && (i+1)%x != 0){
                        tempCells[i].numberOfSB++;
                    }
                }
                if(i-1 >= 0 && i-1 <this.numberOfCells){
                    if(tempCells[i-1].isEmpty == false && tempCells[i-1].isBee && (i+1)%x != 1){
                        tempCells[i].numberOfSB++;
                    }
                }
                if(i+1 >= 0 && i+1 <this.numberOfCells){
                    if(tempCells[i+1].isEmpty == false && tempCells[i+1].isBee && (i+1)%x != 0){
                        tempCells[i].numberOfSB++;
                    }
                }
                if(i+howManyColumns-1 >= 0 && i+howManyColumns-1 <this.numberOfCells && (i+1)%x != 1){
                    if(tempCells[i+howManyColumns-1].isEmpty == false && tempCells[i+howManyColumns-1].isBee){
                        tempCells[i].numberOfSB++;
                    }
                }
                if(i+howManyColumns >= 0 && i+howManyColumns <this.numberOfCells){
                    if(tempCells[i+howManyColumns].isEmpty == false && tempCells[i+howManyColumns].isBee){
                        tempCells[i].numberOfSB++;
                    }
                }
                if(i+howManyColumns+1 >= 0 && i+howManyColumns+1 <this.numberOfCells && (i+1)%x != 0){
                    if(tempCells[i+howManyColumns+1].isEmpty == false && tempCells[i+howManyColumns+1].isBee){
                        tempCells[i].numberOfSB++;
                    }
                }   
            }

            return tempCells;

        } else if(this.x > 0) {
            this.numberOfCells = x*y;
            int howManyBees = this.numberOfBees;
            Random rd = new Random(System.currentTimeMillis());

            Cell[] tempCells = new Cell[this.numberOfCells];

            for(int i = 0; i < this.numberOfCells; i++){
                    tempCells[i] = new Cell(false);
            }

            while(howManyBees > 0){
                int tempPosition = rd.nextInt(this.numberOfCells);
                if(!tempCells[tempPosition].isBee){
                    tempCells[tempPosition].isBee = true;
                    howManyBees--;
                } 
            }

            for(int i = 0; i < this.numberOfCells; i++){
                if(i-x-1 >= 0 && i-x-1 <this.numberOfCells){
                    if(tempCells[i-x-1].isBee && (i+1)%x != 1){
                        tempCells[i].numberOfSB++;
                    }
                }
                if(i-x >= 0 && i-x <this.numberOfCells){
                    if(tempCells[i-x].isBee){
                        tempCells[i].numberOfSB++;
                    }
                }
                if(i-x+1 >= 0 && i-x+1 <this.numberOfCells){
                    if(tempCells[i-x+1].isBee && (i+1)%x != 0){
                        tempCells[i].numberOfSB++;
                    }
                }
                if(i-1 >= 0 && i-1 <this.numberOfCells){
                    if(tempCells[i-1].isBee && (i+1)%x != 1){
                        tempCells[i].numberOfSB++;
                    }
                }
                if(i+1 >= 0 && i+1 <this.numberOfCells){
                    if(tempCells[i+1].isBee  && (i+1)%x != 0){
                        tempCells[i].numberOfSB++;
                    }
                }
                if(i+x-1 >= 0 && i+x-1 <this.numberOfCells){
                    if(tempCells[i+x-1].isBee && (i+1)%x != 1){
                        tempCells[i].numberOfSB++;
                    }
                }
                if(i+x >= 0 && i+x <this.numberOfCells){
                    if(tempCells[i+x].isBee){
                        tempCells[i].numberOfSB++;
                    }
                }
                if(i+x+1 >= 0 && i+x+1 <this.numberOfCells){
                    if(tempCells[i+x+1].isBee && (i+1)%x != 0){
                        tempCells[i].numberOfSB++;
                    }
                }
                
            }
            return tempCells;
        } else {
            Cell[] tempCells = new Cell[1];
            return tempCells;
        }
    }

}
