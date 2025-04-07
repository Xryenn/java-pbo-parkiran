package model.parkiran;

import model.pengguna.Pengguna;

public class Motor extends Parkiran {
    private static final int TARIF_DASAR = 2000;
    
    public Motor(Pengguna pengguna, String plat, int waktu) {
        super(pengguna, plat, waktu);
    }
    
    @Override
    protected int tentukanTarif() {
        // Pengguna VIP mendapat diskon 10%
        if (getPengguna().getJenisPengguna().equals("VIP")) {
            return (int)(TARIF_DASAR * 0.9);
        }
        return TARIF_DASAR;
    }
    
    @Override
    public String getJenisKendaraan() {
        return "Motor";
    }
}