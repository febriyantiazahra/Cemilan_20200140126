/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tugas.IT.Cemilan_SatuDuaEnam;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Febriyanti Azahra
 */
@Controller
public class ControllerPembelian {

    private int hitungawal(int harga, int jumlah){
        int totalawal = harga * jumlah;
        return totalawal;
    }
    
    private int hitungakhir(int harga, int jumlah){
        int hargaAkhir;
        int total = harga * jumlah;
        if (total < 16000){
            hargaAkhir = total - (total * 0);
        }
        else if (total > 16000){
            hargaAkhir = total - (total * 10 / 100);
        }
        else {
            hargaAkhir = total - (total * 15 / 100);
        }
        
        return hargaAkhir;
    }
    
    private int hargadiskon(int total, int hargaAkhir){
        int hargadiskon = total - hargaAkhir;
        return hargadiskon;
    }
    
    private int diskon(int totalawal){
        int disc;
        if (totalawal < 16000){
            disc = 0;
        }
        else if (totalawal > 16000){
            disc = 10;
        }
        else {
            disc = 15;
        }
        return disc;
    }
    
    private int kembalian(int bayar, int hargaAkhir){
        int kembalian = bayar - hargaAkhir;
        return kembalian;
    }
    
    @RequestMapping("/sayur")
    //@ResponseBody
  
    public String getMenu (HttpServletRequest Data, Model Beli){
        
        jualbeli JL = new jualbeli();
        
        String namaSayur = Data.getParameter("nmSayur");
        int hargaSayur = Integer.parseInt(Data.getParameter("hargaperkilo"));
        int jumlahSayur = Integer.parseInt(Data.getParameter("jumlahbeli"));
        int uangbyr = Integer.parseInt(Data.getParameter("uangbayar"));
        
        int totalawal = hitungawal(hargaSayur, jumlahSayur);
        int hargaAkhir = hitungakhir(hargaSayur, jumlahSayur);
        int hargadiskon = hargadiskon(totalawal, hargaAkhir);
        int diskon = diskon(totalawal);
        int kembalian = kembalian(uangbyr, hargaAkhir);
                
        Beli.addAttribute("sayur", namaSayur);
        Beli.addAttribute("harga", hargaSayur);
        Beli.addAttribute("kilo", jumlahSayur);
        Beli.addAttribute("totalbyr", uangbyr);
        Beli.addAttribute("hitungawal", totalawal);
        Beli.addAttribute("hitungakhir", hargaAkhir);
        Beli.addAttribute("totalhargadiskon", hargadiskon);
        Beli.addAttribute("diskon", diskon);
        Beli.addAttribute("kembali", kembalian);
        
        return "view";
    }
}    