package mi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Statistics extends JFrame implements ActionListener{
	
	
	 JLabel st=new JLabel("统计结果",JLabel.LEFT);
	 JPanel stpan=new JPanel();
	 static JTextArea Text=new JTextArea();
	 
	 Statistics(){
		super("统计英文数据") ;
		setSize(300,100);
		JScrollPane sp=new JScrollPane(Text);
		stpan.setLayout(new BorderLayout());
		stpan.add(st,BorderLayout.WEST);
		stpan.add(sp,BorderLayout.CENTER);
		add(stpan);
	   centerWindow();
	 }
 public void centerWindow(){
		  Toolkit tk=getToolkit();
		  Dimension dm=tk.getScreenSize();
		  setLocation((int)(dm.getWidth()-getWidth())/2,(int)(dm.getHeight()-getHeight())/2);
		 
		 }
 public void actionPerformed(ActionEvent e) {
	 Statistics fr = new Statistics();
	  String a=Test.ta.getText();
	  statis(a);
	        fr.setVisible(true);
	 }
 public static void statis(String j) {
	 
	  int a=0;int b=0;int c=0;
	  String []split = j.split(" ");
	  
	  for(int i=0;i<split.length;i++){
	
	   if(split[i].charAt(0)=='w')
	      a++;
	   
	   if(split[i].contains("or"))
		  b++;
		   
	   if(split[i].length()==3)
	      c++;
	    
	  }
      Text.append("以字母w开头的单词数有:"+a+"个"+"\n");
      Text.append("单词中含“or”字符串的单词数有:"+b+"个"+"\n");
      Text.append("长度为3的单词数有:"+c+"个"+"\n"); 
	 }
	}