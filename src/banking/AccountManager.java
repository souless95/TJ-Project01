package banking;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;


public class AccountManager {
	
	Scanner scan = new Scanner(System.in);
	
	HashSet<Account> myAccount;
	
	String iactNum;
	String iname;
	int ibankBal;
	int interest;

	public AccountManager() {
		myAccount = new HashSet<Account>();
	}

	//계좌개설
	public void makeAccount() {
		System.out.println();
		System.out.println("***************** 계좌선택 *****************");
		System.out.print("1.보통계좌                    ");
		System.out.println("2.신용신뢰계좌");
		System.out.println("********************************************");
		System.out.print("계좌선택: ");
		int choice = scan.nextInt();
		scan.nextLine();
		
		if(choice==1) {

			String ynChoice;
			
			System.out.println();
			System.out.println("--------------&보통계좌 개설&---------------");
			System.out.print("계좌번호: "); iactNum = scan.nextLine();
			System.out.print("고객이름: ");iname = scan.nextLine();
			System.out.print("잔고: ");ibankBal = scan.nextInt();
			System.out.print("기본이자%(정수형태로입력): ");interest = scan.nextInt();
			scan.nextLine();
			System.out.println("---------------------------------------------");

			NormalAccount normal = new NormalAccount(iactNum, iname, ibankBal,interest);
			
				if(!(myAccount.add(normal))) {
					System.out.println("중복계좌발견됨. 덮어쓸까요?(y or n)");
					System.out.print("선택:"); ynChoice = scan.nextLine();
						if (ynChoice.equalsIgnoreCase("y")) {
							myAccount.remove(normal);
							myAccount.add(normal);
							System.out.println("계좌개설이 완료되었습니다.");
						}
						else if (ynChoice.equalsIgnoreCase("n")) {
							return;
						}
				}
				else {
					System.out.println("계좌개설이 완료되었습니다.");
					myAccount.add(normal);
				}
		}

		
		else if (choice==2) {
			String cRating;
			System.out.println("--------------&신용계좌 개설&---------------");
			System.out.print("계좌번호:"); iactNum = scan.nextLine();
			System.out.print("고객이름:");iname = scan.nextLine();
			System.out.print("잔고:");ibankBal = scan.nextInt();
			System.out.print("기본이자%(정수형태로입력):");interest = scan.nextInt();
			scan.nextLine();
			System.out.print("신용등급:");cRating = scan.nextLine();
			System.out.println("---------------------------------------------");
			HighCreditAccount high = new HighCreditAccount(iactNum, iname, ibankBal,interest,cRating);
						
			if(!(myAccount.add(high))) {
				String ynChoice;
				System.out.println("중복계좌발견됨. 덮어쓸까요?(y or n)");
				System.out.print("선택:"); ynChoice = scan.nextLine();
					if (ynChoice.equalsIgnoreCase("y")) {
						myAccount.remove(high);
						myAccount.add(high);
						System.out.println("계좌개설이 완료되었습니다.");
					}
					else if (ynChoice.equalsIgnoreCase("n")) {
						return;
					}
			}
			else {
				System.out.println("계좌개설이 완료되었습니다.");
				myAccount.add(high);
			}
		}
	}
	
	//입금
	public void depositMoney() {
		
		int dptMoney=0;
			System.out.println("------------------입   금-------------------");
			System.out.println("계좌번호와 입금할 금액을 입력하세요.");
			System.out.print("계좌번호:"); iactNum = scan.nextLine();
			
			try {
				System.out.print("입금액:");dptMoney = scan.nextInt();
				scan.nextLine();
				System.out.println("---------------------------------------------");
				
				if(dptMoney>=0) {
					//리스트에 저장된 객체의 갯수만큼 반복한다  
					
					int dptDiv = dptMoney%500;
					if (dptDiv==0) {
						for(Account act : myAccount) { 
							if(iactNum.equals(act.actNum)) {
								act.InterestCal(dptMoney);				
								break;
							}
							else {
								System.err.println("찾는 계좌 정보가 없습니다.");
							}
						}
						System.out.println("입금이 완료되었습니다.");	
					}
					else {
						System.err.println("입금은 500원 단위로 가능합니다.");						
					}
				}
				else {
					System.err.println("입금액을 확인해주세요.");
				}
			}
			catch (InputMismatchException e) {
				System.err.println("숫자만 입력해주세요.");
			}
	}
	
