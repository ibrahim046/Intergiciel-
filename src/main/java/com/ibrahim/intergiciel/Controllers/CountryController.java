package com.ibrahim.intergiciel.Controllers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.MalformedQueryException;
import org.eclipse.rdf4j.query.QueryEvaluationException;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.eclipse.rdf4j.model.Value;



@Controller
public class CountryController {

    private final RepositoryConnection repositoryConnection;

    @Autowired
    public CountryController(RepositoryConnection repositoryConnection) {
        this.repositoryConnection = repositoryConnection;
    }
    @GetMapping("/country")
    public String getCountryPage() {
        return "country";
    }

    @GetMapping("/countries")
    public ModelAndView getCountries() throws RepositoryException, MalformedQueryException, QueryEvaluationException {
            List<String> countries = new ArrayList();
    
            String query = "SELECT DISTINCT ?country ?capital WHERE { "
                         + "    ?country rdf:type dbo:Country . "
                         + "    ?country dbo:capital ?capital . "
                         + "} LIMIT 193";
    
            TupleQuery tupleQuery = repositoryConnection.prepareTupleQuery(query);
            TupleQueryResult result = tupleQuery.evaluate();
    
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value country = bindingSet.getValue("country");
                Value capital = bindingSet.getValue("capital");
    
                countries.add(country.stringValue() + " - " + capital.stringValue());
            }
    
            result.close();
            ModelAndView modelAndView = new ModelAndView("countries");
            modelAndView.addObject("countries", countries);
            return modelAndView;
        }

        @PostMapping("/country")
        public ModelAndView getCountryDetails(@RequestParam("countryName") String countryName) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
            ModelAndView modelAndView = new ModelAndView("country");
        
            String queryString = "PREFIX dbo: <http://dbpedia.org/ontology/> \n" +
                                 "PREFIX dbp: <http://dbpedia.org/property/> \n" +
                                 "SELECT DISTINCT * WHERE { \n" +
                                 "?country a dbo:Country ; \n" +
                                 "        rdfs:label \"" + countryName + "\"@en ; \n" +
                                 "        ?property ?value . \n" +
                                 "FILTER((LANG(?value)) = \"\" || LANGMATCHES(LANG(?value), \"en\")) \n" +
                                 "} \n" +
                                 "ORDER BY ?property";
        
            try {
                TupleQuery query = repositoryConnection.prepareTupleQuery(queryString);
                TupleQueryResult result = query.evaluate();
                List<Map<String, String>> countryStatistics = new ArrayList<>();
                while (result.hasNext()) {
                    BindingSet bindingSet = result.next();
                    Map<String, String> statistics = new HashMap<>();
                    for (String bindingName : bindingSet.getBindingNames()) {
                        Value value = bindingSet.getValue(bindingName);
                        statistics.put(bindingName, value.stringValue());
                    }
                    countryStatistics.add(statistics);
                }
                List<String> countryDetails = new ArrayList<>();
                for (Map<String, String> statistics : countryStatistics) {
                    String detail = statistics.get("property") + "-" + statistics.get("value");
                    countryDetails.add(detail);

                }
                modelAndView.addObject("countryDetails", countryDetails);
                modelAndView.setViewName("country");
            } catch (QueryEvaluationException e) {
                e.printStackTrace();
            }
        
            return modelAndView;
        }
        
        // L'implémentation n'a pas encore été terminé 
        @PostMapping("/city")
        public ModelAndView getCityDetails(@RequestParam("cityName") String cityName) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
            ModelAndView modelAndView = new ModelAndView("city");
        
            String queryString = "PREFIX dbo: <http://dbpedia.org/ontology/> \n" +
                                 "PREFIX dbp: <http://dbpedia.org/property/> \n" +
                                 "SELECT DISTINCT * WHERE { \n" +
                                 "?city a dbo:city ; \n" +
                                 "        rdfs:label \"" + cityName + "\"@en ; \n" +
                                 "        ?property ?value . \n" +
                                 "FILTER((LANG(?value)) = \"\" || LANGMATCHES(LANG(?value), \"en\")) \n" +
                                 "} \n" +
                                 "ORDER BY ?property";
        
            try {
                TupleQuery query = repositoryConnection.prepareTupleQuery(queryString);
                TupleQueryResult result = query.evaluate();
                List<Map<String, String>> cityStatistics = new ArrayList<>();
                while (result.hasNext()) {
                    BindingSet bindingSet = result.next();
                    Map<String, String> statistics = new HashMap<>();
                    for (String bindingName : bindingSet.getBindingNames()) {
                        Value value = bindingSet.getValue(bindingName);
                        statistics.put(bindingName, value.stringValue());
                    }
                    cityStatistics.add(statistics);
                }
                List<String> cityDetails = new ArrayList<>();
                for (Map<String, String> statistics : cityStatistics) {
                    String detail = statistics.get("property") + "-" + statistics.get("value");
                    cityDetails.add(detail);

                }
                modelAndView.addObject("cityDetails", cityDetails);
                modelAndView.setViewName("city");
            } catch (QueryEvaluationException e) {
                e.printStackTrace();
            }
        
            return modelAndView;
        }
        

}
    


