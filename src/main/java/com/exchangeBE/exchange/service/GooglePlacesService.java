package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.entity.PlaceEntity;
import com.exchangeBE.exchange.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


//
import com.exchangeBE.exchange.dto.PlaceDto;
import com.google.maps.GeoApiContext;
import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.PlacesApi;
import com.google.maps.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GooglePlacesService {
    @Value("${google.maps.api.key}")
    private String apiKey;

    @Autowired
    private PlaceRepository placeRepository;

    public PlaceEntity savePlace(PlaceEntity place) {
        return placeRepository.save(place);
    }

    public List<PlaceEntity> getAllPlaces() {
        return placeRepository.findAll();
    }

    public String findPlaceFromText(String query) {
        StringBuilder resultBuilder = new StringBuilder();

        try {
            // API 호출 URL 구성
            String url = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json" +
                    "?input=" + URLEncoder.encode(query, "UTF-8") +
                    "&inputtype=textquery" +
                    "&fields=name,formatted_address" +
                    "&key=" + apiKey;

            // HttpURLConnection을 사용하여 요청 보내기
            URL requestUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            // 응답 처리
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 응답 내용을 문자열로 반환
            resultBuilder.append(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
            resultBuilder.append("Error occurred: ").append(e.getMessage());
        }

        return resultBuilder.toString();
    }


}
