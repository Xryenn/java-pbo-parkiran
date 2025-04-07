package service;

import java.util.ArrayList;
import model.parkiran.Parkiran;
import util.SortingUtil;
import util.SearchingUtil;

public class ParkiranService {
    private ArrayList<Parkiran> listParkir;
    
    // Konstruktor
    public ParkiranService() {
        this.listParkir = new ArrayList<>();
    }
    
    // Getter untuk list parkir
    public ArrayList<Parkiran> getListParkir() {
        return listParkir;
    }
    
    // Menambahkan data parkir baru
    public void tambahParkir(Parkiran parkiran) {
        listParkir.add(parkiran);
    }
    
    // Menghapus data parkir
    public void hapusParkir(Parkiran parkiran) {
        listParkir.remove(parkiran);
    }
    
    // Memperbarui data parkir
    public void updateParkir(Parkiran lama, Parkiran baru) {
        int index = listParkir.indexOf(lama);
        if (index != -1) {
            listParkir.set(index, baru);
        }
    }
    
    // Mencari parkir berdasarkan nama
    public Parkiran cariByNama(String nama) {
        return SearchingUtil.cariByNama(listParkir, nama);
    }
    
    // Mencari parkir berdasarkan plat nomor
    public Parkiran cariByPlat(String plat) {
        return SearchingUtil.cariByPlat(listParkir, plat);
    }
    
    // Mengurutkan berdasarkan tarif (ascending)
    public void sortByTarif() {
        SortingUtil.sortByTarif(listParkir);
    }
    
    // Mengurutkan berdasarkan nama
    public void sortByNama() {
        SortingUtil.sortByNama(listParkir);
    }
    
    // Mengurutkan berdasarkan plat nomor menggunakan bubble sort
    public void bubbleSortByPlat() {
        SortingUtil.bubbleSortByPlat(listParkir);
    }
    
    // Mendapatkan jumlah kendaraan
    public int getJumlahKendaraan() {
        return listParkir.size();
    }
    
    // Mendapatkan jumlah kendaraan berdasarkan jenis
    public int getJumlahByJenis(String jenis) {
        int jumlah = 0;
        for (Parkiran parkir : listParkir) {
            if (parkir.getJenisKendaraan().equals(jenis)) {
                jumlah++;
            }
        }
        return jumlah;
    }
    
    // Mendapatkan rata-rata durasi parkir
    public double getRataRataDurasi() {
        if (listParkir.isEmpty()) {
            return 0;
        }
        
        int totalDurasi = 0;
        for (Parkiran parkir : listParkir) {
            totalDurasi += parkir.getWaktu();
        }
        
        return (double) totalDurasi / listParkir.size();
    }
    
    // Mendapatkan perkiraan pendapatan
    public int getPerkiraanPendapatan() {
        int total = 0;
        for (Parkiran parkir : listParkir) {
            total += parkir.hitungTarif();
        }
        return total;
    }
}