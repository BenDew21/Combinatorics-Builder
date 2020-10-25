package uk.co.beniodev.combinatoricsbuilder.utilities;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import uk.co.beniodev.combinatoricsbuilder.controls.BaseGate;

public class ControlEventHandler implements EventHandler<MouseEvent> {

    private Pane pane;
    private BaseGate gate;
    private boolean isAdded = false;
    private double squareWidth = 10;

    public ControlEventHandler(Pane pane, BaseGate gate) {
        this.pane = pane;
        this.gate = gate;
    }

    @Override
    public void handle(MouseEvent event) {
        Pair<Double, Double> snap = calculateSnap(event.getX(), event.getY());
        gate.setPosition(snap.getKey(), snap.getValue());
        if (!isAdded) {
            pane.getChildren().add(gate.getControl());
            isAdded = true;
        }
    }

    /**
     * Calculate the new line position to snap with the grid
     * @param x Cursor X position
     * @param y Cursor Y position
     * @return The snapped position
     */
    private Pair<Double, Double> calculateSnap(double x, double y) {
        double newX = 0, newY = 0;

        double xMod = x % squareWidth;
        double yMod = y % squareWidth;

        if (xMod < squareWidth) {
            newX = x - xMod;
        } else {
            newX = x - xMod + squareWidth;
        }

        if (yMod < squareWidth) {
            newY = y - yMod;
        } else {
            newY = y - yMod + squareWidth;
        }

        return new Pair<>(newX, newY);
    }
}
