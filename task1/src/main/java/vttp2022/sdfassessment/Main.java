package vttp2022.sdfassessment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException
    {
        // takes in args[0]=CSV file and args[1]=template file
        String dataSource = args[0];
        String templateFile = args[1];

        FileReader ds = new FileReader(dataSource);
        BufferedReader brds = new BufferedReader(ds);

        String line = brds.readLine();
        String[] header = line.split(","); // header[0] = first_name ; header[1] = last_name ; header[2] = address ; header[3] = years

        // data lines
        while (line != null) {
            line = brds.readLine();
            if (line == null)
                break;
            String[] dataArray = line.split(",");
            //System.out.println(dataArray[2]);
            //System.out.println("301 Cobblestone Way\nBedrock");
            // System.out.println(line);
            FileReader tf = new FileReader(templateFile);
            BufferedReader brtf = new BufferedReader(tf);

            while (true) {
                String templateFileLine = brtf.readLine();
                if (templateFileLine == null)
                    break;
                //System.out.println(templateFileLine);
                String[] tempArray = templateFileLine.split(" ");
                templateFileLine = "";

                String variable = "";
                String prefix = "";
                String suffix = "";
                for (int i = 0; i < tempArray.length; i++) {
                    if (tempArray[i].contains("__")) {
                        // System.out.println(tempArray[i]);
                        // then it is a variable
                        if (tempArray[i].startsWith("_") && tempArray[i].endsWith("_")) 
                        {
                            // there is no prefix and suffix
                            variable = tempArray[i].substring(2, tempArray[i].length()-2);
                        } 
                        else if (tempArray[i].startsWith("_"))
                        {
                            // there is no prefix but there is a suffix
                            variable = tempArray[i].substring(2, tempArray[i].length()-3);
                            suffix = tempArray[i].substring(tempArray[i].length()-1);
                        }
                        else if (tempArray[i].endsWith("_"))
                        {
                            // there is a prefix but no suffix
                            variable = tempArray[i].substring(3, tempArray[i].length()-2);
                            prefix = tempArray[i].substring(0, 1);
                        } 
                        else
                        {
                            // there is a prefix and there is a suffix
                            variable = tempArray[i].substring(3, tempArray[i].length()-3);
                            prefix = tempArray[i].substring(0, 1);
                            suffix = tempArray[i].substring(tempArray[i].length()-1);
                        }
                        
                        // System.out.println(variable);

                        for (int j = 0; j < header.length; j++) {
                            //System.out.print(header[j]);
                            if (variable.equalsIgnoreCase(header[j])) {
                                int index = j;
                                tempArray[i] = prefix + dataArray[index] + suffix;
                                // System.out.println("prefix= " + prefix);
                                // System.out.println("suffix= " + suffix);
                                // System.out.println(tempArray[i]);
                            }
                        }
                    }
                }
                for (int k = 0; k < tempArray.length; k++) {
                    templateFileLine += tempArray[k] + " ";
                }
            
                System.out.println(templateFileLine);
            }

            System.out.println();
            System.out.println("---------- new filled template ----------");
            System.out.println();
            
        }
    }
}
