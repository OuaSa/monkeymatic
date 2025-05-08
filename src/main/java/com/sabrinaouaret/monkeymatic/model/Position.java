package com.sabrinaouaret.monkeymatic.model;

import com.sabrinaouaret.monkeymatic.utils.Direction;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Position {

    int x;
    int y;
    Direction direction;
    private final List<PositionListener> listeners = new ArrayList<>();

    public Position(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void addListener(PositionListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (PositionListener listener : listeners) {
            listener.onPositionChanged(this);
        }
    }

    public void setX(int x) {
        this.x = x;
        notifyListeners();
    }

    public void setY(int y) {
        this.y = y;
        notifyListeners();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        notifyListeners();
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y + ", " + direction +")";
    }
}
