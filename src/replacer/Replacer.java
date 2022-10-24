package replacer;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Replacer {
	final String dir = System.getProperty("user.dir");

	File f = new File(dir);

	Zamens zm = new Zamens(dir);

	public void replace() {
		// System.out.println(dir);
		FilenameFilter textFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".txt");
			}
		};

		File[] files = f.listFiles(textFilter);
		String content;
		Charset charset = StandardCharsets.UTF_8;
		try {

			for (File e : files) {
				// System.out.println(e.getPath().toString());
				content = new String(Files.readAllBytes(e.toPath()), charset);

				for (Zamena z : zm.getList()) {

					content = content.replaceAll(z.getOldstr(), z.getNewstr());
				}

				Files.write(e.toPath(), content.getBytes(charset));
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
