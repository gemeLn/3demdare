package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadWrite {
	public void overwrite(String path, String text) {
		try {
			BufferedWriter p = new BufferedWriter(new FileWriter(new File(path)));
			p.write(text);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void println(String path, String text) {
		print(path, text, true);
	}

	public void print(String path, String text) {
		print(path, text, false);
	}

	public void print(String path, String text, boolean newline) {
		try {
			BufferedWriter p = new BufferedWriter(new FileWriter(new File(path), true));
			p.write(text);
			if (newline)
				p.newLine();
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> read(String input, boolean insideJar) {
		ArrayList<String> ret = new ArrayList<String>();
		String line;
		try {
			BufferedReader read;
			if (!insideJar) {
				read = new BufferedReader(new FileReader(new File(input)));
			} else {
				read = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(input)));
			}
			while ((line = read.readLine()) != null) {
				if(line.charAt(0)!='/')
					ret.add(line);
			}
			read.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public ArrayList<String> read(String path) {
		return read(path, false);
	}
}
