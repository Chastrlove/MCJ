package mi;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

class addresslist extends JDialog
{
  private static final long serialVersionUID = 1L;
  public static final String[][] orgData = { { "1", "����", "��", "�㽭ʡ������Ϫ��", "�㽭��ҵ��ѧ", "310000", "0571-88888880", "", "", "157000083170", "1935036014@qq.com", "1935036014", "1995-08-15", ""},
                                             { "2", "������", "��", "ɽ��ʡ̫ԭ��", "�㽭��ҵ��ѧ", "310000", "0571-88888881", "", "", "15700083171", "354996162@qq.com", "354996162", "1995-6-12", ""},
                                             { "3", "����", "��", "�㽭ʡ������", "�㽭��ҵ��ѧ", "310000", "0571-88888882", "", "", "15700083172", "309272985@qq.com", "309272985", "1995-9-06", ""},
                                             { "4", "¬�λ�", "��", "�㽭ʡ������", "�㽭��ҵ��ѧ", "310000", "0571-88888883", "", "", "15700083173", "457607792@qq.com", "457607792", "1995-7-26", ""}};

  private static final String[] s = { "���", "����", "�Ա�", "ͨѶ��ַ", "������λ", "��������", "��ͥ�绰����", "�칫�ҵ绰����", "�������", "�ƶ��绰����", "Email", "QQ�Ż�MSN", "��������", "��ע" };

  private static final Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}");
  private static final int[] sizes = { 40, 80, 40, 120, 100, 70, 100, 100, 100, 100, 150, 100, 80, 100 };

  private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

  private Vector<String> colName = new Vector();
  private Vector<Vector<String>> info = new Vector();
  private Vector<ComunityItem> vit = new Vector();
  private String fName;
  private JTable table;
  private DefaultTableModel dtm; //һ��Vector
  private JButton btnAdd;
  private JButton btnModify;
  private JButton btnDel;
  private JButton btnFind;
  private JButton btnClose;
  private JLabel rlab;
  private Vector<MyPair> rvec;

  @SuppressWarnings("unchecked")
