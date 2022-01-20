import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
class Project implements ActionListener
{
    JFrame f1,f2,f3;
    JRadioButton r1,r2;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13;
    JTextField t1,t2,t3,t4,t5;
    JTextArea t6;
    JScrollPane sp,sp1;
    JPasswordField p1;
    JButton b1,b2,b3,b4,b5;
    ButtonGroup bg;
    DefaultTableModel m;
    JTable ta;
    Connection c;
    Statement st;
    int p;
    ResultSet r;
    boolean b;
    String user,pass,name,clas,admn,dob,add,s;
    Project() throws Exception
    {
        f1 = new JFrame("SIGN IN");
        r1 = new JRadioButton("STUDENT");
        r2 = new JRadioButton("ADMIN");
        l1 = new JLabel("USERNAME :");
        l2 = new JLabel("PASSWORD :");
        p1 = new JPasswordField(15);
        t1 = new JTextField(15);
        b1 = new JButton("SIGN IN");
        bg = new ButtonGroup();

        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://ziggy.db.elephantsql.com/","wlbyrhor","h2S3lsOv2uB3d0GI1nM-7v9IBPtfBoBb");
        st = c.createStatement();

        f2 = new JFrame("ADMIN");
        l3 = new JLabel("NAME :");
        l4 = new JLabel("CLASS :");
        l5 = new JLabel("ADMN. NO. *:");
        l6 = new JLabel("DOB :");
        l7 = new JLabel("ADDRESS :");
        t2 = new JTextField(10);
        t3 = new JTextField(10);
        t4 = new JTextField(10);
        t5 = new JTextField(10);
        t6 = new JTextArea(10,2);
        b2 = new JButton("INSERT");
        b3 = new JButton("VIEW DATA");
        b4 = new JButton("VIEW ALL");
        b5 = new JButton("DELETE");
        sp = new JScrollPane(t6);
        m = new DefaultTableModel();
        ta = new JTable(m);
        sp1 = new JScrollPane(ta);

        f3 = new JFrame("STUDENT");
        l8 = new JLabel();
        l9 = new JLabel();
        l10 = new JLabel();
        l11 = new JLabel();
        l12 = new JLabel();
        l13 = new JLabel("STUDENT DETAILS");

        m.addColumn("NAME");
        m.addColumn("CLASS");
        m.addColumn("ADMN. NO.");
        m.addColumn("DOB");
        m.addColumn("ADDRESS");

        f1.add(r1);
        f1.add(r2);
        f1.add(l1);
        f1.add(t1);
        f1.add(l2);
        f1.add(p1);
        f1.add(b1);
        bg.add(r1);
        bg.add(r2);

        f2.add(l3);
        f2.add(t2);
        f2.add(l4);
        f2.add(t3);
        f2.add(l5);
        f2.add(t4);
        f2.add(l6);
        f2.add(t5);
        f2.add(l7);
        f2.add(sp);
        f2.add(b2);
        f2.add(b3);
        f2.add(b4);
        f2.add(b5);
        f2.add(sp1);

        l13.setFont(new Font("Times New Roman", Font.BOLD+Font.ITALIC, 20));
        f3.add(l13);
        f3.add(l8);
        f3.add(l9);
        f3.add(l10);
        f3.add(l11);
        f3.add(l12);

        r1.setBounds(50,30,100,20);
        r2.setBounds(150,30,100,20);
        l1.setBounds(25,80,100,20);
        t1.setBounds(120,80,150,20);
        l2.setBounds(20,120,100,20);
        p1.setBounds(120,120,150,20);
        b1.setBounds(100,160,100,30);

        l3.setBounds(60,10,100,20);
        t2.setBounds(115,10,100,20);
        l4.setBounds(283,10,100,20);
        t3.setBounds(343,10,100,20);
        l5.setBounds(17,40,100,20);
        t4.setBounds(115,40,100,20);
        l6.setBounds(295,40,100,20);
        t5.setBounds(343,40,100,20);
        l7.setBounds(36,70,100,20);
        sp.setBounds(115,70,158,50);
        b2.setBounds(25,130,85,20);
        b3.setBounds(120,130,110,20);
        b4.setBounds(240,130,100,20);
        b5.setBounds(350,130,85,20);
        sp1.setBounds(15,170,430,180);

        l13.setBounds(45,10,210,50);
        l8.setBounds(65,70,200,20);
        l9.setBounds(65,100,200,20);
        l10.setBounds(65,130,200,20);
        l11.setBounds(65,160,200,20);
        l12.setBounds(65,190,200,20);

        r1.addActionListener(this);
        r2.addActionListener(this);
        b1.addActionListener(this);

        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);

        f1.setSize(300,270);
        f1.setLayout(null);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(f1.EXIT_ON_CLOSE);

        f2.setSize(460,400);
        f2.setLayout(null);
        f2.setVisible(false);

