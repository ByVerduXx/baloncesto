package model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Jugador implements Serializable {

  private static final long serialVersionUID = 1L; // Recomendado para la serialización

  Integer id;
  String nombre;
  Integer votos;

}
