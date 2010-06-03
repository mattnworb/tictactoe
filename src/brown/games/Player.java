package brown.games;

import java.util.Collection;

/**
 * Abstracts the ability of the player to manipulate the game state.
 * 
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 2, 2010
 */
public interface Player {

	/**
	 * Returns the score for the current game state from this players position.
	 * 
	 * @param state
	 * @return
	 */
	int eval(GameState state);

	/**
	 * Sets scoring algorithm used.
	 * 
	 * @param score
	 */
	void setScoring(GameScoring score);

	/**
	 * Return a collection of available moves given the game state.
	 * 
	 * @param state
	 * @return
	 */
	Collection<GameMove> validMoves(GameState state);
}
