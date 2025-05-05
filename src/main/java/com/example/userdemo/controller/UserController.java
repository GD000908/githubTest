package com.example.userdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    // 기본 페이지 (input.jsp)
    @GetMapping("/")
    public String inputPage() {
        return "input"; // → WEB-INF/views/input.jsp
    }

    // 사용자 데이터 입력 처리
    @PostMapping("/insert")
    public String insertUser(@RequestParam String name,
                             @RequestParam int age,
                             Model model) {

        try {
            // PostgreSQL 드라이버 로드
            Class.forName("org.postgresql.Driver");

            // Railway PostgreSQL 연결 정보
            Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://nozomi.proxy.rlwy.net:25588/railway",
                "postgres",
                "SmSAhzSffXUkHaLFlsRVZwEskxufSNNk"
            );

            // INSERT 쿼리 실행
            String sql = "INSERT INTO users (name, age) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/list"; // 등록 후 목록 페이지로 이동
    }

    // 사용자 목록 출력 처리
    @GetMapping("/list")
    public String listUsers(Model model) {

        List<String> users = new ArrayList<>();

        try {
            // PostgreSQL 드라이버 로드
            Class.forName("org.postgresql.Driver");

            // Railway PostgreSQL 연결 정보
            Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://nozomi.proxy.rlwy.net:25588/railway",
                "postgres",
                "SmSAhzSffXUkHaLFlsRVZwEskxufSNNk"
            );

            // SELECT 쿼리 실행
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            // 결과 저장
            while (rs.next()) {
                String row = rs.getString("name") + " / " + rs.getInt("age");
                System.out.println("불러온 사용자: " + row); // 콘솔 출력
                users.add(row);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 모델에 값 담아서 JSP로 전달
        model.addAttribute("users", users);
        return "list"; // → WEB-INF/views/list.jsp
    }
}
