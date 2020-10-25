package uk.co.beniodev.combinatoricsbuilder.controls;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import uk.co.beniodev.combinatoricsbuilder.utilities.WireStatus;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseGate implements IWireObserver {

    protected WireStatus wireStatus = WireStatus.getInstance();

    private Pane pane;
    private ImageView uiControl;

    // Wires
    protected Map<Integer, Wire> inputWires = new HashMap<>();
    protected Map<Integer, Wire> outputWires = new HashMap<>();

    // Wire Pixel Offsets
    protected Map<Integer, Pair<Double, Double>> inputWireOffsets = new HashMap<>();
    protected Map<Integer, Pair<Double, Double>> outputWireOffsets = new HashMap<>();

    private ContextMenu inputMenu = new ContextMenu();
    private ContextMenu outputMenu = new ContextMenu();

    protected abstract void registerOffsets();

    public BaseGate(String path) {
        registerOffsets();
        createInputMenu();
        createOutputMenu();
        createControl(path);
    }

    private void createControl(String path) {
        pane = new Pane();
        Image image = new Image(getClass().getResourceAsStream(path));
        uiControl = new ImageView();
        uiControl.setImage(image);
        uiControl.setFitWidth(60);
        uiControl.setFitHeight(40);

        pane.setPrefWidth(60);
        pane.setPrefHeight(40);
        pane.setStyle("-fx-background-color: white;");
        pane.getChildren().add(uiControl);

        uiControl.setOnMouseClicked(event -> {
            System.out.println("Clicked");
            if (wireStatus.getSelected()) {
                if (wireStatus.getWire() == null) {
                    outputMenu.show(pane, event.getScreenX(), event.getScreenY());
                } else {
                    inputMenu.show(pane, event.getScreenX(), event.getScreenY());
                }
            }
        });

        pane.setOnMouseClicked(event -> {
            System.out.println("Clicked");
            if (wireStatus.getSelected()) {
                if (wireStatus.getWire() == null) {
                    outputMenu.show(pane, event.getScreenX(), event.getScreenY());
                } else {
                    inputMenu.show(pane, event.getScreenX(), event.getScreenY());
                }
            }
        });
    }

    /**
     * Generate the menu for input wire (existing wire)
     */
    protected void createInputMenu() {
        for (Integer inputWire : inputWireOffsets.keySet()) {
            MenuItem menuItem = new MenuItem("Input " + inputWire.toString());
            menuItem.setOnAction(event -> {
                Pair<Double, Double> pair = inputWireOffsets.get(inputWire);
                registerInputWire(inputWire, WireStatus.getInstance().getWire());
                wireStatus.setEnd(pane.getLayoutX() + pair.getKey(), pane.getLayoutY() + pair.getValue());
            });
            inputMenu.getItems().add(menuItem);
        }
    }

    /**
     * Generate the menu for output wire (new wire)
     */
    protected void createOutputMenu() {
        for (Integer outputWire : outputWireOffsets.keySet()) {
            MenuItem menuItem = new MenuItem("Output " + outputWire.toString());
            menuItem.setOnAction(event -> {
                Pair<Double, Double> pair = outputWireOffsets.get(outputWire);
                wireStatus.setStart(pane.getLayoutX() + pair.getKey(), pane.getLayoutY() + pair.getValue());
                registerOutputWire(outputWire, wireStatus.getWire());
                wireStatus.getWire().updateEndGraphically();
            });
            outputMenu.getItems().add(menuItem);
        }
    }

    protected void registerInputWire(int port, Wire wire) {
        wire.registerOutput(this);
        inputWires.put(port, wire);
    }

    protected void registerOutputWire(int port, Wire wire) {
        outputWires.put(port, wire);
    }

    public Pane getControl() {
        return this.pane;
    }

    public void setPosition(double x, double y) {
        pane.setLayoutX(x);
        pane.setLayoutY(y);
    }

    @Override
    public void WireStatusChanged(Wire wire, boolean status) {
        calculateOutput();
    }

    public abstract void calculateOutput();
}
