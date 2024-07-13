package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 128;
    private static final int HEIGHT = 72;

    private static final int SEED = 4222242;
    private static final Random RANDOM = new Random(SEED);

    public static void main(String[] args) {
        TERenderer hex = new TERenderer();
        hex.initialize(WIDTH, HEIGHT);
        TETile[][] map = new TETile[WIDTH][HEIGHT];
        initialize(map);
        drawSomeHexes(map, 4, 40, 50);
        hex.renderFrame(map);
    }

    public static void initialize(TETile[][] map) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                map[y][x] = Tileset.NOTHING;
            }
        }
    }

    private static TETile getRandomTile() {
        int seed = RANDOM.nextInt(5);
        return switch (seed) {
            case 0 -> Tileset.FLOWER;
            case 1 -> Tileset.GRASS;
            case 2 -> Tileset.SAND;
            case 3 -> Tileset.MOUNTAIN;
            case 4 -> Tileset.WATER;
            default -> Tileset.NOTHING;
        };
    }

    private static void drawSomeHexes(TETile[][] map, int edge, int startX, int startY) {
        int stepX = 2 * edge - 1;
        int endX = startX + 2 * stepX;
        int stepY = 2 * edge;
        int rangeY = 2 * stepY;
        for (int x = startX; x <= endX; x += stepX) {
            for (int y = startY; y >= startY - rangeY; y -= stepY) {
                drawAHex(map, edge, x, y);
            }
            startY += edge;
            rangeY += stepY;
        }
        startY -= 2 * edge;
        rangeY -= 2 * stepY;
        for (int x = endX + stepX; x <= endX + 2 * stepX; x += stepX) {
            for (int y = startY; y >= startY - rangeY; y -= stepY) {
                drawAHex(map, edge, x, y);
            }
            startY -= edge;
            rangeY -= stepY;
        }
    }

    private static void drawAHex(TETile[][] map, int edge, int startX, int startY) {
        TETile whatTile = getRandomTile();
        int length = edge;
        for (int y = startY; y > startY - edge; y--) {
            for (int x = startX; x < startX + length; x++) {
                map[x][y] = whatTile;
            }
            startX--;
            length += 2;
        }
        length -= 2;
        startX++;
        for (int y = startY - edge; y > startY - edge * 2; y--) {
            for (int x = startX; x < startX + length; x++) {
                map[x][y] = whatTile;
            }
            startX++;
            length -= 2;
        }
    }
}
