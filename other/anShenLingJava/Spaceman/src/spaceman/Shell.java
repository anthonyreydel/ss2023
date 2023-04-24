package spaceman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import spaceman.model.Spaceman;

// TODO: add javadoc
public class Shell {

  // TODO: Add array constant that holds the 7 steps of the flying saucer
  // 1.:
  //        _.---._
  //      .'       '.
  // 2.:
  //  _.-~===========~-._
  // 3.:
  // (___________________)
  // 4.:
  //       \_______/
  // 5.:
  //        |     |
  // 6.:
  //        |_0/  |
  // 7.:
  //        |  \  |
  //        |  /\ |

  private static final List<String> flyingSaucer = Arrays.asList(
                                                    "         _.---._\n       .'       '.\n",
                                                    "   _.-~===========~-._\n", 
                                                    "  (___________________)\n", 
                                                    "        \\_______/\n",
                                                    "         |     |\n",
                                                    "         |_0/  |\n",
                                                    "         |  \\  |\n         |  /\\ |\n");
  // TODO: add String constants for 'SP> ' prompt and others, if necessary

  private Spaceman game;

  /**
   * Read and process input until the quit command has been entered.
   *
   * @param args Command line arguments.
   * @throws IOException Error reading from stdin.
   */
  public static void main(String[] args) throws IOException {
    final Shell shell = new Shell();
    shell.run();
  }

  /**
   * Run the spaceman shell. Shows prompt 'SP> ', takes commands from the user and executes them.
   */
  public void run() throws IOException {
    BufferedReader in =
        new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    boolean quit = false;

    while (!quit) {
      // TODO: add prompt, read user input and handle the commands by parsing and calling the
      // corresponding method
      // (if a separate method makes sense - see below)

      System.out.print("SP>");
      String input = in.readLine();
      String[] tokens = input.trim().split("\\s+");

      if(tokens[0].equals("NEWGAME")){
        if(tokens.length == 1) newGame();
        else newGameWord(tokens[1]);
      }

      if(tokens[0].equals("GUESS")){
        guess(tokens[1].charAt(0));
      }

      if(tokens[0].equals("FORFEIT")){
        forfeit();
      }
    }
  }

  // TIP: Add one method for each of the following actions, to structure your code well:
  // * NEWGAME
  // * NEWGAME $GIVEN_WORD
  // * GUESS $CHAR
  // * DISPLAY
  // * FORFEIT

  /*NEWGAME: Startet ein neues Spiel mit einem zufällig ausgewähltem Wort. Eventuell laufende Spiele werden ohne Nachfrage beendet.
Die Shell zeigt anschliessend das versteckte Wort mit folgender Ausgabe an:
: H I D D E N W O R D :, wobei alle versteckten Buchstaben durch einen Unterstrich ("_") ersetzt werden und alle Buchstaben (versteckt und sichtbar) durch ein Leerzeichen voneinander getrennt sind.
Beispiel für das Wort "Spaceman" mit 8 * '_':
SP> NEWGAME
: _ _ _ _ _ _ _ _ :
SP>*/
  void newGame(){
    Spaceman newgame = new Spaceman();
    newgame = newgame.create();
    for(GuseeChar gs : newgame.getState().getWord().getCharacters()){
      if(gs.check()) System.out.print(gs.get());
    }
  }
  void newGameWord(String givenWord){}
  void guess(char guessChar){}
  void display(){}
  void forfeit(){}

}