	//출금
	public void withdrawMoney() {
		int wtwMoney=0;
		System.out.println("------------------출   금-------------------");
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호:"); iactNum = scan.nextLine();
		System.out.print("출금액:");wtwMoney = scan.nextInt();
		scan.nextLine();
		System.out.println("---------------------------------------------");
		
		if(wtwMoney>=0) {
			
			//리스트에 저장된 객체의 갯수만큼 반복한다  
			for(Account act : myAccount) { 

				if(iactNum.equals(act.actNum)) {
					if(wtwMoney<=act.bankBal) {
						int wtwDiv = wtwMoney%1000;
						
						if(wtwDiv==0) {
							act.bankBal -= wtwMoney;				
							break;
						}
						else {
							System.err.println("출금은 1000원 단위로 가능합니다.");
						}
					}
					else {
						String iChoice;                     
						System.err.println("잔고가 부족합니다. 금액전체를 출금할까요?");
						System.out.println("YES:금액전체 출금처리");
						System.out.println("NO:출금요청취소");
						System.out.print("선택: "); iChoice = scan.nextLine();
						if(iChoice.equalsIgnoreCase("yes")) {
							act.bankBal = 0;				
							break;
						}
						else if(iChoice.equalsIgnoreCase("no")) {
							return;
						}
					}
				}
				else {
					System.err.println("찾는 계좌 정보가 없습니다.");
				}
			}
			System.out.println("출금이 완료되었습니다.");
		}
		else {
			System.err.println("출금액을 확인해주세요.");
		}
	}
	
	//계좌정보 출력
	public void showAccInfo() {
		for(Account act : myAccount) {
			act.showAccInfo();
		}
		System.out.println("-------전체정보 출력이 완료되었습니다-------");
	}
	
	public void saveAccountInfo() {
		try {
			ObjectOutputStream out =
				new ObjectOutputStream(
					new FileOutputStream("src/banking/AccountInfo.obj")
				);
			
			for(Account act : myAccount) {
				out.writeObject(act);
			}
			out.close();
		}
		catch (Exception e) {
			System.err.println("계좌정보 직렬화 중 예외발생");
			e.printStackTrace();
		}
	}

	public void readAccountInfo() {
		try {
			ObjectInputStream in =
				new ObjectInputStream(
					new FileInputStream("src/banking/AccountInfo.obj")
				);
			while(true) {			
				Account act = (Account) in.readObject();
				myAccount.add(act);			
			}
			
		}
		catch(ClassNotFoundException e) {
			System.err.println("[예외]클래스없음");
		}
		catch(FileNotFoundException e) {
			System.err.println("[예외]파일없음");
		}
		catch(IOException e) {
		}
		catch (Exception e) {
			System.out.println("더 이상 복원할 객체가 없습니다.");
			e.printStackTrace();
		}
		System.out.println("계좌 정보가 복원되었습니다.");
	}
	
	public void autoSave() {
		
		try {
			PrintWriter out = new PrintWriter(
					new FileWriter("src/banking/AutoSaveAccount.txt"));
			
			for (Account act : myAccount ) {
				out.println(act);
			}		
			 
			out.close();
		}
		catch (Exception e) {
		}
	}
	
	public void choiceSave(AutoSaver dt) {
		int iChoice2;
		

		System.out.println("***************** 자동저장 *****************");
		System.out.print("1.자동저장On                   ");
		System.out.println("2.자동저장Off");
		System.out.println("********************************************");
		System.out.print("선택: "); iChoice2 = scan.nextInt();
		scan.nextLine();
		
		if(iChoice2==1) {	
			
			if(!dt.isAlive()) {
				dt.start();	
			}
			else {
				System.err.println("이미 자동저장이 실행중입니다.");
			}
		}
		else if (iChoice2==2) {
			dt.interrupt();
		}
	}
	
	
}
