<%-- 
    Document   : transcript
    Created on : Nov 6, 2023, 7:33:36 AM
    Author     : Ph?m V?n Ngh?a
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>FPT University Academic Portal</title>
        <meta charset="utf-8">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/mark.css">
    </head>

    <body>
        <form action="mark" method="GET">
            <div class="container">
                <div class="row content">
                    <div class="col-md-8">
                        <h1><span>FPT University Academic Portal</span></h1>
                    </div>
                    <div class="col-md-4">
                        <table>
                            <tbody>
                                <tr>
                                    <td colspan="2" class="auto-style1"><strong>FAP mobile app (myFAP) is ready at</strong>
                                    </td>
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


                <h2>Grade report for transcript
                    <span id="ctl00_mainContent_lblRollNumber"><span class="label label-default">HE176507</span> - <span
                            class="label label-info">BIT_SE_17C_NJ</span></span><span id="insert_div">
                        <null> - </null><a
                            href="https://ezse.net/other/grade/gpa.html?data=U1dQMzkx2E5hTtgKSVRFMzAyY9hOYU7YClNXUjMwMthOYU7YClNXVDMwMdhOYU7YCkZFUjIwMthOYU7YCkVOVzQ5MmPYTmFO2ApJU0MzMDHYTmFO2ApQUk0zOTLYTmFO2ApTV0QzOTLYTmFO2ApTRE4zMDFt2E5hTtgKRVhFMTAx2E5hTtgKRVhFMjAx2E5hTtgKUE1HMjAyY9hOYU7YCldEVTIwM2PYTmFO2ApNTE4xMTHYTmFO2ApTRS0wMDA02E5hTtgKTUxOMTIy2E5hTtgKU0VQNDkw2E5hTtgKSENNMjAy2E5hTtgKTUxOMTMx2E5hTtgKVk5SMjAy2E5hTtgKVE1JMTAx2DPYNy40ClNTTDEwMWPYM9g1CkNTSTEwNNgz2DYuNgpQUkYxOTLYM9g2LjEKTUFFMTAx2DPYNi45CkNFQTIwMdgz2DcuOApQUk8xOTLYM9g3LjIKTUFEMTAx2DPYNy42Ck9TRzIwMtgz2DcuOApOV0MyMDNj2DPYOS4yClNTRzEwNNgz2DcuOQpKUEQxMTPYM9g3LjkKQ1NEMjAx2DPYNS40CkRCSTIwMtgz2DcuNwpNQVMyOTHYM9gKSlBEMTIz2DPYCklPVDEwMtgz2DguMwpQUkozMDHYM9gKU1dFMjAxY9hOYU7Y"
                            target="_blank" title="Click to edit grade and recalculate GPA" class="label label-default">GPA:
                            7.253</a><br><br>
                        <h4><a href="https://ezse.net/other/grade/gpa.html?data=VE1JMTAx2DPYNy40" target="_blank"
                               class="label label-primary" title="TMI101: 7.4 x 3
                               Average: 7.4

                               Click to edit grade and recalculate GPA">Spring2022: 7.4</a> <a
                               href="https://ezse.net/other/grade/gpa.html?data=U1NMMTAxY9gz2DUKQ1NJMTA02DPYNi42ClBSRjE5Mtgz2DYuMQpNQUUxMDHYM9g2LjkKQ0VBMjAx2DPYNy44"
                               target="_blank" class="label label-success" title="SSL101c: 5 x 3
                               CSI104: 6.6 x 3
                               PRF192: 6.1 x 3
                               MAE101: 6.9 x 3
                               CEA201: 7.8 x 3
                               Average: 6.48

                               Click to edit grade and recalculate GPA">Fall2022: 6.48</a> <a
                               href="https://ezse.net/other/grade/gpa.html?data=UFJPMTky2DPYNy4yCk1BRDEwMdgz2DcuNgpPU0cyMDLYM9g3LjgKTldDMjAzY9gz2DkuMgpTU0cxMDTYM9g3Ljk="
                               target="_blank" class="label label-primary" title="PRO192: 7.2 x 3
                               MAD101: 7.6 x 3
                               OSG202: 7.8 x 3
                               NWC203c: 9.2 x 3
                               SSG104: 7.9 x 3
                               Average: 7.94

                               Click to edit grade and recalculate GPA">Spring2023: 7.94</a> <a
                               href="https://ezse.net/other/grade/gpa.html?data=SlBEMTEz2DPYNy45CkNTRDIwMdgz2DUuNApEQkkyMDLYM9g3Ljc="
                               target="_blank" class="label label-success" title="JPD113: 7.9 x 3
                               CSD201: 5.4 x 3
                               DBI202: 7.7 x 3
                               Average: 7

                               Click to edit grade and recalculate GPA">Summer2023: 7</a> <a
                               href="https://ezse.net/other/grade/gpa.html?data=TUFTMjkx2DPYCkpQRDEyM9gz2ApJT1QxMDLYM9g4LjMKUFJKMzAx2DPYClNXRTIwMWPYTmFO2A=="
                               target="_blank" class="label label-primary" title="MAS291:  x 3
                               JPD123:  x 3
                               IOT102: 8.3 x 3
                               PRJ301:  x 3
                               SWE201c:  x NaN
                               Average: 8.3

                               Click to edit grade and recalculate GPA">Fall2023: 8.3</a> </h4>
                    </span>
                    <div>
                        <a class="btn" disabled="" style="margin-top: 5px;">Exclude: </a>
                        <input value="GDQP,ENT,VOV,TRS,ÐSA,LAB,OJS,OJT,SYB301" id="excludedSubjectCodes" type="text"
                               onkeydown="return event.key != 'Enter';" class="form-control"
                               style="width: calc(100% - 300px);display: inline;"
                               placeholder="Enter the SUBJECT CODEs to EXCLUDE from GPA calculation, separated by commas (,)"
                               fdprocessedid="iuje6y">
                        <a class="btn btn-warning" id="btnExclude" style="margin-top: 5px;">OK</a>
                        <a class="btn btn-primary" id="btnReset" style="margin-top: 5px;">Reset</a>
                    </div>
                </h2>

                <table class="table table-hover">
                    <thead class="thead-inverse">
                        <tr>
                            <th style="width:10px">No</th>
                            <th style="width:15px">Term</th>
                            <th style="width:80px">Semester</th>
                            <th style="width:60px">Subject Code</th>
                            <th>Subject Name</th>
                            <th style="width:20px">Grade</th>
                            <th style="width:80px">Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="rowNo" value="1" />
                        <c:forEach items="${requestScope.enroll}" var="e">
                            <c:if test="${e.student.id eq sessionScope.account.student.id}">
                                <tr>
                                    <td>${rowNo}</td>
                                    <td>${e.semester.id}</td>
                                    <td>${e.semester.name}</td>
                                    <td>${e.course.id}</td>
                                    <td>${e.course.name}</td>

                                    <c:forEach items="${requestScope.filteredScores}" var="f" varStatus="loop">
                                        <c:if test="${f.enrollment.id eq e.id && f.course.id eq e.course.id}">
                                            <c:if test="${loop.index == 0 || f.enrollment.id != requestScope.filteredScores[loop.index - 1].enrollment.id}">
                                                <c:set var="courseTotal" value="0" />
                                            </c:if>
                                            <c:set var="courseTotal" value="${courseTotal + f.total}" />
                                            <c:if test="${loop.index == requestScope.filteredScores.size() - 1 || f.enrollment.id != requestScope.filteredScores[loop.index + 1].enrollment.id}">
                                                <td>
                                                    <span class="label label-primary">${courseTotal}</span>
                                                </td>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${loop.index != 0 && loop.index != requestScope.filteredScores.size() - 1 && f.enrollment.id != requestScope.filteredScores[loop.index - 1].enrollment.id && f.enrollment.id != requestScope.filteredScores[loop.index + 1].enrollment.id}">
                                            <c:set var="courseTotal" value="${f.total}" />
                                        </c:if>
                                    </c:forEach>






                                    <td>
                                        <span class="label label-success">Passed</span>
                                    </td>
                                </tr>
                                <c:set var="rowNo" value="${rowNo + 1}" />
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>

                <div>
                    <c:forEach items="${requestScope.filteredScores}" var="f" >
                        <p>${f.enrollment.id} : ${f.course.id} : ${f.total}</p>
                        <p></p>

                    </c:forEach>
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