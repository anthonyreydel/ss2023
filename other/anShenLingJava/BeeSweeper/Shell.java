import java.util.Random;
import java.lang.Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

// TODO: add javadoc
public class Shell {

  public static void main(String[] args) throws IOException {
    final Shell shell = new Shell();
    shell.run();
  }

  public void run() throws IOException {
    BufferedReader in =
        new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    boolean quit = false;
    boolean gameRunning = false;
    Table table = new Table(0,0,0,0);

    System.out.print("\033[H\033[2J");

    while (!quit) {
      if(!gameRunning){
        System.out.println("There are no game running. You can now start a new game or exit.");
      }
      System.out.print("Input: ");

      String input = in.readLine();

      String[] tokens = input.trim().split("\\s+");

      while(!tokens[0].equals("newgame") 
          && !tokens[0].equals("") 
          && !tokens[0].equals("exit") 
          && !tokens[0].equals("reveal") 
          && !tokens[0].equals("mark") 
          && !tokens[0].equals("unmark")
          && !tokens[0].equals("giveup")
	  && !tokens[0].equals("help")){
        System.out.println("Please give a correct input!");

        System.out.print("Input: ");
        input = in.readLine();
        tokens = input.trim().split("\\s+");

      }

      if (tokens[0].equals("newgame")){
        if(gameRunning){
          System.out.println("There are currently still game running! You can't start a new game, unless you exit first.");
          continue;
        }

        while(tokens.length != 3 
            && tokens.length != 4 
            && tokens.length != 1 
            && !(Integer.parseInt(tokens[tokens.length]) > Math.pow(Integer.parseInt(tokens[tokens.length-1]), 2)-3)){
          System.out.println("Please give a correct input!");

          System.out.print("Input: ");
          input = in.readLine();
          tokens = input.trim().split("\\s+");
        }

        table = generateTable(tokens);
        gameRunning = true;
        printTable(table,tokens);
      }

      if(tokens[0].equals("reveal")){
        if(!gameRunning){
          System.out.println("There are currently no game running! You need to start a new game first.");
          continue;
        }

        while(tokens.length != 3){
          System.out.println("Please give a correct input!");

          System.out.print("Input: ");
          input = in.readLine();
          tokens = input.trim().split("\\s+");
        }

        int revealPosition = (Integer.parseInt(tokens[2])-1)*table.x + tokens[1].charAt(0)-97;

        if(revealPosition < 0 || revealPosition >= table.cells.length){
          System.out.println("This coordinate is wrong!");
          continue;
        }
        if(table.cells[revealPosition].isEmpty){
          System.out.println("This position is empty!");
          continue;
        }
        if(table.cells[revealPosition].isRevealed){
          System.out.println("This position is already revealed! You can reveal another cell, or do something else.");
          continue;
        }
        if(table.cells[revealPosition].isMarked){
          System.out.println("This position is marked! You have to unmark it first to reveal it.");
          continue;
        }
        table.cells[revealPosition].isRevealed = true;

        if(outputAndCheckEnd(table, tokens))  gameRunning = false;

      }

      if(tokens[0].equals("mark")){
        if(!gameRunning){
          System.out.println("There are currently no game running! You need to start a new game first.");
          continue;
        }

        while(tokens.length != 3){
          System.out.println("Please give a correct input!");

          System.out.print("Input: ");
          input = in.readLine();
          tokens = input.trim().split("\\s+");
        }
        
        int markPosition = (Integer.parseInt(tokens[2])-1)*table.x + tokens[1].charAt(0)-97;
        
        if(markPosition < 0 || markPosition >= table.cells.length){
          System.out.println("This coordinate is wrong!");
          continue;
        }
        if(table.cells[markPosition].isEmpty){
          System.out.println("This position is empty!");
          continue;
        }
        if(table.cells[markPosition].isMarked){
          System.out.println("It's already marked! Mark another one, or do somethint else.");
          continue;
        }
        if(table.cells[markPosition].isRevealed){
          System.out.println("This position is already revealed! You can mark/unmark another cell, or do something else.");
          continue;
        }

        table.cells[markPosition].isMarked = true;

        table.numberOfFlowers--;

        if(outputAndCheckEnd(table, tokens))  gameRunning = false;

      }

      if(tokens[0].equals("unmark")){
        if(!gameRunning){
          System.out.println("There are currently no game running! You need to start a new game first.");
          continue;
        }

        while(tokens.length != 3){
          System.out.println("Please give a correct input!");

          System.out.print("Input: ");
          input = in.readLine();
          tokens = input.trim().split("\\s+");
        }
        
        int unmarkPosition = (Integer.parseInt(tokens[2])-1)*table.x + tokens[1].charAt(0)-97;

        if(unmarkPosition < 0 || unmarkPosition >= table.cells.length){
          System.out.println("This coordinate is wrong!");
          continue;
        }
        if(table.cells[unmarkPosition].isEmpty){
          System.out.println("This position is empty!");
          continue;
        }
        if(!table.cells[unmarkPosition].isMarked){
          System.out.println("It's not marked yet! You can mark it, or do somethint else.");
          continue;
        }
        if(table.cells[unmarkPosition].isRevealed){
          System.out.println("This position is already revealed! You can mark/unmark another cell, or do something else.");
          continue;
        }

        table.cells[unmarkPosition].isMarked = false;

        table.numberOfFlowers++;

        if(outputAndCheckEnd(table, tokens))  gameRunning = false;
        

      }

      if(tokens[0].equals("giveup")){
	if(!gameRunning){
		System.out.println("There is currently no game running, what are you giving up for?\n\nStart a newgame using \"newgame\" command.\n");
		continue;
	}
	System.out.println("You pussy!");
        printRealTable(table, tokens);
        gameRunning = false;
        continue;
      }

      if(tokens[0].equals("")){
        continue;
      }

      if(tokens[0].equals("help")){
	System.out.println("You can use following commands: " + "\n\n" 
				+ "reveal [column coordination] [row coordination]: " + "\t\t" + "reveal a cell" + "\n"
				+ "mark [column coordination] [row coordination]: " + "\t\t\t" + "mark a cell as Bee" + "\n"
				+ "unmark [column coordination] [row coordination]: " + "\t\t" + "unmark a marked cell" + "\n\n"
				+ "newgame: " + "\t\t\t\t\t\t\t" + "start a newgame" + "\n"
				+ "giveup: " + "\t\t\t\t\t\t\t" + "give up current game" + "\n"
				+ "exit: " + "\t\t\t\t\t\t\t\t" + "exit this software" + "\n"
				+ "help: " + "\t\t\t\t\t\t\t\t" + "show help table" + "\n");

	continue;
      }

      if(tokens[0].equals("exit")){
        quit = true;
      }
    }
  }
  
