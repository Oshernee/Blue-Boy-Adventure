package main;

public class AudioFacade {
    private Sound music;
    private Sound se;

    public AudioFacade(Sound music, Sound se) {
        this.music = music;
        this.se = se;
    }

    // Background Music Methods
    public void playBackgroundMusic(int index) {
        music.setFile(index);
        music.play();
        music.loop();
    }

    public void stopBackgroundMusic() {
        music.stop();
    }

    // Sound Effect Method
    public void playSoundEffect(int index) {
        se.setFile(index);
        se.play();
    }

    // Volume Control - FIXED
    public void setMusicVolume(int scale) {
        music.volumeScale = scale;
        // Only check volume if a music file is loaded
        if(music.clip != null) {
            music.checkVolume();
        }
    }

    public void setSoundEffectVolume(int scale) {
        se.volumeScale = scale;
        // Only check volume if a sound effect is loaded
        if(se.clip != null) {
            se.checkVolume();
        }
    }

    public int getMusicVolume() {
        return music.volumeScale;
    }

    public int getSoundEffectVolume() {
        return se.volumeScale;
    }

    // Area-Specific Music (convenience method)
    public void playAreaMusic(int area, int outside, int indoor, int dungeon) {
        stopBackgroundMusic();
        if(area == outside) {
            playBackgroundMusic(0);
        } else if(area == indoor) {
            playBackgroundMusic(18);
        } else if(area == dungeon) {
            playBackgroundMusic(19);
        }
    }
}