        f3.setSize(300,300);
        f3.setLayout(null);
        f3.setVisible(false);
    }
    public void actionPerformed(ActionEvent e)
    {
        m.setRowCount(0);
        s = e.getActionCommand();
        try
        {
          b=false;
          if(s.equals("STUDENT"))
          {
              b1.setText("SIGN IN");
          }
          if(s.equals("ADMIN"))
          {
              b1.setText(" SIGN IN ");
          }
          if(s.equals("SIGN IN"))
          {
            user = t1.getText();
            pass = p1.getText();
            r = st.executeQuery("SELECT * FROM PROJECT WHERE ( NAME ='"+user+"' AND ADMN ='"+pass+"');");
            while(r.next())
            {
              b = true;
            }
            if(b)
            {
              r = st.executeQuery("SELECT NAME, CLASS , ADMN, TO_CHAR(DOB :: DATE, 'DD Mon YYYY'), ADDRESS FROM PROJECT WHERE ( NAME ='"+user+"' AND ADMN ='"+pass+"');");
              while(r.next())
              {
                name = r.getString("NAME");
                clas = r.getString("CLASS");
                admn = r.getString("ADMN");
                dob = r.getString("TO_CHAR");
                add = r.getString("ADDRESS");

                f3.setVisible(true);
                l8.setText("NAME : "+name);
                l9.setText("CLASS : "+clas);
                l10.setText("ADMN. NO. :"+admn);
                l11.setText("DOB : "+dob);
                l12.setText("ADDRESS : "+add);
              }
            }
            else
            {
              JOptionPane.showMessageDialog(f1,"INVALID USERNAME/PASSWORD");
            }
          }
          if(s.equals(" SIGN IN "))
          {
              user = t1.getText();
              pass = p1.getText();
              if(user.equals("admin")&&pass.equals("admin"))
              {
                  f2.setVisible(true);
              }
              else
              {
                  JOptionPane.showMessageDialog(f1,"INVALID USERNAME/PASSWORD");
              }
          }
          if(s.equals("INSERT"))
          {
            name = t2.getText();
            clas = t3.getText();
            admn = t4.getText();
            dob = t5.getText();
            add = t6.getText();
            if(name.equals("")||clas.equals("")||admn.equals("")||dob.equals("")||add.equals(""))
            {
              JOptionPane.showMessageDialog(f2,"INVALID ENTRIES");
            }
            else
            {
              r = st.executeQuery("SELECT * FROM PROJECT WHERE ADMN ='"+admn+"';");
              while(r.next())
              {
                b = true;
              }
              if(b)
              {
                JOptionPane.showMessageDialog(f2,"ADMN. NO. ALREADY EXIST!!");
              }
              else
              {
                String sql = "INSERT INTO PROJECT VALUES ('"+name+"','"+clas+"','"+admn+"','"+dob+"','"+add+"');";
                p = st.executeUpdate(sql);
                JOptionPane.showMessageDialog(f2,"SUCESSFULLY INSERTED");
              }
            }
          }
          if(s.equals("VIEW DATA"))
          {
            admn = t4.getText();
            if(admn.equals(""))
            {
              JOptionPane.showMessageDialog(f2,"ENTER THE ADMN. NO.");
            }
            else
            {
              r = st.executeQuery("SELECT * FROM PROJECT WHERE ADMN ='"+admn+"';");
              while(r.next())
              {
                b = true;
              }
              if(b)
              {
                r = st.executeQuery("SELECT NAME, CLASS , ADMN, TO_CHAR(DOB :: DATE, 'DD Mon YYYY'), ADDRESS FROM PROJECT WHERE ADMN ='"+admn+"';");
                while(r.next())
                {
                  name = r.getString("NAME");
                  clas = r.getString("CLASS");
                  admn = r.getString("ADMN");
                  dob = r.getString("TO_CHAR");
                  add = r.getString("ADDRESS");
                  m.addRow(new Object[]{name,clas,admn,dob,add});
                }
              }
              else
              {
                JOptionPane.showMessageDialog(f2,"ENTER THE CORRECT ADMN. NO.");
              }
            }
          }
          if(s.equals("VIEW ALL"))
          {
            r = st.executeQuery("SELECT NAME, CLASS , ADMN, TO_CHAR(DOB :: DATE, 'DD Mon YYYY'), ADDRESS FROM PROJECT ORDER BY ADMN;");
            while(r.next())
            {
              name = r.getString("NAME");
              clas = r.getString("CLASS");
              admn = r.getString("ADMN");
              dob = r.getString("TO_CHAR");
              add = r.getString("ADDRESS");
              m.addRow(new Object[]{name,clas,admn,dob,add});
            }
          }
          if(s.equals("DELETE"))
          {
            admn = t4.getText();
            if(admn.equals(""))
            {
              JOptionPane.showMessageDialog(f2,"ENTER THE ADMN. NO.");
            }
            else
            {
              r = st.executeQuery("SELECT * FROM PROJECT WHERE ADMN ='"+admn+"';");
              while(r.next())
              {
                b = true;
              }
              if(b)
              {
                p =st.executeUpdate("DELETE FROM PROJECT WHERE ADMN ='"+admn+"';");
                JOptionPane.showMessageDialog(f2,"SUCESSFULLY DELETED");
              }
              else
              {
                JOptionPane.showMessageDialog(f2,"ENTER THE CORRECT ADMN. NO.");
              }
            }
          }
          t1.setText("");
          p1.setText("");
          t2.setText("");
          t3.setText("");
          t4.setText("");
          t5.setText("");
          t6.setText("");
        }
        catch (Exception ex)
        {
            System.out.println("EXCEPTION : " + ex);
        }
    }
    public static void main(String[] args) throws Exception
    {
        new Project();
    }
}
