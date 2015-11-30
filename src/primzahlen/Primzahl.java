package primzahlen;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;

/**
 * Das ist die einzige Klasse des Programms und steuert alle Ablaeufe der Primzahlenueberpruefung einer Zahl.
 * @author Lukas Schramm
 * @version 1.0
 */
public class Primzahl {
	
	private NumberFormat format = NumberFormat.getInstance(); 
	private NumberFormatter formatter = new NumberFormatter(format);
	private long abfragezahl;

	public Primzahl() {
		format.setGroupingUsed(false); 
		formatter.setAllowsInvalid(false);
		eingabe();
	}
	
	/**
	 * Diese Methode nimmt die Zahl in einem Eingabefeld an, die der Nutzer eingibt.<br>
	 * Sie fragt die Zahl ab, die der Nutzer auf ihre moegliche Primzahleneigenschaft ueberpruefen moechte.<br>
	 * Wenn der Nutzer nichts eingibt, wird eine Fehlermeldung ausgegeben.<br>
	 * Bei extrem grossen Werten wird der Nutzer gewarnt, dass die Rechenzeit laenger sein koennte.<br>
	 * Die Methode leitet direkt zu ausgabe() weiter, wo ermittelt wird, was dem Nutzer ausgegeben wird.
	 */
	private void eingabe() {
		JFormattedTextField nummernfeld = new JFormattedTextField(formatter);
		Object[] zahlenfrage = {"Bitte gib eine zu prüfende Zahl ein.", nummernfeld};
		JOptionPane pane = new JOptionPane(zahlenfrage, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
		pane.createDialog(null, "Primzahl?").setVisible(true);
		String zahlStr = nummernfeld.getText();
		try {
			if(zahlStr.equals("")) {
				JOptionPane.showMessageDialog(null, "Bitte gib eine natürliche Zahl ein!", "Ungültige Eingabe", JOptionPane.ERROR_MESSAGE);
				eingabe();
			} else {
				abfragezahl = Long.parseLong(zahlStr);
				if(abfragezahl < 1) {
					JOptionPane.showMessageDialog(null, "Bitte gib eine natürliche Zahl ein!", "Ungültige Eingabe", JOptionPane.ERROR_MESSAGE);
					eingabe();
				} else if(abfragezahl > 500000000 && abfragezahl%2 != 0) {
					JOptionPane.showMessageDialog(null, "Bitte beachte, dass die Berechnungszeit\nbei größeren Zahlen etwas länger sein kann.", "Ungültige Eingabe", JOptionPane.WARNING_MESSAGE);
					ausgabe();
				} else {
					ausgabe();
				}
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Bitte gib eine natürliche Zahl ein!", "Ungültige Eingabe", JOptionPane.ERROR_MESSAGE);
			eingabe();
		}
	}
	
	/**
	 * Diese Methode berechnet, ob es sich bei einer Zahl um eine Primzahl handelt oder nicht und gibt dies als boolean zurueck.
	 * @param zahl Nimmt die abzufragende Zahl an
	 * @return Gibt als boolean aus, ob es sich um eine Primzahl handelt oder nicht
	 */
	private boolean istPrimzahl(long zahl) {
		boolean prim = true;
		if(zahl == 1) {
			prim = false;
		} else if(zahl == 2) {
			prim = true;
		} else {
			if(zahl%2 == 0) {
				prim = false;
			} else {
				for(int n=3; n<=Math.sqrt(zahl); n+=2) {
					if(zahl%n == 0) {
						prim = false;
						break;
					}
				}
			}
		}
		
		if(prim == false) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Diese Methode gibt dem Nutzer das Ergebnis der Primzahlenabfrage aus und leitet ihn anschliessend in die neustart()-Methode weiter.
	 */
	private void ausgabe() {
		if(istPrimzahl(abfragezahl) == true) {
			JOptionPane.showMessageDialog(null, "Die "+abfragezahl+" ist eine Primzahl.", "Ergebnis", JOptionPane.PLAIN_MESSAGE);
			neustart();
		} else {
			JOptionPane.showMessageDialog(null, "Die "+abfragezahl+" ist keine Primzahl.", "Ergebnis", JOptionPane.PLAIN_MESSAGE);
			neustart();
		}
	}
	
	/**
	 * Diese Methode fragt den Nutzer, ob er eine weitere Zahl ueberpruefen moechte und startet je nach Nutzereingabe entweder eine neue Abfrage oder beendet das Programm.
	 */
	private void neustart() {
		int dialogneustart = JOptionPane.showConfirmDialog(null, "Möchtest Du eine neue Primzahl prüfen?", "Neue Abfrage?", JOptionPane.YES_NO_OPTION);
        if(dialogneustart == 0) {
     	   new Primzahl();
        } else {
     	   System.exit(0);
        }
	}
	
	public static void main(String[] args) {
		new Primzahl();
	}
}