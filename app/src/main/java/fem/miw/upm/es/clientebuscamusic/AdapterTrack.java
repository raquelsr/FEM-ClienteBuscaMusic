package fem.miw.upm.es.clientebuscamusic;

class AdapterTrack {

    private int n;
    private String track;
    private String artista;
    private String imagen;

    AdapterTrack(int n, String track, String artista, String imagen) {
        this.n = n;
        this.track = track;
        this.artista = artista;
        this.imagen = imagen;
    }

    public int getN (){
        return n;
    }
    String getTrack() {
        return track;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    String getImagen() {
        return imagen;
    }

}
