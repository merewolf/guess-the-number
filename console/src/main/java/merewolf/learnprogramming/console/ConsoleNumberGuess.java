package merewolf.learnprogramming.console;

import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import merewolf.learnprogramming.Game;
import merewolf.learnprogramming.MessageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsoleNumberGuess {

  // == fields ==
  private Game game;

  private MessageGenerator messageGenerator;

  // == constructors ==

  @Autowired
  public ConsoleNumberGuess(Game game, MessageGenerator messageGenerator) {
    this.game = game;
    this.messageGenerator = messageGenerator;
  }

  // == events ==
  @EventListener(ContextRefreshedEvent.class)
  public void start() {
    log.info("Start() -> container ready for use.");

    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println(messageGenerator.getMainMessage());
      System.out.println(messageGenerator.getResultMessage());

      int guess = scanner.nextInt();
      scanner.nextLine();
      game.setGuess(guess);
      game.check();

      if (game.isGameWon() || game.isGameLost()) {
        System.out.println(messageGenerator.getResultMessage());
        System.out.println("Play again y/n?");

        String playAgainString = scanner.nextLine().trim();
        if (!playAgainString.equalsIgnoreCase("y")) {
          break;
        }
        game.reset();
      }
    }
  }
}
