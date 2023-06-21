import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class PanelInfo extends JPanel{
	private JLabel lInfo;
	private DefaultListModel<String> liCommandes;
	private JList<?> jliInfo;
	private JScrollPane spInfo;

	public PanelInfo() {
		super();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Border lineBorder = BorderFactory.createLineBorder(Color.black, 1);
		setBorder(lineBorder);
		setBackground(Color.LIGHT_GRAY);
		String listeCommande = "Liste des commandes :";
		lInfo = new JLabel(listeCommande);
		lInfo.setFont(lInfo.getFont().deriveFont(Font.BOLD));
		add(lInfo);

		liCommandes = new DefaultListModel<>();

		jliInfo = new JList<>(liCommandes);

		spInfo = new JScrollPane(jliInfo);
		spInfo.setPreferredSize(new Dimension(450, screenSize.height - 400));
		add(spInfo);

		liCommandes.addElement(" ");
		liCommandes.addElement("Press 'a' and left click to add a new vertex");
		liCommandes.addElement("Press 'r' and click on a vertex to remove it");
		liCommandes.addElement("Press 'e' and click on 2 different vertex to\n create an edge");
		liCommandes.addElement("Press 't' and click on 2 vertex to remove the edge linking them");
		liCommandes.addElement("Press 's' and click on a vertex to set the starting vertex");
		liCommandes.addElement("Press 'f' and click on a vertex to set the ending vertex");
		liCommandes.addElement("Press 'p' when there are a starting and ending vertex to highlight the shortest path");
	}
}