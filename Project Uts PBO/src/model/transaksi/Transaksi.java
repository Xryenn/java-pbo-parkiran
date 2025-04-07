package model.transaksi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.pengguna.Pengguna;

public class Transaksi {
    private Pengguna pengguna;
    private String jenisKendaraan;
    private String platNomor;
    private int lamaParkir;
    private int tarifPerJam;
    private int totalBayar;
    private LocalDateTime waktuMasuk;
    private LocalDateTime waktuKeluar;
    
    // Konstruktor
    public Transaksi(Pengguna pengguna, String jenisKendaraan, String platNomor, 
                     int lamaParkir, int tarifPerJam, int totalBayar,
                     LocalDateTime waktuMasuk, LocalDateTime waktuKeluar) {
        this.pengguna = pengguna;
        this.jenisKendaraan = jenisKendaraan;
        this.platNomor = platNomor;
        this.lamaParkir = lamaParkir;
        this.tarifPerJam = tarifPerJam;
        this.totalBayar = totalBayar;
        this.waktuMasuk = waktuMasuk;
        this.waktuKeluar = waktuKeluar;
    }
    
    // Getter methods
    public Pengguna getPengguna() {
        return pengguna;
    }
    
    public String getJenisKendaraan() {
        return jenisKendaraan;
    }
    
    public String getPlatNomor() {
        return platNomor;
    }
    
    public int getLamaParkir() {
        return lamaParkir;
    }
    
    public int getTarifPerJam() {
        return tarifPerJam;
    }
    
    public int getTotalBayar() {
        return totalBayar;
    }
    
    public LocalDateTime getWaktuMasuk() {
        return waktuMasuk;
    }
    
    public LocalDateTime getWaktuKeluar() {
        return waktuKeluar;
    }
    
    // Format waktu
    private String formatWaktu(LocalDateTime waktu) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return waktu.format(formatter);
    }
    
    // Menampilkan detail transaksi
    public void tampilkanDetailTransaksi() {
        System.out.println("Nama Pengguna   : " + pengguna.getNama());
        System.out.println("Jenis Pengguna  : " + pengguna.getJenisPengguna());
        System.out.println("No. Telepon     : " + pengguna.getNoTelp());
        System.out.println("Jenis Kendaraan : " + jenisKendaraan);
        System.out.println("Plat Nomor      : " + platNomor);
        System.out.println("Waktu Masuk     : " + formatWaktu(waktuMasuk));
        System.out.println("Waktu Keluar    : " + formatWaktu(waktuKeluar));
        System.out.println("Lama Parkir     : " + lamaParkir + " jam");
        System.out.println("Tarif Per Jam   : Rp" + tarifPerJam);
        System.out.println("Total Bayar     : Rp" + totalBayar);
    }
    
    @Override
    public String toString() {
        return "Transaksi{" +
                "pengguna=" + pengguna.getNama() +
                ", jenisKendaraan='" + jenisKendaraan + '\'' +
                ", platNomor='" + platNomor + '\'' +
                ", lamaParkir=" + lamaParkir +
                ", tarifPerJam=" + tarifPerJam +
                ", totalBayar=" + totalBayar +
                ", waktuMasuk=" + formatWaktu(waktuMasuk) +
                ", waktuKeluar=" + formatWaktu(waktuKeluar) +
                '}';
    }
}