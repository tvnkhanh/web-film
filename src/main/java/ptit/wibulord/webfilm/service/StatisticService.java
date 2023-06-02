package ptit.wibulord.webfilm.service;

import ptit.wibulord.webfilm.dto.Databasehelper;
import ptit.wibulord.webfilm.model.RevenuePremium;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatisticService {
//    public List<TopView> TopFilmList() {
//        Connection con = null;
//        int sum = 0;
//        Statement statement = null;
//        List<TopView> resutl = new ArrayList<>();
//        try {
//            con = Databasehelper.openConnection();
//            statement = con.createStatement();
//            String sql = "SELECT TOP(5) P.ID_PHIM, P.TENPHIM, TK.TONGLUOTXEM FROM PHIM P INNER JOIN" +
//                    "(SELECT ID_PHIM, TONGLUOTXEM = SUM(LUOTXEM) FROM TAP GROUP BY ID_PHIM) TK" +
//                    "ON P.ID_PHIM = TK.ID_PHIM" +
//                    "ORDER BY TONGLUOTXEM DESC";
//            ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                TopView temp = new TopView();
//                temp.setId(resultSet.getInt(1));
//                temp.setName(resultSet.getString(2));
//                temp.setView(resultSet.getLong(3));
//                resutl.add(temp);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return resutl;
//    }

    public boolean isLeap(int year){
        boolean isLeap = false;
        if(year % 4 == 0)//chia hết cho 4 là năm nhuận
        {
            if( year % 100 == 0)
            //nếu vừa chia hết cho 4 mà vừa chia hết cho 100 thì không phải năm nhuận
            {
                if ( year % 400 == 0)//chia hết cho 400 là năm nhuận
                    isLeap = true;
                else
                    isLeap = false;//không chia hết cho 400 thì không phải năm nhuận
            }
            else//chia hết cho 4 nhưng không chia hết cho 100 là năm nhuận
                isLeap = true;
        }
        else {
            isLeap = false;
        }
        return isLeap;
    }

    public int totalRevenue(String fromDate, String toDate) {
        Connection con = null;
        int sum = 0;
        Statement statement = null;
        List<RevenuePremium> resutl = new ArrayList<>();
        try {
            con = Databasehelper.openConnection();
            statement = con.createStatement();
            String sql = "SELECT SUM(GIA)\n" +
                    "FROM (SELECT * FROM CT_MUA WHERE NGAYMUA BETWEEN '"+fromDate+"' AND '"+toDate+"') CT";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                sum = resultSet.getInt(1);;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }
    public int totalBuy(String fromDate, String toDate) {
        Connection con = null;
        int count = 0;
        Statement statement = null;
        List<RevenuePremium> resutl = new ArrayList<>();
        try {
            con = Databasehelper.openConnection();
            statement = con.createStatement();
            String sql = "SELECT SUM(SODIEM)\n" +
                    "FROM (SELECT * FROM CT_MUA WHERE NGAYMUA BETWEEN '"+fromDate+"' AND '"+toDate+"') CT";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                count = resultSet.getInt(1);;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    public int totalRegister(String fromDate, String toDate) {
        Connection con = null;
        int count = 0;
        Statement statement = null;
        List<RevenuePremium> resutl = new ArrayList<>();
        try {
            con = Databasehelper.openConnection();
            statement = con.createStatement();
            String sql = "SELECT COUNT(TEN_TK)\n" +
                    "FROM (SELECT * FROM TAIKHOAN WHERE NGAYTAO BETWEEN '"+fromDate+"' AND '"+toDate+"') CT";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                count = resultSet.getInt(1);;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    public int totalUser(String toDate) {
        Connection con = null;
        int count = 0;
        Statement statement = null;
        List<RevenuePremium> resutl = new ArrayList<>();
        try {
            con = Databasehelper.openConnection();
            statement = con.createStatement();
            String sql = "SELECT COUNT(TEN_TK)\n" +
                    "FROM (SELECT * FROM TAIKHOAN WHERE DATEDIFF(DAY,NGAYTAO, '"+toDate+"')>=0) CT";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                count = resultSet.getInt(1);;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    public List<RevenuePremium> StatisticRevenuePremium(String fromDate, String toDate) {
        Connection con = null;
        int sum = 0;
        Statement statement = null;
        List<RevenuePremium> resutl = new ArrayList<>();
        try {
            con = Databasehelper.openConnection();
            statement = con.createStatement();
            String sql = "SELECT GHV.SODIEM, ISNULL(DOANHTHU,0) FROM GOIDIEM GHV LEFT JOIN "+
                    "(SELECT ID_GOI, DOANHTHU = SUM(GIA) FROM (SELECT * FROM CT_MUA WHERE NGAYMUA BETWEEN '"+fromDate+"' AND '"+toDate+"') CT GROUP BY ID_GOI ) CTM "+
            "ON CTM.ID_GOI = GHV.ID_GOI "+
                    "ORDER BY DOANHTHU DESC";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                RevenuePremium temp = new RevenuePremium();
                temp.setQuantity(resultSet.getInt(1));
                temp.setRevenue(resultSet.getLong(2));
                resutl.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(resutl.size()>5){
            int total = 0;
            for(int i = 4;i < resutl.size();i++){
                total += resutl.get(i).getQuantity();
            }
            while(resutl.size()>=5){
                resutl.remove(resutl.size()-1);
            }
            resutl.add(new RevenuePremium(0, total));
        }
        return resutl;
    }
}
