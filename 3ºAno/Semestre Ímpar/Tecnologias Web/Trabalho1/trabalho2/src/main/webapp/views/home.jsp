<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Eventify - Home</title>
    <link rel="stylesheet" type="text/css" href="../static/css/home.css"> 
    <link rel="icon" href="../static/img/logotipo.png">
</head>
<body>
    <div class="container">
        <header>
            <div class="header-content">
                <h1>Eventify</h1>
                <p>Explore the World of Sports</p>
            </div>
        </header>
        <div id="wrapper">
            <section class="user-session">
                <p>${session}</p>
            </section>
            <main>
                <section class="operations">
                    ${operations}
                </section>
            </main>
        </div>
        <div style="padding-bottom: 40px;">
            <img src="../static/img/background.png" alt="corrida" style="width: 100%;">
        </div>
        <footer>
            <p>&copy; 2024 Eventify. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
