package Utility;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by prakashwagle on 11/26/15.
 */
public class DiscernibilityMatrix {

// This function will create DiscernibilityMatrix from List A and List B which will be in conjunction normal form

 public void makeDiscernibilityMatrix()
 {
     for (ArrayList<String> a : ActionRules.listA)
     {
         TreeSet<String> temp1 = new TreeSet<String>();
         ArrayList<TreeSet<String>> result = new ArrayList<TreeSet<String>>();
         a = removeNull(a, ActionRules.decisionColumn);
         for (String str : a)
         {
             temp1.add(str);
         }
         for (ArrayList<String> b : ActionRules.listB)
         {
             TreeSet<String> temp2 = new TreeSet<String>();
             b = removeNull(b, ActionRules.decisionColumn);
             for (String str2 : b)
             {
                 temp2.add(str2);
             }
             TreeSet<String> temp3 ;
             temp3 = (TreeSet<String>) difference(temp1, temp2);
             if (temp3.size() > 0)
             {
                 result.add(temp3);
             }
         }
         ActionRules.disjunctiveForm.add(result);
     }

 }

    // This function will remove null values if they exsist in a input file
    public static ArrayList<String> removeNull(ArrayList<String> rows,int index)
    {
        String value = "";
        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < rows.size(); i++)
        {
            if (i != index)
            {
                value = rows.get(i);
                String[] attribute = value.split(":");
                if (attribute.length > 1)
                {
                    temp.add(value);
                }
            }
        }
        return temp;
    }

    public  <T> Set<T> union(Set<T> A, Set<T> B) {
        Set<T> temp4 = new TreeSet<T>(A);
        temp4.addAll(B);
        return temp4;
    }

    public  <T> Set<T> difference(Set<T> A, Set<T> B) {
        Set<T> temp5 = new TreeSet<T>(A);
        temp5.removeAll(B);
        return temp5;
    }

}
