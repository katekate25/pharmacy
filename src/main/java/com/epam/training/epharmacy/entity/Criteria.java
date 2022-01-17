package com.epam.training.epharmacy.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class Criteria<T> {

    private Map<T, Object> parametersMap = new LinkedHashMap<>();

    public Map<T, Object> getParametersMap() {
        return parametersMap;
    }
}
