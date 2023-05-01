package HalloHandy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class TemplateDto {

    private  String name;
    private  String vorname;
    private  String strabe;
    private  String plzOrt;
    private  String email;
    private  String telefon;
    private  String mobil;
    private  String passwort;
    private  String hertseller;
    private  String modell;
    private  String seriennr;
    private  String imei;
    private  String zubehor ;
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
