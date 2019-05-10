package oosd.models.units.behaviour.enums;

import static oosd.helpers.NumberHelper.isEven;
import static oosd.helpers.NumberHelper.isOdd;

/**
 * This enum is absolute to describe linear directions.
 * Unless we change the english :)
 */
public enum LinearDirections {
    NORTH {
        @Override
        public int nextRow(int columns, int rows) {
            return rows - 1;
        }

        @Override
        public int nextColumn(int columns, int rows) {
            return columns;
        }
    },
    NORTH_EAST {
        @Override
        public int nextColumn(int columns, int rows) {
            return columns + 1;
        }

        @Override
        public int nextRow(int columns, int rows) {
            return rows - (isEven(columns) ? 1 : 0);
        }
    },
    SOUTH_EAST {
        @Override
        public int nextColumn(int columns, int rows) {
            return columns + 1;
        }

        @Override
        public int nextRow(int columns, int rows) {
            return rows + (isOdd(columns) ? 1 : 0);
        }
    },
    SOUTH {
        @Override
        public int nextRow(int columns, int rows) {
            return rows + 1;
        }

        @Override
        public int nextColumn(int columns, int rows) {
            return columns;
        }
    },
    SOUTH_WEST {
        @Override
        public int nextColumn(int columns, int rows) {
            return columns - 1;
        }

        @Override
        public int nextRow(int columns, int rows) {
            return rows + (isOdd(columns) ? 1 : 0);
        }
    },
    NORTH_WEST {
        @Override
        public int nextColumn(int columns, int rows) {
            return columns - 1;
        }

        @Override
        public int nextRow(int columns, int rows) {
            return rows - (isEven(columns) ? 1 : 0);
        }
    };

    /**
     * Get rows with added calculation.
     *
     * @param columns integer
     * @param rows    integer
     * @return integer
     */
    public abstract int nextRow(int columns, int rows);

    /**
     * Get columns with added calculation.
     *
     * @param columns integer
     * @param rows    integer
     * @return integer
     */
    public abstract int nextColumn(int columns, int rows);
}
