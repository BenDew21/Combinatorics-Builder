package uk.co.beniodev.combinatoricsbuilder.controls;

import javafx.util.Pair;

public class ANDGate extends BaseGate {

    public ANDGate() {
        super("/AND.png");
    }

    @Override
    protected void registerOffsets() {
        inputWireOffsets.put(1, new Pair<>(0.0, 10.0));
        inputWireOffsets.put(2, new Pair<>(0.0, 30.0));
        outputWireOffsets.put(1, new Pair<>(60.0, 20.0));
    }

    @Override
    public void calculateOutput() {
        if (inputWires.get(1).getStatus() && inputWires.get(2).getStatus()) {
            outputWires.get(1).toggleStatus(true);
        } else {
            outputWires.get(1).toggleStatus(false);
        }
    }
}
