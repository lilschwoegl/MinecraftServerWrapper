/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.awt.Component;
import java.awt.Container;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class DropHandler implements DropTargetListener {

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {

        // Determine if can actual process the contents comming in.
        // You could try and inspect the transferable as well, but 
        // There is an issue on the MacOS under some circumstances
        // where it does not actually bundle the data until you accept the
        // drop.
        if (dtde.isDataFlavorSupported(PanelDataFlavor.SHARED_INSTANCE)) {

            dtde.acceptDrag(DnDConstants.ACTION_MOVE);

        } else {

            dtde.rejectDrag();

        }

    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {

        boolean success = false;

        // Basically, we want to unwrap the present...
        if (dtde.isDataFlavorSupported(PanelDataFlavor.SHARED_INSTANCE)) {

            Transferable transferable = dtde.getTransferable();
            try {

                Object data = transferable.getTransferData(PanelDataFlavor.SHARED_INSTANCE);
                if (data instanceof JPanel) {

                    JPanel panel = (JPanel) data;

                    DropTargetContext dtc = dtde.getDropTargetContext();
                    Component component = dtc.getComponent();

                    if (component instanceof JComponent) {

                        Container parent = panel.getParent();
                        if (parent != null) {

                            parent.remove(panel);

                        }

                        ((JComponent)component).add(panel);

                        success = true;
                        dtde.acceptDrop(DnDConstants.ACTION_MOVE);

                        panel.invalidate();
                        panel.repaint();

                    } else {

                        success = false;
                        dtde.rejectDrop();

                    }

                } else {

                    success = false;
                    dtde.rejectDrop();

                }

            } catch (Exception exp) {

                success = false;
                dtde.rejectDrop();
                exp.printStackTrace();

            }

        } else {

            success = false;
            dtde.rejectDrop();

        }

        dtde.dropComplete(success);

    }

}
