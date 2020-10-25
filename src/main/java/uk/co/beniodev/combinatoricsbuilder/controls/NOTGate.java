package uk.co.beniodev.combinatoricsbuilder.controls;

import javafx.util.Pair;

public class NOTGate extends BaseGate {

    public NOTGate() {
        super("/NOT.png");
    }

    @Override
    protected void registerOffsets() {
        inputWireOffsets.put(1, new Pair<>(0.0, 20.0));
        outputWireOffsets.put(1, new Pair<>(60.0, 20.0));
    }

    @Override
    public void calculateOutput() {
        boolean input = inputWires.get(1).getStatus();
        outputWires.get(1).toggleStatus(!input);
    }
}
