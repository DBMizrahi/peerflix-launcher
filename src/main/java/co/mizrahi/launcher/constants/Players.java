package co.mizrahi.launcher.constants;

/**
 * Created at 14/12/2023
 *
 * @author David Mizrahi
 */
public enum Players {

    /*
      -v, --vlc          autoplay in vlc*
      -s, --airplay      autoplay via AirPlay
      -m, --mplayer      autoplay in mplayer*
      -g, --smplayer     autoplay in smplayer*
      --mpchc            autoplay in MPC-HC player*
      --potplayer        autoplay in Potplayer*
      -k, --mpv          autoplay in mpv*
      -o, --omx          autoplay in omx**
      -w, --webplay      autoplay in webplay
      -j, --jack         autoplay in omx** using the audio jack
     */

    VLC("--vlc"),
    AIRPLAY("--airplay"),
    MPLAYER("--mplayer"),
    SMPLAYER("--smplayer"),
    MPCHC("--mpchc"),
    POTPLAYER("--potplayer"),
    MPV("--mpv"),
    OMX("--omx"),
    WEBPLAY("--webplay"),
    JACK("--jack");

    private final String player;

    Players(String player) {
        this.player = player;
    }

    public String getPlayer() {
        return this.player;
    }
}
