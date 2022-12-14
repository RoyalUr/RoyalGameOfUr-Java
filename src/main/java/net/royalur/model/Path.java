package net.royalur.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * The path that a player's pieces must take around the board.
 */
public class Path implements Iterable<Tile> {

    /**
     * The player that this path is intended for.
     */
    public final @Nonnull Player player;

    /**
     * The ordered list of tiles that pieces must progress through on the board.
     * This does not include the start and end tiles that exist off of the board.
     */
    public final @Nonnull List<Tile> tiles;

    /**
     * A tile that exists off the board, which represents where the player's
     * piece should start from to get on to the board. This is mainly useful
     * for building user interfaces.
     */
    public final @Nonnull Tile startTile;

    /**
     * A tile that exists off the board, which represents where the player's
     * piece should end on when it is taken off of the board. This is mainly
     * useful for building user interfaces.
     */
    public final @Nonnull Tile endTile;

    /**
     * The number of tiles in this path.
     */
    public final int length;

    /**
     * Instantiates a path for a player's pieces to take around the board.
     * @param player    The player that this path is intended for.
     * @param tiles     The ordered list of tiles that pieces must progress through on the board.
     * @param startTile The tile that pieces should be moved from so that they can be moved on to the board.
     * @param endTile   The tile that pieces should be moved to so that they can be moved off the board.
     */
    public Path(@Nonnull Player player, @Nonnull List<Tile> tiles, @Nonnull Tile startTile, @Nonnull Tile endTile) {

        this.startTile = startTile;
        this.endTile = endTile;
        if (tiles.isEmpty())
            throw new IllegalArgumentException("Paths must have at least one tile");

        this.player = player;
        this.tiles = Collections.unmodifiableList(new ArrayList<>(tiles));
        this.length = tiles.size();
    }

    /**
     * Retrieves the tile at the index {@code index} in this path. The index
     * is treated as a 0-based index into the tiles of this path.
     * @param index The index of the tile in this path.
     * @return The tile at the given index in this path.
     */
    public @Nonnull Tile get(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException(
                    "Index " + index + " is out of bounds of the tiles of this path " +
                    "(" + length + " tiles)"
            );
        }
        return tiles.get(index);
    }

    @Override
    public int hashCode() {
        return player.hashCode() ^ (37 * tiles.hashCode()) ^ (97 * startTile.hashCode()) ^ (283 * endTile.hashCode());
    }

    /**
     * Determines whether the path the tiles must take around the board is
     * equivalent between this path and {@code other}. This ignores the name
     * and intended player of the paths, and the start and end tiles.
     * @param other The path to check for equivalency.
     * @return Whether the path the tiles must take around the board is equivalent
     *         between this path and {@code other}.
     */
    public boolean isEquivalent(@Nonnull Path other) {
        return tiles.equals(other.tiles);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || !obj.getClass().equals(getClass()))
            return false;

        Path other = (Path) obj;
        return player == other.player && isEquivalent(other) &&
                startTile.equals(other.startTile) && endTile.equals(other.endTile);
    }

    @Override
    public @Nonnull String toString() {
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < tiles.size(); ++index) {
            if (index > 0) {
                builder.append(", ");
            }
            builder.append(tiles.get(index));
        }
        return builder.toString();
    }

    @Override
    public Iterator<Tile> iterator() {
        return tiles.iterator();
    }
}
