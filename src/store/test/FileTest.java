package store.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import store.dao.UserDao;
import store.model.User;
import store.util.DbUtil;

public class FileTest {
	
	private DbUtil dbUtil = new DbUtil();
	private UserDao userDao = new UserDao();
	private static BufferedReader br;
	private static BufferedWriter bw;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		br.close();
		bw.close();
	}

	@Test
	public void test() throws Exception {
		int pass = 0;
		int fail = 0;
		boolean result = false;
		Connection con = dbUtil.getCon();
		br = new BufferedReader(new InputStreamReader(new FileInputStream("file/input.txt")));
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("file/out.txt")));
		String st = null;
		bw.write("----这是函数添加的测试----");
		bw.newLine();
		while ((st = br.readLine()) != null) {
			String[] s = st.split(" ");
			User user = new User(s[0],s[1]);
			result = userDao.add(con, user) > 0 ? true : false;
			if (result) {
				pass++;
				System.out.println("测试用例:    账号：" + s[0] +'\t'+"密码："+s[1]);
				bw.write("测试用例:    账号：" + s[0] +'\t'+"密码："+s[1]);
				bw.newLine();
			} else {
				fail++;
				System.out.println("测试用例:    账号：" + s[0] +"密码："+s[1]);
				bw.write("测试用例:    账号：" + s[0] +"密码："+s[1]);
				bw.newLine();
			}
		}
		System.out.println("该测试有" + (pass + fail) + "个测试用例,其中通过" + pass + "个,失败" + fail + "个");
		bw.write("该测试有" + (pass + fail) + "个测试用例,其中通过" + pass + "个,失败" + fail + "个");
	}

}
