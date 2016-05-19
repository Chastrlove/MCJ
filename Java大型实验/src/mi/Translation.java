package mi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Translation extends JFrame implements ActionListener {
	 JLabel entrance=new JLabel("输入要翻译的文本",JLabel.LEFT);
	 JTextArea enText=new JTextArea();
	 JPanel enpan=new JPanel();
	 
	 JLabel result=new JLabel("结果",JLabel.LEFT);
	 static JTextArea reText=new JTextArea();
	 JPanel repan=new JPanel();
	 
	 JButton YES=new JButton("确定");
	 JButton CANCEL=new JButton("取消");
	 JPanel panButtons=new JPanel();
	 
	 //构造函数
	Translation(){
	  super("数字与英文互译");
		 
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
			translate(e);
		 }
	  });
	  CANCEL.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			  translate(e);
		 }
	  });
	

	   
	    centerWindow();
	}
	public void centerWindow(){
		  Toolkit tk=getToolkit();
		  Dimension dm=tk.getScreenSize();
		  setLocation((int)(dm.getWidth()-getWidth())/2,(int)(dm.getHeight()-getHeight())/2);
		 
		 }
public void translate(ActionEvent e){
	if(e.getSource()==YES)
	{
	String a=enText.getText();
	if(isnumber(a))
	  ntoe(a);
	else{
		
	       eton(a);
	      }
     }
	if(e.getSource()==CANCEL)
		dispose();
}
 public void actionPerformed(ActionEvent e) {
	 Translation f = new Translation();
        f.setVisible(true);
 }
 public boolean isnumber(String str)
 { 
	if(str.length()==0) return false;
	//String str1[]=str.split("\\s{1,}");
	//for(String s:str1)
	//{
	  for(int i=0;i<str.length();i++)
       {if(str.charAt(i)>='0' && str.charAt(i)<='9')
       continue;
       else
       return false;
       }
	  return true;
	//}
	//return true;
 }
 public static void ntoe(String a){
	int i= Integer.parseInt(a);
	  String x[]={"zero","one","two", "three","four", "five","six","seven","eight","nine"} ;
	  String y[]={"ten","eleven","twelve","thirteen","fourteen","fifteen", "sixteen","seventeen","eighteen","nineteen" };
	  String z[]={"twenty","thirty","forty","fifty",  "sixty","seventy", "eighty","ninety"  };
	       if ((i >= 0) && (i < 100)){
	         if (i <= 9)   
	        	 reText.setText(x[i]); 
	           if((i>9)&&(i<20))
	           { i=i-10;
	           reText.setText(y[i]);
	           } 
	             if(i%10==0)
	    	    reText.setText(z[(i/10)-2]);
	             else 
	            reText.setText(z[(i/10)-2] +" "+ x[i%10]);
	       }
	       }
	 
 public static void eton(String s){
		 String x[]={"zero","one","two", "three","four", "five","six","seven","eight","nine"} ;
		 String y[]={"ten","eleven","twelve","thirteen","fourteen","fifteen", "sixteen","seventeen","eighteen","nineteen" };
		 String z[]={"twenty","thirty","forty","fifty",  "sixty","seventy", "eighty","ninety"  };
		  
		 if(s.indexOf(' ')==-1)
		 {
		    int i;
	        for(i=0;i<10;i++)
	          {
	           if(s.equals(x[i])) 
	             {
	        	   String b=Integer.toString(i);
	        	   reText.setText(b);
	               break;
	              }
	           if(s.equals(y[i])) 
	        	 {
	        	   String b=Integer.toString(i+10);
	        	   reText.setText(b);
	               break;
	               }
	           if(i<8&&s.equals(z[i])) 
	        	 {
	        	   String b=Integer.toString((i+2)*10);
	        	   reText.setText(b);
	               break;
	               }
	           } 
		 }
	       else
	      { 
	         String[] a = s.split(" ");
	         int k;
	         for(k=0;k<8;k++)
	           for(int j=0;j<10;j++)
	            {
	        	   if(a[0].equals(z[k]) && a[1].equals(x[j]))  
	                {
	        		   String b=Integer.toString((k+2)*10+j);
	        		   reText.setText(b); 
	                   break;
	                }
	            }
	        }
        }
    }
