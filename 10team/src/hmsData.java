package Data;

public class hmsData {

	public static int[][] makeHmsData(int[][] classHms, int[][] iTime, int rowCount) {
		//�ð�, ������ �� �ð������͸� ����, ���۽ð�, �Ѽ����ð� �����Ͽ� classHms�迭�� ����
		for (int i = 0; i < rowCount; i++) {
			classHms[i][0] = iTime[i][0];
			classHms[i][1] = ((iTime[i][1] - 90) + iTime[i][2]) / 5;
			classHms[i][2] = (iTime[i][3] + iTime[i][4] - iTime[i][1] - iTime[i][2]) / 5;
			if (iTime[i][5] == 0) {
				classHms[i][3] = 0;
				classHms[i][4] = 0;
				classHms[i][5] = 0;
			} else {
				classHms[i][3] = iTime[i][5];
				classHms[i][4] = ((iTime[i][6] - 90) + iTime[i][7]) / 5;
				classHms[i][5] = (iTime[i][8] + iTime[i][9] - iTime[i][6] - iTime[i][7]) / 5;
			}
		}
		return classHms;
	}
}
