package merewolf.learnprogramming.config;

import merewolf.learnprogramming.GuessCount;
import merewolf.learnprogramming.MaxNumber;
import merewolf.learnprogramming.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/game.properties")
@ComponentScan(basePackages = "merewolf.learnprogramming")
public class GameConfig {

  // == fields ==
  @Value("${game.maxNumber:100}")
  private int maxNumber;

  @Value("${game.guessCount:10}")
  private int guessCount;

  @Value("${game.minNumber:0}")
  private int minNumber;
  // == bean methods ==

  @Bean
  @MaxNumber
  public int maxNumber() {
    return maxNumber;
  }

  @Bean
  @GuessCount
  public int guessCount() {
    return guessCount;
  }

  @Bean
  @MinNumber
  public int minNumber() {
    return minNumber;
  }
}
