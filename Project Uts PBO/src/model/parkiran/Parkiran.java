package model.parkiran;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.pengguna.Pengguna;
import model.transaksi.Transaksi;

// Kelas abstrak Parkiran sebagai parent class
public abstract class Parkiran {
    private Pengguna pengguna;
    private String plat;
    private int waktu;
    private LocalDateTime waktuMasuk;
    protected int tarifPerJam;
    
    // Inner class untuk informasi lokasi
    public static class LokasiParkir {
        private String area;
        private String lantai;
        private String blok;
        private int nomorSlot;
        
        public LokasiParkir(String area, String lantai, String blok, int nomorSlot) {
            this.area = area;
            this.lantai = lantai;
            this.blok = blok;
            this.nomorSlot = nomorSlot;
        }
        
        public String getArea() {
            return area;
        }
        
        public String getLantai() {
            return lantai;
        }
        
        public String getBlok() {
            return blok;
        }
        
        public int getNomorSlot() {
            return nomorSlot;
        }
        
        @Override
        public String toString() {
            return "Area " + area + ", Lantai " + lantai + ", Blok " + blok + ", Slot " + nomorSlot;
        }
    }
    
    // Konstruktor
    public Parkiran(Pengguna pengguna, String plat, int waktu) {
        this.pengguna = pengguna;
        this.plat = plat;
        this.waktu = waktu;
        this.waktuMasuk = LocalDateTime.now();
        this.tarifPerJam = tentukanTarif();
    }
    
    // Metode abstrak untuk menentukan tarif berdasarkan jenis kendaraan
    protected abstract int tentukanTarif();
    
    // Metode abstrak untuk mendapatkan jenis kendaraan
    public abstract String getJenisKendaraan();
    
    // Getter dan Setter
    public Pengguna getPengguna() {
        return pengguna;
    }
    
    public String getPlat() {
        return plat;
    }
    
    public int getWaktu() {
        return waktu;
    }
    
    public int getTarifPerJam() {
        return tarifPerJam;
    }
    
    public LocalDateTime getWaktuMasuk() {
        return waktuMasuk;
    }
    
    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
    
    public void setPlat(String plat) {
        this.plat = plat;
    }
    
    public void setWaktu(int waktu) {
        this.waktu = waktu;
    }
    
    // Metode untuk menghitung total tarif
    public int hitungTarif() {
        return getWaktu() * getTarifPerJam();
    }
    
    // Metode untuk mendapatkan format waktu masuk
    public String getWaktuMasukFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return waktuMasuk.format(formatter);
    }
    
    // Metode untuk membayar parkir
    public Transaksi bayarParkir() {
        LocalDateTime waktuKeluar = LocalDateTime.now();
        
        // Membuat karcis pembayaran
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║                    KARCIS PEMBAYARAN                   ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║  NAMA            : " + getPengguna().getNama());
        System.out.println("║  NO TELEPON      : " + getPengguna().getNoTelp());
        System.out.println("║  JENIS PENGGUNA  : " + getPengguna().getJenisPengguna());
        System.out.println("║  KENDARAAN       : " + getJenisKendaraan());
        System.out.println("║  PLAT KENDARAAN  : " + getPlat());
        System.out.println("║  WAKTU MASUK     : " + getWaktuMasukFormatted());
        System.out.println("║  WAKTU KELUAR    : " + waktuKeluar.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        System.out.println("║  LAMA PARKIR     : " + getWaktu() + " Jam");
        System.out.println("║  TARIF PER JAM   : Rp" + getTarifPerJam());
        System.out.println("║  TOTAL BAYAR     : Rp" + hitungTarif());
        System.out.println("╚════════════════════════════════════════════════════════╝");
        
        // Membuat objek transaksi
        return new Transaksi(getPengguna(), getJenisKendaraan(), getPlat(), getWaktu(), getTarifPerJam(), hitungTarif(),  waktuMasuk, waktuKeluar);
    }
    
    @Override
    public String toString() {
        return "Nama: " + getPengguna().getNama() + 
               ", No Telp: " + getPengguna().getNoTelp() + 
               ", Jenis: " + getPengguna().getJenisPengguna() + 
               ", Kendaraan: " + getJenisKendaraan() + 
               ", Plat: " + getPlat() + 
               ", Waktu: " + getWaktu() + " jam" + 
               ", Tarif: Rp" + hitungTarif();
    }
}