  public boolean outputAndCheckEnd(Table table, String[] tokens){
    printTable(table, tokens);
    if(table.numberOfFlowers == 0){
      return checkWin(table);
    }
    boolean end = checkWin(table) || checkLose(table);
    if(end){
      printRealTable(table, tokens);
    }

    return end;
  }

  public boolean checkWin(Table table){
    if(table.numberOfFlowers == 0){
      boolean win = true;
      for(Cell i : table.cells){
        if(i.isBee && !i.isMarked) win = false;
      }

      if(win) System.out.println("All bees are marked, you've won!\n---------------------\n\n");

      return win;

    } else return false;
  }

  public boolean checkLose(Table table){
      boolean lose = false;
      for(Cell i : table.cells){
        if(i.isBee && i.isRevealed){
          lose = true;
          System.out.println("It's a f***ing Bee! Ouch!\nYou've lost!\n---------------------\n\n");
          break;
        }
      }

      int untouchedCells = table.numberOfCells;
      for(Cell i : table.cells){
        if(i.isMarked || i.isRevealed) untouchedCells--;
        if(untouchedCells == 0) break;
      }

      if(untouchedCells == 0 && !checkWin(table)){
        lose = true;
        System.out.println("No unmarked or unrevealed cell left.\nYou've lost!\n---------------------\n\n");
      }

      return lose;
  }

