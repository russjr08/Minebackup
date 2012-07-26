package com.kronosad.minebackup;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainWindow {

	private JFrame frmMinebackup;

	/**
	 * Launch the application.
	 */
	public static String computerOS;
	public static String userPath;
	private static boolean manualPathActivated = false;
	public static String manualPath = "Nonthing";

	public static void main(String[] args) {

		if (!(args.length == 0)) {
			if (args[0].contains("MinecraftPath")) {
				if (!(args[1].length() == 0)) {
					userPath = args[1];
					System.out.println("Using the path: " + args[1]);
					manualPathActivated = true;
					manualPath = args[1];

				} else {
					System.out.println("You forgot an argument. The path to MC");
				}
			}
		}

		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			System.out.println("[Debug] Setting System Look and Feel to: "
					+ UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException error) {
			JOptionPane
					.showMessageDialog(
							null,
							"Sorry, we could not set the look and feel. Window may look bad.",
							"Error!", JOptionPane.ERROR_MESSAGE);
			// If the user's OS isn't supported, we'll throw an error saying the
			// Look and feel isn't supported. Lets throw a stacktrace too!
			error.printStackTrace();
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmMinebackup.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {
			computerOS = whichOS();
		} catch (OSNotSupportedException error) {
			error.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmMinebackup = new JFrame();
		frmMinebackup.setTitle("MineBackup");
		frmMinebackup.setResizable(false);
		frmMinebackup.setBounds(100, 100, 529, 388);
		frmMinebackup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMinebackup.getContentPane().setLayout(null);

		JLabel lblToBeginClick = new JLabel(
				"To begin, click set default location, we'll figure out where .minecraft is!");
		lblToBeginClick.setBounds(58, 25, 397, 14);
		frmMinebackup.getContentPane().add(lblToBeginClick);

		final JButton btnSetLocation = new JButton("Set Default Location");

		btnSetLocation.setBounds(27, 70, 148, 23);
		frmMinebackup.getContentPane().add(btnSetLocation);
		if (manualPathActivated) {
			btnSetLocation.setEnabled(false);

		}

		final JTextField txtPath = new JTextField();
		txtPath.setText("This will contain the path");
		txtPath.setEditable(false);
		txtPath.setBounds(250, 71, 239, 22);
		frmMinebackup.getContentPane().add(txtPath);
		txtPath.setColumns(10);
		if (manualPathActivated) {
			txtPath.setText(manualPath);

		}

		final JButton btnBackup = new JButton("Backup");
		btnBackup.setEnabled(false);
		btnBackup.setBounds(112, 179, 89, 23);
		frmMinebackup.getContentPane().add(btnBackup);
		if(manualPathActivated){
			btnBackup.setEnabled(true);
		}
		

		final JButton btnRestore = new JButton("Restore");
		btnRestore.setEnabled(false);
		btnRestore.setBounds(318, 179, 89, 23);
		frmMinebackup.getContentPane().add(btnRestore);
		if(manualPathActivated){
			btnRestore.setEnabled(true);
		}

		JLabel lblNowAfterYouve = new JLabel(
				"Now, after you've set the location, choose an operation");
		lblNowAfterYouve.setBounds(112, 121, 275, 14);
		frmMinebackup.getContentPane().add(lblNowAfterYouve);

		btnSetLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnRestore.setEnabled(true);
				btnBackup.setEnabled(true);

				try {
					userPath = System.getProperty("user.home");
					txtPath.setText(userPath);
				} catch (SecurityException error) {
					JOptionPane.showMessageDialog(null,
							"We are not allowed to access your home path :(",
							"Security Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				FileOperations fo = new FileOperations(userPath);
				fo.backupMinecraft();
			}
		});
	}

	public static String whichOS() throws OSNotSupportedException {

		if (CheckOS.isWindows()) {
			return "windows";
		} else if (CheckOS.isMac()) {
			return "mac";
		} else if (CheckOS.isLinuxUnix()) {
			return "nix";
		} else if (CheckOS.isSolaris()) {
			return "sunos";
		} else {
			System.out.println("The detected Operating System was: "
					+ System.getProperty("os.name"));
			throw new OSNotSupportedException();
		}
	}

}
