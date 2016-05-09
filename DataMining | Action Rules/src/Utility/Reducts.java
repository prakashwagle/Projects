package Utility;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by prakashwagle on 11/26/15.
 */
public class Reducts
{

// This fuction will create Reducts from DiscernibilityMatrix (Object: disjunctiveForm)

    public void findReducts()
    {
        for(ArrayList<TreeSet<String>> t : ActionRules.disjunctiveForm)
        {
            if(t.size() < ActionRules.listB.size())
            {
                ActionRules.disjunctiveForm.remove(t);
            }
        }

        for(ArrayList<TreeSet<String>> t : ActionRules.disjunctiveForm)
        {
            t.get(0);
            ArrayList<TreeSet<String>> newDisjunctiveForm = new ArrayList<TreeSet<String>>();

            for(String line : t.get(0))
            {
                TreeSet<String> ts1 = new TreeSet<String>();
                ts1.add(line);
                newDisjunctiveForm.add(ts1);
            }

            ArrayList<TreeSet<String>> temp = newDisjunctiveForm;

            for(int k=1 ; k < t.size() ; k++)
            {
                TreeSet<String> leaf = t.get(k);
                ArrayList<TreeSet<String>> newDF =new ArrayList<TreeSet<String>>();
                for(String st : leaf)
                {
                    TreeSet<String> ts2 = new TreeSet<String>();
                    ts2.add(st);
                    newDF.add(ts2);
//                    System.out.println("Action Added"+k);
                }

                temp = conjunctiveTodisjunctive(temp, newDF);

                temp = reduce(temp);
//                System.out.println("Action Added"+k);
            }
            ActionRules.createdreducts.add(temp);

        }


    }

// Transforms conjunction normal form to disjunction normal form

    public ArrayList<TreeSet<String>> conjunctiveTodisjunctive(ArrayList<TreeSet<String>> A,ArrayList<TreeSet<String>> B)
          {
              ArrayList<TreeSet<String>> temp2 = new ArrayList<TreeSet<String>>();

              for(int i=0;i< A.size() ; i++)
              {
                  for(int k=0;k< B.size();k++)
                  {
                      temp2.add((TreeSet<String>) union(A.get(i),B.get(k)));
                  }
              }

              return temp2;
          }

    public ArrayList<TreeSet<String>> reduce (ArrayList<TreeSet<String>> disjunctive)
    {
       TreeSet<String> temp ;
        ArrayList<TreeSet<String>> node = new ArrayList<TreeSet<String>>();
        ArrayList<TreeSet<String>> newnode = new ArrayList<TreeSet<String>>();
        Boolean flag= false;
        for (TreeSet<String> t:disjunctive)
        {
            if(!node.contains(t))
            {
                node.add((TreeSet<String>) t );
            }
        }

        while(node.size() > 0)
        {
            flag=false;
            temp = node.remove(0);
            ArrayList<TreeSet<String>> tempList = new ArrayList<TreeSet<String>>();
            tempList.addAll(node);
            tempList.addAll(newnode);
            for(TreeSet<String> t:tempList)
            {
                if(!(temp.containsAll(t)))
                {
                    continue;
                }
                else
                {
                    flag=true;
                    break;
                }
            }
            if(flag==false)
            {
                newnode.add(temp);
            }
        }

        return newnode;
    }

    public  <T> Set<T> union(Set<T> A, Set<T> B) {
        Set<T> temp4 = new TreeSet<T>(A);
        temp4.addAll(B);
        return temp4;
    }

}
