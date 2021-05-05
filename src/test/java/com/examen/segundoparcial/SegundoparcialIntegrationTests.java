package com.examen.segundoparcial;

import com.examen.segundoparcial.models.Circulo;
import com.examen.segundoparcial.models.Cuadrado;
import com.examen.segundoparcial.models.OperacionRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class SegundoparcialIntegrationTests extends SegundoparcialApplicationTests{
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    MockMvc mockMvc;

    OperacionRequest operacionRequest;
    ObjectMapper mapper;
    Circulo circulo;
    Cuadrado cuadrado;

    @Test
    public void controlSumaTest() throws Exception{
        operacionRequest = new OperacionRequest();
        operacionRequest.setA(12.5);
        operacionRequest.setB(6.0);
        mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(operacionRequest);
        mockMvc.perform(post("/v1/suma").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$").value(18.5));
    }

    @Test
    public void controlRestaTest() throws Exception{
        operacionRequest = new OperacionRequest();
        operacionRequest.setA(18.5);
        operacionRequest.setB(12.0);
        mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(operacionRequest);
        mockMvc.perform(post("/v1/resta").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$").value(6.5));
    }

    @Test
    public void controlMultiTest() throws Exception{
        operacionRequest = new OperacionRequest();
        operacionRequest.setA(12.3);
        operacionRequest.setB(3.0);
        mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(operacionRequest);
        mockMvc.perform(post("/v1/multiplicacion").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$").value(36.900000000000006));
    }

    @Test
    public void controlDiviTest() throws Exception{
        operacionRequest = new OperacionRequest();
        operacionRequest.setA(28.2);
        operacionRequest.setB(2.0);
        mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(operacionRequest);
        mockMvc.perform(post("/v1/division").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$").value(14.1));
    }

    @Test
    public void controlAreaCircTest() throws Exception{
        circulo = new Circulo();
        circulo.setRadio(3);
        mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(circulo);
        mockMvc.perform(post("/v1/circulo/area").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$").value(28.274333882308138));
    }

    @Test
    public void controlAreaCuadTest() throws Exception{
        cuadrado = new Cuadrado();
        cuadrado.setLado(6);
        mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(cuadrado);
        mockMvc.perform(post("/v1/cuadrado/area").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$").value(36.0));
    }

    @Test
    public void controlDiviErrorTest() throws Exception{
        operacionRequest = new OperacionRequest();
        operacionRequest.setA(28.2);
        operacionRequest.setB(0);
        mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(operacionRequest);
        mockMvc.perform(post("/v1/division").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().is5xxServerError());
    }

}
