package merewolf.learnprogramming;

import java.util.Random;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NumberGeneratorImpl implements NumberGenerator {

  // == fields ==
  private final Random random = new Random();

  @Getter
  private final int maxNumber;

  @Getter
  private final int minNumber;

  // == constructors ==
  @Autowired
  public NumberGeneratorImpl(@MaxNumber int maxNumber, @MinNumber int minNumber) {
    this.maxNumber = maxNumber;
    this.minNumber = minNumber;
  }

  // == public methods ==
  @Override
  public int next() {
    return random.nextInt(maxNumber);
  }
}
