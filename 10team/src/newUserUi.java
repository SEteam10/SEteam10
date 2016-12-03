package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Data.studentInformation;

public class newUserUi extends JFrame implements ActionListener {
   
   //새내기 선택하였을경우 뜨는 UI, 각각 학적정보입력 받은 후 mainUI 호출
   
   private studentInformation sInformation;
   private JPanel contentPane;
   private JTextField textMajorC;
   private JTextField textLiveralC;
   private JTextField textName;
   private JTextField textDept;
   private JTextField textGrade;
   private JTextField textGraduationC;
   private JTextField textApplicationC;
   private JTextField textSemester;
   private JButton startButton;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               newUserUi frame = new newUserUi();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public newUserUi() {
      sInformation = new studentInformation();

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 231, 343);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JLabel lblNewLabel = new JLabel("이름: ");
      lblNewLabel.setBounds(12, 47, 40, 21);
      contentPane.add(lblNewLabel);

      JLabel lblNewLabel_1 = new JLabel("학과: ");
      lblNewLabel_1.setBounds(12, 73, 40, 21);
      contentPane.add(lblNewLabel_1);

      JLabel lblNewLabel_2 = new JLabel("수강학기 : ");
      lblNewLabel_2.setBounds(12, 104, 65, 21);
      contentPane.add(lblNewLabel_2);

      JLabel label = new JLabel("수강학점 : ");
      label.setBounds(12, 135, 65, 21);
      contentPane.add(label);

      JLabel lblNewLabel_3 = new JLabel("전공학점");
      lblNewLabel_3.setBounds(75, 135, 55, 21);
      contentPane.add(lblNewLabel_3);

      JLabel lblNewLabel_4 = new JLabel("교양학점");
      lblNewLabel_4.setBounds(75, 159, 55, 21);
      contentPane.add(lblNewLabel_4);

      textMajorC = new JTextField();
      textMajorC.setBounds(130, 135, 73, 21);
      contentPane.add(textMajorC);
      textMajorC.setColumns(10);

      textLiveralC = new JTextField();
      textLiveralC.setBounds(130, 159, 73, 21);
      contentPane.add(textLiveralC);
      textLiveralC.setColumns(10);

      textName = new JTextField();
      textName.setBounds(47, 47, 156, 21);
      contentPane.add(textName);
      textName.setColumns(10);

      textDept = new JTextField();
      textDept.setBounds(47, 73, 156, 21);
      contentPane.add(textDept);
      textDept.setColumns(10);

      textGrade = new JTextField();
      textGrade.setBounds(75, 104, 20, 21);
      contentPane.add(textGrade);
      textGrade.setColumns(10);

      JLabel lblNewLabel_5 = new JLabel("졸업학점 : ");
      lblNewLabel_5.setBounds(12, 188, 65, 21);
      contentPane.add(lblNewLabel_5);

      textGraduationC = new JTextField();
      textGraduationC.setBounds(75, 188, 128, 21);
      contentPane.add(textGraduationC);
      textGraduationC.setColumns(10);

      JLabel lblNewLabel_6 = new JLabel("신청가능학점 : ");
      lblNewLabel_6.setBounds(12, 219, 126, 21);
      contentPane.add(lblNewLabel_6);

      textApplicationC = new JTextField();
      textApplicationC.setBounds(98, 219, 84, 21);
      contentPane.add(textApplicationC);
      textApplicationC.setColumns(10);

      startButton = new JButton("시간표만들기");
      startButton.setBounds(12, 250, 191, 43);
      contentPane.add(startButton);
      startButton.addActionListener(this);

      textSemester = new JTextField();
      textSemester.setColumns(10);
      textSemester.setBounds(130, 104, 20, 21);
      contentPane.add(textSemester);

      JLabel label_1 = new JLabel("\uD559\uB144");
      label_1.setBounds(98, 104, 30, 21);
      contentPane.add(label_1);

      JLabel label_2 = new JLabel("\uD559\uAE30");
      label_2.setBounds(155, 104, 30, 21);
      contentPane.add(label_2);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      if (e.getSource() == startButton) {
         sInformation.setApplicationC(Integer.parseInt(textApplicationC.getText()));
         sInformation.setDept(textDept.getText());
         sInformation.setGrade(Integer.parseInt(textGrade.getText()));
         sInformation.setSemester(Integer.parseInt(textSemester.getText()));
         sInformation.setGraduationC(Integer.parseInt(textGraduationC.getText()));
//         sInformation.setLiveralC(Integer.parseInt(textLiveralC.getText()));
//         sInformation.setMajorC(Integer.parseInt(textMajorC.getText()));
         sInformation.setName(textName.getText());
//         sInformation.setTotalC(sInformation.getMajorC() + sInformation.getLiveralC());
         sInformation.setSaveRow(0);
         mainUi.main(sInformation);
         this.dispose();
      }
   }
}