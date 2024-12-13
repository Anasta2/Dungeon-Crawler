public enum Direction {
    NORTH(2), SOUTH(0), EAST(3), WEST(1);

    private int frameLineNumber;

    // Constructor to initialize each direction with a unique integer value
    Direction(int frameLineNumber) {
        this.frameLineNumber = frameLineNumber;
    }

    // Getter method to retrieve the associated integer value
    public int getFrameLineNumber() {
        return frameLineNumber;
    }
}
