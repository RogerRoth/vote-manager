package br.com.rogerroth.vote_manager.api.agenda;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="agenda")
public class AgendaModel {

  @Id
  @GeneratedValue
  @Column
  private Integer id;

  @Column(nullable = false)
  private String description;
}
