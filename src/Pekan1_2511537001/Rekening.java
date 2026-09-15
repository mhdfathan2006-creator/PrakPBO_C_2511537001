package Pekan1_2511537001;

public class Rekening {

	String nomorRekening;
	String namaPemilik;
	Double saldo;
	
	public Rekening(String nomor, String nama, double saldoAwal) {
		nomorRekening = nomor;
		namaPemilik = nama;
		saldo = saldoAwal;
		System.out.println(" Rekening atas nama " + namaPemilik + "berhasil dibuat dengan saldo Rp" +saldo);
	}
	public void setorTunai(double nominal) {
		if (nominal > 0) {
			saldo += nominal;
			System.out.println("Setor tunai Rp" + nominal + "berhasil.Saldo saat ini: Rp" +saldo);
		} else {
			System.out.println("Gagal: Nominal setor ahrus lebih! dari 0");
		}
	}
	public void cekInformasi() {
		System.out.println("---INFO REKENING---");
		System.out.println("No. Rekening : " + nomorRekening);
		System.out.println("Nama Pemilik : " + namaPemilik);
		System.out.println("Saldo Akhir  :  Rp" + saldo);
		System.out.println("----------------------");
		
	}
	public void tariktunai(double tarik) {
		// TODO Auto-generated method stub
		
	}

	}

