package br.com.rogerroth.vote_manager.api.voting;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "voting")
public class VotingModel {
  
  @Id
  @GeneratedValue
  private Integer id;

  @Column(name = "id_session", nullable = false)
  private Integer idSession;

  @Column(name = "cpf_associated", nullable = false)
  private String cpfAssociated;

  @Column(name = "vote", nullable = false)
  private Boolean vote;
}
