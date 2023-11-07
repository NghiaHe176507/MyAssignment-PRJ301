<%-- 
    Document   : mark
    Created on : Oct 19, 2023, 12:47:05 AM
    Author     : Phạm Văn Nghĩa
--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Mark Report</title>
        <meta charset="utf-8">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/mark.css">

        <style>

            table {
                border-collapse: collapse;
                width: 100%;
            }

            th {
                border-bottom: 1px solid black;
                border-right: 1px solid white;
                padding: 8px;
                text-align: left;
            }

            td{
                border-bottom: 0.5px solid whitesmoke;
                padding: 8px;
                text-align: left;
            }

            tfoot{
                border-top: 2.5px solid whitesmoke;
            }

            .auto-style1{
                border-bottom: none;
                padding: 0px;
                text-align: center;
            }

            th {
                background-color: #f2f2f2;
            }

            tr:last-child td {
                /* Loại bỏ border-bottom cho td trong hàng cuối cùng */
                border-bottom: none;
            }
            .grade-report-heading{
                font-family: inherit;
                font-weight: 500;
                line-height: 1.1;
                color: inherit;
                margin: 20px 0;
                padding: 20px ;
                font-size: 30px;
            }
        </style>
    </head>

    <body>
        <form id="mark" action="mark" method="POST">
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

                <input type="hidden" id="sid" name="sid" value="${sessionScope.account.student.id}">

                <div class="row">
                    <div class="col-md-12 breadcrumb">
                        <div><span id="ctl00_lblNavigation"><a href="<%= request.getContextPath() %>/home">Home</a>&nbsp;|&nbsp;<b>Grade-book</b></span>
                        </div>


                        <div class="menu">
                            <a href="?view=user">
                                <span id="ctl00_lblLogIn" class="label label-success">${sessionScope.account.displayname}</span></a> | 
                            <a href="<%= request.getContextPath()%>/logout" class="label label-success">logout</a> |
                            <span id="ctl00_lblCampusName" class="label label-success">Campus: ${requestScope.campus[sessionScope.account.cid - 1].name}</span>
                        </div>
                    </div>
                </div>

                <h3 class="grade-report-heading">Grade report for ${sessionScope.account.displayname} (${sessionScope.account.student.id})</h3>

                <div style="display: flex;">
                    <div style="width: 50%; vertical-align: top;"> 


                        <h3>Select a term, course ...</h3>
                        <table>
                            <thead>
                                <tr>
                                    <th>Term</th>
                                    <th>Course</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <div>
                                            <table>
                                                <c:forEach items="${requestScope.enroll}" var="e">
                                                    <c:if test="${e.student.id eq sessionScope.account.student.id}">
                                                        <tr>
                                                            <td>
                                                                <a href="<%= request.getContextPath()%>/mark?termName=${requestScope.semester[e.semester.id].name}" data-value="${e.semester.id}">
                                                                    ${requestScope.semester[e.semester.id].name}
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach> 
                                            </table>
                                        </div>
                                    </td>

                                    <td>
                                        <div>
                                            <table>
                                                <c:forEach items="${requestScope.enrolls}" var="enroll" varStatus="enrollStatus">
                                                    <c:if test="${enroll.student.id eq sessionScope.account.student.id}">

                                                        <tr>
                                                            <td>
                                                                <a href="<%= request.getContextPath()%>/mark?termName=${requestScope.term}&courseId=${enroll.course.id}" id="courseLink" onclick="showCourseTable();">${enroll.course.name}(${enroll.course.id})</a>
                                                            </td>
                                                        </tr>

                                                    </c:if>
                                                </c:forEach>


                                            </table>
                                        </div>
                                    </td>

                                </tr>
                            </tbody>
                        </table>
                    </div>




                    <table id="courseTable" style="width: 50%; vertical-align: top;">
                        <thead>
                            <tr>
                                <th>GRADE ITEM</th>
                                <th>WEIGHT</th>
                                <th>VALUE</th>
                                <th>COMMENT</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="totalWeight" value="0" />
                            <c:set var="totalScore" value="0.0" />
                            <c:set var="finalExamWeight" value="0" />
                            <c:set var="finalExamScore" value="0.0" />
                            <c:set var="practicalExamWeight" value="0" />
                            <c:set var="practicalExamScore" value="0.0" />
                            <c:set var="finalExamResitWeight" value="0" />
                            <c:set var="finalExamResitScore" value="0.0" />
                            <c:set var="isFinalExam" value="false" />
                            <c:set var="isPracticalExam" value="false" />
                            <c:set var="isFinalExamResit" value="false" />
                            <c:set var="itemCount" value="0" />

                            <c:forEach items="${requestScope.scores}" var="s" varStatus="loop">
                                <c:if test="${s.enrollment.student.id eq sessionScope.account.student.id}">
                                    <tr>
                                        <td>
                                            <a>${s.assessment.grade.name}</a>
                                        </td>
                                        <td>
                                            <a>${s.assessment.grade.name == 'Total' ? 'Total' : s.assessment.weight}${s.assessment.grade.name == 'Total' ? '' : '%'}</a>
                                        </td>
                                        <td>
                                            <a>${s.assessment.grade.name == 'Total' ? 
                                                 (itemCount > 0 ? totalScore / itemCount : '') : s.score}</a>
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
                                        <c:when test="${!loop.last && s.assessment.weight == requestScope.scores[loop.index + 1].assessment.weight 
                                                        && s.assessment.grade.name.replaceAll('[0-9]', '') == requestScope.scores[loop.index + 1].assessment.grade.name.replaceAll('[0-9]', '')}">
                                                <!-- Hãy tiếp tục vòng lặp -->
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${s.assessment.grade.name != 'Final exam' && s.assessment.grade.name != 'Final exam Resit' && s.assessment.grade.name != 'Practical exam'}">
                                                <tr>
                                                    <td>Total</td>
                                                    <td id="totalWeightValue">${totalWeight > 10.0 && totalWeight < 11.0 ? '10.0' : totalWeight}%</td>
                                                    <!-- Đặt ID cho total scores -->
                                                    <td>
                                                        <span id="totalScoreValue">${itemCount > 0 ? totalScore / itemCount : ''}</span>
                                                    </td>
                                                    <td><!-- Giá trị COMMENT cho Total ở đây --></td>
                                                </tr>
                                            </c:if>
                                            <c:set var="totalWeight" value="0" /> 
                                            <c:set var="totalScore" value="0.0" /> 
                                            <c:set var="itemCount" value="0" /> 
                                        </c:otherwise>
                                    </c:choose>

                                    <c:if test="${isFinalExam}">

                                        <tr>
                                            <td>Total</td>
                                            <td>${finalExamWeight}%</td>
                                            <td>${finalExamScore}</td>
                                            <td><!-- Giá trị COMMENT cho Final Exam ở đây --></td>
                                        </tr>
                                        <c:set var="isFinalExam" value="false" />
                                        <c:set var="finalExamWeight" value="0.0" /> 
                                        <c:set var="finalExamScore" value="0.0" /> 
                                    </c:if>

                                    <c:if test="${isPracticalExam}">

                                        <tr>
                                            <td>Total</td>
                                            <td>${practicalExamWeight}%</td>
                                            <td>${practicalExamScore}</td>
                                            <td><!-- Giá trị COMMENT cho Practical Exam ở đây --></td>
                                        </tr>
                                        <c:set var="isPracticalExam" value="false" />
                                        <c:set var="practicalExamWeight" value="0.0" /> 
                                        <c:set var="practicalExamScore" value="0.0" /> 
                                    </c:if>

                                    <c:if test="${isFinalExamResit}">  <!--   && (s.assessment.grade.name != 'Final exam Resit' || loop.last)-->

                                        <tr>
                                            <td>Total</td>
                                            <td>${finalExamResitWeight}%</td>
                                            <td>${finalExamResitScore}</td>
                                            <td><!-- Giá trị COMMENT cho Final Exam Resit ở đây --></td>
                                        </tr>
                                        <c:set var="isFinalExamResit" value="false" />
                                        <c:set var="finalExamResitWeight" value="0.0" /> 
                                        <c:set var="finalExamResitScore" value="0.0" /> 
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </tbody>

                        <tfoot>
                            <tr>
                                <td>
                                    AVERAGE
                                </td>
                                <td>
                                    <c:set var="averageTotalScore" value="0" />
                                    <c:set var="averageWeight" value="0" />
                                    <c:set var="totalscore" value="0"/>
                                    <c:set var="totalEachScore" value="0" />

                                    <c:set var="finalExamResitTotal" value="0"/>
                                    <c:set var="finalExamTotal" value="0"/>
                                    <c:set var="finalStatus" value ="fasle"/>


                                    <c:set var="practialExamTotal" value="0"/>
                                    <c:set var="practialExamResitTotal" value="0"/>
                                    <c:set var="practicalStatus" value ="fasle"/>



                                    <c:set var="theoryExamTotal" value="0"/>
                                    <c:set var="theoryExamResitTotal" value="0"/>
                                    <c:set var="theoryStatus" value ="fasle"/>

                                    <c:set var="listenExamTotal" value="0"/>
                                    <c:set var="listenExamResitTotal" value="0"/>
                                    <c:set var="listenStatus" value ="fasle"/>

                                    <c:set var="gvrExamTotal" value="0"/>
                                    <c:set var="gvrExamResitTotal" value="0"/>
                                    <c:set var="gvrStatus" value ="fasle"/>

                                    <c:set var="finalExamResitTotalTemp" value="0"/>
                                    <c:set var="practialExamResitTotalTemp" value="0"/>
                                    <c:set var="theoryExamResitTotalTemp" value="0"/>
                                    <c:set var="listenExamResitTotalTemp" value="0"/>
                                    <c:set var="gvrExamResitTotalTemp" value="0"/>

                                    <c:forEach items="${requestScope.scores}" var="s">
                                        <c:if test="${s.enrollment.student.id eq sessionScope.account.student.id}">
                                            <c:choose>
                                                <c:when test="${s.assessment.grade.name == 'Final exam Resit' && s.score != null}">
                                                    <c:set var="finalExamResitTotalTemp" value="${finalExamResitTotalTemp + (s.score * (s.assessment.weight/100))}" />
                                                </c:when>

                                                <c:when test="${s.assessment.grade.name == 'Theory exam resit' && s.score != null}">
                                                    <c:set var="theoryExamResitTotalTemp" value="${theoryExamResitTotalTemp + (s.score * (s.assessment.weight/100))}" />
                                                </c:when>

                                                <c:when test="${s.assessment.grade.name == 'Practical exam Resit' && s.score != null}">
                                                    <c:set var="practialExamResitTotalTemp" value="${practialExamResitTotalTemp + (s.score * (s.assessment.weight/100))}" />
                                                </c:when>

                                                <c:when test="${s.assessment.grade.name == 'FE: Listening Resit' && s.score != null}">
                                                    <c:set var="listenExamResitTotalTemp" value="${listenExamResitTotalTemp + (s.score * (s.assessment.weight/100))}" />
                                                </c:when>

                                                <c:when test="${s.assessment.grade.name == 'FE: GVR Resit' && s.score != null}">
                                                    <c:set var="gvrExamResitTotalTemp" value="${gvrExamResitTotalTemp + (s.score * (s.assessment.weight/100))}" />
                                                </c:when>

                                                <c:otherwise>
                                                    <c:if  test="${s.assessment.grade.name == 'Final exam'}">
                                                        <c:set var="finalExamTotal" value="${finalExamTotal + (s.score * (s.assessment.weight/100))}" />
                                                        <c:set var="finalStatus" value ="true"/>
                                                    </c:if>

                                                    <c:if  test="${s.assessment.grade.name == 'Theory exam'}">
                                                        <c:set var="theoryExamTotal" value="${theoryExamTotal + (s.score * (s.assessment.weight/100))}" />
                                                        <c:set var="theoryStatus" value ="true"/>
                                                    </c:if>

                                                    <c:if  test="${s.assessment.grade.name == 'Practical exam'}">
                                                        <c:set var="practialExamTotal" value="${practialExamTotal + (s.score * (s.assessment.weight/100))}" />
                                                        <c:set var="practicalStatus" value ="true"/>
                                                    </c:if>

                                                    <c:if  test="${s.assessment.grade.name == 'FE: Listening'}">
                                                        <c:set var="listenExamTotal" value="${listenExamTotal + (s.score * (s.assessment.weight/100))}" />
                                                        <c:set var="listenStatus" value ="true"/>
                                                    </c:if>

                                                    <c:if  test="${s.assessment.grade.name == 'FE: GVR'}">
                                                        <c:set var="gvrExamTotal" value="${gvrExamTotal + (s.score * (s.assessment.weight/100))}" />
                                                        <c:set var="gvrStatus" value ="true"/>
                                                    </c:if>

                                                    <c:if test="${s.assessment.grade.name != 'Final exam resit' && s.assessment.grade.name != 'Theory exam resit' && s.assessment.grade.name != 'Practical exam Resit'}">
                                                        <c:set var="averageTotalScore" value="${averageTotalScore + s.score}" />
                                                        <c:set var="averageWeight" value="${s.assessment.weight}" />
                                                        <c:set var="totalscore" value="${totalscore + (s.score * (s.assessment.weight/100))}" />
                                                    </c:if>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                    </c:forEach>



                                    <c:set var="finalExamResitTotal" value="${finalExamResitTotalTemp}" />
                                    <c:set var="theoryExamResitTotal" value="${theoryExamResitTotalTemp}" />
                                    <c:set var="practialExamResitTotal" value="${practialExamResitTotalTemp}" />
                                    <c:set var="listenExamResitTotal" value="${listenExamResitTotalTemp}" />
                                    <c:set var="gvrExamResitTotal" value="${gvrExamResitTotalTemp}" />

                                    <c:if test="${finalExamResitTotal != 0 && finalStatus == true && theoryStatus == false}">
                                        <c:set var="totalscore" value="${totalscore + finalExamResitTotal - finalExamTotal}" />
                                    </c:if>

                                    <c:if test="${finalExamResitTotal == 0 && finalStatus == true && theoryStatus == false}">
                                        <c:set var="totalscore" value="${totalscore}" /> 
                                        <!--+ practialExamTotal-->
                                    </c:if>

                                    <c:if test="${theoryExamResitTotal != 0 && practialExamResitTotal != 0 && theoryStatus == true && finalStatus == false}">
                                        <c:set var="totalscore" value="${totalscore + practialExamResitTotal + theoryExamResitTotal - theoryExamTotal - practialExamTotal}" />
                                    </c:if>

                                    <c:if test="${listenExamResitTotal != 0 && gvrExamResitTotal != 0 && listenStatus == true && gvrStatus == true  && finalStatus == false && theoryStatus == false && practicalStatus == false}">
                                        <c:set var="totalscore" value="${totalscore + listenExamResitTotal + gvrExamResitTotal - gvrExamTotal - listenExamTotal}" />
                                    </c:if>

                                    <c:if test="${listenExamResitTotal == 0 && gvrExamResitTotal == 0 && listenStatus == true && gvrStatus == true  && finalStatus == false && theoryStatus == false && practicalStatus == false}">
                                        <c:set var="totalscore" value="${totalscore}" />
                                    </c:if>



                                    <span id="totalscore">${totalscore}</span>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    STATUS
                                </td>
                                <td>
                                    <c:set var="totalEachWeight" value="0" />

                                    <c:set var="sameWeightAndZeroScore" value="true" />
                                    <c:set var="totalEachScore" value="0" />
                                    <c:set var="practicalExamStatus" value="true" />
                                    <c:set var="weightMatchCount" value="0" />
                                    <c:set var="theoryExamStatus" value="true" />
                                    <c:set var="finalExamStatus" value="true" />

                                    <c:forEach items="${requestScope.scores}" var="s" varStatus="loop">
                                        <c:if test="${s.enrollment.student.id eq sessionScope.account.student.id}">
                                            <c:if test="${sameWeightAndZeroScore == true}">
                                                <c:if test="${s.assessment.grade.name != 'Final exam' && s.assessment.grade.name != 'Final exam Resit' && s.assessment.grade.name != 'Practical exam'}">
                                                    <c:if test="${s.assessment.weight == requestScope.scores[loop.index + 1].assessment.weight 
                                                                  && s.assessment.grade.name.replaceAll('[0-9]', '') == requestScope.scores[loop.index + 1].assessment.grade.name.replaceAll('[0-9]', '')}">
                                                        <c:set var="weightMatchCount" value="${weightMatchCount + 1}" />

                                                        <c:set var="totalEachScore" value="${totalEachScore + s.score}" />
                                                    </c:if>
                                                    <c:if test="${weightMatchCount >= 2 && s.assessment.weight != requestScope.scores[loop.index + 1].assessment.weight
                                                                  || s.assessment.grade.name.replaceAll('[0-9]', '') == requestScope.scores[loop.index + 1].assessment.grade.name.replaceAll('[0-9]', '')}">
                                                        <c:if test="${totalEachScore == 0}">
                                                            <c:set var="sameWeightAndZeroScore" value="false" />
                                                            <c:set var="totalEachScore" value="0" />
                                                        </c:if>
                                                    </c:if>
                                                </c:if>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>

                                    <c:forEach items="${requestScope.scores}" var="s" varStatus="loop">
                                        <c:if test="${s.enrollment.student.id eq sessionScope.account.student.id}">
                                            <c:choose>
                                                <c:when test="${s.assessment.grade.name == 'Final exam Resit' && s.score == null}">
                                                    <c:forEach items="${requestScope.scores}" var="s" varStatus="loop">
                                                        <c:choose>
                                                            <c:when test="${s.assessment.grade.name != 'Final exam' }">
                                                                <c:set var="finalExamStatus" value="true" />
                                                            </c:when>
                                                            <c:when test="${s.assessment.grade.name == 'Final exam'}">
                                                                <c:if test="${s.score < 4}">
                                                                    <c:set var="finalExamStatus" value="false" />
                                                                </c:if>
                                                                <c:if test="${s.score >= 4}">
                                                                    <c:set var="finalExamStatus" value="true" />
                                                                </c:if>
                                                            </c:when>
                                                        </c:choose>
                                                    </c:forEach>
                                                </c:when>

                                                <c:when test="${s.assessment.grade.name == 'Final exam Resit' && s.score > 0}">
                                                    <c:if test="${s.score < 4}">
                                                        <c:set var="finalExamStatus" value="false" />
                                                    </c:if>
                                                    <c:if test="${s.score >= 4}">
                                                        <c:set var="finalExamStatus" value="true" />
                                                    </c:if>
                                                </c:when>

                                                <c:when test="${s.assessment.grade.name == 'Final exam' && s.score < 4}">
                                                    <c:forEach items="${requestScope.scores}" var="s" varStatus="loop">
                                                        <c:if test="${s.assessment.grade.name == 'Final exam Resit' && s.score == 0}">
                                                            <c:set var="finalExamStatus" value="false" />
                                                        </c:if>
                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>

                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                    </c:forEach>

                                    <c:forEach items="${requestScope.scores}" var="s" varStatus="loop">
                                        <c:if test="${s.enrollment.student.id eq sessionScope.account.student.id}">
                                            <c:choose>
                                                <c:when test="${s.assessment.grade.name == 'Practical exam Resit' && s.score == null}">
                                                    <c:forEach items="${requestScope.scores}" var="s" varStatus="loop">
                                                        <c:choose>
                                                            <c:when test="${s.assessment.grade.name != 'Practical exam' }">
                                                                <!-- Nếu mục không phải "Practical exam," tiếp tục vòng lặp -->
                                                                <c:set var="practicalExamStatus" value="true" />
                                                            </c:when>
                                                            <c:when test="${s.assessment.grade.name == 'Practical exam'}">
                                                                <c:if test="${s.score < 4}">
                                                                    <c:set var="practicalExamStatus" value="false" />
                                                                </c:if>
                                                                <c:if test="${s.score >= 4}">
                                                                    <c:set var="practicalExamStatus" value="true" />
                                                                </c:if>
                                                            </c:when>
                                                        </c:choose>
                                                    </c:forEach>
                                                </c:when>

                                                <c:when test="${s.assessment.grade.name == 'Practical exam Resit' && s.score > 0}">
                                                    <c:if test="${s.score < 4}">
                                                        <c:set var="practicalExamStatus" value="false" />
                                                    </c:if>
                                                    <c:if test="${s.score >= 4}">
                                                        <c:set var="practicalExamStatus" value="true" />
                                                    </c:if>
                                                </c:when>

                                                <c:when test="${s.assessment.grade.name == 'Practical exam' && s.score < 4}">
                                                    <c:forEach items="${requestScope.scores}" var="s" varStatus="loop">
                                                        <c:if test="${s.assessment.grade.name == 'Practical exam Resit' && s.score == 0}">
                                                            <c:set var="practicalExamStatus" value="false" />
                                                        </c:if>
                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>

                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                    </c:forEach>


                                    <c:forEach items="${requestScope.scores}" var="s" varStatus="loop">
                                        <c:if test="${s.enrollment.student.id eq sessionScope.account.student.id}">
                                            <c:choose>
                                                <c:when test="${s.assessment.grade.name == 'Theory exam resit' && s.score == null}">
                                                    <c:forEach items="${requestScope.scores}" var="s" varStatus="loop">
                                                        <c:choose>
                                                            <c:when test="${s.assessment.grade.name != 'Theory exam' }">

                                                                <c:set var="theoryExamStatus" value="true" />
                                                            </c:when>
                                                            <c:when test="${s.assessment.grade.name == 'Theory exam'}">
                                                                <c:if test="${s.score < 4}">
                                                                    <c:set var="theoryExamStatus" value="false" />
                                                                </c:if>
                                                                <c:if test="${s.score >= 4}">
                                                                    <c:set var="theoryExamStatus" value="true" />
                                                                </c:if>
                                                            </c:when>
                                                        </c:choose>
                                                    </c:forEach>
                                                </c:when>

                                                <c:when test="${s.assessment.grade.name == 'Theory exam resit' && s.score > 0}">
                                                    <c:if test="${s.score < 4}">
                                                        <c:set var="theoryExamStatus" value="false" />
                                                    </c:if>
                                                    <c:if test="${s.score >= 4}">
                                                        <c:set var="theoryExamStatus" value="true" />
                                                    </c:if>
                                                </c:when>

                                                <c:when test="${s.assessment.grade.name == 'Theory exam' && s.score < 4}">
                                                    <c:forEach items="${requestScope.scores}" var="s" varStatus="loop">
                                                        <c:if test="${s.assessment.grade.name == 'Theory exam resit' && s.score == null}">
                                                            <c:set var="theoryExamStatus" value="false" />
                                                        </c:if>
                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>

                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                    </c:forEach>

                                    <c:forEach items="${requestScope.scores}" var="s" varStatus="loop">
                                        <c:if test="${s.enrollment.student.id eq sessionScope.account.student.id}">
                                            <c:if test="${s.score == null}">
                                                <c:set var="nullScoreCount" value="${nullScoreCount + 1}" />
                                            </c:if>
                                        </c:if>
                                    </c:forEach>

                                    <c:set var="checkNullScore" value="0" />
                                    <c:forEach items="${requestScope.scores}" var="s" varStatus="loop">
                                        <c:if test="${s.enrollment.student.id eq sessionScope.account.student.id}">
                                            <c:if test="${s.score == null}">
                                                <c:set var="checkNullScore" value="${checkNullScore + 1}" />
                                            </c:if>
                                        </c:if>
                                    </c:forEach>


                                    <c:if test="${checkNullScore > 3}">
                                        <c:set var="status" value="STUDYING" />
                                    </c:if>
                                    <c:if test="${totalscore >= 5.0 && sameWeightAndZeroScore == true && finalExamStatus == true && practicalExamStatus == true && theoryExamStatus == true && checkNullScore < 3}">
                                        <c:set var="status" value="PASSED" />
                                    </c:if>
                                    <c:if test="${(totalscore < 5.0 || sameWeightAndZeroScore == false || finalExamStatus == false || practicalExamStatus == false || theoryExamStatus == false) && checkNullScore < 3}">
                                        <c:set var="status" value="NOT PASSED" />
                                    </c:if>

                                    <span id="status" style="color:
                                          <c:choose>
                                              <c:when test="${status == 'STUDYING'}">blue</c:when>
                                              <c:when test="${status == 'PASSED'}">green</c:when>
                                              <c:when test="${status == 'NOT PASSED'}">red</c:when>
                                              <c:otherwise>black</c:otherwise>
                                          </c:choose>;
                                          ">
                                        ${status}
                                    </span>

                                </td>
                            </tr>
                        </tfoot>



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


        <!-- JavaScript để làm tròn giá trị sau dấu phẩy -->
        <script>
            function roundToOneDecimal() {
                var elements = document.querySelectorAll("#totalScoreValue");
                elements.forEach(function (element) {
                    var score = parseFloat(element.innerHTML);
                    if (!isNaN(score)) {
                        element.innerHTML = score.toFixed(1);
                    }
                });
            }

            // Gọi hàm làm tròn sau khi trang đã load xong
            window.onload = roundToOneDecimal;
        </script>

        <script>
            // Lấy giá trị totalscore từ thẻ <span>
            var totalscoreElement = document.getElementById('totalscore');
            var totalscoreValue = parseFloat(totalscoreElement.textContent);

            // Làm tròn totalscore đến 1 chữ số thập phân
            var roundedTotalscore = totalscoreValue.toFixed(1);

            // Cập nhật giá trị trong thẻ <span> với totalscore đã làm tròn
            totalscoreElement.textContent = roundedTotalscore;
        </script>


        <script>
            // Kiểm tra nếu requestScope.scores.score == null thì ẩn thẻ div
            if (<%= request.getAttribute("scores") %>.score == null) {
                document.getElementById("courseTable").style.display = "none";
            }
        </script>





        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.min.js"></script>
    </body>

</html>