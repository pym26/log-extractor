package main;

import service.ILogService;
import service.impl.LogServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
	public static void main(String[] args) throws IOException, InterruptedException
	{
		final ILogService service = new LogServiceImpl();
		String dirPath = "";
		String from = "";
		String to = "";
		if (args[0].equals("-f"))
			from = args[1];
		else
			System.out.println("Please check command");

		if (args[2].equals("-t"))
			to = args[3];
		else
			System.out.println("Please check command");

		if (args[4].equals("-i"))
			dirPath = args[5];
		else
			System.out.println("Please check command");

		storeLogsOnEs(dirPath);
		service.read(from, to);
	}

	private static void storeLogsOnEs(final String dirName) throws IOException, InterruptedException
	{
		String command = "sudo sh /home/poojan/Personal/log-extractor/env/setup.sh " + dirName;
		ProcessBuilder pb = new ProcessBuilder("bash","-c", command);
		Process process = pb.start();
		StringBuilder output = new StringBuilder();

		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream()));

		String line;
		while ((line = reader.readLine()) != null) {
			output.append(line + "\n");
		}

		int exitVal = process.waitFor();
		if (exitVal == 0) {
			System.out.println("Success!");
			System.out.println(output);
		} else {
			System.out.println("Something went wrong:" + exitVal);
		}
	}
}
