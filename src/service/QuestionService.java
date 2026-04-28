package service;

import db.DatabaseManager;
import model.Question;

import java.sql.*;
import java.util.*;

public class QuestionService {

    // GET ALL QUESTIONS
    public static List<Question> getAll() {
        List<Question> list = new ArrayList<>();

        try {
            Connection con = DatabaseManager.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM questions");

            while (rs.next()) {
                String[] o = {
                        rs.getString("option1"),
                        rs.getString("option2"),
                        rs.getString("option3"),
                        rs.getString("option4")
                };

                list.add(new Question(
                        rs.getInt("id"),
                        rs.getString("question"),
                        o,
                        rs.getInt("correct"),
                        rs.getString("difficulty")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // GET BY DIFFICULTY
    public static List<Question> getByDifficulty(String diff) {
        List<Question> list = new ArrayList<>();

        try {
            Connection con = DatabaseManager.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM questions WHERE difficulty=?"
            );

            ps.setString(1, diff);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] o = {
                        rs.getString("option1"),
                        rs.getString("option2"),
                        rs.getString("option3"),
                        rs.getString("option4")
                };

                list.add(new Question(
                        rs.getInt("id"),
                        rs.getString("question"),
                        o,
                        rs.getInt("correct"),
                        rs.getString("difficulty")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ADD QUESTION
    public static void add(String q,String o1,String o2,String o3,String o4,int c,String d){
        try{
            Connection con = DatabaseManager.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO questions(question,option1,option2,option3,option4,correct,difficulty) VALUES(?,?,?,?,?,?,?)"
            );

            ps.setString(1,q);
            ps.setString(2,o1);
            ps.setString(3,o2);
            ps.setString(4,o3);
            ps.setString(5,o4);
            ps.setInt(6,c);
            ps.setString(7,d);

            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // DELETE QUESTION
    public static void delete(int id){
        try{
            Connection con = DatabaseManager.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM questions WHERE id=?"
            );

            ps.setInt(1,id);
            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}