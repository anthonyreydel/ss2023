package spaceman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
    }
  }

  // TIP: Add one method for each of the following actions, to structure your code well:
  // * NEWGAME
  // * NEWGAME $GIVEN_WORD
  // * GUESS $CHAR
  // * DISPLAY
  // * FORFEIT

}
