import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class MainUi implements MouseListener, ActionListener {
	
	//����ȭ��
	private static StudentInformation sInformation; // ��������
	private int saveRow=0;
	private static File file; // �ð�ǥ����
	private static File newFile;	//���� �ҷ����� ����
	
	protected static String[][] totalData = null; // �ð�ǥ��ü������
	protected static String[][] selectedData = null; // �����������
	protected static int[][] classTimeData = null; // ��ü����ȯ������
	protected static int[][] selectedTimeData = null; // �������ȯ������
	private static int[][] iTime = null; // int�� Ÿ�ӵ�����
	private static int[][] newItime=null;

	private static JFrame frmYuMake;
	private JScrollPane totalScrollPane;
	private JScrollPane selectedScrollPane;
	private JTable totalTable;
	protected JTable selectedTable;

	protected static JLabel creditLabel;

	private DefaultTableModel tableModel; // �⺻���̺��
	protected static DefaultTableModel selectedModel; // ���õȰ������̺��
	private String[] saTit = new String[] { "������ȣ", "�г�", "�̼�����", "�����", "����", "��米��", "���ǽð�", "���ǽ�", "�����а�", "���" };
	private JButton addButton;
	private JButton deleteButton;
	private JButton resetButton;
	private JButton makeTimeTableButton;
	private JButton searchButton;
	private JButton myclassAddButton;
	private int row;
	protected static int rowCount;
	protected static int colCount;
	protected static int selectedDataRow = 0;
	protected static int maxCredit = 0;
	protected static int selectedCredit = 0;
	
	private JMenuItem helpMenuItem;
	private JMenuItem informationMenuItem;
	private JMenuItem newFileOpenItem;
	private JMenuItem saveFileItem;
	
	private static GetData getData=new GetData();
	protected static CreditData credit=new CreditData();
	protected static TimeData timeData=new TimeData();
	protected static ChangeData change=new ChangeData();
	protected static ClassAdd add = new ClassAdd();
	protected static OverlapCheck overLap =new OverlapCheck();
	protected static CreditCheck creditCheck=new CreditCheck();
	

	public static void main(StudentInformation sInformation) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					file = FileSelect.fileSelect();

					rowCount=getData.count(1, file);
					colCount =getData.count(2, file);
					
					totalData=getData.getTotalDataSize(file, totalData);
					totalData=getData.getTotalData(file, totalData);
					classTimeData=getData.getClassTimeDataSize(file, classTimeData);
					
					selectedData=new String[50][colCount];
					selectedTimeData=new int[50][colCount];

					MainUi window = new MainUi(sInformation);
					window.frmYuMake.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainUi() {}

	public MainUi(StudentInformation sInformation) {		
		
		this.sInformation=sInformation;
		totalData = getData.getTotalData(file, totalData); // totalData�����ϵ������о����
		iTime=change.changeTimeData(rowCount, totalData);
		//iTime = ChangeData.changeTimeData(rowCount, totalData); // �����ð���ū���γ���������
		
		classTimeData=timeData.makeTimeData(classTimeData, iTime, rowCount);		
		
		frmYuMake = new JFrame();
		frmYuMake.setTitle("YU MAKE");
		frmYuMake.setBounds(100, 100, 900, 650);
		frmYuMake.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmYuMake.getContentPane().setLayout(null);

		// ���̺�
		totalScrollPane = new JScrollPane();
		totalScrollPane.setBounds(12, 93, 849, 196);
		frmYuMake.getContentPane().add(totalScrollPane);

		tableModel = new DefaultTableModel(totalData, saTit);
		totalTable = new JTable(tableModel);
		totalTable.addMouseListener(this);
		totalTable.setFocusable(false);
		totalScrollPane.setViewportView(totalTable);

		totalTable.setAutoCreateRowSorter(true);
		TableRowSorter Tsorter = new TableRowSorter(totalTable.getModel());
		totalTable.setRowSorter(Tsorter);

		selectedScrollPane = new JScrollPane();
		selectedScrollPane.setBounds(12, 342, 849, 199);
		frmYuMake.getContentPane().add(selectedScrollPane);

		selectedModel = new DefaultTableModel(saTit, 0);
		selectedTable = new JTable(selectedModel);
		selectedTable.addMouseListener(this);
		// selectedTable.setFocusable(false);
		selectedScrollPane.setViewportView(selectedTable);

		// �л����� : �̸�
		JLabel name = new JLabel(sInformation.getName());
		name.setBounds(12, 63, 45, 15);
		frmYuMake.getContentPane().add(name);
		// �а�
		JLabel dept = new JLabel(sInformation.getDept());
		dept.setBounds(74, 63, 80, 15);
		frmYuMake.getContentPane().add(dept);
		// �г�
		JLabel grade = new JLabel(sInformation.getGrade() + "�г�");
		grade.setBounds(175, 63, 35, 15);
		frmYuMake.getContentPane().add(grade);
		// �б�
		JLabel semester = new JLabel(sInformation.getSemester() + "�б�");
		semester.setBounds(227, 63, 35, 15);
		frmYuMake.getContentPane().add(semester);
		// ��û��������
		maxCredit = sInformation.getApplicationC();

		creditLabel = new JLabel("New label");
		creditLabel.setBounds(673, 304, 188, 23);
		frmYuMake.getContentPane().add(creditLabel);
		creditLabel.setText("��û�������� : " + selectedCredit + " / " + maxCredit);

		// ��ư
		addButton = new JButton("�߰�");
		addButton.setEnabled(false);
		addButton.setBounds(12, 304, 97, 23);
		frmYuMake.getContentPane().add(addButton);
		addButton.addActionListener(this);

		deleteButton = new JButton("����");
		deleteButton.setEnabled(false);
		deleteButton.setBounds(126, 304, 97, 23);
		frmYuMake.getContentPane().add(deleteButton);
		deleteButton.addActionListener(this);

		resetButton = new JButton("�ʱ�ȭ");
		resetButton.setBounds(764, 556, 97, 23);
		frmYuMake.getContentPane().add(resetButton);
		resetButton.addActionListener(this);

		makeTimeTableButton = new JButton("YU MAKE");
		makeTimeTableButton.setBounds(626, 556, 127, 23);
		frmYuMake.getContentPane().add(makeTimeTableButton);
		makeTimeTableButton.addActionListener(this);

		searchButton = new JButton("�˻�");
		searchButton.setBounds(764, 58, 97, 23);
		frmYuMake.getContentPane().add(searchButton);
		searchButton.addActionListener(this);
		
		myclassAddButton = new JButton("���� �߰�");
		myclassAddButton.setBounds(240, 304, 111, 23);
		frmYuMake.getContentPane().add(myclassAddButton);
		myclassAddButton.addActionListener(this);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 878, 31);
		frmYuMake.getContentPane().add(menuBar);	
		
		JMenu mnNewMenu = new JMenu("����");
		menuBar.add(mnNewMenu);	
		
		newFileOpenItem = new JMenuItem("�ҷ�����");
		mnNewMenu.add(newFileOpenItem);
		newFileOpenItem.addActionListener(this);
		
		saveFileItem = new JMenuItem("�����ϱ�");
		mnNewMenu.add(saveFileItem);
		saveFileItem.addActionListener(this);
		
		JMenu mnNewMenu_1 = new JMenu("����");
		menuBar.add(mnNewMenu_1);		
		
		helpMenuItem = new JMenuItem("����");
		mnNewMenu_1.add(helpMenuItem);
		helpMenuItem.addActionListener(this);
		
		informationMenuItem = new JMenuItem("���α׷�����");
		mnNewMenu_1.add(informationMenuItem);		
		informationMenuItem.addActionListener(this);		
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addButton) {
			if (creditCheck.creditCheck(totalData, row, selectedCredit, maxCredit) == 2) {
				JOptionPane.showMessageDialog(null, "��û���������� �ʰ��߽��ϴ�.");
			} else if (overLap.classNameOverlapCheck(row, selectedDataRow, totalData, selectedData) == 2
					|| overLap.classOverlapCheck(row, selectedDataRow, totalData, selectedData) == 2) {
				JOptionPane.showMessageDialog(null, "�̹� �߰� �� �����Դϴ�.");
			} 
			else if (overLap.timeOverlapCheck(row, classTimeData, selectedTimeData, selectedDataRow)==2){
				
				JOptionPane.showMessageDialog(null, "�ð� �ߺ��Դϴ�.");
			}			
			else {
				add.add(totalData, selectedData, classTimeData, selectedTimeData, row, selectedDataRow);
				add.setSelectedTable(selectedModel, selectedData[selectedDataRow]);
				
				selectedCredit = credit.addCredit(selectedData, selectedDataRow, selectedCredit);			
				selectedDataRow++;
			}
			creditLabel.setText("��û�������� : " + selectedCredit + " / " + maxCredit);

		}

		if (e.getSource() == deleteButton) {
			
			if(selectedData[row][0]==null){
				ClassDelete delete=new ClassDelete();
				delete.deleteSelectedData(selectedData, row, selectedDataRow, colCount);
				delete.deleteClassTimeData(row, selectedDataRow, colCount, selectedTimeData);
				
				
				selectedModel.removeRow(row);
				selectedTable.setModel(selectedModel);			
				
				selectedDataRow--;
			}
			else{
			selectedCredit = credit.deleteCredit(selectedData, row, selectedCredit);
			//selectedCredit = CreditData.deleteCredit(selectedData, row, selectedCredit);
			
			ClassDelete delete=new ClassDelete();
			delete.deleteSelectedData(selectedData, row, selectedDataRow, colCount);
			delete.deleteClassTimeData(row, selectedDataRow, colCount, selectedTimeData);
			
			
			selectedModel.removeRow(row);
			selectedTable.setModel(selectedModel);			
			
			selectedDataRow--;
			}

			creditLabel.setText("��û�������� : " + selectedCredit + " / " + maxCredit);
		}

		if (e.getSource() == resetButton) {
			
			ClassReset reset =new ClassReset();
			reset.resetSelectedTable(selectedData, selectedTimeData, selectedDataRow, colCount);
			//ClassReset.resetSelectedTable(selectedData,selectedHms, selectedDataCol, colCount);
			
			selectedModel.setRowCount(0);
			
			selectedCredit=credit.resetCredit(selectedCredit);
			//selectedCredit = CreditData.resetCredit(selectedCredit);
			
			selectedDataRow=0;
			creditLabel.setText("��û�������� : " + selectedCredit + " / " + maxCredit);
			
		}

		if (e.getSource() == makeTimeTableButton) {
			TimeTableUi.getTimeTable(selectedDataRow, selectedTimeData, selectedData);
		}

		
		
		if(e.getSource()==newFileOpenItem){
			newFile = FileSelect.fileSelect();
			
			rowCount=getData.count(1, newFile);
			colCount =getData.count(2, newFile);
			
			totalData =getData.getTotalDataSize(newFile, totalData);
			totalData =getData.getTotalData(newFile, totalData);
			classTimeData=getData.getClassTimeDataSize(newFile, classTimeData);
			newItime=change.changeTimeData(rowCount, totalData);
			classTimeData=timeData.makeTimeData(classTimeData, newItime, rowCount);
			
			
			tableModel = new DefaultTableModel(totalData, saTit);
			totalTable.setModel(tableModel);
			
		}
		
		if(e.getSource()==saveFileItem){
			ClassSave save=new ClassSave();
			save.classSave(sInformation, selectedData, selectedDataRow, colCount);
			//ClassSave.classSave(sInformation, selectedData,selectedDataCol,colCount);
		}
		
		if(e.getSource()==helpMenuItem){
			HelpUi.main(null);
		}
		
		if(e.getSource()==informationMenuItem){
			InformationUi.main(null);
		}
		
		if (e.getSource() == searchButton) {
			SearchUi.main();
		}
		
		if(e.getSource()==myclassAddButton){
			MyClassUi.main();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == totalTable) {
			JTable jtable = (JTable) e.getSource();
			row = jtable.getSelectedRow();
			deleteButton.setEnabled(false);
			if (row > -1) {
				addButton.setEnabled(true);
			}
		}
		if (e.getSource() == selectedTable) {
			JTable jtable = (JTable) e.getSource();
			row = jtable.getSelectedRow();
			addButton.setEnabled(false);
			if (row > -1) {
				deleteButton.setEnabled(true);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
}
