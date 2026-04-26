Client · JAVA
Copiar

import com.zeroc.Ice.*;
 
public class Client {
    public static void main(String[] args) {
        try (Communicator communicator = Util.initialize(args)) {
 
            // ── Printer ──────────────────────────────────────────────────────
            ObjectPrx base = communicator.stringToProxy(
                "SimplePrinter1:default -h 98.90.53.6 -p 5678"
            );
            Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);
            if (printer == null) {
                throw new Error("Invalid proxy");
            }
 
            // Método original
            String r1 = printer.printString("Hello from Goiania!");
            System.out.println("Server responded: " + r1);
 
            // Novo método: printUpperCase
            String r2 = printer.printUpperCase("hello from Goiania!");
            System.out.println("Server responded: " + r2);
 
            // Novo método: printCount
            String r3 = printer.printCount("Hello from Goiania!");
            System.out.println("Server responded: " + r3);
 
            ObjectPrx baseScanner = communicator.stringToProxy(
                "SimpleScanner:default -h 98.90.53.6 -p 5678"
            );
            Demo.ScannerPrx scanner = Demo.ScannerPrx.checkedCast(baseScanner);
            if (scanner == null) {
                throw new Error("Invalid proxy");
            }
 
            System.out.println("Status: " + scanner.getStatus());
 
            String doc = scanner.scanDocument("relatorio.pdf");
            System.out.println("Scan result: " + doc);
 
            System.out.println("Status: " + scanner.getStatus());
 
            scanner.resetScanner();
            System.out.println("Status apos reset: " + scanner.getStatus());
 
        } catch (LocalException e) {
            e.printStackTrace();
        }
    }
}