<%-- 
    Document   : login
    Created on : Oct 8, 2023, 9:00:58 PM
    Author     : Phạm Văn Nghĩa
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>FPT University Academic Portal</title>
        <meta charset="utf-8">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css" rel="stylesheet">

        <link href="css/login.css" rel="stylesheet" type="text/css"/>
    </head>
    <body style="background-image: url(img/382226814_7441896912506512_1598362730259548959_n.png); background-size: cover; background-repeat: no-repeat; background-attachment: fixed;">
        <form action="login" method="POST" onsubmit="return validateForm()">
            <div class="content">
                <div >
                    <!-- <h1>FPT University Academic Portal</h1> -->
                    <h1 class="main-heading">FPT University Academic Portal</h1>
                </div>

                <div class="app">
                    <table>
                        <tbody>
                            <tr>
                        <strong>FAP mobile app (myFAP) is ready at</strong>
                        </tr>
                        <tr>
                            <td><a href="https://apps.apple.com/app/id1527723314"><img src="https://fap.fpt.edu.vn/images/app-store.png" style="width: 120px; height: 40px" alt="apple store"></a></td>
                            <td><a href="https://play.google.com/store/apps/details?id=com.fuct"><img src="https://fap.fpt.edu.vn/images/play-store.png" style="width: 120px; height: 40px" alt="google store"></a></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="login-table">
                <div class="table" style="border: solid 1px #ccc; padding: 20px;">
                    <h3>Đăng nhập Fap</h3>
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input type="text" class="form-control" id="username" name="username" placeholder="Enter your username" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
                    </div>
                    <div class="form-group">
                        <select class="form-control" id="position" name="pid" required>
                            <option value="" disabled selected>Select your position</option>
                            <c:forEach items="${requestScope.positions}" var="p">
                                <option value="${p.id}">${p.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <select class="form-control" id="campus" name="cid" required>
                            <option value="" disabled selected>Select Campus</option>
                            <c:forEach items="${requestScope.campuses}" var="c">
                                <option value="${c.id}">${c.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-login">Login</button>
                    </div>
                </div>
            </div>

            <div class="footer">
                <p style="text-align: center">
                    © Powered by <a href="http://fpt.edu.vn" target="_blank">FPT University</a>&nbsp;|&nbsp;
                    <a href="http://cms.fpt.edu.vn/" target="_blank">CMS</a>&nbsp;|&nbsp; 
                    <a href="http://library.fpt.edu.vn" target="_blank">library</a>&nbsp;|&nbsp; 
                    <a href="http://library.books24x7.com" target="_blank">books24x7</a>
                    <span id="ctl00_lblHelpdesk"></span>
                </p>
            </div>
        </form>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.min.js"></script>

        <script>
            function validateForm() {
                var username = document.getElementById("username").value;
                var password = document.getElementById("password").value;
                var position = document.getElementById("position").value;
                var campus = document.getElementById("campus").value;

                if (username === "" || password === "" || position === "" || campus === "") {
                    alert("Please fill in all required fields.");
                    return false;
                }
                if (position === "" || campus === "") {
                    alert("Please select your position and campus.");
                    return false;
                }
                return true;
            }
        </script>
    </body>
</html>
