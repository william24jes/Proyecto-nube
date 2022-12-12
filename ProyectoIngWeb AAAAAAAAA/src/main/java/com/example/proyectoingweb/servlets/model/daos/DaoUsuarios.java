package com.example.proyectoingweb.servlets.model.daos;

import com.example.proyectoingweb.servlets.model.beans.Credenciales;
import com.example.proyectoingweb.servlets.model.beans.Usuarios;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class DaoUsuarios extends DaoBase{

    public ArrayList<Usuarios> obtenerlistaUsuarios(){
        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();

        String sql = "SELECT * FROM usuarios ORDER BY idUsuario LIMIT 0,15";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                Usuarios usuarios = new Usuarios();

                usuarios.setIdUsuarios(rs.getInt(1));
                usuarios.setNombres(rs.getString(2));
                usuarios.setApellidos(rs.getString(3));
                usuarios.setDni(rs.getString(4));
                usuarios.setCelular(rs.getString(5));
                usuarios.setCodigoPucp(rs.getString(6));
                usuarios.setCorreoPucp(rs.getString(7));
                usuarios.setCategorias(rs.getString(8));
                usuarios.setRol(rs.getString(9));

                listaUsuarios.add(usuarios);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    public ArrayList<Usuarios> obtenerlistaUsuariosRegistrados(){
        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();

        String sql = "select codigoPucp from mydb2.credenciales";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                Usuarios usuarios = new Usuarios();

                usuarios.setCodigoPucp(rs.getString(1));

                listaUsuarios.add(usuarios);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    public ArrayList<Usuarios> obtenerlistaUsuariosCompleta(){
        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();

        String sql = "SELECT * FROM mydb2.usuarios ORDER BY idUsuario";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                Usuarios usuarios = new Usuarios();

                usuarios.setIdUsuarios(rs.getInt(1));
                usuarios.setNombres(rs.getString(2));
                usuarios.setApellidos(rs.getString(3));
                usuarios.setDni(rs.getString(4));
                usuarios.setCelular(rs.getString(5));
                usuarios.setCodigoPucp(rs.getString(6));
                usuarios.setCorreoPucp(rs.getString(7));
                usuarios.setCategorias(rs.getString(8));
                usuarios.setRol(rs.getString(9));

                listaUsuarios.add(usuarios);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }
    public ArrayList<Usuarios> obtenerlistaFotoNull(){
        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();

        String sql = "SELECT * FROM mydb2.usuarios where fotoPerfil Is NULL";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                Usuarios usuarios = new Usuarios();
                usuarios.setIdUsuarios(rs.getInt(1));
                listaUsuarios.add(usuarios);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    public ArrayList<Usuarios> obtenerlistaUsuariosCompletaOrdenada(String tipo,String orden){
        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();

        String sql = "SELECT * FROM mydb2.usuarios ORDER BY "+tipo+" "+orden;

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                Usuarios usuarios = new Usuarios();

                usuarios.setIdUsuarios(rs.getInt(1));
                usuarios.setNombres(rs.getString(2));
                usuarios.setApellidos(rs.getString(3));
                usuarios.setDni(rs.getString(4));
                usuarios.setCelular(rs.getString(5));
                usuarios.setCodigoPucp(rs.getString(6));
                usuarios.setCorreoPucp(rs.getString(7));
                usuarios.setCategorias(rs.getString(8));
                usuarios.setRol(rs.getString(9));

                listaUsuarios.add(usuarios);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    public Usuarios buscarPorId(String idUsuario){

        String sql = "SELECT * FROM usuarios WHERE idUsuario = ?";
        Usuarios usuarios = null;

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idUsuario);

            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    usuarios = new Usuarios();

                    usuarios.setIdUsuarios(rs.getInt(1));
                    usuarios.setNombres(rs.getString(2));
                    usuarios.setApellidos(rs.getString(3));
                    usuarios.setDni(rs.getString(4));
                    usuarios.setCelular(rs.getString(5));
                    usuarios.setCodigoPucp(rs.getString(6));
                    usuarios.setCorreoPucp(rs.getString(7));
                    usuarios.setCategorias(rs.getString(8));
                    usuarios.setRol(rs.getString(9));
                    usuarios.setFotoPerfil(rs.getString(10));
                }
            }

        }
        catch (SQLException e){
            throw new RuntimeException();
        }

        return usuarios;
    }

    public Usuarios buscarPorCorreo(String correoPucp){

        String sql = "SELECT * FROM usuarios WHERE correoPucp = ?";
        Usuarios usuarios = null;

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correoPucp);

            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    usuarios = new Usuarios();

                    usuarios.setIdUsuarios(rs.getInt(1));
                    usuarios.setNombres(rs.getString(2));
                    usuarios.setApellidos(rs.getString(3));
                    usuarios.setDni(rs.getString(4));
                    usuarios.setCelular(rs.getString(5));
                    usuarios.setCodigoPucp(rs.getString(6));
                    usuarios.setCorreoPucp(rs.getString(7));
                    usuarios.setCategorias(rs.getString(8));
                    usuarios.setRol(rs.getString(9));
                    usuarios.setFotoPerfil(rs.getString(10));
                }
            }

        }
        catch (SQLException e){
            throw new RuntimeException();
        }

        return usuarios;

    }

    public String obtenerRol(String codigo, String passw, String correo){

        String sql = "SELECT * FROM usuarios WHERE codigoPucp = ? and contrasena = ? and correoPucp = ?";
        String rol = null;
        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigo);
            pstmt.setString(2, passw);
            pstmt.setString(3, correo);

            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    rol = rs.getString(9);
                }
            }
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        return rol;
    }

    public Credenciales validarUsuarioPassword(String codigo, String passw, String correo ){

        Credenciales credenciales = null;
        String sql = "select * from credenciales " +
                "where correoPucp = ? and codigoPucp = ? and contrasenaHasheada = sha2(?, 256)";

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);
            pstmt.setString(2, codigo);
            pstmt.setString(3, passw);

            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    credenciales = new Credenciales();

                    credenciales.setIdUsuario(rs.getString(1));
                    credenciales.setCorreoPucp(rs.getString(2));
                    credenciales.setCodigoPucp(rs.getString(3));
                    credenciales.setContrasenaHasheada(rs.getString(4));
                }
            }
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        return credenciales;
    }

    public boolean guardarUsuario(Usuarios usuarios){

        String sql = "INSERT INTO usuarios (nombres, apellidos, dni, celular, codigoPucp, correoPucp, categoria, rol, fotoPerfil) VALUES (?,?,?,?,?,?,?,?,?)";

        try(Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {

            if (usuarios.getCodigoPucp().length()<=8 && usuarios.getDni().length()<=8 && usuarios.getCelular().length()<=9){

                pstmt.setString(1,usuarios.getNombres());
                pstmt.setString(2,usuarios.getApellidos());
                pstmt.setString(3,usuarios.getDni());
                pstmt.setString(4,usuarios.getCelular());
                pstmt.setString(5,usuarios.getCodigoPucp());
                pstmt.setString(6,usuarios.getCorreoPucp());
                pstmt.setString(7,usuarios.getCategorias());
                pstmt.setString(8,usuarios.getRol());
                pstmt.setString(9,"perfilDefault.png");
                //pstmt.setNull(9, Types.VARCHAR);

                pstmt.executeUpdate();

                return true;
            }
            else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean actualizarUsuario(Usuarios usuarios){
        String sql = "UPDATE usuarios SET nombres = ?, apellidos = ?, dni = ?, celular = ?, codigoPucp = ?, correoPucp = ?, categoria = ?, rol = ? WHERE idUsuario = ?";

        try(Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {

            if (usuarios.getCodigoPucp().length()<=8 && usuarios.getDni().length()<=8 && usuarios.getCelular().length()<=9){

                pstmt.setString(1,usuarios.getNombres());
                pstmt.setString(2,usuarios.getApellidos());
                pstmt.setString(3,usuarios.getDni());
                pstmt.setString(4,usuarios.getCelular());
                pstmt.setString(5,usuarios.getCodigoPucp());
                pstmt.setString(6,usuarios.getCorreoPucp());
                pstmt.setString(7,usuarios.getCategorias());
                pstmt.setString(8,usuarios.getRol());
                pstmt.setInt(9, usuarios.getIdUsuarios());

                pstmt.executeUpdate();


                return true;
            }
            else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void actualizar_usuario_telefono(Usuarios idUser) {
        String sql = "UPDATE usuarios SET celular = ? WHERE idUsuario = ?";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, idUser.getCelular());
            pstmt.setInt(2, idUser.getIdUsuarios());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void actualizar_usuario_telefono_fotoPerfil(Usuarios idUser, InputStream file) {
        String sql = "UPDATE usuarios SET celular = ?,fotoPerfil = ? WHERE idUsuario = ?";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, idUser.getCelular());
            if (file != null) {
                pstmt.setBlob(2, file);
            }
            pstmt.setInt(3, idUser.getIdUsuarios());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizar_userAdmin_datos_foto(Usuarios idUser, InputStream file) {
        String sql = "UPDATE usuarios SET  codigoPucp = ?, nombres = ?, apellidos = ?,correoPucp = ?, dni = ?, celular = ?, fotoPerfil = ? WHERE idUsuario = ?";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, idUser.getCodigoPucp());
            pstmt.setString(2, idUser.getNombres());
            pstmt.setString(3, idUser.getApellidos());
            pstmt.setString(4, idUser.getCorreoPucp());
            pstmt.setString(5, idUser.getDni());
            pstmt.setString(6, idUser.getCelular());
            if (file != null) {
                pstmt.setBlob(7, file);
            }
            pstmt.setString(8, String.valueOf(idUser.getIdUsuarios()));

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizar_userAdmin_datos(Usuarios idUser) {
        String sql = "UPDATE usuarios SET  codigoPucp = ?, nombres = ?, apellidos = ?,correoPucp = ?, dni = ?, celular = ? WHERE idUsuario = ?";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, idUser.getCodigoPucp());
            pstmt.setString(2, idUser.getNombres());
            pstmt.setString(3, idUser.getApellidos());
            pstmt.setString(4, idUser.getCorreoPucp());
            pstmt.setString(5, idUser.getDni());
            pstmt.setString(6, idUser.getCelular());
            pstmt.setString(7, String.valueOf(idUser.getIdUsuarios()));
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminar_fotoPerfil(Usuarios idUser, InputStream file) {
        String sql = "UPDATE usuarios SET fotoPerfil = ? WHERE idUsuario = ?";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            if (file != null) {
                pstmt.setBlob(1, file);
            }
            pstmt.setInt(2, idUser.getIdUsuarios());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrarUsuario(String idUsuario){
        String sql = "DELETE from credenciales WHERE idUsuario = ?";

        try(Connection connection = this.getConnection();
            PreparedStatement pstmt=connection.prepareStatement(sql))
        {

            pstmt.setString(1,idUsuario);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Usuarios> buscarUsuariosCompleto(String nombreuser){
        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM mydb2.usuarios WHERE lower(nombres) like ? or lower(apellidos) like ? or dni like ? or codigoPucp like ?";

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%"+nombreuser+"%");
            pstmt.setString(2, "%"+nombreuser+"%");
            pstmt.setString(3, "%"+nombreuser+"%");
            pstmt.setString(4, "%"+nombreuser+"%");


            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    Usuarios usuario = new Usuarios();

                    usuario.setIdUsuarios(rs.getInt(1));
                    usuario.setNombres(rs.getString(2));
                    usuario.setApellidos(rs.getString(3));
                    usuario.setDni(rs.getString(4));
                    usuario.setCelular(rs.getString(5));
                    usuario.setCodigoPucp(rs.getString(6));
                    usuario.setCorreoPucp(rs.getString(7));
                    usuario.setCategorias(rs.getString(8));
                    usuario.setRol(rs.getString(9));

                    listaUsuarios.add(usuario);

                }
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }

        return listaUsuarios;
    }

    public ArrayList<Usuarios> buscarUsuarios(String nombreuser){
        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM mydb2.usuarios WHERE lower(nombres) like ? or lower(apellidos) like ? or dni like ? or codigoPucp like ? limit 0,15";

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%"+nombreuser+"%");
            pstmt.setString(2, "%"+nombreuser+"%");
            pstmt.setString(3, "%"+nombreuser+"%");
            pstmt.setString(4, "%"+nombreuser+"%");


            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    Usuarios usuario = new Usuarios();

                    usuario.setIdUsuarios(rs.getInt(1));
                    usuario.setNombres(rs.getString(2));
                    usuario.setApellidos(rs.getString(3));
                    usuario.setDni(rs.getString(4));
                    usuario.setCelular(rs.getString(5));
                    usuario.setCodigoPucp(rs.getString(6));
                    usuario.setCorreoPucp(rs.getString(7));
                    usuario.setCategorias(rs.getString(8));
                    usuario.setRol(rs.getString(9));

                    listaUsuarios.add(usuario);

                }
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }

        return listaUsuarios;
    }

    public ArrayList<Usuarios> paginarUsuarios(int i){
        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
        int inicio=15*(i-1);

        String sql = "SELECT * FROM mydb2.usuarios ORDER BY idUsuario LIMIT "+inicio+","+"15";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                Usuarios usuarios = new Usuarios();

                usuarios.setIdUsuarios(rs.getInt(1));
                usuarios.setNombres(rs.getString(2));
                usuarios.setApellidos(rs.getString(3));
                usuarios.setDni(rs.getString(4));
                usuarios.setCelular(rs.getString(5));
                usuarios.setCodigoPucp(rs.getString(6));
                usuarios.setCorreoPucp(rs.getString(7));
                usuarios.setCategorias(rs.getString(8));
                usuarios.setRol(rs.getString(9));

                listaUsuarios.add(usuarios);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    public ArrayList<Usuarios> paginarUsuariosBuscados(int i, String nombreuser){
        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
        int inicio=15*(i-1);

        String sql = "SELECT * FROM mydb2.usuarios WHERE lower(nombres) like ? or lower(apellidos) like ? or dni like ? or codigoPucp like ? LIMIT "+inicio+","+"15";

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%"+nombreuser+"%");
            pstmt.setString(2, "%"+nombreuser+"%");
            pstmt.setString(3, "%"+nombreuser+"%");
            pstmt.setString(4, "%"+nombreuser+"%");


            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    Usuarios usuario = new Usuarios();

                    usuario.setIdUsuarios(rs.getInt(1));
                    usuario.setNombres(rs.getString(2));
                    usuario.setApellidos(rs.getString(3));
                    usuario.setDni(rs.getString(4));
                    usuario.setCelular(rs.getString(5));
                    usuario.setCodigoPucp(rs.getString(6));
                    usuario.setCorreoPucp(rs.getString(7));
                    usuario.setCategorias(rs.getString(8));
                    usuario.setRol(rs.getString(9));

                    listaUsuarios.add(usuario);

                }
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }

        return listaUsuarios;
    }

    public ArrayList<Usuarios> paginarUsuariosOrdenados(int i,String tipo, String orden){
        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
        int inicio=15*(i-1);

        String sql = "SELECT * FROM mydb2.usuarios ORDER BY "+tipo+" "+orden+" LIMIT "+inicio+","+"15";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                Usuarios usuarios = new Usuarios();

                usuarios.setIdUsuarios(rs.getInt(1));
                usuarios.setNombres(rs.getString(2));
                usuarios.setApellidos(rs.getString(3));
                usuarios.setDni(rs.getString(4));
                usuarios.setCelular(rs.getString(5));
                usuarios.setCodigoPucp(rs.getString(6));
                usuarios.setCorreoPucp(rs.getString(7));
                usuarios.setCategorias(rs.getString(8));
                usuarios.setRol(rs.getString(9));

                listaUsuarios.add(usuarios);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    public Usuarios validarRegistro(String correoPucp, String codigoPucp){

        String sql = "select u.* from usuarios u " +
                "left join credenciales c on (u.idUsuario = c.idUsuario) " +
                "where u.correoPucp = ? and u.codigoPucp = ? and c.contrasenaHasheada is null";
        Usuarios usuario = null;

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correoPucp);
            pstmt.setString(2, codigoPucp);

            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    usuario = new Usuarios();

                    usuario.setIdUsuarios(rs.getInt(1));
                    usuario.setNombres(rs.getString(2));
                    usuario.setApellidos(rs.getString(3));
                    usuario.setDni(rs.getString(4));
                    usuario.setCelular(rs.getString(5));
                    usuario.setCodigoPucp(rs.getString(6));
                    usuario.setCorreoPucp(rs.getString(7));
                    usuario.setCategorias(rs.getString(8));
                    usuario.setRol(rs.getString(9));
                    usuario.setFotoPerfil(rs.getString(11));

                }
            }

        }catch (SQLException e){
            e.getStackTrace();
        }

        return usuario;

    }

    public Credenciales validarCambioPassword(int idUsuario, String password){

        String sql = "select * from credenciales where idUsuario = ? and contrasenaHasheada = sha2(?, 256)";
        Credenciales credenciales = null;

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, idUsuario);
            pstmt.setString(2, password);

            try(ResultSet rs = pstmt.executeQuery()){

                if (rs.next()){
                    credenciales = new Credenciales();

                    credenciales.setIdUsuario(rs.getString(1));
                    credenciales.setCorreoPucp(rs.getString(2));
                    credenciales.setCodigoPucp(rs.getString(3));
                    credenciales.setContrasenaHasheada(rs.getString(4));
                }

            }

        }catch (SQLException e){
            e.getStackTrace();
        }

        return credenciales;
    }

    public void actualizarPassword(int idUsuario, String nuevaPassword){

        String sql = "update credenciales set contrasenaHasheada = sha2(?, 256) where idUsuario = ?";

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, nuevaPassword);
            pstmt.setInt(2, idUsuario);

            pstmt.executeUpdate();

        }catch (SQLException e){
            e.getStackTrace();
        }

    }

    public void enviarCorreo(String correoDestino, String asunto, String mensaje){

        String correoOrigen = "incidencias.pucp.2022@gmail.com";
        String password = "nndvyeldcezaukju";

        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.port", "587");
        properties.put("mail.smtp.user", correoOrigen);
        properties.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);
        MimeMessage mail = new MimeMessage(session);

        try {

            mail.setFrom(new InternetAddress(correoOrigen, "Incidencias PUCP"));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
            mail.setSubject(asunto, "UTF-8");
            mail.setText(mensaje, "UTF-8");
            mail.setSentDate(new Date());

            Transport transport = session.getTransport("smtp");
            transport.connect(correoOrigen, password);
            transport.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transport.close();

        }catch (Exception e){
            e.getStackTrace();
        }

    }

    public void guardarToken(int idUsuario, String token, String fechaExpiracion){

        String sql = "insert into tokens (idUsuario, token, fechaExpiracion) " +
                "values (?, ?, ?)";

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, idUsuario);
            pstmt.setString(2, token);
            pstmt.setString(3, fechaExpiracion);
            pstmt.executeUpdate();

        }catch (SQLException e){
            e.getStackTrace();
        }
    }

    public int validarToken(int idUsuario){

        String sql = "select fechaExpiracion from tokens where idUsuario = ?";

        try(Connection conn = this.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql)){

            psmt.setInt(1, idUsuario);

            try(ResultSet rs = psmt.executeQuery()){
                if (rs.next()){

                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date fechaExpiracion = df.parse(rs.getString(1));
                    Date fechaActual = new Date();

                    return fechaActual.compareTo(fechaExpiracion);

                }
                else {
                    return 0;
                }
            }
        }
        catch (SQLException | ParseException e){
            e.getStackTrace();
        }
        return 0;
    }

    public void guardarPassword(Usuarios usuario, String password){

        String sql = "insert into credenciales (idUsuario, correoPucp, codigoPucp, contrasenaHasheada) " +
                "values (?, ?, ?, sha2(?, 256))";

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, usuario.getIdUsuarios());
            pstmt.setString(2, usuario.getCorreoPucp());
            pstmt.setString(3, usuario.getCodigoPucp());
            pstmt.setString(4, password);

            pstmt.executeUpdate();

        }catch (SQLException e){
            e.getStackTrace();
        }

    }

    public Usuarios buscarPorToken(String token){
        String sql = "select u.idUsuario, u.nombres, u.apellidos, u.dni, u.celular, u.codigoPucp, \n" +
                "u.correoPucp, u.categoria, u.rol, u.fotoPerfil \n" +
                "from usuarios u \n" +
                "inner join tokens t on (t.idUsuario = u.idUsuario) \n" +
                "where token = ?";

        Usuarios usuarios = null;

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, token);

            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    usuarios = new Usuarios();

                    usuarios.setIdUsuarios(rs.getInt(1));
                    usuarios.setNombres(rs.getString(2));
                    usuarios.setApellidos(rs.getString(3));
                    usuarios.setDni(rs.getString(4));
                    usuarios.setCelular(rs.getString(5));
                    usuarios.setCodigoPucp(rs.getString(6));
                    usuarios.setCorreoPucp(rs.getString(7));
                    usuarios.setCategorias(rs.getString(8));
                    usuarios.setRol(rs.getString(9));
                    usuarios.setFotoPerfil(rs.getString(10));
                }
            }

        }
        catch (SQLException e){
            throw new RuntimeException();
        }

        return usuarios;
    }

    public void borrarToken(int idUsuario){
        String sql = "delete from tokens where idUsuario = ?";

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, idUsuario);
            pstmt.executeUpdate();

        }catch (SQLException e){
            e.getStackTrace();
        }
    }

    public Usuarios validarOlvidoContrasena(String correoPucp){

        String sql = "select u.* from usuarios u " +
                "inner join credenciales c on (u.idUsuario = c.idUsuario) " +
                "where c.correoPucp = ?";

        Usuarios usuarios = null;

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correoPucp);

            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    usuarios = new Usuarios();

                    usuarios.setIdUsuarios(rs.getInt(1));
                    usuarios.setNombres(rs.getString(2));
                    usuarios.setApellidos(rs.getString(3));
                    usuarios.setDni(rs.getString(4));
                    usuarios.setCelular(rs.getString(5));
                    usuarios.setCodigoPucp(rs.getString(6));
                    usuarios.setCorreoPucp(rs.getString(7));
                    usuarios.setCategorias(rs.getString(8));
                    usuarios.setRol(rs.getString(9));
                    usuarios.setFotoPerfil(rs.getString(10));
                }
            }

        }
        catch (SQLException e){
            throw new RuntimeException();
        }

        return usuarios;

    }

    public Credenciales validarUsuarioEnCredenciales(String correoPucp, String codigoPucp){

        String sql = "select * from credenciales where correoPucp = ? and codigoPucp = ?";
        Credenciales credenciales = null;

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, correoPucp);
            pstmt.setString(2, codigoPucp);

            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    credenciales = new Credenciales();

                    credenciales.setIdUsuario(rs.getString(1));
                    credenciales.setCorreoPucp(rs.getString(2));
                    credenciales.setCodigoPucp(rs.getString(3));
                    credenciales.setContrasenaHasheada(rs.getString(4));
                }
            }

        }catch (SQLException e){
            e.getStackTrace();
        }
        return credenciales;
    }


}
