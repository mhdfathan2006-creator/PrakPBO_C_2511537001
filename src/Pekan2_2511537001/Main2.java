package Pekan2_2511537001;
import java.util.*;

public class Main2 {
	
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
			// nomor 7 challenge
			System.out.println("7.Total setoran, tarik dan tunai");
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
				
				// bonus dari pemikiran saya hehe
				//1.jika ada nomor rekening yang sama diinput, maka rekeningbaru gagal dilakukan
				//2 jika saldo kurang dari nol, maka rekeningbaru gagal dilakukan
				//case 1 jika di masukkan noRekening yang sama dengan sebelumnya
				Rekening duplikat = null;
				for(Rekening r : DaftarRekening) {
					if(r.nomorRekening.equals(no)) {
						duplikat = r;
						break;
					}
				}
				//gagalkan jika noRekening sudah dibuat sebelumnya
				if(duplikat != null) {
					System.out.print("No rekening " + duplikat.nomorRekening +" sudah ada");
				}else if(saldo < 0) {
					//gagalkan juga jika saldo kurang dari 0
					System.out.print("Saldo tidak boleh kurang dari nol");
				}else {
					//instanslasi object / menjalankan constructor
					Rekening rekeningBaru = new Rekening(no,nama,saldo);
					DaftarRekening.add(rekeningBaru);
					akunAktif = rekeningBaru;
				}
				break;
				
			case 2:
				if(akunAktif == null) {
					System.out.println("Error : Mohon maaf, Anda belum memiliki nomor rekening!");
				} else {
					System.out.print("Masukkan nominal setor :");
					double setor = input.nextDouble();
					akunAktif.setorTunai(setor);
				}
				break;
			case 3:
				System.out.println("Masukkan nominal yang harus ditarik(minimal 10 ribu): ");
				double tarik =  input.nextDouble();
				akunAktif.TarikTunai(tarik);
				break;
			
			case 4:
				if(akunAktif == null) {
					System.out.println("Error: Anda belum membuka rekening");
				} else {
					akunAktif.cekInformasi();
				}
				break;
			
			case 5:
				//soal bonus dari modul
				if(DaftarRekening.isEmpty()) {
					System.out.println("belum ada rekening");
					return;
				}
				System.out.print("Masukkan nomor rekening yang ingin di aktifkan");
				String noRekening = input.nextLine();
				Rekening ditemukan = null;
				for(Rekening r : DaftarRekening) {
					if(r.nomorRekening.equals(noRekening)) {
						ditemukan = r;
						break;
					}
				}
				if(ditemukan != null) {
					akunAktif = ditemukan;
					System.out.println("Akun aktif berhasil diganti ke:" +akunAktif.nomorRekening);
				} else {
					System.out.println("Rekning dengan nomor di inputkan tidak ditemukan");
				}
				break;
				
			case 6:
				akunAktif.cetakMutasi();
				break;
				
			case 7:
				akunAktif.Totall();
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