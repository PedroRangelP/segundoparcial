package com.examen.segundoparcial;

import com.examen.segundoparcial.models.Circulo;
import com.examen.segundoparcial.models.Cuadrado;
import com.examen.segundoparcial.service.FigurasService;
import com.examen.segundoparcial.service.OperacionesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SegundoparcialUnitTests extends SegundoparcialApplicationTests{
    @Autowired
    OperacionesService operacionesService;
    @Autowired
    FigurasService figurasService;

    Circulo circulo = new Circulo();
    Cuadrado cuadrado = new Cuadrado();

    @Test
    public void sumaTest(){
        assertEquals(18.5, operacionesService.suma(12.0,6.5));
    }

    @Test
    public void restaTest(){
        assertEquals(6.5, operacionesService.resta(18.5, 12.0));
    }

    @Test
    public void multiTest(){
        assertEquals(36.900000000000006, operacionesService.multiplicacion(12.3, 3.0));
    }

    @Test
    public void diviTest() throws Exception{
        assertEquals(14.1, operacionesService.division(28.2, 2.0));
    }

    @Test
    public void areaCircTest(){
        circulo.setRadio(3);
        assertEquals(28.274333882308138, figurasService.calcularArea(circulo));
    }

    @Test
    public void areaCuadTest(){
        cuadrado.setLado(6);
        assertEquals(36.0, figurasService.calcularArea(cuadrado));
    }
}
