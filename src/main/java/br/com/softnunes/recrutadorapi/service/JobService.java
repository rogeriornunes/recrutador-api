package br.com.softnunes.recrutadorapi.service;

import br.com.softnunes.recrutadorapi.entity.Job;
import br.com.softnunes.recrutadorapi.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }
}
