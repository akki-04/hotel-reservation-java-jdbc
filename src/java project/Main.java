import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        try(Connection con = DbConnection.getConnection()){

            while (true) {

                System.out.println("Hotel Management System");

                System.out.println("1. Reservation a Room");
                System.out.println("2. View Reservations");
                System.out.println("3. Get room no.");
                System.out.println("4. Update reservation");
                System.out.println("5. Delete reservation");
                System.out.println("6. Exit");
                System.out.printf("Choose an option : ");
                int ip = sc.nextInt();

                switch (ip) {
                    case 1:
                        Reservation.reserve(con,sc);
                        break;
                    
                    case 2:
                        Reservation.viewreservation(con);
                        break;
                    case 3:
                        Reservation.GetroomNumber(con,sc);
                        break;
                    case 4:
                        Reservation.updatereservation(con,sc);
                        break;
                    case 5:
                        Reservation.dltreservation(con,sc);
                        break;
                    case 6:
                        Reservation.exit();
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid input given. Try Again....... ");
                    }
                        
                }
            }catch(SQLException d){
                System.out.println("Sql exception "+d.getMessage());
                throw new RuntimeException(d);
            }finally{
                sc.close();
            }
    }
}
