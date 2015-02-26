package com.github.kolorobot.icm.incident;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class IncidentSearchTest {

    private IncidentService incidentService;
    private IncidentRepository incidentRepositoryMock;

    @Before
    public void setUp() throws Exception {
        incidentRepositoryMock = Mockito.mock(IncidentRepository.class);
        incidentService = new IncidentService(incidentRepositoryMock, null, null);
    }

    @Test
    public void searchesById() {
        when(incidentRepositoryMock.findOne(1l)).thenReturn(null);
        incidentService.search("1");
        verify(incidentRepositoryMock).findOne(1l);
        verifyNoMoreInteractions(incidentRepositoryMock);
    }

    @Test
    public void searcheByIdOfNoneExistingIncidentReturnsEmptyResult() {
        when(incidentRepositoryMock.findOne(1l)).thenThrow(EmptyResultDataAccessException.class);
        List<Incident> incidents = incidentService.search("1");
        assertThat(incidents).isEmpty();
        verify(incidentRepositoryMock).findOne(1l);
        verifyNoMoreInteractions(incidentRepositoryMock);
    }

    @Test
    public void searchesByText() {
        when(incidentRepositoryMock.search("%Test%")).thenReturn(null);
        incidentService.search("Test");
        verify(incidentRepositoryMock).search("%Test%");
        verifyNoMoreInteractions(incidentRepositoryMock);
    }

    @Test
    public void specialCharactersAreStrippedOut() {
        when(incidentRepositoryMock.search("%Test%")).thenReturn(null);
        incidentService.search("%T%_e_s%t%");
        verify(incidentRepositoryMock).search("%Test%");
        verifyNoMoreInteractions(incidentRepositoryMock);
    }
}
