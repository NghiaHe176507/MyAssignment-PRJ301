<%-- 
    Document   : mark
    Created on : Oct 19, 2023, 12:47:05 AM
    Author     : Phạm Văn Nghĩa
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Mark Report</title>
        <meta charset="utf-8">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/mark.css">
    </head>

    <body>
        <form action="mark" method="POST">
            <div class="container">
                <div class="row content">
                    <div class="col-md-8">
                        <h1><span>FPT University Academic Portal</span></h1>
                    </div>
                    <div class="col-md-4">
                        <table>
                            <tbody>
                                <tr>
                                    <td colspan="2" class="auto-style1"><strong>FAP mobile app (myFAP) is ready at</strong></td>
                                </tr>
                                <tr>
                                    <td><a href="https://apps.apple.com/app/id1527723314">
                                            <img src="https://fap.fpt.edu.vn/images/app-store.png"
                                                 style="width: 120px; height: 40px" alt="apple store"></a></td>
                                    <td><a href="https://play.google.com/store/apps/details?id=com.fuct">
                                            <img src="https://fap.fpt.edu.vn/images/play-store.png"
                                                 style="width: 120px; height: 40px" alt="google store"></a></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>


                <span id="sid">Mã sinh viên: ${sessionScope.account.student.id}</span>
                <input type="hidden" id="sid" name="sid" value="${sessionScope.account.student.id}">





                <div class="row">
                    <div class="col-md-12 breadcrumb">
                        <div><span id="ctl00_lblNavigation"><a href="<%= request.getContextPath() %>/home">Home</a>&nbsp;|&nbsp;<b>Grade-book</b></span>
                        </div>


                        <div class="menu">
                            <a href="?view=user">
                                <span id="ctl00_lblLogIn" class="label label-success">${sessionScope.account.displayname}</span></a> | 
                            <a href="<%= request.getContextPath() %>/logout" class="label label-success">logout</a> |
                            <span id="ctl00_lblCampusName" class="label label-success">Campus: ${requestScope.campus[sessionScope.account.cid - 1].name}</span>
                        </div>
                    </div>
                </div>

                <div>
                    <table>
                        <tr>
                            <th>Term</th>
                            <th>Course</th>
                        </tr>

                        <c:forEach items="${requestScope.enroll}" var="e">
                            <c:if test="${e.student.id eq sessionScope.account.student.id}">
                                <tr>
                                    <td>
                                        <a href="#" value="${e.semester.id}">${requestScope.semester[e.semester.id].name}</a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>

                        <c:forEach items="${requestScope.enrolls}" var="enroll">
                            <c:if test="${enroll.student.id eq sessionScope.account.student.id}">
                                <tr>
                                    <td></td>
                                    <td>
                                        <a href="#" value="${enroll.course.id}">${enroll.course.name}</a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>

                    </table>
                </div>



                <div class="footer">
                    <div id="ctl00_divSupport" style="text-align: center">
                        <br>
                        <b>Mọi góp ý, thắc mắc xin liên hệ: </b><span
                            style="color: rgb(34, 34, 34); font-family: arial, sans-serif; font-size: 13.333333969116211px; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; line-height: normal; orphans: auto; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); display: inline !important; float: none;">Phòng
                            dịch vụ sinh viên</span>: Email: <a
                            href="mailto:dichvusinhvien@fe.edu.vn">dichvusinhvien@fe.edu.vn</a>.
                        Điện thoại: <span class="style1"
                                          style="color: rgb(34, 34, 34); font-family: arial, sans-serif; font-size: 13.333333969116211px; font-style: normal; font-variant: normal; letter-spacing: normal; line-height: normal; orphans: auto; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); display: inline !important; float: none;">(024)7308.13.13
                        </span>
                        <br>
                    </div>

                    <p style="text-align: center">
                        © Powered by <a href="http://fpt.edu.vn" target="_blank">FPT University</a>&nbsp;|&nbsp;
                        <a href="http://cms.fpt.edu.vn/" target="_blank">CMS</a>&nbsp;|&nbsp; <a
                            href="http://library.fpt.edu.vn" target="_blank">library</a>&nbsp;|&nbsp; <a
                            href="http://library.books24x7.com" target="_blank">books24x7</a>
                        <span id="ctl00_lblHelpdesk"></span>
                    </p>
                </div>

            </div>
        </form>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.min.js"></script>
    </body>

</html>