package mi;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class Judge extends JFrame implements ActionListener{

	 JLabel entrance=new JLabel("输入要检测的手机号码",JLabel.LEFT);
	 JTextArea enText=new JTextArea();
	 JPanel enpan=new JPanel();
	 
	 JLabel result=new JLabel("结果",JLabel.LEFT);
	 static JTextArea reText=new JTextArea();
	 JPanel repan=new JPanel();
	 
	 JButton YES=new JButton("确定");
	 JButton CANCEL=new JButton("取消");
	 JPanel panButtons=new JPanel();
	 
	 //构造函数
	 Judge(){
	  super("手机号码合法性判断");
		 
	  setSize(400,130);
	  
	  JScrollPane sp=new JScrollPane(enText);
	  JScrollPane sq=new JScrollPane(reText);
	  GridLayout gl=new GridLayout(3,1);
	  setLayout(gl);
		 
	  //输入
	  enpan.setLayout(new BorderLayout());
	  enpan.add(entrance,BorderLayout.WEST);
	  enpan.add(sp,BorderLayout.CENTER);
	  add(enpan);
	  
	  //结果
	  repan.setLayout(new BorderLayout());
	  repan.add(result,BorderLayout.WEST);
	  repan.add(sq,BorderLayout.CENTER);
	  add(repan);
	  
	  panButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
	  panButtons.add(YES);
	  panButtons.add(CANCEL);
	  add(panButtons);
		 
	  YES.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			judge(e);
		 }
	  });
	  CANCEL.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			  judge(e);
		 }
	  });
	

	   
	    centerWindow();
	}
	public void centerWindow(){
		  Toolkit tk=getToolkit();
		  Dimension dm=tk.getScreenSize();
		  setLocation((int)(dm.getWidth()-getWidth())/2,(int)(dm.getHeight()-getHeight())/2);
		 
		 }
public void judge(ActionEvent e){
	if(e.getSource()==YES)
	{ 
		String i=enText.getText();
	    StringBuffer str=new StringBuffer(i);
	    if(str.indexOf("+")==0){ //判断第一位是否为+，有就删除判断长度
	   	   str.deleteCharAt(0);
	    	 }
	        if(str.indexOf("-")==2||(str.indexOf("-")==2&&str.indexOf("-")==6&&str.indexOf("-")==11)){
	        	String s=str.toString();
	        	s.replace("-","");
	           }
	    	   if(str.length()==13){
	    		   if(str.indexOf("+") != -1||str.indexOf("-") != -1)  
	    			   reText.setText("4");  
	   		       else{  if (!If86(str)){
	   		    	reText.setText("3");
	                 }
	   		        else { if(isletter(str))
	   		        	reText.setText("2");
	   		          else reText.setText("0");
	    		       }
	                 }
	    		  }
	    	     else reText.setText("1");
	    	      
	       }
     
	if(e.getSource()==CANCEL)
		dispose();
}
 public void actionPerformed(ActionEvent e) {
	Judge f = new Judge();
        f.setVisible(true);
 }

 
 public static boolean isletter(StringBuffer str)
 {for(int i=0 ; i<str.length() ; i++){ //循环遍历字符串   
     if(Character.isLetter(str.charAt(i))){   //用char包装类中的判断字母的方法判断每一个字符
        return true;
      }
     }
      return false;
 }
 public static boolean If86(StringBuffer str)
 {
    String a="86";
    String s=str.toString();
  
     if (s.startsWith(a, 0))
      return true;
     else return false;
 }
 }