package view;

import java.util.Scanner;

public class LoginView {


	public void login() {

		String username;
		String password;

		Scanner entrada = new Scanner(System.in);
		System.out.println("********LOGIN********");
		System.out.println("USERNAME: ");
		username = entrada.next();
		System.out.println("PASSWORD: ");
		password = entrada.next();
		System.out.println("******LOGADO COM SUCESSO!******");


		MenuView menu = new MenuView();

		menu.menu();
	}









}