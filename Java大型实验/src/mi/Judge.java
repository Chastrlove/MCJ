package mi;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class Judge extends JFrame implements ActionListener{

	 JLabel entrance=new JLabel("����Ҫ�����ֻ�����",JLabel.LEFT);
	 JTextArea enText=new JTextArea();
	 JPanel enpan=new JPanel();
	 
	 JLabel result=new JLabel("���",JLabel.LEFT);
	 static JTextArea reText=new JTextArea();
	 JPanel repan=new JPanel();
	 
	 JButton YES=new JButton("ȷ��");
	 JButton CANCEL=new JButton("ȡ��");
	 JPanel panButtons=new JPanel();
	 
	 //���캯��
	 Judge(){
	  super("�ֻ�����Ϸ����ж�");
		 
	  setSize(400,130);
	  
	  JScrollPane sp=new JScrollPane(enText);
	  JScrollPane sq=new JScrollPane(reText);
	  GridLayout gl=new GridLayout(3,1);
	  setLayout(gl);
		 
	  //����
	  enpan.setLayout(new BorderLayout());
	  enpan.add(entrance,BorderLayout.WEST);
	  enpan.add(sp,BorderLayout.CENTER);
	  add(enpan);
	  
	  //���
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
	    if(str.indexOf("+")==0){ //�жϵ�һλ�Ƿ�Ϊ+���о�ɾ���жϳ���
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
 {for(int i=0 ; i<str.length() ; i++){ //ѭ�������ַ���   
     if(Character.isLetter(str.charAt(i))){   //��char��װ���е��ж���ĸ�ķ����ж�ÿһ���ַ�
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