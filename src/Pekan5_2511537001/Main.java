package Pekan5_2511537001;

import java.util.ArrayList;
import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Rekening> daftarRekening = new ArrayList<>();
        Rekening akunAktif = null;
        boolean isRunning = true;
 
        System.out.println("=== SISTEM PERBANKAN MINI ===");
 
        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Buka Rekening Baru");
            System.out.println("2. Setor Tunai");
            System.out.println("3. Tarik Tunai");
            System.out.println("4. Cek Informasi Rekening");
            System.out.println("5. Ganti Akun");
            System.out.println("6. Cetak Mutasi (Riwayat)");
            System.out.println("7. Simulasi Akhir Bulan (Khusus Tabungan)");
            System.out.println("8. Transfer ke Rekening Lain");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
 
            int pilihan = input.nextInt();
            input.nextLine();
            
            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan No Rekening: ");
                    String no = input.nextLine();
                    System.out.print("Masukkan Nama Pemilik: ");
                    String nama = input.nextLine();
                    System.out.print("Masukkan Saldo Awal: ");
                    double saldo = input.nextDouble();
                    input.nextLine();
                    System.out.print("Masukkan PIN (6 digit): ");
                    String pin = input.nextLine();
                    System.out.println("Pilih Produk: 1. Tabungan Umum | 2. Giro Bisnis");
                    System.out.print("Pilihan: ");
                    int produk = input.nextInt();
                    input.nextLine();
 
                    if (produk == 1) {
                        System.out.print("Masukkan suku bunga (%): ");
                        double bunga = input.nextDouble();
                        input.nextLine();
                        akunAktif = new RekeningTabungan(no, nama, saldo, pin, bunga);
                        daftarRekening.add(akunAktif); // upcasting
                    } else if (produk == 2) {
                        System.out.print("Masukkan batas overdraft: ");
                        double batas = input.nextDouble();
                        input.nextLine();
                        akunAktif = new RekeningGiro(no, nama, saldo, pin, batas);
                        daftarRekening.add(akunAktif); // upcasting
                    } else {
                        System.out.println("Pilihan produk tidak valid!");
                    }
                    break;
                    
                case 2:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum memiliki nomor rekening!");
                    } else {
                        System.out.print("Masukkan nominal setor: ");
                        double setor = input.nextDouble();
                        input.nextLine();
                        akunAktif.setorTunai(setor);
                    }
                    break;
 
                case 3:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum membuka rekening!");
                    } else {
                        System.out.print("Masukkan PIN: ");
                        String pinTarik = input.nextLine();
                        if (akunAktif.otentikasi(pinTarik)) {
                            System.out.print("Masukkan nominal tarik: ");
                            double tarik = input.nextDouble();
                            input.nextLine();
                            akunAktif.tarikTunai(tarik); // dynamic dispatch
                        } else {
                            System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                        }
                    }
                    break;
 
                case 4:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum membuka rekening!");
                    } else {
                        akunAktif.cekInformasi();
                    }
                    break;
 
                case 5:
                    System.out.print("Masukkan nomor rekening: ");
                    String cari = input.nextLine();
                    Rekening ditemukan = null;
                    for (Rekening r : daftarRekening) {
                        if (r.getNomorRekening().equals(cari)) {
                            ditemukan = r;
                            break;
                        }
                    }
                    if (ditemukan != null) {
                        akunAktif = ditemukan;
                        System.out.println("Akun aktif: " + akunAktif.getNamaPemilik());
                    } else {
                        System.out.println("Rekening tidak ditemukan!");
                    }
                    break;
 
                case 6:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum membuka rekening!");
                    } else {
                        System.out.print("Masukkan PIN: ");
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
                        System.out.println("Error: Anda belum membuka rekening!");
                    } else if (akunAktif instanceof RekeningTabungan) {
                        RekeningTabungan tab = (RekeningTabungan) akunAktif; // downcasting
                        tab.tambahBungaAkhirBulan();
                    } else {
                        System.out.println("Gagal: Fitur bunga akhir bulan hanya berlaku untuk Rekening Tabungan.");
                    }
                    break;
 
                case 8:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum membuka rekening!");
                    } else {
                        System.out.print("Masukkan No Rekening Tujuan: ");
                        String noTujuan = input.nextLine();
                        Rekening akunTujuan = null;
                        for (Rekening r : daftarRekening) {
                            if (r.getNomorRekening().equals(noTujuan)) {
                                akunTujuan = r;
                                break;
                            }
                        }
                        if (akunTujuan == null) {
                            System.out.println("Rekening tujuan tidak ditemukan!");
                        } else if (akunTujuan == akunAktif) {
                            System.out.println("Tidak bisa transfer ke rekening sendiri!");
                        } else {
                            System.out.print("Masukkan nominal transfer: ");
                            double nominal = input.nextDouble();
                            input.nextLine();
                            System.out.print("Masukkan PIN: ");
                            String pinTransfer = input.nextLine();
                            akunAktif.transfer(akunTujuan, nominal, pinTransfer);
                        }
                    }
                    break;
 
                case 0:
                    isRunning = false;
                    System.out.println("Sistem ditutup. Terima kasih!");
                    break;
 
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        input.close();
    }
}
