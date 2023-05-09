import java.util.Random;

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

    while (!quit) {
      System.out.println("It is running!");

      String input = in.readLine();

      String[] tokens = input.trim().split("\\s+");

      if(tokens[0].equals("test") || tokens[0].equals("")){
        Random rd = new Random(System.currentTimeMillis());
        int tempX = rd.nextInt(100)+1;
        int tempY = rd.nextInt(100)+1;
        int tempNumberOfBees = rd.nextInt(tempX * tempY / 2 + 1) + 1;
        int tempNumberOfFlowers = tempNumberOfBees;

        System.out.println(tempX + " " +  tempY + " " +  tempNumberOfBees + " " +  tempNumberOfFlowers);

        Table table = new Table(tempNumberOfBees, tempNumberOfFlowers, tempX, tempY);
        System.out.print("\n" + "---------------------" + "\n");
        for(int i = 0; i < table.y; i++){
          System.out.print("#" + (i+1) + "\t");
          for(int j = 0; j < table.x; j++){
            if(table.cells[i*table.x+j].isBee) System.out.print("B ");
            else if(!table.cells[i*table.x+j].isBee) System.out.print(table.cells[i*table.x+j].numberOfSB + " ");
          }
          System.out.println("");
        }
        System.out.print("\n" + "---------------------" + "\n");
      }

      if(tokens[0].equals("exit")){
        quit = true;
      }
    }
  }

}
