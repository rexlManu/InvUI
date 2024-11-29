package xyz.xenondevs.invui.gui.structure;

import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.gui.ScrollGui;
import xyz.xenondevs.invui.gui.TabGui;

/**
 * Contains markers
 */
public class Markers {
    
    /**
     * The marker for horizontal content list slots in {@link PagedGui PagedGuis},
     * {@link ScrollGui ScrollGuis} and {@link TabGui TabGuis}
     */
    public static final Marker CONTENT_LIST_SLOT_HORIZONTAL = new Marker(true);
    
    /**
     * The marker for vertical content list slots in {@link PagedGui PagedGuis},
     * {@link ScrollGui ScrollGuis} and {@link TabGui TabGuis}
     */
    public static final Marker CONTENT_LIST_SLOT_VERTICAL = new Marker(false);
    
}
