package tests;

import views.MainWindow;
import controllers.WordGenerator;

public class MainWindowTest {

	public static void main(String[] args) {
		MainWindow window = new MainWindow(new WordGenerator());
		window.init();
	}
}