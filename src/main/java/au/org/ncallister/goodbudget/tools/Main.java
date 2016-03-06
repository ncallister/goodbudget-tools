/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.org.ncallister.goodbudget.tools;

import au.org.ncallister.goodbudget.tools.coms.GoodBudgetSession;
import java.util.ArrayList;

/**
 *
 * @author ncallister
 */
public class Main
{
  public static void main(String[] args) throws Exception
  {
    GoodBudgetSession session = new GoodBudgetSession();
    session.login(args[0], args[1]);
    
    System.out.println(session.get("/api/transactions", new ArrayList<>()));
  }
  
  // TODO: Local H2 Database - http://www.h2database.com/html/quickstart.html
}
