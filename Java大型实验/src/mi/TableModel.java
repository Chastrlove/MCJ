package mi;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

class TableModel extends DefaultTableModel
{
  private static final long serialVersionUID = 1L;

  public TableModel(Vector<Vector<String>> paramVector, Vector<String> paramVector1)
  {
    super(paramVector, paramVector1);
  }
  public boolean isCellEditable(int paramInt1, int paramInt2) {
    return false;
  }
}