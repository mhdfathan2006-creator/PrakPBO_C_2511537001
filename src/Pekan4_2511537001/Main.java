package Pekan4_2511537001;
import java.util.*;

public class Main {
	static Scanner input = new Scanner(System.in);

	static int bacaInt(String prompt) {
		while (true) {
			System.out.print(prompt);
			try {
				return Integer.parseInt(input.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("Error: masukkan angka bulat yang valid!");
			}
		}
	}

	static double bacaDouble(String prompt) {
		while (true) {
			System.out.print(prompt);
			try {
				return Double.parseDouble(input.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("Error: masukkan angka yang valid!");
			}
		}
	}

	static Rekening cariRekening(ArrayList<Rekening> daftar, String no) {
		for (Rekening r : daftar) {
			if (r.getNomorRekening().equals(no)) {
				return r;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Rekening akunAktif = null;
		boolean isRunning = true;
		ArrayList<Rekening> daftarRekening = new ArrayList<>();

		System.out.println("=== SISTEM PERBANKAN MINI ===");

		while (isRunning) {
			System.out.println("\nMenu Utama");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Rekening");
			System.out.println("5. Ganti Akun");
			System.out.println("6. Riwayat Transaksi");
			System.out.println("7. Total Setoran dan Tarikan");
			System.out.println("8. Ganti PIN");
			System.out.println("9. Tambah Bunga Akhir Bulan (khusus Tabungan)");
			System.out.println("10. Simulasi Akhir Bulan (Khusus Tabungan)");
			System.out.println("0. Keluar");

			int pilihan = bacaInt("Pilih menu: ");

			switch (pilihan) {
			case 1:
			    System.out.println("=== Buka Rekening Baru ===");
			    System.out.print("Nama Pemilik   : ");
			    String pemilik = input.nextLine();
			    System.out.print("Nomor Rekening : ");
			    String noRek = input.nextLine();
			    System.out.print("Saldo Awal     : ");
			    double saldoAwal = input.nextDouble();
			    System.out.print("PIN            : ");
			    String pin = input.next();
			    input.nextLine(); // membersihkan buffer newline

			    System.out.println("Pilih Produk: 1. Tabungan Umum | 2. Giro Bisnis | 3. VIP");
			    System.out.print("Pilihan        : ");
			    int produk = input.nextInt();
			    input.nextLine();

			    Rekening rekeningBaru = null; // tipe Superclass

			    if (produk == 1) {
			        System.out.print("Suku Bunga (%) : ");
			        double sukuBunga = input.nextDouble();
			        input.nextLine();
			        // Upcasting: RekeningTabungan otomatis dikenali sebagai Rekening
			        rekeningBaru = new RekeningTabungan(noRek, pemilik, saldoAwal, pin, sukuBunga);

			    } else if (produk == 2) {
			        System.out.print("Batas Overdraft: ");
			        double batasOverdraft = input.nextDouble();
			        input.nextLine();
			        // Upcasting: RekeningGiro otomatis dikenali sebagai Rekening
			        rekeningBaru = new RekeningGiro(noRek, pemilik, saldoAwal, pin, batasOverdraft);
			        
			    } else if (produk == 3) {
			        // Upcasting: RekeningVIP dikenali sebagai Rekening
			        rekeningBaru = new RekeningVIP(noRek, pemilik, saldoAwal, pin);
			    } else {
			        System.out.println("Gagal: Pilihan produk tidak valid.");
			    }

			    if (rekeningBaru != null) {
			        daftarRekening.add(rekeningBaru);
			        System.out.println("Rekening berhasil dibuat untuk " + pemilik + ".");
			    }
			    break;
			case 2:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum memiliki rekening aktif!");
				} else {
					double setor = bacaDouble("Masukkan nominal setor: ");
					akunAktif.setorTunai(setor);
				}
				break;

			case 3:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum memiliki rekening aktif!");
				} else {
					System.out.print("Masukkan PIN Anda: ");
					String pinTarik = input.nextLine();
					if (akunAktif.otentikasi(pinTarik)) {
						double tarik = bacaDouble("Masukkan nominal yang ditarik (minimal 10 ribu): ");
						akunAktif.TarikTunai(tarik);
					} else {
						System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
					}
				}
				break;

			case 4:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum membuka rekening");
				} else {
					akunAktif.cekInformasi();
					if (akunAktif instanceof RekeningTabungan) {
						System.out.println("Jenis        : Tabungan");
						System.out.println("Suku Bunga   : " + ((RekeningTabungan) akunAktif).getSukuBunga() + "%");
					} else if (akunAktif instanceof RekeningGiro) {
						System.out.println("Jenis        : Giro");
						System.out.println("Batas Overdraft : Rp" + ((RekeningGiro) akunAktif).getBatasOverdraft());
					} else if (akunAktif instanceof RekeningVIP) {
					    System.out.println("Jenis        : VIP");
					    System.out.println("Bonus Daftar : Rp" + (long) ((RekeningVIP) akunAktif).getBonusPendaftaran());
					} else {
						System.out.println("Jenis        : Biasa");
					}
					System.out.println("---------------------");
				}
				break;

			case 5:
				if (daftarRekening.isEmpty()) {
					System.out.println("Belum ada rekening");
					break;
				}
				System.out.print("Masukkan nomor rekening yang ingin diaktifkan: ");
				String noRekening = input.nextLine().trim();
				Rekening ditemukan = cariRekening(daftarRekening, noRekening);
				if (ditemukan != null) {
					akunAktif = ditemukan;
					System.out.println("Akun aktif berhasil diganti ke: " + akunAktif.getNomorRekening());
				} else {
					System.out.println("Rekening dengan nomor tersebut tidak ditemukan");
				}
				break;

			case 6:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum memiliki rekening aktif!");
				} else {
					System.out.print("Masukkan PIN Anda: ");
					String pinMutasi = input.nextLine();
					if (akunAktif.otentikasi(pinMutasi)) {
						akunAktif.cetakMutasi();
					} else {
						System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
					}
				}
				break;

			case 7:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum memiliki rekening aktif!");
				} else {
					akunAktif.Totall();
				}
				break;

			case 8:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum memiliki rekening aktif!");
				} else {
					System.out.print("Masukkan PIN lama: ");
					String pinLama = input.nextLine();
					System.out.print("Masukkan PIN baru (6 digit angka): ");
					String pinBaruGanti = input.nextLine();

					if (akunAktif.gantiPin(pinLama, pinBaruGanti)) {
						System.out.println("PIN berhasil diubah!");
					}
				}
				break;

			case 9:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum memiliki rekening aktif!");
				} else if (akunAktif instanceof RekeningTabungan) {
					((RekeningTabungan) akunAktif).tambahBungaAkhirBulan();
				} else {
					System.out.println("Fitur ini hanya untuk rekening tabungan.");
				}
				break;
			case 10:
			    if (akunAktif == null) {
			        System.out.println("Error: Anda belum memiliki rekening aktif!");
			    } else if (akunAktif instanceof RekeningTabungan) {
			        // Downcasting: Rekening -> RekeningTabungan
			        RekeningTabungan tabungan = (RekeningTabungan) akunAktif;
			        tabungan.tambahBungaAkhirBulan();
			    } else {
			        System.out.println("Gagal: Fitur bunga akhir bulan hanya berlaku untuk Rekening Tabungan.");
			    }
			    break;

			case 0:
				isRunning = false;
				System.out.println("Sistem ditutup. Terima kasih!");
				break;

			default:
				System.out.println("Pilihan tidak valid");
			}
		}
		input.close();
	}
}