package brown.games;

/**
 * Represents a scoring computation
 * 
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 2, 2010
 */
public interface GameScoring {

	int score(GameState state, Player player);
}
