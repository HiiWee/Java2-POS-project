package com.project.service;

import com.project.controller.dto.GraphDto;
import com.project.repository.SellRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GraphService {
    SellRepository sellRepository = SellRepository.getInstance();

    public List<GraphDto> getDailyGraphList(List<GraphDto> graphDtos) {
        List<GraphDto> dailyGraphData = new ArrayList<>();
        LocalDate localDate = LocalDate.now();
        for (GraphDto i : graphDtos) {

            int dailyMax = i.getPrice();
            LocalDate date = i.getDate();
            if (localDate.getMonth().getValue() > date.getMonth().getValue() || localDate.minusDays(5).equals(date)) {
                break;
            }
            dailyGraphData.add(new GraphDto(dailyMax / 10000, date));
        }
        return dailyGraphData;
    }

    public List<GraphDto> getMonthlyGraphList(List<GraphDto> graphDtos) {
        List<GraphDto> monthlyGraphData = graphDtos.stream()
                .limit(5)
                .map(graphDto -> new GraphDto(graphDto.getPrice() / 10000, graphDto.getDate()))
                .collect(Collectors.toList());
        return monthlyGraphData;
    }

    public List<GraphDto> getQuantityGraphList(List<GraphDto> graphDtos) {
        List<GraphDto> quantityGraphData = graphDtos.stream()
                .limit(5)
                .map(graphDto -> new GraphDto(graphDto.getName(), graphDto.getQuantity()))
                .collect(Collectors.toList());
        return quantityGraphData;
    }

    public List<GraphDto> dailyList() {
        return getDailyGraphList(sellRepository.fineBydays());
    }

    public List<GraphDto> monthlyList() {
        return getMonthlyGraphList(sellRepository.fineByMonth());
    }

    public List<GraphDto> quantityList() {
        return getQuantityGraphList(sellRepository.fineByQuantity());
    }
}
