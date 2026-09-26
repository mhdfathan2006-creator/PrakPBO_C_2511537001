package Pekan3_2511537001;

import java.util.*;

public class Rekening {
	//1. mengunci attribute dengan private
	private String nomorRekening;
	private String namaPemilik;
	private double saldo;
	private String pin;
	
	//variabel dari challenge asprak
	double totalsetor = 0;
	double totaltarik = 0; 
	//implementasi Asosiasi (1 to many)
	private ArrayList<Transaksi> riwayatTransaksi;
	
	public Rekening(String nomor, String nama, double saldoAwal, String pinAwal) {
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		//Validasi PIN di dalam Construktor
		if (pinAwal.length() == 6) {
			this.pin = pinAwal;
		} else {
			System.out.println("Peringatan : PIN harus 6 digit! Menggunakan PIN default 123456");
			this.pin = "123456";
		}
		// wajib menginisialisasi ArrayList di dalam construktor agar tidak NullPointerException
		this.riwayatTransaksi = new ArrayList<>();
		System.out.println("Rekening atas nama" + namaPemilik + " berhasil dibuat dengan saldo Rp" + saldo);
	}
	//3. Getter untuk atribut yang diizinkan di baca publik
	public String getNomorRekening() {return nomorRekening;}
	public String getNamaPemilik() {return namaPemilik;}
	
	//4. Method otentikasi internal (validasi enkapsulasi)
	public boolean otentikasi(String inputPin) {
		return this.pin.equals(inputPin);
	}
	public boolean gantiPin(String pinLama, String pinBaru) {
	    // Cek dulu PIN lama harus benar
	    if (!otentikasi(pinLama)) {
	        return false;
	    }
	    // Validasi format PIN baru harus 6 digit angka
	    if (!pinBaru.matches("\\d{6}")) {
	        System.out.println("Error: PIN baru harus terdiri dari 6 digit angka!");
	        return false;
	    }
	    this.pin = pinBaru;
	    return true;
	}
	public void setorTunai(double nominal) {
		if(nominal > 0) {
			saldo += nominal;
			totalsetor += nominal;
			//merekam riwayat (Pembuatan objek Transaksi di dalam method)
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx,"Kredit", nominal);
			riwayatTransaksi.add(trxBaru);
			System.out.println("Setor tunai Rp" + nominal 
					+" berhasil. saldo saat ini: Rp" + saldo);
		}else {
			System.out.println("Gagal : Nominal setor harus lebih dari 0!");
		}
	}
	
	public void TarikTunai(double nominal) {
		if(nominal < 10000) {
			System.out.println("Gagal : Nominal tarik setor harus lebih dari 10000!");
			
		}else if (saldo <= nominal) {
			System.out.println("Gagal : Saldo lebih kecil dari nominal tarikan");
		}
		else {
			//case berhasil di sini yakk
			//rekam riwayat 
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx,"Dedit", nominal);
			riwayatTransaksi.add(trxBaru);
			saldo -= nominal;
			totaltarik += nominal;
			System.out.println("Tarik tunai Rp " + nominal 
					+" berhasil. saldo saat ini: Rp" + saldo);
		}
	}
	public void cetakMutasi() {
		System.out.println("Riwayat Transaksi");
	
		int totalriwayat = 0;
		if(riwayatTransaksi.isEmpty()) {
			System.out.println("Belum ada Transaksi pada rekening ini");
			return;
		}
		for(Transaksi tr : riwayatTransaksi) {
				tr.cetakDetail();
				totalriwayat++;
			
			}
		System.out.println("Total Transaksi sebanyak :" + totalriwayat);
	}
	
	//ini challange dari asprak
	public void Totall() {
		System.out.println("total setor :" + totalsetor);
		System.out.println("total tarik :" + totaltarik);
		System.out.println("total saldo :" + saldo);
	}
	
	
	
	public void cekInformasi() {
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening : " + nomorRekening);
		System.out.println("Nama Pemilik : " + namaPemilik);
		System.out.println("Salod Akhir  : Rp" + saldo);
		System.out.println("---------------------");
	}
	
	
	
}