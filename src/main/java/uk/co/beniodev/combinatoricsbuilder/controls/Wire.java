package uk.co.beniodev.combinatoricsbuilder.controls;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Wire {

    private Line line;
    private IWireObserver output;

    private double x1, x2, y1, y2;
    private boolean status;

    public void setStart(double x, double y) {
        x1 = x;
        y1 = y;
    }

    public void setEnd(double x, double y) {
        x2 = x;
        y2 = y;
    }

    public void updateEndGraphically() {
        line.setEndX(x2);
        line.setEndY(y2);
    }

    public Line getControl() {
        line = new Line();
        line.setStroke(Color.BLACK);
        line.setStartX(x1);
        line.setEndX(x2);
        line.setStartY(y1);
        line.setEndY(y2);

        line.setStrokeWidth(3);
        return line;
    }

    public void sendToBack() {
        line.toBack();
    }

    public void toggleStatus(boolean status) {
        this.status = status;
        if (status) {
            line.setStroke(Color.GREEN);
        } else {
            line.setStroke(Color.BLACK);
        }
        if (output != null)  output.WireStatusChanged(this, status);
    }

    public boolean getStatus() {
        return status;
    }

    public void registerOutput(IWireObserver observer) {
        this.output = observer;
    }
}
