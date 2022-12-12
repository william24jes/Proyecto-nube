package com.example.proyectoingweb.servlets.model.dtos;
import java.io.*;
import java.util.ArrayList;

import com.example.proyectoingweb.servlets.model.beans.Incidencias;
import com.example.proyectoingweb.servlets.model.daos.DaoIncidencias;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ResporteIncidenciasEXCEL {
    public void crearDocumento(){

        DaoIncidencias daoIncidencias = new DaoIncidencias();
        ArrayList<Incidencias> incidencias = daoIncidencias.obtenerlistaIncidenciasPDF();


        Workbook libro =new XSSFWorkbook();
        final String nombreArchivo= "ReporteIncidencias.xlsx";
        Sheet hoja = libro.createSheet("Hoja 1");
        String [] encabezados = new String[]{"ID","NOMBRE","DESCRIPCION","ZONA PUCP","TIPO","FECHA","URGENCIA","ESTADO"};
        int indiceFila = 0;

        Row fila = hoja.createRow(indiceFila);
        for (int i=0;i<encabezados.length; i++){
            String encabezado = encabezados[i];
            Cell celda = fila.createCell(i);
            celda.setCellValue(encabezado);
        }
        indiceFila++;
        for (int i =0; i < incidencias.size();i++){
            fila = hoja.createRow(indiceFila);
            Incidencias incidencia = incidencias.get(i);
            fila.createCell(0).setCellValue(incidencia.getIdIncidencia());
            fila.createCell(1).setCellValue(incidencia.getNombre());
            fila.createCell(2).setCellValue(incidencia.getDescripcion());
            fila.createCell(3).setCellValue(incidencia.getZonaPucp().getNombreZona());
            fila.createCell(4).setCellValue(incidencia.getTipo());
            fila.createCell(5).setCellValue(incidencia.getDatetime());
            fila.createCell(6).setCellValue(incidencia.getUrgencia());
            fila.createCell(7).setCellValue(incidencia.getEstadoIncidencia());
            indiceFila++;
        }

        //GUARDAR
        String ruta = System.getProperty("user.home");

        File directorioActual = new File(ruta + "/Desktop/ReporteIncidencias.xlsx");

        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(directorioActual);
            libro.write(outputStream);
            libro.close();
            
        }catch (FileNotFoundException ex){
            System.out.printf("Error de FileNotFoundException");
        }catch (IOException ex){
            System.out.println("Error IOException");
        }





    }


}
