package mi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

class DlgAddModify extends JDialog
{
  private static final long serialVersionUID = 1L;
  private static final String[] s = { "序号", "姓名(不能为空)", "性别(不能为空)", "通讯地址", "工作单位", "邮政编码", "家庭电话号码", "办公室电话号码", "传真号码", "移动电话号码", "Email", "QQ号或MSN", "出生日期", "备注" };

  private JLabel[] labels = new JLabel[14];
  private JTextField[] texts = new JTextField[14];
  private JButton btnOK = new JButton("确定"); private JButton btnCancel = new JButton("取消");
  Vector<String> result = null;

  public Vector<String> getResult() {
    return this.result;
  }

  private boolean performOK() {
    this.result = new Vector();
    for (int i = 0; i < s.length; i++)
      this.result.add(this.texts[i].getText());
    try {
    	addresslist.detrans(this.result);
      return true;
    } catch (MyException localMyException) {
      JOptionPane.showMessageDialog(this, localMyException.getMessage(), getTitle(), 0);
      this.result = null;
      this.texts[localMyException.wm].requestFocus();
    }return false;
  }

  public DlgAddModify(addresslist paramMyComunity, Vector<String> paramVector, String paramString)
  {
    super(paramMyComunity, paramString);
    setModal(true);
    setSize(230, 500);
    setResizable(false);
    setLayout(new FlowLayout());
    setLocationRelativeTo(paramMyComunity);
    Container localContainer = getContentPane();
    for (int i = 0; i < s.length; i++) {
      this.labels[i] = new JLabel(s[i]);
      if (paramVector != null)
        this.texts[i] = new JTextField((String)paramVector.elementAt(i));
      else
        this.texts[i] = new JTextField();
      this.labels[i].setPreferredSize(new Dimension(100, 25));
      this.texts[i].setPreferredSize(new Dimension(100, 25));
      localContainer.add(this.labels[i]);
      localContainer.add(this.texts[i]);
    }
    for (int i = 1; i < s.length - 1; i++) {
      final int j = i;
      this.texts[j].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
          DlgAddModify.this.texts[(j + 1)].requestFocus();
        }
      });
    }
    this.texts[13].addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
        if (DlgAddModify.this.performOK())
          DlgAddModify.this.dispose();
      }
    });
    this.texts[5].addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent paramAnonymousKeyEvent) {
        if ((paramAnonymousKeyEvent.getKeyChar() < '0') || (paramAnonymousKeyEvent.getKeyChar() > '9'))
          paramAnonymousKeyEvent.consume();
      }
    });
    for (int i = 6; i < 9; i++)
      this.texts[i].addKeyListener(new KeyAdapter() {
        public void keyTyped(KeyEvent paramAnonymousKeyEvent) {
          if (((paramAnonymousKeyEvent.getKeyChar() < '0') || (paramAnonymousKeyEvent.getKeyChar() > '9')) && (paramAnonymousKeyEvent.getKeyChar() != '+') && (paramAnonymousKeyEvent.getKeyChar() != '-'))
          {
            paramAnonymousKeyEvent.consume();
          }
        } } );
    this.texts[9].addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent paramAnonymousKeyEvent) {
        if (((paramAnonymousKeyEvent.getKeyChar() < '0') || (paramAnonymousKeyEvent.getKeyChar() > '9')) && (paramAnonymousKeyEvent.getKeyChar() != '+'))
          paramAnonymousKeyEvent.consume();
      }
    });
    this.texts[0].setEditable(false);
    localContainer.add(this.btnOK);
    this.btnOK.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
        if (DlgAddModify.this.performOK())
          DlgAddModify.this.dispose();
      }
    });
    localContainer.add(this.btnCancel);
    this.btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
        DlgAddModify.this.dispose();
      }
    });
    addWindowListener(new WindowAdapter() {
      public void windowOpened(WindowEvent paramAnonymousWindowEvent) {
        DlgAddModify.this.texts[1].requestFocus();
      }
    });
    setVisible(true);
  }
}