package net.royalur.model.state;

import net.royalur.model.*;

import javax.annotation.Nonnull;

/**
 * A game state where the game is waiting for a player to roll the dice.
 * @param <P> The type of pieces that are stored on the board in this game state.
 * @param <S> The type of state that is stored for each player.
 * @param <R> The type of roll that will be made from this game state.
 */
public class WaitingForRollGameState<
        P extends Piece, S extends PlayerState, R extends Roll
> extends PlayableGameState<P, S, R> {

    /**
     * Instantiates a game state where the game is waiting for a player to roll the dice.
     * @param board       The state of the pieces on the board.
     * @param lightPlayer The state of the light player.
     * @param darkPlayer  The state of the dark player.
     * @param turn        The player who can roll the dice.
     */
    public WaitingForRollGameState(
            @Nonnull Board<P> board,
            @Nonnull S lightPlayer,
            @Nonnull S darkPlayer,
            @Nonnull Player turn) {

        super(GameStateType.WAITING_FOR_ROLL, board, lightPlayer, darkPlayer, turn);
    }

    @Override
    public @Nonnull String describe() {
        return "Waiting for the " + turn.lowerName + " player to roll the dice.";
    }
}
