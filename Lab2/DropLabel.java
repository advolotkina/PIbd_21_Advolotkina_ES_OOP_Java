/**
 * Created by Ekaterina Advolotkina on 16.01.2018.
 */
import java.awt.Color;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import javax.swing.*;

public class DropLabel extends JLabel implements DropTargetListener {

    public DropLabel(String s) {
        super(s);
        new DropTarget(this, this);
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        if (dtde.getTransferable().isDataFlavorSupported(ColorData.colorFlavor)) {
            dtde.acceptDrag(DnDConstants.ACTION_COPY);
        } else {
            dtde.acceptDrag(DnDConstants.ACTION_NONE);
        }
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        try {
            Transferable transferable = dtde.getTransferable();
            if (transferable.isDataFlavorSupported(ColorData.colorFlavor)) {
                Color color = (Color) transferable.getTransferData(ColorData.colorFlavor);
                setBackground(color);
                IAnimal shark = ((DropPanel) getLabelFor()).GetShark();
                if (shark != null)
                    if (getText() == "MainColor") {
                        ((Shark) shark).setBodyPattern(color);
                    } else if (getText() == "DopColor") {
                        if (shark instanceof TigerShark) {
                            ((TigerShark) shark).setDopColor(color);
                        }
                    }
                if (getLabelFor() instanceof JPanel) {
                    ((JPanel) getLabelFor()).repaint();
                }

            } else {
                dtde.rejectDrop();
            }
        } catch (Exception e) {
            dtde.rejectDrop();
        }
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

}
