package Pekan3_2511537001;

public class Transaksi {
	//1. Mengubah semua attribute menjadi private
	private String idTransaksi;
	private String jenis;
	private double nominal;
	
	public Transaksi (String id, String jenis, double nominal) {
		this.idTransaksi = id;
		this.jenis = jenis;
		this.nominal = nominal;
	}
	//2. Hanya menyediakan getter (Ready - Only)
	public String getIdTransaksi() {return idTransaksi;}
	public String getJenis() {return jenis;}
	public double getnominal() {return nominal;}
	
	public void cetakDetail() {
		System.out.println("ID: " + idTransaksi + "| Jenis: " + jenis + "| Nominal: Rp " + nominal);
	}

}