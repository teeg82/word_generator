import java.util.Random;

public class NameGen {
	static String word = "";

	public static void main(String Args[]) {
		Random r = new Random();
		addCons(r);
		addVows(r);
		addCons(r);
		addVows(r);
		addCons(r);
		System.out.println(word);
	}

	public static void addCons(Random r) {
		String Cons[] = { "b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z" };
		int c = (1 + r.nextInt(100));
		
		if ( c > 0 && c <= 75)
			c = 1;
		else if (c >= 76 && c <= 90)
			c = 2;
		else if (c >= 91)
			c = 3;
		
		for (int i = 0; i < c; i++)
			word += Cons[r.nextInt(20)];
	}

	public static void addVows(Random r) {
		String[] Vows = { "a", "e", "i", "o", "u", "y" };
		int v = (1 + r.nextInt(2));
		for (int e = 0; e < v; e++) {
			word += Vows[r.nextInt(6)];
		}
	}
}