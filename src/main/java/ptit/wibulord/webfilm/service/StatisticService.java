package ptit.wibulord.webfilm.service;

import ptit.wibulord.webfilm.dto.Databasehelper;
import ptit.wibulord.webfilm.model.RevenuePremium;
import ptit.wibulord.webfilm.model.TopView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatisticService {
    public List<TopView> TopFilmList() {
        Connection con = null;
        int sum = 0;
        Statement statement = null;
        List<TopView> resutl = new ArrayList<>();
        try {
            con = Databasehelper.openConnection();
            statement = con.createStatement();
            String sql = "SELECT TOP(5) P.ID_PHIM, P.TENPHIM, TK.TONGLUOTXEM FROM PHIM P INNER JOIN" +
                    "(SELECT ID_PHIM, TONGLUOTXEM = SUM(LUOTXEM) FROM TAP GROUP BY ID_PHIM) TK" +
                    "ON P.ID_PHIM = TK.ID_PHIM" +
                    "ORDER BY TONGLUOTXEM DESC";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                TopView temp = new TopView();
                temp.setId(resultSet.getInt(1));
                temp.setName(resultSet.getString(2));
                temp.setView(resultSet.getLong(3));
                resutl.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resutl;
    }

    public List<RevenuePremium> StatisticRevenuePremium(String fromDate, String toDate) {
        Connection con = null;
        int sum = 0;
        Statement statement = null;
        List<RevenuePremium> resutl = new ArrayList<>();
        try {
            con = Databasehelper.openConnection();
            statement = con.createStatement();
            String sql = "SELECT GHV.ID_GOI, ISNULL(DOANHTHU,0) FROM GOIHOIVIEN GHV LEFT JOIN"+
                    "(SELECT ID_GOI, DOANHTHU = SUM(GIA)"+
                            "FROM (SELECT * FROM CT_MUA WHERE NGAYMUA BETWEEN '"+fromDate+"' AND '"+toDate+"') CT GROUP BY ID_GOI ) CTM"+
            "ON CTM.ID_GOI = GHV.ID_GOI "+
                    "ORDER BY DOANHTHU DESC";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                RevenuePremium temp = new RevenuePremium();
                temp.setId(resultSet.getInt(1));
                temp.setRevenue(resultSet.getLong(2));
                resutl.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resutl;
    }
}
