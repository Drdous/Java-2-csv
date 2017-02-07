package cz.unicorncollege.bt.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.unicorncollege.bt.model.MeetingCentre;
import cz.unicorncollege.bt.model.MeetingRoom;

public class FileParser {

	/**
	 * Method to import data from the chosen file.
	 * 
	 * @throws IOException
	 */

	public static List<MeetingCentre> importData() throws IOException {

		String locationFilter = Choices.getInput("Enter path of imported file: ");

		List<MeetingCentre> allMeetingCentres = new ArrayList<>();
		// TODO: Nacist data z importovaneho souboru

		String line = "";
		BufferedReader br = null;
		// /Users/Leos/Documents/workspace/xa02/ImportData2.csv
		try {
			br = new BufferedReader(new FileReader(locationFilter));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if (data[0].equals("MEETING_CENTRES")) {
					continue;
				}
				if (data[0].equals("MEETING_ROOMS")) {
					break;
				}

				allMeetingCentres.add(new MeetingCentre(data[0], data[1], data[2]));
			}

			while ((line = br.readLine()) != null) {

				String[] data = line.split(",");
				for (MeetingCentre mc : allMeetingCentres) {
					if (data[5].equals(mc.getCode())) {
						mc.addMeetingRooms(new MeetingRoom(data[0], data[1], data[2], Integer.parseInt(data[3]),
								data[4].equals("YES"), mc));
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println();

		System.out.println("**************************************************");
		System.out.println("Data was imported. " + allMeetingCentres.size() + " objects of MeetingCentres was loaded");
		System.out.println("**************************************************");

		System.out.println();

		return allMeetingCentres;
	}

	/**
	 * Method to save the data to file.
	 * 
	 * @throws IOException
	 */

	public static void saveData(List<MeetingCentre> meetingCentres) throws IOException {
		// TODO: ulozeni dat do souboru
		File soubor = new File("SavedData.csv");
		FileWriter writer = null;
		try {
			soubor.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			writer = new FileWriter(soubor);
			writer.write("MEETING_CENTRES,,,,," + "\n");
			for (MeetingCentre mc : meetingCentres) {
				writer.write(mc.toCSV());

			}
			writer.write("MEETING_ROOMS,,,,," + "\n");
			for (MeetingCentre mc : meetingCentres) {
				for (MeetingRoom mr : mc.getMeetingRooms()) {
					writer.write(mr.toCSV());

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}

		System.out.println();

		System.out.println("**************************************************");
		System.out.println("Data was saved correctly.");
		System.out.println("**************************************************");

		System.out.println();
	}

	/**
	 * Method to load the data from file.
	 * 
	 * @return
	 * @throws IOException
	 */
	public static List<MeetingCentre> loadDataFromFile() throws IOException {
		// TODO: nacist data ze souboru
		List<MeetingCentre> allMeetingCentres = new ArrayList<>();
		String line = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("SavedData.csv"));
			while ((line = br.readLine()) != null) {
				if (line.equals("MEETING_CENTRES,,,,,")) {
					continue;
				}
				if (line.equals("MEETING_ROOMS,,,,,")) {
					break;
				}

				String[] data = line.split(",");
				allMeetingCentres.add(new MeetingCentre(data[0], data[1], data[2]));
			}

			while ((line = br.readLine()) != null) {

				String[] data = line.split(",");
				for (MeetingCentre mc : allMeetingCentres) {
					if (data[5].equals(mc.getCode())) {
						mc.addMeetingRooms(new MeetingRoom(data[0], data[1], data[2], Integer.parseInt(data[3]),
								data[4].equals("YES"), mc));
					}
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println();

		System.out.println("**************************************************");
		System.out.println("Data was loaded correctly.");
		System.out.println("**************************************************");

		System.out.println();

		return allMeetingCentres;
	}
}
