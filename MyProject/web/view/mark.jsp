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
                            <!--                            <th>GRADE CATEGORY |</th>-->
                            <th>GRADE ITEM |</th>
                            <th>WEIGHT |</th>
                            <th>VALUE |</th>
                            <th>COMMENT</th>
                        </tr>

                        <c:set var="totalWeight" value="0" />
                        <c:set var="totalScore" value="0" />
                        <c:set var="finalExamWeight" value="0" />
                        <c:set var="finalExamScore" value="0" />
                        <c:set var="practicalExamWeight" value="0" />
                        <c:set var="practicalExamScore" value="0" />
                        <c:set var="finalExamResitWeight" value="0" />
                        <c:set var="finalExamResitScore" value="0" />
                        <c:set var="isFinalExam" value="false" />
                        <c:set var="isPracticalExam" value="false" />
                        <c:set var="isFinalExamResit" value="false" />
                        <c:set var="itemCount" value="0" />

                        <c:forEach items="${requestScope.scores}" var="s" varStatus="loop">
                            <tr>
                                <td>
                                    <a>${s.assessment.grade.name}</a>
                                </td>
                                <td>
                                    <a>${s.assessment.grade.name == 'Total' ? 'Total' : s.assessment.weight}</a>
                                </td>
                                <td>
                                    <a>${s.score}</a>
                                </td>
                                <td><!-- Giá trị COMMENT ở đây --></td>
                            </tr>

                            <c:choose>
                                <c:when test="${s.assessment.grade.name == 'Final exam'}">
                                    <c:set var="finalExamWeight" value="${finalExamWeight + s.assessment.weight}" />
                                    <c:set var="finalExamScore" value="${finalExamScore + s.score}" />
                                    <c:set var="isFinalExam" value="true" />
                                </c:when>
                                <c:when test="${s.assessment.grade.name == 'Practical exam'}">
                                    <c:set var="practicalExamWeight" value="${practicalExamWeight + s.assessment.weight}" />
                                    <c:set var="practicalExamScore" value="${practicalExamScore + s.score}" />
                                    <c:set var="isPracticalExam" value="true" />
                                </c:when>
                                <c:when test="${s.assessment.grade.name == 'Final exam Resit'}">
                                    <c:set var="finalExamResitWeight" value="${finalExamResitWeight + s.assessment.weight}" />
                                    <c:set var="finalExamResitScore" value="${finalExamResitScore + s.score}" />
                                    <c:set var="isFinalExamResit" value="true" />
                                </c:when>
                                <c:otherwise>
                                    <c:set var="totalWeight" value="${totalWeight + s.assessment.weight}" />
                                    <c:set var="totalScore" value="${totalScore + s.score}" />
                                    <c:set var="itemCount" value="${itemCount + 1}" />
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${!loop.last && s.assessment.weight == requestScope.scores[loop.index + 1].assessment.weight}">
                                    <!-- Hãy tiếp tục vòng lặp -->
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td>Total</td>
                                        <td>${totalWeight}</td>
                                        <!-- Đặt ID cho total scores -->
                                        <td>
                                            <span id="totalScoreValue">${itemCount > 0 ? totalScore / itemCount : ''}</span>
                                        </td>
                                        <td><!-- Giá trị COMMENT cho Total ở đây --></td>
                                    </tr>
                                    <c:set var="totalWeight" value="0" /> <!-- Reset trọng số của Total -->
                                    <c:set var="totalScore" value="0" /> <!-- Reset điểm của Total -->
                                    <c:set var="itemCount" value="0" /> <!-- Reset số lượng mục -->
                                </c:otherwise>
                            </c:choose>

                            <c:if test="${isFinalExam}">
                                <!-- Hiển thị dòng "Total" cho "Final Exam" sau khi hoàn thành -->
                                <tr>
                                    <td>Total</td>
                                    <td>${finalExamWeight}</td>
                                    <td>${finalExamScore}</td>
                                    <td><!-- Giá trị COMMENT cho Final Exam ở đây --></td>
                                </tr>
                                <c:set var="isFinalExam" value="false" />
                                <c:set var="finalExamWeight" value="0" /> <!-- Reset trọng số của Final Exam -->
                                <c:set var="finalExamScore" value="0" /> <!-- Reset điểm của Final Exam -->
                            </c:if>

                            <c:if test="${isPracticalExam}">
                                <!-- Hiển thị dòng "Total" cho "Practical Exam" sau khi hoàn thành -->
                                <tr>
                                    <td>Total</td>
                                    <td>${practicalExamWeight}</td>
                                    <td>${practicalExamScore}</td>
                                    <td><!-- Giá trị COMMENT cho Practical Exam ở đây --></td>
                                </tr>
                                <c:set var="isPracticalExam" value="false" />
                                <c:set var="practicalExamWeight" value="0" /> <!-- Reset trọng số của Practical Exam -->
                                <c:set var="practicalExamScore" value="0" /> <!-- Reset điểm của Practical Exam -->
                            </c:if>

                            <c:if test="${isFinalExamResit && (s.assessment.grade.name != 'Final exam Resit' || loop.last)}">
                                <!-- Hiển thị dòng "Total" cho "Final Exam Resit" sau khi hoàn thành -->
                                <tr>
                                    <td>Total</td>
                                    <td>${finalExamResitWeight}</td>
                                    <td>${finalExamResitScore}</td>
                                    <td><!-- Giá trị COMMENT cho Final Exam Resit ở đây --></td>
                                </tr>
                                <c:set var="isFinalExamResit" value="false" />
                                <c:set var="finalExamResitWeight" value="0" /> <!-- Reset trọng số của Final Exam Resit -->
                                <c:set var="finalExamResitScore" value="0" /> <!-- Reset điểm của Final Exam Resit -->
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

        <script>
            // Làm tròn điểm đến 1 chữ số sau dấu phẩy
            function roundToOneDecimal() {
                var elements = document.querySelectorAll("#scoreValue, #totalScoreValue");

                elements.forEach(function (element) {
                    var score = parseFloat(element.innerHTML);
                    if (!isNaN(score)) {
                        element.innerHTML = score.toFixed(1);
                    }
                });
            }

            // Gọi hàm làm tròn sau khi tài liệu tải xong
            window.addEventListener('load', roundToOneDecimal);
        </script>



        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.min.js"></script>
    </body>

</html>