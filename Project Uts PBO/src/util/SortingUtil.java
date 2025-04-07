package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import model.parkiran.Parkiran;

public class SortingUtil {
    
    // Mengurutkan berdasarkan tarif (ascending)
    public static void sortByTarif(ArrayList<Parkiran> listParkir) {
        Collections.sort(listParkir, new Comparator<Parkiran>() {
            @Override
            public int compare(Parkiran p1, Parkiran p2) {
                return Integer.compare(p1.hitungTarif(), p2.hitungTarif());
            }
        });
    }
    
    // Mengurutkan berdasarkan nama (ascending)
    public static void sortByNama(ArrayList<Parkiran> listParkir) {
        Collections.sort(listParkir, new Comparator<Parkiran>() {
            @Override
            public int compare(Parkiran p1, Parkiran p2) {
                return p1.getPengguna().getNama().compareToIgnoreCase(p2.getPengguna().getNama());
            }
        });
    }
    
    // Implementasi Bubble Sort untuk mengurutkan berdasarkan plat nomor
    public static void bubbleSortByPlat(ArrayList<Parkiran> listParkir) {
        int n = listParkir.size();
        boolean swapped;
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            
            for (int j = 0; j < n - i - 1; j++) {
                if (listParkir.get(j).getPlat().compareToIgnoreCase(listParkir.get(j+1).getPlat()) > 0) {
                    // Swap
                    Parkiran temp = listParkir.get(j);
                    listParkir.set(j, listParkir.get(j+1));
                    listParkir.set(j+1, temp);
                    swapped = true;
                }
            }
            
            // Jika tidak ada swap pada iterasi ini, array sudah terurut
            if (!swapped) {
                break;
            }
        }
    }
    
    // Implementasi Selection Sort (tambahan)
    public static void selectionSortByDurasi(ArrayList<Parkiran> listParkir) {
        int n = listParkir.size();
        
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            
            for (int j = i + 1; j < n; j++) {
                if (listParkir.get(j).getWaktu() < listParkir.get(min_idx).getWaktu()) {
                    min_idx = j;
                }
            }
            
            // Swap
            Parkiran temp = listParkir.get(min_idx);
            listParkir.set(min_idx, listParkir.get(i));
            listParkir.set(i, temp);
        }
    }
    
    // Implementasi Insertion Sort (tambahan)
    public static void insertionSortByTarifPerJam(ArrayList<Parkiran> listParkir) {
        int n = listParkir.size();
        
        for (int i = 1; i < n; i++) {
            Parkiran key = listParkir.get(i);
            int j = i - 1;
            
            while (j >= 0 && listParkir.get(j).getTarifPerJam() > key.getTarifPerJam()) {
                listParkir.set(j + 1, listParkir.get(j));
                j = j - 1;
            }
            
            listParkir.set(j + 1, key);
        }
    }
}