package util;

import java.util.ArrayList;
import model.parkiran.Parkiran;

public class SearchingUtil {
    
    // Mencari parkir berdasarkan nama (Linear Search)
    public static Parkiran cariByNama(ArrayList<Parkiran> listParkir, String nama) {
        for (Parkiran parkir : listParkir) {
            if (parkir.getPengguna().getNama().equalsIgnoreCase(nama)) {
                return parkir;
            }
        }
        return null;
    }
    
    // Mencari parkir berdasarkan plat nomor (Linear Search)
    public static Parkiran cariByPlat(ArrayList<Parkiran> listParkir, String plat) {
        for (Parkiran parkir : listParkir) {
            if (parkir.getPlat().equalsIgnoreCase(plat)) {
                return parkir;
            }
        }
        return null;
    }
    
    // Binary Search untuk mencari parkir berdasarkan plat nomor 
    // (harus diurutkan dulu sebelum menggunakan binary search)
    public static Parkiran binarySearchByPlat(ArrayList<Parkiran> listParkir, String plat) {
        // Pastikan list sudah terurut berdasarkan plat
        SortingUtil.bubbleSortByPlat(listParkir);
        
        int left = 0;
        int right = listParkir.size() - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            String platMid = listParkir.get(mid).getPlat();
            int compareResult = platMid.compareToIgnoreCase(plat);
            
            // Jika plat ditemukan di tengah
            if (compareResult == 0) {
                return listParkir.get(mid);
            }
            
            // Jika plat ada di bagian kanan
            if (compareResult < 0) {
                left = mid + 1;
            }
            // Jika plat ada di bagian kiri
            else {
                right = mid - 1;
            }
        }
        
        // Plat tidak ditemukan
        return null;
    }
    
    // Mencari parkir dengan durasi terlama
    public static Parkiran cariDurasiTerlama(ArrayList<Parkiran> listParkir) {
        if (listParkir.isEmpty()) {
            return null;
        }
        
        Parkiran terlama = listParkir.get(0);
        for (Parkiran parkir : listParkir) {
            if (parkir.getWaktu() > terlama.getWaktu()) {
                terlama = parkir;
            }
        }
        
        return terlama;
    }
    
    // Mencari parkir dengan tarif tertinggi
    public static Parkiran cariTarifTertinggi(ArrayList<Parkiran> listParkir) {
        if (listParkir.isEmpty()) {
            return null;
        }
        
        Parkiran tertinggi = listParkir.get(0);
        for (Parkiran parkir : listParkir) {
            if (parkir.hitungTarif() > tertinggi.hitungTarif()) {
                tertinggi = parkir;
            }
        }
        
        return tertinggi;
    }
    
    // Mencari semua parkir berdasarkan jenis kendaraan
    public static ArrayList<Parkiran> cariByJenisKendaraan(ArrayList<Parkiran> listParkir, String jenisKendaraan) {
        ArrayList<Parkiran> hasil = new ArrayList<>();
        
        for (Parkiran parkir : listParkir) {
            if (parkir.getJenisKendaraan().equalsIgnoreCase(jenisKendaraan)) {
                hasil.add(parkir);
            }
        }
        
        return hasil;
    }
}