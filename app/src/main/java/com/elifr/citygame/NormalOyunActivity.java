package com.elifr.citygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class NormalOyunActivity extends AppCompatActivity {

    public TextView txtIlBilgi, txtIl, txtToplamPuan;
    public EditText editTxtTahmin;
    public String[] iller = {"Adana", "Adiyaman", "Afyonkarahisar", "Agri", "Aksaray", "Amasya", "Ankara",
            "Antalya", "Ardahan", "Artvin", "Aydin", "Balikesir", "Bartin", "Batman", "Bayburt",
            "Bilecik", "Bingol", "Bitlis", "Bolu", "Burdur", "Bursa", "Canakkale", "Cankiri",
            "Corum", "Denizli", "Diyarbakir", "Duzce", "Edirne", "Elazig", "Erzincan", "Erzurum",
            "Eskisehir", "Gaziantep", "Giresun", "Gumushane", "Hakkari", "Hatay", "Igdir", "Isparta",
            "Istanbul", "Izmir", "Kahramanmaras", "Karabuk", "Karaman", "Kars", "Kastamonu", "Kayseri",
            "Kilis", "Kirikkale", "Kirklareli", "Kirsehir", "Kocaeli", "Konya", "Kutahya", "Malatya",
            "Manisa", "Mardin", "Mersin", "Mugla", "Mus", "Nevsehir", "Nigde", "Ordu", "Osmaniye",
            "Rize", "Sakarya", "Samsun", "Sanliurfa", "Siirt", "Sinop", "Sirnak", "Sivas", "Tekirdag",
            "Tokat", "Trabzon", "Tunceli", "Usak", "Van", "Yalova", "Yozgat", "Zonguldak"};

    public Random rndIl = new Random();
    public Random rndHarf = new Random();
    public int rndIlNumber, rndHarfNumber, baslangicHarfSayisi;
    public String gelenIl, ilBoyutu, editTxtGelenTahmin;
    public ArrayList<Character> ilHarfleri = new ArrayList<>();
    public float maxPuan = 100.0f, azaltilacakPuan, toplamPuan = 0, bolumToplamPuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_oyun);

        txtIlBilgi = (TextView)findViewById(R.id.txtNormalSehir);
        txtIl = (TextView)findViewById(R.id.textViewNormalSehir);
        editTxtTahmin = (EditText)findViewById(R.id.editTextNormalTahmin);
        txtToplamPuan = (TextView)findViewById(R.id.txtToplamPuan);

        randomDegerleriBelirle();

    }

    public void randomDegerleriBelirle(){
        ilBoyutu = "";
        rndIlNumber = rndIl.nextInt(iller.length);
        gelenIl = iller[rndIlNumber];
        System.out.println(gelenIl);
        txtIlBilgi.setText(gelenIl.length() + " Harfli İlimiz");

        if (gelenIl.length() > 0 && gelenIl.length() < 5)
            baslangicHarfSayisi = 0;
        else if (gelenIl.length() >= 5 && gelenIl.length() <= 7)
            baslangicHarfSayisi = 1;
        else if (gelenIl.length() >= 8 && gelenIl.length() < 10)
            baslangicHarfSayisi = 2;
        else if (gelenIl.length() >= 10)
            baslangicHarfSayisi = 3;

        System.out.println(baslangicHarfSayisi);

        for (int i = 0; i < gelenIl.length(); i++){
            if (i < gelenIl.length() - 1)
                ilBoyutu += "_ ";
            else
                ilBoyutu += "_";
        }

        txtIl.setText(ilBoyutu);

        for (char c: gelenIl.toCharArray())
            ilHarfleri.add(c);

        for (int c = 0; c < baslangicHarfSayisi; c++)
            randomHarfAl();

        azaltilacakPuan = maxPuan / ilHarfleri.size();
        toplamPuan = maxPuan;
        //System.out.println("Azaltılacak puan = " + azaltilacakPuan);
    }
    public void btnHarfAl(View v){
        if (ilHarfleri.size() > 0) {
            randomHarfAl();
            toplamPuan -= azaltilacakPuan;
        }else{
            Toast.makeText(getApplicationContext(), "Alınacak harf bitti!", Toast.LENGTH_LONG).show();
        }
    }


    public void btnTahminEt(View v){
        editTxtGelenTahmin = editTxtTahmin.getText().toString();

        if (!TextUtils.isEmpty(editTxtGelenTahmin)){
            if (editTxtGelenTahmin.equals(gelenIl)) {
                bolumToplamPuan +=toplamPuan;
                txtToplamPuan.setText("Toplam Puan = " + bolumToplamPuan);
                Toast.makeText(getApplicationContext(), "Tebrikler! Doğru Tahminde Bulundunuz.", Toast.LENGTH_LONG).show();
                editTxtTahmin.setText("");
                randomDegerleriBelirle();

                //System.out.println("Doğru tahminde bulundunuz");
            }
            else
                Toast.makeText(getApplicationContext(), "Üzgünüz! Yanlış Tahminde Bulundunuz.", Toast.LENGTH_LONG).show();
                //System.out.println("Yanlış tahminde bulundunuz");
        }else
            System.out.println("Tahmin değeri boş olamaz");
    }

    public void randomHarfAl(){
        rndHarfNumber = rndHarf.nextInt(ilHarfleri.size());
        //System.out.println(ilHarfleri.get(rndHarfNumber));
        String[] txtHarfler = txtIl.getText().toString().split(" ");
        char[] gelenIlHarfler = gelenIl.toCharArray();

        for (int  i = 0; i < gelenIl.length(); i++){
            if (txtHarfler[i].equals("_") && gelenIlHarfler[i] == ilHarfleri.get(rndHarfNumber)){
                txtHarfler[i] = String.valueOf(ilHarfleri.get(rndHarfNumber));
                ilBoyutu = "";

                for (int j = 0; j < gelenIl.length(); j++){
                    if (j == i)
                        ilBoyutu += txtHarfler[j] + " ";
                    else if (j < gelenIl.length() - 1)
                        ilBoyutu += txtHarfler[j] + " ";
                    else
                        ilBoyutu += txtHarfler[j];
                }

                break;
            }
        }

        txtIl.setText(ilBoyutu);
        ilHarfleri.remove(rndHarfNumber);
    }
}