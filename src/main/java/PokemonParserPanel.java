
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//TODO add thread support
@SuppressWarnings("serial")
public class PokemonParserPanel extends JPanel implements ActionListener {
	private final String WEBSITE = "https://pokeapi.co/api/v2/pokemon/";
	private JComboBox<String> pokemonChoser, attributesChooser;
	private JButton btnGo, btnClear;// TODO add chooser for attributes
	private JLabel pokePicture;
	private JTextArea txtArea;
	private String[] pokemonNamesArray, pokemonAttributesArray; //TODO
	private Networking net;
	
	public PokemonParserPanel() {
		this.setLayout(new BorderLayout());
		JPanel controlPanel = new JPanel();
		//setters of panel attributes
		net = null;
		pokePicture = new JLabel("Picutre here");
		btnGo = new JButton("Go");
		btnClear = new JButton("clear");
		txtArea = new JTextArea();
		txtArea.setEditable(false);
		txtArea.setFont(txtArea.getFont().deriveFont(Font.BOLD, txtArea.getFont().getSize()));
		JScrollPane scrollPane = new JScrollPane(txtArea);
		
		WebScrapper web = new WebScrapper();
		pokemonNamesArray = web.getPokemonArray(); // TODO use jsoup 
		pokemonChoser = new JComboBox<String>(pokemonNamesArray);
		pokemonChoser.addActionListener(this);//TODO
		
		pokemonAttributesArray = new String[] {"abilities","base-exp"}; // TODO use jsoup
		attributesChooser = new JComboBox<String>(pokemonAttributesArray);
		attributesChooser.addActionListener(this);
		
		btnGo.addActionListener(this);
		btnClear.addActionListener(this);
		
		controlPanel.add(pokePicture);
		controlPanel.add(pokemonChoser);
		controlPanel.add(attributesChooser);
		controlPanel.add(btnGo);
		controlPanel.add(btnClear);
		
		
		this.add(controlPanel , BorderLayout.NORTH);
		this.add(scrollPane , BorderLayout.CENTER);	
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String pokemonURL = WEBSITE + (String) pokemonChoser.getSelectedItem();
		String actionSelect = (String) attributesChooser.getSelectedItem();
		net = new Networking(pokemonURL);
		
		if(e.getSource() == btnGo) {
			String JSONtoParse = net.getJSON();
			
			if(actionSelect.equals("abilities")) {
				String parsedAbilities = PokemonParser.parseAbilities(JSONtoParse);
				txtArea.append(pokemonChoser.getSelectedItem()+parsedAbilities);
			}
			if(actionSelect.equals("base-exp")) {
				int parseBaseExp = PokemonParser.parseBaseExp(JSONtoParse);
				txtArea.append(""+pokemonChoser.getSelectedItem()+"'s base exp: "+parseBaseExp+"\n");
			}
		}
		if(e.getSource() == btnClear) {
			txtArea.setText(null);
		}
	}

}
