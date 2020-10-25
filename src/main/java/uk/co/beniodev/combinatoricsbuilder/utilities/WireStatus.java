package uk.co.beniodev.combinatoricsbuilder.utilities;

import javafx.scene.layout.Pane;
import uk.co.beniodev.combinatoricsbuilder.controls.Wire;

public class WireStatus {
    private static WireStatus instance = null;

    private Pane paneInstance;

    private boolean isSelected = false;
    private Wire wire;

    public static WireStatus getInstance() {
        if (instance == null) instance = new WireStatus();
        return instance;
    }

    public void setPaneInstance(Pane pane) {
        this.paneInstance = pane;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Wire getWire() {
        return wire;
    }

    public void setWire(Wire wire) {
        this.wire = wire;
        if (wire != null) {
            paneInstance.getChildren().add(wire.getControl());
            wire.sendToBack();
        }
    }

    public void setStart(double x, double y) {
        this.wire = new Wire();
        wire.setStart(x, y);
        paneInstance.getChildren().add(wire.getControl());
        wire.sendToBack();
    }

    public void setEnd(double x, double y) {
        wire.setEnd(x, y);
        wire.updateEndGraphically();
        this.wire = null;
    }

}
