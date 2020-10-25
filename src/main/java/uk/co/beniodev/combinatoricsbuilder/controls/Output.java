package uk.co.beniodev.combinatoricsbuilder.controls;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import uk.co.beniodev.combinatoricsbuilder.utilities.WireStatus;

public class Output implements IWireObserver {

    private Pane pane;
    private Circle uiControl;
    private Text outputControl;

    private boolean isEnabled;

    private Wire input;

    private double centreX, centreY;

    public void setPosition(double x, double y) {
        centreX = x;
        centreY = y;
    }

    public Pane getControl() {
        pane = new Pane();
        pane.setPrefWidth(20);
        pane.setPrefHeight(20);

        uiControl = new Circle();
        uiControl.setCenterX(10);
        uiControl.setCenterY(10);
        uiControl.setRadius(10);
        uiControl.setStroke(Color.BLACK);
        uiControl.setFill(Color.WHITE);
        uiControl.setStrokeWidth(0.5);

        outputControl = new Text();
        outputControl.setText("0");
        outputControl.setLayoutX(7);
        outputControl.setLayoutY(14);
        pane.getChildren().add(uiControl);
        pane.getChildren().add(outputControl);

        pane.setOnMouseClicked(event -> {
            MouseDown(event);
        });

        return pane;
    }

    public void bringToFront() {
        pane.toFront();
    }

    public void setInput(Wire wire) {
        this.input = wire;
    }

    public void toggleEnabled(boolean enabled) {
        if (enabled) {
            outputControl.setText("1");
        } else {
            outputControl.setText("0");
        }
    }

    @Override
    public void WireStatusChanged(Wire wire, boolean status) {
        toggleEnabled(status);
    }

    private void MouseDown(MouseEvent event) {
        if (WireStatus.getInstance().getSelected()) {
            if (WireStatus.getInstance().getWire() != null) {
                Wire wire = WireStatus.getInstance().getWire();
                wire.setEnd(centreX, centreY);
                wire.registerOutput(this);
                WireStatus.getInstance().setWire(null);
            }
        }
    }
}
