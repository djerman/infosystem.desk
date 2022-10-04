package rs.atekom.infosystem.desk.app.pomocne;

import java.math.BigDecimal;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class TekstDecimalni extends TextField{
	
	public TekstDecimalni() {

		focusedProperty().addListener((observable, oldValue, newValue) -> {
			if(oldValue) {
				try {
					setText(String.format("%,.2f", vratiDecimalniBroj(getText())));
					setStyle("");
					}catch (Exception e) {
						setText("0,00");
						setStyle("-fx-background-color: lightpink");
						vratiUpozorenje();
						}
				}
			});
		setAlignment(Pos.CENTER_RIGHT);
		}

	public TekstDecimalni(Boolean viseCifara, String boja, Boolean editable, Double maxWidth) {
		Boolean cifre = viseCifara;
		String pozadina = "";
		switch (boja) {
		case "bela": 
			pozadina = "-fx-background-color: white";
			break;
		case "zelena":
			pozadina = "-fx-background-color: mediumaquamarine";
			break;
		case "plava":
			pozadina = "-fx-background-color: deepskyblue";
			break;
		case "podrazumevana":
			pozadina = "-fx-background-color: #EFEFC0";
			break;
		case "":
			pozadina = "-fx-background-color: #EFEFC0";
			break;
		default:
			//System.out.println("boja pozadine je " + pozadina);
			}
		setEditable(editable);
		setStyle(pozadina);
		if(maxWidth != null) {
			setMaxWidth(maxWidth);
			}
		final String bojaPozadine = pozadina;
		
		if(cifre) {
			setText("0,0000");
			}else {
				setText("0,00");
				}
		focusedProperty().addListener((observable, oldValue, newValue) -> {
			if(oldValue) {
				try {
					if(cifre) {
						setText(String.format("%,.2f", vratiDecimalniBroj(getText())));
						}else {
							setText(String.format("%,.2f", vratiDecimalniBroj(getText())));
							}
					setStyle(bojaPozadine);
					}catch (Exception e) {
						setStyle("-fx-background-color: lightpink");
						vratiUpozorenje();
						}
				}
			setAlignment(Pos.CENTER_RIGHT);
			});
		setAlignment(Pos.CENTER_RIGHT);
		}
	
	public String vratiBroj() {
		try {
			String tekst = getText();
			String brojPrvi = tekst.replace(".", "");
			String broj = brojPrvi.replace(",", ".");
			return broj;
			}catch (Exception e) {
				return null;
				}
		}
	
	public String vratiBroj(String tekst) {
		try {
			String brojPrvi = tekst.replace(".", "");
			String broj = brojPrvi.replace(",", ".");
			return broj;
			}catch (Exception e) {
				//vratiUpozorenje();
				return null;
				}
		}
	
	public String vratiFormatiranBroj(BigDecimal broj) {
		try {
			String tekst = String.format("%,.2f", broj);
			return tekst;
			}catch (Exception e) {
				//vratiUpozorenje();
				return null;
				}
		}
	
	public String vratiFormatiranBroj(Double broj) {
		try {
			String tekst = String.format("%,.2f", broj);
			return tekst;
			}catch (Exception e) {
				//vratiUpozorenje();
				return null;
				}
		}
	
	public BigDecimal vratiDecimalniBroj(String broj) {
		try {
			String brojTekst = broj.replace(".", "");
			brojTekst = brojTekst.replace(",", ".");
			return new BigDecimal(brojTekst);
			}catch (Exception e) {
				//vratiUpozorenje();
				return new BigDecimal("0.00	");
				}
		}
	
	public Double vratiDoubleBroj(String broj) {
		try {
			String brojTekst = broj.replace(".", "");
			brojTekst = brojTekst.replace(",", ".");
			return Double.parseDouble(brojTekst);
			}catch (Exception e) {
				//vratiUpozorenje();
				return null;
				}
    	}
	
	private void vratiUpozorenje() {
		setText("");
		Alert error = new Alert(Alert.AlertType.ERROR);
		error.setAlertType(Alert.AlertType.ERROR);
		error.setTitle("Грешка уноса");
		error.setHeaderText("Неисправан унос");
		error.showAndWait();
		requestFocus();
		}
	/*
	 * proba
	private void azurirajFormat(String vrednost) {
		this.setText(decimalni.format(Double.valueOf(vrednost)).toString());
	}
	*/
}
