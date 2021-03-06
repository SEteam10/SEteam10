import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class existUserUi extends JFrame implements ActionListener {
   
   //기존에 사용한 학생일 경우 뜨는 UI, 수강가능학점만 입력받은 후 mainUI 호출
   
   private studentInformation sInformation;
   private JPanel contentPane;
   private JTextField textApplicationC;
   private JButton startButton;
   private static String[] loginData;
   private static int rowCount;
   private static File file;   //학적정보파일
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               
               //fileSelect();
               file=fileSelect.fileSelect();
               rowCount = getData.count(1, file);
               loginData = new String[8];
               existUserUi frame = new existUserUi();
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
   public existUserUi() {
      
      sInformation = new studentInformation();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 203, 141);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
    
      try {
          Workbook wb = null;
          wb = Workbook.getWorkbook(file);
          Sheet sheet = wb.getSheet(0);
          for (int i = 0; i < 8; i++) {
                Cell cell = sheet.getCell(i, 0);
                if (cell == null)
                   continue;
                loginData[i] = cell.getContents();
             }         
       }

       catch (Exception e) {
          e.printStackTrace();
       }

      JLabel lblNewLabel = new JLabel("신청가능학점 : ");
      lblNewLabel.setBounds(12, 21, 91, 21);
      contentPane.add(lblNewLabel);
      
      textApplicationC = new JTextField();
      textApplicationC.setBounds(96, 21, 77, 21);
      contentPane.add(textApplicationC);
      textApplicationC.setColumns(10);
      
      startButton = new JButton("시간표 만들기");
      startButton.setBounds(12, 52, 161, 36);
      contentPane.add(startButton);
      startButton.addActionListener(this);   
   }
   
   
//   public static void fileSelect() {
//         fc = new JFileChooser();
//         file = null;
//
//         if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//            file = fc.getSelectedFile();
//         }
//      }

   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      if ( e.getSource() == startButton ){
         sInformation.setApplicationC(Integer.parseInt(textApplicationC.getText()));
         sInformation.setDept(loginData[1]);
         sInformation.setGrade(Integer.parseInt(loginData[2]));
         sInformation.setSemester(Integer.parseInt(loginData[3]));
         sInformation.setGraduationC(Integer.parseInt(loginData[4]));
//         sInformation.setLiveralC(Integer.parseInt(loginData[5]));
//         sInformation.setMajorC(Integer.parseInt(loginData[4]));
         sInformation.setName(loginData[0]);
         sInformation.setTotalC(Integer.parseInt(loginData[5]));
         sInformation.setSaveRow(rowCount);
         System.out.println(sInformation.getDept());
         
         mainUi.main(sInformation);
         this.dispose();
      }
      
   }
}