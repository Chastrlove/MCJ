package mi;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Test extends JFrame implements ActionListener,DocumentListener {
    JMenuBar mb = new JMenuBar();
    FgMenu mFile = new FgMenu("文件(F)", KeyEvent.VK_F);
    FgMenu mjava = new FgMenu("Java上机题目(J)", KeyEvent.VK_J);
    FgMenu mcall = new FgMenu("通讯录(C)", KeyEvent.VK_C);
    JMenuItem miNew = new JMenuItem("新建(N)", KeyEvent.VK_N),
            miOpen = new JMenuItem("打开(O)", KeyEvent.VK_O),
            miSave = new JMenuItem("保存(S)", KeyEvent.VK_S),
            miFont = new JMenuItem("字体与颜色(F)", KeyEvent.VK_F),
            miQuit = new JMenuItem("退出(X)", KeyEvent.VK_X),
            miHwen = new JMenuItem("回文数(1)", KeyEvent.VK_1),
            miTrsl = new JMenuItem("数字与英文翻译程序(2)", KeyEvent.VK_2),
            miStat = new JMenuItem("字符串统计(3)", KeyEvent.VK_3),
            miPhone = new JMenuItem("手机号码合法性判断4)", KeyEvent.VK_4);
    static JMenuItem miSum = new JMenuItem("文本文件计算并求和(5)", KeyEvent.VK_5);
    static JMenuItem miAress = new JMenuItem("通讯录维护(A)", KeyEvent.VK_A);
    static JMenuItem micfile = new JMenuItem("通讯录存储文件设置(M)", KeyEvent.VK_M);
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
        try {//将界面风格设置为系统风格
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //去除JTextField，JTextArea在输入中文时多出的一个浮动输入框
            System.setProperty("java.awt.im.style", "on-the-spot");
        } catch (Exception e) {
            e.printStackTrace();
        }
        centerWindow();

    }

    private void addMenus() {
        setJMenuBar(mb);
        mFile.add(miNew);
        miNew.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        miNew.addActionListener(this);
        mFile.add(miOpen);
        miOpen.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        miOpen.addActionListener(this);
        mFile.add(miSave);
        miSave.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        miSave.addActionListener(this);
        mFile.add(miFont);
        miFont.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        miFont.addActionListener(this);
        mFile.add(miQuit);
        miQuit.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        miQuit.addActionListener(this);
        mb.add(mFile);
        mFile.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        mb.add(mjava);
        mjava.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        mjava.add(miHwen);
        miHwen.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        miHwen.addActionListener(new Hwen());
        mjava.add(miTrsl);
        miTrsl.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        miTrsl.addActionListener(new Translation());
        mjava.add(miStat);
        miStat.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        miStat.addActionListener(new Statistics());
        mjava.add(miPhone);
        miPhone.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        miPhone.addActionListener(new Judge());
        mjava.add(miSum);
        miSum.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        miSum.addActionListener(this);
        mcall.add(miAress);
        miAress.addActionListener(this);
        miAress.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        mcall.add(micfile);
        micfile.addActionListener(this);
        micfile.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        mb.add(mcall);
        mcall.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        ta.getDocument().addDocumentListener(this);


    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == miNew) {
            if (!isModified) {

                ta.setText("");
            } else {
                int n = JOptionPane.showConfirmDialog(null, "是否保存修改", "Java程序设计综合实验", JOptionPane.YES_NO_CANCEL_OPTION);
                if (n == 0) {
                    JFileChooser Saver = new JFileChooser();
                    int i = Saver.showSaveDialog(Test.this); //显示保存文件对话框
                    if (i == JFileChooser.APPROVE_OPTION) {  //点击对话框中保存按钮
                        File f = Saver.getSelectedFile(); //得到选择的文件
                        try {
                            FileOutputStream out = new FileOutputStream(f);  //得到文件输出流
                            out.write(ta.getText().getBytes()); //写出文件
                            out.close();
                        } catch (Exception ex) {
                            ex.printStackTrace(); //输出出错信息
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
                int i = Saver.showSaveDialog(Test.this); //显示保存文件对话框
                if (i == JFileChooser.APPROVE_OPTION) {  //点击对话框中保存按钮
                    File f = Saver.getSelectedFile(); //得到选择的文件
                    try {
                        FileOutputStream out = new FileOutputStream(f);  //得到文件输出流
                        out.write(ta.getText().getBytes()); //写出文件
                        out.close();
                    } catch (Exception ex) {
                        ex.printStackTrace(); //输出出错信息
                    }
                }
            } else
                try {
                    FileOutputStream out = new FileOutputStream(fpath + fname);  //得到文件输出流
                    out.write(ta.getText().getBytes()); //写出文件
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
                int a = JOptionPane.showConfirmDialog(null, "是否要退出系统？", "Java程序设计综合实验", JOptionPane.YES_NO_CANCEL_OPTION);
                if (a == 0) {
                    System.exit(0);
                }
            } else {
                int n = JOptionPane.showConfirmDialog(null, "是否保存修改？", "Java程序设计综合实验", JOptionPane.YES_NO_CANCEL_OPTION);
                if (n == 0) {
                    if (fpath.equals("")) {
                        JFileChooser Saver = new JFileChooser();
                        int i = Saver.showSaveDialog(Test.this); //显示保存文件对话框
                        if (i == JFileChooser.APPROVE_OPTION) {  //点击对话框中保存按钮
                            File f = Saver.getSelectedFile(); //得到选择的文件
                            try {
                                FileOutputStream out = new FileOutputStream(f);  //得到文件输出流
                                out.write(ta.getText().getBytes()); //写出文件
                                out.close();
                            } catch (Exception ex) {
                                ex.printStackTrace(); //输出出错信息
                            }
                        }
                    } else
                        try {
                            FileOutputStream out = new FileOutputStream(fpath + fname);  //得到文件输出流
                            out.write(ta.getText().getBytes()); //写出文件
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
                    FileDialog fd = new FileDialog(f, "打开文件", FileDialog.LOAD);//选文件的对话框
                    fd.setVisible(true);
                    fpath = fd.getDirectory();
                    fname = fd.getFile();
                    BufferedReader br = new BufferedReader(new FileReader(fpath + fname));
                    ta.setText("");//jta为.JTextArea的实例，全局的
                    String s = br.readLine();  //读文件
                    while (s != null) {
                        ta.append(s + "\r\n");
                        s = br.readLine();
                    }
                    this.setTitle("201326810611－马晨杰－Java程序设计综合实验—" + fpath + fname);
                    br.close();
                    ta.getDocument().addDocumentListener(this);
                } catch (Exception ex) {
                }
                //String类的实例，全局变量
            } else {
                int n = JOptionPane.showConfirmDialog(null, "是否保存修改？", "Java程序设计综合实验", JOptionPane.YES_NO_CANCEL_OPTION);
                if (n == 0) {
                    JFileChooser Saver = new JFileChooser();
                    int i = Saver.showSaveDialog(Test.this); //显示保存文件对话框
                    if (i == JFileChooser.APPROVE_OPTION) {  //点击对话框中保存按钮
                        File f = Saver.getSelectedFile(); //得到选择的文件
                        try {
                            FileOutputStream out = new FileOutputStream(f);  //得到文件输出流
                            out.write(ta.getText().getBytes()); //写出文件
                            out.close();
                        } catch (Exception ex) {
                            ex.printStackTrace(); //输出出错信息
                        }

                    }
                }
                if (n == 1) {
                    try {
                        Frame f = new Frame();//
                        FileDialog fd = new FileDialog(f, "打开文件", FileDialog.LOAD);//选文件的对话框
                        fd.setVisible(true);
                        fpath = fd.getDirectory();
                        fname = fd.getFile();
                        BufferedReader br = new BufferedReader(new FileReader(fpath + fname));
                        ta.setText("");//jta为.JTextArea的实例，全局的
                        String s = br.readLine();  //读文件
                        while (s != null) {
                            ta.append(s + "\r\n");
                            s = br.readLine();
                        }
                        this.setTitle("201326810611－马晨杰－Java程序设计综合实验—" + fpath + fname);
                        br.close();
                        ta.getDocument().addDocumentListener(this);
                    } catch (Exception ex) {
                    }
                    //String类的实例，全局变量
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
                JOptionPane.showMessageDialog(this, localMyException.getMessage(), "Java程序设计综合实验", 0);
            }

        }

        if (e.getSource() == micfile) {
            JFileChooser localJFileChooser = new JFileChooser();
            localJFileChooser.setSelectedFile(new File(this.cfName));
            if (localJFileChooser.showDialog(this, "选择文件") == 0)
                this.cfName = localJFileChooser.getSelectedFile().getPath();

        }
    }

    public void miQuit(ActionEvent e) {
        if (!isModified) {
            int a = JOptionPane.showConfirmDialog(null, "是否要退出系统？", "Java程序设计综合实验", JOptionPane.YES_NO_CANCEL_OPTION);
            if (a == 0) {
                System.exit(0);
            }
        } else {
            int n = JOptionPane.showConfirmDialog(null, "是否保存修改？", "Java程序设计综合实验", JOptionPane.YES_NO_CANCEL_OPTION);
            if (n == 0) {
                if (fpath.equals("")) {
                    JFileChooser Saver = new JFileChooser();
                    int i = Saver.showSaveDialog(Test.this); //显示保存文件对话框
                    if (i == JFileChooser.APPROVE_OPTION) {  //点击对话框中保存按钮
                        File f = Saver.getSelectedFile(); //得到选择的文件
                        try {
                            FileOutputStream out = new FileOutputStream(f);  //得到文件输出流
                            out.write(ta.getText().getBytes()); //写出文件
                            out.close();
                        } catch (Exception ex) {
                            ex.printStackTrace(); //输出出错信息
                        }
                    }
                } else
                    try {
                        FileOutputStream out = new FileOutputStream(fpath + fname);  //得到文件输出流
                        out.write(ta.getText().getBytes()); //写出文件
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
        Test frm = new Test("201326810611－马晨杰－Java程序设计综合实验");
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