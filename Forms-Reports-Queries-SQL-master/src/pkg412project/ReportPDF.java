/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg412project;
import com.itextpdf.text.Chunk;
import java.sql.*;
import java.sql.ResultSetMetaData;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;



import java.io.FileOutputStream;
import java.io.FileNotFoundException;


/**
 *
 * @author sishirmohan
 */
public class ReportPDF 
{
    public static void main(String args[]) throws ClassNotFoundException, SQLException, DocumentException, FileNotFoundException
    {
        try
        {
            //Class.forName("oracle.jdbc.OracleDriver");
            int rowno = 0;
            String url = "jdbc:oracle:thin:@acaddb2.asu.edu:1521:orcl";
            String username = "sbmohan1";
            String password = "sbmohan1";
            
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM SBMOHAN1.ORDERTABLE");
            ResultSetMetaData rsmd = rs.getMetaData();
            int colno = rsmd.getColumnCount();
            while(rs.next())
            {
                rowno++;
            }
            rs.first();
            Document d = new Document();
            PdfWriter.getInstance(d, new FileOutputStream("report.pdf"));
            Font font1 = new Font(Font.FontFamily.HELVETICA  , 25, Font.BOLD);
            Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 27);
            Font font3 = new Font(Font.FontFamily.COURIER    , 12, Font.BOLD);
            float[] columnWidths = {4f, 4f, 16f, 16f, 4f, 4f, 4f, 4f};
            PdfPTable ptable = new PdfPTable(colno);
            ptable.setWidths(columnWidths);
            d.open();
            Paragraph title = new Paragraph("Order Report", font1);
            title.setAlignment(Element.ALIGN_CENTER);
            d.add(title);
            Paragraph date = new Paragraph("December 10th, 2015", font2);
            date.setAlignment(Element.ALIGN_CENTER);
            d.add(date);
            //d.add(new Paragraph("Order Report"));
            
            //d.add(new Paragraph("Date: December 10th, 2015"));
            d.add(Chunk.NEWLINE);
            //d.add(Chunk.NEWLINE);
            ptable.addCell(new Paragraph("OrderID",font3));
            ptable.addCell(new Paragraph("NumItems",font3));
            ptable.addCell(new Paragraph("ItemsOrdered",font3));
            ptable.addCell(new Paragraph("OrderStatus",font3));
            ptable.addCell(new Paragraph("TotalPrice",font3));
            ptable.addCell(new Paragraph("PaymentID",font3));
            ptable.addCell(new Paragraph("TableID",font3));
            ptable.addCell(new Paragraph("ChefID",font3));
            
            //insertCell(ptable, "OrderID", Element.ALIGN_LEFT, 1, bfBold12);
            for(int i = 0; i<rowno; i++)
            {
                    ptable.addCell(""+ rs.getString(1));
                    ptable.addCell(""+ rs.getString(2));
                    ptable.addCell(""+ rs.getString(3));
                    ptable.addCell(""+ rs.getString(4));
                    ptable.addCell(""+ rs.getString(5));
                    ptable.addCell(""+ rs.getString(6));
                    ptable.addCell(""+ rs.getString(7));
                    ptable.addCell(""+ rs.getString(8));  
                    rs.next();
            }

            d.add(ptable);
            d.close();
            
        }
        catch(SQLException ex)
        {
            System.out.println(ex.toString());
        }
    }
    
}