  public Table generateTable(String[] tokens){
      if(tokens.length == 4){

        Table table = new Table(Integer.parseInt(tokens[3]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
        return table;

      } else if(tokens.length == 3){

        Table table = new Table(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[2]), -1, Integer.parseInt(tokens[1]));
        return table;

      } else {

        Random rd = new Random(System.currentTimeMillis());
        int tempX = rd.nextInt(17)+1, tempY = rd.nextInt(17)+1;
        while(tempY%2 != 1){
          tempY = rd.nextInt(17)+1;
        }
        int tempAmountOfBees = rd.nextInt(tempY) + (2*tempY*tempY-tempY+1)/20 + 1;
        int tempAmountOfFlowers = tempAmountOfBees;

        if(rd.nextInt(1) > 0.5){
          Table table = new Table(tempAmountOfBees, tempAmountOfFlowers, tempX, tempY);
          return table;

        } else {
          Table table = new Table(tempAmountOfBees, tempAmountOfFlowers, -1, tempY);
          return table;
        }
      }
  }

  public void printTable(Table table, String[] tokens){
    System.out.print("\033[H\033[2J");

    System.out.print("\n" + "---------------------" + "\n"
        + "Number of Cells: " + table.numberOfNotEmptyCells
        + "\nNumber of Bees: " + table.numberOfBees
        + "\nNumber of Flowers: " + table.numberOfFlowers 
        + "\n---------------------\n\n");

        System.out.print("    \t  ");
        for(int i = 0; i < table.x; i++){
          System.out.print((char)(i+97));
          System.out.print(" ");
        }
        System.out.print("\n");
        
        System.out.print("    \t  ");
        for(int i = 0; i < table.x; i++){
          System.out.print("- ");
        }
        System.out.print("\n");

        for(int i = 0; i < table.y; i++){

          System.out.print("  " + (i+1) + "\t| ");

          for(int j = 0; j < table.x; j++){

            if(table.cells[i*table.x+j].isMarked) 
              System.out.print("B ");

	          else if(table.cells[i*table.x+j].isEmpty) 
              System.out.print("  ");

            else if(table.cells[i*table.x+j].isRevealed && !table.cells[i*table.x+j].isBee)
              System.out.print(table.cells[i*table.x+j].numberOfSB + " ");

            else if(table.cells[i*table.x+j].isRevealed && table.cells[i*table.x+j].isBee)
              System.out.print("! ");

            else if(!table.cells[i*table.x+j].isMarked && !table.cells[i*table.x+j].isEmpty && !table.cells[i*table.x+j].isRevealed) 
              System.out.print("* ");
          
          }
          
          System.out.print("|\n");
        
        }

        System.out.print("    \t  ");
        for(int i = 0; i < table.x; i++){
          System.out.print("- ");
        }
        System.out.print("\n");

        System.out.print("\n" + "---------------------" + "\n");
        System.out.print("Last Input: ");
        for(String i : tokens){
          System.out.print(i + " ");
        }
        System.out.print("\n");
      
  }

  public void printRealTable(Table table, String[] tokens){

    System.out.print("\n---------------------\nThis is the real table.\n" + "---------------------\n");

        System.out.print("    \t  ");
        for(int i = 0; i < table.x; i++){
          System.out.print((char)(i+97));
          System.out.print(" ");
        }
        System.out.print("\n");
        
        System.out.print("    \t  ");
        for(int i = 0; i < table.x; i++){
          System.out.print("- ");
        }
        System.out.print("\n");

        for(int i = 0; i < table.y; i++){

          System.out.print("  " + (i+1) + "\t| ");

          for(int j = 0; j < table.x; j++){

            if(table.cells[i*table.x+j].isBee) 
              System.out.print("B ");

	          else if(table.cells[i*table.x+j].isEmpty) 
              System.out.print("  ");

            else if(!table.cells[i*table.x+j].isEmpty && !table.cells[i*table.x+j].isBee) 
              System.out.print("* ");
          
          }
          
          System.out.print("|\n");
        
        }

        System.out.print("    \t  ");
        for(int i = 0; i < table.x; i++){
          System.out.print("- ");
        }
        System.out.print("\n");

        System.out.print("\n" + "---------------------" + "\n");
        System.out.print("Last Input: ");
        for(String i : tokens){
          System.out.print(i + " ");
        }
        System.out.print("\n");
  }
}
