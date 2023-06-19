import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class PanelSettings extends JPanel {
	private JPanel conteneurBottom;
	private GridBagConstraints contraintes;
	private JLabel lDepart, lArrivee, lVille, lRue, lPoint;
	private JComboBox<?> cbVilleDepart, cbVilleArrivee, cbRueDepart, cbRueArrivee, cbPointDepart, cbPointArrivee;
	
	public PanelSettings() {
		super();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Border lineBorder = BorderFactory.createLineBorder(Color.black, 1);
		setBorder(lineBorder);
		
		conteneurBottom = new JPanel(new GridBagLayout());
		conteneurBottom.setMaximumSize(new Dimension(screenSize.width, 200));
		conteneurBottom.setBackground(Color.LIGHT_GRAY);
		
		contraintes = new GridBagConstraints();
		contraintes.insets = new Insets(5, 5, 5, 5);
		//contraintes.anchor = GridBagConstraints.LINE_START;
		//contraintes.weightx = 0.0;
		
		// Ajout des Label de départ et d'arrivée
		lDepart = new JLabel("Départ");
		lDepart.setFont(lDepart.getFont().deriveFont(Font.BOLD));
		contraintes.gridx = 1;
		contraintes.gridy = 0;
		conteneurBottom.add(lDepart, contraintes);
		
		lArrivee = new JLabel("Arrivée");
		lArrivee.setFont(lArrivee.getFont().deriveFont(Font.BOLD));
		contraintes.gridx = 2;
		contraintes.gridy = 0;
		conteneurBottom.add(lArrivee, contraintes);
		
		// Ajout des Component pour le choix de la ville
		lVille = new JLabel("Ville");
		lVille.setFont(lVille.getFont().deriveFont(Font.BOLD));
		contraintes.gridx = 0;
		contraintes.gridy = 1;
		conteneurBottom.add(lVille, contraintes);
		
		cbVilleDepart = new JComboBox<>();
		contraintes.gridx = 1;
		contraintes.gridy = 1;
		conteneurBottom.add(cbVilleDepart, contraintes);
		
		cbVilleArrivee = new JComboBox<>();
		contraintes.gridx = 2;
		contraintes.gridy = 1;
		conteneurBottom.add(cbVilleArrivee, contraintes);
		
		// Ajout des Component pour le choix de la Rue
		lRue = new JLabel("Rue");
		lRue.setFont(lRue.getFont().deriveFont(Font.BOLD));
		contraintes.gridx = 0;
		contraintes.gridy = 2;
		conteneurBottom.add(lRue, contraintes);
		
		cbRueDepart = new JComboBox<>();
		contraintes.gridx = 1;
		contraintes.gridy = 2;
		conteneurBottom.add(cbRueDepart, contraintes);
		
		cbRueArrivee = new JComboBox<>();
		contraintes.gridx = 2;
		contraintes.gridy = 2;
		conteneurBottom.add(cbRueArrivee, contraintes);
		
		// Ajout des Component pour le choix de la Point
		lPoint = new JLabel("Point");
		lPoint.setFont(lPoint.getFont().deriveFont(Font.BOLD));
		contraintes.gridx = 0;
		contraintes.gridy = 3;
		conteneurBottom.add(lPoint, contraintes);
		
		cbPointDepart = new JComboBox<>();
		contraintes.gridx = 1;
		contraintes.gridy = 3;
		conteneurBottom.add(cbPointDepart, contraintes);
		
		cbPointArrivee = new JComboBox<>();
		contraintes.gridx = 2;
		contraintes.gridy = 3;
		conteneurBottom.add(cbPointArrivee, contraintes);
		
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		add(conteneurBottom);
		
	}
}