package pre_proj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TestMain {
	public static void main(String[] args) throws IOException {
		rent();
	}

	private static void rent() throws FileNotFoundException, IOException {

		File file = new File("C:\\Users\\3P003\\Desktop\\도서 목록.txt");

		FileInputStream inFile = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inFile));

		File temp = new File("C:\\Users\\3P003\\Desktop\\임시 도서 목록.txt");
		temp.createNewFile();

		FileOutputStream outFile = new FileOutputStream(temp);
		PrintWriter writer = new PrintWriter(outFile);

		String str;
		ArrayList list = new ArrayList<String>();

		while ((str = reader.readLine()) != null) {
			System.out.println(str.split("/")[0]);

			if (str.split("/")[0].equals("3"/* 책번호 입력. */)) {

				String updateData = "";

				// 날짜....
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				DateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일");
				
				System.out.println("current: " + df.format(cal.getTime()));

				//cal.add(Calendar.MONTH, 2);
				

				updateData += str.split("/")[0] + "/";
				updateData += str.split("/")[1] + "/";
				updateData += str.split("/")[2] + "/";
				updateData += str.split("/")[3] + "/";
				updateData += str.split("/")[4] + "/";
				updateData += "대여중/";
				updateData += df.format(cal.getTime()) + "/";
				
				cal.add(Calendar.DATE, 14);
				updateData += df.format(cal.getTime());

				list.add(updateData);
			} else {
				list.add(str);
			}

		}

		// BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
		for (int i = 0; i < list.size(); i++) {
			writer.println((String) list.get(i));

			System.out.println(list.get(i));
		}

		writer.close();
		reader.close();

		file.delete();

		temp.renameTo(file);
	}
}
