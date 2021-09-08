package tests;

public class RoundingTest {

	public static void main(String[] args){
		float test1 = 9.1f;
		int test2 = (int) test1;
		int test3 = (int) ((test1 - test2) * 10);
		System.out.println("Test1 = " + test1 + ", test2 = " + test2 + ", test3 = " + test3);
	}
}