package pre_proj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TestMain {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\82109\\OneDrive\\바탕 화면\\도서 목록.txt"));
		int ch;
		String str;
		ArrayList list = new ArrayList<String>();
		
		//while ((ch = reader.read()) != -1) {
		//	System.out.print((char) ch);
		//}
		
		while ((str = reader.readLine()) != null) {
			
			list.add(str);
			System.out.println(str);
			System.out.println(list);
		}
	}
}
