package vttp2022.sdfassessment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TemplateGenerator {

    private String dataSource;
    private String templateFile;
    
    public TemplateGenerator (String dataSource, String templateFile) {
        this.dataSource = dataSource;
        this.templateFile = templateFile;
    }

    public void apply () throws IOException {
        
        FileReader ds = new FileReader(dataSource);
        BufferedReader brds = new BufferedReader(ds);

        String line = brds.readLine();
        String[] header = line.split(","); // header[0] = first_name ; header[1] = last_name ; header[2] = address ; header[3] = years

        // data lines
        while (line != null) {
            line = brds.readLine();
            if (line == null)
                break;
            line = line.replaceAll("\\\\n", "\n");
            String[] dataArray = line.split(",");
            FileReader tf = new FileReader(templateFile);
            BufferedReader brtf = new BufferedReader(tf);

            while (true) {
                String templateFileLine = brtf.readLine();
                if (templateFileLine == null)
                    break;
                String[] tempArray = templateFileLine.split(" ");
                templateFileLine = "";

                String variable = "";
                String prefix = "";
                String suffix = "";
                for (int i = 0; i < tempArray.length; i++) {
                    if (tempArray[i].contains("__")) {
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
