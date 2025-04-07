import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import model.parkiran.Parkiran;
import model.parkiran.Motor;
import model.parkiran.Mobil;
import model.parkiran.Truk;
import model.pengguna.Pengguna;
import model.pengguna.Regular;
import model.pengguna.VIP;
import model.transaksi.Transaksi;
import service.ParkiranService;
import service.TransaksiService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Membuat objek service untuk mengelola data parkiran dan transaksi
        ParkiranService parkiranService = new ParkiranService();
        TransaksiService transaksiService = new TransaksiService();
        
        // Inisialisasi data awal
        inisialisasiData(parkiranService);
        
        int pilihan;

        do {
            tampilkanHeader();
            tampilkanMenu();
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            switch (pilihan) {
                case 1:
                    tambahParkir(scanner, parkiranService);
                    break;
                case 2:
                    cariParkir(scanner, parkiranService);
                    break;
                case 3:
                    ubahDataParkir(scanner, parkiranService);
                    break;
                case 4:
                    bayarParkir(scanner, parkiranService, transaksiService);
                    break;
                case 5:
                    daftarParkir(parkiranService.getListParkir());
                    break;
                case 6:
                    parkiranService.sortByTarif();
                    System.out.println("\nDaftar Parkir Setelah Sorting (Tarif Terendah):");
                    daftarParkir(parkiranService.getListParkir());
                    break;
                case 7:
                    parkiranService.sortByNama();
                    System.out.println("\nDaftar Parkir Setelah Sorting (Berdasarkan Nama):");
                    daftarParkir(parkiranService.getListParkir());
                    break;
                case 8:
                    parkiranService.bubbleSortByPlat();
                    System.out.println("\nDaftar Parkir Setelah Bubble Sort (Berdasarkan Plat):");
                    daftarParkir(parkiranService.getListParkir());
                    break;
                case 9:
                    lihatRiwayatTransaksi(transaksiService);
                    break;
                case 10:
                    statistikParkir(parkiranService);
                    break;
                case 11:
                    System.out.println("Keluar dari program. Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi.");
            }
            
            if (pilihan != 11) {
                System.out.println("\nTekan Enter untuk melanjutkan...");
                scanner.nextLine();
            }
            
        } while (pilihan != 11);

        scanner.close();
    }

    private static void tampilkanHeader() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║                     APLIKASI PARKIR                    ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║  Waktu Sistem: " + formattedDateTime + "                  ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
    }
    
    private static void tampilkanMenu() {
        System.out.println("║                     Menu Utama                         ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║  1. Tambah Data Parkir                                 ║");
        System.out.println("║  2. Mencari Parkir (Berdasarkan Nama/Plat)             ║");
        System.out.println("║  3. Mengubah Data Parkir                               ║");
        System.out.println("║  4. Bayar Parkir                                       ║");
        System.out.println("║  5. Daftar Parkir                                      ║");
        System.out.println("║  6. Daftar Parkir (Sort Tarif Terendah)                ║");
        System.out.println("║  7. Daftar Parkir (Sort Berdasarkan Nama)              ║");
        System.out.println("║  8. Daftar Parkir (Bubble Sort Berdasarkan Plat)       ║");
        System.out.println("║  9. Lihat Riwayat Transaksi                            ║");
        System.out.println("║  10. Statistik Parkir                                  ║");
        System.out.println("║  11. Keluar                                            ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║               By Restu Mahardika (24111814034)         ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
    
    private static void inisialisasiData(ParkiranService parkiranService) {
        // Membuat pengguna
        Pengguna candra = new Regular("Candra", "081234567890");
        Pengguna pandi = new VIP("Pandi", "081234567891", "VIP-001");
        Pengguna agus = new Regular("Agus", "081234567892");
        Pengguna bagas = new VIP("Bagas", "081234567893", "VIP-002");
        Pengguna siti = new Regular("Siti", "081234567894");
        
        // Menambahkan data parkir
        parkiranService.tambahParkir(new Motor(candra, "AE 1234 A", 2));
        parkiranService.tambahParkir(new Mobil(pandi, "L 1345 B", 4));
        parkiranService.tambahParkir(new Motor(agus, "B 5432 C", 1));
        parkiranService.tambahParkir(new Mobil(bagas, "AD 0987 D", 2));
        parkiranService.tambahParkir(new Motor(siti, "AF 5678 E", 3));
    }
    
    private static void tambahParkir(Scanner scanner, ParkiranService parkiranService) {
        System.out.println("\n=== Tambah Data Parkir ===");
        
        System.out.print("Masukkan nama: ");
        String nama = scanner.nextLine();
        
        System.out.print("Masukkan nomor telepon: ");
        String noTelp = scanner.nextLine();
        
        System.out.print("Tipe pengguna (1: Regular, 2: VIP): ");
        int tipePengguna = scanner.nextInt();
        scanner.nextLine();
        
        Pengguna pengguna;
        if (tipePengguna == 2) {
            System.out.print("Masukkan ID VIP: ");
            String idVip = scanner.nextLine();
            pengguna = new VIP(nama, noTelp, idVip);
        } else {
            pengguna = new Regular(nama, noTelp);
        }
        
        System.out.print("Jenis kendaraan (1: Motor, 2: Mobil, 3: Truk): ");
        int jenisKendaraan = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Masukkan plat nomor: ");
        String plat = scanner.nextLine();
        
        System.out.print("Masukkan durasi parkir (jam): ");
        int durasi = scanner.nextInt();
        scanner.nextLine();
        
        Parkiran parkiran;
        switch (jenisKendaraan) {
            case 1:
                parkiran = new Motor(pengguna, plat, durasi);
                break;
            case 2:
                parkiran = new Mobil(pengguna, plat, durasi);
                break;
            case 3:
                parkiran = new Truk(pengguna, plat, durasi);
                break;
            default:
                System.out.println("Jenis kendaraan tidak valid. Menggunakan motor sebagai default.");
                parkiran = new Motor(pengguna, plat, durasi);
        }
        
        parkiranService.tambahParkir(parkiran);
        System.out.println("Data parkir berhasil ditambahkan:");
        System.out.println(parkiran);
    }
    
    private static void cariParkir(Scanner scanner, ParkiranService parkiranService) {
        System.out.println("\n=== Cari Data Parkir ===");
        System.out.println("Cari berdasarkan: ");
        System.out.println("1. Nama");
        System.out.println("2. Plat Nomor");
        System.out.print("Pilih opsi: ");
        int opsi = scanner.nextInt();
        scanner.nextLine();
        
        if (opsi == 1) {
            System.out.print("Masukkan nama yang ingin dicari: ");
            String namaDicari = scanner.nextLine();
            Parkiran hasil = parkiranService.cariByNama(namaDicari);
            
            if (hasil != null) {
                System.out.println("Data ditemukan:");
                System.out.println(hasil);
            } else {
                System.out.println("Nama \"" + namaDicari + "\" tidak ditemukan.");
            }
        } else if (opsi == 2) {
            System.out.print("Masukkan plat nomor yang ingin dicari: ");
            String platDicari = scanner.nextLine();
            Parkiran hasil = parkiranService.cariByPlat(platDicari);
            
            if (hasil != null) {
                System.out.println("Data ditemukan:");
                System.out.println(hasil);
            } else {
                System.out.println("Plat \"" + platDicari + "\" tidak ditemukan.");
            }
        } else {
            System.out.println("Opsi tidak valid.");
        }
    }
    
    private static void ubahDataParkir(Scanner scanner, ParkiranService parkiranService) {
        System.out.println("\n=== Ubah Data Parkir ===");
        System.out.print("Masukkan nama pemakir yang ingin diubah: ");
        String namaUbah = scanner.nextLine();
        
        Parkiran parkiranEdit = parkiranService.cariByNama(namaUbah);
        if (parkiranEdit != null) {
            System.out.println("Data ditemukan: " + parkiranEdit);
            
            System.out.print("Masukkan nama baru: ");
            String namaBaru = scanner.nextLine();
            
            System.out.print("Masukkan nomor telepon baru: ");
            String noTelpBaru = scanner.nextLine();
            
            System.out.print("Ubah jenis kendaraan? (y/n): ");
            String ubahJenis = scanner.nextLine();
            
            if (ubahJenis.equalsIgnoreCase("y")) {
                System.out.print("Jenis kendaraan baru (1: Motor, 2: Mobil, 3: Truk): ");
                int jenisKendaraanBaru = scanner.nextInt();
                scanner.nextLine();
                
                System.out.print("Masukkan plat nomor baru: ");
                String platBaru = scanner.nextLine();
                
                System.out.print("Masukkan durasi parkir baru (jam): ");
                int durasiBaru = scanner.nextInt();
                scanner.nextLine();
                
                Pengguna pengguna = parkiranEdit.getPengguna();
                pengguna.setNama(namaBaru);
                pengguna.setNoTelp(noTelpBaru);
                
                Parkiran parkiranBaru;
                switch (jenisKendaraanBaru) {
                    case 1:
                        parkiranBaru = new Motor(pengguna, platBaru, durasiBaru);
                        break;
                    case 2:
                        parkiranBaru = new Mobil(pengguna, platBaru, durasiBaru);
                        break;
                    case 3:
                        parkiranBaru = new Truk(pengguna, platBaru, durasiBaru);
                        break;
                    default:
                        System.out.println("Jenis kendaraan tidak valid. Menggunakan motor sebagai default.");
                        parkiranBaru = new Motor(pengguna, platBaru, durasiBaru);
                }
                
                parkiranService.updateParkir(parkiranEdit, parkiranBaru);
                System.out.println("Data parkir berhasil diperbarui: " + parkiranBaru);
            } else {
                parkiranEdit.getPengguna().setNama(namaBaru);
                parkiranEdit.getPengguna().setNoTelp(noTelpBaru);
                System.out.println("Data parkir berhasil diperbarui: " + parkiranEdit);
            }
        } else {
            System.out.println("Nama \"" + namaUbah + "\" tidak ditemukan.");
        }
    }
    
    private static void bayarParkir(Scanner scanner, ParkiranService parkiranService, TransaksiService transaksiService) {
        System.out.println("\n=== Bayar Parkir ===");
        System.out.print("Masukkan nama pemilik kendaraan yang ingin membayar: ");
        String namaBayar = scanner.nextLine();
        
        Parkiran parkirDibayar = parkiranService.cariByNama(namaBayar);
        if (parkirDibayar != null) {
            Transaksi transaksi = parkirDibayar.bayarParkir();
            transaksiService.tambahTransaksi(transaksi);
            parkiranService.hapusParkir(parkirDibayar);
            System.out.println("Pembayaran berhasil. Data parkir telah dihapus.");
        } else {
            System.out.println("Nama \"" + namaBayar + "\" tidak ditemukan.");
        }
    }
    
    private static void daftarParkir(ArrayList<Parkiran> listParkir) {
        if (listParkir.isEmpty()) {
            System.out.println("Tidak ada data parkir.");
            return;
        }
        
        System.out.println("\n=== Daftar Parkir ===");
        System.out.println("Jumlah Data: " + listParkir.size());
        System.out.println("-------------------------------------------------------");
        
        for (int i = 0; i < listParkir.size(); i++) {
            System.out.println((i+1) + ". " + listParkir.get(i));
        }
    }
    
    private static void lihatRiwayatTransaksi(TransaksiService transaksiService) {
        ArrayList<Transaksi> riwayat = transaksiService.getRiwayatTransaksi();
        
        if (riwayat.isEmpty()) {
            System.out.println("Belum ada riwayat transaksi.");
            return;
        }
        
        System.out.println("\n=== Riwayat Transaksi ===");
        System.out.println("Jumlah Transaksi: " + riwayat.size());
        System.out.println("-------------------------------------------------------");
        
        for (int i = 0; i < riwayat.size(); i++) {
            System.out.println("Transaksi #" + (i+1));
            riwayat.get(i).tampilkanDetailTransaksi();
            System.out.println("-------------------------------------------------------");
        }
        
        System.out.println("Total Pendapatan: Rp" + transaksiService.hitungTotalPendapatan());
    }
    
    private static void statistikParkir(ParkiranService parkiranService) {
        System.out.println("\n=== Statistik Parkir ===");
        System.out.println("Jumlah Kendaraan: " + parkiranService.getJumlahKendaraan());
        System.out.println("Jumlah Motor: " + parkiranService.getJumlahByJenis("Motor"));
        System.out.println("Jumlah Mobil: " + parkiranService.getJumlahByJenis("Mobil"));
        System.out.println("Jumlah Truk: " + parkiranService.getJumlahByJenis("Truk"));
        System.out.println("Rata-rata Durasi Parkir: " + parkiranService.getRataRataDurasi() + " jam");
        System.out.println("Perkiraan Pendapatan: Rp" + parkiranService.getPerkiraanPendapatan());
    }
}