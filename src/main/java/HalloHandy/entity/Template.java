package HalloHandy.entity;


import HalloHandy.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.Id;


@Entity(name = "template")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Template extends AbstractEntity {

    @Column(name = "name")
    private  String name;
    @Column(name = "vorname")
    private  String vorname;
    @Column(name = "strabe")
    private  String strabe;
    @Column(name = "plzOrt")
    private  String plzOrt;
    @Column(name = "email")
    private  String email;
    @Column(name = "telefon")
    private  String telefon;
    @Column(name = "mobil")
    private  String mobil;
    @Column(name = "passwort")
    private  String passwort;
    @Column(name = "hertseller")
    private  String hertseller;
    @Column(name = "modell")
    private  String modell;
    @Column(name = "seriennr")
    private  String seriennr;
    @Column(name = "imei")
    private  String imei;
    @Column(name = "zubehor")
    private  String zubehor ;
    @Column(name="signature")
    private byte[] signature;
    @Column(name="lockScreen")
    private  String lockScreen;
    @Column(name="preis")
    private  String preis;
    @Column(name = "fehferdiagnose")
    private  boolean fehferdiagnose;
    @Column(name = "akku")
    private  boolean akku;
    @Column(name = "annaherungssensor")
    private  boolean annaherungssensor;
    @Column(name = "backlight")
    private  boolean backlight;
    @Column(name = "board")
    private  boolean board;
    @Column(name = "datensicherung")
    private  boolean datensicherung;
    @Column(name = "wiederherstellung")
    private  boolean wiederherstellung;
    @Column(name = "display")
    private  boolean display;
    @Column(name = "frontkamera")
    private  boolean frontkamera;
    @Column(name = "hauptkamera")
    private  boolean hauptkamera;
    @Column(name = "homebutton")
    private  boolean homebutton;
    @Column(name = "hormuschel")
    private  boolean hormuschel;
    @Column(name = "kamera")
    private  boolean kamera;
    @Column(name = "kameragias")
    private  boolean kameragias;
    @Column(name = "kopfhorereing")
    private  boolean kopfhorereing;
    @Column(name = "ladebuchse")
    private  boolean ladebuchse;
    @Column(name = "lautsprecher")
    private  boolean lautsprecher;
    @Column(name = "mikrofon")
    private  boolean mikrofon;
    @Column(name = "powerLLL")
    private  boolean powerLLL;
    @Column(name = "ruckseite")
    private  boolean ruckseite;
    @Column(name = "vibrationasalarm")
    private  boolean vibrationasalarm;
    @Column(name = "wasserschaden")
    private  boolean wasserschaden;
    @Column(name = "wifiAntenne")
    private  boolean wifiAntenne;
    @Column(name = "sonstigeFehler")
    private  boolean sonstigeFehler;
    @Column(name = "status")
    private Status status;

}
