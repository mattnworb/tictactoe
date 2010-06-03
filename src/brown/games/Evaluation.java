package brown.games;

/**
 * Implementations of Evaluation will represent various algorithms that can be
 * used to solve a game.
 * 
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 2, 2010
 */
public interface Evaluation {

	GameMove bestMove(GameState state, Player player, Player opponent);
}
