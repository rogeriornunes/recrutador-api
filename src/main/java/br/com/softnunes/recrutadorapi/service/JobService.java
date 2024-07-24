package br.com.softnunes.recrutadorapi.service;

import br.com.softnunes.recrutadorapi.entity.Job;
import br.com.softnunes.recrutadorapi.entity.JobCandidato;
import br.com.softnunes.recrutadorapi.entity.User;
import br.com.softnunes.recrutadorapi.repository.JobCandidatoRepository;
import br.com.softnunes.recrutadorapi.repository.JobRepository;
import br.com.softnunes.recrutadorapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobCandidatoRepository jobCandidatoRepository;

    @Autowired
    private UserRepository userRepository;

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public void applyJob(Long jobId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = getUserIdByUsername(currentPrincipalName);
        JobCandidato jobCandidato = new JobCandidato();
        jobCandidato.setUserId(user.getId());
        jobCandidato.setJobId(jobId);
        jobCandidatoRepository.save(jobCandidato);
    }

    private User getUserIdByUsername(String username) {
            return userRepository.findByUsername(username);
    }
}
