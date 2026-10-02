package Pekan4_2511537001;

public class RekeningGiro extends Rekening {
	private double batasOverdraft;
	
	public RekeningGiro(String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft)
	{	//Memanggil inisialisasi dasar dari superclass
		super(nomor, nama, saldoAwal, pinAwal);
		this.batasOverdraft = batasOverdraft;
	}
	
	//Getter Khusus Giro
	public double getBatasOverdraft() {
		return batasOverdraft;
	}

	// (Catatan : Penarikan hingga limit overdraft akan kita selesaikan di Modul 5)
}
