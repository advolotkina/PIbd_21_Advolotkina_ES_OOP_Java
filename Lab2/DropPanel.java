/**
 * Created by Ekaterina Advolotkina on 16.01.2018.
 */
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import javax.swing.*;

public class DropPanel extends JPanel implements DropTargetListener {
    private IAnimal shark;

    public DropPanel() {
        new DropTarget(this, this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (shark != null) {
            shark.setPosition(90,30);
            shark.draw(g);
        }
    }

    public IAnimal GetShark() {
        return shark;
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        if (dtde.getTransferable().isDataFlavorSupported(DataFlavor.stringFlavor)) {
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
            if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                switch ((String) transferable.getTransferData(DataFlavor.stringFlavor)) {
                    case "Shark":
                        shark = new Shark(10, 10, 300, Color.BLACK);
                        break;
                    case "TigerShark":
                        shark = new TigerShark(10, 10, 300, Color.BLACK, Color.gray);
                        break;
                }
                repaint();
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
