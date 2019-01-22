/*
 * Esta clase se utiliza como contenedor de muchos metodos que son útiles en la
 * programacion y los necesito para el trabajo que estoy realizando
 */
package myclass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author carlos860920
 */
public class herramientas {
    public herramientas() {
    }   
    
    /**
     * Método útil para validar correos electronicos
     * @param email
     * @return 
     */
    public static boolean validarEmailFuerte(String email){        
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"; 
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);        
        return matcher.matches();
    }
    
    /**
     * Método que debulve el <b>Numero de Serie de la MotherBoard</b> en los
     * sistemas operativos <b>Windows</b><b>Linux</b>
     * @return 
     */
    public static String getSystemMotherBoard_SerialNumber() {
        try {
            String OSName = System.getProperty("os.name");
            if (OSName.contains("Windows")) {
                return (getWindowsMotherboard_SerialNumber());
            } else {
                return (GetLinuxMotherBoard_serialNumber());
            }
        } catch (Exception E) {
            System.err.println("System MotherBoard Exp : " + E.getMessage());
            return null;
        }
    }

    /**
     * Método para saber el Numero de Serie de la MotherBoard en Windows
     *
     * @return
     */
    private static String getWindowsMotherboard_SerialNumber() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("wmic", "bios", "get", "SerialNumber");
        Process process = pb.start();
        process.waitFor();
        String serialNumber = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                process.getInputStream()))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                if (line.length() < 1 || line.startsWith("SerialNumber")) {
                    continue;
                }
                serialNumber = line;
                break;
            }
        }
        return serialNumber;
        
    }

    /**
     * Método para saber el Numero de Serie de la MotherBoard en Linux
     *
     * @return
     */
    private static String GetLinuxMotherBoard_serialNumber() {
        String command = "dmidecode -s baseboard-serial-number";
        String sNum = null;
        try {
            Process SerNumProcess = Runtime.getRuntime().exec(command);
            BufferedReader sNumReader = new BufferedReader(new InputStreamReader(SerNumProcess.getInputStream()));
            sNum = sNumReader.readLine().trim();
            SerNumProcess.waitFor();
            sNumReader.close();
        } catch (IOException | InterruptedException ex) {
            System.err.println("Linux Motherboard Exp : " + ex.getMessage());
            sNum = null;
        }
        return sNum;
    }

    /**
     * Método para conocer el número de Serie de una unidad de Almacenamiento
     * @param drive
     * @return 
     */
    public static String getSerialNumber(String drive) {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                    + "Set colDrives = objFSO.Drives\n"
                    + "Set objDrive = colDrives.item(\"" + drive + "\")\n"
                    + "Wscript.Echo objDrive.SerialNumber";  // see note
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }

}
