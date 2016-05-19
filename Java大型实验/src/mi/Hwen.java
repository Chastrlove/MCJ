package mi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hwen  extends JFrame implements ActionListener {
	 JLabel entrance=new JLabel("请输入 1-99999之间的整数：",JLabel.LEFT);
	 static JTextArea enText=new JTextArea();
	 JPanel enpan=new JPanel();
	 static JButton Yeah=new JButton("判断是否为回文数");
	 static JButton CANCEL=new JButton("取消");
	 JPanel panButtons=new JPanel();
	 Hwen(){
		 super("回文数判断");
		 setSize(300,110);
		 JScrollPane sp=new JScrollPane(enText);
		  GridLayout gl=new GridLayout(2,1);
		  setLayout(gl);
		  enpan.setLayout(new BorderLayout());
		  enpan.add(entrance,BorderLayout.WEST);
		  enpan.add(sp,BorderLayout.CENTER);
		  add(enpan);
		  
		  
		  panButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
		  panButtons.add(Yeah);
		  panButtons.add(CANCEL);
		  add(panButtons); 
		  Yeah.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e){
				  loopNumber(e);
			 }
		  });
		  CANCEL.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e){
				  loopNumber(e);
			 }
		  });
		  
		  centerWindow();
		} 
public void centerWindow(){
	   Toolkit tk=getToolkit();
	   Dimension dm=tk.getScreenSize();
	   setLocation((int)(dm.getWidth()-getWidth())/2,(int)(dm.getHeight()-getHeight())/2);		 
			 }
public void actionPerformed(ActionEvent e) {
	Hwen f = new Hwen();
     f.setVisible(true);
 }
public  void loopNumber(ActionEvent e){
	if(e.getSource()==Yeah){
		String number=enText.getText();
		boolean flag = true;
		for(int i=0;i<number.trim().length()/2;i++){
			 //把索引为i位置的字符和它对称索引位置的字符相比，看是否一样
	   if(!String.valueOf(number.charAt(i)).equals(String.valueOf(number.charAt(number.trim().length()-i-1)))){
	   flag = false;
		break;
			 }
			}
	
		if(flag){
			JOptionPane.showMessageDialog(this, new StringBuilder().append(number).append("是回文数。").toString(), "回文数判断程序", 1);
			}
		else{
			JOptionPane.showMessageDialog(this, new StringBuilder().append(number).append("不是回文数。").toString(), "回文数判断程序", 1);
			}
}	
	if(e.getSource()==CANCEL)
		dispose();
	
  }
}

