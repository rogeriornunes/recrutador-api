package br.com.softnunes.recrutadorapi.repository;

import br.com.softnunes.recrutadorapi.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
