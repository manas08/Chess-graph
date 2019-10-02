package cz.uhk.diplom.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import cz.uhk.diplom.MainWindow;
import cz.uhk.diplom.model.Edge;
import cz.uhk.diplom.model.Image;
import cz.uhk.diplom.model.Vertex;

public class Filer {


	
	public void load(MainWindow frame) {
		Image obrazek = null;
		List<Vertex> vertices = null;
		List<Edge> edges = null;
		try (
				FileInputStream in = new FileInputStream("obrazek.dat");
				ObjectInputStream ois = new ObjectInputStream(in);
				) {
			obrazek = (Image)ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (
				FileInputStream in = new FileInputStream("vertices.dat");
				ObjectInputStream ois = new ObjectInputStream(in);
				) {
			vertices = (List<Vertex>)ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (
				FileInputStream in = new FileInputStream("edges.dat");
				ObjectInputStream ois = new ObjectInputStream(in);
				) {
			edges = (List<Edge>)ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		frame.setLoadedData(obrazek, vertices, edges);
	}
	
	public void save(Image obrazek, List<Vertex> vertices, List<Edge> edges) {
		// try with resource
		try (
				FileOutputStream out = new FileOutputStream("vertices.dat");
				ObjectOutputStream oos = new ObjectOutputStream(out);
				) {
			oos.writeObject(vertices);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (
				FileOutputStream out = new FileOutputStream("obrazek.dat");
				ObjectOutputStream oos = new ObjectOutputStream(out);
				) {
			oos.writeObject(obrazek);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (
				FileOutputStream out = new FileOutputStream("edges.dat");
				ObjectOutputStream oos = new ObjectOutputStream(out);
				) {
			oos.writeObject(edges);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
