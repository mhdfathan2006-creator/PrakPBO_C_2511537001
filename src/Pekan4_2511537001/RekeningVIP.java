package Pekan4_2511537001;

public class RekeningVIP extends Rekening {
	private static final double BONUS_PENDAFTARAN = 100_000;

	public RekeningVIP(String noRek, String pemilik, double saldoAwal, String pin) {
		super(noRek, pemilik, saldoAwal, pin);
		setorTunai(BONUS_PENDAFTARAN); // bonus otomatis masuk saat rekening dibuat
		System.out.println("Selamat! Bonus pendaftaran Rp" + (long) BONUS_PENDAFTARAN
				+ " telah masuk ke rekening Anda.");
	}

	public double getBonusPendaftaran() {
		return BONUS_PENDAFTARAN;
	}
}