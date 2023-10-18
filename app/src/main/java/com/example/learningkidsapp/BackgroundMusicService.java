package com.example.learningkidsapp;


    import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

    public class BackgroundMusicService extends Service {
        private MediaPlayer mediaPlayer;

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            mediaPlayer = MediaPlayer.create(this, R.raw.main_music);
            mediaPlayer.setLooping(true); // Set the music to loop
            mediaPlayer.start();
            return START_STICKY;
        }

        @Override
        public void onDestroy() {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }


