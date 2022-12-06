package com.example.proyectoingweb.servlets.model.dtos;

import com.example.proyectoingweb.servlets.model.beans.Incidencias;
import com.example.proyectoingweb.servlets.model.daos.DaoIncidencias;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ReporteIncidenciasPDF {
    Document documento;
    FileOutputStream fileOutputStream;

    //Fuente de titutlo y parrafo
    Font fuenteTitulo = FontFactory.getFont(FontFactory.TIMES_ROMAN,16);
    Font fuenteParrafo = FontFactory.getFont(FontFactory.HELVETICA,12);

    public void crearDocumento () throws DocumentException, FileNotFoundException {
        documento = new Document(PageSize.A4,35,35,50,50);
        //archivo pdf a generar
        String ruta = System.getProperty("user.home");
        fileOutputStream = new FileOutputStream(ruta + "/Desktop/ReporteIncidencias.pdf");

        //obener una instancia del pdfwritter

        PdfWriter.getInstance(documento, fileOutputStream);

    }

    public void abrirDOcumento (){
        documento.open();
    }

    public void agregarTitulo (String texto) throws DocumentException {
        PdfPTable tabla = new PdfPTable(1);
        PdfPCell celda = new PdfPCell(new Phrase(texto,fuenteTitulo));
        celda.setColspan(5);
        celda.setBorderColor(BaseColor.WHITE);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);

        documento.add(tabla);

    }

    public void agregarParrafo (String texto) throws DocumentException {
        Paragraph parrafo = new Paragraph();
        parrafo.add(new Phrase(texto,fuenteParrafo));
        documento.add(parrafo);
    }

    public void agregarSaltoLiena() throws DocumentException {
        Paragraph saltosliena= new Paragraph();
        saltosliena.add(new Phrase(Chunk.NEWLINE));
        saltosliena.add(new Phrase(Chunk.NEWLINE));
        documento.add(saltosliena);
    }

    public void agregarTablaIncidencias() throws DocumentException {
        PdfPTable tabla = new PdfPTable(8);
        tabla.addCell("ID");
        tabla.addCell("Nombre");
        tabla.addCell("Descripción");
        tabla.addCell("Zona PUCP");
        tabla.addCell("Tipo");
        tabla.addCell("Fecha");
        tabla.addCell("Urgencia");
        tabla.addCell("Estado");

        DaoIncidencias daoIncidencias = new DaoIncidencias();
        ArrayList<Incidencias> incidencias = daoIncidencias.obtenerlistaIncidenciasPDF();

        for(Incidencias incidencia : incidencias){
            tabla.addCell(String.valueOf(incidencia.getIdIncidencia()));
            tabla.addCell(incidencia.getNombre());
            tabla.addCell(incidencia.getDescripcion());
            tabla.addCell(String.valueOf(incidencia.getZonaPucp()));
            tabla.addCell(incidencia.getTipo());
            tabla.addCell(incidencia.getDatetime());
            tabla.addCell(incidencia.getUrgencia());
            tabla.addCell(incidencia.getEstadoIncidencia());

        }
        documento.add(tabla);
        cerrarDocumento();

    }
    public void cerrarDocumento(){
        documento.close();
    }

}
