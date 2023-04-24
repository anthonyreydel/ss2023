package spaceman.model;

//  TODO: add javadoc
public class GameState {

  private Phase currentPhase;
  private WordToGuess wordToGuess;
  private Countdown countdown;
  private int initialCountdownValue;

  GameState(final String word, final int countdownValue) {
    wordToGuess = new WordToGuess(word);
    countdown = new Countdown(countdownValue);
    initialCountdownValue = countdownValue;
    currentPhase = Phase.RUNNING;
  }

  // TODO: add methods to set state values
  public void setCurrentPhase(Phase currentPhase){
    this.currentPhase = currentPhase;
  }

  public void setWordToGuess(WordToGuess wordToGuess){
    this.wordToGuess = wordToGuess;
  }

  public void setCountdown(Countdown countdown){
    this.countdown = countdown;
  }

  public void setInitialCountdownValue(int initialCountdownValue){
    this.initialCountdownValue = initialCountdownValue;
  }

  /**
   * Return the current phase of the game.
   *
   * @return the current phase.
   */
  public Phase getCurrentPhase() {
    return currentPhase;
  }

  public WordToGuess getWord() {
    return wordToGuess;
  }

  public int getCountdownValue() {
    return countdown.getCurrentValue();
  }

  public Countdown getCountdown(){
    return countdown;
  }

  public int getMaximumCountdownValue() {
    return initialCountdownValue;
  }
}
