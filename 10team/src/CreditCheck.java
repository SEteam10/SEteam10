package Check;

public class CreditCheck {	//�����߰��� ������û���������� �ʰ��ϴ��� üũ
	
	public static int creditCheck(String[][] totalData, int row, int selectedCredit, int maxCredit){
		String credit;
		credit=totalData[row][4].trim();
		selectedCredit=selectedCredit+Integer.parseInt(credit);
		if(selectedCredit>maxCredit){
			selectedCredit=selectedCredit-Integer.parseInt(credit);
			return 2;
		}
		selectedCredit=selectedCredit-Integer.parseInt(credit);
		return 1;
	}

}
