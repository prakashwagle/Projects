package Utility;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class ActionRules {

    public static String line = "";
    public static String rightDecisionValue = "";
    public static String leftDecisionValue = "";
    public static int decisionColumn = 0;


    public static ArrayList<ArrayList<String>> listA = null; // Contains objects of Desired Decision
    public static ArrayList<ArrayList<String>> listB = null;
    public static ArrayList<ArrayList<String>> decisionTable = null;

    public static ArrayList<String> attributesNames =new ArrayList<String>();
    public static ArrayList<ArrayList<TreeSet<String>>>  disjunctiveForm =null;

    public static String[] stableAttributeNames = null;
    public static ArrayList<String> stableAttributesList = null;

    public static HashSet<String> actionRules = null;
    public static ArrayList<ArrayList<TreeSet<String>>> createdreducts = null;

        public ActionRules()
        {
         line = "";
         rightDecisionValue = "";
         leftDecisionValue = "";
         decisionColumn = 0;


         listA = new ArrayList<ArrayList<String>>();
         listB = new ArrayList<ArrayList<String>>();
         decisionTable = new ArrayList<ArrayList<String>>();

        attributesNames =new ArrayList<String>();
        disjunctiveForm =new ArrayList<ArrayList<TreeSet<String>>>(); // Change the name

         stableAttributeNames = new String[3];
         stableAttributesList =new ArrayList<String>();

         actionRules = new HashSet<String>();
         createdreducts =new ArrayList<ArrayList<TreeSet<String>>>();

         }
        public HashSet<String> run(String filepath,String leftValue,String rightValue)
        {
        //inputFileName = "/Users/prakashwagle/Documents/Projects/KDD_Project/FragileStatesIndex.csv";
        System.out.println("Left : "+leftValue+" Right: "+rightValue);
        ActionRules ar = new ActionRules();
        rightDecisionValue = rightValue;
        leftDecisionValue = leftValue;

        decisionColumn=15;
        stableAttributeNames[0] = "13";
        stableAttributeNames[1] = "14";
        stableAttributeNames[2] = "0";

        CsvReader cv = new CsvReader();
        cv.readCSV(filepath,leftDecisionValue,rightDecisionValue);
        System.out.println("File Read");
        DiscernibilityMatrix dm = new DiscernibilityMatrix();
        dm.makeDiscernibilityMatrix();

        Reducts reducts = new Reducts();
        reducts.findReducts();
        System.out.println("Reducts Created");


        constructActionRules();

        try {
            //String userHomeFolder = System.getProperty("user.home");
            FileWriter fwriter = new FileWriter(new File("KDDActionRules.txt"));
            BufferedWriter buffwritter = new BufferedWriter(fwriter);
            for (String str : actionRules)
            {
                buffwritter.write(str.replaceAll("<bw>"," "));
                buffwritter.write("\n");
            }
            buffwritter.close();
            System.out.println("File Created");
        }
        catch (IOException error)
        {
            error.printStackTrace();
        }
        return actionRules;

    }


// Following function will create Actions Rules from Action Reducts

    public void constructActionRules()
        {
            boolean flag;
            StringBuilder sb;
              for (ArrayList<TreeSet<String>> reduct : createdreducts) {
                 for (TreeSet<String> temp : reduct) {
                      HashMap<String, String> temphm = new HashMap<String, String>();
                    for (String at : temp) {
                        String[] attributes = at.split(":");
                        temphm.put(attributes[0], attributes[1]);
                                            }
                     for (ArrayList<String> al : listB) {
                         flag=false;
                         sb=new StringBuilder();
                        for (String str : al) {
                             String[] attrs = str.split(":");
                            if(attrs.length>1)
                            {
                                if (temphm.containsKey(attrs[0])) {
                                    if (!stableAttributesList.contains(attrs[0]))
                                    {
                                         flag=true;
                                         sb.append("("+str+")"+" --> ("+attrs[0]+" : "+temphm.get(attrs[0])+")");
                                     //   System.out.print("("+str+")"+" -> ("+attrs[0]+" : "+temphm.get(attrs[0])+")");
                                    }
                                    else
                                    {
                                         flag=true;
                                         sb.append("("+attrs[0]+":"+temphm.get(attrs[0])+")");
                                      //  System.out.print("("+attrs[0]+":"+temphm.get(attrs[0])+")");
                                    }
                                }
                            }
                        }
                        if(flag)
                        {
                            sb.append(" ==> "+al.get(decisionColumn)+" --> "+rightDecisionValue);
                        }
                        actionRules.add(sb.toString());

                    }
                }
            }


        }

}
