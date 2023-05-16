package com.ibrahim.intergiciel.config;

import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Configuration
public class RDF4JConfig {

    @Value("${rdf4j.url}")
    private String rdf4jUrl;

    @Value("${rdf4j.repository}")
    private String rdf4jRepository;

    @Bean
    public RepositoryConnection repositoryConnection() throws RepositoryException {
        Repository repository = new SPARQLRepository(rdf4jUrl + rdf4jRepository);
        repository.init();
        return repository.getConnection();
    }
}
}
