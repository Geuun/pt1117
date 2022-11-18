package com.mustache.ptbbs.service;

import com.mustache.ptbbs.domain.hospital.dto.HospitalResponse;
import com.mustache.ptbbs.domain.hospital.entity.Hospital;
import com.mustache.ptbbs.repository.HospitalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class HospitalServiceTest {

    private HospitalRepository hospitalRepository = Mockito.mock(HospitalRepository.class);

    private HospitalService hospitalService;

    @BeforeEach
    void setUp() {
        hospitalService = new HospitalService(hospitalRepository);
    }

    @Test
    @DisplayName("3일때 폐업")
    void businessStatusName3() {
        Hospital hospital1 = Hospital.builder()
                .id(718457)
                .businessStatusCode(3)
                .build();

        Mockito.when(hospitalRepository.findById(any()))
                .thenReturn(Optional.of(hospital1));

        HospitalResponse closedHospitalResponse = hospitalService.getHospital(71857);

        assertEquals("폐업", closedHospitalResponse.getBusinessStatusName());

    }

    @Test
    @DisplayName("13일때 영업중")
    void businessStatusName13() {
        Hospital hospital1 = Hospital.builder()
                .id(4)
                .businessStatusCode(13)
                .build();

        Mockito.when(hospitalRepository.findById(any()))
                .thenReturn(Optional.of(hospital1));

        HospitalResponse hospitalResponse = hospitalService.getHospital(71857);

        assertEquals("영업중", hospitalResponse.getBusinessStatusName());
    }
}