package qunlysanpham.service;

import qunlysanpham.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {

    List<Customer> customerList = new ArrayList<>();

    public CustomerDAO() {
    }

    protected static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo2006?useSSL=false", "root", "1234");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }


    @Override
    public int findIndexById(int id) {
        int index = -1;

        for (int i = 0; i < this.customerList.size(); ++i) {
            if (customerList.get(i).getId() == id) {
                index = i;
            }
        }

        return index;
    }

    @Override
    public void add(Customer customer) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into customer(id,name ,age) value (?,?,?)");) {
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setInt(3, customer.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    @Override
    public Customer findById(int id) {
        List<Customer> customers = findAll();
        Customer customer = new Customer();
        for (Customer cus: customers
             ) {
            if(id == cus.getId()) {
             customer = cus;
            }
        }
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select *from customer");) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = Integer.parseInt(rs.getString("age"));
                customers.add(new Customer(id, name, age));
            }
        } catch (SQLException e) {

        }
        return customers;
    }

    @Override
    public List<Customer> findByName(String tim) {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from customer where name like ?");) {
            preparedStatement.setString(1,"%"+ tim +"%");
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = Integer.parseInt(rs.getString("age"));
                customers.add(new Customer(id, name, age));
            }
        } catch (SQLException e) {
        }
        return customers;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean em = false;
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from customer where id=?;")){
            preparedStatement.setInt(1,id);
            em = preparedStatement.executeUpdate()>0;
        }
        return em;
    }

    @Override
    public boolean update(Customer customer)throws SQLException {
        boolean anh = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update customer set name = ? , age = ?  where id = ?");) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setInt(2, customer.getAge());
            preparedStatement.setInt(3, customer.getId());
            anh = preparedStatement.executeUpdate() > 0;
        }
        System.out.println(customer.toString());
        return anh;
    }
}

