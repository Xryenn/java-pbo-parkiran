package model.pengguna;

public class VIP extends Pengguna {
    private String idVip;
    
    public VIP(String nama, String noTelp, String idVip) {
        super(nama, noTelp);
        this.idVip = idVip;
    }
    
    public String getIdVip() {
        return idVip;
    }
    
    public void setIdVip(String idVip) {
        this.idVip = idVip;
    }
    
    @Override
    public String getJenisPengguna() {
        return "VIP";
    }
    
    @Override
    public String toString() {
        return super.toString() + ", ID VIP: " + idVip;
    }
}