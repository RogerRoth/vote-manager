package br.com.rogerroth.vote_manager.api.voting;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VotingRepository extends JpaRepository<VotingModel, Integer>{

  Optional<VotingModel> findByIdSessionAndCpfAssociated(Integer idSession, String cpfAssociated);

  List<VotingModel> findByIdSession(Integer idSession);
}
