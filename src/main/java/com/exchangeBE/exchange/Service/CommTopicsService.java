package com.exchangeBE.exchange.Service;


import com.exchangeBE.exchange.Entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommTopicsService {
    public List<String> getTopics(User user) {
        List<String> topics = new ArrayList<>();

        // 기본 주제 추가
        topics.add("꿀팁");
        topics.add("후기");
        topics.add("질문");

        // 사용자 대학에 따른 주제 추가
        if (user.getDomesticUniversity() != null && !user.getDomesticUniversity().isEmpty()) {
            topics.add(user.getDomesticUniversity() + " 게시판");
        }
        if (user.getExchangeUniversity() != null && !user.getExchangeUniversity().isEmpty()) {
            topics.add(user.getExchangeUniversity() + " 게시판");
        }

        return topics;
    }
}
