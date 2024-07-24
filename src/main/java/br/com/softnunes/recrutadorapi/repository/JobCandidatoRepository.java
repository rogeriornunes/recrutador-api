package br.com.softnunes.recrutadorapi.repository;

import br.com.softnunes.recrutadorapi.entity.JobCandidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCandidatoRepository extends JpaRepository<JobCandidato, Long> {
}
