
public class overlapCheck {

	public static int classNameOverlapCheck(int row, int selectedDataCol, String[][] totalData,
			String[][] selectedData) {	//�̸��� ���� ������ �̹� ���õǾ����� üũ
		for (int i = 0; i < selectedDataCol; i++) {
			if (totalData[row][3].equals(selectedData[i][3])) {
				return 2;
			}
		}
		return 1;
	}

	public static int classOverlapCheck(int row, int selectedDataCol, String[][] totalData, String[][] selectedData) {
		for (int i = 0; i < selectedDataCol; i++) {	//�����ȣ�� ���� ���� �ִ��� üũ
			if (totalData[row][0].equals(selectedData[i][0])) {
				return 2;
			}
		}
		return 1;
	}
	
	public static int timeOverlapCheck(int row, int[][] classHms,int[][] selectedHms, int selectedDataCol) {
		//�ߺ��Ǵ� �ð��� ������ �ִ��� üũ
		int Day, startTime, classClock, finishTime;		
		if(classHms[row][3]==0){
			Day = classHms[row][0];
			startTime = classHms[row][1];
			classClock = classHms[row][2];
			finishTime = startTime + classClock - 1;

			for (int i = 0; i < selectedDataCol; i++) {
				if (classHms[row][0] == selectedHms[i][0]) {
					if (compareTime.compareHms(selectedHms[i][1], selectedHms[i][1] + selectedHms[i][2] - 1, startTime, finishTime) == 2) {
						return 2;
					}
				}
				if (classHms[row][0] == selectedHms[i][3]) {
					if (compareTime.compareHms(selectedHms[i][4], selectedHms[i][4] + selectedHms[i][5] - 1, startTime, finishTime) == 2) {
						return 2;
					}
				}
			}
		}
		else{
			Day = classHms[row][0];
			startTime = classHms[row][1];
			classClock = classHms[row][2];
			finishTime = startTime + classClock - 1;

			System.out.println("111");
			
			for (int i = 0; i < selectedDataCol; i++) {
				if (classHms[row][0] == selectedHms[i][0]) {
					if (compareTime.compareHms(selectedHms[i][1], selectedHms[i][1] + selectedHms[i][2] - 1, startTime, finishTime) == 2) {
						return 2;
					}
				}
				if (classHms[row][0] == selectedHms[i][3]) {
					if (compareTime.compareHms(selectedHms[i][4], selectedHms[i][4] + selectedHms[i][5] - 1, startTime, finishTime) == 2) {
						return 2;
					}
				}
			}
			System.out.println("222");
			Day = classHms[row][3];
			startTime = classHms[row][4];
			classClock = classHms[row][5];
			finishTime = startTime + classClock - 1;
			
			for (int i = 0; i < selectedDataCol; i++) {
				if (classHms[row][0] == selectedHms[i][0]) {
					if (compareTime.compareHms(selectedHms[i][1], selectedHms[i][1] + selectedHms[i][2] - 1, startTime, finishTime) == 2) {
						return 2;
					}
				}
				if (classHms[row][0] == selectedHms[i][3]) {
					if (compareTime.compareHms(selectedHms[i][4], selectedHms[i][4] + selectedHms[i][5] - 1, startTime, finishTime) == 2) {
						return 2;
					}
				}
			}
			System.out.println("333");
		}
		System.out.println("444");
		return 1;
	}
}
