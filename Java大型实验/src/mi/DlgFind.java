package mi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.Vector;

class DlgFind extends JDialog
{
  private static final long serialVersionUID = 1L;
  private static final String[] s = { "姓名", "性别", "通讯地址", "工作单位", "邮政编码", "家庭电话号码", "办公室电话号码", "传真号码", "移动电话号码", "Email", "QQ号或MSN", "出生日期", "备注" };

  private JLabel[] labels = new JLabel[13];
  private JTextField[] texts = new JTextField[13];
  private JButton btnFind = new JButton("查找");
  private Vector<MyPair> vResult = new Vector();

  private int objCol = -1;
  private int ip = -1;
  private addresslist p;
  private Vector<Vector<String>> vvs;
  private Vector<Vector<String>> dvvs = new Vector();

  private boolean tellEmpty(String paramString)
  {
    if ((paramString == null) || (paramString.equals(""))) {
      return true;
    }
    return false;
  }

  private int getMatchValue(String paramString, int paramInt1, int paramInt2)
  {
    String str = (String)((Vector)this.vvs.elementAt(paramInt1)).elementAt(paramInt2);
    int[][] arrayOfInt = new int[paramString.length() + 1][str.length() + 1];
    for (int i = 0; i < paramString.length(); i++)
      for (int j = 0; j < str.length(); j++) {
        arrayOfInt[(i + 1)][(j + 1)] = Math.max(arrayOfInt[i][(j + 1)], arrayOfInt[(i + 1)][j]);
        if (paramString.charAt(i) == str.charAt(j))
          arrayOfInt[(i + 1)][(j + 1)] = Math.max(arrayOfInt[(i + 1)][(j + 1)], arrayOfInt[i][j] + 1);
      }
    return arrayOfInt[paramString.length()][str.length()];
  }

  private void performFind() {
    this.objCol = -1;
    for (int i = 0; i < this.texts.length; i++)
      this.texts[i].setText(this.texts[i].getText().trim());
    for (int i = 0; i < this.texts.length; i++)
      if (!tellEmpty(this.texts[i].getText())) {
        this.objCol = (i + 1);
        break;
      }
    if (this.objCol == -1) {
      JOptionPane.showMessageDialog(this, "请输入要查找的信息！", "通讯录", 1);

      return;
    }
    this.vvs = this.p.getData();
    this.dvvs.clear();
    this.vResult.clear();
    for (int i = 0; i < this.vvs.size(); i++) {
      int j = 0;
      for (int k = 0; k < this.texts.length; k++)
        if (!tellEmpty(this.texts[k].getText()))
          j += getMatchValue(this.texts[k].getText(), i, k + 1);
      if (j > 0)
        this.vResult.add(new MyPair(i, j));
    }
    Collections.sort(this.vResult);
    for (int i = 0; i < this.vResult.size(); i++)
      this.dvvs.add(this.vvs.elementAt(((MyPair)this.vResult.elementAt(i)).rid));
    if (this.vResult.size() > 0)
      new addresslist(this, "查找结果", this.dvvs, this.vResult);
    else
      JOptionPane.showMessageDialog(this, "没有找到匹配的记录。", getTitle(), 1);
  }

  public DlgFind(addresslist paramMyComunity)
  {
    super(paramMyComunity, "查找");
    this.p = paramMyComunity;
    setModal(true);
    setSize(230, 460);
    setLocationRelativeTo(paramMyComunity);
    setResizable(false);
    setLayout(new FlowLayout());
    Container localContainer = getContentPane();
    for (int i = 0; i < this.labels.length; i++) {
      this.labels[i] = new JLabel(s[i]);
      this.texts[i] = new JTextField();
    }
    for (int i = 0; i < this.labels.length; i++) {
      this.labels[i].setPreferredSize(new Dimension(100, 25));
      this.texts[i].setPreferredSize(new Dimension(100, 25));
      localContainer.add(this.labels[i]);
      localContainer.add(this.texts[i]);
      if (i + 1 != this.texts.length) {
        final int j = i;
        this.texts[j].addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            DlgFind.this.texts[(j + 1)].requestFocus();
          }
        });
      }
    }
    this.texts[(this.texts.length - 1)].addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
        DlgFind.this.performFind();
      }
    });
    this.btnFind.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
        DlgFind.this.performFind();
      }
    });
    localContainer.add(this.btnFind);
    addWindowListener(new WindowAdapter() {
      public void windowOpened(WindowEvent paramAnonymousWindowEvent) {
        DlgFind.this.texts[0].requestFocus();
      }
    });
    setVisible(true);
  }
}