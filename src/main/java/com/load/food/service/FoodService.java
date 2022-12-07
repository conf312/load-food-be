package com.load.food.service;

import com.load.food.common.OrderByNull;
import com.load.food.domain.food.Food;
import com.load.food.domain.food.FoodRepository;
import com.load.food.domain.food.QFood;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final QFood food = QFood.food;

    public BooleanExpression eqBusinessStatusCode(String businessStatusCode) {
        if (!StringUtils.hasText(businessStatusCode)) return null;
        return food.businessStatusCode.eq(businessStatusCode);
    }

    public BooleanExpression containsAddress(String address) {
        if (!StringUtils.hasText(address)) return null;
        return food.address.containsIgnoreCase(address);
    }

    public BooleanExpression containsRoadNameAddress(String roadNameAddress) {
        if (!StringUtils.hasText(roadNameAddress)) return null;
        return food.roadNameAddress.containsIgnoreCase(roadNameAddress);
    }

    public BooleanExpression containsBusinessPlaceName(String businessPlaceName) {
        if (!StringUtils.hasText(businessPlaceName)) return null;
        return food.businessPlaceName.containsIgnoreCase(businessPlaceName);
    }

    public BooleanExpression containsBusinessStatusClassificationName(String businessStatusClassificationName) {
        if (!StringUtils.hasText(businessStatusClassificationName)) return null;
        return food.businessPlaceName.containsIgnoreCase(businessStatusClassificationName);
    }

    public HashMap<String, Object> findAll(Food.Request request, Integer page, Integer pageSize) {
        HashMap<String, Object> resultMap = new HashMap<>();

        List<Food> list = jpaQueryFactory.from(food)
            .where (
                eqBusinessStatusCode(request.getBusinessStatusCode()),
                containsAddress(request.getAddress()),
                containsRoadNameAddress(request.getRoadNameAddress()),
                containsBusinessPlaceName(request.getBusinessPlaceName()),
                containsBusinessStatusClassificationName(request.getBusinessStatusClassificationName())
            )
            .offset(page)
            .limit(pageSize)
            .fetch()
            .stream()
            .map(Food::new)
            .collect(Collectors.toList());

        Long totalSize = (long) jpaQueryFactory.select(food.count())
            .from(food)
            .where(
                eqBusinessStatusCode(request.getBusinessStatusCode()),
                containsAddress(request.getAddress()),
                containsRoadNameAddress(request.getRoadNameAddress()),
                containsBusinessPlaceName(request.getBusinessPlaceName()),
                containsBusinessStatusClassificationName(request.getBusinessStatusClassificationName())
            )
            .fetchOne();

        resultMap.put("list", list);
        resultMap.put("page", page);
        resultMap.put("pageSize", pageSize);
        resultMap.put("totalSize", totalSize);

        return resultMap;
    }

    public Food findById(Long id) {
        return foodRepository.findById(id).get();
    }

    public HashMap<String, Object> findClassNameList() {
        HashMap<String, Object> resultMap = new HashMap<>();

        List<String> list = jpaQueryFactory.select(Projections.constructor(String.class,
                food.businessStatusClassificationName
            ))
            .from(food)
            .groupBy(food.businessStatusClassificationName)
            .orderBy(OrderByNull.DEFAULT)
            .fetch();

        resultMap.put("list", list);

        return resultMap;
    }

}
