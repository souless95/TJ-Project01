package shopping;

import java.sql.Types;

/*
Java에서 프로시저 호출하기

프로시저를 실행하기 위한 CallableStatement 객체 생성
-프로시저 호출시에는 {call 프로시저명(?, ? ...N)}
-파라미터는 in, out 순서없이 작성할 수 있다. 즉 프로시저 정의에 따라 달라진다.
*/
public class UpdateShop extends IConnectImpl{

	public UpdateShop() {
		super("education", "1234");
	}
	
	@Override
	public void execute() {
		try {
		
			csmt = con.prepareCall("{call ShopUpdateGoods(?, ? , ?, ?, ?)}");

			csmt.setString(1, scanValue("상품명"));			
			csmt.setString(2, scanValue("가격"));
			csmt.setString(3, scanValue("제품코드"));
			csmt.setString(4, scanValue("일련번호"));

			csmt.registerOutParameter(5, Types.NUMERIC);

			csmt.execute();

			int affected = csmt.getInt(5);
			
			if(affected==0)
				System.out.println("상품정보를 찾을 수 없습니다.");
			else
				System.out.println(affected + "개 상품정보를 수정했습니다.");				

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
	public static void main(String[] args) {
		new UpdateShop().execute();
	}

}
