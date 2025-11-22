package Models;

public class termModel {
    private String hocKy;
    private String namBatDau;
    private String namKetThuc;
    private String ngayBatDau;
    private boolean trangthai;
    
    // Constructor
    public termModel(String hocKy, String namBatDau, String namKetThuc, String ngayBatDau, boolean trangthai) {
        this.hocKy = hocKy;
        this.namBatDau = namBatDau;
        this.namKetThuc = namKetThuc;
        this.ngayBatDau = ngayBatDau;
        this.trangthai = trangthai;
    }

    // Getters and setters (có thể tự định nghĩa hoặc sinh ra tự động)

    public String getHocKy() {
        return hocKy;
    }

    public void setHocKy(String hocKy) {
        this.hocKy = hocKy;
    }

    public String getNamBatDau() {
        return namBatDau;
    }

    public void setNamBatDau(String namBatDau) {
        this.namBatDau = namBatDau;
    }

    public String getNamKetThuc() {
        return namKetThuc;
    }

    public void setNamKetThuc(String namKetThuc) {
        this.namKetThuc = namKetThuc;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }
    
    public boolean getTrangthai() {
    	return trangthai;
    }
    
    public void setTrangthai(boolean trangthai) {
    	this.trangthai = trangthai;
    }

    // toString method
    @Override
    public String toString() {
        return "termModel{" +
                "hocKy=" + hocKy +
                ", namBatDau=" + namBatDau +
                ", namKetThuc=" + namKetThuc +
                ", ngayBatDau='" + ngayBatDau + '\'' +
                ", trangthai=" + trangthai +
                '}';
    }
}
