package brown.games;

/**
 * Defines how moves can manipulate the game state.
 * 
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 2, 2010
 */
public interface GameMove {

	boolean isValid(GameState state);

	void execute(GameState state);

	void undo(GameState state);
}
