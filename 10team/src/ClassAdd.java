import javax.swing.table.DefaultTableModel;

public class ClassAdd {

	public void add(String[][] totalData, String[][] selectedData, int[][] classTimeData, int[][] selectedTimeData, int row, int selectedDataRow) {
		//totalData, classHms �� ���õ� �����͸� selectedData, selectedHms�� �־��ִ� �޼ҵ� 
		selectedData[selectedDataRow] = totalData[row].clone();
		selectedTimeData[selectedDataRow]=classTimeData[row].clone();
	}
	
	public void setSelectedTable(DefaultTableModel selectedModel, String[] selectedData){	
		selectedModel.addRow(selectedData);
	}
}
