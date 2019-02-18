package njfbrowser.utils;
 

import java.sql.*;
import java.io.*;
import java.net.*;


import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.json.JSONArray;
import org.json.JSONObject;

import njfbrowser.utils.StringUtils; 
import njfbrowser.utils.BasicUtils;
/**
 * Created by IntelliJ IDEA.
 * User: boss
 * Date: 16-03-2014
 * Time: 17:26
 * To change this template use File | Settings | File Templates.
 */
public class UtilSQLAdapter {


    public static final String MYDATABASE_NAME = "evenflow.db";
    public static final String MYDATABASE_TABLE = "quser";
    public static final int MYDATABASE_VERSION = 3;


    String dburl = "jdbc:sqlite:";
    StringUtils stringUtils;
    BasicUtils basicUtils;
    Connection conn = null;


    public UtilSQLAdapter() {

        stringUtils = new StringUtils();
        basicUtils = new BasicUtils();
        String furl = "jdbc:sqlite:" + basicUtils.getUfile("evenflow.db");
        dburl = furl;
        try {
            conn = DriverManager.getConnection(dburl);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                doTblTest();

            }
        } catch (SQLException e) {
            System.out.println("UtilSQLAdapter: " + e.getMessage());
            e.printStackTrace();
        }


    }



    public void doTblTest() {
        try { 
		conn = DriverManager.getConnection(dburl); 
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();

  


              String sql = "SELECT _id FROM quser limit 0,1";
 
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql);
            
            // loop through the result set
            while (rs.next()) {
                System.out.println("rs is next");
            }
     

         }
        } catch (SQLException e) {
            System.out.println("doTblTest: " + e.getMessage());
		copyDataBase(); 
        }
    }


    public String doTest(String tString) {
        return tString;
    }




    public String getTblFQ(String strTQstr) {
String adbleS = "";
try {

String qstr = "SELECT * FROM " + strTQstr  + " limit 0,1";
conn = DriverManager.getConnection(dburl); 
Statement stmt  = conn.createStatement();
ResultSet rstq = stmt.executeQuery(qstr);

ResultSetMetaData arsmd = rstq.getMetaData();
int columnCount = arsmd.getColumnCount();
for (int ia = 0; ia < columnCount; ia++)
{
adbleS += arsmd.getColumnName(ia+1) + "\n";
}
adbleS += "<ttok>\n";

} catch(Exception e) {
adbleS = "Error: " + e;
return adbleS;
}
return adbleS;
}





 
    public String getTblsQ() {
String dbleS = "";
try {
conn = DriverManager.getConnection(dburl); 
DatabaseMetaData md = conn.getMetaData();
ResultSet rstq = md.getTables(null, null, "%", null);
// Statement stmt  = conn.createStatement();
// ResultSet rstq = stmt.executeQuery("SELECT name FROM qbits WHERE type='table'");
while(rstq.next()) {
dbleS += "<tbl>" + rstq.getString(3) + "\n";
dbleS += getTblFQ(rstq.getString(3));
}

} catch(Exception e) {
dbleS = "Error: " + e;
return dbleS;
}
return dbleS;
}


    public String setTabsQ(String strQstr) {

	JSONArray resultSet = new JSONArray(); 
 	String fnlStrRes = "";

        
        try {
conn = DriverManager.getConnection(dburl); 
Statement stmt  = conn.createStatement();
String mnmstring = strQstr.toLowerCase();
if(mnmstring.contains("select ")) {
ResultSet rs = stmt.executeQuery(strQstr);

ResultSetMetaData rsmd = rs.getMetaData();
System.out.println("rsrsrsrsrsrsrs: " +  rs.toString());
int columnCount = rsmd.getColumnCount();
 System.out.println("dbSelectQ.getColumnCount: " + columnCount);


for (int ia = 0; ia < columnCount; ia++)
{
fnlStrRes += rsmd.getColumnName(ia+1);
if(ia+1 < columnCount) {
fnlStrRes += "\t";
}
}
fnlStrRes += "\n";


while (rs.next()) {
 System.out.println("setTabsQ.getFString: " +  rs.getString(1));
 


JSONObject rowObject = new JSONObject();
for (int i = 0; i < columnCount; i++)
{
System.out.println("dbSelectQ.getFString: " + rsmd.getColumnName(i+1) + " : " +  rs.getString(i+1));

if(rsmd.getColumnName(i+1) != null ) 
{ 
if(rs.getString(i+1) != null)
{
System.out.println("dbSelectQ.getString: " + rsmd.getColumnName(i+1) + " : " +  rs.getString(i+1));
 fnlStrRes += rs.getString(i+1);
} else {
 fnlStrRes += " ";
}
}
if(i+1 < columnCount) {
 fnlStrRes += "\t";
}

}


 fnlStrRes += "\n"; 

 /* */
}
 } else {

int tint = stmt.executeUpdate(strQstr);
fnlStrRes = "[]";
}
 

		// fnlStrRes = resultSet.toString();
            System.out.println("dbSelectQ.resultSet.toString: " + fnlStrRes);
            return fnlStrRes;
        } catch (Exception e) {
 
              e.printStackTrace();
            System.out.println("dbSelectQ.error: " + e.toString() + fnlStrRes);
            return fnlStrRes;
        } 

    }











    public String setDBselectQ(String strQstr) {

	JSONArray resultSet = new JSONArray(); 
 	String fnlStrRes = "[]";

        
        try {
conn = DriverManager.getConnection(dburl); 
Statement stmt  = conn.createStatement();
String mnmstring = strQstr.toLowerCase();
if(mnmstring.contains("select ")) {
ResultSet rs = stmt.executeQuery(strQstr);

ResultSetMetaData rsmd = rs.getMetaData();
System.out.println("rsrsrsrsrsrsrs: " +  rs.toString());
int columnCount = rsmd.getColumnCount();
 System.out.println("dbSelectQ.getColumnCount: " + columnCount);

while (rs.next()) {
 System.out.println("dbSelectQ.getFString: " +  rs.getString(1));


JSONObject rowObject = new JSONObject();
for (int i = 0; i < columnCount; i++)
{
System.out.println("dbSelectQ.getFString: " + rsmd.getColumnName(i+1) + " : " +  rs.getString(i+1));

if(rsmd.getColumnName(i+1) != null ) 
{ 
if(rs.getString(i+1) != null)
{
System.out.println("dbSelectQ.getString: " + rsmd.getColumnName(i+1) + " : " +  rs.getString(i+1));
rowObject.put(rsmd.getColumnName(i+1) ,  rs.getString(i+1));
} else {
rowObject.put(rsmd.getColumnName(i+1) ,  "null" ); 
}
}
 
}

resultSet.put(rowObject);
 /* */
}
 } else {
int tint = stmt.executeUpdate(strQstr);
}
 

		fnlStrRes = resultSet.toString();
            System.out.println("dbSelectQ.resultSet.toString: " + fnlStrRes);
            return fnlStrRes;
        } catch (Exception e) {
 
              e.printStackTrace();
            System.out.println("dbSelectQ.error: " + e.toString() + fnlStrRes);
            return fnlStrRes;
        } 

    }







    public String dbSelectQ(boolean isDistinct, String strTable, String[] dcolumns, String qstring, String[] qargs, String strGrpBy, String strHaving, String strOrderBy, String strQlimit) {

	JSONArray resultSet = new JSONArray(); 
 	String fnlStrRes = "noQvalue";

        
        try {
 

          //  -pcsw epDBcursor = sqLiteDatabase.query(isDistinct, strTable, null, qstring, qargs, strGrpBy, strHaving, strOrderBy, strQlimit);
 
            System.out.println("dbSelectQ.resultSet.toString: " + fnlStrRes);
            return fnlStrRes;
        } catch (Exception e) {
 

            System.out.println("dbSelectQ.error: " + e.toString());
            return fnlStrRes;
        } 

    }

 


    public int delete(String table, String qstring, String[] qargs) {
        // return sqLiteDatabase.delete(table, qstring, qargs);
        return 0;
    }

    public int update(String theTable, String qstring, String[] qargs) {
        int retInt = 33;
 

        return retInt;

    }


    public int deleteAll() {
        // sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MYDATABASE_TABLE);

        // return sqLiteDatabase.delete(MYDATABASE_TABLE, null, null);
        return 0;
    }

 

    public int getLastID() {
        int lastId = 1;
        try {

  
          //  String query = "SELECT " + KEY_REC_ID + " from " + MYDATABASE_TABLE + " order by " + KEY_REC_ID + " DESC limit 1";

 
        } catch (Exception e) {
 

            System.out.println("E:getLastID" + e.toString());
        }
        return lastId;

    }


  

 


    public String doStrRecDelete(String strChallID) {
        String retString = "ok";
        try {
 


        } catch (Exception e) {
            retString = "noQvalue";
            System.out.println("doChallDelete: " + e.toString());

        }
        return retString;
    }


    public void doRecordDelete(String strChallID) {
        try {
 

        } catch (Exception e) {
            System.out.println("doChallDelete: " + e.toString());

        }
    }


 

 

 

 

    public String doUpdateRecords(String strQselection, String strQargs, String strConentValues) {

       String resString = "noQvalue";
	String[] strQselArgs;

                        if (strQargs.contains(":")) {
                           strQselArgs = strQargs.split(":");
				} else {
			         strQselArgs = new String[]{strQargs};
				}

   
        StringTokenizer st = new StringTokenizer(strConentValues, "=;");
        while (st.hasMoreTokens()) {
            String key = st.nextToken();
            String val = st.nextToken();
 
            System.out.println(key + "\t" + val);
        }


        try {
 
            resString = String.valueOf("command here");
 
       return resString;
        } catch (Exception e) {
            System.out.println("updateChallRecord: " + e.toString());
 
        return resString;

        }

 
    }


    public String doUpdateRecord(int iTheID, String strConentValues) {

       String resString = "noQvalue";
       String strQselection = "_id=?";
	String[] strQselArgs;
	strQselArgs = new String[]{String.valueOf(iTheID)};
        // ContentValues args = new ContentValues();
        // args.put(KEY_TITLE, title);
        StringTokenizer st = new StringTokenizer(strConentValues, "=;");
        while (st.hasMoreTokens()) {
            String key = st.nextToken();
            String val = st.nextToken();
            // args.put(key, val);
            System.out.println(key + "\t" + val);
        }


        try {
 
            resString = String.valueOf("sql comm here");
 
       return resString;
        } catch (Exception e) {
            System.out.println("updateChallRecord: " + e.toString());
  
        return resString;

        }

 
    }



    public String doJSUpdateRecord(String theTable, String strQselection, String[] strQArgs) {
        String resString = "noQvalue";
        try {
 
            resString = String.valueOf("-pcsw");
 
        } catch (Exception e) {
 
        return resString;
        }
        return resString;
    }

  

    public void doAllRecordsDelete() {

 
       // deleteAll();
 
    }


 


    public static String[] getStrArr(String s, String s1) {
        String s2 = s;
        String s3 = s1;
        StringTokenizer stringtokenizer = new StringTokenizer(s2, s3);
        int i = stringtokenizer.countTokens();
        String as[] = new String[i];
        for (int j = 0; j < i; j++)
            as[j] = stringtokenizer.nextToken();

        return as;
    }











   public void copyDataBase() 
   {
 	String strfnl = "nada";
    	try {
	String strDbra = basicUtils.readFileAsString(basicUtils.getUfile("db_schema.txt"));
	String strDbrb = stringUtils.replaceString(strDbra, "  ", " ");
	String strDbrca = stringUtils.replaceString(strDbrb, "auto_increment", "autoincrement");
	String strDbrcb = stringUtils.replaceString(strDbrca, "int(", "integer(");
	String strDbrc = stringUtils.replaceString(strDbrcb, "\n", " ");

	String strDbrd = stringUtils.replaceString(strDbrc, "  ", " ");

	String[] pNums = StringUtils.readmessTokens(strDbrd, ";");
      System.out.println("copyDataBase.strDbrd: " + strDbrd);
 

	for (int j = 0; j < pNums.length; j++) {

 
	strfnl += setDBselectQ(pNums[j]);
      System.out.println("copyDataBase.pNums[j]: " + j + " : " + pNums[j]);


	}
 	} catch(Exception e) {
 
		 e.printStackTrace();
            System.out.println("copyDataBase: " + e.toString());

	} finally {
 
            System.out.println("copyDataBase:final " + strfnl);
	}

    } 






 




}
