package service;

import java.util.ArrayList;
import model.transaksi.Transaksi;

public class TransaksiService {
    private ArrayList<Transaksi> riwayatTransaksi;
    
    // Konstruktor
    public TransaksiService() {
        this.riwayatTransaksi = new ArrayList<>();
    }
    
    // Mendapatkan riwayat transaksi
    public ArrayList<Transaksi> getRiwayatTransaksi() {
        return riwayatTransaksi;
    }
    
    // Menambahkan transaksi baru
    public void tambahTransaksi(Transaksi transaksi) {
        riwayatTransaksi.add(transaksi);
    }
    
    // Menghitung total pendapatan dari semua transaksi
    public int hitungTotalPendapatan() {
        int total = 0;
        for (Transaksi transaksi : riwayatTransaksi) {
            total += transaksi.getTotalBayar();
        }
        return total;
    }
    
    // Mendapatkan jumlah transaksi
    public int getJumlahTransaksi() {
        return riwayatTransaksi.size();
    }
    
    // Mendapatkan rata-rata pendapatan per transaksi
    public double getRataRataPendapatan() {
        if (riwayatTransaksi.isEmpty()) {
            return 0;
        }
        return (double) hitungTotalPendapatan() / riwayatTransaksi.size();
    }
    
    // Mendapatkan transaksi dengan pembayaran tertinggi
    public Transaksi getTransaksiTertinggi() {
        if (riwayatTransaksi.isEmpty()) {
            return null;
        }
        
        Transaksi tertinggi = riwayatTransaksi.get(0);
        for (Transaksi transaksi : riwayatTransaksi) {
            if (transaksi.getTotalBayar() > tertinggi.getTotalBayar()) {
                tertinggi = transaksi;
            }
        }
        return tertinggi;
    }
    
    // Mendapatkan transaksi dengan pembayaran terendah
    public Transaksi getTransaksiTerendah() {
        if (riwayatTransaksi.isEmpty()) {
            return null;
        }
        
        Transaksi terendah = riwayatTransaksi.get(0);
        for (Transaksi transaksi : riwayatTransaksi) {
            if (transaksi.getTotalBayar() < terendah.getTotalBayar()) {
                terendah = transaksi;
            }
        }
        return terendah;
    }
    
    // Menghitung jumlah transaksi berdasarkan jenis kendaraan
    public int getJumlahTransaksiByJenisKendaraan(String jenisKendaraan) {
        int jumlah = 0;
        for (Transaksi transaksi : riwayatTransaksi) {
            if (transaksi.getJenisKendaraan().equals(jenisKendaraan)) {
                jumlah++;
            }
        }
        return jumlah;
    }
}