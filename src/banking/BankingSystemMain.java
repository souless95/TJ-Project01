package banking;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain {
	
	
	public static void menuShow() {
		
		System.out.println("1차프로젝트(학원)");
		System.out.println("클론 후 작업01");
		
		System.out.println();
		System.out.println("******************* MENU *******************");
		System.out.print("1.계좌개설       ");
		System.out.print("2.입금       ");
		System.out.println("3.출금");
		System.out.print("4.계좌정보출력   ");
		System.out.print("5.저장옵션   ");
		System.out.println("6.프로그램종료");
		System.out.println("********************************************");
		System.out.print("메뉴선택: ");
		 
	}
	
	public static void main(String[] args) {
			
		Scanner scan = new Scanner(System.in);
		
		//핸들러 객체와 핸들러 객체를 담은 오토세이버 객체 생성	
		AccountManager handler = new AccountManager();
		AutoSaver dt = new AutoSaver(handler);
		dt.setName("데몬쓰레드");
		dt.setDaemon(true);
		
		handler.readAccountInfo();
		
		int choice = 0;

		while(true) {
				
			try {
				menuShow();
				choice = scan.nextInt();
				System.out.println();
			
				if (choice<0||choice>6) {
					MenuSelectException mx = new MenuSelectException();
					throw mx;
				}
			}
			catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("숫자만 입력해주세요.");
			}
			catch (MenuSelectException e) {
				System.out.println(e.getMessage());
			}
			


			//선택한 번호에 따라 switch문으로 각 메서드 호출
			switch(choice) {
			case ICustomDefine.MAKE:
				handler.makeAccount();
				break;
			case ICustomDefine.DEPOSIT:
				handler.depositMoney();
				break;
			case ICustomDefine.WITHDRAW:
				handler.withdrawMoney();
				break;
			case ICustomDefine.INQUIRE:
				handler.showAccInfo();
				break;
			case ICustomDefine.THREAD:
				handler.choiceSave(dt);
				break;		
			case ICustomDefine.EXIT:
				System.out.println("프로그램종료");
				handler.saveAccountInfo();
				return;
			}
		}
	}
}
