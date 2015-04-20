package com.github.kolorobot.icm.incident;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IncidentSearchTest {

    @InjectMocks
    private IncidentService incidentService = new IncidentService();

    @Mock
    private IncidentRepository incidentRepository;


    @Test
    public void searchesById() {
        when(incidentRepository.findOne(1l)).thenReturn(null);
        incidentService.search("1");
        verify(incidentRepository).findOne(1l);
        verifyNoMoreInteractions(incidentRepository);
    }

    @Ignore
    public void searcheByIdOfNoneExistingIncidentReturnsEmptyResult() {
        when(incidentRepository.findOne(1l)).thenThrow(EmptyResultDataAccessException.class);
        List<Incident> incidents = incidentService.search("1");
        assertThat(incidents).isEmpty();
        verify(incidentRepository).findOne(1l);
        verifyNoMoreInteractions(incidentRepository);
    }

    @Test
    public void searchesByText() {
        when(incidentRepository.search("%Test%")).thenReturn(null);
        incidentService.search("Test");
        verify(incidentRepository).search("%Test%");
        verifyNoMoreInteractions(incidentRepository);
    }

    @Test
    public void specialCharactersAreStrippedOut() {
        when(incidentRepository.search("%Test%")).thenReturn(null);
        incidentService.search("%T%_e_s%t%");
        verify(incidentRepository).search("%Test%");
        verifyNoMoreInteractions(incidentRepository);
    }
}
