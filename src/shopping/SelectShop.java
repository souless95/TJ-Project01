package shopping;

import java.sql.SQLException;
import java.util.Scanner;

public class SelectShop extends IConnectImpl {

	public SelectShop() {
		super("education","1234");
	}
	
	@Override
	public void execute() {
		
		try {
			
			while(true) {
				
				String scname = scanValue("검색 상품명");
				
				String query = "SELECT g_idx, goods_name, ltrim(to_char(goods_price,'999,900')) price, "
						+ " regidate, p_code "
						+ " FROM sh_goods "
						+ " WHERE goods_name like '%" + scname + "%' ";

				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				
				while(rs.next()) {
					String idx = rs.getString("g_idx");
					String name = rs.getString("goods_name");
					String price = rs.getString("price");					
					String regidate = rs.getString("regidate");
					String code = rs.getString("p_code");
					
					System.out.println();
					System.out.println("****************************상품을 찾았습니다.****************************");
					System.out.printf("%s 상품명:%s / 가격:%s / 등록일:%s / 상품코드:%s \n",
							idx, name, price, regidate, code);
					System.out.println("**************************************************************************");
					System.out.println();
					
				}
				
				
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

	public static void main(String[] args) {
		new SelectShop().execute();
	}

}
