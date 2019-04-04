import java.awt.*;
import java.awt.event.*;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dialog;
import java.io.*;
import java.util.regex.*;

public class MyMenu extends WindowAdapter implements ActionListener,ItemListener,KeyListener
{
	Frame f;		MenuBar mb;		Menu m1,m2,m3,m4,m5,m6;
	MenuItem nw,opn,sve,svas,ext,ct,cp,pst,fnd,fndnxt,rplc,fnt,abtus;
	CheckboxMenuItem stbar;
	Panel p1;
	Label l1,l2;
	TextArea ta;
	String strp,text,ttext,rtext,paste,str6="Untitled";
	int ln=1,col=1;
	int start =0,end=0;
	WindowEvent we;
	Window w;
	public MyMenu()
	{
		f = new Frame(str6+" - SamNotepad");	f.setSize(400,500);
		
		ta = new TextArea("",200,300,TextArea.SCROLLBARS_BOTH);
		//f.setBackground(Color.blue);
		f.setFont(new Font("Arial",Font.PLAIN,14));
		ta.setFont(new Font("Arial",Font.PLAIN,20));
		ta.setForeground(Color.blue);
		ta.setBackground(Color.yellow);
		f.add(ta,BorderLayout.CENTER);
		
		f.addWindowListener(this);
		l1 = new Label();
		l2 = new Label("Ln :  "+ln+"	"+"Col : "+col);
		f.setIconImage(Toolkit.getDefaultToolkit().getImage("notepad2.png"));
		
		p1 = new Panel();
		p1.add(l1);
		p1.add(l2);
		p1.setBackground(Color.white);
		f.add(p1,BorderLayout.SOUTH);
		
		
		mb = new MenuBar();
		m1 = new Menu("File");
		m2 = new Menu("Edit");
		m3 =  new Menu("Others");
		m4 = new Menu("Format");
		m5 = new Menu("Help");
		m6 = new Menu("View");
		
		nw = new MenuItem("New");
		opn = new MenuItem("Open");
		sve = new MenuItem("Save");
		svas = new MenuItem("Save As...");
		ext = new MenuItem("Exit");
		ct = new MenuItem("Cut");
		cp = new MenuItem("Copy");
		pst = new MenuItem("Paste");
		fnd = new MenuItem("Find...");
		fndnxt = new MenuItem("Find Next");
		rplc = new MenuItem("Replace");
		fnt = new MenuItem("Font...");
		abtus = new MenuItem("About Us");
		
		nw.addActionListener(this);
		opn.addActionListener(this);
		sve.addActionListener(this);
		ext.addActionListener(this);
		ct.addActionListener(this);
		cp.addActionListener(this);
		pst.addActionListener(this);
		svas.addActionListener(this);
		abtus.addActionListener(this);
		fnd.addActionListener(this);
		fndnxt.addActionListener(this);
		rplc.addActionListener(this);
		fnt.addActionListener(this);
		
		stbar = new CheckboxMenuItem("Status Bar");
		stbar.setState(true);
		stbar.addItemListener(this);
		
		ta.addKeyListener(this);
		 
		m6.add(stbar);
		m4.add(fnt);
		m5.add(abtus);
		m2.add(ct);		m2.add(cp);		m2.add(pst);
		m2.addSeparator();
		m2.add(fnd);	m2.add(fndnxt);		m2.add(rplc);
		m1.add(nw); 	m1.add(opn);		m1.add(sve);	m1.add(svas);
		m1.addSeparator();	m1.add(ext);
		
		mb.add(m1);
		mb.add(m2);
		mb.add(m4);

		mb.add(m6);
		mb.add(m5);
		
		f.setMenuBar(mb);
		f.setVisible(true);
	}
	public void keyPressed(KeyEvent ke)
	{
		System.out.println("Key Pressed");
		int key = ke.getKeyCode();
		switch(key)
		{
			case KeyEvent.VK_BACK_SPACE :
										if(col==1);
										else
											col--;
											break;
			case KeyEvent.VK_ENTER :
										ln++;
										break;
			default :
							col++;
		}
		l2.setText("Ln :  "+ln+""+"	"+"Col : "+col+"");	
	}
	public void keyReleased(KeyEvent ke){}
	public void keyTyped(KeyEvent ke){}
	public void windowClosing(WindowEvent we)
	{
		w = we.getWindow();
		try{
		
		System.out.println("str6 ::"+str6);
		if(!((ta.getText()).equals("")))
		{
		if(str6.equals("Untitled"))
		{
			MyDialog4 md4 = new MyDialog4(f);
			md4.setVisible(true);
		}
		}
		else{
		w.setVisible(false);
		w.dispose();}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void itemStateChanged(ItemEvent ie)
	{
		String stri = (String)ie.getItem();
		if(stri.equals("Status Bar"))
		{	
			System.out.println("Hey "+stri);
			if(stbar.getState())
			{
			p1.setVisible(true);
			}
			else
			{
				p1.setVisible(false);
				System.out.println("No Status Bar");
			}
		}
	}
	public void actionPerformed(ActionEvent e1)
	{
		String str = e1.getActionCommand();
		System.out.println(str+" Was Clicked");
		if(str.equals("About Us"))
		{
			MyDialog md = new MyDialog(f);
			md.setVisible(true);
		}
		else if(str.equals("Cut"))
		{
			strp=ta.getSelectedText();
			ta.setText(ta.getText().replace(strp,""));
		}
		else if(str.equals("Copy"))
		{
			strp=ta.getSelectedText();
			System.out.println("Selected Text ="+strp);
		}
		else if(str.equals("Paste"))
		{
			ta.replaceRange(strp,ta.getSelectionStart(),ta.getSelectionEnd());
		}
		
		else if(str.equals("Open"))
		{
			MyFileDialog mfd = new MyFileDialog(f);
		}
		else if(str.equals("New"))
		{
			if((ta.getText()).equals(""))
			{	
				if(!(str6.equals("Untitled")))
				{
					MyDialog5 msd5 = new MyDialog5(f);
					msd5.setVisible(true);
				}
			}
			else
			{
				MyDialog5 msd5 = new MyDialog5(f);
				msd5.setVisible(true);
			}
			
		}
		else if(str.equals("Save"))
		{
			MyFileDialog1 mfd1 = new MyFileDialog1(f,str);
		}
		else if(str.equals("Save As..."))
		{
			MyFileDialog1 mfd2 = new MyFileDialog1(f,str);
		}
		else if(str.equals("Exit"))
		{
			try{
		
		System.out.println("str6 ::"+str6);
		if(!((ta.getText()).equals("")))
		{
		if(str6.equals("Untitled"))
		{
			MyDialog4 md4 = new MyDialog4(f);
			md4.setVisible(true);
		}
		}
		else{
		f.setVisible(false);
		f.dispose();}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
		else if(str.equals("Find..."))
		{
			MyDialog1 md1 = new MyDialog1(f);
			md1.setVisible(true);
		}
		else if(str.equals("Find Next"))
		{
			try
			{ 
				 System.out.println(ttext);
				 if(ttext.contains(text))
				 {
					 start=ttext.indexOf(text,start);
					   System.out.println(text+start); 
                             if(start==-1){
                             start=0;
                             start=ttext.indexOf(text,start);
                             }
                             if(start!=0){
                           ta.select(start,start+text.length());} 

                           start+=text.length()+1;  
				 }
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
				 }
		}
		else if(str.equals("Replace"))
		{
			MyDialog2 md2 = new MyDialog2(f);
			md2.setVisible(true);
		}
		else if(str.equals("Font..."))
		{
			MyDialog3 md3 = new MyDialog3(f);
			md3.setVisible(true);
		}
	}
	
	public static void main(String args[])
	{
		MyMenu mm = new MyMenu();
	}
	
	
	class MyDialog extends Dialog implements ActionListener
	{
		public MyDialog(Frame f1)
		{
		 super(f1,true);
		 setBackground(Color.black);
		 setForeground(Color.white);
		 setTitle("About Us");
         setLayout(new BorderLayout());
         Panel panel = new Panel();
         Button bu = new Button("Close");
		 bu.setForeground(Color.black);
		 panel.add(bu);
         add("South", panel);
         setSize(400,200);
		 setResizable(false);
		 bu.addActionListener(this);

         addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
               dispose();
            }
         });
		}
		 
		 public void actionPerformed(ActionEvent e2)
		 {
			 String str2 = e2.getActionCommand();
			 if(str2.equals("Close"))
			 {
				 dispose();
			 }
		 }
		 public void paint(Graphics g)
		 {
			g.setColor(Color.white);
			g.drawString("SamNotepad is a Notepad Just Like that of Windows.",25,90);
			g.drawString("This Was Developed By Smr_Hritik.", 25,110);
			g.drawString("SamNotepad.  Version[1.0.1721]",15,60);
			g.drawString("(c) 2017  Smr_Hritik..!! All Rights Reserved",20,150);
		 } 
	}
	
	
	class MyFileDialog extends FileDialog
	{
		public MyFileDialog(Frame f1)
		{
		 super(f1,"Open",FileDialog.LOAD);
         setSize(700,500);
		 setResizable(false);
		 setVisible(true);
		 String str4;
		 str4=getFile();
		 if(str4==null)
			 return;
		 String str5;
		 str5=getDirectory();
		 System.out.println("File To be Opened "+str4);
		 System.out.println("File To be Opened from "+str5);
		 try
		 {
			 File file1 = new File(str5,str4);
			 if(!file1.exists())
			 {
				 return;
			 }
			 FileInputStream fis = new FileInputStream(file1);
			 int ch,i=0;
			 char[] chst = new char[5000];
			 String strc;
			 while((ch=fis.read())!=-1)
			 {
				 chst[i]=(char)ch;
				 i++;
			 }
			 strc=String.copyValueOf(chst);
			 ta.setText(strc);
			 f.setTitle(str4+" - SamNotepad");
			 fis.close();
		 }
		 catch(Exception ee)
		 {
			 ee.printStackTrace();
		 }
		 
			addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
               dispose();
            }
         });
		}
		 
		 public void actionPerformed(ActionEvent e2)
		 {
			 String str3 = e2.getActionCommand();
			 System.out.println(str3+" was Clicked");
		 } 
	}
	class MyFileDialog1 extends FileDialog
	{
		public MyFileDialog1(Frame f1,String title)
		{
		 super(f1,title,FileDialog.SAVE);
         setSize(700,500);
		 setResizable(false);
		 setVisible(true);
		 
		 if(getFile()!=null)
		 {
			str6=getFile();
		 }
		 if(str6==null)
			 return;
		 
		 String str7;
		 str7 = getDirectory();
		 
		 try
		 {
		 
		 File file = new File(str7,str6);
		 if(!file.exists())
			 file.createNewFile();
		 
		 FileOutputStream fos = new FileOutputStream(file,false);
		 byte[] stringInBytes = (ta.getText()).getBytes();
		 
		 f.setTitle(str6+" - SamNotepad");
		 fos.write(stringInBytes);
		 fos.close();
		 System.out.println(str6);
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 
		 System.out.println("File To be Saved "+str6);
		 System.out.println("File Saved to "+str7);
		 
		}
	}
	
  class MyDialog1 extends Dialog implements ActionListener
	{
		TextField tf;
		public MyDialog1(Frame f1)
		{
		 super(f1,false);
		 setTitle("Find");
		 setUndecorated(false);
         setSize(400,200);
		 setResizable(false);
		 setLayout(new GridLayout(1,2));
		 Panel pa1 = new Panel();
		 Panel pa2 = new Panel();
		 pa1.setLayout(new GridBagLayout());
		 pa2.setLayout(new GridBagLayout());
		 GridBagConstraints gbc = new GridBagConstraints();
		 gbc.gridx=0;
		 gbc.gridy=0;
		 Label lf = new Label("Find What :");
		 Button bu2 = new Button("Find Next");
		 Button bu3 = new Button("Cancel");
		 tf = new TextField(7);
		
		bu2.addActionListener(this);
		bu3.addActionListener(this);
		
		pa1.add(lf,gbc);
		gbc.gridx=1;
		pa1.add(tf,gbc);
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridy=2;
		pa2.add(bu2,gbc);
		gbc.gridy=4;
		pa2.add(bu3,gbc);
		
		add(pa1);
		add(pa2);
		
         addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
               dispose();
            }
         });
		}
	
		 public void actionPerformed(ActionEvent e2)
		 {
			 String str2 = e2.getActionCommand();
			 if(str2.equals("Cancel"))
			 {
				 dispose();
			 }
			 else if(str2.equals("Find Next"))
			 {
				 try
				 { 
				 text = tf.getText();
				 ttext=ta.getText();
				 ttext=ttext.replaceAll("\r\n","\n");
				 ta.setText(ttext);
				 System.out.println(ttext);
				 if(ttext.contains(text))
				 {
					 start=ttext.indexOf(text,start);
					   System.out.println(text+start);
							
                             if(start==-1){
                             start=0;
                             start=ttext.indexOf(text,start);
                             }
                             if(start>=0){
								 f.toFront();
                           ta.select(start,start+text.length());} 

                           start+=text.length()+1;  
				 }
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
				 }
			 }
		 }
	}
	
	class MyDialog2 extends Dialog implements ActionListener
	{
		TextField tf1,tf2;
		public MyDialog2(Frame f1)
		{
		 super(f1,true);
		 setTitle("Replace");
		 
         setSize(400,200);
		 setResizable(false);
		 setLayout(new GridLayout(1,2));
		 Panel pa1 = new Panel();
		 Panel pa2 = new Panel();
		 pa1.setLayout(new GridBagLayout());
		 pa2.setLayout(new GridBagLayout());
		 GridBagConstraints gbc = new GridBagConstraints();
		 gbc.gridx=0;
		 gbc.gridy=0;
		 Label lf1 = new Label("Find What :");
		 Label lf2 = new Label("Replace With:");
		 Button bu2 = new Button("Replace");
		 Button bu3 = new Button("Replace All");
		 Button bu4 = new Button("Cancel");
		 Button bu1 = new Button("Find Next");
		 tf1 = new TextField(7);
		 tf2 = new TextField(7);
		
		bu1.addActionListener(this);
		bu2.addActionListener(this);
		bu3.addActionListener(this);
		bu4.addActionListener(this);
		
		pa1.add(lf1,gbc);
		gbc.gridx=1;
		pa1.add(tf1,gbc);
		gbc.gridy=1;
		gbc.gridx=0;
		pa1.add(lf2,gbc);
		gbc.gridx=1;
		pa1.add(tf2,gbc);
		gbc.gridx=0;
		gbc.gridy=0;
		pa2.add(bu1,gbc);
		gbc.gridy=2;
		pa2.add(bu2,gbc);
		gbc.gridy=4;
		pa2.add(bu3,gbc);
		gbc.gridy=6;
		pa2.add(bu4,gbc);
		
		add(pa1);
		add(pa2);
		
         addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
               dispose();
            }
         });
		}
		 
		 public void actionPerformed(ActionEvent e2)
		 {
			 String strx = e2.getActionCommand();
			 if(strx.equals("Cancel"))
			 {
				 dispose();
			 }
			 else if(strx.equals("Find Next"))
			 {
				 try
				 { text = tf1.getText().trim();
				 ttext=ta.getText().trim();
				 System.out.println(ttext);
				 if(ttext.contains(text))
				 {
					 start=ttext.indexOf(text,start);
					   System.out.println(text+start); 
                            if(start>=0){
                           ta.select(start,start+text.length());
						   start+=text.length()+1;} 

							if(start==-1){
                             start=0;
                             start=ttext.indexOf(text,start);
                             }
                              
				 }
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
				 }
			 }
			 else if(strx.equals("Replace"))
			 { try
				 { text = tf1.getText().trim();
				 ttext=ta.getText().trim();
				 System.out.println(ttext);
				 rtext = tf2.getText().trim();
				 
				 if(ttext.contains(text))
				 {
					 start=ttext.indexOf(text,start);
				 end=start+(text.length())+1;

				 if(rtext.length()!=0)
				 {
					 ta.replaceRange(rtext,start,end);
				 }
					   System.out.println(text+start); 
                           if(start!=0){
                           ta.select(start,end);} 

						   if(start==-1){
                             start=0;
                             start=ttext.indexOf(text,start);
							 end=start+(text.length())+1;
                             }
                            
                           start+=text.length()+1;
							end=start+(text.length())+1;
				 }
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
				 }

				 }
				 else if(strx.equals("Replace All"))
				 {
					 text = tf1.getText().trim();
				 ttext=ta.getText().trim();
				 System.out.println(ttext);
				 rtext = tf2.getText().trim();
				 ttext=ttext.replaceAll(text,rtext);
				 ta.setText(ttext);
				 }
		 }
	}
	
	class MyDialog3 extends Dialog implements ActionListener,ItemListener
	{
		String strfo,strst,strsi;
		TextField tf1,tf2,tf3;
		List li1,li2,li3;
		public MyDialog3(Frame f1)
		{
			super(f1,true);
		 setTitle("Font...");
		 
         setSize(450,600);
		 setResizable(false);
		 setLayout(new GridLayout(2,1));
		 Panel pa1 = new Panel();
		 Panel pa2 = new Panel();
		 pa1.setLayout(new GridBagLayout());
		 pa2.setLayout(new GridBagLayout());
		 GridBagConstraints gbc = new GridBagConstraints();
		 
		Label l1 = new Label("Font");
		Label l2 = new Label("Font Style");
		Label l3 = new Label("Size");
		l1.setAlignment(Label.LEFT);
		l2.setAlignment(Label.LEFT);
		l3.setAlignment(Label.LEFT);
		
		tf1 = new TextField("Arial",16);
		tf2 = new TextField("Regular",12);
		tf3 = new TextField("20",7);
		
		Button bu5 = new Button("Ok");
		Button bu6 = new Button("Cancel");
		bu5.addActionListener(this);
		bu6.addActionListener(this);
		
		li1 =new List(6,false);
		li2 =new List(6,false);
		li3 =new List(6,false);
		
		li1.addActionListener(this);
		li2.addActionListener(this);
		li3.addActionListener(this);
		
		li1.addItemListener(this);
		li2.addItemListener(this);
		li3.addItemListener(this);
		
		li1.add("Arial");
		li1.add("Algerian");
		li1.add("Britannic");
		li1.add("Broadway");
		li1.add("Calibri");
		li1.add("Cambria");
		li1.add("Chiller");
		li1.add("Elephant");
		li1.add("EuroRoman");
		li1.add("Forte");
		li2.add("Regular");
		li2.add("Bold");
		li2.add("Italic");
		li2.add("Bold+Italic");
		li3.add("8");
		li3.add("9");
		li3.add("10");
		li3.add("11");
		li3.add("12");
		li3.add("14");
		li3.add("16");
		li3.add("18");
		li3.add("20");
		li3.add("22");
		li3.add("24");
		li3.add("26");
		li3.add("28");
		li3.add("36");
		li3.add("48");
		li3.add("72");
		
		
		gbc.gridx=0;
		gbc.gridy=0;
		pa1.add(l1,gbc);
		
		gbc.gridy=1;
		pa1.add(tf1,gbc);
		
		gbc.gridy=2;
		pa1.add(li1,gbc);
		
		gbc.gridx=1;
		gbc.gridy=0;
		pa1.add(l2,gbc);
		
		gbc.gridy=1;
		pa1.add(tf2,gbc);
		
		gbc.gridy=2;
		pa1.add(li2,gbc);
		
		gbc.gridx=2;
		gbc.gridy=0;
		pa1.add(l3,gbc);
		
		gbc.gridy=1;
		pa1.add(tf3,gbc);
		
		gbc.gridy=2;
		pa1.add(li3,gbc);
		
		gbc.gridx=0;
		gbc.gridy=0;
		pa2.add(bu5,gbc);
		
		gbc.gridx=2;
		pa2.add(bu6,gbc);
		
		add(pa1);
		add(pa2);
		
         addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
               dispose();
            }
         });
		}
		
		public void actionPerformed(ActionEvent ea)
		{
			String strf = ea.getActionCommand();
			System.out.println(strf);
			if(strf.equals("Cancel"))
			{
				dispose();
			}
			else if(strf.equals("Ok"))
			{
				strfo=tf1.getText();
				strst=tf2.getText();
				strsi=tf3.getText();
				int siz = Integer.parseInt(strsi);
				/*if(strst.equals("Regular"))
				{
					ta.setFont(strfo,Font.PLAIN,siz);
				}
				else if(strst.equals("Bold"))
				{
					ta.setFont(strfo,Font.BOLD,siz);
				}
				else if(strst.equals("Italic"))
				{
					ta.setFont(strfo,Font.ITALIC,siz);
				}
				else if(strst.equals("Bold+Italic"))
				{
					ta.setFont(strfo,Font.BOLD+Font.ITALIC,siz);
				}*/
				dispose();
			}
		}
		
		public void itemStateChanged(ItemEvent ie)
	{
		tf1.setText(li1.getSelectedItem());
		tf2.setText(li2.getSelectedItem());
		tf3.setText(li3.getSelectedItem());
		
	}
	}

	class MyDialog4 extends Dialog implements ActionListener
	{
		public MyDialog4(Frame f1)
		{
			super(f1,true);
		 setTitle("SamNOTEPAD");
		 
         setSize(400,150);
		 setResizable(false);
		 setLayout(new GridLayout(2,1));
		 Panel pa1 = new Panel();
		 Panel pa2 = new Panel();
		 pa1.setLayout(new GridBagLayout());
		 pa2.setLayout(new GridBagLayout());
		 GridBagConstraints gbc = new GridBagConstraints();
		 
			Label ls1 = new Label("Do You Want to Save Changes To Untitled ?");
			Button bus1 = new Button("Save");
			Button bus2 = new Button("Don't Save");
			Button bus3 = new Button("Cancel");
			bus1.addActionListener(this);	
			bus2.addActionListener(this);	
			bus3.addActionListener(this);	
			gbc.gridx=0;
			gbc.gridy=0;
			pa1.add(ls1,gbc);

			gbc.gridx=0;
			gbc.gridy=1;
			pa2.add(bus1,gbc);
			
			gbc.gridx=1;
			pa2.add(bus2,gbc);
			
			gbc.gridx=2;
			pa2.add(bus3,gbc);
			
			add(pa1);
			add(pa2);
			
			addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
               dispose();
            }
         });
		 
		}
		
		public void actionPerformed(ActionEvent e)
		{
			String strd=e.getActionCommand();
			if(strd.equals("Save"))
			{
				MyFileDialog1 mfds= new MyFileDialog1(f,"Save");
				System.out.println("str6 in save::"+str6);
				dispose();
			}
			else if(strd.equals("Cancel"))
			{
				dispose();
			}
			else if(strd.equals("Don't Save"))
			{
			w.setVisible(false);
			w.dispose();

			}
		}
	
	}
		
	class MyDialog5 extends Dialog implements ActionListener
	{
		public MyDialog5(Frame f1)
		{
			super(f1,true);
		 setTitle("SamNOTEPAD");
		 
         setSize(400,150);
		 setResizable(false);
		 setLayout(new GridLayout(2,1));
		 Panel pa1 = new Panel();
		 Panel pa2 = new Panel();
		 pa1.setLayout(new GridBagLayout());
		 pa2.setLayout(new GridBagLayout());
		 GridBagConstraints gbc = new GridBagConstraints();
		 
			Label ls1 = new Label("Do You Want to Save Changes To "+str6+" ?");
			Button bus1 = new Button("Save");
			Button bus2 = new Button("Don't Save");
			Button bus3 = new Button("Cancel");
			bus1.addActionListener(this);	
			bus2.addActionListener(this);	
			bus3.addActionListener(this);	
			gbc.gridx=0;
			gbc.gridy=0;
			pa1.add(ls1,gbc);

			gbc.gridx=0;
			gbc.gridy=1;
			pa2.add(bus1,gbc);
			
			gbc.gridx=1;
			pa2.add(bus2,gbc);
			
			gbc.gridx=2;
			pa2.add(bus3,gbc);
			
			add(pa1);
			add(pa2);
			
			addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
               dispose();
            }
         });
		 
		}
		
		public void actionPerformed(ActionEvent e)
		{
			String strd=e.getActionCommand();
			if(strd.equals("Save"))
			{
				MyFileDialog1 mfds= new MyFileDialog1(f,"Save");
				System.out.println("str6 in save::"+str6);
				dispose();
				if(!(str6.equals("Untitled")))
				{
					ta.setText("");
			str6="Untitled";
			f.setTitle(str6+" - SamNOTEPAD");
				}
				
			}
			else if(strd.equals("Cancel"))
			{
				dispose();
			}
			else if(strd.equals("Don't Save"))
			{
			ta.setText("");
			str6="Untitled";
			f.setTitle(str6+" - SamNOTEPAD");
			dispose();
			}
		}
	
	}
}	
