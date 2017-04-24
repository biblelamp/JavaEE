package simplewebapp.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import simplewebapp.beans.Product;
import simplewebapp.beans.UserAccount;

public class DBUtils {

	public static UserAccount findUser(Connection conn, String userName,
			String password) throws SQLException {

		String sql = "SELECT user_name, gender, password FROM " +
	        "user_account WHERE user_name = ? and password = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String gender = rs.getString("gender");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            return user;
        }
        return null;
	}
 
	public static UserAccount findUser(Connection conn, String userName)
			throws SQLException {

		String sql = "SELECT user_name, gender, password FROM " +
	        "user_account WHERE user_name = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String password = rs.getString("password");
			String gender = rs.getString("gender");
			UserAccount user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);
			user.setGender(gender);
			return user;
		}
		return null;
	}

	public static List<Product> queryProduct(Connection conn)
			throws SQLException {

		String sql = "SELECT code, name, price FROM product";

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<Product> list = new ArrayList<Product>();
		while (rs.next()) {
			String code = rs.getString("code");
			String name = rs.getString("name");
			float price = rs.getFloat("price");
			Product product = new Product();
			product.setCode(code);
			product.setName(name);
			product.setPrice(price);
			list.add(product);
		}
		return list;
	}

	public static Product findProduct(Connection conn, String code)
			throws SQLException {

		String sql = "SELECT code, name, price FROM product WHERE code = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, code);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String name = rs.getString("name");
			float price = rs.getFloat("price");
			Product product = new Product(code, name, price);
			return product;
		}
		return null;
	}

	public static void updateProduct(Connection conn, Product product)
			throws SQLException {

		String sql = "UPDATE product set name = ?, price = ? WHERE code = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, product.getName());
		pstm.setFloat(2, product.getPrice());
		pstm.setString(3, product.getCode());
		pstm.executeUpdate();
	}

	public static void insertProduct(Connection conn, Product product)
			throws SQLException {

		String sql = "INSERT INTO product(code, name, price) VALUES (?,?,?)";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, product.getCode());
		pstm.setString(2, product.getName());
		pstm.setFloat(3, product.getPrice());
		pstm.executeUpdate();
	}

	public static void deleteProduct(Connection conn, String code)
			throws SQLException {

		String sql = "DELETE product WHERE code = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, code);
		pstm.executeUpdate();
	}
}