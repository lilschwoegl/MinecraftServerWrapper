/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.awt.datatransfer.DataFlavor;
import javax.swing.JPanel;

/**
 *
 * @author Mike
 */
public class PanelDataFlavor extends DataFlavor {

    // This saves me having to make lots of copies of the same thing
    public static final PanelDataFlavor SHARED_INSTANCE = new PanelDataFlavor();

    public PanelDataFlavor() {

        super(JPanel.class, null);

    }

}
