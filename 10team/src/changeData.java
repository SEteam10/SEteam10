package Data;

import java.util.StringTokenizer;

public class changeData {

	public static int[][] changeTimeData(int rowCount, String[][] totalData) {
		//���������� �ð������͸� �������� �ð������ͷ� ��ȯ
		
		String day=null;
		int iTime[][]=new int[rowCount][10];
		for(int i=0; i<rowCount; i++){
			
			day=totalData[i][6].replace(':',',');
			day = day.replace(' ', ',');
			day = day.replace('-', ',');
			day = day.replace("��", "1,");
			day = day.replace("ȭ", "2,");a
			day = day.replace("��", "3,");
			day = day.replace("��", "4,");
			day = day.replace("��", "5,");
			day = day.replace("��", "6,");
			day.trim();
			
			String[] time=new String[10];
			
			StringTokenizer st=new StringTokenizer(day,",");
			int tokenCount=0;
			while(st.hasMoreTokens()){
				time[tokenCount]=st.nextToken();
				tokenCount++;
			}
			
			for(int j=0;j<tokenCount; j++){
				iTime[i][j]=Integer.parseInt(time[j]);
			}
			
			changeMin(iTime,i, 1);
			changeMin(iTime,i, 3);
			
			if(tokenCount==10){
				changeMin(iTime,i, 6);
				changeMin(iTime,i, 8);
			}
		}		
		return iTime;
	}
	
	public static void changeMin(int iTime[][],int i, int j) {
		//45, 15�� ������ �����ð��� 0��, 30�� ������ ��ȯ
		
	      if (iTime[i][j + 1] > 10 && iTime[i][j + 1] <= 40) {
	         iTime[i][j] = iTime[i][j] * 10;
	         iTime[i][j + 1] = 5;
	      } else if (iTime[i][j + 1] > 40 && iTime[i][j + 1] <= 59) {
	         iTime[i][j] = (iTime[i][j] + 1) * 10;
	         iTime[i][j + 1] = 0;
	      } else if (iTime[i][j + 1] >= 0 && iTime[i][j + 1] <= 10) {
	         iTime[i][j] = iTime[i][j] * 10;
	         iTime[i][j + 1] = 0;
	      }
	   }
}
