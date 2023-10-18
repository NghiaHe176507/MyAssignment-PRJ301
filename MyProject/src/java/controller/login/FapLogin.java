/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.login;

import Dal.AccountDBContext;
import Dal.CampusDBContext;
import Dal.PositionDBContext;
import Entity.Account;
import Entity.Campus;
import Entity.Position;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author Phạm Văn Nghĩa
 */
public class FapLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FapLogin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FapLogin at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CampusDBContext cp = new CampusDBContext();
        ArrayList<Campus> campuses = cp.list();

        PositionDBContext pb = new PositionDBContext();
        ArrayList<Position> positions = pb.list();

        request.setAttribute("campuses", campuses);
        request.setAttribute("positions", positions);
        request.getRequestDispatcher("view/login.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String p = request.getParameter("pid");
        String c = request.getParameter("cid");

        Account param = new Account();

        int pid = Integer.parseInt(p);
        int cid = Integer.parseInt(c);

        param.setUsername(username);
        param.setPassword(password);
        param.setPid(pid);
        param.setCid(cid);

        AccountDBContext db = new AccountDBContext();
        Account loggedUser = db.get(param);

        if (loggedUser == null) {
            response.getWriter().println("Login information is incorrect. Please check your account, password, position or campus again.");
        } else {
            String remember = request.getParameter("remember");

            HttpSession session = request.getSession();
            session.setAttribute("account", loggedUser);

            if (remember != null) {
                Cookie c_user = new Cookie("user", username);
                Cookie c_pass = new Cookie("pass", password);
                c_user.setMaxAge(3600 * 24);
                c_pass.setMaxAge(3600 * 24);
                response.addCookie(c_user);
                response.addCookie(c_pass);
            }
            // Thực hiện chuyển hướng đến trang / home
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
