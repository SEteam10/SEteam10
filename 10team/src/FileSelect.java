import java.io.File;

import javax.swing.JFileChooser;

public class FileSelect {
	public static File fileSelect() { // ���ϼ���
		
		JFileChooser fc = new JFileChooser();	//JFileChooser ����
		File file = null;
		
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();	//file�� ���õ� ������ ����
			return file;	//���õ� ������ return
		}
		return file = null;
	}
}
