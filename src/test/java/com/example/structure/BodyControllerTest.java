package com.example.structure;

import com.example.structure.body.dto.response.BodyResponse;
import com.example.structure.body.presentation.BodyController;
import com.example.structure.body.service.BodyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
public class BodyControllerTest {

    @InjectMocks
    private BodyController bodyController;

    @Mock
    private BodyService bodyService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void testGetBody() {
//        // Arrange
//        List<BodyResponse> bodyResponses = new ArrayList<>();
//        // Add BodyResponse objects to the list here
//
//        // Mock the behavior of the bodyService
//        when(bodyService.getAllBodys(1)).thenReturn(bodyResponses);
//
//        // Act
//        ResponseEntity<List<BodyResponse>> response = bodyController.getBody();
//
//        // Assert
//        assertThat(response).isNotNull();
//        assertThat(response.getStatusCodeValue()).isEqualTo(200);
//        assertThat(response.getBody()).isEqualTo(bodyResponses);
//    }
}