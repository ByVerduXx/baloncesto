package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jugador;
import repository.ModeloDatos;

public class VerVotos extends HttpServlet {
  private ModeloDatos bd;

  @Override
  public void init(ServletConfig cfg) throws ServletException {
    bd = new ModeloDatos();
    bd.abrirConexion();
  }

  @Override
  public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    HttpSession s = req.getSession(true);

    List<Jugador> jugadores = bd.getJugadores();
    s.setAttribute("votos", jugadores);

    res.sendRedirect(res.encodeRedirectURL("VerVotos.jsp"));
  }

  @Override
  public void destroy() {
    bd.cerrarConexion();
    super.destroy();
  }
}
