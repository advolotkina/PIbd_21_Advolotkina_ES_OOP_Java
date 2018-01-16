/**
 * Created by Ekaterina Advolotkina on 16.01.2018.
 */
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;

import javax.swing.JLabel;

public class DragLabel extends JLabel implements DragGestureListener, DragSourceListener {

    DragSource dragSource;

    public DragLabel(String text) {
        super(text);
        dragSource = new DragSource();
        dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY_OR_MOVE, this);
    }

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {
    }

    @Override
    public void dragEnter(DragSourceDragEvent dsde) {
    }

    @Override
    public void dragExit(DragSourceEvent dse) {
    }

    @Override
    public void dragOver(DragSourceDragEvent dsde) {
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        Transferable transfer = new StringSelection(getText());
        dragSource.startDrag(dge, DragSource.DefaultCopyDrop, transfer, this);

    }

}
