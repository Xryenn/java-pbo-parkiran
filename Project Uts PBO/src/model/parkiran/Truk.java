package model.parkiran;

import model.pengguna.Pengguna;

public class Truk extends Parkiran {
    private static final int TARIF_DASAR = 10000;
    
    public Truk(Pengguna pengguna, String plat, int waktu) {
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
        return "Truk";
    }
}