package merewolf.learnprogramming;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Slf4j
@Component
public class GameImpl implements Game {

  // == fields ==
  @Getter(AccessLevel.NONE)
  private NumberGenerator numberGenerator;

  private int guessCount;
  private int number;
  private int guess;
  private int smallest;
  private int biggest;
  private int remainingGuesses;
  private boolean validNumberRange = true;

  // == constructors ==
  @Autowired
  public GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount) {
    this.numberGenerator = numberGenerator;
    this.guessCount = guessCount;
  }

  // == init ==
  @PostConstruct
  @Override
  public void reset() {
    smallest = numberGenerator.getMinNumber();
    guess = 0;
    remainingGuesses = guessCount;
    biggest = numberGenerator.getMaxNumber();
    number = numberGenerator.next();
    log.debug("the number is {}", number);
  }

  @PreDestroy
  public void preDestroy() {
    log.info("In Game preDestroy()");
  }

  // == public methods ==


  @Override
  public void check() {
    checkValidNumberRange();
    if (validNumberRange) {
      if (guess > number) {
        biggest = guess - 1;
      }

      if (guess < number) {
        smallest = guess + 1;
      }
    }
    remainingGuesses--;
  }

  @Override
  public boolean isGameWon() {
    return guess == number;
  }

  @Override
  public boolean isGameLost() {
    return !isGameWon() && remainingGuesses <= 0;
  }

  // == private methods ==
  private void checkValidNumberRange() {
    validNumberRange = (guess >= smallest) && (guess <= biggest);
  }
}
