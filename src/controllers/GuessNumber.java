package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GuessNumber
 */
@WebServlet("/GuessNumber")
public class GuessNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuessNumber() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		 HttpSession session = request.getSession(true);
		 ServletContext contexto = this.getServletContext();
		 int menor = 0;
		 int mayor = 0;
		try {
			 menor = Integer.parseInt(request.getParameter("menor"));
			 mayor = Integer.parseInt(request.getParameter("mayor"));
		}catch(Exception e) {
			session.setAttribute("message", "Ambos campos deben ser un número");
			contexto.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		if(menor == mayor) {
			session.setAttribute("message", "Los campos deben ser distintos");
			contexto.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		 if(menor>mayor) {
			 session.setAttribute("message", "El campo mayor debe ser mas grande que el campo menor");
				contexto.getRequestDispatcher("/index.jsp").forward(request, response);
				return; 
		 }
		Random r = new Random();
		int numero = r.nextInt((mayor-menor)+1)+menor;
		session.setAttribute("menor", menor);
		session.setAttribute("mayor", mayor);
		session.setAttribute("valor", numero);
		contexto.getRequestDispatcher("/selectNumber.jsp").forward(request, response);
	
	}

}
