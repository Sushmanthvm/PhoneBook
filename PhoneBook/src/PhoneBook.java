import java.sql.*;

public class PhoneBook{

    //view Record
    public static ResultSet viewRecord(Connection conn) throws SQLException {
        PreparedStatement Selectstmt = conn.prepareStatement("select * from contacts");
        ResultSet rs = Selectstmt.executeQuery();

        return rs;


    }

    //Delete Record
    public static String deleteRecord(Connection conn,String name) throws SQLException {
        PreparedStatement Deletestmt = conn.prepareStatement("delete from contacts where name = ?");
        Deletestmt.setString(1,name);
        int deleteCount = Deletestmt.executeUpdate();
        if(deleteCount == 0){return "Record not found";}
        else{return " Record deleted successfully";}

    }
    //Insert Record
    public static String insertRecord(Connection conn,String name,TenDigitNumber validNumber) throws SQLException {
        PreparedStatement Insertstmt = conn.prepareStatement("insert into contacts (name, contact_no) values (?, ?)");
        Insertstmt.setString(1, name);
        Insertstmt.setString(2,validNumber.getNumber());
        int insertCount = Insertstmt.executeUpdate();
        if (insertCount==0) {
            return "Record Not Inserted";
        } else {
           return insertCount +" Record Inserted";
        }


    }

    //Update Record
    public static String updateRecord(Connection conn,String name, TenDigitNumber validNumber) throws SQLException {
        PreparedStatement Updatestmt = conn.prepareStatement( "update contacts set contact_no=? where name=?");
        Updatestmt.setString(1, validNumber.getNumber());
        Updatestmt.setString(2, name);
        int deleteCount = Updatestmt.executeUpdate();
        if (deleteCount==0) {return "Record Not Updated";}
        else{return deleteCount +" Record Updated";}

    }

    //Find Record
    public static ResultSet findRecord(Connection conn,String str) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("select name,contact_no from contacts where name=?");
        pstmt.setString(1, str);
        ResultSet rs = pstmt.executeQuery();
        return rs;
    }
}
