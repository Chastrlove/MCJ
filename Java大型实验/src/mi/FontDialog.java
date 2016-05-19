package mi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FontDialog extends JFrame implements ItemListener, ActionListener, WindowListener{
	public JDialog Dialog;
	public JCheckBox Bold,Italic;
	public List Size,Name;
	public int FontName;
	public int FontStyle;
	public int FontSize;
	public JButton OK,Cancel,color;
	public JTextArea Text;
	FontDialog(JFrame notepad, int x, int y) {
		GraphicsEnvironment g=GraphicsEnvironment.getLocalGraphicsEnvironment(); 
		String name[]=g.getAvailableFontFamilyNames();
		Bold=new JCheckBox("加粗",false);
		Italic=new JCheckBox("斜体",false);
		Dialog=new JDialog(notepad,"字体",true);
		Text=new JTextArea("字体预览用例\n9876543210\nAaBbCcXxYyZz");
		OK=new JButton("OK");
		Cancel=new JButton("Cancel");
		color=new JButton("Color");
		Size=new List();
		Name=new List();
		int i=0;
		Name.add("Default Value");
		for(i=0;i<name.length;i++) Name.add(name[i]);
		for(i=8;i<100;i++) Size.add(String.valueOf(i));
		FontName=0;
		FontStyle=0;
		FontSize=8;
		Dialog.setLayout(null);
		Dialog.setLocation(x, y);
		Dialog.setSize(480, 306);
		Dialog.setResizable(false);
		OK.setFocusable(false);
		Cancel.setFocusable(false);
		color.setFocusable(false);
		Bold.setFocusable(false);
		Italic.setFocusable(false);
		Name.setFocusable(false);
		Size.setFocusable(false);
		Name.setBounds(10, 10, 212, 259);
		Dialog.add(Name);
		Bold.setBounds(314, 10, 64, 25);
		Dialog.add(Bold);
		Italic.setBounds(388, 10, 64, 25);
		Dialog.add(Italic);
		Size.setBounds(232, 10, 64, 259);
		Dialog.add(Size);
		Text.setBounds(306, 40, 150, 150);
		Dialog.add(Text);
		OK.setBounds(306, 243, 74, 26);
		Dialog.add(OK);
		Cancel.setBounds(390, 243, 74, 26);
		Dialog.add(Cancel);
		color.setBounds(350,200,74,26);
		Dialog.add(color);
		Name.select(FontName);
		Size.select(FontSize);
		Text.setFont(getFont());
		Dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Name.addItemListener(this);
		Size.addItemListener(this);
		Bold.addItemListener(this);
		Italic.addItemListener(this);
		OK.addActionListener(this);
		Cancel.addActionListener(this);
		color.addActionListener(this);
		Dialog.addWindowListener(this);
	}
	public void itemStateChanged(ItemEvent e) {
		Text.setFont(getFont());
	}
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==color){
			
			JColorChooser colorChooser=new JColorChooser();
            Color ForeColor= colorChooser.showDialog(color, "字体颜色", Color.BLACK);
            Text.setForeground(ForeColor);
            Test.ta.setForeground(ForeColor);
			
		}
		if(e.getSource()==OK){
			FontName=Name.getSelectedIndex();
			FontStyle=getStyle();
			FontSize=Size.getSelectedIndex();
			Dialog.setVisible(false);
		}
		if(e.getSource()==Cancel)
	    cancel();
		
	}
	
	public void windowClosing(WindowEvent e) {
		cancel();
	}
	public Font getFont(){
		if(Name.getSelectedIndex()==0) return new Font("新宋体",getStyle(),Size.getSelectedIndex()+8);
		else return new Font(Name.getSelectedItem(),getStyle(),Size.getSelectedIndex()+8);
	}
	public void cancel(){
		Name.select(FontName);
		Size.select(FontSize);
		setStyle();
		Text.setFont(getFont());
		Dialog.setVisible(false);
	}
	public void setStyle(){
		if(FontStyle==0 || FontStyle==2) Bold.setSelected(false);
		else Bold.setSelected(true);
		if(FontStyle==0 || FontStyle==1) Italic.setSelected(false);
		else Italic.setSelected(true);
	}
	public int getStyle(){
		int bold=0,italic=0;
		if(Bold.isSelected()) bold=1;
		if(Italic.isSelected()) italic=1;
		return bold+italic*2;
	}
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
}