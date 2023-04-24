package spaceman.model;

import java.util.Random;

/** Class that contains all possible words for guessing. */
class WordDatabase {

  private static final String[] WORDS = {
    "Spaceman", "Alien", "Earthling", "Homo Sapiens",
  };

  /**
   * Return a randomly chosen word from the word database. Words are chosen randomly according to a
   * uniform distribution.
   */
  String getWord() {
    // TODO: implement

    Random r = new Random();
    int i = r.nextInt(5)-1;

    return WORDS[i];
  }
}
