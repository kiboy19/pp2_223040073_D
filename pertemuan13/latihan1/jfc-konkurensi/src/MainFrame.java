import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame {
    public static void main(String[] args) {
        // Membuat frame utama
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Contoh Konkurensi di Swing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLayout(new BorderLayout());

            // Label untuk status
            JLabel statusLabel = new JLabel("Tekan tombol untuk mulai tugas berat.", JLabel.CENTER);

            // Tombol untuk memulai proses
            JButton startButton = new JButton("Mulai");

            // Progress bar
            JProgressBar progressBar = new JProgressBar(0, 60);
            progressBar.setValue(0);
            progressBar.setStringPainted(true);

            // Tambahkan komponen ke frame
            frame.add(statusLabel, BorderLayout.NORTH);
            frame.add(progressBar, BorderLayout.CENTER);
            frame.add(startButton, BorderLayout.SOUTH);

            // Tombol aksi
            startButton.addActionListener(e -> {
                // Update progress bar 1% per detik
                new SwingWorker<Void, Integer>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        for (int i = 0; i <= 60; i++) {
                            Thread.sleep(100); // Simulasi kerja berat
                            publish(i);
                        }
                        return null;
                    }

                    @Override
                    protected void process(List<Integer> chunks) {
                        for (int value : chunks) {
                            progressBar.setValue(value);
                        }
                    }

                    @Override
                    protected void done() {
                        statusLabel.setText("Tugas selesai!");
                    }
                }.execute();
            });

            // Tampilkan frame
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
