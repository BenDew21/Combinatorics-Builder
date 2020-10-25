package uk.co.beniodev.combinatoricsbuilder.controls;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import uk.co.beniodev.combinatoricsbuilder.utilities.WireStatus;

import java.util.ArrayList;
import java.util.List;

public class Terminal extends BaseControl {
    private Circle uiControl;
    private boolean isEnabled;
    private List<Wire> wires = new ArrayList<>();

    private double centreX, centreY;

    public Terminal() {

    }

    public void setCentre(double x, double y) {
        centreX = x;
        centreY = y;
    }

    public Circle getControl() {
        uiControl = new Circle();
        uiControl.setCenterX(centreX);
        uiControl.setCenterY(centreY);
        uiControl.setRadius(6);
        uiControl.setStroke(Color.RED);
        uiControl.setFill(Color.RED);
        uiControl.setStrokeWidth(0.5);
        uiControl.setOnMouseClicked(event -> {
            OnClick(event);
        });
        return uiControl;
    }

    public void OnClick(MouseEvent event) {
        if (WireStatus.getInstance().getSelected()) {
            if (WireStatus.getInstance().getWire() == null) {
                Wire wire = new Wire();
                wire.setStart(centreX, centreY);
                this.wires.add(wire);
                WireStatus.getInstance().setWire(wire);
            } else {
                WireStatus.getInstance().getWire().setEnd(centreX, centreY);
            }
        } else {
            isEnabled = !isEnabled;
            if (isEnabled) {
                uiControl.setStroke(Color.GREEN);
                uiControl.setFill(Color.GREEN);
            } else {
                uiControl.setStroke(Color.RED);
                uiControl.setFill(Color.RED);
            }

            for (Wire wire : this.wires) {
                wire.toggleStatus(isEnabled);
            }
        }
    }

    public void registerWire(Wire wire) {
        this.wires.add(wire);
    }

    public void bringToFront() {
        uiControl.toFront();
    }

    @Override
    public boolean MouseDown(MouseEvent event, Wire wire) {
        if (wire == null) {
            wire = new Wire();
            wire.setStart(centreX, centreY);
            return false;
        } else {
            wire.setEnd(centreX, centreY);
            return true;
        }
    }
}
