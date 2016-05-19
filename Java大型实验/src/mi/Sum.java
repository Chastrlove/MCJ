package mi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.Vector;

public class Sum extends JFrame implements ActionListener {
	static BarThread stepper;
	static JLabel txt=new JLabel();
	//�������߳�
	static JButton btnStart = new JButton("��ʼ");
	static JButton btnStop = new JButton("ȡ��");
	static JProgressBar aJProgressBar = new JProgressBar(0, 100);//��������0-100
	//���췽��
static class BarThread extends Thread{
		private static int DELAY = 0;
		JProgressBar progressBar;
	    
		private boolean m_bStopped;
		private boolean m_bPaused=false;
		
public BarThread(JProgressBar bar) {
			progressBar = bar;
			m_bStopped=false;
			m_bPaused=false;
		}
		//�߳���
public  void run() {
	try{
		
	    BufferedReader num=new BufferedReader(new StringReader (Test.taa));
	     Vector<String> v=new Vector<String>();
	     float sum=0;  
	     String str,a,c;
	     int line=0;
	     double Time=0,time;
	     while((str=num.readLine())!=null){
	    	  
	              v.add(str);
	               line++;
	     }
			for (int i = 0; i <v.size(); i++) {
				if(m_bStopped){
					progressBar.setValue(0);	
					txt.setText("");
					break;
				  }
				try {
					
					while (m_bPaused)
					Thread.sleep(DELAY);
				
					double start = System.nanoTime(); // ��ȡ��ʼʱ��
					int b=((String) v.get(i)).indexOf('=');
			        a=((String) v.get(i)).substring(b+1);
			        c=((String) v.get(i)).substring(0,b);
			        float f=Float.parseFloat(a);
			        sum=sum+f; 
			        double end=System.nanoTime();
			        time=end-start;
			        Time+=time;
			        int k=i+1;
			        DecimalFormat df=new DecimalFormat("#.0000");
			
			        txt.setText("��ǰ��Ϊ"+sum+"���ڼ������"+c+"("+k+"/"+line+")"+" ��Լ��ʣ��"+df.format((Time*(line-k)/k/1000000))+"ms");
			      
			        int value = ((i + 1) * 100 / v.size());
			        progressBar.setValue(value);
			        if(i== v.size()-1){
			        	JOptionPane.showMessageDialog(null,"��Ϊ"+ sum,"��ʾ", JOptionPane.INFORMATION_MESSAGE); 
			        	btnStart.setText("��ʼ");
			        	btnStart.setEnabled(true);	
						btnStop.setEnabled(false);
						stepper.Stop(true);
						stepper=null;
			        	progressBar.setValue(0);	
			        	txt.setText("");
				   }
			        

			        }
				
				
				catch (InterruptedException ignoredException) {
				
				}
				
		   }
		}
	catch(Exception ex){
		
	}
}
		//������ͣ
public void Pause(boolean bPaused){
			m_bPaused=bPaused;
		}
		//����ֹͣ
public void Stop(boolean bStopped){
			m_bStopped=bStopped;
			
		}
	}

Sum(){
	super("�ı���ͳ���");
	   
		//���ý���������
		
		aJProgressBar.setStringPainted(true);
		aJProgressBar.setBackground(Color.white);
		aJProgressBar.setForeground(Color.blue);		
		//���尴ť
		
		
		//�����¼�Դ
		btnStart.addActionListener(this);
		btnStop.addActionListener(this);
		//��������������
		JPanel jp1=new JPanel();
		jp1.add(txt);
		add(jp1, BorderLayout.NORTH);
		add(aJProgressBar, BorderLayout.CENTER);
		
		JPanel jp2=new JPanel();
		jp2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jp2.add(btnStart);
		jp2.add(btnStop);
		btnStop.setEnabled(false);
		add(jp2, BorderLayout.SOUTH);
		//���ô�������
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 140);
		setResizable(false); 
		//frm.pack();
		
		centerWindow();
}

//��ť�¼��������
public void actionPerformed(ActionEvent e) {				
		if(e.getSource()==btnStart){//��ʼ����ͣ
			if(stepper==null){
				stepper=new BarThread(aJProgressBar);
				btnStop.setEnabled(true);		
				stepper.start();	
				btnStart.setEnabled(false);	
				
				}
			
			}
		if(e.getSource()==btnStop){
			btnStart.setText("��ʼ");
			btnStop.setEnabled(false);
			stepper.Stop(true);
			stepper=null;
			btnStart.setEnabled(true);	
		}
		
	}
public void centerWindow(){
	  Toolkit tk=getToolkit();
	  Dimension dm=tk.getScreenSize();
	  setLocation((int)(dm.getWidth()-getWidth())/2,(int)(dm.getHeight()-getHeight())/2);
	 
	 }


	   }
	 
 

