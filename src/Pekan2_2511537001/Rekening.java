package Pekan2_2511537001;

import java.util.*;
public class Rekening {
	
	String nomorRekening;
	String namaPemilik;
	double saldo;
	//variabel dari challenge asprak
	double totalsetor = 0;
	double totaltarik = 0; 
	//implementasi Asosiasi (1 to many)
	ArrayList<Transaksi> riwayatTransaksi;
	
	public Rekening(String nomor, String nama, double saldoAwal) {
		nomorRekening = nomor;
		namaPemilik = nama;
		saldo = saldoAwal;
		
		// wajib menginisialisasi ArrayList di dalam construktor agar tidak NullPointerException
		this.riwayatTransaksi = new ArrayList<>();
		System.out.println("Rekening atas nama" + namaPemilik + 
				" berhasil dibuat dengan saldo Rp" + saldo);
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