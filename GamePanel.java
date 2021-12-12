package com.mygame;

import java.awt.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.FloatControl.Type;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import javax.swing.*;

public class GamePanel extends JPanel{
    Floor floor;
    VoiceBar voiceBar;
    Bear player;
    Line line;
    TargetDataLine dataLine;
    FloatControl voiceControl;
    public GamePanel(){
        setLayout(null);
        setSize(800, 600);
        setFloor();
        setVoiceBar();
        setPlayer();
        //setVoiceInput();
        setVoiceControl();
        //printMixersDetails();
    }
    public void setFloor(){
        floor = new Floor();
        this.add(floor);
    }

    public void setVoiceBar(){
        voiceBar = new VoiceBar();
        this.add(voiceBar);
    }

    public void setPlayer(){
        player = new Bear();
        this.add(player);
    }

    public void start() {
        ////开启键盘监听事件
        //KeyListener l = new KeyAdapter() {
        //    /*
        //     * KeyPressed()
        //     * 是键盘按钮 按下去所调用的方法
        //     */
        //    @Override
        //    public void keyPressed(KeyEvent e) {
        //        // 获取一下键子的代号
        //        int code = e.getKeyCode();
        //        switch (code) {
        //            case KeyEvent.VK_DOWN:
        //                play();
        //                break;
        //            default:
        //                break;
        //        }
        //        //按一次重新绘制一次
        //        repaint();
        //    }
        //};
        ////面板添加监听事件对象
        //this.addKeyListener(l);
        ////面板对象设置成焦点
        this.requestFocus();
        while(true) {
            play();
            repaint();
        }
    }

    public void play() {
        double volume = getVolume();
        System.out.println(volume);
        if(volume < 50){
            return;
        }
        //System.out.println(volume);
        voiceBar.update((int)(volume * 200));
        floor.update();
        collide();

    }

    public void collide(){
        int playX = player.getX() - floor.getX();
        for(Component grass: floor.getComponents()){
            if(grass.getX() <= playX + player.getWidth() * 0.8 && player.getWidth() * 0.2 <= grass.getX() + grass.getWidth()){
                if(player.getY() < grass.getHeight()){
                    player.land(grass.getHeight());
                    break;
                }
            }
        }
    }

    public double getVolume(){
        byte[] audioData = new byte[dataLine.getBufferSize()];
        dataLine.read(audioData, 0, audioData.length);

        //ByteBuffer buffer = ByteBuffer.allocate(dataLine.getBufferSize());
        //buffer.put(bytes, 0, bytes.length);
        //buffer.flip();//need flip
        //System.out.println(buffer.getShort());
        //return buffer.getShort();


        long lSum = 0;
        for(int i=0; i<audioData.length; i++) {
            lSum = lSum + audioData[i];
        }

        double dAvg = lSum / audioData.length;

        double sumMeanSquare = 0d;
        for(int j=0; j<audioData.length; j++) {
            sumMeanSquare = sumMeanSquare + Math.pow(audioData[j] - dAvg, 2d);
        }

        double averageMeanSquare = sumMeanSquare / audioData.length;
        return Math.pow(averageMeanSquare,0.5d) + 0.5;
    }

    public void setVoiceControl(){
        javax.sound.sampled.Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        for(int i=0;i<mixers.length;i++){
            Mixer.Info mixerInfo = mixers[i];
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            Line.Info[] lineinfos = mixer.getTargetLineInfo();
            for(Line.Info lineinfo : lineinfos){
                try {
                    Line line = mixer.getLine(lineinfo);
                    line.open();
                    if(line.isControlSupported(Type.BALANCE)){
                        voiceControl = (FloatControl) line.getControl(FloatControl.Type.BALANCE);
                        return;
                    }
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setVoiceInput(){
        try {
            AudioFormat format  = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 16000,16, 1, 2, 1000, false);
            dataLine = AudioSystem.getTargetDataLine(format);
            dataLine.open();
            dataLine.start();
        }catch (Exception e){

        }
    }

    public void test(){
        //JavaSound javaSound = new JavaSoundAudioClip()
        //JPortAudio jPortAudio = new JPortAudio();

    }
    //def update(self,dt):
    //    # read voice simple
    //    string_audio_data = self.stream.read(self.NUM_SAMPLES)
    //    k = max(struct.unpack('1000h', string_audio_data))
    //    self.voicebar.scale_x = k / 10000.0
    //    if k > 3000:
    //        self.floor.x -= min((k / 20.0), 150) * dt
    //    if k > 8000:
    //        self.player.jump((k - 8000) / 1000.0)
    //    self.collide()
    //
    //def collide(self):
    //    px = self.player.x - self.floor.x
    //        for b in self.floor.get_children():
    //            if b.x <= px + self.player.width * 0.8 and px + self.player.width * 0.2 <= b.x + b.width:
    //                if self.player.y < b.height:
    //                    self.player.land(b.height)
    //                    break
    //
    //def reset(self):
    //
    //    self.floor.x = 0
}
