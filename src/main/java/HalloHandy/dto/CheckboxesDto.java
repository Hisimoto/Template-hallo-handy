package HalloHandy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckboxesDto {
    private  boolean fehferdiagnose;
    private  boolean akku;
    private  boolean annaherungssensor;
    private  boolean backlight;
    private  boolean board;
    private  boolean datensicherung;
    private  boolean wiederherstellung;
    private  boolean display;
    private  boolean frontkamera;
    private  boolean hauptkamera;
    private  boolean homebutton;
    private  boolean hormuschel;
    private  boolean kamera;
    private  boolean kameragias;
    private  boolean kopfhorereing;
    private  boolean ladebuchse;
    private  boolean lautsprecher;
    private  boolean mikrofon;
    private  boolean powerLLL;
    private  boolean ruckseite;
    private  boolean vibrationasalarm;
    private  boolean wasserschaden;
    private  boolean wifiAntenne;
    private  boolean sonstigeFehler;
}
