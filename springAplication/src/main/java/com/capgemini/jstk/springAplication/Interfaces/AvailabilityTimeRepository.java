package com.capgemini.jstk.springAplication.Interfaces;

import com.capgemini.jstk.springAplication.AvailabilityTime.AvailabilityTimeEntity;

public interface AvailabilityTimeRepository extends DAO<AvailabilityTimeEntity> {

    void setupAvailabilityTimeDAO();
}