static Vector<String> trans(ComunityItem paramComunityItem) //���Զ����ComunityItem����Ϊ�β�
  {                                                         //ʵ����һ�������캯����ȫתΪString����
    @SuppressWarnings("rawtypes")
	Vector localVector = new Vector();
    localVector.add(new Integer(paramComunityItem.nId).toString());
    localVector.add(paramComunityItem.sName);
    localVector.add(paramComunityItem.byteSex == 1 ? "��" : "Ů");
    localVector.add(paramComunityItem.sAddress);
    localVector.add(paramComunityItem.sCompany);
    localVector.add(paramComunityItem.sPostalCode);
    localVector.add(paramComunityItem.sHomeTele);
    localVector.add(paramComunityItem.sOfficeTele);
    localVector.add(paramComunityItem.sFax);
    localVector.add(paramComunityItem.sCellPhone);
    localVector.add(paramComunityItem.sEmail);
    localVector.add(paramComunityItem.sInstantMessager);
    if (paramComunityItem.dateBirthday != null)
      localVector.add(df.format(paramComunityItem.dateBirthday));
    else
      localVector.add("");
    localVector.add(paramComunityItem.sMemo);
    return localVector;  //����StringΪԪ�����͵�����
  }

  static ComunityItem detrans(Vector<String> paramVector) throws MyException { //ͨѶ¼��������������//����������ComunityItem
    ComunityItem localComunityItem = new ComunityItem();                       //ʵ����һ���򿽱���������StringȫתΪԭ������
    localComunityItem.nId = Integer.parseInt((String)paramVector.elementAt(0));
    String str = (String)paramVector.elementAt(1);
    if ((str != null) && (!str.trim().equals("")))
      localComunityItem.sName = str;
    else
      throw new MyException("��������Ϊ�գ�", 1);
    if (((String)paramVector.elementAt(2)).equals("��"))
      localComunityItem.byteSex = 1;
    else if (((String)paramVector.elementAt(2)).equals("Ů"))
      localComunityItem.byteSex = 0;
    else
      throw new MyException("�Ա��ֶ�ֻ�ܺ���\"��\"��\"Ů\"��", 2);
    localComunityItem.sAddress = ((String)paramVector.elementAt(3));
    localComunityItem.sCompany = ((String)paramVector.elementAt(4));
    localComunityItem.sPostalCode = ((String)paramVector.elementAt(5));
    localComunityItem.sHomeTele = ((String)paramVector.elementAt(6));
    localComunityItem.sOfficeTele = ((String)paramVector.elementAt(7));
    localComunityItem.sFax = ((String)paramVector.elementAt(8));
    localComunityItem.sCellPhone = ((String)paramVector.elementAt(9));
    localComunityItem.sEmail = ((String)paramVector.elementAt(10));
    if ((localComunityItem.sEmail != null) && (!localComunityItem.sEmail.trim().equals("")) 
    		&& (!p.matcher(localComunityItem.sEmail).matches())) //������ʽ�����Ƿ�Ϸ�
      throw new MyException("Email��ַ�Ƿ���", 10);
    localComunityItem.sInstantMessager = ((String)paramVector.elementAt(11));
    if ((paramVector.elementAt(12) != null) && (!((String)paramVector.elementAt(12)).trim().equals("")))
      try { 
        localComunityItem.dateBirthday = df.parse((String)paramVector.elementAt(12));
      } catch (ParseException localParseException) {
        throw new MyException("���������ֶθ�ʽ����\n��ʽ����Ϊ yyyy-MM-dd��", 12);
      }
    else
      localComunityItem.dateBirthday = null;
    localComunityItem.sMemo = ((String)paramVector.elementAt(13));
    return localComunityItem;
  }

  Vector<Vector<String>> getData()
  {
    return this.info;  //�������ͨѶ¼�洢��������
  }

  private void getInfo() throws MyException {  //���
    File localFile = new File(this.fName);
    Object localObject1;
    if (!localFile.exists())
      try
      {
        localFile.createNewFile();  //���������򴴽���fName�������ļ�
        //�����ݶ����ļ���
        localObject1 = new ObjectOutputStream(new FileOutputStream(localFile));

        for (int i = 0; i < orgData.length; i++) {
          Vector localVector = new Vector();  //����һ��������
          for (int j = 0; j < orgData[i].length; j++)
            localVector.add(orgData[i][j]);   //��һ���û���ȫ����Ϣ��ӵ����µ�������
          ((ObjectOutputStream)localObject1).writeObject(detrans(localVector));
          this.info.add(localVector);  //������Ϣ����һ�����ӵ���������
        }
        ((ObjectOutputStream)localObject1).close();
      } catch (IOException localIOException1) {
        throw new MyException("д�ļ�����");
      }
    else
      try
      {
    	//���ļ������ݶ�����
        localObject1 = new ObjectInputStream(new FileInputStream(localFile));
        try
        {
          while (true)
          {
            Object localObject2 = ((ObjectInputStream)localObject1).readObject();
            if ((localObject2 instanceof ComunityItem))  //�ж��Ƿ���ComunityItem��Ķ���
              this.info.add(trans((ComunityItem)localObject2));  //�ӽ�ȥ
            else
              throw new MyException("�ļ���ʽ����"); 
          }
        } catch (EOFException localEOFException) { ((ObjectInputStream)localObject1).close(); }

      }
      catch (IOException localIOException2)
      {
        throw new MyException("���ļ�����");
      } catch (ClassNotFoundException localClassNotFoundException) {
        throw new MyException("�ļ���ʽ����");
      }
  }

  private void saveInfo()  //����
  {
    try {
    	//�����ݶ����ļ���
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(new FileOutputStream(this.fName));

      for (int i = 0; i < this.vit.size(); i++)  //ѭ������ÿһ������
        localObjectOutputStream.writeObject(this.vit.elementAt(i));
      localObjectOutputStream.close();
    } catch (IOException localIOException) {
      JOptionPane.showMessageDialog(this, "�ļ���д����", "ͨѶ¼", 0);
    }
  }

  private Dimension testInfo() {
    this.vit.clear();  //���ԭ��������
    for (int i = 0; i < this.info.size(); i++) {
      try {
        this.vit.add(detrans((Vector)this.info.elementAt(i))); //��info�Ķ���vit��
      } catch (MyException localMyException) {
        JOptionPane.showMessageDialog(this, "��" + i + "��" + localMyException.getMessage(), "ͨѶ¼", 0);

        return new Dimension(i, 2); //���ؿ��Ϊi��
      }
    }
    return null;
  }

  private void performQuit() {  //�˳�
    int i = JOptionPane.showConfirmDialog(this, "�˳�ͨѶ¼�����棿", "ͨѶ¼", 1);

    if (i == 1) {  //���ѡ��"��"
      dispose();
    } else if (i == 0) {
      Dimension localDimension = testInfo();
      if (localDimension != null) {
        this.table.grabFocus();
        this.table.changeSelection(localDimension.width, localDimension.height, false, false);
      } else {
        saveInfo();  //�����ر�
        dispose();
      }
    }
  }

  private void performAddModify(int paramInt)
  {
    int[] arrayOfInt = new int[14];
    for (int i = 0; i < sizes.length; i++)
      arrayOfInt[i] = this.table.getColumnModel().getColumn(i).getWidth();
    DlgAddModify localDlgAddModify;
    if (paramInt != -1) {
      localDlgAddModify = new DlgAddModify(this, (Vector)this.info.elementAt(paramInt), "�޸�ͨѶ¼");
      if (localDlgAddModify.getResult() != null) {
        this.info.setElementAt(localDlgAddModify.getResult(), paramInt);
        this.dtm.setDataVector(this.info, this.colName);
      }
    } else {
      ComunityItem localComunityItem = new ComunityItem();
      localComunityItem.nId = (this.info.size() + 1);
      Vector localVector = trans(localComunityItem);
      localVector.setElementAt("", 2);
      localDlgAddModify = new DlgAddModify(this, localVector, "���ͨѶ¼");
      if (localDlgAddModify.getResult() != null) {
        this.info.add(localDlgAddModify.getResult());
        this.dtm.setDataVector(this.info, this.colName);
      }
    }
    for (int j = 0; j < sizes.length; j++)
      this.table.getColumnModel().getColumn(j).setPreferredWidth(arrayOfInt[j]);
  }

  public addresslist(JFrame paramJFrame, String paramString) throws MyException {
    super(paramJFrame, "ͨѶ¼");  //�̳�
    setModal(true);  //��ʾ����
    setBounds(100, 100, 550, 300);  //ȷ��λ�����С
    setLayout(null);
    this.fName = paramString;
    getInfo();
    setDefaultCloseOperation(0);
    addWindowListener(new WindowAdapter() {  //�ر��������
      public void windowClosing(WindowEvent paramAnonymousWindowEvent) {
    	  addresslist.this.performQuit();
      }
    });
    for (int i = 0; i < s.length; i++)
      this.colName.add(s[i]);  //���˵��ӵ���һ��
    this.dtm = new TableModel(this.info, this.colName);
    this.table = new JTable(this.dtm);
    for (int i = 0; i < sizes.length; i++)
      this.table.getColumnModel().getColumn(i).setPreferredWidth(sizes[i]);
    this.table.setAutoResizeMode(0);  //����Ϊ�Զ�����ģʽ
    JScrollPane localJScrollPane = new JScrollPane(this.table);  //�������ڻ��������
    localJScrollPane.setBounds(0, 0, 545, 230);
    getContentPane().add(localJScrollPane);

    this.btnAdd = new JButton("���");
    this.btnAdd.setBounds(10, 240, 70, 25);
    this.btnAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
    	addresslist.this.performAddModify(-1);
        int i = addresslist.this.table.getRowCount();  //����
        addresslist.this.table.grabFocus();
        addresslist.this.table.changeSelection(i - 1, 0, false, false);
      }
    });
    getContentPane().add(this.btnAdd);

    this.btnModify = new JButton("�޸�");
    this.btnModify.setBounds(100, 240, 70, 25);
    this.btnModify.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
        int i = addresslist.this.table.getSelectedRow();
        if (i != -1)
           addresslist.this.performAddModify(i);
      }
    });
    getContentPane().add(this.btnModify);

    this.btnDel = new JButton("ɾ��");
    this.btnDel.setBounds(190, 240, 70, 25);
    this.btnDel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
        int i = addresslist.this.table.getSelectedRow();
        if (i != -1) {
          addresslist.this.dtm.removeRow(i);
          if (addresslist.this.table.getRowCount() > 0) {
        	addresslist.this.table.grabFocus();
        	addresslist.this.table.changeSelection(0, 0, false, false);
          }
          for (int j = 0; j < addresslist.this.table.getRowCount(); j++)
        	addresslist.this.dtm.setValueAt(new Integer(j + 1).toString(), j, 0);
          addresslist.this.info = addresslist.this.dtm.getDataVector();
        }
      }
    });
    getContentPane().add(this.btnDel);

    this.btnFind = new JButton("��ѯ");
    this.btnFind.setBounds(280, 240, 70, 25);
    final addresslist localMyComunity = this;
    this.btnFind.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
        new DlgFind(localMyComunity);
      }
    });
    getContentPane().add(this.btnFind);

    this.btnClose = new JButton("�ر�");
    this.btnClose.setBounds(450, 240, 70, 25);
    this.btnClose.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
    	 addresslist.this.performQuit();
      }
    });
    getContentPane().add(this.btnClose);

    setResizable(false);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public addresslist(JDialog paramJDialog, String paramString, Vector<Vector<String>> paramVector, Vector<MyPair> paramVector1)
  {
    super(paramJDialog, paramString);
    setModal(true);
    setBounds(100, 100, 550, 300);

    for (int i = 0; i < s.length; i++)
      this.colName.add(s[i]);
    this.dtm = new TableModel(paramVector, this.colName);
    this.table = new JTable(this.dtm);
    for (int i = 0; i < sizes.length; i++) {
      this.table.getColumnModel().getColumn(i).setPreferredWidth(sizes[i]);
    }
    this.table.setAutoResizeMode(0);
    this.table.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent paramAnonymousMouseEvent) {
        int i = addresslist.this.table.getSelectedRow();
        addresslist.this.rlab.setText(((MyPair)addresslist.this.rvec.elementAt(i)).val + "���ַ�ƥ��");
      }
    });
    JScrollPane localJScrollPane = new JScrollPane(this.table);
    getContentPane().add(localJScrollPane);
    this.rlab = new JLabel(((MyPair)paramVector1.elementAt(0)).val + "���ַ�ƥ�䡣");
    getContentPane().add(this.rlab, "South");
    this.rvec = paramVector1;
    setResizable(false);
    setLocationRelativeTo(paramJDialog);
    addWindowListener(new WindowAdapter() {
      public void windowOpened(WindowEvent paramAnonymousWindowEvent) {
    	 addresslist.this.table.grabFocus();
    	 addresslist.this.table.changeSelection(0, 0, false, false);
      }
    });
    setVisible(true);
  }
}
