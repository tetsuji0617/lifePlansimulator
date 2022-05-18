package bean;

import lombok.Data;

@Data
public class Dataset {

	String type;

	String label;

	String data[];

	String backgroundColor;

	int backgroundColorRed;

	int backgroundColorBlue;

	int backgroundColorGreen;

	float backgroundColorAlpha;
}
