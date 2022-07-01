package vttp2022.sdfassessment;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException
    {
        // takes in args[0]=CSV file and args[1]=template file
        String dataSource = args[0];
        String templateFile = args[1];
        TemplateGenerator template = new TemplateGenerator(dataSource, templateFile);
        template.apply();
 
    }
}
