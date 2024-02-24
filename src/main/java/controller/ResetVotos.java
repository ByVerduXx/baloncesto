package controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository.ModeloDatos;

public class ResetVotos extends HttpServlet {

  private ModeloDatos bd;

  @Override
  public void init(ServletConfig cfg) throws ServletException {
    bd = new ModeloDatos();
    bd.abrirConexion();
  }

  @Override
  public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    bd.resetVotos();
  }

  @Override
  public void destroy() {
    bd.cerrarConexion();
    super.destroy();
  }

}
