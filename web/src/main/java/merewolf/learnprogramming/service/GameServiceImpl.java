package merewolf.learnprogramming.service;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import merewolf.learnprogramming.Game;
import merewolf.learnprogramming.MessageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

  private final Game game;

  private final MessageGenerator messageGenerator;

  @Autowired
  public GameServiceImpl(Game game, MessageGenerator messageGenerator) {
    this.game = game;
    this.messageGenerator = messageGenerator;
  }

  @PostConstruct
  public void init() {
    log.info("Main message is {}", getMainMessage());
    log.info("Number to guess is {}", game.getNumber());
  }

  @Override
  public boolean isGameOver() {
    return game.isGameLost() || game.isGameWon();
  }

  @Override
  public String getMainMessage() {
    return messageGenerator.getMainMessage();
  }

  @Override
  public String getResultMessage() {
    return messageGenerator.getResultMessage();
  }

  @Override
  public void checkGuess(int guess) {
    game.setGuess(guess);
    game.check();
  }

  @Override
  public void reset() {
    game.reset();
  }
}
