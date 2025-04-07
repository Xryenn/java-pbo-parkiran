package model.pengguna;

// Kelas abstrak Pengguna
public abstract class Pengguna {
    private String nama;
    private String noTelp;
    
    public Pengguna(String nama, String noTelp) {
        this.nama = nama;
        this.noTelp = noTelp;
    }
    
    public String getNama() {
        return nama;
    }
    
    public String getNoTelp() {
        return noTelp;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }
    
    // Metode abstrak untuk mendapatkan jenis pengguna
    public abstract String getJenisPengguna();
    
    @Override
    public String toString() {
        return "Nama: " + nama + ", No. Telp: " + noTelp + ", Jenis: " + getJenisPengguna();
    }
}