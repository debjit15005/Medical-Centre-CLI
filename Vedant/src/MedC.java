package medc;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.*;


/**
 *
 * @author vedan
 */

class register {
   
    private String Name;
    private String BITS_ID;
    private String BITS_email;
    private String mobile_no;
    
    
    public Boolean valid_email(String BITS_email)
    {
        ///
     Pattern p = Pattern.compile("^[p|f](2018|2019|2020|2021|2022)[0-9]{4}(@pilani.bits-pilani.ac.in)");
     Matcher m = p.matcher(BITS_email);
    return (m.find() && m.group().equals(BITS_email));
    }
    public void write_to_file(String Name,String BITS_ID,String BITS_email,String mobile_no)
    {
        if(valid_email(BITS_email))
        {
            try
            {
            FileWriter myWriter = new FileWriter("D:\\filename.txt");
            myWriter.write(Name+","+ BITS_ID+"," + BITS_email+"," + mobile_no);
            myWriter.close();
            System.out.println("File created");
            }
            catch (IOException e)
            {
              System.out.println("An error occurred.");
            }
            
        }
        else
        {
            System.out.println("Error in the Email");
        }
    }
    public register(String Name,String BITS_ID,String BITS_email,String mobile_no)
    {
        this.Name=Name;
        this.BITS_ID=BITS_ID;
        this.BITS_email=BITS_email;
        this.mobile_no=mobile_no;
        write_to_file(Name,BITS_ID,BITS_email,mobile_no);
        
    }
    
    
    
}
public class MedC {

    public static void main(String[] args) {
        // TODO code application logic here    
        
        
        
    }
    
}
