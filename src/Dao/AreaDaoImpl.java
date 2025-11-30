package Dao;

import database.ConexionBD;
import entity.Area;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Repository.IArea;

public class AreaDaoImpl implements IArea {

    @Override
    public List<Area> listar() {
        List<Area> lista = new ArrayList<>();
        try (Connection con = ConexionBD.getConnection();
                CallableStatement cs = con.prepareCall("{call sp_listarAreas()}"); 
                ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                Area a = new Area();
                a.setIdArea(rs.getInt("idArea"));
                a.setNombreArea(rs.getString("nombreArea"));
                a.setDescripcion(rs.getString("descripcion"));
                lista.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;

    }

}
