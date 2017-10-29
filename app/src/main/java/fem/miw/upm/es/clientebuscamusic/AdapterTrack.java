package fem.miw.upm.es.clientebuscamusic;

/**
 * Created by Raquel on 29/10/17.
 */

public class AdapterTrack {

    private int n;
    private String track;
    private String artista;
    private String imagen;
    private int id;

    public AdapterTrack(int n, String track, String artista, String imagen) {
        this.n = n;
        this.track = track;
        this.artista = artista;
        this.imagen = imagen;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
