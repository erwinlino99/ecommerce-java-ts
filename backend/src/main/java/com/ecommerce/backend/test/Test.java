// package com.ecommerce.backend.test;

// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// public class Test {

//     private final JdbcTemplate jdbc;

//     public Test(JdbcTemplate jdbc) {
//         this.jdbc = jdbc;
//     }

//     // GET http://localhost:8080/api/web-user-test
//     // @GetMapping("/web-users")
//     // public List<Map<String, Object>> getWebUsers() {
//     //     // Devuelve todos los registros de la tabla web_user
//     //     return jdbc.queryForList("SELECT * FROM `web_user`");
//     // }

//     @GetMapping("/web-users")
//     public String getWebUsers() {
       
//         return "This are all my web users from my database";
//     }

// }
