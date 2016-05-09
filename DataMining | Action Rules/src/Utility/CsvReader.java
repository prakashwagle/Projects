package Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by prakashwagle on 11/25/15.
 */
// This function will Read input CSV and divide objects into two list based on their decision attribute value.
    // list A will contain objects of desired decision while rest of the objects will be added to listB
public class CsvReader {

    public void readCSV(String Path,String leftDecisionValue,String rightDecisionValue)
    {
        String line = "";
        BufferedReader br =null;
        try
        {
            br = new BufferedReader(new FileReader(Path));
            if ((line = br.readLine()) != null) {
                String[] names = line.split(",");
                for (String attr : names) {
                    ActionRules.attributesNames.add(attr);
                }
            }
//            System.out.println("Attributes Names: "+ActionRules.attributesNames.toString());

            while((line = br.readLine())!=null)
            {
                String [] readline = line.split(",");
                String[] data = line.split(",");
                ArrayList<String> lines = new ArrayList<String>();
                for (String attr : data) {
                    lines.add(attr);
                }
                ActionRules.decisionTable.add(lines);
//                System.out.println(readline[1]);
            }
//            System.out.println("Decision Table: "+ActionRules.decisionTable.toString());

            for (ArrayList<String> row :  ActionRules.decisionTable) {
                int counter = 0;
                for (String attribute : ActionRules.attributesNames) {
                    if (counter != ActionRules.decisionColumn) {
                        row.set(counter,
                                attribute + ":" + row.get(counter));
                    }
                    counter++;
                }
                if (row.get(ActionRules.decisionColumn).equals(rightDecisionValue))
                {
                    ActionRules.listA.add(row);
                }
                else if (row.get(ActionRules.decisionColumn).equals(leftDecisionValue))
                {
                        ActionRules.listB.add(row);

                }
                else if(leftDecisionValue.equalsIgnoreCase("All"))
                {
                    ActionRules.listB.add(row);
                }
            }
//            System.out.println("ListA Names: "+ActionRules.listA.toString());
//            System.out.println("ListB Names: "+ActionRules.listB.toString());
            for (String attributes : ActionRules.stableAttributeNames)
            {
                ActionRules.stableAttributesList.add(ActionRules.attributesNames.get(Integer.parseInt(attributes)));
            }

//            System.out.println("StableAttributes Names: "+ActionRules.stableAttributeNames);
//            System.out.println("StableAttributes Names: "+ActionRules.stableAttributesList.toString());
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }

    }


}