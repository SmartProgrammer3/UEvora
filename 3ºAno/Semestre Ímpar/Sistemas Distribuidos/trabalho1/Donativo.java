package trabalho;

public class Donativo implements java.io.Serializable{
    public int artistID;  
    public int donativo;    


    public Donativo(int artistID, int donativo){
        this.artistID = artistID;
        this.donativo = donativo;
    }

    public void setDonativo(int donativo) {
        this.donativo = donativo;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public int getDonativo(){
        return donativo;
    }

    public int artistID(){
        return artistID;    
    }
}