package com.sabrinaouaret.monkeymatic.model;


import com.sabrinaouaret.monkeymatic.utils.Direction;
import lombok.Data;

@Data
public class Tractor implements Drivable, PositionListener {
    final Position position;
    String appreciationComment;

    public Tractor(Position position) {
        this.position = position;
        this.position.addListener(this);
        updateComment();
    }

    @Override
    public void onPositionChanged(Position position) {
        updateComment();
    }

    private void updateComment() {
        if(position.getX() < 0 || position.getY() < 0){
            this.appreciationComment = "Oupsi, I fell. I'm out of the face of the Banana Earth.";
        } else {
            this.appreciationComment = "Wooot wooot! Yum, all these bananas!";
        }
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
        int currentIndex = Direction.directionsList.indexOf(position.getDirection());
        int size = Direction.directionsList.size();
        int newIndex = (currentIndex + delta + size) % size;
        position.setDirection(Direction.directionsList.get(newIndex));
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

}
