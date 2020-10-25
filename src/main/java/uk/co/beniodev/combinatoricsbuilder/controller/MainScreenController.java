package uk.co.beniodev.combinatoricsbuilder.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Pair;
import uk.co.beniodev.combinatoricsbuilder.controls.*;
import uk.co.beniodev.combinatoricsbuilder.utilities.ControlEventHandler;
import uk.co.beniodev.combinatoricsbuilder.utilities.WireStatus;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainScreenController {

    /** Width of the squares in the backing grid **/
    private int squareWidth = 10;

    /** Background Pane **/
    @FXML
    private Pane paneBackground;

    /** Checkbox for drawing wires **/
    @FXML
    private CheckBox checkboxWire;

    @FXML
    private Pane paneNOTGate;

    EventHandler controlHandler = new EventHandler<MouseEvent>() {
        boolean isAdded = false;
        NOTGate gate = new NOTGate();

        @Override
        public void handle(MouseEvent event) {
            Pair<Double, Double> snap = calculateSnap(event.getX(), event.getY());
            gate.setPosition(snap.getKey(), snap.getValue());
            if (!isAdded) {
                paneBackground.getChildren().add(gate.getControl());
                isAdded = true;
            }
        }
    };

    /**
     * Initialise the view
     */
    @FXML
    private void initialize() {
        generateBackground();

        WireStatus.getInstance().setPaneInstance(paneBackground);

        paneBackground.setOnMouseMoved(event -> {
            if (WireStatus.getInstance().getSelected()) {
                if (WireStatus.getInstance().getWire() != null) {
                    Wire wire = WireStatus.getInstance().getWire();
                    Pair<Double, Double> snappedValues = this.calculateSnap(event.getX(), event.getY());

                    wire.setEnd(snappedValues.getKey(), snappedValues.getValue());
                    wire.updateEndGraphically();
                }
            }
        });

        checkboxWire.selectedProperty().addListener((observable, oldValue, newValue) -> {
            WireStatus.getInstance().setSelected(newValue);
        });

        Terminal terminal = new Terminal();
        terminal.setCentre(20, 80);
        paneBackground.getChildren().add(terminal.getControl());

        Terminal terminal2 = new Terminal();
        terminal2.setCentre(20, 100);
        paneBackground.getChildren().add(terminal2.getControl());

        ANDGate andGate = new ANDGate();
        andGate.setPosition(60, 70);
        paneBackground.getChildren().add(andGate.getControl());

        Output out = new Output();
        Pane pane = out.getControl();
        paneBackground.getChildren().add(pane);
        pane.setLayoutX(160 - 10);
        pane.setLayoutY(70 + 10);

        paneNOTGate.setOnMouseClicked(event -> {
            NOTGate gate = new NOTGate();

            ControlEventHandler handler = new ControlEventHandler(paneBackground, gate);
            paneBackground.addEventHandler(MouseEvent.MOUSE_MOVED, handler);

            paneBackground.setOnMouseClicked(event1 -> {
                paneBackground.removeEventHandler(MouseEvent.MOUSE_MOVED, handler);
            });
        });
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

    /**
     * Generate the background grid
     */
    private void generateBackground() {
        double width = 700;
        double height = 460;

        for(int i=10; i<=width-10; i+=squareWidth){
            for(int j=10; j<=height-10; j+=squareWidth){
                Circle circ = new Circle();
                circ.setCenterX(i);
                circ.setCenterY(j);
                circ.setRadius(1);
                circ.setStroke(Color.LIGHTGRAY);
                circ.setFill(Color.LIGHTGRAY);
                circ.setStrokeWidth(0.5);
                paneBackground.getChildren().add(circ);
                circ.toBack();
            }
        }
    }
}
