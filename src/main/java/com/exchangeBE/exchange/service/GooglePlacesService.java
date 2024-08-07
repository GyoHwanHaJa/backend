package com.exchangeBE.exchange.service;

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

    public List<PlaceDto> searchPlaces(String query) {
        List<PlaceDto> places = new ArrayList<>();

        try {
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(apiKey)
                    .build();

            PlacesSearchResponse response = PlacesApi.textSearchQuery(context, query).await();

            for (PlacesSearchResult result : response.results) {
                String name = result.name;
                String city = extractCity(result, context);
                String type = extractType(result);

                places.add(new PlaceDto(name, city, type));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return places;
    }

    private String extractCity(PlacesSearchResult result, GeoApiContext context) {
        try {
            PlaceDetails details = PlacesApi.placeDetails(context, result.placeId).await();
            for (AddressComponent component : details.addressComponents) {
                for (AddressComponentType type : component.types) {
                    if (type == AddressComponentType.LOCALITY) {
                        return component.longName;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private String extractType(PlacesSearchResult result) {
        if (result.types != null && result.types.length > 0) {
            String type = result.types[0].toString();
            // 타입을 한글로 변환
            switch (type) {
                case "restaurant":
                    return "식당";
                case "school":
                    return "학교";
                case "park":
                    return "공원";
                // 필요한 만큼 타입을 추가
                default:
                    return type;
            }
        }
        return "";
    }

}
