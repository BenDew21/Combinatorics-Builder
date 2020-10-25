package uk.co.beniodev.combinatoricsbuilder.controls;

/**
 * IWireable - Interface for all elements which can be connected via a wire
 */
public interface IWireable {
    /**
     * Select the node with Draw Wire selected
     * @param wire The wire to draw, or null if new wire should be created
     * @return false if the wire should end, true if not
     */
    boolean selectedWithWire(Wire wire);
}
