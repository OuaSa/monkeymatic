package com.sabrinaouaret.monkeymatic.model;


import com.sabrinaouaret.monkeymatic.utils.Direction;
import lombok.Data;

@Data
public class Tractor implements Drivable, PositionListener {

    public static final String ERROR_APPRECIATION_COMMENT = "Oupsi, I fell. I'm out of the face of the Banana Earth.";
    public static final String KEEP_GOING_APPRECIATION_COMMENT = "Wooot wooot! Yum, all these bananas!";

    final Position position;
    String appreciationComment;

    public Tractor(Position position) {
        this.position = position;
        this.position.addListener(this);
        updateComment();
    }

    @Override
    public void turnLeft() {
        rotate(-1);
    }

    @Override
    public void turnRight() {
        rotate(1);
    }

    private void rotate(int delta) {
        int currentIndex = Direction.directionsListOrder.indexOf(position.getDirection());
        int size = Direction.directionsListOrder.size();
        int newIndex = (currentIndex + delta + size) % size;
        position.setDirection(Direction.directionsListOrder.get(newIndex));
    }

    @Override
    public void moveForward() {
        switch (position.getDirection()) {
            case N -> position.setY(position.getY() + 1);
            case S -> position.setY(position.getY() - 1);
            case E -> position.setX(position.getX() + 1);
            case O -> position.setX(position.getX() - 1);
        }
    }

    @Override
    public void onPositionChanged(Position position) {
        updateComment();
    }

    private void updateComment() {
        if(position.getX() < 0 || position.getY() < 0){
            this.appreciationComment = ERROR_APPRECIATION_COMMENT;
        } else {
            this.appreciationComment = KEEP_GOING_APPRECIATION_COMMENT;
        }
    }

}
