package com.project.appoint;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookAppointmentServlet")
public class BookAppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Static block to load the JDBC driver during class initialization
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("Error loading JDBC driver: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/pet_hotel";
        String user = "root";
        String password = "";

        System.out.println("Connecting to database...");

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected successfully.");

            String sql = "INSERT INTO appointments (customer_name, phone_number,dog_breed, dog_age, no_of_dogs, service, appointment_date, appointment_time, nights_required, pickup_services) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Retrieve data from the request (you may need to adjust these based on your form)
            	String customerName = request.getParameter("customer_name");
            	String phoneNumber = request.getParameter("phone_number");
                String dogBreed = request.getParameter("dog_breed");
                int dogAge = Integer.parseInt(request.getParameter("dog_age"));
                int noOfDogs = Integer.parseInt(request.getParameter("no_of_dogs"));
                String service = request.getParameter("service");
                java.sql.Date appointmentDate = java.sql.Date.valueOf(request.getParameter("appointment_date"));
                String timeStr = request.getParameter("appointment_time");
                java.sql.Time appointmentTime = new java.sql.Time(new SimpleDateFormat("HH:mm").parse(timeStr).getTime());
                int nightsRequired = Integer.parseInt(request.getParameter("nights_required"));
                boolean pickupServices = "Yes".equals(request.getParameter("pickup_services"));

                // Set parameters for the SQL statement
                preparedStatement.setString(1, customerName);
                preparedStatement.setString(2, phoneNumber);
                preparedStatement.setString(3, dogBreed);
                preparedStatement.setInt(4, dogAge);
                preparedStatement.setInt(5, noOfDogs);
                preparedStatement.setString(6, service);
                preparedStatement.setDate(7, appointmentDate);
                preparedStatement.setTime(8, appointmentTime);
                preparedStatement.setInt(9, nightsRequired);
                preparedStatement.setBoolean(10, pickupServices);

                // Execute the SQL statement
                int rowsInserted = preparedStatement.executeUpdate();
                System.out.println(rowsInserted + " row(s) inserted successfully.");

                // You can add further logic or redirect the user to a confirmation page
                response.sendRedirect("confirmation.jsp");    
            } catch (SQLException e) {
                System.out.println("Error occurred while inserting data: " + e.getMessage());
                response.sendRedirect("error.jsp"); // Redirect to an error page
            } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (SQLException e) {
            System.out.println("Error occurred while connecting to the database: " + e.getMessage());
            response.sendRedirect("error.jsp"); // Redirect to an error page
        }
    }
}

