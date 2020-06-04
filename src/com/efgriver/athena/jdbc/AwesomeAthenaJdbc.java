package com.efgriver.athena.jdbc;

import java.sql.*;

import com.simba.athena.jdbc.DataSource;

public class AwesomeAthenaJdbc {

    static final String JDBC_DRIVER = "com.simba.athena.jdbc.Driver";  
    static final String CONNECTION_URL_FORMAT = "jdbc:awsathena://AwsRegion=%s;AwsCredentialsProviderClass=com.amazonaws.custom.athena.jdbc.CustomIAMRoleAssumptionCredentialsProvider;AwsCredentialsProviderArguments=%s,%s,%s;Workgroup=%s;";

   public static void main(final String[] args) throws InterruptedException {
       final String awsRegionCode = System.getenv("AWS_REGION");
       final String accessKeyId = System.getenv("AWS_ACCESS_KEY_ID");
       final String secretAccessKey = System.getenv("AWS_SECRET_ACCESS_KEY");
       final String roleArn = System.getenv("IAM_ROLE_ARN");
       final String workgroupName = System.getenv("ATHENA_WORKGROUP_NAME");
       final String glueDbName = System.getenv("GLUE_DB_NAME");

       AwesomeAthenaJdbc.executeQuery(
           awsRegionCode, 
           accessKeyId, 
           secretAccessKey, 
           roleArn, 
           workgroupName,
           glueDbName
       );
   }

   private static void executeQuery(String awsRegionCode, String accessKeyId, String secretAccessKey, String roleArn, String workgroupName, String glueDbName) {
       
      Connection conn = null;
      Statement stmt = null;
      try {

            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Create DataSource
            final DataSource ds = new DataSource();

            String connectionUrl = String.format(
                CONNECTION_URL_FORMAT, 
                awsRegionCode, 
                accessKeyId, 
                secretAccessKey, 
                roleArn, 
                workgroupName
            );
            ds.setURL(connectionUrl);

            // STEP 3: Open a connection
            conn = ds.getConnection();
    
            // STEP 4: Execute a statement
            stmt = conn.createStatement();
            String sql = String.format("SELECT id FROM %s.alma limit 10", glueDbName);
            System.out.println(sql);
            final ResultSet rs = stmt.executeQuery(sql);

            // STEP 5: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                final String id = rs.getString("id");
                // Display values
                System.out.println("ID: " + id);
            }
            // STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (final Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (final SQLException se2) {
            } // nothing we can do

            try {
                if (conn != null)
                    conn.close();
            } catch (final SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
}
