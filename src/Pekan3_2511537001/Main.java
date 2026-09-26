package Pekan3_2511537001;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Rekening akunAktif = null;
		boolean isRunning = true;
		ArrayList<Rekening> DaftarRekening = new ArrayList<>();
		System.out.println("=== SISTEM PERBANKAN MINI ===");

		while (isRunning) {
			System.out.println("\nMenu Utama");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek infromasi Rekening");
			System.out.println("5. Ganti Akun");
			System.out.println("6.riwayat transaksi");
			System.out.println("7.Total setoran, tarik dan tunai");
			System.out.println("8. Ganti PIN");
			System.out.println("0. Keluar");
			System.out.print("Pilih menu: ");

			int pilihan = input.nextInt();
			input.nextLine();

			switch (pilihan) {
			case 1:
				System.out.println("Masukkan No Rekening : ");
				String no = input.nextLine();
				System.out.println("Masukkan Nama Pemilik: ");
				String nama = input.nextLine();
				System.out.println("Masukkan saldo Awal  : ");
				double saldo = input.nextDouble();
				input.nextLine();

				System.out.print("Buat PIN (6 digit angka): ");
				String pinBaru = input.nextLine();

				if (!pinBaru.matches("\\d{6}")) {
					System.out.println("Error: PIN harus terdiri dari 6 digit angka!");
					break;
				}

				Rekening duplikat = null;
				for(Rekening r : DaftarRekening) {
					if(r.getNomorRekening().equals(no)) {   // diperbaiki: pakai getter
						duplikat = r;
						break;
					}
				}

				if(duplikat != null) {
					System.out.println("No rekening " + duplikat.getNomorRekening() +" sudah ada"); // diperbaiki
				}else if(saldo < 0) {
					System.out.println("Saldo tidak boleh kurang dari nol");
				}else {
					Rekening rekeningBaru = new Rekening(no,nama,saldo,pinBaru);
					DaftarRekening.add(rekeningBaru);
					akunAktif = rekeningBaru;
					System.out.println("Rekening berhasil dibuat!");
				}
				break;

			case 2:
				if(akunAktif == null) {
					System.out.println("Error : Mohon maaf, Anda belum memiliki nomor rekening!");
				} else {
					System.out.print("Masukkan nominal setor :");
					double setor = input.nextDouble();
					input.nextLine();
					akunAktif.setorTunai(setor);
				}
				break;

			case 3:
				if(akunAktif == null) {
					System.out.println("Error : Anda belum memiliki rekening aktif!");
				} else {
					System.out.print("Masukkan PIN Anda: ");
					String pinTarik = input.nextLine();

					if(akunAktif.otentikasi(pinTarik)) {
						System.out.print("Masukkan nominal yang harus ditarik(minimal 10 ribu): ");
						double tarik =  input.nextDouble();
						input.nextLine();
						akunAktif.TarikTunai(tarik);
					} else {
						System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
					}
				}
				break;

			case 4:
				if(akunAktif == null) {
					System.out.println("Error: Anda belum membuka rekening");
				} else {
					akunAktif.cekInformasi();
				}
				break;

			case 5:
				if(DaftarRekening.isEmpty()) {
					System.out.println("belum ada rekening");
					break;   // diperbaiki dari return
				}
				System.out.print("Masukkan nomor rekening yang ingin di aktifkan");
				String noRekening = input.nextLine();
				Rekening ditemukan = null;
				for(Rekening r : DaftarRekening) {
					if(r.getNomorRekening().equals(noRekening)) {   // diperbaiki: pakai getter
						ditemukan = r;
						break;
					}
				}
				if(ditemukan != null) {
					akunAktif = ditemukan;
					System.out.println("Akun aktif berhasil diganti ke: " +akunAktif.getNomorRekening()); // diperbaiki
				} else {
					System.out.println("Rekning dengan nomor di inputkan tidak ditemukan");
				}
				break;

			case 6:
				if(akunAktif == null) {
					System.out.println("Error: Anda belum memiliki rekening aktif!");
				} else {
					System.out.print("Masukkan PIN Anda: ");
					String pinMutasi = input.nextLine();

					if(akunAktif.otentikasi(pinMutasi)) {
						akunAktif.cetakMutasi();
					} else {
						System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
					}
				}
				break;

			case 7:
				if(akunAktif == null) {                          // ditambahkan
					System.out.println("Error: Anda belum memiliki rekening aktif!");
				} else {
					akunAktif.Totall();
				}
				break;
				
			case 8:
			    if(akunAktif == null) {
			        System.out.println("Error: yaaaa gapunyaa rekening yakkkk hahahaha!");
			    } else {
			        System.out.print("Masukkan PIN lama: ");
			        String pinLama = input.nextLine();

			        System.out.print("Masukkan PIN baru (6 digit angka): ");
			        String pinBaruGanti = input.nextLine();

			        boolean berhasil = akunAktif.gantiPin(pinLama, pinBaruGanti);

			        if(berhasil) {
			            System.out.println("PIN sudah diubah yups!");
			        } else if(!akunAktif.otentikasi(pinLama)) {
			            // catatan: PIN sudah terlanjur diganti kalau berhasil,
			            // jadi pengecekan ulang ini hanya valid kalau otentikasi awal gagal
			            System.out.println("Akses Ditolak: PIN lama yang Anda masukkan salah!");
			        }
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