package com.metatech.crypto.exchange;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.metatech.JavaCat.Testslf4j;
import org.slf4j.Logger;

public class CryptoGateway {

    public static final String helpUsage = "CryptoGateway <FunctionClass> -DCONFIG=<cfg file name> -DTARGET=<exchange code>\n\n";
    private static final Logger logger = Testslf4j.getLogger(CryptoGateway.class);

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Please provide a config file name as command-line argument.");
            return;
        }
        logger.info(CryptoGateway.class.getName() + "::" + args.toString());

        String configFileName = args[0];

        List<String> classNames = readClassNamesFromConfigFile(configFileName);
        for (String className : classNames) {
            System.out.println("Running main method of " + className);
            Class<?> clazz = Class.forName(className);
            String[] classArgs = new String[args.length - 1];
            System.arraycopy(args, 1, classArgs, 0, args.length - 1);
            clazz.getMethod("main", String[].class).invoke(null, (Object) classArgs);

        }
    }

    private static List<String> readClassNamesFromConfigFile(String configFile) {
        List<String> classNames = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(configFile));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty() && !line.startsWith("#")) {
                    classNames.add(line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Config file not found: " + configFile);
            System.exit(1);
        }
        return classNames;
    }
}
