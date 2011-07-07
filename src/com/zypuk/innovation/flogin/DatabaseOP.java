package com.zypuk.innovation.flogin;

import com.griaule.grFinger.Context;
import com.griaule.grFinger.FingerprintTemplate;
import com.griaule.grFinger.GrErrorException;
import com.griaule.grFinger.GrFinger;
import com.griaule.grFinger.MatchingResult;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

public class DatabaseOP {

    private LoadDataToMemory loadData;
    private Connection con;
    private PreparedStatement insertStmt;
    private PreparedStatement selectStmt;
    private PreparedStatement deleteStmt;
    private static final String URL = "jdbc:mysql://127.0.0.1/openmrs";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USUARIO = "mistaguy";
    private static final String SENHA = "mistaguy";

    public DatabaseOP() throws Exception {
        this.connect();
        this.loadToMemory();
    }

    private void loadToMemory() throws Exception {

        selectStmt = con.prepareStatement("select * from userfingers");
        ResultSet rs = selectStmt.executeQuery();

        /* In order to load the data from the database the method loadDataToMemory expects a result set as a
         * parameter.
         * This result set should be obtained by a select that brings all the templates.
         */
        loadData = new LoadDataToMemory();
        loadData.loadDataToMemory(rs);
    }

    public void insert(String name, byte[] temp1, String pwd) throws Exception {

        insertStmt = con.prepareStatement("INSERT INTO userfingers(username,password,thumb) values" + "('" + name + "','" + pwd + "',?)");
        insertStmt.setBinaryStream(1, new ByteArrayInputStream(temp1), temp1.length);

        insertStmt.executeUpdate();

        this.loadToMemory();
    }

    public void remove(int ID) throws Exception {
        deleteStmt = con.prepareStatement("DELETE FROM userfingers where id = " + ID);
        deleteStmt.executeUpdate();
        this.loadToMemory();
    }

    public LoadDataToMemory getData() {
        return this.loadData;
    }

    public StringTokenizer getComboValues() throws SQLException {

        String s = "";

        for (int i = 0; i < loadData.numberOfRegisters(); i++) {
            s += loadData.getName(i) + "|";
        }

        return new StringTokenizer(s, "|");

    }

    public int authenticate(FormMain frm, GrFinger grFinger, FingerprintTemplate template) throws GrErrorException {

      

        for (int i = 0; i < loadData.numberOfRegisters(); i++) {
            byte[] templateMatrix = loadData.getTemplate1(i);



            FingerprintTemplate referenceTamplate = new FingerprintTemplate(templateMatrix, templateMatrix.length);

            MatchingResult result = grFinger.verify(referenceTamplate, template, Context.DEFAULT_CONTEXT);
            if (result.doesMatched()) {
                frm.username=loadData.getName(i);
                frm.pwd=loadData.getPassword(i);
                return result.getScore();

            }

        }

        return -1;
    }

    private void connect() throws ClassNotFoundException, SQLException {

        String URL = "jdbc:mysql://127.0.0.1/openmrs";
        String DRIVER = "com.mysql.jdbc.Driver";
        String USUARIO = "mistaguy";
        String SENHA = "mistaguy";
        Class.forName(DRIVER);
        con = DriverManager.getConnection(URL, USUARIO, SENHA);

    }
}
