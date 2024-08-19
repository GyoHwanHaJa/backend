//package com.exchangeBE.exchange.service.trip;
//
//import com.exchangeBE.exchange.entity.Trip.Country;
//import com.exchangeBE.exchange.repository.Trip.CountryRepository;
//import jakarta.annotation.PostConstruct;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class CountryService {
//
//    @Autowired
//    private CountryRepository countryRepository;
//
//    private static final Logger logger = LoggerFactory.getLogger(CountryService.class);
//
//    @PostConstruct
//    public void init() {
//        // 나라 데이터 초기화
//        countryRepository.save(new Country("Eastern Europe", "폴란드"));
//        countryRepository.save(new Country("Eastern Europe", "러시아"));
//        countryRepository.save(new Country("Eastern Europe", "헝가리"));
//        countryRepository.save(new Country("Eastern Europe", "슬로바키아"));
//        countryRepository.save(new Country("Eastern Europe", "체코"));
//
//
//        countryRepository.save(new Country("Western Europe", "프랑스"));
//        countryRepository.save(new Country("Western Europe", "독일"));
//        countryRepository.save(new Country("Western Europe", "네덜란드"));
//        countryRepository.save(new Country("Western Europe", "벨기에"));
//        countryRepository.save(new Country("Western Europe", "스위스"));
//        countryRepository.save(new Country("Western Europe", "영국"));
//        countryRepository.save(new Country("Western Europe", "오스트리아"));
//        countryRepository.save(new Country("Western Europe", "아일랜드"));
//        countryRepository.save(new Country("Western Europe", "룩셈부르크"));
//
//
//        countryRepository.save(new Country("Northern Europe", "덴마크"));
//        countryRepository.save(new Country("Northern Europe", "스웨덴"));
//        countryRepository.save(new Country("Northern Europe", "노르웨이"));
//        countryRepository.save(new Country("Northern Europe", "핀란드"));
//        countryRepository.save(new Country("Northern Europe", "아이슬란드"));
//
//        countryRepository.save(new Country("Southern Europe", "이탈리아"));
//        countryRepository.save(new Country("Southern Europe", "스페인"));
//        countryRepository.save(new Country("Southern Europe", "그리스"));
//        countryRepository.save(new Country("Southern Europe", "포르투갈"));
//    }
//
//    public Map<String, List<Country>> getCountries() {
//        Map<String, List<Country>> countries = new HashMap<>();
//        countries.put("Eastern Europe", countryRepository.findByRegion("Eastern Europe"));
//        countries.put("Western Europe", countryRepository.findByRegion("Western Europe"));
//        countries.put("Northern Europe", countryRepository.findByRegion("Northern Europe"));
//        countries.put("Southern Europe", countryRepository.findByRegion("Southern Europe"));
//
//
//
//        return countries;
//    }
//}
