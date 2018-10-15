package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GuessAttempt
 */
@WebServlet("/GuessAttempt")
public class GuessAttempt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuessAttempt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		ArrayList<ArrayList<Object>> intentos = new ArrayList<ArrayList<Object>>();
		HttpSession session = request.getSession(true);
		 ServletContext contexto = this.getServletContext();
		 int valor = 0;
		 if(request.getParameter("cancelar")!=null) {
			 session.invalidate();
			 contexto.getRequestDispatcher("/index.jsp").forward(request, response);
			 return;
		 }
		try {
			 valor = Integer.parseInt(request.getParameter("attempt"));
		}catch(Exception e) {
			session.setAttribute("message", "Debe introducir un número entero");
			contexto.getRequestDispatcher("/selectNumber.jsp").forward(request, response);
			return;
		}
		if(valor == (Integer) session.getAttribute("valor")) {
			session.setAttribute("message", "Has acertado, el numero era " + session.getAttribute("valor"));
			contexto.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}else {
			ArrayList<Object> attempt = new ArrayList<Object>();
			if(session.getAttribute("intentos") != null) {
				intentos = (ArrayList<ArrayList<Object>>) session.getAttribute("intentos");
				attempt.add((int)(intentos.get(intentos.size()-1).get(0))+1);
				attempt.add(valor);
				Date date = new Date();
				attempt.add(date);
			}else {
				attempt.add(1);
				attempt.add(valor);
				Date date = new Date();
				attempt.add(date);
			}
			intentos.add(attempt);
			session.setAttribute("intentos", intentos);
			if(valor>(Integer) session.getAttribute("valor")) {
				session.setAttribute("message", "Intentalo de nuevo, el numero que tienes que adivinar es más pequeño.");
			}else {
				session.setAttribute("message", "Intentalo de nuevo, el numero que tienes que adivinar es más grande.");
			}
			contexto.getRequestDispatcher("/selectNumber.jsp").forward(request, response);
		}
	}

}
