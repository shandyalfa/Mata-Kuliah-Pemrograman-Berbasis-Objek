import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Kelas User
class User {
    private String username;
    private String password;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter dan Setter
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

// Kelas Barang
class Barang {
    private int id;
    private String nama;
    private int jumlah;
    private double harga;

    // Constructor
    public Barang(int id, String nama, int jumlah, double harga) {
        this.id = id;
        this.nama = nama;
        this.jumlah = jumlah;
        this.harga = harga;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    @Override
    public String toString() {
        return "Barang{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", jumlah=" + jumlah +
                ", harga=" + harga +
                '}';
    }
}

// Kelas Gudang
class Gudang {
    private List<Barang> daftarBarang;

    // Constructor
    public Gudang() {
        this.daftarBarang = new ArrayList<>();
    }

    // Metode untuk menambah barang
    public void tambahBarang(Barang barang) {
        daftarBarang.add(barang);
    }

    // Metode untuk menghapus barang berdasarkan ID
    public void hapusBarang(int id) {
        daftarBarang.removeIf(barang -> barang.getId() == id);
    }

    // Metode untuk menampilkan semua barang
    public void tampilkanBarang() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Tidak ada barang di gudang.");
        } else {
            for (Barang barang : daftarBarang) {
                System.out.println(barang);
            }
        }
    }

    // Metode untuk mencari barang berdasarkan nama
    public Barang cariBarang(String nama) {
        for (Barang barang : daftarBarang) {
            if (barang.getNama().equalsIgnoreCase(nama)) {
                return barang;
            }
        }
        return null;
    }

    // Metode untuk mengedit barang
    public void editBarang(int id, String nama, int jumlah, double harga) {
        for (Barang barang : daftarBarang) {
            if (barang.getId() == id) {
                barang.setNama(nama);
                barang.setJumlah(jumlah);
                barang.setHarga(harga);
                break;
            }
        }
    }
}

// Kelas ManajerGudang
class ManajerGudang {
    private Gudang gudang;
    private Scanner scanner;
    private List<User> users;
    private User loggedInUser;

    // Constructor
    public ManajerGudang() {
        this.gudang = new Gudang();
        this.scanner = new Scanner(System.in);
        this.users = new ArrayList<>();
    }

    // Metode untuk registrasi pengguna
    public void registrasi() {
        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();
        users.add(new User(username, password));
        System.out.println("Registrasi berhasil!");
    }

    // Metode untuk login pengguna
    public boolean login() {
        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Login berhasil!");
                return true;
            }
        }
        System.out.println("Username atau password salah.");
        return false;
    }

    // Metode untuk logout pengguna
    public void logout() {
        loggedInUser = null;
        System.out.println("Anda telah logout.");
    }

    // Metode untuk menampilkan menu dan memilih opsi
    public void tampilkanMenu() {
        int pilihan;
        do {
            System.out.println("\nMenu Manajemen Gudang:");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Hapus Barang");
            System.out.println("3. Tampilkan Semua Barang");
            System.out.println("4. Cari Barang");
            System.out.println("5. Edit Barang");
            System.out.println("6. Logout");
            System.out.println("7. Keluar");
            System.out.print("Pilih opsi: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            switch (pilihan) {
                case 1:
                    tambahBarang();
                    break;
                case 2:
                    hapusBarang();
                    break;
                case 3:
                    gudang.tampilkanBarang();
                    break;
                case 4:
                    cariBarang();
                    break;
                case 5:
                    editBarang();
                    break;
                case 6:
                    logout();
                    break;
                case 7:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Opsi tidak valid. Silakan pilih opsi yang tersedia.");
            }
        } while (pilihan != 7);
    }

    // Metode untuk menambah barang
    private void tambahBarang() {
        System.out.print("Masukkan ID barang: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer
        System.out.print("Masukkan nama barang: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan jumlah barang: ");
        int jumlah = scanner.nextInt();
        System.out.print("Masukkan harga barang: ");
        double harga = scanner.nextDouble();

        Barang barang = new Barang(id, nama, jumlah, harga);
        gudang.tambahBarang(barang);
        System.out.println("Barang berhasil ditambahkan.");
    }

    // Metode untuk menghapus barang
    private void hapusBarang() {
        System.out.print("Masukkan ID barang yang akan dihapus: ");
        int id = scanner.nextInt();
        gudang.hapusBarang(id);
        System.out.println("Barang dengan ID " + id + " berhasil dihapus.");
    }

    // Metode untuk mencari barang
    private void cariBarang() {
        System.out.print("Masukkan nama barang yang dicari: ");
        String nama = scanner.nextLine();
        Barang barang = gudang.cariBarang(nama);
        if (barang != null) {
            System.out.println("Barang ditemukan: " + barang);
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    // Metode untuk mengedit barang
    private void editBarang() {
        System.out.print("Masukkan ID barang yang akan diedit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer
        System.out.print("Masukkan nama baru barang: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan jumlah baru barang: ");
        int jumlah = scanner.nextInt();
        System.out.print("Masukkan harga baru barang: ");
        double harga = scanner.nextDouble();

        gudang.editBarang(id, nama, jumlah, harga);
        System.out.println("Barang berhasil diedit.");
    }
}

// Kelas Utama
public class Main {
    public static void main(String[] args) {
        ManajerGudang manajerGudang = new ManajerGudang();
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Registrasi");
            System.out.println("2. Login");
            System.out.println("3. Keluar");
            System.out.print("Pilih opsi: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            switch (pilihan) {
                case 1:
                    manajerGudang.registrasi();
                    break;
                case 2:
                    if (manajerGudang.login()) {
                        manajerGudang.tampilkanMenu();
                    }
                    break;
                case 3:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Opsi tidak valid. Silakan pilih opsi yang tersedia.");
            }
        } while (pilihan != 3);
    }
}
