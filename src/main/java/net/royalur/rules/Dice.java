package net.royalur.rules;

import net.royalur.model.Roll;

import javax.annotation.Nonnull;

/**
 * A generator of dice rolls.
 */
public interface Dice<R extends Roll> {

    /**
     * Generates a random roll using this dice.
     * @return A random roll of this dice.
     */
    @Nonnull R roll();

    /**
     * Retrieves the maximum value that this dice could possibly roll.
     * @return The maximum value that this dice could possibly roll.
     */
    int getMaxRoll();
}
