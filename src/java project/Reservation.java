import java.sql.*;
import java.util.Scanner;


public class Reservation{
    public static void reserve(Connection con,Scanner sc){
        System.out.printf("Enter the guest name:- ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.printf("Enter room number :- ");
        int room = sc.nextInt();
        System.out.printf("Enter contact no. :- ");
        sc.nextLine();
        String s = sc.nextLine();

        String query ="INSERT INTO reservation(name,room_no,contact_no) values(?,?,?)";
        try{
            PreparedStatement pre = con.prepareStatement(query);

            pre.setString(1, name);
            pre.setInt(2, room);
            pre.setString(3,s);
            int rows = pre.executeUpdate(); 
            if(rows>0)System.out.println("reservation successful.....");
            else{
                System.out.println("Reservation failed");
            }

        }catch (SQLSyntaxErrorException e) {
        System.out.println("SQL SYNTAX ERROR: " + e.getMessage());
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("CONSTRAINT ERROR: " + e.getMessage());
        } catch (SQLDataException e) {
            System.out.println("DATA TYPE ERROR: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        } 

    }

    public static void viewreservation(Connection con){
        String query = "select res_id,name,room_no,contact_no,reservation_date from reservation";
        try(PreparedStatement st = con.prepareStatement(query)){
            
            ResultSet result = st.executeQuery();
            System.out.println("Current Reservation ");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("|  ID  |      NAME       | ROOM NO |   CONTACT NO   | RESERVATION DATE              |");
            System.out.println("-------------------------------------------------------------------------------------");

            while(result.next()){
                int id = result.getInt("res_id");
                String name = result.getString("name");
                int room = result.getInt("room_no");
                String contact = result.getString("contact_no");
                Timestamp da = result.getTimestamp("reservation_date");

                System.out.printf("| %-4d | %-15s | %-7d | %-14s | %-29s |\n",
                        id, name, room, contact, da);
            }

            System.out.println("-------------------------------------------------------------------------------------");

        }catch(Exception e){
            System.out.println("exception h "+ e.getMessage());
        }
    }


    public static void GetroomNumber(Connection con,Scanner sc){
        try{
            System.out.printf("Enter reservation id : ");
            int id = sc.nextInt();
            System.out.printf("Enter Guest Name : ");
            sc.nextLine();
            String name = sc.nextLine();

            String query = "select room_no from reservation where res_id = " 
                            + id +" AND name = '" 
                            + name + "'";
            try(Statement spl = con.createStatement()){
                ResultSet result = spl.executeQuery(query);
                if(result.next()){
                    int s = result.getInt("room_no");
                    System.out.println("Room no for Reservation ID : " 
                    + id + " and Guest name is "
                    + name + " room no is "
                    +s);
                }else{
                    System.out.println("Guest not exists");
                }
            }
        }catch(Exception e){
                System.out.println("error in code");
        }
    }

    public static void updatereservation(Connection con, Scanner sc){
        System.out.printf("Enter reserve ID to update : ");
        int id = sc.nextInt();

        if(!ReservationExists(con,id)){
            System.out.println("Reservation Not found ");
            return;
        }

        sc.nextLine();
        System.out.printf("Enter new Guest name : ");
        String name = sc.nextLine();
        System.out.printf("Enter contact no. : ");
        String cn = sc.nextLine();
        System.out.printf("Enter new room no. : ");
        int no = sc.nextInt();

        String query = "update reservation set name = '"+name+"' , room_no = " +no
                    +", contact_no = '" + cn + "' where res_id =" + id; 

        try(Statement st = con.createStatement()){
            int row = st.executeUpdate(query);
            if(row>0){
                System.out.println("Reservation Upadate successfully");
            }else{
                System.out.println("Reservation updation failed..... ");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void dltreservation(Connection con, Scanner sc){
        try{
            System.out.printf("Enter Reservation ID to delete : ");
            int id = sc.nextInt();
            if(!ReservationExists(con,id)){
                System.out.println("Reservation Not found....");
                return;
            }else{
                String query = "Delete from reservation where res_id = "+id;

                try(Statement st = con.createStatement()){
                    int x = st.executeUpdate(query);
                    if(x>0)System.out.println("Delete reservation succcessfully....");
                    else{
                        System.out.println("Reservation deleted failed try Again....");
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void exit() throws InterruptedException{
        System.out.printf("Exiting System");
        int i = 5;
        while (i!=0){
            System.out.printf(".");
            Thread.sleep(450);
            i--;
        }
        System.out.println();
        System.out.println("THANK YOU FOR USING HOTEL RESERVATION SYSTEM....!!!");
    }
    public static boolean ReservationExists(Connection con,int id){
        try{
            String query = "Select res_id from reservation  where res_id = "+ id;
            try(Statement st = con.createStatement();
                ResultSet res = st.executeQuery(query)){
                    return res.next();
            }
    }catch(SQLException e){
                System.out.println("Error sql " + e.getMessage());
                return false;
        }
    }
}