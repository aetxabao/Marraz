package com.masanz.marraz.db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.masanz.marraz.enums.EForm;
import com.masanz.marraz.io.Serializer;
import com.masanz.marraz.model.AForm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Dao {

    private static final String PST_INSERT_DRAWINGS = "INSERT INTO drawings(ts) VALUES(?)";
    private static final String PST_INSERT_OBJECTS = "INSERT INTO objects(drawing, type, data) VALUES(?, ?, ?)";
    private static final String PST_SELECT_DRAWINGS = "SELECT id, ts FROM drawings ORDER BY id";
    private static final String PST_SELECT_DRAWING_ID = "SELECT id FROM drawings WHERE ts=?";
    private static final String PST_SELECT_OBJECTS = "SELECT id, type, data FROM objects WHERE drawing=? ORDER BY id";

    public static boolean testConnection() {
        return DbCon.testConnection();
    }

    public static void setupConnection(String setup) {
        DbCon.setDbPath(setup);
    }

    public static int save(List<AForm> objects) {
        String ts;
        int drawingId = 0;

        try {
            Connection conn = DbCon.getConnection();

            try(PreparedStatement pst = conn.prepareStatement(PST_INSERT_DRAWINGS)) {
                LocalDateTime localDateTime = LocalDateTime.now();
                ts = localDateTime.toString().substring(0, 19).replace('T', ' ');
                pst.setString(1, ts);
                pst.executeUpdate();
            }catch (Exception e) {
                //e.printStackTrace();
                return -1;
            }

            try(PreparedStatement pst = conn.prepareStatement(PST_SELECT_DRAWING_ID)) {
                pst.setString(1, ts);
                try (ResultSet rs = pst.executeQuery()){
                    if (rs.next()){
                        drawingId = rs.getInt(1);
                    }
                }
            }catch (Exception e) {
                //e.printStackTrace();
                return -2;
            }

            conn.setAutoCommit(false);

            try(PreparedStatement pst = conn.prepareStatement(PST_INSERT_OBJECTS)) {
                for (AForm form : objects) {
                    pst.setInt(1, drawingId);
                    pst.setString(2, form.getType().toString());
                    pst.setString(3, Serializer.toJSON(form));
                    pst.executeUpdate();
                }
            }catch (Exception e) {
                //e.printStackTrace();
                return -3;
            }

        } catch (SQLException e) {
            //e.printStackTrace();
            return -4;
        } finally {
            try {
                DbCon.getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }
        return drawingId;
    }

    public static Map<String, Integer> getDrawings() {
        Map<String, Integer> map = new TreeMap<>();
        try {
            Connection conn = DbCon.getConnection();
            try(PreparedStatement pst = conn.prepareStatement(PST_SELECT_DRAWINGS)) {
                try (ResultSet rs = pst.executeQuery()){
                    while (rs.next()){
                        map.put(rs.getString(2), rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            return null;
        }
        return map;
    }

    public static List<AForm> getForms(int drawingId) {
        List<AForm> list = new ArrayList<>();
        try {
            Connection conn = DbCon.getConnection();
            try(PreparedStatement pst = conn.prepareStatement(PST_SELECT_OBJECTS)) {
                pst.setInt(1, drawingId);
                try (ResultSet rs = pst.executeQuery()){
                    while (rs.next()){
                        AForm form = null;
                        try {
                            form = Serializer.fromJSON(rs.getString(3), EForm.valueOf(rs.getString(2)));
                        } catch (JsonProcessingException e) {
                            System.out.println("Error al leer el objeto con id " + rs.getInt(1) + " de tipo " + rs.getString(2) + " del dibujo " + drawingId);
                            System.out.println(rs.getString(3));
                        }
                        list.add(form);
                    }
                }
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            return null;
        }
        return list;
    }

}
