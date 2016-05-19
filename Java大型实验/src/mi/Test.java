package mi;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Test extends JFrame implements ActionListener,DocumentListener {
    JMenuBar mb = new JMenuBar();
    FgMenu mFile = new FgMenu("�ļ�(F)", KeyEvent.VK_F);
    FgMenu mjava = new FgMenu("Java�ϻ���Ŀ(J)", KeyEvent.VK_J);
    FgMenu mcall = new FgMenu("ͨѶ¼(C)", KeyEvent.VK_C);
    JMenuItem miNew = new JMenuItem("�½�(N)", KeyEvent.VK_N),
            miOpen = new JMenuItem("��(O)", KeyEvent.VK_O),
            miSave = new JMenuItem("����(S)", KeyEvent.VK_S),
            miFont = new JMenuItem("��������ɫ(F)", KeyEvent.VK_F),
            miQuit = new JMenuItem("�˳�(X)", KeyEvent.VK_X),
            miHwen = new JMenuItem("������(1)", KeyEvent.VK_1),
            miTrsl = new JMenuItem("������Ӣ�ķ������(2)", KeyEvent.VK_2),
            miStat = new JMenuItem("�ַ���ͳ��(3)", KeyEvent.VK_3),
            miPhone = new JMenuItem("�ֻ�����Ϸ����ж�4)", KeyEvent.VK_4);
    static JMenuItem miSum = new JMenuItem("�ı��ļ����㲢���(5)", KeyEvent.VK_5);
    static JMenuItem miAress = new JMenuItem("ͨѶ¼ά��(A)", KeyEvent.VK_A);
    static JMenuItem micfile = new JMenuItem("ͨѶ¼�洢�ļ�����(M)", KeyEvent.VK_M);
    static String taa;
    static String fpath = null;
    static String fname = null;
    static JTextArea ta = new JTextArea();
    static boolean isModified = false;
    private String fName = null;
    private String cfName = new String("addresslist.dat");

    Test(String sTitle) {

        super(sTitle);
        addMenus();
        JScrollPane sp = new JScrollPane(ta);

        add(sp);
        setSize(700, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                miQuit(null);
            }
        });
        try {//������������Ϊϵͳ���
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //ȥ��JTextField��JTextArea����������ʱ�����һ�����������
            System.setProperty("java.awt.im.style", "on-the-spot");
        } catch (Exception e) {
            e.printStackTrace();
        }
        centerWindow();

    }

    private void addMenus() {
        setJMenuBar(mb);
        mFile.add(miNew);
        miNew.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        miNew.addActionListener(this);
        mFile.add(miOpen);
        miOpen.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        miOpen.addActionListener(this);
        mFile.add(miSave);
        miSave.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        miSave.addActionListener(this);
        mFile.add(miFont);
        miFont.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        miFont.addActionListener(this);
        mFile.add(miQuit);
        miQuit.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        miQuit.addActionListener(this);
        mb.add(mFile);
        mFile.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        mb.add(mjava);
        mjava.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        mjava.add(miHwen);
        miHwen.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        miHwen.addActionListener(new Hwen());
        mjava.add(miTrsl);
        miTrsl.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        miTrsl.addActionListener(new Translation());
        mjava.add(miStat);
        miStat.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        miStat.addActionListener(new Statistics());
        mjava.add(miPhone);
        miPhone.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        miPhone.addActionListener(new Judge());
        mjava.add(miSum);
        miSum.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        miSum.addActionListener(this);
        mcall.add(miAress);
        miAress.addActionListener(this);
        miAress.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        mcall.add(micfile);
        micfile.addActionListener(this);
        micfile.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        mb.add(mcall);
        mcall.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        ta.getDocument().addDocumentListener(this);


    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == miNew) {
            if (!isModified) {

                ta.setText("");
            } else {
                int n = JOptionPane.showConfirmDialog(null, "�Ƿ񱣴��޸�", "Java��������ۺ�ʵ��", JOptionPane.YES_NO_CANCEL_OPTION);
                if (n == 0) {
                    JFileChooser Saver = new JFileChooser();
                    int i = Saver.showSaveDialog(Test.this); //��ʾ�����ļ��Ի���
                    if (i == JFileChooser.APPROVE_OPTION) {  //����Ի����б��水ť
                        File f = Saver.getSelectedFile(); //�õ�ѡ����ļ�
                        try {
                            FileOutputStream out = new FileOutputStream(f);  //�õ��ļ������
                            out.write(ta.getText().getBytes()); //д���ļ�
                            out.close();
                        } catch (Exception ex) {
                            ex.printStackTrace(); //���������Ϣ
                        }
                        ta.setText("");
                    }

                }

                if (n == 1) {
                    ta.setText("");
                }
                if (n == -1) {
                }
            }
        }
        if (e.getSource() == miSave) {
            if (fpath.equals("")) {
                JFileChooser Saver = new JFileChooser();
                int i = Saver.showSaveDialog(Test.this); //��ʾ�����ļ��Ի���
                if (i == JFileChooser.APPROVE_OPTION) {  //����Ի����б��水ť
                    File f = Saver.getSelectedFile(); //�õ�ѡ����ļ�
                    try {
                        FileOutputStream out = new FileOutputStream(f);  //�õ��ļ������
                        out.write(ta.getText().getBytes()); //д���ļ�
                        out.close();
                    } catch (Exception ex) {
                        ex.printStackTrace(); //���������Ϣ
                    }
                }
            } else
                try {
                    FileOutputStream out = new FileOutputStream(fpath + fname);  //�õ��ļ������
                    out.write(ta.getText().getBytes()); //д���ļ�
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }
        if (e.getSource() == miFont) {
            JFrame dialog = new JFrame();
            FontDialog font;
            Toolkit tk = getToolkit();
            Dimension screen = tk.getScreenSize();
            font = new FontDialog(dialog, screen.width / 2 - 240, screen.height / 2 - 150);
            font.Dialog.setVisible(true);
            ta.setFont(font.getFont());
        }
        if (e.getSource() == miQuit) {
            if (!isModified) {
                int a = JOptionPane.showConfirmDialog(null, "�Ƿ�Ҫ�˳�ϵͳ��", "Java��������ۺ�ʵ��", JOptionPane.YES_NO_CANCEL_OPTION);
                if (a == 0) {
                    System.exit(0);
                }
            } else {
                int n = JOptionPane.showConfirmDialog(null, "�Ƿ񱣴��޸ģ�", "Java��������ۺ�ʵ��", JOptionPane.YES_NO_CANCEL_OPTION);
                if (n == 0) {
                    if (fpath.equals("")) {
                        JFileChooser Saver = new JFileChooser();
                        int i = Saver.showSaveDialog(Test.this); //��ʾ�����ļ��Ի���
                        if (i == JFileChooser.APPROVE_OPTION) {  //����Ի����б��水ť
                            File f = Saver.getSelectedFile(); //�õ�ѡ����ļ�
                            try {
                                FileOutputStream out = new FileOutputStream(f);  //�õ��ļ������
                                out.write(ta.getText().getBytes()); //д���ļ�
                                out.close();
                            } catch (Exception ex) {
                                ex.printStackTrace(); //���������Ϣ
                            }
                        }
                    } else
                        try {
                            FileOutputStream out = new FileOutputStream(fpath + fname);  //�õ��ļ������
                            out.write(ta.getText().getBytes()); //д���ļ�
                            out.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                    System.exit(0);
                }
                if (n == 1) {
                    System.exit(0);
                }
                if (n == -1) {
                }
            }
        }
        if (e.getSource() == miOpen) {

            if (!isModified) {
                try {
                    Frame f = new Frame();//
                    FileDialog fd = new FileDialog(f, "���ļ�", FileDialog.LOAD);//ѡ�ļ��ĶԻ���
                    fd.setVisible(true);
                    fpath = fd.getDirectory();
                    fname = fd.getFile();
                    BufferedReader br = new BufferedReader(new FileReader(fpath + fname));
                    ta.setText("");//jtaΪ.JTextArea��ʵ����ȫ�ֵ�
                    String s = br.readLine();  //���ļ�
                    while (s != null) {
                        ta.append(s + "\r\n");
                        s = br.readLine();
                    }
                    this.setTitle("201326810611�����ܣ�Java��������ۺ�ʵ�顪" + fpath + fname);
                    br.close();
                    ta.getDocument().addDocumentListener(this);
                } catch (Exception ex) {
                }
                //String���ʵ����ȫ�ֱ���
            } else {
                int n = JOptionPane.showConfirmDialog(null, "�Ƿ񱣴��޸ģ�", "Java��������ۺ�ʵ��", JOptionPane.YES_NO_CANCEL_OPTION);
                if (n == 0) {
                    JFileChooser Saver = new JFileChooser();
                    int i = Saver.showSaveDialog(Test.this); //��ʾ�����ļ��Ի���
                    if (i == JFileChooser.APPROVE_OPTION) {  //����Ի����б��水ť
                        File f = Saver.getSelectedFile(); //�õ�ѡ����ļ�
                        try {
                            FileOutputStream out = new FileOutputStream(f);  //�õ��ļ������
                            out.write(ta.getText().getBytes()); //д���ļ�
                            out.close();
                        } catch (Exception ex) {
                            ex.printStackTrace(); //���������Ϣ
                        }

                    }
                }
                if (n == 1) {
                    try {
                        Frame f = new Frame();//
                        FileDialog fd = new FileDialog(f, "���ļ�", FileDialog.LOAD);//ѡ�ļ��ĶԻ���
                        fd.setVisible(true);
                        fpath = fd.getDirectory();
                        fname = fd.getFile();
                        BufferedReader br = new BufferedReader(new FileReader(fpath + fname));
                        ta.setText("");//jtaΪ.JTextArea��ʵ����ȫ�ֵ�
                        String s = br.readLine();  //���ļ�
                        while (s != null) {
                            ta.append(s + "\r\n");
                            s = br.readLine();
                        }
                        this.setTitle("201326810611�����ܣ�Java��������ۺ�ʵ�顪" + fpath + fname);
                        br.close();
                        ta.getDocument().addDocumentListener(this);
                    } catch (Exception ex) {
                    }
                    //String���ʵ����ȫ�ֱ���
                }

                if (n == -1) {
                }
            }


        }

        if (e.getSource() == miSum) {
            taa = ta.getText();
            Sum f = new Sum();
            f.setVisible(true);
        }
        if (e.getSource() == miAress) {
            try {
                new addresslist(this, this.cfName);
            } catch (MyException localMyException) {
                JOptionPane.showMessageDialog(this, localMyException.getMessage(), "Java��������ۺ�ʵ��", 0);
            }

        }

        if (e.getSource() == micfile) {
            JFileChooser localJFileChooser = new JFileChooser();
            localJFileChooser.setSelectedFile(new File(this.cfName));
            if (localJFileChooser.showDialog(this, "ѡ���ļ�") == 0)
                this.cfName = localJFileChooser.getSelectedFile().getPath();

        }
    }

    public void miQuit(ActionEvent e) {
        if (!isModified) {
            int a = JOptionPane.showConfirmDialog(null, "�Ƿ�Ҫ�˳�ϵͳ��", "Java��������ۺ�ʵ��", JOptionPane.YES_NO_CANCEL_OPTION);
            if (a == 0) {
                System.exit(0);
            }
        } else {
            int n = JOptionPane.showConfirmDialog(null, "�Ƿ񱣴��޸ģ�", "Java��������ۺ�ʵ��", JOptionPane.YES_NO_CANCEL_OPTION);
            if (n == 0) {
                if (fpath.equals("")) {
                    JFileChooser Saver = new JFileChooser();
                    int i = Saver.showSaveDialog(Test.this); //��ʾ�����ļ��Ի���
                    if (i == JFileChooser.APPROVE_OPTION) {  //����Ի����б��水ť
                        File f = Saver.getSelectedFile(); //�õ�ѡ����ļ�
                        try {
                            FileOutputStream out = new FileOutputStream(f);  //�õ��ļ������
                            out.write(ta.getText().getBytes()); //д���ļ�
                            out.close();
                        } catch (Exception ex) {
                            ex.printStackTrace(); //���������Ϣ
                        }
                    }
                } else
                    try {
                        FileOutputStream out = new FileOutputStream(fpath + fname);  //�õ��ļ������
                        out.write(ta.getText().getBytes()); //д���ļ�
                        out.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                System.exit(0);
            }
            if (n == 1) {
                System.exit(0);
            }
            if (n == -1) {
            }
        }
    }

    public void changedUpdate(DocumentEvent e) {
        isModified = true;
    }

    public void insertUpdate(DocumentEvent e) {
        isModified = true;
    }

    public void removeUpdate(DocumentEvent e) {
        isModified = true;
    }

    public void centerWindow() {
        Toolkit tk = getToolkit();
        Dimension dm = tk.getScreenSize();
        setLocation((int) (dm.getWidth() - getWidth()) / 2, (int) (dm.getHeight() - getHeight()) / 2);

    }

    public static void main(String[] args) {
        Test frm = new Test("201326810611�����ܣ�Java��������ۺ�ʵ��");
        frm.setVisible(true);

    }

}

class FgMenu extends JMenu {
    public FgMenu(String label) {
        super(label);
    }

    public FgMenu(String label, int nAccelerator) {
        super(label);
        setMnemonic(nAccelerator);
    }

}