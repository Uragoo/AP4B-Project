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
	private JLabel lInfo, lFeuilleRoute;
	private Border jspBorder, outsideBorder, insideBorder;
	private DefaultListModel<String> liFeuilleRoute, liInfo;
	private JList<?> jliFeuilleRoute, jliInfo;
	private JScrollPane spInfo;
	
	public PanelInfo() {
		super();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBackground(Color.LIGHT_GRAY);
		
		lInfo = new JLabel("Informations sur l'itinéraire :");
		lInfo.setFont(lInfo.getFont().deriveFont(Font.BOLD));
		//lInfo.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		add(lInfo);
		
		liFeuilleRoute = new DefaultListModel<>();
		jliFeuilleRoute = new JList<>(liFeuilleRoute);
		
		liInfo = new DefaultListModel<>();
		jliInfo = new JList<>(liFeuilleRoute);
		
		spInfo = new JScrollPane(jliInfo);
		spInfo.setPreferredSize(new Dimension(250, screenSize.height - 400));
		add(spInfo);
		
		liFeuilleRoute.addElement("Feuille de route :");
	}
	
}
