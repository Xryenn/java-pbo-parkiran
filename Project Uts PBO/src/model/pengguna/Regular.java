package model.pengguna;

public class Regular extends Pengguna {
    
    public Regular(String nama, String noTelp) {
        super(nama, noTelp);
    }
    
    @Override
    public String getJenisPengguna() {
        return "Regular";
    }
}