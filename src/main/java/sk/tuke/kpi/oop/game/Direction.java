package sk.tuke.kpi.oop.game;

public enum Direction {
    NONE(0,0),
    NORTH(0,1),
    EAST(1,0),
    SOUTH(0,-1),
    WEST(-1,0),
    NORHTEAST(1,1),
    NORTHWEST(-1,1),
    SOUTHEAST(1,-1),
    SOUTHWEST(-1,-1);

    Direction(int dx,int dy){
        this.dx = dx;
        this.dy = dy;
    }

    private final int dx;
    private final int dy;

    public float getAngle() {
        return (float)Math.toDegrees( Math.atan2(dy, dx))-90;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public static Direction getDirection(float angle) {
        float degrees;
        if (angle>180) {
            degrees = angle-360;
            Direction[] directions = Direction.values();
            for (Direction direction:directions) {
                if (direction.getAngle() == degrees) {
                    return direction;
                }
            }
        } else {
            if (angle == 180) return SOUTH;
            if (angle == 135) return SOUTHWEST;
            else {
                Direction[] directions = Direction.values();
                for (Direction direction:directions) {
                    if (direction.getAngle() == angle) {
                        return direction;
                    }
                }
            }
        }
        return NONE;
    }

    public Direction combine(Direction other){

        if (this == Direction.NONE ){
            return other;
        }
        if (other == null || other == Direction.NONE){
            return this;
        }
        int x;
        int y;
        x = other.getDx() + this.getDx();
        y = this.getDy() + other.getDy();

        int nx = edit(x);
        int ny = edit(y);
        Direction direction1 = Direction.NONE;
        Direction[] directions=Direction.values();
        for (Direction direction : directions) {
            if (direction.getDx() == nx && direction.getDy() == ny ){
                direction1 = direction;
            }
        }
        return direction1;
    }

    private int edit(int value){
        if (value >0) {
            return 1;
        } else {
            if (value <0) return -1;
            else return 0;
        }

    }
}
