package com.vodafone.vodalife.service;

import com.vodafone.vodalife.model.RecyclingPoint;
import com.vodafone.vodalife.repository.RecyclingPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecyclingPointService {

    @Autowired
    private RecyclingPointRepository recyclingPointRepository;

    public RecyclingPoint createRecyclingPoint(RecyclingPoint recyclingPoint) {
        return recyclingPointRepository.save(recyclingPoint);
    }

    public RecyclingPoint updateRecyclingPoint(String id, RecyclingPoint recyclingPoint) {
        recyclingPoint.setId(id);
        return recyclingPointRepository.save(recyclingPoint);
    }

    public List<RecyclingPoint> getAllRecyclingPoints() {
        return recyclingPointRepository.findAll();
    }

    public List<RecyclingPoint> getNearbyRecyclingPoints(double latitude, double longitude, double radiusInKm) {
        return recyclingPointRepository.findAll().stream()
                .filter(point -> calculateDistance(latitude, longitude, point.getCoordinate().getLatitude(), point.getCoordinate().getLongitude()) <= radiusInKm)
                .collect(Collectors.toList());
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371; // Dünya'nın yarıçapı (km)
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    public RecyclingPoint getRecyclingPointById(String id) {
        return recyclingPointRepository.findById(id).orElse(null);
    }
}