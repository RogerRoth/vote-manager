package br.com.rogerroth.vote_manager.api.session;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<SessionModel, Integer> {
  
  Optional<SessionModel> findByIdAgenda(Integer id);

  @Query("SELECT s FROM SessionModel s WHERE s.idAgenda = :idAgenda AND s.endAt > :now")
  Optional<SessionModel> findByOpeningSession(@Param("idAgenda") Integer idAgenda, @Param("now") LocalDateTime now);
}
