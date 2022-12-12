package com.example.proyectoingweb.servlets.model.dtos;
import com.example.proyectoingweb.servlets.model.beans.Incidencias;
import com.example.proyectoingweb.servlets.model.daos.DaoIncidencias;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReporteIncidenciaTXT {


    public FileWriter crearDocumento () throws IOException {
        DaoIncidencias daoIncidencias = new DaoIncidencias();
        ArrayList<Incidencias> incidencias = daoIncidencias.obtenerlistaIncidenciasPDF();

        String ruta = System.getProperty("user.home");
        FileWriter fichero = new FileWriter(ruta + "/Desktop/ReporteIncidencias.txt");
        fichero.write("===================================================================\n");
        fichero.write("############### REPORTE DE INCIDENCIAS PUCP #######################\n\n");

        fichero.write("Reporte actual de las incidencias PUCP 2022\n\n");
        fichero.write("ID |         NOMBRE       |         DESCRIPCION      |       ZONA PUCP      |        TIPO        |       FECHA        |      URGENCIA        |       ESTADO\n");

        for(Incidencias incidencia : incidencias){
            String id = String.valueOf(incidencia.getIdIncidencia());
            String nombre = incidencia.getNombre();
            String desc= incidencia.getDescripcion();
            String zona=incidencia.getZonaPucp().getNombreZona();
            String tipo=incidencia.getTipo();
            String date =incidencia.getDatetime();
            String urg= incidencia.getUrgencia();
            String estado= incidencia.getEstadoIncidencia();

            fichero.write(id + " | "+nombre+" | "+desc+" | "+zona+" | "+tipo+" | "+date+" | "+urg+" | "+estado+" \n ");

        }
        fichero.close();
        return fichero;
    }


}
