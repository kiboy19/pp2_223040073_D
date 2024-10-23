import javax.swing.*;
import java.awt.event.*;

public class WindowListenerExample {
    public static void main(String[] args) {
        // Membuat frame
        JFrame frame = new JFrame("WindowListener Example");

        // Membuat label untuk menampilkan pesan
        JLabel label = new JLabel("Lakukan operasi pada jendela.");
        label.setBounds(50, 50, 300, 30);

        // Menambahkan WindowListener ke frame
        frame.addWindowListener(new WindowListener() {
            // Dijalankan ketika jendela dibuka
            public void windowOpened(WindowEvent e) {
                label.setText("Window Opened.");
            }

            // Dijalankan ketika jendela benar-benar ditutup
            public void windowClosed(WindowEvent e) {
                System.out.println("Window Closed.");
            }

            // Dijalankan ketika jendela diminimalkan
            public void windowIconified(WindowEvent e) {
                label.setText("Window Minimized.");
            }

            // Dijalankan ketika jendela dipulihkan dari minimal
            public void windowDeiconified(WindowEvent e) {
                label.setText("Window Restored.");
            }

            // Dijalankan ketika jendela menjadi aktif (berfokus)
            public void windowActivated(WindowEvent e) {
                label.setText("Window Activated.");
            }

            // Dijalankan ketika jendela kehilangan fokus
            public void windowDeactivated(WindowEvent e) {
                label.setText("Window Deactivated.");
            }

            // Dibutuhkan tetapi tidak digunakan di sini
            public void windowClosing(WindowEvent e) {}
        });

        // Menambahkan label ke frame
        frame.add(label);

        // Setting frame
        frame.setSize(400, 200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Agar jendela benar-benar tertutup
    }
}
