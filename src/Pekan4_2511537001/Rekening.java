package Pekan4_2511537001;
import java.util.*;

public class Rekening {
	private String nomorRekening;
	private String namaPemilik;
	protected double saldo;
	private String pin;

	private double totalsetor = 0;
	private double totaltarik = 0;

	protected ArrayList<Transaksi> riwayatTransaksi;

	public Rekening(String nomor, String nama, double saldoAwal, String pinAwal) {
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;

		if (pinAwal.matches("\\d{6}")) {
			this.pin = pinAwal;
		} else {
			System.out.println("Peringatan : PIN harus 6 digit angka! Menggunakan PIN default 123456");
			this.pin = "123456";
		}
		this.riwayatTransaksi = new ArrayList<>();
		System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp" + saldo);
	}

	public String getNomorRekening() { return nomorRekening; }
	public String getNamaPemilik() { return namaPemilik; }

	public boolean otentikasi(String inputPin) {
		return this.pin.equals(inputPin);
	}

	public boolean gantiPin(String pinLama, String pinBaru) {
		if (!otentikasi(pinLama)) {
			System.out.println("Akses Ditolak: PIN lama yang Anda masukkan salah!");
			return false;
		}
		if (!pinBaru.matches("\\d{6}")) {
			System.out.println("Error: PIN baru harus terdiri dari 6 digit angka!");
			return false;
		}
		this.pin = pinBaru;
		return true;
	}

	public void setorTunai(double nominal) {
		if (nominal > 0) {
			saldo += nominal;
			totalsetor += nominal;
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			riwayatTransaksi.add(new Transaksi(idTrx, "Kredit", nominal));
			System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
		} else {
			System.out.println("Gagal : Nominal setor harus lebih dari 0!");
		}
	}

	public void TarikTunai(double nominal) {
		if (nominal < 10000) {
			System.out.println("Gagal : Nominal tarik minimal Rp10000!");
		} else if (saldo < nominal) {
			System.out.println("Gagal : Saldo lebih kecil dari nominal tarikan");
		} else {
			String idTrx = "TRX-T-" + System.currentTimeMillis();
			riwayatTransaksi.add(new Transaksi(idTrx, "Debit", nominal));
			saldo -= nominal;
			totaltarik += nominal;
			System.out.println("Tarik tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
		}
	}

	public void cetakMutasi() {
		System.out.println("--- Riwayat Transaksi ---");
		if (riwayatTransaksi.isEmpty()) {
			System.out.println("Belum ada transaksi pada rekening ini");
			return;
		}
		int totalriwayat = 0;
		for (Transaksi tr : riwayatTransaksi) {
			tr.cetakDetail();
			totalriwayat++;
		}
		System.out.println("Total transaksi sebanyak : " + totalriwayat);
	}

	public void Totall() {
		System.out.println("Total setor : Rp" + totalsetor);
		System.out.println("Total tarik : Rp" + totaltarik);
		System.out.println("Saldo akhir : Rp" + saldo);
	}

	public void cekInformasi() {
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening : " + nomorRekening);
		System.out.println("Nama Pemilik : " + namaPemilik);
		System.out.println("Saldo Akhir  : Rp" + saldo);
	}
}